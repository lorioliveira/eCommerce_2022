package com.les.roupa.core.dominio;

import java.util.List;

/**
 * Classe para representar o produto 
 * @author Lorena Oliveira
 */

public class Produto extends EntidadeDominio {
	private String nome;
	private String descricao;
	private String categoria;
	private String cores;
	private String tamanho;
	private String precoCompra;
	private String precoVenda;
	private String qtdeEstoque;
	private String foto;
	private String status;
	private String motivoStatus;
	private String grupoPrecificacao;
	
	private String alteraProduto;
	private List<Produto> todosProdutos;
		
	/* NOME */
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/* DESCRICAO */
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	/* Motivo Ativação/Inativação */
	public String getMotivoStatus() {
		return motivoStatus;
	}
	public void setMotivoStatus(String motivoStatus) {
		this.motivoStatus = motivoStatus;
	}
	
	/* CATEGORIA */
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	/* CORES */
	public String getCores() {
		return cores;
	}
	public void setCores(String cores) {
		this.cores = cores;
	}
	
	/* TAMANHO */
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	
	/* PRECO DE COMPRA */
	public String getPrecoCompra() {
		return precoCompra;
	}
	public void setPrecoCompra(String precoCompra) {
		this.precoCompra = precoCompra;
	}
	
	/* PRECO DE VENDA */
	public String getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(String precoVenda) {
		this.precoVenda = precoVenda;
	}
	
	/* QUANTIDADE */
	public String getQtdeEstoque() {
		return qtdeEstoque;
	}
	public void setQtdeEstoque(String qtdeEstoque) {
		this.qtdeEstoque = qtdeEstoque;
	}
	
	/* FOTO */
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	// STATUS = ATIVO OU INATIVO
    public String getStatus() {
    	return status;
    }
    public void setStatus(String status) {
    	this.status = status;
    }
		
    /* GRUPO DE PRECIFICACAO */
    public String getGrupoPrecificacao() {
    	return grupoPrecificacao;
    }
    public void setGrupoPrecificacao(String grupoPrecificacao) {
    	this.grupoPrecificacao = grupoPrecificacao; 
    }
	
    /* Alterar Produto */
    public String getAlteraProduto() {
    	return alteraProduto;
    }
    public void setAlteraProduto(String alteraProduto) {
    	this.alteraProduto = alteraProduto;
    }
    
	public void setQuantidadeSelecionada(String string) {
		// TODO Auto-generated method stub
		
	}
	public String getQuantidadeSelecionada() {
		// TODO Auto-generated method stub
		return null;
	}
	
	// Lista de Todos os cartoes do Cliente cadastrados
	public List<Produto> getTodosProdutos() {
		return todosProdutos;
	}

	public void setTodosProdutos(List<Produto> todosProdutos) {
		this.todosProdutos = todosProdutos;
	}
}
