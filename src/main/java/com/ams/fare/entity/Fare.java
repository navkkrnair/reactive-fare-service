package com.ams.fare.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Fare
{
	@Id
	@GeneratedValue(generator = "fare-uuid")
	@GenericGenerator(name = "fare-uuid", strategy = "uuid2")
	@NotNull
	private String id;

	@NotNull
	private String flightNumber;

	@NotNull
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate flightDate;

	@NotNull
	private String amount;

	public Fare(String flightNumber, LocalDate flightDate, String amount)
	{
		this.flightNumber = flightNumber;
		this.flightDate   = flightDate;
		this.amount       = amount;
	}

}
