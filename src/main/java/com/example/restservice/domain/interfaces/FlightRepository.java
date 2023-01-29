package com.example.restservice.domain.interfaces;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.restservice.domain.models.Flight;
import com.example.restservice.infrastructure.repositories.shared.PaginationRepository;

public interface FlightRepository extends CrudRepository<Flight, String>, PaginationRepository<Flight> {
	
	List<Flight> findByOriginOrDestination(String origin, String destination);
	
}
