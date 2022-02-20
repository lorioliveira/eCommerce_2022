package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Produto;

/**
 * Classe para validar o campo foto do Produto
 */
public class ValidarFoto implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Produto produto = (Produto) entidade;
		
		// se o "alteraProduto" for igual a 1, executa essa regra
		if(produto.getAlteraProduto().contentEquals("1")) {
			if(produto.getFoto()== null || produto.getFoto().equals("")) {
				return ("Insira uma foto do produto.  <br>");
			}
			else {
				return null;
			}
			// Se o alteraProduto for 0, ele não executa a regra
		}else {
			return null;
		}
	}
}
