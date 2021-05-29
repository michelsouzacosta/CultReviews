package com.br.cultreviews.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.cultreviews.model.Nota;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {

	List<Nota>findByIdProduto(String idProduto);
}
