package com.latam.millas.dal_millas_equipoflama.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name="vuelo")

@Data public class Vuelo {
	
	@Id
	@Column(name="cdg_vuelo", nullable=false)
	private int codigoVuelo;
	
	@Column(name="pnr", length=45, nullable=false)
	private String pnr;
	
	@Column(name="departure", length=45, nullable=false)
	private String departure;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="departure_date", nullable=false)
	private Date departureDate;
	
	@Column(name="arrival", length=45, nullable=false)
	private String arrival;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="arrival_date", nullable=false)
	private Date arrivalDate;
	
	@Column(name="flight_number", length=45, nullable=false)
	private String flightNumber;
	

}
