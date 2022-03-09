package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;

/**
 * Classe para validar o campo CEP do Endereco
 * 
 */

public class ValidarCEP implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Endereco endereco = (Endereco) entidade;
		
			if (endereco.getCep() == null || endereco.getCep().equals("")) {
				return ("Insira um CEP. <br>");
			}
			else if (endereco.getCep().length() < 8) {
				return ("Insira um CEP com no minimo 8 dígitos. <br>");
			}
			else {
				return null;
			}
		}
	
}