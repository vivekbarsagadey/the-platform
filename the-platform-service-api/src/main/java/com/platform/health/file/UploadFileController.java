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

import com.platform.health.doctor.Doctor;
import com.platform.health.doctor.DoctorRepository;
import com.platform.health.hospital.Hospital;
import com.platform.health.hospital.HospitalRepository;
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

	@Autowired
	HospitalRepository hospitalRepository;

	@Autowired
	DoctorRepository doctorRepository;

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
			out = fileStorage.loadPatientInfo(file);
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
			patient.setCreatedDate(newPatient.getCreatedDate());
			patient.setUpdatedDate(newPatient.getUpdatedDate());
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

//------------------------------------------HOSPITAL DATA CSV READING CODE--------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/hospital", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Load hospital data from csv file", produces = "application/text")
	public ResponseEntity<String> loadHospitalData(@RequestParam("uploadfile") MultipartFile file) {
		String out = null;
		try {
			out = fileStorage.loadHospitalInfo(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(out, HttpStatus.OK);
	}

	@GetMapping("/hospital")
	@ApiOperation(value = "Get all hospital data", produces = "application/text")
	public ResponseEntity<List<Hospital>> getHospitalData() {
		List<Hospital> list = hospitalRepository.findAll();
		return new ResponseEntity<List<Hospital>>(list, HttpStatus.OK);
	}

	@GetMapping("/hospital/{id}")
	public ResponseEntity<Hospital> findHospitalById(@PathVariable String id) {
		Optional<Hospital> entity = hospitalRepository.findById(id);
		return new ResponseEntity<Hospital>(entity.get(), HttpStatus.OK);

	}

	@PutMapping("/hospital/{id}")
	public ResponseEntity<Hospital> updateHospital(@RequestBody Hospital newHospital,
			@PathVariable(name = "id", required = true) String id) {
		return hospitalRepository.findById(id).map(hospital -> {

			hospital.setHospitalId(newHospital.getHospitalId());
			hospital.setState(newHospital.getState());
			hospital.setCity(newHospital.getCity());
			hospital.setHospital(newHospital.getHospital());
			hospital.setCategory(newHospital.getCategory());
			hospital.setSystemsOfMedicine(newHospital.getSystemsOfMedicine());
			hospital.setContactDetails(newHospital.getContactDetails());
			hospital.setPinCode(newHospital.getPinCode());
			hospital.setEmailAddress(newHospital.getEmailAddress());
			hospital.setWebsiteLink(newHospital.getWebsiteLink());
			hospital.setCreatedDate(newHospital.getCreatedDate());
			hospital.setUpdatedDate(newHospital.getUpdatedDate());
			return new ResponseEntity<Hospital>(hospitalRepository.save(hospital), HttpStatus.OK);
		}).orElseGet(() -> {
			return new ResponseEntity<Hospital>(newHospital, HttpStatus.NOT_FOUND);
		});

	}

	@DeleteMapping("/hospital/{id}")
	public ResponseEntity<String> deleteHospital(@PathVariable String id) {
		hospitalRepository.deleteById(id);
		return new ResponseEntity<String>("Hospital deleted successfully!!", HttpStatus.OK);
	}

	// ------------------------------------------DOCTOR DATA CSV READING
	// CODE--------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/doctor", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Load Doctor data from csv file", produces = "application/text")
	public ResponseEntity<String> loadDoctorData(@RequestParam("uploadfile") MultipartFile file) {
		String out = null;
		try {
			out = fileStorage.loadDoctorInfo(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(out, HttpStatus.OK);
	}

	@GetMapping("/doctor")
	@ApiOperation(value = "Get all Doctor data", produces = "application/text")
	public ResponseEntity<List<Doctor>> getDoctorData() {
		List<Doctor> list = doctorRepository.findAll();
		return new ResponseEntity<List<Doctor>>(list, HttpStatus.OK);
	}

	@GetMapping("/doctor/{id}")
	public ResponseEntity<Doctor> findDoctorById(@PathVariable String id) {
		Optional<Doctor> entity = doctorRepository.findById(id);
		return new ResponseEntity<Doctor>(entity.get(), HttpStatus.OK);

	}

	@PutMapping("/doctor/{id}")
	public ResponseEntity<Doctor> updateDoctor(@RequestBody Doctor newDoctor,
			@PathVariable(name = "id", required = true) String id) {
		return doctorRepository.findById(id).map(doctor -> {

			doctor.setId(newDoctor.getId());
			doctor.setName(newDoctor.getName());
			doctor.setCity(newDoctor.getCity());
			doctor.setEmail(newDoctor.getEmail());
			doctor.setPhone(newDoctor.getPhone());
			doctor.setArea(newDoctor.getArea());
			doctor.setAddress(newDoctor.getAddress());
			doctor.setCreatedDate(newDoctor.getCreatedDate());
			doctor.setUpdatedDate(newDoctor.getUpdatedDate());

			return new ResponseEntity<Doctor>(doctorRepository.save(doctor), HttpStatus.OK);
		}).orElseGet(() -> {
			return new ResponseEntity<Doctor>(newDoctor, HttpStatus.NOT_FOUND);
		});

	}

	@DeleteMapping("/doctor/{id}")
	public ResponseEntity<String> deleteDoctor(@PathVariable String id) {
		doctorRepository.deleteById(id);
		return new ResponseEntity<String>("Doctor deleted successfully!!", HttpStatus.OK);
	}
}
