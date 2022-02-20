package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;

/**
 * Classe para validar o campo Bairro - Alterar Endereco
 * 
 */
public class ValidarBairro_Alt implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Endereco endereco = (Endereco) entidade;
		
		// se o "alteraEndereco" for igual a 1, executa essa regra
		if(endereco.getAlteraEndereco().contentEquals("1")) {
			if(endereco.getBairro() == null || endereco.getBairro().equals("")) {
				return (" Insira um Bairro.<br>");
			}
			else {
				return null;
			}
		}// se o "alteraEndereco" é igual a 0, passa reto da validação
		else {
			return null;
		}
	}
}
