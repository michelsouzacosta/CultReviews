package com.br.cultreviews.dto;

import java.time.LocalDate;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.br.cultreviews.model.User;


public class UsuarioPerfilDTO {
	
	private String nome;
	
	private String nomeUsuario;

	private String dataNascimento;
	
	private String cidade;
    
	private String estado;

	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public User toUsuario() {
		User usuario = new User();
		
		usuario.setNome(this.nome);
		usuario.setNomeUsuario(this.nomeUsuario);
		usuario.setEstado(this.estado);
		usuario.setDataNascimento(LocalDate.parse(this.dataNascimento));
		usuario.setCidade(this.cidade);


		return usuario;
		
	}
 
	
	
}
