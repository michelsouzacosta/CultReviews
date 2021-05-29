package com.br.cultreviews.dto;

import com.br.cultreviews.model.Nota;

public class NotaDTO {
	
	private String titulo;
	
	private float valorNota;


	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public float getValorNota() {
		return valorNota;
	}

	public void setValorNota(float valorNota) {
		this.valorNota = valorNota;
	}

	public Nota toNota() {
		Nota nota = new Nota();
		
		nota.setIdProduto(this.titulo);
		nota.setValorNota(this.valorNota);
		
		return nota;
	}
	
}
