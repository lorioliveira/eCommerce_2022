package com.les.roupa.core.domain;

/**
 * Classe para representar um Item do Carrinho
 * @author Davi Rodrigues
 * @date 20/08/2021
 */
public class ItemCarrinho extends EntidadeDominio {
	private Produto produto;
	
	public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
