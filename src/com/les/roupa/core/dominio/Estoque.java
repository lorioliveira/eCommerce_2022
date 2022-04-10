package com.les.roupa.core.dominio;

import java.util.List;

/**
 * Classe para representar o Estoque
 */
public class Estoque extends EntidadeDominio {
	private String id_produto;
	private String tipo;
	private String quantidade_entrada_saida;
	private String valor_custo;
	private String fornecedor;
	private String dt_entrada;
	private String quantidade_final;
	private List<Estoque> estoqueDoProduto;
	private String nome_produto;

	
	public String getIdProduto() {
		return id_produto;
	}
	public void setIdProduto(String id_produto) {
		this.id_produto = id_produto;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getQuantidadeEntradaSaida() {
		return quantidade_entrada_saida;
	}
	public void setQuantidadeEntradaSaida(String quantidade_entrada_saida) {
		this.quantidade_entrada_saida = quantidade_entrada_saida;
	}
	public String getValorCusto() {
		return valor_custo;
	}
	public void setValorCusto(String valor_custo) {
		this.valor_custo = valor_custo;
	}
	public String getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}
	public String getDtEntrada() {
		return dt_entrada;
	}
	public void setDtEntrada(String dt_entrada) {
		this.dt_entrada = dt_entrada;
	}
	public String getQuantidadeFinal() {
		return quantidade_final;
	}
	public void setQuantidadeFinal(String quantidade_final) {
		this.quantidade_final = quantidade_final;
	}
	
	public List<Estoque> getEstoqueDoProduto() {
        return estoqueDoProduto;
    }
    public void setEstoqueDoProduto(List<Estoque> estoqueDoProduto) {
        this.estoqueDoProduto = estoqueDoProduto;
    }
    
	public String getNomeProduto() {
		return nome_produto;
	}
	public void setNomeProduto(String nome_produto) {
		this.nome_produto = nome_produto;
	}
	
}