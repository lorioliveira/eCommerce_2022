package com.les.roupa.core.dominio;

/**
 * Classe para representar o Carrinho
 */
public class Carrinho extends Produto {
	private String id_cliente;
	private String id_produto;
	private String qtde_produto;
	private String status;
	
	private String valorOperacao;
	
	/* Id Cliente */
	public String getIdCliente() {
		return id_cliente;
	}
	public void setIdCliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}
	
	/* Id Produto */
	public String getIdProduto() {
		return id_produto;
	}
	public void setIdProduto(String id_produto) {
		this.id_produto = id_produto;
	}
	
	/* Qtde Produto */
	public String getQtdeProduto() {
		return qtde_produto;
	}
	public void setQtdeProduto(String qtde_produto) {
		this.qtde_produto = qtde_produto;
	}
	
	// STATUS = ATIVO OU INATIVO
    public String getStatus() {
    	return status;
    }
    public void setStatus(String status) {
    	this.status = status;
    }
    
    /* Alterar Quantidade - Carrinho */
    public String getValorOperacao() {
    	return valorOperacao;
    }
    public void setValorOperacao(String valorOperacao) {
    	this.valorOperacao = valorOperacao;
    }
	
}
