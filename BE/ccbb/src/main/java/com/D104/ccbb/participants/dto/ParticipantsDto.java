package com.D104.ccbb.participants.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantsDto {
	private Integer participantsId;
	private LocalDateTime createDate;
	private Integer userId;
	private Integer eventId;
	private Integer goodsId;
}
