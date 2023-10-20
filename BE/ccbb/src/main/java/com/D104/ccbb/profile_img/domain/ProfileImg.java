package com.D104.ccbb.profile_img.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.D104.ccbb.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileImg {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "profile_img_id", nullable = false)
	private Integer profileImgId;

	@Column(nullable = false, columnDefinition = "varchar(1000)")
	private String name;

	@Column(name = "org_name", nullable = false, columnDefinition = "varchar(2000)")
	private String orgName;

	@Column(nullable = false, columnDefinition = "varchar(4)")
	private String extension;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User userId;

}
