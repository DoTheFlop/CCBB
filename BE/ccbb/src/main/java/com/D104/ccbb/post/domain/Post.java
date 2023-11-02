package com.D104.ccbb.post.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.D104.ccbb.comment.domain.Comment;
import com.D104.ccbb.user.domain.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id", nullable = false)
	private Integer postId;

	@Column(nullable = false, columnDefinition = "varchar(100)")
	private String title;

	@Column(nullable = false, columnDefinition = "varchar(2000)")
	private String content;

	@CreatedDate
	@Column(name = "create_date", nullable = false, columnDefinition = "DATETIME")
	private LocalDateTime createDate;

	@Column(nullable = false, columnDefinition = "int")
	private Integer type;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User userId;

	@JsonManagedReference
	@Builder.Default
	@OneToMany(mappedBy = "postId", cascade = CascadeType.ALL)
	private List<Comment> Comment = new ArrayList<>();
}
