package com.br.cultreviews.dto;

import com.br.cultreviews.model.Serie;
import com.br.cultreviews.model.User;

public class SerieDTO {
	private String titulo;
	private String diretor;
	private String elencoPrincipal;
	private String pais;
	private String ano;
	private String url;
	private int temporada;
	private String sinopse;
	
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDiretor() {
		return diretor;
	}
	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}
	public String getElencoPrincipal() {
		return elencoPrincipal;
	}
	public void setElencoPrincipal(String elencoPrincipal) {
		this.elencoPrincipal = elencoPrincipal;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getTemporada() {
		return temporada;
	}
	public void setTemporada(int temporada) {
		this.temporada = temporada;
	}
	public String getSinopse() {
		return sinopse;
	}
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public Serie toSerie(User usuario) {
		Serie serie = new Serie();
		
		serie.setUsuario(usuario);
		serie.setTitulo(this.titulo);
		serie.setElencoPrincipal(this.elencoPrincipal);
		serie.setDiretor(this.diretor);
		serie.setAno(this.ano);
		serie.setPais(this.pais);
		serie.setUrl(this.url);
		serie.setSinopse(this.sinopse);
		serie.setTemporada(this.temporada);
		
		return serie;
	}
	
}
