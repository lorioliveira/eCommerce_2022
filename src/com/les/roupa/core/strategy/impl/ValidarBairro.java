package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;

/**
 * Classe para validar o campo Bairro do Endereco
 * 
 */
public class ValidarBairro implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Endereco endereco = (Endereco) entidade;
		
		if(endereco.getBairro() == null || endereco.getBairro().equals("")) {
			return ("Insira um Bairro. <br>");
		}
		else {
			return null;
		}
	}
}
