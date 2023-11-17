package com.D104.ccbb.event.domain;

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
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.D104.ccbb.file.domain.File;
import com.D104.ccbb.goods.domain.Goods;
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
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id", nullable = false)
	private Integer eventId;

	@Column(columnDefinition = "varchar(500)")
	private String title;

	@Column(columnDefinition = "varchar(2000)")
	private String content;

	@CreatedDate
	@Column(name = "create_date", nullable = false, columnDefinition = "DATETIME")
	private LocalDateTime createDate;

	@Column(name = "start_date", nullable = false, columnDefinition = "DATETIME")
	private LocalDateTime startDate;

	@Column(name = "end_date", nullable = false, columnDefinition = "DATETIME")
	private LocalDateTime endDate;

	@JsonManagedReference
	@Builder.Default
	@OneToMany(mappedBy = "eventId", cascade = CascadeType.ALL)
	private List<Goods> goods = new ArrayList<>();

}
