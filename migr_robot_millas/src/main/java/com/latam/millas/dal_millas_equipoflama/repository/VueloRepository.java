package com.latam.millas.dal_millas_equipoflama.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.latam.millas.dal_millas_equipoflama.entity.Vuelo;

@Repository
public interface VueloRepository extends JpaRepository<Vuelo, Integer> {
	
	@Query(value = "SELECT cdg_vuelo,pnr,departure,arrival,departure_date, arrival_date, flight_number FROM vuelo WHERE pnr=:vueloPnr ORDER BY cdg_vuelo DESC", nativeQuery = true)
	public List<Vuelo> obtenerVueloPorPnr(@Param("vueloPnr") String vueloPnr);
	
	@Query(value = "SELECT cdg_vuelo,pnr,departure,arrival,departure_date, arrival_date, flight_number FROM vuelo WHERE cdg_vuelo=:Codigo", nativeQuery = true)
	public Vuelo obtenerVueloPorCodigo(@Param("Codigo") int Codigo);
	
	@Query(value = "SELECT cdg_vuelo,pnr,departure,arrival,departure_date, arrival_date, flight_number FROM vuelo WHERE pnr=:vueloPnr LIMIT 1 ", nativeQuery = true)
    public Vuelo obtenerVuelo(@Param("vueloPnr") String vueloPnr);
	
	

}