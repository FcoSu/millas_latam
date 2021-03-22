package com.latam.millas.dal_millas_equipoflama.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.latam.millas.dal_millas_equipoflama.entity.EstadoVuelo;

@Repository
public interface EstadoVueloRepository {

	@Query(value = "SELECT vuelo_cdg_vuelo,cdg_status,flight_number,flight_status FROM vuelo WHERE vuelo_cdg_vuelo=:codVuelo && =:codStatus", nativeQuery = true)
	public EstadoVuelo obtenerEstadoVueloPorCodVueloYCodStatus(@Param("codVuelo") int codVuelo, @Param("codStatus") String codStatus);
}
