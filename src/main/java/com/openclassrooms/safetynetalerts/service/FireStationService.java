package com.openclassrooms.safetynetalerts.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.openclassrooms.safetynetalerts.model.FireStations;
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
					persons.add(p.getFirstName());
					persons.add(p.getLastName());
					persons.add(p.getAddress());
					persons.add(p.getPhone());
					
				}
			}
		}
		return persons;
	}
	
//	public List<String> getPhoneNumbersFromStationNumber(String stationNumber) {
//		List<String> persons = getPersonsFromAddresses(stationNumber);
//		for(String p : persons) {
//			if()
//		}
//	}

}
