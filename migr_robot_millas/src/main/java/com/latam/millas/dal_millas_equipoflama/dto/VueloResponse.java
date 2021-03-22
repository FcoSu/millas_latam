package com.latam.millas.dal_millas_equipoflama.dto;

import lombok.Data;

@Data
public class VueloResponse {
	
	private String message;
	private Integer responseCode;
	private VueloDto vueloDto;
	
}
