package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Usuario;

/**
 * Classe para validar a senha do cliente ao Alterar Cliente
 */
public class ValidarSenhaConfSenha implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Cliente cliente = (Cliente) entidade;
		
		// se o "alteraCliente" for igual a 1, executa essa regra
		if(cliente.getAlteraCliente().contentEquals("1")) {
			if (cliente.getUsuario().getSenha() == null || cliente.getUsuario().getSenha().equals("")) {
				return ("Favor insira uma senha.<br>");
			}
			else if (cliente.getUsuario().getSenha().length() < 8) {
				return ("Favor insira uma senha com no minimo 8 caracteres.<br>");
			}
			else if (cliente.getUsuario().getConfSenha() == null || cliente.getUsuario().getConfSenha().equals("")) {
				return ("Favor insira a senha novamente.<br>");
			}
			else if (cliente.getUsuario().getConfSenha().length() < 8) {
				return ("Favor insira a mesma senha, com no minimo 8 caracteres.<br>");
			}
			else if (!cliente.getUsuario().getSenha().equals(cliente.getUsuario().getConfSenha())) {
				return ("As senhas digitadas não se correspondem. Insira novamente.<br>");
			}
			else {
				return null;
			}
		}//se não for, ele não executa, passa adiante
		else {
			return null;
		}
	}
	
}