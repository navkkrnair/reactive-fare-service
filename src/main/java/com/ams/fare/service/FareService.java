package com.ams.fare.service;

import java.time.LocalDate;

import com.ams.fare.entity.Fare;

import reactor.core.publisher.Mono;

public interface FareService
{
	public Mono<Fare> getFare(String flightNumber, LocalDate flightDate);
}
