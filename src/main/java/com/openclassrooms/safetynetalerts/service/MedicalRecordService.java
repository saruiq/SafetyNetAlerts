package com.openclassrooms.safetynetalerts.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.openclassrooms.safetynetalerts.model.MedicalRecords;
import com.openclassrooms.safetynetalerts.utils.JsonParser;

public class MedicalRecordService {
	
	public void addMedicalRecords(MedicalRecords medicalRecord) {
		MedicalRecords[] medicalrecords = JsonParser.personsProfile.getMedicalrecords();
		List<MedicalRecords> medicalRecordList = new ArrayList<MedicalRecords>(Arrays.asList(medicalrecords));
		medicalRecordList.add(medicalRecord);
		medicalrecords = medicalRecordList.toArray(medicalrecords);
		JsonParser.personsProfile.setMedicalrecords(medicalrecords);	
	}
	
	public HttpStatus updateMedicalRecordsByName(MedicalRecords medicalRecord) {
		MedicalRecords[] medicalRecords = JsonParser.personsProfile.getMedicalrecords();
		for(MedicalRecords m : medicalRecords) {
			if(m.getFirstName().equals(medicalRecord.getFirstName()) && m.getLastName().equals(medicalRecord.getLastName())) {
				m.setBirthdate(medicalRecord.getBirthdate());
				m.setMedications(medicalRecord.getMedications());
				m.setAllergies(medicalRecord.getAllergies());		
				return HttpStatus.OK;
			}	
		}
		System.out.println("Medical Record Not Found");
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}
	
	public HttpStatus deleteMedicalRecordsByName(MedicalRecords medicalRecord) {
		Boolean medicalrecordDeleted = false;
		MedicalRecords[] medicalRecords = JsonParser.personsProfile.getMedicalrecords();
		List<MedicalRecords> medicalRecordsList = new ArrayList<MedicalRecords>();
		for(MedicalRecords m : medicalRecords) {
			if(!m.getFirstName().equals(medicalRecord.getFirstName()) && !m.getLastName().equals(medicalRecord.getLastName())) {
				medicalRecordsList.add(m);
				continue;
			}
			medicalrecordDeleted = true;
		}
		if(medicalrecordDeleted == true) {
			MedicalRecords[] newMedicalRecordsArray = new MedicalRecords[medicalRecordsList.size()];
			newMedicalRecordsArray = medicalRecordsList.toArray(newMedicalRecordsArray);
			JsonParser.personsProfile.setMedicalrecords(newMedicalRecordsArray);
			return HttpStatus.OK;
		}else {
			System.out.println("Medical Record Not Found");
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	
	}

}
