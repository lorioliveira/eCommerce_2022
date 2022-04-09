package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Pedido;
import com.les.roupa.core.strategy.IStrategy;

/**
 * Classe para validar o total do Pedido
 */
public class ValidarTotalPedido implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Pedido pedido = (Pedido) entidade;
		
		if(pedido.getTotalPedido() == null || pedido.getTotalPedido().equals("") || pedido.getTotalPedido().equals("0.0")) {
			return ("Favor insira algum Produto ao Carrinho antes de finalizar a compra.");
		}
		else {
			return null;
		}
	}

}