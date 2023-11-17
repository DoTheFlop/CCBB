package com.D104.ccbb.payment_history.domain;

import java.time.LocalDateTime;

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
public class PaymentHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "history_id", nullable = false)
	private Integer historyId;

	@Column(nullable = false, columnDefinition = "int")
	private Integer amount;

	@Column(name = "is_returned", nullable = false)
	private Boolean isReturned;

	@Column(name = "pay_date", nullable = false, columnDefinition = "DATETIME")
	private LocalDateTime payDate;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User userId;

	@ManyToOne
	@JoinColumn(name = "vote_id", nullable = false)
	private Vote voteId;

	@Column(name = "tid", nullable = false)
	private String tid;

	@Column(name = "is_approve", nullable = false)
	private boolean isApprove;

	@Column(name = "partner_order_id", nullable = false)
	private String partnerOrderId;
}
