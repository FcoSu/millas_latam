package com.latam.millas.backend_millas_equipoflama.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.latam.millas.backend_millas_equipoflama.service.EstadoVueloService;
import com.latam.millas.dal_millas_equipoflama.dto.EstadoVueloDto;
import com.latam.millas.dal_millas_equipoflama.entity.EstadoVuelo;
import com.latam.millas.dal_millas_equipoflama.repository.EstadoVueloRepository;



@Service
public class EstadoVueloServiceImpl implements EstadoVueloService {
	
	@Autowired
	EstadoVueloRepository EstadoVueloRepositorio;

	@Override
	public List<EstadoVueloDto> ObtenerEstadosVuelo(String NumeroVuelo) {
		List<EstadoVueloDto> EstadosDto = new ArrayList<>();

		List<EstadoVuelo> EstadoVueloEntidad = EstadoVueloRepositorio.obtenerEstadoVueloPorNumeroVuelo(NumeroVuelo);
		
		EstadoMapper(EstadosDto,EstadoVueloEntidad);
		return EstadosDto;
	}
	@Override
	public List<Integer> ObtenerCodVuelosMedianteNumeroVuelo(String NumeroVuelo) {
		
		return null;
	}
	
	public void EstadoMapper(List<EstadoVueloDto> EstadoDto, List<EstadoVuelo> EstadosBD) {
		for(EstadoVuelo Estados: EstadosBD) {
			EstadoVueloDto EstadosVueloDTO = new EstadoVueloDto();
			
			EstadosVueloDTO.setCodigoStatus(Estados.getCodigoStatus());
			EstadosVueloDTO.setCodigoVuelo(Estados.getCodigoVuelo());
			EstadosVueloDTO.setFlightNumber(Estados.getFlightNumber());
			EstadosVueloDTO.setFlightStatus(Estados.getFlightStatus());
			
			EstadoDto.add(EstadosVueloDTO);
			
		}
		
		
	}

	
	

}
