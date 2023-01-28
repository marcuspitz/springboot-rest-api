package com.example.restservice.infrastructure.mapping;

import org.modelmapper.ModelMapper;

import com.example.restservice.application.dtos.FlightDto;
import com.example.restservice.domain.models.Flight;
import com.example.restservice.view.model.FlightViewModel;

public class MapperDefinitions {
	
	public static FlightViewModel ToFlightView(FlightDto value, ModelMapper mapper) {
		return mapper.map(value, FlightViewModel.class);
	}
	
	public static FlightDto ToFlightDto(Flight value, ModelMapper mapper) {
		return mapper.map(value, FlightDto.class);
	}
	
	public static Flight ToFlightModel(FlightDto value, ModelMapper mapper) {
		return mapper.map(value, Flight.class);
	}

}
