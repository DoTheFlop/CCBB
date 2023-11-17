package com.D104.ccbb.payment_history.dto;

import java.time.LocalDateTime;

import com.D104.ccbb.payment_history.domain.PaymentHistory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentHistoryDto {
	private Integer historyId;
	private Integer amount;
	private Boolean isReturned;
	private LocalDateTime payDate;
	private Integer userId;
	private Integer voteId;
	private String tid;
	private boolean isApprove;
	private String partnerOrderId;

	public static PaymentHistoryDto fromEntity(PaymentHistory paymentHistory) {
		return PaymentHistoryDto.builder()
			.historyId(paymentHistory.getHistoryId())
			.amount(paymentHistory.getAmount())
			.isReturned(paymentHistory.getIsReturned())
			.payDate(paymentHistory.getPayDate())
			.userId(paymentHistory.getUserId().getUserId())
			.voteId(paymentHistory.getVoteId().getVoteId())
			.tid(paymentHistory.getTid())
			.isApprove(paymentHistory.isApprove())
			.partnerOrderId(paymentHistory.getPartnerOrderId())
			.build();
	}
}
