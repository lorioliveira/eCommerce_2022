package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Estoque;
import com.les.roupa.core.strategy.IStrategy;

/**
 * Classe para validar data entrada/saida - Estoque
 */
public class ValidarDataEntradaSaidaEstoque implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Estoque estoque = (Estoque) entidade;
		
		if(estoque.getDtEntrada() == null || estoque.getDtEntrada().equals("")) {
			return ("Insira uma data de entrada/saida do produto no Estoque.<br>");
		}
		else {
			return null;
		}
	}

}