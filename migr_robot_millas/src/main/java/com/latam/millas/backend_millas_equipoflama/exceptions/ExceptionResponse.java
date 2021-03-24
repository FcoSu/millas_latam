package com.latam.millas.backend_millas_equipoflama.exceptions;

import java.time.LocalDateTime;

import com.latam.millas.dal_millas_equipoflama.dto.EstadoVueloDto;

import lombok.Data;

@Data
public class ExceptionResponse {
	 private String mensaje;
	 private LocalDateTime dateTime;
}
