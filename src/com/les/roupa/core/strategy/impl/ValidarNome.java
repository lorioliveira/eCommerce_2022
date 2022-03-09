package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.EntidadeDominio;

/**
 * Classe para validar o campo nome do cliente
 */
public class ValidarNome implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
			if(cliente.getNome() == null || cliente.getNome().equals("")) {
				return ("Insira o nome do cliente. <br>");
			}
			else {
				return null;
			}
	}

}