package com.platform.health.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.platform.health.doctor.Doctor;
import com.platform.health.doctor.DoctorRepository;
import com.platform.health.hospital.Hospital;
import com.platform.health.hospital.HospitalRepository;
import com.platform.health.patient.Patient;
import com.platform.health.patient.PatientRepository;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import au.com.bytecode.opencsv.CSVReader;

@Service
public class FileStorageServices {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	HospitalRepository hospitalRepository;

	@Autowired
	DoctorRepository doctorRepository;
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	private final Path rootLocation = Paths.get("filestorage");

	/*
	 * @Autowired private FileRepository fileRepository;
	 * 
	 * public ItemImages storeImage(MultipartFile file, String storeId) { ItemImages
	 * imageProperty = null; try {
	 * 
	 * String path = System.getProperty("user.dir") +
	 * "\\src\\main\\resources\\images\\logo\\" + file.getOriginalFilename();
	 * 
	 * Files.copy(file.getInputStream(), Paths.get( System.getProperty("user.dir") +
	 * "\\src\\main\\resources\\images\\" + file.getOriginalFilename()));
	 * imageProperty = new ItemImages();
	 * imageProperty.setName(file.getOriginalFilename());
	 * imageProperty.setPath("\\resources\\images\\" + file.getOriginalFilename());
	 * imageProperty.setExt(getFileExtension(file.getOriginalFilename()));
	 * System.out.println("File Extension: " +
	 * getFileExtension(file.getOriginalFilename()));
	 * fileRepository.save(imageProperty); } catch (Exception e) { throw new
	 * RuntimeException("FAIL! -> message = " + e.getMessage()); } return
	 * imageProperty; }
	 * 
	 * public ItemImages storeLogoFile(MultipartFile file, String storeId) {
	 * ItemImages imageProperty = null; try {
	 * 
	 * String path = System.getProperty("user.dir") +
	 * "\\src\\main\\resources\\images\\logo\\" + file.getOriginalFilename();
	 * 
	 * Files.copy(file.getInputStream(), Paths.get(System.getProperty("user.dir") +
	 * "\\src\\main\\resources\\images\\logo\\" + file.getOriginalFilename()));
	 * imageProperty = new ItemImages();
	 * imageProperty.setName(file.getOriginalFilename());
	 * imageProperty.setPath("\\resources\\images\\logo\\" +
	 * file.getOriginalFilename());
	 * imageProperty.setExt(getFileExtension(file.getOriginalFilename()));
	 * System.out.println("File Extension: " +
	 * getFileExtension(file.getOriginalFilename()));
	 * fileRepository.save(imageProperty); } catch (Exception e) { throw new
	 * RuntimeException("FAIL! -> message = " + e.getMessage()); } return
	 * imageProperty; }
	 * 
	 * public Resource loadFile(String filename) { try { Path file =
	 * rootLocation.resolve(filename); Resource resource = new
	 * UrlResource(file.toUri()); if (resource.exists() || resource.isReadable()) {
	 * return resource; } else { throw new RuntimeException("FAIL!"); } } catch
	 * (MalformedURLException e) { throw new RuntimeException("Error! -> message = "
	 * + e.getMessage()); } }
	 * 
	 * public void deleteAll() {
	 * FileSystemUtils.deleteRecursively(rootLocation.toFile()); }
	 * 
	 * public void init() { try { Files.createDirectory(rootLocation); } catch
	 * (IOException e) { throw new
	 * RuntimeException("Could not initialize storage!"); } }
	 * 
	 * public Stream<Path> loadFiles() { try { return Files.walk(this.rootLocation,
	 * 1).filter(path -> !path.equals(this.rootLocation))
	 * .map(this.rootLocation::relativize); } catch (IOException e) { throw new
	 * RuntimeException("\"Failed to read stored file"); } }
	 * 
	 * private static String getFileExtension(String fileName) { if
	 * (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) return
	 * fileName.substring(fileName.lastIndexOf(".") + 1); else return ""; }
	 */
	public String loadPatientInfo(MultipartFile file)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		final String delimiter = ",";
		try {
			File f = new File(
					System.getProperty("user.dir") + "\\src\\main\\resources\\csv's\\" + file.getOriginalFilename());
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			String[] tempArr;
			int i = 0;
			while ((line = br.readLine()) != null) {
				Patient newPatient = new Patient();
				tempArr = line.split(delimiter);
				if (i != 0) {
					newPatient.setPatientId(tempArr[0]);
					newPatient.setPregnancy(tempArr[1]);
					newPatient.setGlucose(tempArr[2]);
					newPatient.setBloodpressure(tempArr[3]);
					newPatient.setSkinThickness(tempArr[4]);
					newPatient.setInsulin(tempArr[5]);
					newPatient.setBmi(tempArr[6]);
					newPatient.setDiabetesPedigreeFunction(tempArr[6]);
					newPatient.setAge(tempArr[8]);
					newPatient.setCreatedDate(newPatient.getCreatedDate());
					newPatient.setUpdatedDate(newPatient.getUpdatedDate());
					patientRepository.save(newPatient);
				}
				i++;
				System.out.println();
			}
			br.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return "successfully loaded!!";
	}

	// ------------------------------------------HOSPITAL DATA CSV READING
	// CODE--------------------------------------------------------------------------------------------------------
	public String loadHospitalInfo(MultipartFile file)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		final String delimiter = ",";
		try {
			File f = new File(
					System.getProperty("user.dir") + "\\src\\main\\resources\\csv's\\" + file.getOriginalFilename());
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			String[] tempArr;
			int i = 0;
			while ((line = br.readLine()) != null) {
				Hospital newHospital = new Hospital();
				tempArr = line.split(delimiter);
				if (i != 0) {
					newHospital.setHospitalId(tempArr[0]);
					newHospital.setState(tempArr[1]);
					newHospital.setCity(tempArr[2]);
					newHospital.setHospital(tempArr[3]);
					newHospital.setCategory(tempArr[4]);
					newHospital.setSystemsOfMedicine(tempArr[5]);
					newHospital.setContactDetails(tempArr[6]);
					newHospital.setPinCode(tempArr[7]);
					newHospital.setEmailAddress(tempArr[8]);
					newHospital.setWebsiteLink(tempArr[9]);
					newHospital.setCreatedDate(newHospital.getCreatedDate());
					newHospital.setUpdatedDate(newHospital.getUpdatedDate());
					hospitalRepository.save(newHospital);
				}
				i++;
				System.out.println();
			}
			br.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return "successfully loaded!!";
	}

	// ------------------------------------------DOCTOR DATA CSV READING
	// CODE--------------------------------------------------------------------------------------------------------
	public String loadDoctorInfo(MultipartFile file)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		final String delimiter = ",";
		try {
			File f = new File(
					System.getProperty("user.dir") + "\\src\\main\\resources\\csv's\\" + file.getOriginalFilename());
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			String[] tempArr;
			int i = 0;
			while ((line = br.readLine()) != null) {
				Doctor newHDoctor = new Doctor();
				tempArr = line.split(delimiter);
				if (i != 0) {
					newHDoctor.setId(tempArr[0]);
					newHDoctor.setName(tempArr[1]);
					newHDoctor.setAddress(tempArr[2]);
					newHDoctor.setArea(tempArr[3]);
					newHDoctor.setCity(tempArr[4]);
					newHDoctor.setPhone(tempArr[5]);
					newHDoctor.setEmail(tempArr[6]);
					newHDoctor.setCreatedDate(newHDoctor.getCreatedDate());
					newHDoctor.setUpdatedDate(newHDoctor.getUpdatedDate());
					doctorRepository.save(newHDoctor);

				}
				i++;
				System.out.println();
			}
			br.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return "successfully loaded!!";
	}
}