package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.EntidadeDominio;

/**
 * Classe para validar o campo genero do cliente
 * 
 */
public class ValidarGenero implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
			if(cliente.getGenero() == null || cliente.getGenero().equals("")) {
				return (" Selecione um gênero. <br>");
			}
			else {
				return null;
			}
	}

}
