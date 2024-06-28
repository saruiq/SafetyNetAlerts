package com.openclassrooms.safetynetalerts.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.openclassrooms.safetynetalerts.model.Persons;
import com.openclassrooms.safetynetalerts.utils.JsonParser;

public class PersonService {
	
	public void addPersons(Persons person) {
		Persons[] persons = JsonParser.personsProfile.getPersons();
		List<Persons> personList = new ArrayList<Persons>(Arrays.asList(persons));
		personList.add(person);
		persons = personList.toArray(persons);
		JsonParser.personsProfile.setPersons(persons);	
	}
	
	public HttpStatus updatePersonsByName(Persons person) {
		Persons[] persons = JsonParser.personsProfile.getPersons();
		for(Persons p : persons) {
			if(p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName())) {
				p.setAddress(person.getAddress());
				p.setCity(person.getCity());
				p.setZip(person.getZip());
				p.setPhone(person.getPhone());
				p.setEmail(person.getEmail());
				return HttpStatus.OK;
			}	
		}
		System.out.println("Person Not Found");
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}
	
	public HttpStatus deletePersonsByName(Persons person) {
		Boolean personDeleted = false;
		Persons[] persons = JsonParser.personsProfile.getPersons();
		List<Persons> personList = new ArrayList<Persons>();
		for(Persons p : persons) {
			if(!p.getFirstName().equals(person.getFirstName()) && !p.getLastName().equals(person.getLastName())) {
				personList.add(p);
				continue;
			}
			personDeleted = true;
		}
		if(personDeleted == true) {
			Persons[] newPersonsArray = new Persons[personList.size()];
			newPersonsArray = personList.toArray(newPersonsArray);
			JsonParser.personsProfile.setPersons(newPersonsArray);
			return HttpStatus.OK;
		}else {
			System.out.println("Person Not Found");
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	
	}

}
