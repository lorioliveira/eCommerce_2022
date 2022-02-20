package com.les.roupa.core.domain;

/**
 * Classe para representar o Cupom
 * @author Davi Rodrigues
 * @date 03/09/2021
 */
public class Cupom extends EntidadeDominio {
	private String nome;
	private String valor;
	private String tipo;
	private String utilizado;
	private String id_cliente;
	private String AlteraCupom;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getUtilizado() {
		return utilizado;
	}
	public void setUtilizado(String utilizado) {
		this.utilizado = utilizado;
	}
	public String getIdCliente() {
		return id_cliente;
	}
	public void setIdCliente(String idCliente) {
		this.id_cliente = idCliente;
	}
	public String getAlteraCupom() {
		return AlteraCupom;
	}
	public void setAlteraCupom(String AlteraCupom) {
		this.AlteraCupom = AlteraCupom;
	}
}
