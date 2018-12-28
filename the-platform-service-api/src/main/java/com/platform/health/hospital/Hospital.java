package com.platform.health.hospital;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Hospital {
	@Id
	private String hospitalId;
	private String state;
	private String city;
	private String hospital;
	private String category;
	private String systemsOfMedicine;
	private String contactDetails;
	private String pinCode;
	private String emailAddress;
	private String websiteLink;
	

}
