package com.cosa2.warikan_api.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@MappedSuperclass
public class BaseModel {
	@Column
	@Temporal(TemporalType.DATE)
	@JsonIgnore
	private Date insert;

	@Column
	@Temporal(TemporalType.DATE)
	@JsonIgnore
	private Date update;

	@PrePersist
	public void preInsert() {
		Date date = new Date();
		setInsert(date);
		setUpdate(date);
	}

	@PreUpdate
	public void preUpdate() {
		setUpdate(new Date());
	}
}
