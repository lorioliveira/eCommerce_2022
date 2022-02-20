package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.CartaoCredito;
import com.les.roupa.core.dominio.EntidadeDominio;

/**
 * Classe para validar o campo Numero do Cartao
 * CartaoCredito cartaoCredito = new CartaoCredito();
 */

public class ValidarNumeroCartao implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		CartaoCredito cartaoCredito = (CartaoCredito) entidade;
		
		if(cartaoCredito.getNCartao() == null ||cartaoCredito.getNCartao().equals("")) {
			return (" Insira o numero do cartao.  <br>");
		}
		else if(cartaoCredito.getNCartao().length() < 16) {
			return (" Números incompletos! Insira todos os números do cartao corretamente. <br>");
		}
		else {
			return null;
		}
	}

}
