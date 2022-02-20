package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.CartaoCredito;
import com.les.roupa.core.dominio.EntidadeDominio;

/**
 * Classe para validar o campo CVV do Cartao
 * CartaoCredito cartaoCredito = new CartaoCredito();
 */

public class ValidarCVV implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		CartaoCredito cartaoCredito = (CartaoCredito) entidade;
		
		if(cartaoCredito.getCvv()== null ||cartaoCredito.getCvv().equals("")) {
			return ("- Insira o código de segurança do cartão. <br>");
		}
		else {
			return null;
		}
	}

}
