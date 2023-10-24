package com.D104.ccbb.event.domain;

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
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id", nullable = false)
	private Integer eventId;

	@Column(columnDefinition = "varchar(500)")
	private String title;

	@Column(columnDefinition = "varchar(2000)")
	private String content;

	@Column(name = "create_date", nullable = false, columnDefinition = "DATETIME")
	private LocalDateTime createDate;

	@Column(name = "start_date", nullable = false, columnDefinition = "DATETIME")
	private LocalDateTime startDate;

	@Column(name = "end_date", nullable = false, columnDefinition = "DATETIME")
	private LocalDateTime endDate;

}