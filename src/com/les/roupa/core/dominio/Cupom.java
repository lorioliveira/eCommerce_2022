package com.les.roupa.core.dominio;

import java.util.List;

/**
 * Classe para representar o Cupom
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
	
	/* NOME */
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/* VALOR */
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	/* TIPO DO CUPOM */
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	/* CUPOM - UTILIZADO */
	public String getUtilizado() {
		return utilizado;
	}
	public void setUtilizado(String utilizado) {
		this.utilizado = utilizado;
	}
	
	/* ID DO CLIENTE */
	public String getIdCliente() {
		return id_cliente;
	}
	public void setIdCliente(String idCliente) {
		this.id_cliente = idCliente;
	}
	
	/* ALTERAR CUPOM */
	public String getAlteraCupom() {
		return AlteraCupom;
	}
	public void setAlteraCupom(String AlteraCupom) {
		this.AlteraCupom = AlteraCupom;
	}
	
	/* LISTA DE CUPONS */
	public List<Cupom> getTodosCupons() {
        return todosCupons;
    }
    public void setTodosCupons(List<Cupom> todosCupons) {
        this.todosCupons = todosCupons;
    }
    
    /* CUPOM PESQUISADO */
	public Cupom getCupomPesquisado() {
		return cupomPesquisado;
	}
	public void setCupomPesquisado(Cupom cupomPesquisado) {
		this.cupomPesquisado = cupomPesquisado;
	}
	
	/* NOME DO CLIENTE NO CUPOM */
	public String getNomeClienteNoCupom() {
		return nomeClienteNoCupom;
	}
	public void setNomeClienteNoCupom(String nomeClienteNoCupom) {
		this.nomeClienteNoCupom = nomeClienteNoCupom;
	}
	
}