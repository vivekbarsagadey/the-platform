/*package com.platform.health.media;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.platform.health.enterprise.EnterpriseEndpointRepository;
import com.platform.health.file.FileRepository;
import com.platform.health.file.ItemImages;

import lombok.Data;

@Service
@PropertySource(ignoreResourceNotFound = true, value = "classpath:config.properties") // ("file:config.properties")
@Data
public class IipImageAdapter {

	@Value("${iip.image.server.url}")
	private String imageServerUrl;

	@Value("${iip.image.local.path}")
	private String imageLocalPath;

	@Autowired
	private FileRepository fileRepository;

	@Autowired
	private EnterpriseEndpointRepository enterpriseEndpointRepository;

	private final Path rootLocation = Paths.get("filestorage");

	public ItemImages upload(ItemImages imageProperty) {

		try {

			enterpriseEndpointRepository.findByName("IMAGE_SERVER").ifPresentOrElse(eep -> {
				imageProperty.setLocalImageUrl(this.createUrl(imageProperty, eep.getUrl()));
				fileRepository.save(imageProperty);
			}, () -> {
				imageProperty.setLocalImageUrl(this.createUrl(imageProperty, imageServerUrl));
				fileRepository.save(imageProperty);
			});

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return imageProperty;
	}

	private String getOrgName(ItemImages imageProperty) {
		String orgName = imageProperty.getName();
		if (orgName.contains(".")) {
			orgName = orgName.substring(0, orgName.indexOf("."));
		}
		return orgName;
	}

	private String createUrl(ItemImages imageProperty, String serverUrl) {
		String orgName = this.getOrgName(imageProperty);
		StringBuilder path = new StringBuilder(serverUrl);
		path.append("?");
		path.append("FIF=" + imageLocalPath + orgName + ".jp2");
		path.append("&CVT=jpeg");
		path.append("&HEI=100");
		path.append("&WID=100");

		return path.toString();
	}

}
*/