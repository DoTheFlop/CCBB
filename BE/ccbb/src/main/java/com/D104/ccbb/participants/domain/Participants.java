package com.D104.ccbb.participants.domain;

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
public class Participants {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "participants_id", nullable = false)
	private Integer participantsId;

	@Column(name = "create_date", nullable = false, columnDefinition = "DATETIME")
	private LocalDateTime createDate;

}
