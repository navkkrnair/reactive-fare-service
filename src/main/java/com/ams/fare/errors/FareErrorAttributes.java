package com.ams.fare.errors;

import java.util.Map;

import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import lombok.Getter;
import lombok.Setter;

@Component
public class FareErrorAttributes extends DefaultErrorAttributes
{

	@Getter
	@Setter
	private String description = "flightDate should be in a valid format";

	@Getter
	@Setter
	private HttpStatus status = HttpStatus.BAD_REQUEST;

	public FareErrorAttributes()
	{
		super(false);
	}

	@Override
	public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace)
	{
		Map<String, Object> map = super.getErrorAttributes(request, includeStackTrace);
		map.put("status", getStatus());
		map.put("description", getDescription());
		return map;
	}

}
