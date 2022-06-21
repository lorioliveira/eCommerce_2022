package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Pedido;
import com.les.roupa.core.strategy.IStrategy;

/**
 * Classe para validar o campo endere�o do Pedido
 */
public class ValidarEnderecoPedido implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Pedido pedido = (Pedido) entidade;
		
		if(pedido.getIdEndereco() == null || pedido.getIdEndereco().equals("")) {
			return ("Selecione algum Endere�o ou cadastre um novo. <br>");
		}
		else {
			return null;
		}
	}

}