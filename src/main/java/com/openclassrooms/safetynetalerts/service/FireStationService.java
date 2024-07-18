package com.openclassrooms.safetynetalerts.service;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.openclassrooms.safetynetalerts.model.FireStations;
import com.openclassrooms.safetynetalerts.model.MedicalRecords;
import com.openclassrooms.safetynetalerts.model.Persons;
import com.openclassrooms.safetynetalerts.utils.JsonParser;

public class FireStationService {
	
	public void addFireStations(FireStations fireStation) {
		FireStations[] firestations = JsonParser.personsProfile.getFirestations();
		List<FireStations> fireStationList  = new ArrayList<FireStations>(Arrays.asList(firestations));
		fireStationList.add(fireStation);
		firestations = fireStationList.toArray(firestations);
		JsonParser.personsProfile.setFirestations(firestations);
	}
	
	public HttpStatus updateFireStationsNumberByAddress(FireStations fireStation) {
		FireStations[] firestations = JsonParser.personsProfile.getFirestations();
		for(FireStations f : firestations) {
			if(f.getAddress().equals(fireStation.getAddress())) {
				f.setStation(fireStation.getStation());
				return HttpStatus.OK;
			}	
		}
		System.out.println("FireStation Address Not Found");
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}
	
	
	public HttpStatus deleteFireStationsByAddress(FireStations fireStation) {
		Boolean firestationDeleted = false;
		FireStations[] fireStations = JsonParser.personsProfile.getFirestations();
		List<FireStations> fireStationsList = new ArrayList<FireStations>();
		for(FireStations f : fireStations) {
			if(!f.getAddress().equals(fireStation.getAddress())) {
				fireStationsList.add(f);
				continue;
			}
			firestationDeleted = true;
		}
		if(firestationDeleted == true) {
			FireStations[] newFireStationsArray = new FireStations[fireStationsList.size()];
			newFireStationsArray = fireStationsList.toArray(newFireStationsArray);
			JsonParser.personsProfile.setFirestations(newFireStationsArray);
			return HttpStatus.OK;
		}else {
			System.out.println("FireStation Address Not Found");
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	
	}
	
	public List<String> getAddressesFromStationNumber(String stationNumber) {
		FireStations[] fireStations = JsonParser.personsProfile.getFirestations();
		List<String> addresses = new ArrayList<String>();
		for(FireStations f : fireStations) {
			if(f.getStation().equals(stationNumber)) {
			   addresses.add(f.getAddress());
			}	
		}
		return addresses;
	}
	
	public List<String> getPersonsFromAddresses(String stationNumber) {
		List<String> addresses = getAddressesFromStationNumber(stationNumber);
		Persons[] allPersons = JsonParser.personsProfile.getPersons();
		List<String> persons = new ArrayList<String>();
		for(Persons p : allPersons) {
			for(String address : addresses) {
				if(p.getAddress().equals(address)) {
					persons.add(p.getFirstName()+ " " + p.getLastName() + "," + p.getAddress() + ", Phone: " + p.getPhone());		
				}
			}
		}
//		String summary = getSummaryOfAdultsAndChildren(stationNumber);
//		persons.add(summary);
		return persons;
	}
	
	public List<String> getBirthdatesFromMedicalRecords(String stationNumber) {
		List<String> persons = getPersonsFromAddresses(stationNumber);
		MedicalRecords[] medicalRecords = JsonParser.personsProfile.getMedicalrecords();
		List<String> birthDates = new ArrayList<String>();
		String[] splitString;
		String name;
		String[] names;
		String firstName;
		String lastName;
		List<String> firstNames = new ArrayList<String>();
		List<String> lastNames = new ArrayList<String>();
		for(String p : persons) {
			splitString = p.split(",");
			name = splitString[0];
			names = name.split(" ");
			firstName = names[0];
			lastName = names[1];
			firstNames.add(firstName);
			lastNames.add(lastName);
		}
		for(MedicalRecords m : medicalRecords) {
			for(String fn : firstNames) {
				for(String ln : lastNames) {
					if(m.getFirstName().equals(fn) && m.getLastName().equals(ln)) {
						birthDates.add(m.getBirthdate());
					}
				}
				
			}
		}
		return birthDates;
	}
	
	public List<Integer> getAgeFromBirthDates(String stationNumber) {
		List<String> birthDates = getBirthdatesFromMedicalRecords(stationNumber);
		String[] newDate;
		String month;
		String day;
		String year;
		String finalDate;
		LocalDate givenDate;
		LocalDate currentDate;
		int age;
		List<Integer> ages = new ArrayList<Integer>();
		for(String birthDate : birthDates) {
			newDate = birthDate.split("/");
			month = newDate[0];
			day = newDate[1];
			year = newDate[2];
			finalDate = year + "-" + month + "-" + day;
			givenDate = LocalDate.parse(finalDate);
			currentDate = LocalDate.now();
			if(givenDate != null && currentDate != null) {
				age = Period.between(givenDate, currentDate).getYears();
				ages.add(age);
			}
		}
		return ages;
	}
	
	public String getSummaryOfAdultsAndChildren(String stationNumber) {
		List<Integer> ages = getAgeFromBirthDates(stationNumber);
		int adult = 0;
		int child = 0;
		String summary = "";
		for(int age : ages) {
			if(age > 18) {
				adult++;
			}
			else {
				child++;
			}
		}
		summary = "Adults: " + adult + "\nChildren: " + child;
		return summary;
	}
	
	
	public List<String> getPhoneNumbersFromFireStationNumber(String fireStationNumber) {
		List<String> persons = getPersonsFromAddresses(fireStationNumber);
		String[] splitString;
		String phoneNumber;
		List<String> phoneNumbers = new ArrayList<String>();
		for(String p : persons) {
			splitString = p.split(", ");
			phoneNumber = splitString[1];
			phoneNumbers.add(phoneNumber);
		}
		return phoneNumbers;
	}

}
