package com.ams.fare.routes;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ams.fare.filters.FareFilter;
import com.ams.fare.handler.FareHandler;

import io.micrometer.core.instrument.MeterRegistry;

@Configuration
public class FareRoutesConfiguration
{
	private static final Logger logger = LoggerFactory.getLogger(FareRoutesConfiguration.class);

	@Bean
	public RouterFunction<ServerResponse> routingFunction(FareHandler fareHandler, MeterRegistry registry)
	{
		logger.info("Routing functions activated.");
		return RouterFunctions
			.route(GET("/fare").and(accept(APPLICATION_JSON)), fareHandler.getFare)
			.filter(new FareFilter(registry));
	}
}
