package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.EntidadeDominio;

/** 
 * Classe para validar o campo Telefone do cliente ao alterar
 * 
 */
public class ValidarTelefone implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
		// se o "alteraCliente" for igual a 1, executa essa regra
		if(cliente.getAlteraCliente().contentEquals("1")) {
			if(cliente.getTelefone() == null || cliente.getTelefone().equals("")) {
				return (" Insira um celular. <br>");
			}
			else {
				return null;
			}
		}
		else {// se o "alteraCliente" é igual a 0, passa reto da validação
			return null;
		}
	}

}
