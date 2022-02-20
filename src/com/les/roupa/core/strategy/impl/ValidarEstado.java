package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;

/**
 * Classe para validar o campo Estado do Endereco
 * 
 */

public class ValidarEstado implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Endereco endereco = (Endereco) entidade;
		
			if(endereco.getEstado() == null || endereco.getEstado().equals("")) {
				return (" Selecione um estado.  <br>");
			}
			else {
				return null;
			}
	}

}