package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.CartaoCredito;
import com.les.roupa.core.dominio.EntidadeDominio;

/**
 * Classe para validar o campo Nome no Cartao
 * CartaoCredito cartaoCredito = new CartaoCredito();
 */

public class ValidarNomeCartao_Alt implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		CartaoCredito cartaoCredito = (CartaoCredito) entidade;
		
		if(cartaoCredito.getAlteraPref().contentEquals("1")) {
			if(cartaoCredito.getNome() == null ||cartaoCredito.getNome().equals("")) {
				return (" Insira o nome do titular do cartao.  <br>");
			}
			else {
				return null;
			}

		}else {
			return null;
		}
	}

}
