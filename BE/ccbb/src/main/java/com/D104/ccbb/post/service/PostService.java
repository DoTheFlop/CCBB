package com.D104.ccbb.post.service;

import com.D104.ccbb.ballot_box.repo.BallotBoxRepo;
import com.D104.ccbb.file.domain.File;
import com.D104.ccbb.file.repo.FileRepo;
import com.D104.ccbb.file.service.FileService;
import com.D104.ccbb.notification.dto.NotificationRequestDto;
import com.D104.ccbb.payment_history.service.PaymentHistoryService;
import com.D104.ccbb.post.domain.Post;
import com.D104.ccbb.post.dto.PostDto;
import com.D104.ccbb.post.dto.PostLoadDto;
import com.D104.ccbb.post.dto.PostPageDto;
import com.D104.ccbb.post.repo.PostRepo;
import com.D104.ccbb.user.repository.UserRepository;
import com.D104.ccbb.vote.domain.Vote;
import com.D104.ccbb.vote.repo.VoteRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepo postRepo;
    private final UserRepository userRepository;
    private final BallotBoxRepo ballotBoxRepo;
    private final VoteRepo voteRepo;
    private final PaymentHistoryService paymentHistoryService;
    private final FileRepo fileRepo;
    private final FileService fileService;
    private final ApplicationEventPublisher eventPublisher;

    public Post setPost(PostDto postDto) {

        Post post = Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .type(postDto.getType())
                .userId(userRepository.getReferenceById(postDto.getUserId()))
                .build();
        Post save = postRepo.save(post);
        return save;
    }

    public List<Post> getFree() {
        return postRepo.findByTypeOrderByPostIdDesc(0);
    }

    public List<Map<String, Object>> getVote() {
        return postRepo.getVoteList();
    }

    public PostLoadDto getDetail(int postId) {

        PostLoadDto postLoadDto = PostLoadDto.fromEntity(postRepo.getReferenceById(postId));
        return postLoadDto;
    }

    public Page<PostPageDto> getPageList(int page) {
        int pageLimit = 12;
        Page<Post> postsPages = postRepo.findByVote_Accept1AndVote_Accept2(true, true,
                PageRequest.of(page - 1, pageLimit, Sort.by(Sort.Direction.DESC, "postId")));
        Page<PostPageDto> postPageDto = postsPages.map(m -> PostPageDto.fromEntity(m, m.getVote(), ballotBoxRepo));
        return postPageDto;
    }
	public Page<PostPageDto> getPopularityPage(int page){
		int pageLimit = 12;
		Page<Map<String,Object>> postsPages = voteRepo.popularPage(PageRequest.of(page-1, pageLimit));
		Page<PostPageDto> postPageDto = postsPages.map(m -> PostPageDto.fromEntity(postRepo.getReferenceById((Integer)m.get("post_id")),voteRepo.getReferenceById((Integer)m.get("vote_id")),ballotBoxRepo));
		return postPageDto;
	}


	public void deletePost(int postId) {
		postRepo.delete(postRepo.getReferenceById(postId));
	}

    public void modifyPost(PostDto postDto) {
        Post post = postRepo.getReferenceById(postDto.getPostId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        postRepo.save(post);
    }

    @Transactional
    public void rejectPost(int postId, String authorization) throws Exception {
        Optional<Vote> voteOpt = voteRepo.findByPostId_PostId(postId);
        if (voteOpt.isEmpty()) {
            throw new Exception("투표 게시글이 없습니다.");
        }
        String result = paymentHistoryService.returnAllPayment(voteOpt.get().getVoteId());
        log.info("환불 결과: {}", result);

        List<File> fileList = fileRepo.findByPostId_PostId(postId);

        for (File file : fileList) {
            boolean fileDeleteResult = fileService.deleteFile(file.getFileId());
            log.info("File Id {} remove result : {}", file.getFileId(), fileDeleteResult);
        }

        //post에서 user1 뽑고 취소되었다고 알림
        eventPublisher.publishEvent(NotificationRequestDto.voteRejectOf(voteOpt.get().getPostId(), voteOpt.get().getUserId1(), voteOpt.get().getUserId2()));

        voteRepo.deleteById(voteOpt.get().getVoteId());
        postRepo.deleteById(postId);

    }
}
