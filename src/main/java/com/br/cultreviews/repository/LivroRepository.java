package com.br.cultreviews.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.cultreviews.model.Livro;
@Repository
public interface LivroRepository  extends JpaRepository<Livro, String>{

	@Query(value = "Select * from Livro l where l.nome_usuario = :nomeUsuario",nativeQuery=true)
	List<Livro>findByUser(@Param("nomeUsuario") String nomeUsuario);


}
