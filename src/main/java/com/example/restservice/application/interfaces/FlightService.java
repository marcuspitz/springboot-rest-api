package com.example.restservice.application.interfaces;

import java.util.List;
import java.util.Optional;

import com.example.restservice.application.dtos.FlightDto;

public interface FlightService {
	
	List<FlightDto> getAll(Optional<Integer> limit);
	
	void addFlight(FlightDto value);
	
	void deleteFlight(String id);
	
	void seed();
	
}
