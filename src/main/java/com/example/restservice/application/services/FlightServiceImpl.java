package com.example.restservice.application.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.restservice.application.dtos.FlightClusterDto;
import com.example.restservice.application.dtos.FlightDto;
import com.example.restservice.application.dtos.FlightPathDto;
import com.example.restservice.application.interfaces.FlightService;
import com.example.restservice.domain.interfaces.FlightDomainService;
import com.example.restservice.domain.interfaces.FlightRedisRepository;
import com.example.restservice.domain.interfaces.FlightRepository;
import com.example.restservice.domain.models.Flight;
import com.example.restservice.domain.models.FlightPath;
import com.example.restservice.domain.models.FlightRedis;
import com.example.restservice.infrastructure.exceptions.NotFoundException;
import com.example.restservice.infrastructure.mapping.MapperDefinitions;

@Service
@Scope("request")
public class FlightServiceImpl implements FlightService{
	
	private final FlightRepository repository;
	private final FlightRedisRepository redis;
	private final ModelMapper mapper;
	private final FlightDomainService domainService;
	private final Logger logger = LoggerFactory.getLogger(FlightServiceImpl.class);
	
	public FlightServiceImpl(FlightRepository repository, ModelMapper mapper, FlightDomainService domainService, FlightRedisRepository redis) {
		this.repository = repository;
		this.redis = redis;
		this.mapper = mapper;
		this.domainService = domainService;
	}

	public List<FlightDto> getAll(Optional<Integer> limit) {
		return repository.findAll(PageRequest.of(0, limit.orElse(10))).toList()
				.stream().map(flight -> MapperDefinitions.ToFlightDto(flight, this.mapper))
				.toList();
	}

	public void seed() {
		List<Flight> seeds = domainService.getSeeds();
		repository.saveAll(seeds);
		List<FlightRedis> redisSeeds = seeds.stream().map(v -> new FlightRedis(v.getId(), v.getOrigin(), v.getDestination())).toList();
		redis.saveAll(redisSeeds);
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
		List<Flight> flights = (List<Flight>) repository.findAll();
		logger.info(String.format("Found flights: \n%s", String.join("\n", flights.stream().map(flight -> String.format("%s -> %s", flight.getOrigin(), flight.getDestination())).toList())));
		
		List<FlightPath> paths = domainService.doPaths(origin, destination, flights);
		logger.info(String.format("Paths (%d): \n%s", paths.size(), String.join("\n", paths.stream()
				.map(path -> String.join(" -> ", path.getFlights()
						.stream()
						.map(f -> String.format("%s - %s", f.getOrigin(), f.getDestination())).toList()))
				.toList())));
		
		return paths.stream().map(path -> {
			FlightPathDto pathDto = new FlightPathDto();
			pathDto.setFlights(path.getFlights().stream().map(f -> MapperDefinitions.ToFlightDto(f, this.mapper)).toList());
			pathDto.setDestination(destination);
			pathDto.setOrigin(origin);
			pathDto.setDeparture(pathDto.getFlights().stream().findFirst().get().getDeparture());
			pathDto.setDuration(pathDto.getFlights().stream().map(f -> f.getDuration()).reduce(0l, Long::sum));
			return pathDto;
		}).toList();
	}

	@Override
	public List<FlightClusterDto> getAllCluster() {
		return StreamSupport.stream(redis.findAll().spliterator(), false).map(v -> MapperDefinitions.ToFlightClusterDto(v, this.mapper)).toList();
	}
	
}
