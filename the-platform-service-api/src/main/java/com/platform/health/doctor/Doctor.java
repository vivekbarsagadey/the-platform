package com.platform.health.doctor;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Doctor {
	@Id
	private String id;
	private String name;
	private String address;
	private String area;
	private String city;
	private String phone;
	private String email;
}
