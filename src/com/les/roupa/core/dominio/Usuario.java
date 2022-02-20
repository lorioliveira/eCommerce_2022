package com.les.roupa.core.dominio;

/**
 * Classe para representar o Usuario
 */
public class Usuario extends EntidadeDominio {
	private String email;
	private String senha;
	private String confSenha;
	private String tipo;
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
	public String getConfSenha() {
		if (confSenha == null || confSenha.equals("")) {
            return null;
        }
		return confSenha;
	}
	public void setConfSenha(String confSenha) {
		this.confSenha = confSenha;
	}
	
	/* Tipo de Cliente - se é admin ou cliente*/
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	/* Nome */
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
