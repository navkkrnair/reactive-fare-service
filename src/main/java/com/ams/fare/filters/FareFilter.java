package com.ams.fare.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import io.micrometer.core.instrument.MeterRegistry;
import reactor.core.publisher.Mono;

public class FareFilter implements HandlerFilterFunction<ServerResponse, ServerResponse>
{
	private static final Logger logger = LoggerFactory.getLogger(FareFilter.class);
	private MeterRegistry       registry;
	private static final String FARE   = "/fare";
	private static String       URI, METHOD, pathKey;

	public FareFilter(MeterRegistry registry)
	{
		this.registry = registry;
	}

	@Override
	public Mono<ServerResponse> filter(ServerRequest request, HandlerFunction<ServerResponse> next)
	{
		logger.info("Filtering fare-service");
		URI     = request.path();
		METHOD  = request.methodName();
		pathKey = "api_".concat(METHOD.toLowerCase()).concat(URI.replaceAll("/", "_")).toLowerCase();
		if (URI.contains(FARE))
		{
			logger.info("PathKey metrics {} incrementing", pathKey);
			registry.counter(pathKey).increment();
		}
		return next.handle(request);
	}

}
