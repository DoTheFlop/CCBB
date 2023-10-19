package com.D104.ccbb.ballot_box.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
}
