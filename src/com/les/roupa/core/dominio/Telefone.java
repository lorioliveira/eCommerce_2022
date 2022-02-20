package com.les.roupa.core.dominio;

/**
 * Classe para representar o Cliente
 */
public class Telefone extends Cliente {
	private String telefone;
	private String tipo;
	
	
	private String alteraCliente;
	
	
	/* Telefone */
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	
	/* Altera Cliente */
	public String getAlteraCliente() {
		return alteraCliente;
	}
	public void setAlteraCliente(String alteraCliente) {
		this.alteraCliente = alteraCliente;
	}
	
	/* Tipo - Cliente/ Admin */
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
