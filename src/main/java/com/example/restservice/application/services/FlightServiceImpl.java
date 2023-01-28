package com.example.restservice.application.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.restservice.application.dtos.FlightDto;
import com.example.restservice.application.interfaces.FlightService;
import com.example.restservice.domain.interfaces.FlightRepository;
import com.example.restservice.infrastructure.mapping.MapperDefinitions;

@Service
@Scope("request")
public class FlightServiceImpl implements FlightService{
	
	private final FlightRepository repository;
	private final ModelMapper mapper;
	
	public FlightServiceImpl(FlightRepository repository, ModelMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	public List<FlightDto> getAll(Optional<Integer> limit) {		
		return repository.findAll(PageRequest.of(0, limit.orElse(10))).toList()
				.stream().map(flight -> MapperDefinitions.ToFlightDto(flight, this.mapper))
				.toList();
	}
	
	
}
