package com.les.roupa.core.dominio;

/**
 * Classe Entidade Dominio
 * @author Lorena Oliveira
 */
public class EntidadeDominio implements IEntidade {
	private String id;
	private String data_Cadastro;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getData_Cadastro() {
		return data_Cadastro;
	}
	public void setData_Cadastro(String data_Cadastro) {
		data_Cadastro = data_Cadastro;
	}
	
	
	
}
