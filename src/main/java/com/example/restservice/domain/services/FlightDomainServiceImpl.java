package com.example.restservice.domain.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.restservice.domain.interfaces.FlightDomainService;
import com.example.restservice.domain.models.Flight;
import com.example.restservice.domain.models.FlightPath;
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
		if (value.getId() == null || value.getId().trim().equals("")) {
			throw new ValidateException("Flight ID can't be null or empty");
		}
		if (value.getOrigin() == null || value.getOrigin().trim().equals("")) {
			throw new ValidateException("Flight ORIGIN can't be null or empty");
		}
		if (value.getDestination() == null || value.getDestination().trim().equals("")) {
			throw new ValidateException("Flight DESTINATION can't be null or empty");
		}
	}

	@Override
	public List<FlightPath> doPaths(String origin, String destination, List<Flight> flights) {
		List<FlightPath> container = new ArrayList<FlightPath>();
		List<Flight> origins = flights.stream().filter(f -> f.getOrigin().equals(origin)).toList();
		origins.forEach(o -> this.reducePath(new ArrayList<Flight>(Arrays.asList(o)), container, flights, destination));
		return container;
	}
	
	private void reducePath(List<Flight> currentPath, List<FlightPath> container, List<Flight> availableFlights, String finalDestination) {
		Flight current = currentPath.get(currentPath.size()-1);
		if (current.getDestination().equals(finalDestination)) {
			container.add(new FlightPath(currentPath));
		} else if (availableFlights.size() > 0) {
			List<Flight> nextOnes = availableFlights.stream().filter(a -> a.getOrigin().equals(current.getDestination())).toList();
			nextOnes.forEach(next -> {
				List<Flight> newPath = new ArrayList<Flight>(currentPath);
				newPath.add(next);
				reducePath(newPath, container, availableFlights.stream().filter(a -> !nextOnes.contains(a)).toList(), finalDestination);
			});
		}
	}

}
