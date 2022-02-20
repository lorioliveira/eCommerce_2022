package com.les.roupa.core.dominio;

/**
 * Classe para representar o Usuario (Cliente/Pessoa)
 * @author Lorena Oliveira
 */

public class Usuario extends EntidadeDominio {
	private String email;
	private String senha;
	private String confirmarSenha;
	private String tipoCliente;
	private String nome;
	
	/* Email */
	public String getEmail() {
		if (email == null || email.equals("")) {
            return null;
        }
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	/* Senha*/
	public String getSenha() {
		if (senha == null || senha.equals("")) {
            return null;
        }
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	/* Confirmar Senha*/
	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}
	
	
	/* Tipo de Cliente - se é admin ou cliente*/
	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	
	/* Nome */
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
