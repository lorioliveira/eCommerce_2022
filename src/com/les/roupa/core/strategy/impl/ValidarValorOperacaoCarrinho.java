package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Carrinho;

/**
 * Classe para validar o campo Quantidade do Produto no Carrinho
 * 
 */

public class ValidarValorOperacaoCarrinho implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Carrinho carrinho = (Carrinho) entidade;
		int nova_quantidade = 0;
		
		if(carrinho.getValorOperacao().equals("SUBTRACAO")) {
			nova_quantidade = Integer.parseInt(carrinho.getQtdeProduto());
			nova_quantidade --;
			carrinho.setQtdeProduto(Integer.toString(nova_quantidade));
			
			return null;
		}
		else if(carrinho.getValorOperacao().equals("ADICAO")) {
			nova_quantidade = Integer.parseInt(carrinho.getQtdeProduto());
			nova_quantidade ++;
			carrinho.setQtdeProduto(Integer.toString(nova_quantidade));
			
			return null;
		}
		else {
			return null;
		}
	}
}