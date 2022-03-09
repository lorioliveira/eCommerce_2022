package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Usuario;

/**
 * Classe para validar Email vazio ao alterar Cliente
 */
public class ValidarEmail implements IStrategy {

	
	public String validar(EntidadeDominio entidade) {
		
		Cliente cliente = (Cliente) entidade;
		Usuario usuario = cliente.getUsuario();
		
		// se o "alteraCliente" for igual a 1, executa essa regra
		if(cliente.getAlteraCliente().contentEquals("1")) {
			if(usuario.getEmail() == null || usuario.getEmail().equals("")) {
				return (" Insira um email. <br>");
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
