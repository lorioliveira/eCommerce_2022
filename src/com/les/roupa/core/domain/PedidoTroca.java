package com.les.roupa.core.domain;

/**
 * Classe para representar a Troca do Pedido ou dos Itens do Pedido
 * @author Davi Rodrigues
 * @date 07/09/2021
 */
public class PedidoTroca extends EntidadeDominio {
	private ItemPedido itemPedido;
	
	public ItemPedido getItemPedido() {
		return itemPedido;
	}
	public void setItemPedido(ItemPedido itemPedido) {
		this.itemPedido = itemPedido;
	}
}
