package com.latam.millas.dal_millas_equipoflama.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class VueloDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int codigoVuelo;
	private String pnr;
	private String departure;
	private Date departureDate;
	private String arrival;
	private Date arrivalDate;
	private String flightNumber;
}
