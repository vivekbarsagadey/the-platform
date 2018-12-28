package com.platform.health.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/*import com.platform.health.media.CloudinaryAdapter;
import com.platform.health.media.IipImageAdapter;*/

@Controller
@RequestMapping(value = "/api/file")
public class UploadFileController {

	@Autowired
	private FileStorageServices fileStorage;

	/*
	 * @Autowired private CloudinaryAdapter cloudinaryAdapter;
	 * 
	 * @Autowired private IipImageAdapter iipImageAdapter;
	 * 
	 * @RequestMapping(value = "/upload/image/store/{storeId}", method =
	 * RequestMethod.POST, produces = "application/json") public
	 * ResponseEntity<ItemImages> uploadImage(@RequestParam("uploadfile")
	 * MultipartFile file, Model model,
	 * 
	 * @PathVariable(name = "storeId", required = true) String storeId) {
	 * System.out.
	 * println("#######################UploadFileController        #########" +
	 * storeId); ItemImages imageProperty = null; try { imageProperty =
	 * fileStorage.storeImage(file, storeId); imageProperty =
	 * cloudinaryAdapter.upload(imageProperty); imageProperty =
	 * iipImageAdapter.upload(imageProperty); model.addAttribute("message",
	 * "File uploaded successfully! -> filename = " + file.getOriginalFilename()); }
	 * catch (Exception e) { model.addAttribute("message",
	 * "Fail! -> uploaded filename: " + file.getOriginalFilename()); } return new
	 * ResponseEntity<ItemImages>(imageProperty, HttpStatus.OK); }
	 * 
	 * @RequestMapping(value = "/logo/upload/image/store/{storeId}", method =
	 * RequestMethod.POST, produces = "application/json") public
	 * ResponseEntity<ItemImages>
	 * uploadLogoMultipartFile(@RequestParam("uploadfile") MultipartFile file, Model
	 * model, @PathVariable(name = "storeId", required = true) String storeId) {
	 * System.out.
	 * println("#######################UploadFileController        #########" +
	 * storeId); ItemImages imageProperty = null; try { imageProperty =
	 * fileStorage.storeLogoFile(file, storeId); imageProperty =
	 * cloudinaryAdapter.upload(imageProperty); imageProperty =
	 * iipImageAdapter.upload(imageProperty); model.addAttribute("message",
	 * "File uploaded successfully! -> filename = " + file.getOriginalFilename()); }
	 * catch (Exception e) { model.addAttribute("message",
	 * "Fail! -> uploaded filename: " + file.getOriginalFilename()); } return new
	 * ResponseEntity<ItemImages>(imageProperty, HttpStatus.OK); }
	 */

	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> loadPatientData(@RequestParam("uploadfile") MultipartFile file) {
		String out = null;
		try {
			out = fileStorage.loadloadPatientInfo(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(out, HttpStatus.OK);
	}
}
