package com.D104.ccbb.user.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {

	private String name;
	private String nickname;
	private String email;
	private String password;
	private Boolean sex;
	private Integer point;
	private LocalDateTime createDate;
	private Byte state;
	private Integer voteCount;
	private Integer voteVictory;

}
