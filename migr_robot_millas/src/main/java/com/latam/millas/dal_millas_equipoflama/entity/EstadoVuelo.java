package com.latam.millas.dal_millas_equipoflama.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name="estado_vuelo")
public class EstadoVuelo {
	
	
	@Column(name="vuelo_cdg_vuelo", nullable=false)
	private int vueloCodigoVuelo;
	
	@Id
	@Column(name="cdg_status", length=45, nullable=false)
	private String codigoStatus;
	
	@Column(name="flight_number", length=45, nullable=false)
	private String flightNumber;
	
	@Column(name="flight_status", nullable=false)
	private int flightStatus;

	
}
