package com.platform.health.enterprise;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.platform.health.common.domain.BaseEntity;

import lombok.Data;

@Entity
@Table(name = "TB_ENTERPRISE_ENDPOINT")
@Data
@Access(AccessType.PROPERTY)
public class EnterpriseEndpoint extends BaseEntity {

	private String url;
	private String type;
	private String name;
}
