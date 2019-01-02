package com.platform.health.doctor;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Doctor {
	@Id
	@Column(name = "ID", updatable = false, length = 200)
	private String id;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", updatable = false)
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
	private String name;
	private String address;
	private String area;
	private String city;
	private String phone;
	private String email;

	@PrePersist
	public void setPrePersistEntity() {
		this.createdDate = new Date();
		this.updatedDate = new Date();

	}
}
