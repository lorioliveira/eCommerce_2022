package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.CartaoCredito;
import com.les.roupa.core.dominio.EntidadeDominio;

/**
 * Classe para validar o campo Bandeira do Cartao
 * CartaoCredito cartaoCredito = new CartaoCredito();
 */

public class ValidarBandeiraCartao implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		CartaoCredito cartaoCredito = (CartaoCredito) entidade;
		
		if(cartaoCredito.getBandeira() == null ||cartaoCredito.getBandeira().equals("")) {
			return ("Insira a bandeira do cartao.");
		}
		else {
			return null;
		}
	}

}
