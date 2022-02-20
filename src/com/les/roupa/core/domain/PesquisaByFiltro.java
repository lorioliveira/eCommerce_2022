package com.les.roupa.core.domain;

/**
 * Classe para representar o campo (search) de Pesquisas nas telas
 * @author Davi Rodrigues
 * @date 20/11/2021
 */
public class PesquisaByFiltro extends EntidadeDominio {
	private String nomeTabela;
	
	
	public String getNomeTabela() {
		return nomeTabela;
	}
	public void setNomeTabela(String nomeTabela) {
		this.nomeTabela = nomeTabela;
	}
}
