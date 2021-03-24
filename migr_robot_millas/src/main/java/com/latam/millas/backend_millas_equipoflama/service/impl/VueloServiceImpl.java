package com.latam.millas.backend_millas_equipoflama.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.latam.millas.backend_millas_equipoflama.service.VueloService;
import com.latam.millas.dal_millas_equipoflama.dto.VueloDto;
import com.latam.millas.dal_millas_equipoflama.entity.Vuelo;
import com.latam.millas.dal_millas_equipoflama.repository.VueloRepository;

@Service
public class VueloServiceImpl implements VueloService {
	
	@Autowired
	VueloRepository VueloRepositorio;

	@Override
	public List<VueloDto> obtenerVuelosPorPnrOrdDESC(String pnr) {
		List<VueloDto> VuelosDTO = new ArrayList<>();
		List<Vuelo> VueloEntidad = VueloRepositorio.obtenerVueloPorPnr(pnr);
		
		VueloMapper(VuelosDTO, VueloEntidad);
		return VuelosDTO;
	}
	
	@Override
	public List<VueloDto> ObtenerVuelosPorCodOrdASC(List<Integer> Codigos) {
		List<VueloDto> VuelosDTO = new ArrayList<>();
		List<Vuelo> VueloEntidad = new ArrayList<>();
		for(int i=0;i<Codigos.size();i++) {
			
			VueloEntidad.add(VueloRepositorio.obtenerVueloPorCodigo(Codigos.get(i)));
		}
		
		
		VueloMapper(VuelosDTO, VueloEntidad);
		return VuelosDTO;
	}
	
	
	private void VueloMapper(List<VueloDto> VuelosDto, List<Vuelo> vueloBD) {
		for (Vuelo Vuelos : vueloBD) {
			VueloDto VueloDTO = new VueloDto();
			
			VueloDTO.setPnr(Vuelos.getPnr());
			VueloDTO.setCodigoVuelo(Vuelos.getCodigoVuelo());
			VueloDTO.setDeparture(Vuelos.getDeparture());
			VueloDTO.setArrival(Vuelos.getArrival());
			VueloDTO.setDepartureDate(Vuelos.getDepartureDate());
			VueloDTO.setArrivalDate(Vuelos.getArrivalDate());
			VueloDTO.setFlightNumber(Vuelos.getFlightNumber());
			

			VuelosDto.add(VueloDTO);
		}
	}

	

	
	

}
