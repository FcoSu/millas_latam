package com.latam.millas.dal_millas_equipoflama.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="estado_vuelo")
public class EstadoVuelo {
	
	@Id
	@Column(name="vuelo_cdg_vuelo", nullable=false)
	int vueloCodigoVuelo;
	
	@Id
	@Column(name="cdg_status", length=45, nullable=false)
	String codigoStatus;
	
	@Column(name="flight_number", length=45, nullable=false)
	String flightNumber;
	
	@Column(name="flight_status", nullable=false)
	int flightStatus;

	
}
