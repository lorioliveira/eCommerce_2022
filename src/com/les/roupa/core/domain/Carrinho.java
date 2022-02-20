package com.les.roupa.core.domain;

/**
 * Classe para representar um Carrinho
 * @author Davi Rodrigues
 * @date 22/08/2021
 */
public class Carrinho extends EntidadeDominio {
	private ItemCarrinho itemCarrinho;
	
	public ItemCarrinho getItemCarrinho() {
        return itemCarrinho;
    }
    public void setItemCarrinho(ItemCarrinho itemCarrinho) {
        this.itemCarrinho = itemCarrinho;
    }
}
