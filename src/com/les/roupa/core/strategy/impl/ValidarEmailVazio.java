package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Usuario;

/**
 * Classe para validar Email vazio - Formulário Nova Conta
 * 
 * 
 */
public class ValidarEmailVazio implements IStrategy {

	
	public String validar(EntidadeDominio entidade) {
		
		Cliente cliente = (Cliente) entidade;
		Usuario usuario = cliente.getUsuario();
		
			if(usuario.getEmail() == null || usuario.getEmail().equals("")) {
				return (" Insira um email completo. <br>");
			}
			else {
				return null;
			}
	}

}
