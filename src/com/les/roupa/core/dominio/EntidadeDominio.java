package com.les.roupa.core.dominio;

/**
 * Classe Entidade Dominio
 * @author Lorena Oliveira
 */
public class EntidadeDominio implements IEntidade {
	private String id;
	private String data_cadastro;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getData_cadastro() {
		return data_cadastro;
	}
	public void setData_cadastro(String data_cadastro) {
		this.data_cadastro = data_cadastro;
	}
	
}
