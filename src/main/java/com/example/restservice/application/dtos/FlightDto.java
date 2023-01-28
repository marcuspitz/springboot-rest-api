package com.example.restservice.application.dtos;

public class FlightDto {
	
	private String id;
	private String origin;
	private String destination;
	private long departure;
	private long destinationDeparture;
	private long duration;
	private long backDuration;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public long getDestinationDeparture() {
		return destinationDeparture;
	}
	public void setDestinationDeparture(long destinationDeparture) {
		this.destinationDeparture = destinationDeparture;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public long getBackDuration() {
		return backDuration;
	}
	public void setBackDuration(long backDuration) {
		this.backDuration = backDuration;
	}
	
}
