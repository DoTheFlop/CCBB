package com.D104.ccbb.vote.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.D104.ccbb.post.domain.Post;
import com.D104.ccbb.user.domain.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vote {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vote_id", nullable = false)
	private Integer voteId;

	@Column(nullable = false, columnDefinition = "varchar(200)")
	private String argument;

	@Column(name = "user1_accept", nullable = false)
	private Boolean accept1;

	@Column(name = "user2_accept", nullable = false)
	private Boolean accept2;

	@Column(name = "vote_start", nullable = false, columnDefinition = "DATETIME")
	private LocalDateTime voteStart;

	@Column(name = "vote_deadline", nullable = false, columnDefinition = "DATETIME")
	private LocalDateTime deadline;

	@Column(name = "tier_limit", columnDefinition = "varchar(10)")
	private String tier;

	@Column(columnDefinition = "varchar(100)")
	private String promise;

	@Column(columnDefinition = "int")
	private Integer deposit;

	@Column(name = "select_line", columnDefinition = "int")
	private Integer selectLine;

	@Column(name = "do_promise")
	private Boolean doPromise;
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "user_id1", nullable = false)
	private User userId1;
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "user_id2", nullable = false)
	private User userId2;

	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "post_id", nullable = false)
	private Post postId;
	//게릿테스트용 주석입니다
}
