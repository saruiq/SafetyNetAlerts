package com.openclassrooms.safetynetalerts.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynetalerts.model.FireStations;
import com.openclassrooms.safetynetalerts.model.MedicalRecords;
import com.openclassrooms.safetynetalerts.model.Persons;
import com.openclassrooms.safetynetalerts.model.PersonsProfile;

public class JsonParser {
	
	byte[] mapData;

	java.util.Map<String, String> personsMap;
	
	Map<String, String> fireStationsMap;
	
	Map<String, String> medicalRecordsMap;
	
	
	public void parseJsonToObject() throws IOException {
		
		File jsonData = new File("src/main/resources/templates/data.json");
				
		ObjectMapper objectMapper = new ObjectMapper();
	
		PersonsProfile personsProfile = objectMapper.readValue(jsonData, PersonsProfile.class);
		
		 for (Persons p : personsProfile.getPersons()) {
			 System.out.println(p.getFirstName() + " " + p.getLastName());
	     }
		 
		 for (FireStations f : personsProfile.getFirestations()) {
			 System.out.println(f.getAddress() + " " + f.getStation());
		 }
		 
		 for (MedicalRecords mr : personsProfile.getMedicalrecords()) {
			 System.out.print(mr.getFirstName() + " " + mr.getLastName());
			 System.out.print("  Medications : ");
			 	for (String s : mr.getMedications())
			 		System.out.print(s + " ");
			 System.out.print("  Allergies : ");
			 	for (String s : mr.getAllergies())
			 		System.out.print(s + " ");
			 System.out.println("");
		 }
	}
	
	

}
