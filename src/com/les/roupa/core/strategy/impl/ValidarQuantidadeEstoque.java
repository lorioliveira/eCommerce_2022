package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Estoque;
import com.les.roupa.core.strategy.IStrategy;

/**
 * Classe para validar o campo quantidade entrada/saida do Estoque
 */
public class ValidarQuantidadeEstoque implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Estoque estoque = (Estoque) entidade;
		
		if(estoque.getQuantidadeEntradaSaida() == null || estoque.getQuantidadeEntradaSaida().equals("")) {
			return ("Insira uma quantidade no Estoque.<br>");
		}
		else if (Integer.parseInt(estoque.getQuantidadeEntradaSaida()) <= 0) {
			return ("Selecione uma quantidade no Estoque maior que ZERO!<br>");
		}
		else {
			return null;
		}
	}

}