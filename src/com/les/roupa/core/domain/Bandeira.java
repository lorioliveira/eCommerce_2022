package com.les.roupa.core.domain;

/**
 * Classe para representar a Bandeira do Cartão de Crédito
 * @author Davi Rodrigues
 * @date 05/10/2021
 */
public class Bandeira extends EntidadeDominio {
	private String nome;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
