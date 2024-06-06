package com.openclassrooms.safetynetalerts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.openclassrooms.safetynetalerts.model.Persons;
import com.openclassrooms.safetynetalerts.repository.SafetyNetAlertsRepository;

public class SafetyNetAlertsController {
	
	private static final Logger logger = LoggerFactory.getLogger(SafetyNetAlertsController.class);
	
	@Autowired
	SafetyNetAlertsRepository repo;
	
	 @PostMapping("/addPersons")
	  public ResponseEntity<Persons> createTutorial(@RequestBody Persons persons) {
		 logger.info("HTTP POST request received at /addPersons URL ");
	    try {
	      Persons newPerson = (new Persons(persons.getFirstName(), persons.getLastName(), persons.getAddress(), persons.getCity(), persons.getZip(), persons.getPhone(), persons.getEmail()));
	      return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	

}
