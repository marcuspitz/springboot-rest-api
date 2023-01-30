package com.example.restservice.application.dtos;

import java.util.List;

public class FlightPathDto {

	private List<FlightDto> flights;
	private String origin;
	private String destination;
	private long departure;
	private long duration;
	
	public List<FlightDto> getFlights() {
		return flights;
	}
	public void setFlights(List<FlightDto> flights) {
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
