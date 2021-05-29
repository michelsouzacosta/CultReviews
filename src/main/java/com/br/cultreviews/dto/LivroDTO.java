package com.br.cultreviews.dto;

import com.br.cultreviews.model.Livro;
import com.br.cultreviews.model.User;

public class LivroDTO {

	private String titulo;
	private String autor;
	private String editora;
	private String pais;
	private String ano;
	private String url;
	private String sinopse;

	public String getSinopse() {
		return sinopse;
	}
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	
	
	public Livro toLivro(User usuario) {
		Livro livro = new Livro();
		livro.setUsuario(usuario);
		livro.setTitulo(this.titulo);
		livro.setAutor(this.autor);
		livro.setEditora(this.editora);
		livro.setAno(this.ano);
		livro.setPais(this.pais);
		livro.setUrl(this.url);
		livro.setUrl(this.url);
		livro.setSinopse(this.sinopse);
		
		return livro;
	}
}
