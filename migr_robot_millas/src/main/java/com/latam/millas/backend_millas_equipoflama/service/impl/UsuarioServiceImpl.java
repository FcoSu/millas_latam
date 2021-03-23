package com.latam.millas.backend_millas_equipoflama.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.latam.millas.backend_millas_equipoflama.service.UsuarioService;
import com.latam.millas.dal_millas_equipoflama.dto.UsuarioDto;
import com.latam.millas.dal_millas_equipoflama.repository.UsuarioRepository;
import com.latam.millas.dal_millas_equipoflama.entity.Usuario;

import lombok.extern.slf4j.Slf4j;




@Slf4j
@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	UsuarioRepository Usuariorepositorio;
	
	
	@Override
	public UsuarioDto ValidarUsuario(String UsuarioEmail) {
		Usuario usuarioEntidad = Usuariorepositorio.obtenerUsuarioPorEmail(UsuarioEmail);
		
		UsuarioDto usuarioDto = null;
		try {
		usuarioDto = new UsuarioDto();
		
		usuarioDto.setNombreUsuario(usuarioEntidad.getNombreUsuario());
		usuarioDto.setEmailUsuario(usuarioEntidad.getEmailUsuario());
		usuarioDto.setCodigoUsuario(usuarioEntidad.getCodigoUsuario());
		usuarioDto.setAppUsuario(usuarioEntidad.getAppUsuario());
		}catch (RuntimeException e) {
			log.error(e.getMessage());
		}
		
		return usuarioDto;
	}

	
	
}
