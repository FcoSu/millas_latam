package com.latam.millas.dal_millas_equipoflama.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.latam.millas.dal_millas_equipoflama.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query (value = "SELECT Cdg_usuario,email_usuario,nom_usuario,app_usuario FROM usuario WHERE email_usuario=:userEmail", nativeQuery = true)
	public Usuario obtenerUsuarioPorEmail(@Param("userEmail") String userEmail);
	
}