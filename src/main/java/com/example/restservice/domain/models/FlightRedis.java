package com.example.restservice.domain.models;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("Flight")
public class FlightRedis {
	
	private String id;
	private String origin;
	private String destination;
	
	public FlightRedis() {
	}

	public FlightRedis(String id, String origin, String destination) {
		this.id = id;
		this.origin = origin;
		this.destination = destination;
	}

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

}
