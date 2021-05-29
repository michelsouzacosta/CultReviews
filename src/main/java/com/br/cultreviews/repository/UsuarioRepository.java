package com.br.cultreviews.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.br.cultreviews.model.User;

public interface UsuarioRepository extends JpaRepository<User, String> {

	User findByNomeUsuario(String nomeUsuario);
}
