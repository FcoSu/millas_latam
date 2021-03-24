package com.latam.millas.backend_millas_equipoflama.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.latam.millas.dal_millas_equipoflama.dto.EstadoVueloDto;

@Service
public interface EstadoVueloService {
	
	List<EstadoVueloDto> ObtenerEstadosVuelo(String NumeroVuelo);
	List<Integer> ObtenerCodVuelosMedianteNumeroVuelo(String NumeroVuelo);
}
