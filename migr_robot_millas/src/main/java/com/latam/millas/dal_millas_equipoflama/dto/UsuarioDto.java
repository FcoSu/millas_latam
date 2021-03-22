package com.latam.millas.dal_millas_equipoflama.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UsuarioDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int codigoUsuario;
	private String emailUsuario;
	private String nombreUsuario;
	private String appUsuario;
}
