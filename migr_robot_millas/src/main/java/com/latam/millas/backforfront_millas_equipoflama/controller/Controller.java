package com.latam.millas.backforfront_millas_equipoflama.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.latam.millas.backend_millas_equipoflama.service.UsuarioService;
import com.latam.millas.backend_millas_equipoflama.service.VueloService;
import com.latam.millas.dal_millas_equipoflama.dto.UsuarioDto;
import com.latam.millas.dal_millas_equipoflama.dto.VueloDto;
import com.latam.millas.dal_millas_equipoflama.dto.json;


@RestController
@RequestMapping("/api/millas_latam")
public class Controller {
	public String Usuario = null;
	public String pnr;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private VueloService vueloService;
	
	@RequestMapping (value ="/Millas/Post/Usuario/validar", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<List<VueloDto>> getData(@RequestBody json Json) {
		
		System.out.println("Email: " + Json.getUserEmail());
         UsuarioDto Usuario = null;
         List<VueloDto> Vuelos = null;
         Usuario = usuarioService.ValidarUsuario(Json.getUserEmail());
         if (Usuario.getCodigoUsuario() != 0) {
        	 System.out.println("Llego a validar");
        	 Vuelos = vueloService.obtenerVuelos(Json.getPnr());
        	 return new ResponseEntity <>(Vuelos, HttpStatus.OK);
         }
         else
		
		return new ResponseEntity <>(Vuelos, HttpStatus.BAD_REQUEST);
		
	}
	
	//cosas por ahcer bla bla
}
