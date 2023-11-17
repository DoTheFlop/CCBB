package com.D104.ccbb.payment_history.service;

import com.D104.ccbb.jwt.service.JwtTokenService;
import com.D104.ccbb.notification.dto.NotificationRequestDto;
import com.D104.ccbb.payment_history.domain.PaymentHistory;
import com.D104.ccbb.payment_history.dto.PaymentHistoryDto;
import com.D104.ccbb.payment_history.repo.PaymentHistoryRepo;
import com.D104.ccbb.post.repo.PostRepo;
import com.D104.ccbb.user.domain.User;
import com.D104.ccbb.user.repository.UserRepository;
import com.D104.ccbb.vote.domain.Vote;
import com.D104.ccbb.vote.repo.VoteRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class PaymentHistoryService {

    @Value("${kakao.adminKey}")
    private String KAKAO_ADMIN_KEY;

    private final String READY_KAKAO_URL = "https://kapi.kakao.com/v1/payment/ready";
    private final String APPROVE_KAKAO_URL = "https://kapi.kakao.com/v1/payment/approve";
    private final String CANCEL_KAKAO_URL = "https://kapi.kakao.com/v1/payment/cancel";

    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;
    private final PaymentHistoryRepo paymentHistoryRepo;
    private final VoteRepo voteRepo;
    private final PostRepo postRepo;
    private final ApplicationEventPublisher eventPublisher;

    public String addPayment(int postId, int price, String authorization) throws
            Exception {
        // 결제하는 유저 정보 불러오기
        String userEmail = jwtTokenService.getUserEmail(jwtTokenService.extractToken(authorization));
        Optional<User> byEmail = userRepository.findByEmail(userEmail);
        if (byEmail.isEmpty()) {
            throw new Exception("존재하지 않는 유저입니다.");
        }
        log.info("userId : {}", byEmail.get().getUserId().toString());
        String partnerOrderId = UUID.randomUUID().toString();
        // 카카오 페이 API에 결제 요청
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + KAKAO_ADMIN_KEY);
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("cid", "TC0ONETIME");
        requestBody.add("partner_order_id", partnerOrderId);
        requestBody.add("partner_user_id", byEmail.get().getUserId().toString());
        requestBody.add("item_name", price + "포인트");
        requestBody.add("quantity", "1");
        requestBody.add("total_amount", String.valueOf(price));
        requestBody.add("tax_free_amount", String.valueOf(price));
        // 성공, 실패, 취소시 리다이렉트 주소 설정
        // todo: 각 주소를 결제 성공시 db에 결제 정보 넣는 백엔드 API로 설정, 그 후 해당 API에서 프론트 결제 완료창으로 리다이렉트 시키기
        requestBody.add("approval_url",
                String.format("https://ccbb.pro/api/payment/success?price=%d&postId=%d&userId=%d", price, postId,
                        byEmail.get().getUserId()));
        requestBody.add("cancel_url", "https://ccbb.pro/api/payment/cancel");
        requestBody.add("fail_url", "https://ccbb.pro/api/payment/fail");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> Kakaoresponse = restTemplate.exchange(
                READY_KAKAO_URL,
                HttpMethod.POST, entity, Map.class);
        log.info("response headers : {}", Kakaoresponse.getHeaders());
        log.info("response body : {}", Kakaoresponse.getBody());
        Map<String, String> body = Kakaoresponse.getBody();
        log.info("redirect_uri: {}", body.get("next_redirect_pc_url"));

        Optional<Vote> byId = voteRepo.findByPostId_PostId(postId);
        if (byId.isEmpty()) {
            throw new Exception("존재하지 않는 투표입니다.");
        }
        // 결제 시도 정보 DB에 저장
        PaymentHistory readyPay = PaymentHistory.builder()
                .voteId(byId.get())
                .userId(byEmail.get())
                .amount(price)
                .isReturned(false)
                .payDate(LocalDateTime.now())
                .tid(body.get("tid"))
                .partnerOrderId(partnerOrderId)
                .isApprove(false)
                .build();

        paymentHistoryRepo.save(readyPay);

        return body.get("next_redirect_pc_url");
    }

    public boolean approvePayment(Integer userId, int postId, int price, String pgToken) throws Exception {
        Optional<Vote> byPostIdPostId = voteRepo.findByPostId_PostId(postId);
        if (byPostIdPostId.isEmpty()) {
            throw new Exception("존재하지 않는 투표입니다.");
        }
        // 결제 시도 정보 DB에서 조회
        PaymentHistory readyPayment = paymentHistoryRepo.findByUserId_UserIdAndVoteId_VoteId(userId,
                byPostIdPostId.get().getVoteId());
        log.info("readyPayment: {}", readyPayment);
        // 카카오 페이 API에 결제 요청
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + KAKAO_ADMIN_KEY);
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("cid", "TC0ONETIME");
        requestBody.add("tid", readyPayment.getTid());
        requestBody.add("partner_order_id", readyPayment.getPartnerOrderId());
        requestBody.add("partner_user_id", userId.toString());
        requestBody.add("pg_token", pgToken);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> Kakaoresponse = restTemplate.exchange(
                APPROVE_KAKAO_URL,
                HttpMethod.POST,
                entity,
                Map.class);
        log.info("response headers : {}", Kakaoresponse.getHeaders());
        log.info("response body : {}", Kakaoresponse.getBody());
        readyPayment.setApprove(true);
        paymentHistoryRepo.save(readyPayment);
        Vote vote = byPostIdPostId.get();
        if (vote.getUserId1().getUserId().equals(userId)) {
            vote.setAccept1(true);
        }
        if (vote.getUserId2().getUserId().equals(userId)) {
            vote.setAccept2(true);
            // user1에게 승락했다고 알람
            eventPublisher.publishEvent(NotificationRequestDto.voteApproveOf(postRepo.getReferenceById(postId), vote.getUserId1(), vote.getUserId2()));
        }
        voteRepo.save(vote);

        return true;
    }

    public String returnAllPayment(Integer voteId) {
        List<PaymentHistory> foundPaymentList = paymentHistoryRepo.findAllByVoteId_VoteId(voteId);

        if (foundPaymentList == null) {
            return "환불해야할 기록이 존재하지 않습니다.";
        }
        for (PaymentHistory foundPayment : foundPaymentList) {
            // 이미 환불된 결제는 넘어감
            if (foundPayment.getIsReturned()) {
                continue;
            }
            log.info("foundPayment: {}", foundPayment);
            // 카카오 페이 API에 결제 취소 요청
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "KakaoAK " + KAKAO_ADMIN_KEY);
            headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
            requestBody.add("cid", "TC0ONETIME");
            requestBody.add("tid", foundPayment.getTid());
            requestBody.add("cancel_amount", foundPayment.getAmount().toString());
            requestBody.add("cancel_tax_free_amount", foundPayment.getAmount().toString());

            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<Map> Kakaoresponse = restTemplate.exchange(
                    CANCEL_KAKAO_URL,
                    HttpMethod.POST,
                    entity,
                    Map.class);
            log.info("response body : {}", Kakaoresponse.getBody());
            foundPayment.setIsReturned(true);
            foundPayment.setVoteId(null);
            paymentHistoryRepo.save(foundPayment);
        }

        return "환불완료";
    }

    public String returnPayment(String authorization, Integer voteId, boolean isMine) throws Exception {
        String userEmail = jwtTokenService.getUserEmail(jwtTokenService.extractToken(authorization));
        Optional<User> foundUser = userRepository.findByEmail(userEmail);
        if (foundUser.isEmpty()) {
            throw new Exception("존재하지 않는 유저입니다.");
        }
        PaymentHistory foundPayment;
        if (isMine) { // 내꺼를 환불하는지, 혹은 공약을 이행한 상대를 환분하는지
            foundPayment = paymentHistoryRepo.findByUserId_UserIdAndVoteId_VoteId(
                    foundUser.get().getUserId(), voteId);
        } else {
            foundPayment = paymentHistoryRepo.findByUserId_UserIdIsNotAndVoteId_VoteId(
                    foundUser.get().getUserId(), voteId);
        }
        if (foundPayment == null) {
            return "환불해야할 기록이 존재하지 않습니다.";
        }
        if (foundPayment.getIsReturned()) {
            return "이미 환불된 기록입니다.";
        }

        log.info("foundPayment: {}", foundPayment);
        // 카카오 페이 API에 결제 취소 요청
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + KAKAO_ADMIN_KEY);
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("cid", "TC0ONETIME");
        requestBody.add("tid", foundPayment.getTid());
        requestBody.add("cancel_amount", foundPayment.getAmount().toString());
        requestBody.add("cancel_tax_free_amount", foundPayment.getAmount().toString());

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> Kakaoresponse = restTemplate.exchange(
                CANCEL_KAKAO_URL,
                HttpMethod.POST,
                entity,
                Map.class);
        log.info("response body : {}", Kakaoresponse.getBody());
        foundPayment.setIsReturned(true);
        foundPayment.setVoteId(null);
        paymentHistoryRepo.save(foundPayment);
        return "환불완료";
    }

    public List<PaymentHistoryDto> getPaymentList(String authorization) {
        String userEmail = jwtTokenService.getUserEmail(jwtTokenService.extractToken(authorization));
        Optional<User> foundUser = userRepository.findByEmail(userEmail);
        if (foundUser.isEmpty()) {
            throw new UsernameNotFoundException("존재하지 않는 유저입니다.");
        }
        List<PaymentHistory> paymentList = paymentHistoryRepo.findByUserId(foundUser.get());
        return paymentList.stream()
                .map(PaymentHistoryDto::fromEntity)
                .collect(Collectors.toList());
    }
}