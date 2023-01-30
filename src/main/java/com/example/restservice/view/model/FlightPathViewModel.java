package com.example.restservice.view.model;

import java.util.List;

public class FlightPathViewModel {
	
	private List<FlightViewModel> flights;
	private String origin;
	private String destination;
	private long departure;
	private long duration;
	
	public List<FlightViewModel> getFlights() {
		return flights;
	}
	public void setFlights(List<FlightViewModel> flights) {
		this.flights = flights;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public long getDeparture() {
		return departure;
	}
	public void setDeparture(long departure) {
		this.departure = departure;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	
	
}
