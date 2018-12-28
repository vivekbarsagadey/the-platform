package com.platform.health.file;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.platform.health.patient.Patient;
import com.platform.health.patient.PatientRepository;

import io.swagger.annotations.ApiOperation;

/*import com.platform.health.media.CloudinaryAdapter;
import com.platform.health.media.IipImageAdapter;*/

@Controller
@RequestMapping(value = "/api/file")
public class UploadFileController {

	@Autowired
	private FileStorageServices fileStorage;

	@Autowired
	PatientRepository patientRepository;

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
	@ApiOperation(value = "Load patient data from csv file", produces = "application/text")
	public ResponseEntity<String> loadPatientData(@RequestParam("uploadfile") MultipartFile file) {
		String out = null;
		try {
			out = fileStorage.loadloadPatientInfo(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(out, HttpStatus.OK);
	}

	@GetMapping("/")
	@ApiOperation(value = "Get all patient data", produces = "application/text")
	public ResponseEntity<List<Patient>> get() {
		List<Patient> list = patientRepository.findAll();
		return new ResponseEntity<List<Patient>>(list, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Patient> findById(@PathVariable String id) {
		Optional<Patient> entity = patientRepository.findById(id);
		return new ResponseEntity<Patient>(entity.get(), HttpStatus.OK);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Patient> update(@RequestBody Patient newPatient,
			@PathVariable(name = "id", required = true) String id) {
		return patientRepository.findById(id).map(patient -> {

			patient.setPatientId(newPatient.getPatientId());
			patient.setPregnancy(newPatient.getPregnancy());
			patient.setSkinThickness(newPatient.getSkinThickness());
			patient.setInsulin(newPatient.getInsulin());
			patient.setGlucose(newPatient.getGlucose());
			patient.setDiabetesPedigreeFunction(newPatient.getDiabetesPedigreeFunction());
			patient.setBmi(newPatient.getBmi());
			patient.setBloodpressure(newPatient.getBloodpressure());
			patient.setAge(newPatient.getAge());
			return new ResponseEntity<Patient>(patientRepository.save(patient), HttpStatus.OK);
		}).orElseGet(() -> {
			return new ResponseEntity<Patient>(newPatient, HttpStatus.NOT_FOUND);
		});

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable String id) {
		patientRepository.deleteById(id);
		return new ResponseEntity<String>("Patient deleted successfully!!", HttpStatus.OK);
	}

}
