package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.EntidadeDominio;

/**
 * Classe para validar a senha e confirmar senha do cliente - Formulario Nova Conta
 */
public class ValidarSenha implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
			if(cliente.getUsuario().getSenha() == null || cliente.getUsuario().getSenha().equals("")) {
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
				return ("As senhas digitadas não se correspondem.<br>");
			}
			else {
				return null;
			}
	}
	
}