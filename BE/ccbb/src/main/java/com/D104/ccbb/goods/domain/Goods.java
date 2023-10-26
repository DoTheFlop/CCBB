package com.D104.ccbb.goods.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.D104.ccbb.event.domain.Event;

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

	@Column(nullable = false, columnDefinition = "int")
	private Integer count;

	@Column(nullable = false, columnDefinition = "int")
	private Integer price;

	@ManyToOne
	@JoinColumn(name = "event_id", nullable = false)
	private Event eventId;

}
