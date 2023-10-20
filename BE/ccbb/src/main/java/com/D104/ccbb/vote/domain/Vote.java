package com.D104.ccbb.vote.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Vote {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vote_id", nullable = false)
	private Integer voteId;

	@Column(name = "vote_user1", nullable = false, columnDefinition = "int")
	private Integer vote1;

	@Column(name = "vote_user2", nullable = false, columnDefinition = "int")
	private Integer vote2;

	@Column(nullable = false, columnDefinition = "int")
	private Integer neutrality;

	@Column(nullable = false, columnDefinition = "varchar(200)")
	private String argument;

	@Column(name = "user1_accept", nullable = false, columnDefinition = "boolean")
	private Boolean accept1;

	@Column(name = "user2_accept", nullable = false, columnDefinition = "boolean")
	private Boolean accept2;

	@Column(name = "vote_deadline", nullable = false, columnDefinition = "DATETIME")
	private LocalDateTime deadline;

	@Column(name = "tier_limit", nullable = false, columnDefinition = "varchar(10)")
	private String tier;

	@ManyToOne
	@JoinColumn(name = "user_id1", nullable = false)
	private User userId1;

	@ManyToOne
	@JoinColumn(name = "user_id2", nullable = false)
	private User userId2;

}
