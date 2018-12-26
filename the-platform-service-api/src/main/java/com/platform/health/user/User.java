package com.platform.health.user;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.platform.health.common.domain.BaseEntity;

import lombok.Data;

@Entity
@Table(name = "TB_USER")
@Data
@Access(AccessType.PROPERTY)
public class User extends BaseEntity {

	@Column(name = "user_id", length = 100, nullable = false, unique = true)
	private String firstName;
	private String lastName;
	private String emailId;
	private String phone;
	private String password;
	private String confirmPassword;
}
