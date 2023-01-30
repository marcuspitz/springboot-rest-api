package com.example.restservice.domain.interfaces;

import java.util.List;

import com.example.restservice.domain.models.Flight;
import com.example.restservice.domain.models.FlightPath;

public interface FlightDomainService {

	List<Flight> getSeeds();
	
	void validateFlight(Flight value);
	
	List<FlightPath> doPaths(String origin, String destination, List<Flight> flights);
	
}
