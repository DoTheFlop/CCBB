package com.D104.ccbb.vote.service;

import com.D104.ccbb.ballot_box.repo.BallotBoxRepo;
import com.D104.ccbb.comment.repo.CommentRepo;
import com.D104.ccbb.notification.dto.NotificationRequestDto;
import com.D104.ccbb.post.domain.Post;
import com.D104.ccbb.post.repo.PostRepo;
import com.D104.ccbb.user.domain.User;
import com.D104.ccbb.user.repository.UserRepository;
import com.D104.ccbb.vote.domain.Vote;
import com.D104.ccbb.vote.dto.VoteAcceptDto;
import com.D104.ccbb.vote.dto.VoteAddDto;
import com.D104.ccbb.vote.dto.VoteListDto;
import com.D104.ccbb.vote.dto.VoteModifyDto;
import com.D104.ccbb.vote.repo.VoteRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepo voteRepo;
    private final UserRepository userRepository;
    private final PostRepo postRepo;
    private final CommentRepo commentRepo;
    private final BallotBoxRepo ballotBoxRepo;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void setVote(VoteAddDto voteAddDto) {
        Post post = postRepo.getReferenceById(voteAddDto.getPostId());
        User producer = userRepository.getReferenceById(voteAddDto.getUserId1());
        User receiver = userRepository.findByEmail(voteAddDto.getUserId2()).get();

        Vote vote = Vote.builder()
                .argument(voteAddDto.getArgument())
                .accept1(voteAddDto.getAccept1())
                .accept2(voteAddDto.getAccept2())
                .voteStart(voteAddDto.getVoteStart())
                .deadline(voteAddDto.getDeadline())
                .tier(voteAddDto.getTier())
                .promise(voteAddDto.getPromise())
                .deposit(voteAddDto.getDeposit())
                .userId1(producer)
                .userId2(receiver)
                .postId(post)
                .doPromise(voteAddDto.getDoPromise())
                .selectLine(voteAddDto.getSelectLine())
                .build();

        voteRepo.save(vote);

        eventPublisher.publishEvent(NotificationRequestDto.voteRequestOf(post, receiver, producer));
    }

    public List<Vote> getVoteList(LocalDateTime deadLine) {
        return voteRepo.findByDeadlineLessThan(deadLine);
    }

    @Transactional
    public void deleteVote(int voteId) {
        voteRepo.delete(voteRepo.getReferenceById(voteId));
    }

    @Transactional
    public void modifyVote(VoteModifyDto voteModifyDto) {
        Vote vote = postRepo.getReferenceById(voteModifyDto.getPostId()).getVote();
        if (voteModifyDto.getAccept2() != null)
            vote.setAccept2(voteModifyDto.getAccept2());
        if (voteModifyDto.getDoPromise() != null)
            vote.setDoPromise(voteModifyDto.getDoPromise());
        voteRepo.save(vote);
    }

    public List<VoteListDto> getParticipationList(int userId) {

        List<VoteListDto> partList = voteRepo.participateList(userId,userId)
                .stream()
                .map(m -> VoteListDto.fromEntity(m, commentRepo, ballotBoxRepo))
                .collect(Collectors.toList());
        return partList;
    }
    public List<VoteListDto> getParticipationPastList(int userId) {

        List<VoteListDto> partList = voteRepo.participatePastList(userId,userId)
                .stream()
                .map(m -> VoteListDto.fromEntity(m, commentRepo, ballotBoxRepo))
                .collect(Collectors.toList());
        return partList;
    }

    public List<VoteAcceptDto> getNotAccept(int userId) {

        List<VoteAcceptDto> acceptList = voteRepo.findByUserId2_UserIdAndAccept2(userId, false)
                .stream()
                .map(m -> VoteAcceptDto.fromEntity(m))
                .collect(Collectors.toList());
        return acceptList;
    }
}
