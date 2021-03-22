package com.latam.millas.dal_millas_equipoflama.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class EstadoVueloDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private int vueloCodigoVuelo;
	private String codigoStatus;
	private String flightNumber;
	private int flightStatus;
	
}
