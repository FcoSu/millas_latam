package com.latam.millas.dal_millas_equipoflama.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="vuelo")
public class Vuelo {
	
	@Id
	@Column(name="cdg_vuelo", nullable=false)
	int codigoVuelo;
	
	@Column(name="pnr", length=45, nullable=false)
	String pnr;
	
	@Column(name="departure", length=45, nullable=false)
	String departure;
	
	@Temporal(TemporalType.DATE)
	@Column(name="departure_date", nullable=false)
	Date departureDate;
	
	@Column(name="arrival", length=45, nullable=false)
	String arrival;
	
	@Temporal(TemporalType.DATE)
	@Column(name="arrival_date", nullable=false)
	Date arrivalDate;
	
	@Column(name="flight_number", length=45, nullable=false)
	String flightNumber;
	

}
