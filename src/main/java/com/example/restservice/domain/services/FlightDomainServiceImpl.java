package com.example.restservice.domain.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.restservice.domain.interfaces.FlightDomainService;
import com.example.restservice.domain.models.Flight;
import com.example.restservice.infrastructure.exceptions.ValidateException;

@Service
@Scope("request")
public class FlightDomainServiceImpl implements FlightDomainService {

	public List<Flight> getSeeds() {
		List<Flight> seeds = new ArrayList<Flight>();
		
		seeds.add(new Flight(UUID.randomUUID().toString(), "SP", "RJ", 10, 20, 30, 40));
		seeds.add(new Flight(UUID.randomUUID().toString(), "NY", "HK", 11, 22, 33, 44));
		seeds.add(new Flight(UUID.randomUUID().toString(), "HK", "FK", 3, 12, 66, 55));
		
		return seeds;
	}

	@Override
	public void validateFlight(Flight value) {
		if (value.getId() == null || value.getId().trim() == "") {
			throw new ValidateException("Flight ID can't be null or empty");
		}
		if (value.getOrigin() == null || value.getOrigin().trim() == "") {
			throw new ValidateException("Flight ORIGIN can't be null or empty");
		}
		if (value.getDestination() == null || value.getDestination().trim() == "") {
			throw new ValidateException("Flight DESTINATION can't be null or empty");
		}
	}

}
