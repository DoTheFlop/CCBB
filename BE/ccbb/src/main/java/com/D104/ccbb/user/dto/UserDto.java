package com.D104.ccbb.user.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.D104.ccbb.post.domain.Post;
import com.D104.ccbb.post.dto.PostDto;
import com.D104.ccbb.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private Integer userId;
	private String nickname;
	private String name;
	private String email;
	private String password;
	private Boolean sex;
	private LocalDate birthYear;
	private String riot;
	private String blizzard;
	private Integer point;
	private LocalDateTime createDate;
	private String lol;
	private String val;
	private Byte state;
	private String role;

	public static UserDto fromEntity(User user) {
		return UserDto.builder()
			.userId(user.getUserId())
			.nickname(user.getNickname())
			.name(user.getName())
			.email(user.getEmail())
			.password(user.getPassword())
			.sex(user.getSex())
			.birthYear(user.getBirthYear())
			.riot(user.getRiot())
			.blizzard(user.getBlizzard())
			.point(user.getPoint())
			.createDate(user.getCreateDate())
			.lol(user.getLol())
			.val(user.getVal())
			.state(user.getState())
			.role(user.getRole().getKey())
			.build();
	}
}
