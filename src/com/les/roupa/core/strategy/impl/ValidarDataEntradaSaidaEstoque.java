package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Estoque;
import com.les.roupa.core.strategy.IStrategy;

/**
 * Classe para validar o campo fornecedor do Estoque
 */
public class ValidarDataEntradaSaidaEstoque implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Estoque estoque = (Estoque) entidade;
		
		if(estoque.getDtEntrada() == null || estoque.getDtEntrada().equals("")) {
			return ("Favor insira uma data de entrada/saida no Estoque.");
		}
		else {
			return null;
		}
	}

}