package com.br.cultreviews.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Nota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String idProduto;
	
	private float valorNota;

	
	

	
	public Nota() {
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}

	public float getValorNota() {
		return valorNota;
	}

	public void setValorNota(float valorNota) {
		this.valorNota = valorNota;
	}
	
	public static float mediaNota(List<Nota> notas) {
		float total = 0; 
		float media = 0;
		
		for (Nota nota : notas) {
			total += nota.valorNota;
		}
		
		media = total/notas.size();
		 
		 return media;
	}
}
