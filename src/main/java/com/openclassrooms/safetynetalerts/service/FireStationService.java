package com.openclassrooms.safetynetalerts.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

}
