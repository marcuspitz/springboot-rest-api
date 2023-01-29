package com.example.restservice.view.model;

public class FlightPathViewModel {
	private String flightId;
	private String origin;
	private String destination;
	private long departure;
	private long duration;
	
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
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
