package com.latam.millas.backend_millas_equipoflama.service;

import java.util.List;

import com.latam.millas.dal_millas_equipoflama.dto.EstadoVueloDto;

public interface MillasService {

	List<EstadoVueloDto> ListarEstadosVuelos(String PNR);
}
