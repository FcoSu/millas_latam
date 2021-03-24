package com.latam.millas.backforfront_millas_equipoflama.controller;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.latam.millas.backend_millas_equipoflama.exception.ExceptionResponse;
import com.latam.millas.backend_millas_equipoflama.service.MillasService;
import com.latam.millas.backend_millas_equipoflama.service.UsuarioService;
import com.latam.millas.dal_millas_equipoflama.dto.MillasDto;
import com.latam.millas.dal_millas_equipoflama.dto.UsuarioDto;
import com.latam.millas.dal_millas_equipoflama.dto.json;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/millas_latam")
public class MillasController {
	public String UsuarioEmail = null;
	public String pnr;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private MillasService Millas;
	ExceptionResponse response = new ExceptionResponse();

	@RequestMapping(value = "/Millas/Post", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Object> getData(@RequestBody json Json) {
		pnr = Json.getPnr().trim();
		UsuarioEmail = Json.getUserEmail().trim();
		UsuarioDto Usuario = null;
		MillasDto MillasDTO = null;
		
		if(pnr.equals("") && UsuarioEmail.equals("")) {
            log.error("Debe completar los datos necesarios");
            response.setDateTime(LocalDateTime.now());
            response.setMensaje("Debe completar los datos necesarios");
            ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            return entity;
        }
		
		if (pnr.equals("")) {
			log.error("PNR no debe estar vacio");
			response.setDateTime(LocalDateTime.now());
			response.setMensaje("PNR no debe estar vacio");
			ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			return entity;
		}

		if (UsuarioEmail.equals("")) {
			log.error("Correo no debe estar vacio");
			response.setDateTime(LocalDateTime.now());
			response.setMensaje("Correo no debe estar vacio");
			ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			return entity;
		}

		if (ValidarFormatoEmail(UsuarioEmail.toString()) == false) {
			log.error("Correo no es valido");
			response.setDateTime(LocalDateTime.now());
			response.setMensaje("Correo no es valido");
			ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			return entity;
		}
		if(ValidarFormatoPnr(pnr.toString()) == false) {
			log.error("El formato del PNR no es valido");
			response.setDateTime(LocalDateTime.now());
			response.setMensaje("El formato del PNR no es valido");
			ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			return entity;		
			
		}		

		if (pnr.length() != 6) {
			log.error("PNR Solo debe tener 6 caracteres");
			response.setDateTime(LocalDateTime.now());
			response.setMensaje("PNR Solo debe tener 6 caracteres");
			ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			return entity;
		}

		Usuario = usuarioService.ValidarUsuario(UsuarioEmail);
		if (Usuario.getCodigoUsuario() == 0) {
			log.error("Correo no existe en los datos de latam");
			response.setDateTime(LocalDateTime.now());
			response.setMensaje("Correo no existe en los datos de latam");
			ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			return entity;
		}
		if (Usuario.getCodigoUsuario() != 0) {

			MillasDTO = Millas.ListarMillas(pnr, UsuarioEmail);
			System.out.println(MillasDTO);

			return new ResponseEntity<>(MillasDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(MillasDTO, HttpStatus.BAD_REQUEST);
		}
	}

	public boolean ValidarFormatoEmail(String Email) {
		Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
		Matcher matcher = pattern.matcher(Email);
		return matcher.matches();

	}
	
	public boolean ValidarFormatoPnr(String Pnr) {
		Pattern pattern = Pattern.compile("^[A-Z]*");
		Matcher matcher = pattern.matcher(Pnr);
		return matcher.matches();
	}

}
