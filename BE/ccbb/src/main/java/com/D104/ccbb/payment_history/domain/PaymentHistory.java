package com.D104.ccbb.payment_history.domain;

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
public class PaymentHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "history_id", nullable = false)
	private Integer historyId;

	@Column(nullable = false, columnDefinition = "int")
	private Integer amount;

	@Column(name = "is_returned", nullable = false, columnDefinition = "boolean")
	private Boolean isReturned;

	@Column(name = "pay_date", nullable = false, columnDefinition = "DATETIME")
	private LocalDateTime payDate;

	// 유저 ID

	// 투표 ID
}
