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
				return ("Insira uma senha.<br>");
			}
			else if (cliente.getUsuario().getSenha().length() < 8) {
				return ("Insira uma senha com no mínimo 8 caracteres.<br>");
			}
			else if (cliente.getUsuario().getConfirmarSenha() == null || cliente.getUsuario().getConfirmarSenha().equals("")) {
				return ("Insira a senha novamente. <br>");
			}
			else if (cliente.getUsuario().getConfirmarSenha().length() < 8) {
				return ("Insira a mesma senha, com no minimo 8 caracteres. <br>");
			}
			else if (!cliente.getUsuario().getSenha().equals(cliente.getUsuario().getConfirmarSenha())) {
				return ("As senhas digitadas não se correspondem. Insira-as corretamente. <br>");
			}
			else {
				return null;
			}
	}
	
}