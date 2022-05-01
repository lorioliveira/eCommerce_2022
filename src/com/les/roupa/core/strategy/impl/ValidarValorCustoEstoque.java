package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Estoque;
import com.les.roupa.core.strategy.IStrategy;

/**
 * Classe para validar o campo valor de custo do Estoque
 */
public class ValidarValorCustoEstoque implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Estoque estoque = (Estoque) entidade;
		
		if(estoque.getValorCusto() == null || estoque.getValorCusto().equals("")) {
			return ("Favor insira um valor de custo no Estoque.");
		}
		else {
			return null;
		}
	}

}