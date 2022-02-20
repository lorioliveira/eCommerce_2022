package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Produto;

/**
 * Classe para validar o campo preco de venda do produto
 * 
 */
public class ValidarPrecoVenda implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Produto produto = (Produto)entidade;
		
		// se o "alteraProduto" for igual a 1, executa essa regra
		if(produto.getAlteraProduto().contentEquals("1")) {
			if(produto.getPrecoVenda() == null || produto.getPrecoVenda().equals("")) {
				return (" Insira um preco de venda para o produto. <br>");
			}
			else {
				return null;
			}
		}else {
			return null;
		}
	}
}
