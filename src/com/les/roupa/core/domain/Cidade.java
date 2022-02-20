package com.les.roupa.core.domain;

/**
 * Classe para representar a Cidade
 * @author Davi Rodrigues
 * @date 10/03/2021
 */
public class Cidade extends EntidadeDominio {
	private String descricao;
	private Estado estado;

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Estado getEstado() {
        return estado;
    }
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
