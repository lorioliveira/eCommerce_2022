package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.CartaoCredito;
import com.les.roupa.core.dominio.EntidadeDominio;

/**
 * Classe para validar o campo Numero do Cartao
 */

public class ValidarNumeroCartao_Alt implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		CartaoCredito cartaoCredito = (CartaoCredito) entidade;
		
		if(cartaoCredito.getAlteraPref().contentEquals("1")) {
			if(cartaoCredito.getNCartao() == null ||cartaoCredito.getNCartao().equals("")) {
				return (" Insira os números do cartao.  <br>");
			}
			else if(cartaoCredito.getNCartao().length() < 16) {
				return (" Números incompletos! Insira todos os números do cartao corretamente. <br>");
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}

}
