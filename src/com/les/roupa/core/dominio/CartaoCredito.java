package com.les.roupa.core.dominio;

/**
 * Classe para representar o Cartão de Credito
 */
public class CartaoCredito extends Cliente {
	private String nCartao;
	private String bandeira;
	private String validade;
	private String cvv;
	private String idCliente;
	private String preferencial;
	
	private String alteraPreferencial;
	
	/* CARTÃO */
	public String getNCartao() {
		return nCartao;
	}
	public void setNCartao(String nCartao) {
		this.nCartao = nCartao;
	}
	
	/* BANDEIRA */
	public String getBandeira() {
		return bandeira;
	}
	public void setBandeira(String bandeira) {
		this.bandeira= bandeira;
	}
	
	/* VALIDADE */
	public String getValidade() {
		return validade;
	}
	public void setValidade(String validade) {
		this.validade = validade;
	}
	
	/* CODIGO DE SEGURANÇA */
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
	/*Preferencial */
	public String getPreferencial() {
		return preferencial;
	}
	public void setPreferencial(String preferencial) {
		this.preferencial = preferencial;
	}
	
	/* Altera Preferencial */
	public String getAlteraPref() {
		return alteraPreferencial;
	}
	public void setAlteraPref(String alteraPreferencial) {
		this.alteraPreferencial = alteraPreferencial;
	}
	
	/* ID CLIENTE */
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
}
