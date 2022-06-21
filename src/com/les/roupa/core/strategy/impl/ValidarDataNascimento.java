package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.EntidadeDominio;

/**
 * Classe para validar o campo Data de Nascimento do Cliente
 */
public class ValidarDataNascimento implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
			if(cliente.getData_Nascimento() == null || cliente.getData_Nascimento().equals("")) {
				return (" Insira uma Data de Nascimento. <br>");
			}
			else {
				return null;
			}
	}
}
