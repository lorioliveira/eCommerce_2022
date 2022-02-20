package com.les.roupa.core.domain;

/**
 * Classe para representar o Cartão de Credito
 * @author Davi Rodrigues
 * @date 05/10/2021
 */
public class CartaoDeCredito extends EntidadeDominio {
	private String num_cartao;
	private String nome;
	private String cod_seguranca;
	private String flgPreferencial;
	private String id_cliente;
	private String id_bandeira;
	private String dt_validade;
	private String AlteraCartao;
	
	
	public String getNum_cartao() {
		return num_cartao;
	}
	public void setNum_cartao(String num_cartao) {
		this.num_cartao = num_cartao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getIdBandeira() {
		return id_bandeira;
	}
	public void setIdBandeira(String id_bandeira) {
		this.id_bandeira = id_bandeira;
	}
	public String getCod_seguranca() {
		return cod_seguranca;
	}
	public void setCod_seguranca(String cod_seguranca) {
		this.cod_seguranca = cod_seguranca;
	}
	public String getFlgPreferencial() {
		return flgPreferencial;
	}
	public void setFlgPreferencial(String flgPreferencial) {
		this.flgPreferencial = flgPreferencial;
	}
	public String getIdCliente() {
		return id_cliente;
	}
	public void setIdCliente(String idCliente) {
		this.id_cliente = idCliente;
	}
	public String getDt_validade() {
		return dt_validade;
	}
	public void setDt_validade(String dt_validade) {
		this.dt_validade = dt_validade;
	}
	public String getAlteraCartao() {
		return AlteraCartao;
	}
	public void setAlteraCartao(String AlteraCartao) {
		this.AlteraCartao = AlteraCartao;
	}
}
