package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;

/**
 * Classe para validar o campo CIDADE do Endereco 
 * 
 */

public class ValidarCidade implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Endereco endereco = (Endereco) entidade;
		
			if(endereco.getCidade() == null || endereco.getCidade().equals("")) {
				return (("Selecione uma Cidade.  <br>"));
			}
			else {
				return null;
			}
		}

}