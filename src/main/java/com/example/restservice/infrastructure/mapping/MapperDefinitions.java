package com.example.restservice.infrastructure.mapping;

import org.modelmapper.ModelMapper;

import com.example.restservice.application.dtos.FlightClusterDto;
import com.example.restservice.application.dtos.FlightDto;
import com.example.restservice.application.dtos.FlightPathDto;
import com.example.restservice.domain.models.Flight;
import com.example.restservice.domain.models.FlightRedis;
import com.example.restservice.view.model.FlightClusterViewModel;
import com.example.restservice.view.model.FlightPathViewModel;
import com.example.restservice.view.model.FlightViewModel;

public class MapperDefinitions {
	
	public static FlightViewModel ToFlightView(FlightDto value, ModelMapper mapper) {
		return mapper.map(value, FlightViewModel.class);
	}
	
	public static FlightClusterViewModel ToFlightClusterView(FlightClusterDto value, ModelMapper mapper) {
		return mapper.map(value, FlightClusterViewModel.class);
	}
	
	public static FlightClusterDto ToFlightClusterDto(FlightRedis value, ModelMapper mapper) {
		return mapper.map(value, FlightClusterDto.class);
	}
	
	public static FlightDto ToFlightDto(Flight value, ModelMapper mapper) {
		return mapper.map(value, FlightDto.class);
	}
	
	public static FlightDto ToFlightDto(FlightViewModel value, ModelMapper mapper) {
		return mapper.map(value, FlightDto.class);
	}
	
	public static Flight ToFlightModel(FlightDto value, ModelMapper mapper) {
		return mapper.map(value, Flight.class);
	}
	
	public static FlightPathViewModel ToFlightPathView(FlightPathDto value, ModelMapper mapper) {
		return mapper.map(value, FlightPathViewModel.class);
	}

}
