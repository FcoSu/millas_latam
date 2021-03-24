package com.latam.millas.dal_millas_equipoflama.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.latam.millas.dal_millas_equipoflama.entity.EstadoVuelo;

@Repository
public interface EstadoVueloRepository extends JpaRepository<EstadoVuelo, Integer>{

	
	@Query(value = "SELECT * FROM estado_vuelo WHERE flight_number=:NumeroVuelo && flight_status= 1", nativeQuery = true)
    public List<EstadoVuelo> obtenerEstadoVueloPorNumeroVuelo(@Param("NumeroVuelo") String NumeroVuelo);
	
	@Query(value = "SELECT cdg_vuelo FROM estado_vuelo WHERE flight_number=:NumeroVuelo && flight_status= 1", nativeQuery = true)
    public List<Integer> obtenerCodVueloMedianteStatus(@Param("NumeroVuelo") String NumeroVuelo);


}