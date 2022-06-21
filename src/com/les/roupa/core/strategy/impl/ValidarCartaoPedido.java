 package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Pedido;
import com.les.roupa.core.strategy.IStrategy;

/**
 * Classe para validar o campo cartão de crédito do Pedido
 */
public class ValidarCartaoPedido implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Pedido pedido = (Pedido) entidade;
		
		// ajuste do BUG para quando for digitado um valor quebrado na tela com vírgula,
		// será substituido para o ponto
		pedido.setValorCartao1(pedido.getValorCartao1().replace(",", "."));
		pedido.setValorCartao2(pedido.getValorCartao2().replace(",", "."));
		
		if (pedido.getFormaPagamento().equals("cartao")) {
			if((pedido.getIdCartao1() == null || pedido.getIdCartao1().equals("")) &&
				(pedido.getIdCartao2() == null || pedido.getIdCartao2().equals(""))) {
				return ("Favor selecione algum cartão de crédito ou cadastre um novo. <br>");
			}
			// se tiver cupom sendo utilizado, o valor minimo no cartão é até R$ 5,00
			else if (!pedido.getTotalCupons().equals("0.0")) {
				// se foi selecionado 2 cartões de créditos
				if ((pedido.getIdCartao1() != null) && (pedido.getIdCartao2() != null )) {
					if (pedido.getIdCartao1().equals(pedido.getIdCartao2())) {
						return ("Os Cartões de Créditos selecionados devem ser diferentes. <br>");
					}
					if ((Double.parseDouble(pedido.getValorCartao1())) < 5) {
						return ("Valor mínimo no 1º Cartão de Crédito deve ser R$ 5,00.");
					}
					if ((Double.parseDouble(pedido.getValorCartao2())) < 5) {
						return ("Valor mínimo no 2º Cartão de Crédito deve ser R$ 5,00.");
					}
					
					double total_cartoes = 0;
					total_cartoes = ((Double.parseDouble(pedido.getValorCartao1())) + (Double.parseDouble(pedido.getValorCartao2())));
					if (!Double.toString(total_cartoes).equals(pedido.getTotalPedido())) {
						return ("A soma entre os valores dos Cartões, não correspondem com o total do Pedido. <br>Tente novamente!");
					}
					else {
						return null;
					}
				}
				// se foi selecionado só o 1º cartão
				if (pedido.getIdCartao1() != null) {
					//if ((Double.parseDouble(pedido.getValorCartao1())) < 5) {
					//	return ("Valor mínimo no 1º Cartão de Crédito deve ser maior que R$ 5,00.");
					//}
					if (!pedido.getValorCartao1().equals(pedido.getTotalPedido())) {
						return ("O valor do 1º Cartão, não corresponde com o total do Pedido. <br>Tente novamente!");
					}
					else {
						return null;
					}
				}
				// se foi selecionado só o 2º cartão
				if (pedido.getIdCartao2() != null) {
					//if ((Double.parseDouble(pedido.getValorCartao2())) < 5) {
					//	return ("Valor mínimo no 2º Cartão de Crédito deve ser maior que R$ 5,00.");
					//}
					if (!pedido.getValorCartao2().equals(pedido.getTotalPedido())) {
						return ("O valor do 2º Cartão, não corresponde com o total do Pedido. <br>Tente novamente!");
					}
					else {
						return null;
					}
				}
				else {
					return null;
				}
			}
			// se NÂO tiver cupom sendo utilizado, o valor minimo no cartão é até R$ 10,00
			else if (pedido.getTotalCupons().equals("0.0")) {
				// se foi selecionado 2 cartões de créditos
				if ((pedido.getIdCartao1() != null) && (pedido.getIdCartao2() != null)) {
					if (pedido.getIdCartao1().equals(pedido.getIdCartao2())) {
						return ("Os Cartões de Créditos selecionados devem ser diferentes");
					}
					if ((Double.parseDouble(pedido.getValorCartao1())) < 10) {
						return ("Valor mínimo no 1º Cartão de Crédito deve ser R$ 10,00.");
					}
					if ((Double.parseDouble(pedido.getValorCartao2())) < 10) {
						return ("Valor mínimo no 2º Cartão de Crédito deve ser R$ 10,00.");
					}
					
					double total_cartoes = 0;
					total_cartoes = ((Double.parseDouble(pedido.getValorCartao1())) + (Double.parseDouble(pedido.getValorCartao2())));
					if (!Double.toString(total_cartoes).equals(pedido.getTotalPedido())) {
						return ("A soma entre os valores dos Cartões, não correspondem com o total do Pedido. <br>Tente novamente!");
					}
					else {
						return null;
					}
				}
				// se foi selecionado só o 1º cartão
				if (pedido.getIdCartao1() != null) {
					//if ((Double.parseDouble(pedido.getValorCartao1())) < 10) {
					//	return ("Valor mínimo no 1º Cartão de Crédito deve ser maior que R$ 10,00.");
					//}
					if (!pedido.getValorCartao1().equals(pedido.getTotalPedido())) {
						return ("O valor do 1º Cartão, não se corresponde com o total do Pedido.");
					}
					else {
						return null;
					}
				}
				// se foi selecionado só o 2º cartão
				if (pedido.getIdCartao2() != null) {
					//if ((Double.parseDouble(pedido.getValorCartao2())) < 10) {
					//	return ("Valor mínimo no 2º Cartão de Crédito deve ser maior que R$ 10,00.");
					//}
					if (!pedido.getValorCartao2().equals(pedido.getTotalPedido())) {
						return ("O valor do 2º Cartão, não se corresponde com o total do Pedido.");
					}
					else {
						return null;
					}
				}
				else {
					return null;
				}
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}

}