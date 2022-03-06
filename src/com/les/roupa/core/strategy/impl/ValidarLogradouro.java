package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;

/**
 * Classe para validar o campo Logradouro do Endereco
 * 
 */

public class ValidarLogradouro implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Endereco endereco = (Endereco) entidade;
		
			if(endereco.getLogradouro() == null || endereco.getLogradouro().equals("")) {
				return (" Insira um logradouro.  <br>");
			}
			else {
				return null;
			}
	}

}
