package com.example.restservice.view.controllers;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.application.interfaces.FlightService;
import com.example.restservice.infrastructure.mapping.MapperDefinitions;
import com.example.restservice.view.model.FlightViewModel;

@RestController
@Scope("request")
@RequestMapping("flights")
public class FlightController {
	
	private final FlightService service;
	private final ModelMapper mapper;
	
	public FlightController(FlightService service, ModelMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	@GetMapping("")
	public List<FlightViewModel> flights(@RequestParam(value = "limit", defaultValue = "10") int limit) {		
		return service.getAll(Optional.of(limit)).stream()
				.map((f) -> MapperDefinitions.ToFlightView(f, this.mapper))
				.toList();
	}
	
	@PostMapping("")
	public void addFlight(@RequestBody FlightViewModel value) {
		service.addFlight(MapperDefinitions.ToFlightDto(value, mapper));
	}
	
	@DeleteMapping("{id}")
	public void deleteFlight(@PathVariable(value="id") String id) {
		service.deleteFlight(id);
	}
	
	@PostMapping("seed")
	public void seed() {
		service.seed();
	}
	
}
