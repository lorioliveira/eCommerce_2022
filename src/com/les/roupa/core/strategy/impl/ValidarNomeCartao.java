package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.CartaoCredito;
import com.les.roupa.core.dominio.EntidadeDominio;

/**
 * Classe para validar o campo No do Carmetao
 * CartaoCredito cartaoCredito = new CartaoCredito();
 */

public class ValidarNomeCartao implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		CartaoCredito cartaoCredito = (CartaoCredito) entidade;
		
		if(cartaoCredito.getNome() == null ||cartaoCredito.getNome().equals("")) {
			return (" Insira o nome do titular do cartao.  <br>");
		}
		else {
			return null;
		}
	}

}
