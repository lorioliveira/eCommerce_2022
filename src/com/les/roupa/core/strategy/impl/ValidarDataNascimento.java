package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.EntidadeDominio;



/**
 * Classe para validar o campo data nascimento do cliente
 */
public class ValidarDataNascimento implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
			if(cliente.getDt_nasc() == null || cliente.getDt_nasc().equals("")) {
				return (" Insira uma Data de Nascimento.  <br>");
			}
			else {
				return null;
			}
	}
}
