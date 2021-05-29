package com.br.cultreviews.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.cultreviews.model.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, String> {
	@Query(value = "Select * from Filme f where f.nome_usuario = :nomeUsuario",nativeQuery=true)
	List<Filme>findByUser(@Param("nomeUsuario") String nomeUsuario);

}
