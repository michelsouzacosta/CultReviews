package com.br.cultreviews.dto;


import com.br.cultreviews.model.Filme;
import com.br.cultreviews.model.User;

public class FilmeDTO {
	private String titulo;
	private String diretor;
	private String elencoPrincipal;
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


	
	public Filme toFilme(User usuario) {
		Filme filmeConvert = new Filme();
		
		filmeConvert.setUsuario(usuario);
		filmeConvert.setTitulo(this.titulo);
		filmeConvert.setElencoPrincipal(this.elencoPrincipal);
		filmeConvert.setDiretor(this.diretor);
		filmeConvert.setAno(this.ano);
		filmeConvert.setPais(this.pais);
		filmeConvert.setUrl(this.url);
		filmeConvert.setSinopse(this.sinopse);
		
		return filmeConvert;
	}
	
}
