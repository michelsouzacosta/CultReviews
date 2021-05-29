package com.br.cultreviews.dto;

import com.br.cultreviews.model.Comentario;

public class ComentarioDTO {

	private String idUsuario;
	private String idProduto;
	private String comentario;
	
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	
	public Comentario toComentario(String idUsuario,String idProduto) {
		Comentario comentario = new Comentario();
		
		comentario.setComentario(this.comentario);
		comentario.setIdUsuario(idUsuario);
		comentario.setIdProduto(idProduto);
		
		return comentario;
	}
	
}
