package com.latam.millas.backend_millas_equipoflama.service;


import org.springframework.stereotype.Service;

import com.latam.millas.dal_millas_equipoflama.dto.MillasDto;

@Service
public interface MillasService {
	
	MillasDto ListarMillas(String PNR,String Email);
	
}
