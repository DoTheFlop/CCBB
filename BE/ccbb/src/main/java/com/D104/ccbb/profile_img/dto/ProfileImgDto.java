package com.D104.ccbb.profile_img.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileImgDto {
	private Integer profileImgId;
	private String name;
	private String orgName;
	private String extension;
	private Integer userId;
}
