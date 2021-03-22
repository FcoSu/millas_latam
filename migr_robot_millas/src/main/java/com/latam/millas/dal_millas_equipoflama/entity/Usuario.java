package com.latam.millas.dal_millas_equipoflama.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
	@Column(name="Cdg_usuario", nullable=false)
	int codigoUsuario;
	
	@Column(name="email_usuario", length=45, nullable=false)
	String emailUsuario;
	
	@Column(name="nom_usuario", length=45, nullable=false)
	String nombreUsuario;
	
	@Column(name="app_usuario", length=45, nullable=false)
	String appUsuario;

}
