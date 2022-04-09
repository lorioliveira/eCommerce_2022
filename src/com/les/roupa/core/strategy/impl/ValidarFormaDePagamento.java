package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Pedido;
import com.les.roupa.core.strategy.IStrategy;

/**
 * Classe para validar o campo forma de pagamento do Pedido
 */
public class ValidarFormaDePagamento implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Pedido pedido = (Pedido) entidade;
		
		if(pedido.getFormaPagamento() == null || pedido.getFormaPagamento().equals("")) {
			pedido.setFormaPagamento("");		
			return ("Favor selecione uma Forma de Pagamento.");
		}
		else {
			return null;
		}
	}

}