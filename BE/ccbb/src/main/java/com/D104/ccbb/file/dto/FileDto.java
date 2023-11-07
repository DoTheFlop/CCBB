package com.D104.ccbb.file.dto;

import com.D104.ccbb.file.domain.File;

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
	private Boolean isPromise;
	private Integer postId;
	private Integer goodsId;
	private Integer eventId;

	public static FileDto fromEntity(File file) {
		return FileDto.builder()
			.fileId(file.getFileId())
			.name(file.getName())
			.orgName(file.getOrgName())
			.extension(file.getExtension())
			.type(file.getType())
			.isPromise(file.getIsPromise())
			.postId(file.getPostId() == null ? null : file.getPostId().getPostId())
			.goodsId(file.getGoodsId() == null ? null : file.getGoodsId().getGoodsId())
			.eventId(file.getEventId() == null ? null : file.getEventId().getEventId())
			.build();
	}
}
