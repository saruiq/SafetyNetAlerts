package com.openclassrooms.safetynetalerts.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.openclassrooms.safetynetalerts.model.FireStations;
import com.openclassrooms.safetynetalerts.utils.JsonParser;

public class FireStationService {
	
	public void addFireStations(FireStations fireStation) {
		FireStations[] firestations = JsonParser.personsProfile.getFirestations();
		List<FireStations> fireStationList  = new ArrayList<FireStations>(Arrays.asList(firestations));
		fireStationList.add(fireStation);
		firestations = fireStationList.toArray(firestations);
		JsonParser.personsProfile.setFirestations(firestations);
	}
	
	public HttpStatus findAndUpdateFireStationsNumberByAddress(FireStations fireStation) {
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

}
