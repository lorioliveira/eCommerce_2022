package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.EntidadeDominio;

/**
 * Classe para validar o campo data nascimento do cliente
 */
public class ValidarDataNascimento_Alt implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
		// se o "alteraCliente" for igual a 1, executa essa regra
		if(cliente.getAlteraCliente().contentEquals("1")) {
			if(cliente.getDt_nasc() == null || cliente.getDt_nasc().equals("")) {
				return ("- Insira uma Data de Nascimento.  <br>");
			}
			else {
				return null;
			}
		}// se o "alteraCliente" é igual a 0, passa reto da validação
		else {
			return null;
		}
	}
}
