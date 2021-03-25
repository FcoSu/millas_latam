package com.latam.millas.backend_millas_equipoflama.service;

import java.util.List;

import com.latam.millas.dal_millas_equipoflama.dto.VueloDto;

public interface VueloService {
	
	List<VueloDto> obtenerVuelosPorPnrOrdDESC(String pnr);
	List<VueloDto> ObtenerVuelosPorCodOrdASC(List<Integer> Codigos);
	VueloDto ValidarPnr(String pnr);

}
