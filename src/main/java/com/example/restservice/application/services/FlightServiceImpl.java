package com.example.restservice.application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.restservice.application.dtos.FlightDto;
import com.example.restservice.application.dtos.FlightPathDto;
import com.example.restservice.application.interfaces.FlightService;
import com.example.restservice.domain.interfaces.FlightDomainService;
import com.example.restservice.domain.interfaces.FlightRepository;
import com.example.restservice.domain.models.Flight;
import com.example.restservice.infrastructure.exceptions.NotFoundException;
import com.example.restservice.infrastructure.mapping.MapperDefinitions;

@Service
@Scope("request")
public class FlightServiceImpl implements FlightService{
	
	private final FlightRepository repository;
	private final ModelMapper mapper;
	private final FlightDomainService domainService;
	private final Logger logger = LoggerFactory.getLogger(FlightServiceImpl.class);
	
	public FlightServiceImpl(FlightRepository repository, ModelMapper mapper, FlightDomainService domainService) {
		this.repository = repository;
		this.mapper = mapper;
		this.domainService = domainService;
	}

	public List<FlightDto> getAll(Optional<Integer> limit) {
		return repository.findAll(PageRequest.of(0, limit.orElse(10))).toList()
				.stream().map(flight -> MapperDefinitions.ToFlightDto(flight, this.mapper))
				.toList();
	}

	public void seed() {
		repository.saveAll(domainService.getSeeds());
	}

	public void addFlight(FlightDto value) {
		Flight flight = MapperDefinitions.ToFlightModel(value, mapper);
		domainService.validateFlight(flight);
		repository.save(flight);
	}

	public void deleteFlight(String id) {
		Flight value = repository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Flight %s not found", id)));
		repository.delete(value);
	}
	
	public List<FlightPathDto> getPaths(String origin, String destination) {
		logger.info(String.format("Bulding paths for %s to %s", origin, destination));
		
		List<Flight> flights = repository.findByOriginOrDestination(origin, destination);
		
		logger.info(String.format("Found flights: \n%s", String.join("\n", flights.stream().map(flight -> String.format("%s -> %s", flight.getOrigin(), flight.getDestination())).toList())));
		
		return new ArrayList<FlightPathDto>();
	}
	
}
