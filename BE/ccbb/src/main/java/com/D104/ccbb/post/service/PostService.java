package com.D104.ccbb.post.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.D104.ccbb.ballot_box.repo.BallotBoxRepo;
import com.D104.ccbb.post.domain.Post;
import com.D104.ccbb.post.dto.PostDto;
import com.D104.ccbb.post.dto.PostLoadDto;
import com.D104.ccbb.post.dto.PostPageDto;
import com.D104.ccbb.post.repo.PostRepo;
import com.D104.ccbb.user.repository.UserRepository;
import com.D104.ccbb.vote.repo.VoteRepo;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

	private final PostRepo postRepo;
	private final UserRepository userRepository;
	private final BallotBoxRepo ballotBoxRepo;
	private final VoteRepo voteRepo;
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

	public List<Map<String,Object>> getVote() {
		return postRepo.getVoteList();
	}

	public PostLoadDto getDetail(int postId){

		PostLoadDto postLoadDto = PostLoadDto.fromEntity(postRepo.getReferenceById(postId));
		return postLoadDto;
	}

	public Page<PostPageDto> getPageList(int page){
		int pageLimit = 12;
		Page<Post> postsPages = postRepo.findByVote_Accept1AndVote_Accept2(true,true,PageRequest.of(page-1, pageLimit, Sort.by(Sort.Direction.DESC, "postId")));
		Page<PostPageDto> postPageDto = postsPages.map(m -> PostPageDto.fromEntity(m,m.getVote(),ballotBoxRepo));
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

}
