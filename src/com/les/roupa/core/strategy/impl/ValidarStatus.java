package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.EntidadeDominio;

/**
 * Classe para validar o flg de ativo do cliente - ADMIN
 */
public class ValidarStatus implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
		if(cliente.getStatus() == null || cliente.getStatus().equals("")) {
			return ("Insira um status. <br>");
		}
		else {
			return null;
		}
	}

}