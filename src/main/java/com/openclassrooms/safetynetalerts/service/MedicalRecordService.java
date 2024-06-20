package com.openclassrooms.safetynetalerts.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

}
