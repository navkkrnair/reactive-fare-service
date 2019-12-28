package com.ams.fare.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ams.fare.entity.Fare;

public interface FareRepository extends JpaRepository<Fare, String>
{
	Fare getFareByFlightNumberAndFlightDate(String flightNumber, LocalDate flightDate);
}
