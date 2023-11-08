package com.D104.ccbb.ballot_box.service;

import com.D104.ccbb.ballot_box.domain.BallotBox;
import com.D104.ccbb.ballot_box.dto.BallotBoxDto;
import com.D104.ccbb.ballot_box.dto.BallotResultDto;
import com.D104.ccbb.ballot_box.repo.BallotBoxRepo;
import com.D104.ccbb.notification.dto.NotificationRequestDto;
import com.D104.ccbb.post.domain.Post;
import com.D104.ccbb.user.domain.User;
import com.D104.ccbb.user.repository.UserRepository;
import com.D104.ccbb.vote.domain.Vote;
import com.D104.ccbb.vote.repo.VoteRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BallotBoxService {

    private final BallotBoxRepo ballotBoxRepo;
    private final UserRepository userRepository;
    private final VoteRepo voteRepo;
    private final ApplicationEventPublisher eventPublisher;

    public void setBallotBox(BallotBoxDto ballotBoxDto) {
        Vote vote = voteRepo.getReferenceById(ballotBoxDto.getVoteId());
        BallotBox ballotBox = BallotBox.builder()
                .ballotBoxId(ballotBoxDto.getBallotBoxId())
                .pick(ballotBoxDto.getPick())
                .userId(userRepository.getReferenceById(ballotBoxDto.getUserId()))
                .vote(vote)
                .build();
        ballotBoxRepo.save(ballotBox);

        // 알림 전송
        Post post = vote.getPostId();
        User receiver1 = vote.getUserId1();
        User receiver2 = vote.getUserId2();
        eventPublisher.publishEvent(NotificationRequestDto.voteAddOf(post, receiver1, receiver2));
        eventPublisher.publishEvent(NotificationRequestDto.voteAddOf(post, receiver2, receiver1));
    }

    public BallotResultDto getBallotResult(int voteId) {
        BallotResultDto ballotResultDto = new BallotResultDto();
        ballotResultDto.setPick1(ballotBoxRepo.countByVote_VoteIdAndPick(voteId, 1));
        ballotResultDto.setPick2(ballotBoxRepo.countByVote_VoteIdAndPick(voteId, 2));
        return ballotResultDto;
    }

    public BallotResultDto getUserPick(int voteId, int userId) {
        BallotResultDto ballotResultDto = new BallotResultDto();
        if (ballotBoxRepo.countByVote_VoteIdAndPick(voteId, 1) != null)
            ballotResultDto.setPick1(ballotBoxRepo.countByVote_VoteIdAndPick(voteId, 1));
        else ballotResultDto.setPick1(0L);
        if (ballotBoxRepo.countByVote_VoteIdAndPick(voteId, 2) != null)
            ballotResultDto.setPick2(ballotBoxRepo.countByVote_VoteIdAndPick(voteId, 2));
        else ballotResultDto.setPick1(0L);
        if (ballotBoxRepo.findByVote_VoteIdAndUserId_UserId(voteId, userId) != null)
            ballotResultDto.setUserPick(ballotBoxRepo.findByVote_VoteIdAndUserId_UserId(voteId, userId).getPick());
        else
            ballotResultDto.setUserPick(0);
        return ballotResultDto;
    }

    public List<BallotBox> getBallotList(int voteId) {
        return ballotBoxRepo.findByVote_VoteId(voteId);
    }
}
