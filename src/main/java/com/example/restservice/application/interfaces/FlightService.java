package com.example.restservice.application.interfaces;

import java.util.List;
import java.util.Optional;

import com.example.restservice.application.dtos.FlightClusterDto;
import com.example.restservice.application.dtos.FlightDto;
import com.example.restservice.application.dtos.FlightPathDto;

public interface FlightService {
	
	List<FlightDto> getAll(Optional<Integer> limit);
	
	List<FlightClusterDto> getAllCluster();
	
	void addFlight(FlightDto value);
	
	void deleteFlight(String id);
	
	void seed();
	
	List<FlightPathDto> getPaths(String origin, String destination);
	
}
