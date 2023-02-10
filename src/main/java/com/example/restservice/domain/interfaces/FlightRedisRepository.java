package com.example.restservice.domain.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.example.restservice.domain.models.FlightRedis;

public interface FlightRedisRepository extends CrudRepository<FlightRedis, String> {

}
