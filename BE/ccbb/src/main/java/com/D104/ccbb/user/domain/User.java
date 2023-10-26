package com.D104.ccbb.user.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.D104.ccbb.user.dto.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false)
	private Integer userId;

	@Column(columnDefinition = "varchar(100)")
	private String nickname;

	@Column(nullable = false, columnDefinition = "varchar(10)")
	private String name;

	@Column(nullable = false, columnDefinition = "varchar(100)")
	private String email;

	@Column(nullable = false, columnDefinition = "varchar(1000)")
	private String password;

	@Column(name = "sex", nullable = false)
	private Boolean sex;

	@Column(name = "birthyear", columnDefinition = "DATE")
	private LocalDate birthYear;

	@Column(name = "riot_token", columnDefinition = "varchar(1000)")
	private String riot;

	@Column(name = "blizzard_token", columnDefinition = "varchar(1000)")
	private String blizzard;

	@Column(nullable = false, columnDefinition = "int")
	private Integer point;

	@Column(name = "create_date", nullable = false, columnDefinition = "DATETIME")
	private LocalDateTime createDate;

	@Column(name = "lol_tier", columnDefinition = "varchar(50)")
	private String lol;

	@Column(name = "val_tier", columnDefinition = "varchar(50)")
	private String val;

	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(name = "state")
	private Byte state;
}
