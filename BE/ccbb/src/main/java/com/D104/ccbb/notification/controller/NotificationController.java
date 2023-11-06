package com.D104.ccbb.notification.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.D104.ccbb.jwt.service.JwtTokenService;
import com.D104.ccbb.notification.dto.NotificationResponseDto;
import com.D104.ccbb.notification.service.NotificationService;
import com.D104.ccbb.user.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    private final UserRepository userRepository;
    private final JwtTokenService jwtTokenService;
    private final NotificationService notificationService;

    @ApiOperation(value = "알림 구독", notes = "알림을 구독한다.")
    @GetMapping(value = "/subscribe", produces = "text/event-stream")
    public SseEmitter subscribe(@RequestHeader String Authorization,
                                @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {
        Integer userId = userRepository.findByEmail(jwtTokenService.getUserEmail((jwtTokenService.extractToken(Authorization))))
                .get()
                .getUserId();

        return notificationService.subscribe(userId, lastEventId);
    }

    @ApiOperation(value = "알림 목록", notes = "유저의 알림 목록을 받아온다.")
    @GetMapping(value = "/notification/get")
    public ResponseEntity<Map<String, Object>> getList(@RequestHeader String Authorization) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status;
        try {
            Integer userId = userRepository.findByEmail(jwtTokenService.getUserEmail((jwtTokenService.extractToken(Authorization))))
                    .get()
                    .getUserId();
            List<NotificationResponseDto> notificationList = notificationService.getList(userId);
            resultMap.put("notificationList", notificationList);
            resultMap.put("message", "success");
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            log.error("알림 검색 실패", e);
            resultMap.put("message", "fail: " + e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @ApiOperation(value = "알림 읽음 처리", notes = "유저의 알림을 읽었다고 처리한다.")
    @PutMapping(value = "/notification/read/{notificationId}")
    public ResponseEntity<Map<String, Object>> getList(@RequestHeader String Authorization, @PathVariable Integer notificationId) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status;
        try {
            Integer userId = userRepository.findByEmail(jwtTokenService.getUserEmail((jwtTokenService.extractToken(Authorization))))
                    .get()
                    .getUserId();
            notificationService.updateIsRead(userId, notificationId);
            resultMap.put("message", "success");
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            log.error("알림 읽음 처리 실패", e);
            resultMap.put("message", "fail: " + e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }
}
