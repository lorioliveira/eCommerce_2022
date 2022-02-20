package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Produto;

/**
 * Classe para validar o campo Quantidade do Produto
 * 
 */

public class ValidarQtdeProduto implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Produto produto = (Produto) entidade;
		
		// se o "alteraProduto" for igual a 1, executa essa regra
		if(produto.getAlteraProduto().contentEquals("1")) {
			if(produto.getQtdeEstoque() == null || produto.getQtdeEstoque().equals("")) {
				return (" Insira uma quantidade no produto.<br> ");
			}
			else {
				return null;
			}
		}else {
			return null;
		}
	}

}