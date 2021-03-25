package com.latam.millas.backend_millas_equipoflama.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.latam.millas.backend_millas_equipoflama.service.VueloService;
import com.latam.millas.backforfront_millas_equipoflama.controller.MillasController;
import com.latam.millas.dal_millas_equipoflama.dto.VueloDto;
import com.latam.millas.dal_millas_equipoflama.entity.Vuelo;
import com.latam.millas.dal_millas_equipoflama.repository.VueloRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
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
	@Override
    public VueloDto ValidarPnr(String pnr) {

        Vuelo vueloEntidad = VueloRepositorio.obtenerVuelo(pnr);

        VueloDto vueloDto = null;


        try {
            vueloDto = new VueloDto();

            vueloDto.setPnr(vueloEntidad.getPnr());
            vueloDto.setCodigoVuelo(vueloEntidad.getCodigoVuelo());
            vueloDto.setDeparture(vueloEntidad.getDeparture());
            vueloDto.setArrival(vueloEntidad.getArrival());
            vueloDto.setDepartureDate(vueloEntidad.getDepartureDate());
            vueloDto.setArrivalDate(vueloEntidad.getArrivalDate());
            vueloDto.setFlightNumber(vueloEntidad.getFlightNumber());
            }catch (RuntimeException e) {
                log.error(e.getMessage());
            }

        return vueloDto ;

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
