package com.les.roupa.core.dominio;

/**
 * Classe para representar o Gráfico da Análise
 */
public class GraficoAnalise extends EntidadeDominio {
	private ItemPedido itemPedido;
	private String dtInicio;
	private String dtFim;
	
	public ItemPedido getItemPedido() {
		return itemPedido;
	}
	public void setItemPedido(ItemPedido itemPedido) {
		this.itemPedido = itemPedido;
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
