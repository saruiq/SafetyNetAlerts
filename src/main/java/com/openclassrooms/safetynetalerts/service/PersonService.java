package com.openclassrooms.safetynetalerts.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.openclassrooms.safetynetalerts.model.FireStations;
import com.openclassrooms.safetynetalerts.model.MedicalRecords;
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
	
	public List<String> getEmailAddressesByCity(String city) {
		Persons[] persons = JsonParser.personsProfile.getPersons();
		List<String> emailAddresses = new ArrayList<String>();
		for(Persons p : persons) {
			if(p.getCity().equals(city)) {
				emailAddresses.add(p.getEmail());
			}
			else {
				System.out.println("City Not Found");
			}
		}
		return emailAddresses;
	}
	
	public List<String> listOfChildrenByAddress(String address) {
		Persons[] persons = JsonParser.personsProfile.getPersons();
		MedicalRecords[] medicalRecords = JsonParser.personsProfile.getMedicalrecords();
		List<String> adults = new ArrayList<String>();
		List<String> childs = new ArrayList<String>();
		int child = 0;
		String birthDate;
		String[] newDate;
		String month;
		String day;
		String year;
		String finalDate;
		LocalDate givenDate;
		LocalDate currentDate;
		int age = 0;
		
		for(Persons p : persons) {
			if(p.getAddress().equals(address)) {
				for(MedicalRecords m : medicalRecords) {
					if(m.getFirstName().equals(p.getFirstName()) && m.getLastName().equals(p.getLastName())) {
						birthDate = m.getBirthdate();
						newDate = birthDate.split("/");
						month = newDate[0];
						day = newDate[1];
						year = newDate[2];
						finalDate = year + "-" + month + "-" + day;
						givenDate = LocalDate.parse(finalDate);
						currentDate = LocalDate.now();
						if(givenDate != null && currentDate != null) {
							age = Period.between(givenDate, currentDate).getYears();
						} 
						if(age >= 18) {
							adults.add(m.getFirstName() + " " + m.getLastName());
						}
						else {
							child++;
							childs.add("Child #" + child + " " + m.getFirstName() + " " + m.getLastName() + ", Age: " + age);
						}
					}
				}
			}
		}
		if(!childs.isEmpty()) {
			childs.addAll(adults);
		}
		else {
			childs.add("");
		}
		
		return childs;
	}
	
	public List<String> getPersonInfo(String firstName, String lastName) {
		Persons[] persons = JsonParser.personsProfile.getPersons();
		MedicalRecords[] medicalRecords = JsonParser.personsProfile.getMedicalrecords();
		List<String> personInfo = new ArrayList<String>();
		String birthDate;
		String[] newDate;
		String month;
		String day;
		String year;
		String finalDate;
		LocalDate givenDate;
		LocalDate currentDate;
		int age = 0;
		for(Persons p : persons) {
			if(p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {
				for(MedicalRecords m : medicalRecords) {
					if(m.getFirstName().equals(p.getFirstName()) && m.getLastName().equals(p.getLastName())) {
						birthDate = m.getBirthdate();
						newDate = birthDate.split("/");
						month = newDate[0];
						day = newDate[1];
						year = newDate[2];
						finalDate = year + "-" + month + "-" + day;
						givenDate = LocalDate.parse(finalDate);
						currentDate = LocalDate.now();
						if(givenDate != null && currentDate != null) {
							age = Period.between(givenDate, currentDate).getYears();
						} 
						personInfo.add(m.getFirstName() + " " + m.getLastName() + ", Age: " + age + ", Email: " + p.getEmail() + ", Medications: " + Arrays.toString(m.getMedications()) + ", Allergies: " + Arrays.toString(m.getAllergies()));
					}
				}
			}
		}
		return personInfo;
	}
	

}
