package com.openclassrooms.safetynetalerts.model;

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

}
