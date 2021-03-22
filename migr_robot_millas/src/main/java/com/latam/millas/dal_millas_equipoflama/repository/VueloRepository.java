package com.latam.millas.dal_millas_equipoflama.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.latam.millas.dal_millas_equipoflama.entity.Vuelo;

@Repository
public interface VueloRepository {
	
	@Query(value = "SELECT cdg_vuelo,pnr,departure,departure_date, arrival_date, flight_number FROM vuelo WHERE pnr=:vueloPnr", nativeQuery = true)
	public Vuelo obtenerVueloPorPnr(@Param("vueloPnr") String vueloPnr);

}
