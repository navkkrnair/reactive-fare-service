package com.ams.fare.service.impl;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ams.fare.entity.Fare;
import com.ams.fare.repository.FareRepository;
import com.ams.fare.service.FareService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class FareServiceImpl implements FareService
{
	private final Logger logger = LoggerFactory.getLogger(FareServiceImpl.class);

	private final FareRepository fareRepository;

	public Mono<Fare> getFare(String flightNumber, LocalDate flightDate)
	{
		logger.info("Looking for fares for flightNumber " + flightNumber + " flightDate " + flightDate);
		return Mono.justOrEmpty(this.fareRepository.getFareByFlightNumberAndFlightDate(flightNumber, flightDate));
	}
}
