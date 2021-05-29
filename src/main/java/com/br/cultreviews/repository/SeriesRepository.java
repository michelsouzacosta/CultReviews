package com.br.cultreviews.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.cultreviews.model.Serie;

@Repository
public interface SeriesRepository extends JpaRepository<Serie, String>{
	@Query(value = "Select * from Serie f where f.nome_usuario = :nomeUsuario",nativeQuery=true)
	List<Serie>findByUser(@Param("nomeUsuario") String nomeUsuario);

}
