package com.D104.ccbb.repot.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Repot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "report_id", nullable = false)
	private Integer reportId;

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
