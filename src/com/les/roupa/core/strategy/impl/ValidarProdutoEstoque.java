package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Estoque;
import com.les.roupa.core.strategy.IStrategy;

/**
 * Classe para validar o campo produto do Estoque
 */
public class ValidarProdutoEstoque implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Estoque estoque = (Estoque) entidade;
		
		if(estoque.getIdProduto() == null || estoque.getIdProduto().equals("")) {
			return ("Favor insira um produto no Estoque.");
		}
		else {
			return null;
		}
	}

}