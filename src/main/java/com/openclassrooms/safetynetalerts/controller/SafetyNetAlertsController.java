package com.openclassrooms.safetynetalerts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetynetalerts.model.FireStations;
import com.openclassrooms.safetynetalerts.model.MedicalRecords;
import com.openclassrooms.safetynetalerts.model.Persons;
import com.openclassrooms.safetynetalerts.utils.JsonParser;

@RestController
public class SafetyNetAlertsController {
	
	private static final Logger logger = LoggerFactory.getLogger(SafetyNetAlertsController.class);

	
	 @PostMapping("/person")
	  public ResponseEntity<Persons> createPersons(@RequestBody Persons persons) {
		 logger.info("HTTP POST request received at /person URL ");
	    try {
	      Persons newPerson = new Persons(persons.getFirstName(), persons.getLastName(), persons.getAddress(), persons.getCity(), persons.getZip(), persons.getPhone(), persons.getEmail());
	      JsonParser.personsProfile.addPersons(newPerson);
	      JsonParser.printJson();
	      return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
	    } catch (Exception e) {
	    	e.printStackTrace();
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	 
	 @PostMapping("/fireStation")
	  public ResponseEntity<FireStations> createFireStations(@RequestBody FireStations fireStation) {
		 logger.info("HTTP POST request received at /fireStation URL ");
	    try {
	      FireStations newFireStation = new FireStations(fireStation.getAddress(), fireStation.getStation());
	      JsonParser.personsProfile.addFireStations(newFireStation);
	      JsonParser.printJson();
	      return new ResponseEntity<>(newFireStation, HttpStatus.CREATED);
	    } catch (Exception e) {
	    	e.printStackTrace();
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	 
	 @PostMapping("/medicalRecord")
	  public ResponseEntity<MedicalRecords> createMedicalRecords(@RequestBody MedicalRecords medicalRecord) {
		 logger.info("HTTP POST request received at /medicalRecord URL ");
	    try {
	      MedicalRecords newMedicalRecord = new MedicalRecords(medicalRecord.getFirstName(), medicalRecord.getLastName(), medicalRecord.getBirthdate(), medicalRecord.getMedications(), medicalRecord.getAllergies());
	      JsonParser.personsProfile.addMedicalRecords(newMedicalRecord);
	      JsonParser.printJson();
	      return new ResponseEntity<>(newMedicalRecord, HttpStatus.CREATED);
	    } catch (Exception e) {
	    	e.printStackTrace();
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	

}
