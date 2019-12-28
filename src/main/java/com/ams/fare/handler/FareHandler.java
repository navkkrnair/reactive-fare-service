package com.ams.fare.handler;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ams.fare.entity.Fare;
import com.ams.fare.service.FareService;

import reactor.core.publisher.Mono;

@Component
public class FareHandler
{
	private static final Logger logger = LoggerFactory.getLogger(FareHandler.class);
	private FareService         fareService;

	public FareHandler(FareService fareService)
	{
		this.fareService = fareService;
	}

	public HandlerFunction<ServerResponse> getFare = serverRequest ->
	{
		logger.info(">> getFare handler called");
		String               flightNumber = serverRequest.queryParam("flightNumber").get();
		LocalDate            flightDate   = serverRequest.queryParam("flightDate").map(fd -> LocalDate.parse(fd)).get();
		Mono<ServerResponse> notFound     = ServerResponse.notFound().build();
		Mono<Fare>           fareMono     = this.fareService.getFare(flightNumber, flightDate);
		return fareMono.map(fare ->
		{
			logger.info("Fare fetched: {}", fare);
			return fare;
		})
			.flatMap(fare -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromValue(fare)))
			.switchIfEmpty(notFound);
	};

}
