package com.les.roupa.core.dominio;

/**
 * Classe para representar os Itens do Pedido
 */
public class ItemPedido extends Produto{
	private String qtde_produto;
	private String id_pedido;
	private Produto produto;
	
	
	
	/* Qtde Produto */
	public String getQtdeProduto() {
		return qtde_produto;
	}
	public void setQtdeProduto(String qtde_produto) {
		this.qtde_produto = qtde_produto;
	}
	
	/* ID do pedido */
	public String getIdPedido() {
		return id_pedido;
	}
	public void setIdPedido(String id_pedido) {
		this.id_pedido = id_pedido;
	}
	
	/* Produto */
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
}
