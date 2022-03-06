package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;

/**
 * Classe para validar o campo Pais do Endereco
 * 
 */

public class ValidarPais implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Endereco endereco = (Endereco) entidade;
		
			if(endereco.getPais() == null || endereco.getPais().equals("")) {
				return ("Selecione um pais. <br>");
			}
			else {
				return null;
			}
	}

}