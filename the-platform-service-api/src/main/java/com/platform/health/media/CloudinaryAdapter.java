/*package com.platform.health.media;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.platform.health.file.FileRepository;
import com.platform.health.file.ItemImages;

import lombok.Data;

@Service
@PropertySource(ignoreResourceNotFound = true, value = "classpath:config.properties")//("file:config.properties")
@Data
public class CloudinaryAdapter {

	@Value("${cloudinary.cloud.name}")
    private String cloudName;
	
	@Value("${cloudinary.api.key}")
    private String apiKey;
	
	@Value("${cloudinary.api.secret}")
    private String apiSecret;
	
	@Autowired
	private FileRepository fileRepository;
	
	private final Path rootLocation = Paths.get("filestorage");
	
	public ItemImages upload(ItemImages imageProperty){
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				  "cloud_name", cloudName,
				  "api_key", apiKey,
				  "api_secret", apiSecret));
		try {
			var basePath = Paths.get(System.getProperty("user.dir")+ "\\src\\main\\");
			var imagePath = basePath + imageProperty.getPath() ;
			System.out.println(String.format("The image path is %s ", imagePath));
			File toUpload = new File(imagePath);
			Map<String, String> uploadResult = cloudinary.uploader().upload(toUpload, ObjectUtils.emptyMap());
			if(uploadResult.containsKey("secure_url")) {
				imageProperty.setWebImageUrl(uploadResult.get("secure_url"));
				imageProperty = fileRepository.save(imageProperty);
			}
			
			System.out.println(uploadResult);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imageProperty;
	}
	
}
*/