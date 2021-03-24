package com.latam.millas.backend_millas_equipoflama.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.latam.millas.backend_millas_equipoflama.service.EstadoVueloService;
import com.latam.millas.backend_millas_equipoflama.service.MillasService;
import com.latam.millas.backend_millas_equipoflama.service.VueloService;
import com.latam.millas.dal_millas_equipoflama.dto.EstadoVueloDto;
import com.latam.millas.dal_millas_equipoflama.dto.LegsDto;
import com.latam.millas.dal_millas_equipoflama.dto.MillasDto;
import com.latam.millas.dal_millas_equipoflama.dto.VueloDto;

@Service
public class MillasServiceImpl implements MillasService {

	@Autowired
	private VueloService vueloService;

	@Autowired
	private EstadoVueloService EstadoService;

	@Override
	public MillasDto ListarMillas(String PNR, String Email) {
		int Rewards = 0;
		List<VueloDto> Vuelos = null;
		
		MillasDto millas = new MillasDto();
		
		List<Integer> VuelosPostVerificacion = null;

		/* obtencion de vuelos(sin filtrar) mediante pnr*/
		
		Vuelos = vueloService.obtenerVuelosPorPnrOrdDESC(PNR);
		System.out.println("Vuelos sin filtrar y en orden descendente" + Vuelos);
		
		/* verificacion y llenado de array con codigo de vuelos validos(Flight_Numbre,Flight_Status)*/
		if (!Vuelos.isEmpty()) {
			VuelosPostVerificacion = VerificarEstado1UltimoVuelo(Vuelos);
			System.out.println("VuelosPostVerificacion :  " + VuelosPostVerificacion);
		}
		Rewards = 50 * VuelosPostVerificacion.size();
		Vuelos = vueloService.ObtenerVuelosPorCodOrdASC(VuelosPostVerificacion);
		//System.out.println("Vuelos:   " + Vuelos);
		millas = armarRespuesta(Vuelos,Rewards,PNR,Email);
		System.out.print("Objeto: "+ millas);

		return millas;
	}

	public List<Integer> VerificarEstado1UltimoVuelo(List<VueloDto> VuelosOrdenadosDesc) {
		List<Integer> VuelosVerificados = new ArrayList<>();
		List<EstadoVueloDto> EstadosVuelos = null;
		
		if (!VuelosOrdenadosDesc.isEmpty()) {
			do {
				
				// obtiene lista de vuelos mediante codigo de vuelo,desde Array ya filtrado por
				// PNR
				EstadosVuelos = EstadoService.ObtenerEstadosVuelo(VuelosOrdenadosDesc.get(0).getFlightNumber());
				// quita el item 0 para seguir con el siguiente en lista
				VuelosOrdenadosDesc.remove(0);
			} while (EstadosVuelos == null || VuelosOrdenadosDesc.isEmpty());

			for (int i = 0; i < EstadosVuelos.size(); i++) {
				VuelosVerificados.add(EstadosVuelos.get(i).getCodigoVuelo());
			}
		}

		return VuelosVerificados;
	}

	public MillasDto armarRespuesta(List<VueloDto> vuelos, int rewards, String PNR, String UserEmail) {
		MillasDto Respuesta = new MillasDto();
		Respuesta.setPNR(PNR);
		Respuesta.setUserEmail(UserEmail);
		Respuesta.setRoute(CalcularRuta(vuelos));
		Respuesta.setRewards(rewards);
		Respuesta.setFlightDate(vuelos.get(0).getDepartureDate());
		Respuesta.setLegs(CalcularLegs(vuelos));
		return Respuesta;
	}
	public String CalcularRuta(List<VueloDto> vuelos) {
		String Ruta = null;
		Ruta= vuelos.get(0).getDeparture();
		for(VueloDto Vuelos: vuelos) {
			Ruta= Ruta + "-" + Vuelos.getArrival();
		}
		return Ruta;
	}
	public List<LegsDto> CalcularLegs(List<VueloDto> VuelosDTO){
		List<LegsDto> Legs = new ArrayList<>();
		for(VueloDto Vuelos: VuelosDTO) {
			LegsDto Escalas = new LegsDto();
			
			Escalas.setDeparture(Vuelos.getDeparture());
			Escalas.setArrival(Vuelos.getArrival());
			Escalas.setNumeroVuelo(Vuelos.getFlightNumber());
			Legs.add(Escalas);
			
		}
		return Legs;
	}

}
