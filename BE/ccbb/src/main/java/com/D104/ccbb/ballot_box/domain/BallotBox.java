package com.D104.ccbb.ballot_box.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.D104.ccbb.user.domain.User;
import com.D104.ccbb.vote.domain.Vote;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BallotBox {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ballot_box_id", nullable = false)
	private Integer ballotBoxId;

	//1,2 유저 //0 중립
	@Column(nullable = false, columnDefinition = "int")
	private Integer pick;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User userId;

	@ManyToOne
	@JoinColumn(name = "vote_id", nullable = false)
	private Vote voteId;

}
