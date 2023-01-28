package com.example.restservice.domain.interfaces;

import java.util.List;

import com.example.restservice.domain.models.Flight;

public interface FlightDomainService {

	List<Flight> getSeeds();
	
	void validateFlight(Flight value);
	
}
