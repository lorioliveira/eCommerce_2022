package com.les.roupa.core.dominio;

/**
 * Classe para representar o Estoque
 *
 */
public class Estoque extends EntidadeDominio {
	private String id_produto;
	private String tipo;
	private String quantidade;
	private String valorCompra;
	private String dt_entrada;
	private String quantidadeTotal;

	
	public String getIdProduto() {
		return id_produto;
	}
	public void setIdProduto(String id_produto) {
		this.id_produto = id_produto;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	public String getValorCompra() {
		return valorCompra;
	}
	public void setValorCompra(String valorCompra) {
		this.valorCompra = valorCompra;
	}
	public String getDtEntrada() {
		return dt_entrada;
	}
	public void setDtEntrada(String dt_entrada) {
		this.dt_entrada = dt_entrada;
	}
	public String getQuantidadeTotal() {
		return quantidadeTotal;
	}
	public void setQuantidadeTotal(String quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}
	
}