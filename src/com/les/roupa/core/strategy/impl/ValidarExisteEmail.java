package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;

import java.util.List;

import com.les.roupa.core.dao.impl.LoginDAO;
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Usuario;

/**
 * Classe para validar se existe Email ja cadastrado no sistema - Formulario de Nova Conta 
 * 
 * @author Lorena Oliveira
 */

public class ValidarExisteEmail implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Cliente cliente = (Cliente) entidade;
		LoginDAO dao = new LoginDAO();
		
		List<Usuario> usuarios = dao.consultarUsuarioByEmail(cliente.getUsuario().getEmail());
		
			if(!usuarios.isEmpty()) {
				// Caso exista algo igual ao email
				return (("Esse email já existe! Entre ou cadastre com outro email. <br>"));
			}
			else {
				return null;
			}
	}
	
}