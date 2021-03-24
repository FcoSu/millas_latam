package com.latam.millas.backforfront_millas_equipoflama.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.latam.millas.backend_millas_equipoflama.exceptions.ExceptionResponse;
import com.latam.millas.backend_millas_equipoflama.service.UsuarioService;
import com.latam.millas.backend_millas_equipoflama.service.VueloService;
import com.latam.millas.dal_millas_equipoflama.dto.UsuarioDto;
import com.latam.millas.dal_millas_equipoflama.dto.VueloDto;
import com.latam.millas.dal_millas_equipoflama.dto.json;

import ch.qos.logback.core.boolex.Matcher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/millas_latam")
public class Controller {
	public String Usuario = null;
	public String pnr;
	ExceptionResponse response = new ExceptionResponse();
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private VueloService vueloService;
	
	@RequestMapping (value ="/Millas/Post/Usuario/validar", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Object> getData(@RequestBody json Json) {
		
		
         UsuarioDto Usuario = null;
         List<VueloDto> Vuelos = null;
         Usuario = usuarioService.ValidarUsuario(Json.getUserEmail());
         
         if(Json.getPnr().equals("")) {
        	 log.error("PNR no debe estar vacio"); 
        	 response.setDateTime(LocalDateTime.now());
             response.setMensaje("PNR no debe estar vacio");
             ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
             return entity;	
         }
         
         if(Json.getUserEmail().equals("")) {
        	 log.error("Correo no debe estar vacio"); 
        	 response.setDateTime(LocalDateTime.now());
             response.setMensaje("Correo no debe estar vacio");
             ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
             return entity;	
         }
        
	  	if (validaEmail(Json.getUserEmail().toString())== false) {
        	log.error("Correo no es valido"); 
       	 	response.setDateTime(LocalDateTime.now());
            response.setMensaje("Correo no es valido");
            ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
            return entity;
        }
	  		  	
         if(Usuario.getCodigoUsuario() == 0) {
        	 log.error("Correo no existe en los datos de latam"); 
        	 response.setDateTime(LocalDateTime.now());
             response.setMensaje("Correo no existe en los datos de latam");
             ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
             return entity;
          }
         
         if(Json.getPnr().length()!=6) {
        	 log.error("PNR Solo debe tener 6 caracteres");   
        	 response.setDateTime(LocalDateTime.now());
             response.setMensaje("PNR Solo debe tener 6 caracteres");
             ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
             return entity;
         	}     
         
         if (Usuario.getCodigoUsuario() != 0) {
        	 Vuelos = vueloService.obtenerVuelos(Json.getPnr());
        	 return new ResponseEntity <>(Vuelos, HttpStatus.OK);
         }
         
         
         
		return new ResponseEntity <>(Vuelos, HttpStatus.BAD_REQUEST);		
	}	
	
	public boolean validaEmail(String email) { 
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                     + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	  	java.util.regex.Matcher mather = pattern.matcher(email);
	  	if (mather.find() == false) {
	  	return false;
	  	}else {
	  	return true;
	  	}
	}
}