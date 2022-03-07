package com.les.roupa.core.dominio;

import java.util.List;

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
	private String telefone;
	private String genero;
	private String cpf;
	private String data_Nascimento;
	
	private List<Endereco> enderecosCliente; 
	private List<Cliente> todosClientes;
	
	

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
	
	/* Telefone */
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	/*Genero*/
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	/* CPF */
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	/* Data Nascimento */
	public String getData_Nascimento() {
		return data_Nascimento;
	}
	public void setData_Nascimento(String data_Nascimento) {
		this.data_Nascimento = data_Nascimento;
	}
	
	/*Enderecos do Cliente */
	public List<Endereco> getEnderecosCliente() {
		return enderecosCliente;
	}

	public void setEnderecosCliente(List<Endereco> enderecosCliente) {
		this.enderecosCliente = enderecosCliente;
	}
	
	/* Lista de todos os Clientes cadastrados*/
	public List<Cliente> getTodosClientes() {
		return todosClientes;
	}

	public void setTodosClientes(List<Cliente> todosClientes) {
		this.todosClientes = todosClientes;
	}
}
