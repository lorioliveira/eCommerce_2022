package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;

/**
 * Classe para validar o campo CEP do Endereco ao alterar
 * 
 */

public class ValidarCEP_Alt implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Endereco endereco = (Endereco) entidade;
		
		// se o "alteraEndereco" for igual a 1, executa essa regra
		if(endereco.getAlteraEndereco().contentEquals("1")) {
			if (endereco.getCep() == null || endereco.getCep().equals("")) {
				return ("Insira um CEP.<br>");
			}
			else if (endereco.getCep().length() < 8) {
				return ("Insira um CEP com no minimo 8 dígitos. <br>");
			}
			else {
				return null;
			}
		}
		// se o "alteraEndereco" é igual a 0, passa reto da validação
		
		else {
			return null;
		}
	}
	
}