package com.les.roupa.core.domain;

/**
 * Classe para representar os Itens do Pedido
 * @author Davi Rodrigues
 * @date 09/09/2021
 */
public class ItemPedido extends EntidadeDominio {
	private String id_pedido;
	private Produto produto;
	private String trocado;
	
	public String getIdPedido() {
		return id_pedido;
	}
	public void setIdPedido(String id_pedido) {
		this.id_pedido = id_pedido;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public String getTrocado() {
		return trocado;
	}
	public void setTrocado(String trocado) {
		this.trocado = trocado;
	}
}
