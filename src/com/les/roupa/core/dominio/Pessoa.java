package com.les.roupa.core.dominio;

/**
 * Classe para representar a Pessoa (Cliente/Usuario) 
 * @author Lorena Oliveira
 */
public class Pessoa extends EntidadeDominio {
	private String nome;
	private String cpf;
	private String genero;
	
	
	/* Nome */
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	/* CPF */
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	/*Genero*/
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
}
