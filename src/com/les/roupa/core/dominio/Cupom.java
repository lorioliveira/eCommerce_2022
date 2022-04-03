package com.les.roupa.core.dominio;

import java.util.List;

/**
 * Classe para representar o Cupom do Cliente
 */
public class Cupom extends EntidadeDominio {
	private String nome;
	private String valor;
	private String tipo;
	private String utilizado;
	private String id_cliente;
	private String AlteraCupom;
	private List<Cupom> todosCupons;
	private Cupom cupomPesquisado;
	private String nomeClienteNoCupom;
	
	
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
	
	public List<Cupom> getTodosCupons() {
        return todosCupons;
    }
    public void setTodosCupons(List<Cupom> todosCupons) {
        this.todosCupons = todosCupons;
    }
    
	public Cupom getCupomPesquisado() {
		return cupomPesquisado;
	}
	public void setCupomPesquisado(Cupom cupomPesquisado) {
		this.cupomPesquisado = cupomPesquisado;
	}
	
	public String getNomeClienteNoCupom() {
		return nomeClienteNoCupom;
	}
	public void setNomeClienteNoCupom(String nomeClienteNoCupom) {
		this.nomeClienteNoCupom = nomeClienteNoCupom;
	}
	
}