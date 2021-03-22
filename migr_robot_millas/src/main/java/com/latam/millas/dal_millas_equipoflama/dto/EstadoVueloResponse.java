package com.latam.millas.dal_millas_equipoflama.dto;

import lombok.Data;

@Data
public class EstadoVueloResponse {

	private String message;
	private Integer responseCode;
	private EstadoVueloDto estadoVueloDto;
}
