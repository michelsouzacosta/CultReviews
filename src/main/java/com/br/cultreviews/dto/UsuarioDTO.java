package com.br.cultreviews.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.br.cultreviews.model.User;


public class UsuarioDTO {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String nomeUsuario;

	@NotBlank
	private String senha;
	

	@NotBlank
	private String dataNascimento;
	
	@NotBlank
	private String cidade;
    
	@NotBlank
	private String estado;

	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
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
		usuario.setSenha(new BCryptPasswordEncoder().encode(senha));

		return usuario;
		
	}
 
	
	
}
