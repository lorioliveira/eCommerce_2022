package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;

/**
 * Classe para validar o campo Numero do Endereco
 * 
 */

public class ValidarNumero implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Endereco endereco = (Endereco) entidade;
		
			if(endereco.getNumero() == null || endereco.getNumero().equals("")) {
				return (" Insira um numero do endereco.  <br>");
			}
			else {
				return null;
			}
	}

}