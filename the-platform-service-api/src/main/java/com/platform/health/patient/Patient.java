package com.platform.health.patient;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Patient {
	@Id
	private String patientId;
	private String pregnancy;
	private String glucose;
	private String bloodpressure;
	private String skinThickness;
	private String insulin;
	private String bmi;
	private String diabetesPedigreeFunction;
	private String age;
}
