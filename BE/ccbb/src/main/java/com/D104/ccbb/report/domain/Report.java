package com.D104.ccbb.report.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@EntityListeners(AuditingEntityListener.class)
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "report_id", nullable = false)
	private Integer reportId;

	@CreatedDate
	@Column(name = "create_date", nullable = false, columnDefinition = "DATETIME")
	private LocalDateTime createDate;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User userId;

	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post postId;

	@ManyToOne
	@JoinColumn(name = "comment_id")
	private Comment commentId;

	@ManyToOne
	@JoinColumn(name = "re_comment_id")
	private ReComment reCommentId;
}
