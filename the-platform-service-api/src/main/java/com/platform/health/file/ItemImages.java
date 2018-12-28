package com.platform.health.file;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

//@Entity
//@Table(name = "TB_IMAGES")
@Data
@Access(AccessType.PROPERTY)
public class ItemImages {

	@Column(name = "name")
	private String name;

	@Column(name = "path")
	private String path;

	@Column(name = "ext")
	private String ext;

	@Column(name = "local_image_url")
	private String localImageUrl;

	@Column(name = "web_image_url")
	private String webImageUrl;

	@Column(name = "image_size")
	private String imageSize;

	@Column(name = "image_type")
	private String imageType;

}
