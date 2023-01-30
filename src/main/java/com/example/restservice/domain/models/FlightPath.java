package com.example.restservice.domain.models;

import java.util.List;

public class FlightPath {
	private List<Flight> flights;

	
	
	public FlightPath(List<Flight> flights) {
		super();
		this.flights = flights;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}
	
}
