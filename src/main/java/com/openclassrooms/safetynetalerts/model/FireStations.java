package com.openclassrooms.safetynetalerts.model;

public class FireStations {
	
	private String address;

	private String station;
	
	
	public FireStations(String address, String station) {
		super();
		this.address = address;
		this.station = station;
	}

	public FireStations() {
		super();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

}
