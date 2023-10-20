package com.D104.ccbb.file.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {
	private Integer fileId;
	private String name;
	private String orgName;
	private String extension;
	private String type;
	private Integer postId;
	private Integer goodsId;
	private Integer eventId;
}
