package com.openclassrooms.safetynetalerts.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonsProfile {
	
	Persons[] persons;
	
	FireStations[] firestations;
	
	MedicalRecords[] medicalrecords;

	public Persons[] getPersons() {
		return persons;
	}

	public void setPersons(Persons[] persons) {
		this.persons = persons;
	}

	public FireStations[] getFirestations() {
		return firestations;
	}

	public void setFirestations(FireStations[] firestations) {
		this.firestations = firestations;
	}

	public MedicalRecords[] getMedicalrecords() {
		return medicalrecords;
	}

	public void setMedicalrecords(MedicalRecords[] medicalrecords) {
		this.medicalrecords = medicalrecords;
	}
	
	public void addPersons(Persons person) {
		List<Persons> personList = new ArrayList<Persons>(Arrays.asList(persons));
		personList.add(person);
		persons = personList.toArray(persons);
		
	}
	
	public void addFireStations(FireStations fireStation) {
		List<FireStations> fireStationList  = new ArrayList<FireStations>(Arrays.asList(firestations));
		fireStationList.add(fireStation);
		firestations = fireStationList.toArray(firestations);
		
	}
	
	public void addMedicalRecords(MedicalRecords medicalRecord) {
		List<MedicalRecords> medicalRecordList = new ArrayList<MedicalRecords>(Arrays.asList(medicalrecords));
		medicalRecordList.add(medicalRecord);
		medicalrecords = medicalRecordList.toArray(medicalrecords);
		
	}
	

	
	


}
