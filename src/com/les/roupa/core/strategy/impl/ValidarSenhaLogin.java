package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Usuario;

/**
 * Classe para validar a senha do cliente - LOGIN
 */
public class ValidarSenhaLogin implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Usuario usuario = (Usuario) entidade;
		
			if(usuario.getSenha() == null || usuario.getSenha().equals("")) {
				return ("Favor insira uma senha. <br>");
			}
			else if (usuario.getSenha().length() < 8) {
				return ("Favor insira uma senha com no minimo 8 caracteres.<br>");
			}
			else {
				return null;
			}
	}
	
}