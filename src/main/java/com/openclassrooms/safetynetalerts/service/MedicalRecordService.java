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
	
	public HttpStatus findAndUpdateMedicalRecordsByName(MedicalRecords medicalRecord) {
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

}
