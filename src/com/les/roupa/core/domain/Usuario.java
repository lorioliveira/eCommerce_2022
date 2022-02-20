package com.les.roupa.core.domain;

/**
 * Classe para representar o Usuario
 * @author Davi Rodrigues
 * @date 13/11/2021
 */
public class Usuario extends Pessoa {
	private String login;
	private String senha;
	private String confirmarSenha;
	private String nome;
	private String telefone;
	private String status;
	private String tipo;
	private String cd_sistema;
	
	
	public String getLogin() {
		if (login == null || login.equals("")) {
            return null;
        }
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		if (senha == null || senha.equals("")) {
            return null;
        }
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getConfirmarSenha() {
		if (confirmarSenha == null || confirmarSenha.equals("")) {
            return null;
        }
		return confirmarSenha;
	}
	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getCdSistema() {
		return cd_sistema;
	}
	public void setCdSistema(String cd_sistema) {
		this.cd_sistema = cd_sistema;
	}
}
