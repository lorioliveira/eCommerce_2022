package com.les.roupa.core.dominio;

/**
 * Classe para representar os Itens do Pedido que serão trocados
 */

public class PedidoTroca extends EntidadeDominio {
	private ItemPedido itemPedido;
	
	/* Qtde Produto */
	public ItemPedido getItemPedido() {
		return itemPedido;
	}
	public void setItemPedido(ItemPedido itemPedido) {
		this.itemPedido = itemPedido;
	}
	
}

