package com.openclassrooms.safetynetalerts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetynetalerts.model.FireStations;
import com.openclassrooms.safetynetalerts.model.MedicalRecords;
import com.openclassrooms.safetynetalerts.model.Persons;
import com.openclassrooms.safetynetalerts.service.FireStationService;
import com.openclassrooms.safetynetalerts.service.MedicalRecordService;
import com.openclassrooms.safetynetalerts.service.PersonService;
import com.openclassrooms.safetynetalerts.utils.JsonParser;

@RestController
public class SafetyNetAlertsController {
	
	private static final Logger logger = LoggerFactory.getLogger(SafetyNetAlertsController.class);
	
	PersonService personService = new PersonService();
	
	FireStationService fireStationService = new FireStationService();
	
	MedicalRecordService medicalRecordService = new MedicalRecordService();

	
	 @PostMapping("/person")
	  public ResponseEntity<Persons> createPersons(@RequestBody Persons persons) {
		 logger.info("HTTP POST request received at /person URL ");
	    try {
	      Persons newPerson = new Persons(persons.getFirstName(), persons.getLastName(), persons.getAddress(), persons.getCity(), persons.getZip(), persons.getPhone(), persons.getEmail());
	      personService.addPersons(newPerson);
	      JsonParser.printJson();
	      return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
	    } catch (Exception e) {
	    	e.printStackTrace();
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	 
	 @PutMapping("/person")
	  public ResponseEntity<Persons> updatePersons(@RequestBody Persons persons) {
		 logger.info("HTTP PUT request received at /person URL ");
		 try {
			 HttpStatus httpStatus = personService.updatePersonsByName(persons);
			 Persons updatedPerson = new Persons(persons.getFirstName(), persons.getLastName(), persons.getAddress(), persons.getCity(), persons.getZip(), persons.getPhone(), persons.getEmail());
			 JsonParser.printJson();
		      return new ResponseEntity<>(updatedPerson, httpStatus);
		    } catch (Exception e) {
		    	e.printStackTrace();
		      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		    }
	    }
	 
	 @DeleteMapping("/person")
	  public ResponseEntity<Persons> deletePersons(@RequestBody Persons persons) {
		 logger.info("HTTP DELETE request received at /person URL ");
		 try {
			 HttpStatus httpStatus = personService.deletePersonsByName(persons);
			 JsonParser.printJson();
		      return new ResponseEntity<>(null, httpStatus);
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
	      fireStationService.addFireStations(newFireStation);
	      JsonParser.printJson();
	      return new ResponseEntity<>(newFireStation, HttpStatus.CREATED);
	    } catch (Exception e) {
	    	e.printStackTrace();
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	 
	 @PutMapping("/fireStation")
	  public ResponseEntity<FireStations> updateFireStations(@RequestBody FireStations fireStation) {
		 logger.info("HTTP PUT request received at /fireStation URL ");
	    try {
	    	HttpStatus httpStatus = fireStationService.updateFireStationsNumberByAddress(fireStation);
	    	FireStations updatedFireStation = new FireStations(fireStation.getAddress(), fireStation.getStation());
	      JsonParser.printJson();
	      return new ResponseEntity<>(updatedFireStation, httpStatus);
	    } catch (Exception e) {
	    	e.printStackTrace();
	      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	    }
	  }
	 
	 @DeleteMapping("/fireStation")
	  public ResponseEntity<Persons> deleteFireStations(@RequestBody FireStations fireStation) {
		 logger.info("HTTP DELETE request received at /fireStation URL ");
		 try {
			 HttpStatus httpStatus = fireStationService.deleteFireStationsByAddress(fireStation);
			 JsonParser.printJson();
		      return new ResponseEntity<>(null, httpStatus);
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
	      medicalRecordService.addMedicalRecords(newMedicalRecord);
	      JsonParser.printJson();
	      return new ResponseEntity<>(newMedicalRecord, HttpStatus.CREATED);
	    } catch (Exception e) {
	    	e.printStackTrace();
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	 
	 @PutMapping("/medicalRecord")
	  public ResponseEntity<MedicalRecords> updateMedicalRecords(@RequestBody MedicalRecords medicalRecord) {
		 logger.info("HTTP PUT request received at /medicalRecord URL ");
	    try {
	    	HttpStatus httpStatus = medicalRecordService.updateMedicalRecordsByName(medicalRecord);
	    	MedicalRecords updatedMedicalRecord =  new MedicalRecords(medicalRecord.getFirstName(), medicalRecord.getLastName(), medicalRecord.getBirthdate(), medicalRecord.getMedications(), medicalRecord.getAllergies());
	      JsonParser.printJson();
	      return new ResponseEntity<>(updatedMedicalRecord, httpStatus);
	    } catch (Exception e) {
	    	e.printStackTrace();
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	 
	 @DeleteMapping("/medicalRecord")
	  public ResponseEntity<Persons> deleteMedicalRecords(@RequestBody MedicalRecords medicalRecord) {
		 logger.info("HTTP DELETE request received at /medicalRecord URL ");
		 try {
			 HttpStatus httpStatus = medicalRecordService.deleteMedicalRecordsByName(medicalRecord);
			 JsonParser.printJson();
		      return new ResponseEntity<>(null, httpStatus);
		    } catch (Exception e) {
		    	e.printStackTrace();
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	    }

	

}
