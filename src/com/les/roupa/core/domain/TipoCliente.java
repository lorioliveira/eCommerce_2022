package com.les.roupa.core.domain;

/**
 * Classe para representar o Tipo de Cliente
 * @author Davi Rodrigues
 * @date 10/03/2021
 */
public class TipoCliente extends EntidadeDominio {
	private String nome;
	private String descricao;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
