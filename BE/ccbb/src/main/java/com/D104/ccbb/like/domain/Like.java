package com.D104.ccbb.like.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.D104.ccbb.comment.domain.Comment;
import com.D104.ccbb.post.domain.Post;
import com.D104.ccbb.re_comment.domain.ReComment;
import com.D104.ccbb.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "likes")
public class Like {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "like_id", nullable = false)
	private Integer likeId;

	@Column(nullable = false)
	private Boolean type;

	@Column(name = "create_date", nullable = false, columnDefinition = "DATETIME")
	private LocalDateTime createDate;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User userId;

	@ManyToOne
	@JoinColumn(name = "post_id", nullable = false)
	private Post postId;

	@ManyToOne
	@JoinColumn(name = "comment_id", nullable = false)
	private Comment commentId;

	@ManyToOne
	@JoinColumn(name = "re_comment_id", nullable = false)
	private ReComment reCommentId;

}
