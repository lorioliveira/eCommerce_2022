package com.les.roupa.core.strategy.impl;

import java.util.List;

import com.les.roupa.core.dao.impl.ClienteDAO;
import com.les.roupa.core.dao.impl.LoginDAO;
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Usuario;
import com.les.roupa.core.strategy.IStrategy;

/**
 * Validar a existencia do Usuario (email e senha) ao logar -  LOGIN
 */
public class ValidarExisteEmailSenha implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Usuario usuario = (Usuario) entidade;
		ClienteDAO dao = new ClienteDAO();
		// consultar email e senha cadastrados do usuario
		// que é retornado numa lista de cliente, para poder puxar o status
		// no momento de verificar
		
		List<Cliente> clientes = dao.consultarStatusByEmailSenha(usuario.getEmail(), usuario.getSenha());
		
		if(clientes.isEmpty()) {
			return ("Email e/ou senha invalidos. Por favor, tente novamente. <br>");
		}
		else if(clientes.get(0).getStatus().equals("inativo") || (clientes.get(0).getStatus().equals("Inativo"))) {
			return ("Usuário temporariamente <b>inativado</b>. <br>Entre em contato com o Administrador. <br>");
		}else {
			return null;
		}
	}
}
	