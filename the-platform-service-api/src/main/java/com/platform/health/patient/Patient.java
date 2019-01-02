package com.platform.health.patient;

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
public class Patient {

	@Id
	@Column(name = "ID", updatable = false, length = 200)
	private String patientId;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", updatable = false)
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
	private String pregnancy;
	private String glucose;
	private String bloodpressure;
	private String skinThickness;
	private String insulin;
	private String bmi;
	private String diabetesPedigreeFunction;
	private String age;
	
	@PrePersist
	public void setPrePersistEntity() {
		this.createdDate = new Date();
		this.updatedDate = new Date();

	}
}
