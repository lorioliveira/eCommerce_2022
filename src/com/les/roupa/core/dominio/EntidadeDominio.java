package com.les.roupa.core.dominio;

/**
 * Classe entidade dominio
 */
public class EntidadeDominio implements IEntidade {
	private String id;
	private String dt_cadastro;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDt_cadastro() {
		return dt_cadastro;
	}
	public void setDt_cadastro(String dt_cadastro) {
		this.dt_cadastro = dt_cadastro;
	}
}
