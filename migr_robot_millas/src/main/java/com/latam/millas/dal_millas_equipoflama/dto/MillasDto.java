package com.latam.millas.dal_millas_equipoflama.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class MillasDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String PNR;
	private String UserEmail;
	private String Route;
	private String FlightDate;
	private int Rewards;
	private List<LegsDto> Legs;

}
