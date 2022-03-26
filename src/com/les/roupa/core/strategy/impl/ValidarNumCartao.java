package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.CartaoCredito;
import com.les.roupa.core.dominio.EntidadeDominio;

/**
 * Classe para validar o campo Numero do Cartao
 * CartaoCredito cartaoCredito = new CartaoCredito();
 */

public class ValidarNumCartao implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		CartaoCredito cartaoCredito = (CartaoCredito) entidade;
		
		if(cartaoCredito.getNumCartao() == null ||cartaoCredito.getNumCartao().equals("")) {
			return ("Insira o numero do cartao.");
		}
		else {
			return null;
		}
	}

}
