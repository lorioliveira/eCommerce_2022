package com.les.roupa.core.dominio;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para representar o Gráfico da Análise
 * @author Lorena Oliveira
 */
public class GraficoAnalise extends EntidadeDominio {
	private Produto produto;
	private String dtInicio;
	private String dtFim;
	private List<Produto> nomeProduto1;
	private List<Produto> nomeProduto2;
	private List<Produto> nomeProduto3;
	private List<String> totalColunas;
	private List<String> totalValorProduto1;
	private List<String> totalValorProduto2;
	private List<String> totalValorProduto3;
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public String getDtInicio() {
		return dtInicio;
	}
	public void setDtInicio(String dtInicio) {
		this.dtInicio = dtInicio;
	}
	public String getDtFim() {
		return dtFim;
	}
	public void setDtFim(String dtFim) {
		this.dtFim = dtFim;
	}
	
	
	
	public List<Produto> getNomeProduto1() {
        return nomeProduto1;
    }
    public void setNomeProduto1(List<Produto> nomeProduto1) {
        this.nomeProduto1 = nomeProduto1;
    }
    
    public List<Produto> getNomeProduto2() {
        return nomeProduto2;
    }
    public void setNomeProduto2(List<Produto> nomeProduto2) {
        this.nomeProduto2 = nomeProduto2;
    }
    
    public List<Produto> getNomeProduto3() {
        return nomeProduto3;
    }
    public void setNomeProduto3(List<Produto> nomeProduto3) {
        this.nomeProduto3 = nomeProduto3;
    }
    
    
    
	public List<String> getTotalColunas() {
        return totalColunas;
    }
    public void setTotalColunas(List<String> totalColunas) {
        this.totalColunas = totalColunas;
    }
    
    
    
	public List<String> getTotalValorProduto1() {
        return totalValorProduto1;
    }
    public void setTotalValorProduto1(List<String> totalValorProduto1) {
        this.totalValorProduto1 = totalValorProduto1;
    }
    
	public List<String> getTotalValorProduto2() {
        return totalValorProduto2;
    }
    public void setTotalValorProduto2(List<String> totalValorProduto2) {
        this.totalValorProduto2 = totalValorProduto2;
    }
    
	public List<String> getTotalValorProduto3() {
        return totalValorProduto3;
    }
    public void setTotalValorProduto3(List<String> totalValorProduto3) {
        this.totalValorProduto3 = totalValorProduto3;
    }
}