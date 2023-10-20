package com.D104.ccbb.payment_history.dto;

import java.time.LocalDateTime;

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
	private Integer postId;
}
