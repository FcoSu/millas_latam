package com.latam.millas.backforfront_millas_equipoflama.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.latam.millas.backend_millas_equipoflama.service.MillasService;
import com.latam.millas.backend_millas_equipoflama.service.UsuarioService;
import com.latam.millas.dal_millas_equipoflama.dto.MillasDto;
import com.latam.millas.dal_millas_equipoflama.dto.UsuarioDto;
import com.latam.millas.dal_millas_equipoflama.dto.json;

@RestController
@RequestMapping("/api/millas_latam")
public class MillasController {
	public String UsuarioEmail = null;
	public String pnr;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private MillasService Millas;

	@RequestMapping(value = "/Millas/Post", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MillasDto> getData(@RequestBody json Json) {
		pnr = Json.getPnr();
		UsuarioEmail = Json.getUserEmail();
		UsuarioDto Usuario = null;
		MillasDto MillasDTO = null;

		if (ValidarFormatoEmail(Json.getUserEmail())) {
			Usuario = usuarioService.ValidarUsuario(Json.getUserEmail());
			if (Usuario.getCodigoUsuario() != 0) {
			
				
				MillasDTO = Millas.ListarMillas(pnr,UsuarioEmail);
				System.out.println(MillasDTO);

				return new ResponseEntity<>(MillasDTO, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(MillasDTO, HttpStatus.BAD_REQUEST);
			}
		} else

			return new ResponseEntity<>(MillasDTO, HttpStatus.BAD_REQUEST);

	}

	public boolean ValidarFormatoEmail(String Email) {
		Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
		Matcher matcher = pattern.matcher(Email);
		return matcher.matches();

	}

}
