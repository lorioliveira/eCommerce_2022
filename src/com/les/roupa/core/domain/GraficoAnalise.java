package com.les.roupa.core.domain;

/**
 * Classe para representar o Gráfico da Análise
 * @author Davi Rodrigues
 * @date 11/11/2021
 */
public class GraficoAnalise extends EntidadeDominio {
	private Produto produto;
	private String dtInicio;
	private String dtFim;
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public String getDtInicio() {
		return dtInicio;
	}
	public void setDtInicio(String dtInicio) {
		this.dtInicio = dtInicio;
	}
	public String getDtFim() {
		return dtFim;
	}
	public void setDtFim(String dtFim) {
		this.dtFim = dtFim;
	}
}
