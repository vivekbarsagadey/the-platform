package com.platform.health.hospital;

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
public class Hospital {
	@Id
	@Column(name = "ID", updatable = false, length = 200)
	private String hospitalId;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", updatable = false)
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
	private String state;
	private String city;
	private String hospital;
	private String category;
	private String systemsOfMedicine;
	private String contactDetails;
	private String pinCode;
	private String emailAddress;
	private String websiteLink;
	
	@PrePersist
	public void setPrePersistEntity() {
		this.createdDate = new Date();
		this.updatedDate = new Date();

	}

}
