package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.EntidadeDominio;

/**
 * Classe para validar o campo nome do cliente
 */
public class ValidarNome_Alt implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
		// se o "alteraCliente" for igual a 1, executa essa regra
		if(cliente.getAlteraCliente().contentEquals("1")) {
			if(cliente.getNome() == null || cliente.getNome().equals("")) {
				return ("Favor insira o nome do cliente. <br>");
			}
			else {
				return null;
			}
		}
		// se não, o "alteraCliente" é igual a 0, e não executa essa regra
		else {
			return null;
		}
	}

}