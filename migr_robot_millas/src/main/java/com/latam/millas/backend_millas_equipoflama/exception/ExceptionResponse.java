package com.latam.millas.backend_millas_equipoflama.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ExceptionResponse {
	 private String mensaje;
     private LocalDateTime dateTime;
     private String Detalle;
}
