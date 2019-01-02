package com.platform.health.file;

import javax.persistence.Column;

import lombok.Data;
@Data
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
