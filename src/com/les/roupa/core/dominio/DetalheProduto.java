package com.les.roupa.core.dominio;

import java.util.List;

/**
 * Classe para representar o detalhe do produto 
 * @author Lorena Oliveira
 */

public class DetalheProduto extends EntidadeDominio {
	
	private Produto produto;
		
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
}
