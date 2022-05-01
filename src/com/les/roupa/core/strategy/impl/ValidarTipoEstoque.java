package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Estoque;
import com.les.roupa.core.strategy.IStrategy;

/**
 * Classe para validar o campo tipo do Estoque
 */
public class ValidarTipoEstoque implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Estoque estoque = (Estoque) entidade;
		
		if(estoque.getTipo() == null || estoque.getTipo().equals("")) {
			return ("Favor insira um tipo no Estoque.");
		}
		else {
			return null;
		}
	}

}