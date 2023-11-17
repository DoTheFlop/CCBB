package com.D104.ccbb.goods.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import com.D104.ccbb.event.domain.Event;
import com.D104.ccbb.file.domain.File;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Goods {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "goods_id", nullable = false)
	private Integer goodsId;

	@Column(nullable = false, columnDefinition = "varchar(100)")
	private String name;

	@Column(name = "win_percent", nullable = false, columnDefinition = "int")
	private Integer winPercent; // 당첨 확률

	@Column(name = "win_count", nullable = false, columnDefinition = "int")
	private Integer winCount; // 승리 상품 개수

	@Column(nullable = false, columnDefinition = "int")
	private Integer price;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "event_id", nullable = false)
	private Event eventId;

	@JsonManagedReference
	@OneToOne(mappedBy = "goodsId")
	private File fileId;

	@Version
	private int version; // 추가된 부분
}
