package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Produto;

/**
 * Classe para validar a categoria do Produto
 * 
 */

public class ValidarCategoria implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Produto produto = (Produto) entidade;
		
		// se o "alteraProduto" for igual a 1, executa essa regra
		if(produto.getAlteraProduto().contentEquals("1")) {
			if(produto.getCategoria() == null || produto.getCategoria().equals("")) {
				return ("- Selecione uma Categoria. <br>");
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}

}