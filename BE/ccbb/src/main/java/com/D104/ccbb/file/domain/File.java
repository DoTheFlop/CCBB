package com.D104.ccbb.file.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.D104.ccbb.event.domain.Event;
import com.D104.ccbb.goods.domain.Goods;
import com.D104.ccbb.post.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class File {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "file_id", nullable = false)
	private Integer fileId;

	@Column(nullable = false, columnDefinition = "varchar(1000)")
	private String name;

	@Column(name = "org_name", nullable = false, columnDefinition = "varchar(2000)")
	private String orgName;

	@Column(nullable = false, columnDefinition = "varchar(4)")
	private String extension;

	@Column(nullable = false, columnDefinition = "varchar(10)")
	private String type;

	@Column(name = "is_promise", nullable = false, columnDefinition = "tinyint")
	private Boolean isPromise;

	@ManyToOne
	@JoinColumn(name = "post_id", nullable = false)
	private Post postId;

	@ManyToOne
	@JoinColumn(name = "goods_id", nullable = false)
	private Goods goodsId;

	@ManyToOne
	@JoinColumn(name = "event_id", nullable = false)
	private Event eventId;

}
