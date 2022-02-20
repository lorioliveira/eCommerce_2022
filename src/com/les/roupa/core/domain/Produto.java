package com.les.roupa.core.domain;

/**
 * Classe para representar o Produto
 * @author Davi Rodrigues
 * @date 17/11/2021
 */
public class Produto extends EntidadeDominio {
	private String nome;
	private String descricao;
	private String categoria;
	private String preco_de_compra;
	private String preco_de_venda;
	private String foto;
	private String foto_detalhe;
	private String grupo_de_precificacao;
	private String quantidade;
	private String status;
	private String observacao;
	private String alteraProduto;
	private String quantidadeSelecionada;
	private String cd_sistema;
	
	
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
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getPrecoDeCompra() {
		return preco_de_compra;
	}
	public void setPrecoDeCompra(String preco_de_compra) {
		this.preco_de_compra = preco_de_compra;
	}
	public String getPrecoDeVenda() {
		return preco_de_venda;
	}
	public void setPrecoDeVenda(String preco_de_venda) {
		this.preco_de_venda = preco_de_venda;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getFotoDetalhe() {
		return foto_detalhe;
	}
	public void setFotoDetalhe(String foto_detalhe) {
		this.foto_detalhe = foto_detalhe;
	}
	public String getGrupoDePrecificacao() {
		return grupo_de_precificacao;
	}
	public void setGrupoDePrecificacao(String grupo_de_precificacao) {
		this.grupo_de_precificacao = grupo_de_precificacao;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getAlteraProduto() {
		return alteraProduto;
	}
	public void setAlteraProduto(String alteraProduto) {
		this.alteraProduto = alteraProduto;
	}
	public String getQuantidadeSelecionada() {
		return quantidadeSelecionada;
	}
	public void setQuantidadeSelecionada(String quantidadeSelecionada) {
		this.quantidadeSelecionada = quantidadeSelecionada;
	}
	public String getCdSistema() {
		return cd_sistema;
	}
	public void setCdSistema(String cd_sistema) {
		this.cd_sistema = cd_sistema;
	}
}
