package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Estoque;
import com.les.roupa.core.strategy.IStrategy;

/**
 * Classe para validar o campo fornecedor do Estoque
 */
public class ValidarFornecedorEstoque implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Estoque estoque = (Estoque) entidade;
		
		if(estoque.getFornecedor() == null || estoque.getFornecedor().equals("")) {
			return ("Insira um fornecedor no Estoque.");
		}
		else {
			return null;
		}
	}

}