package com.les.roupa.core.dominio;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para representar o Gráfico da Análise com base em categorias
 */
public class GraficoCategoria extends EntidadeDominio {
	private Produto produto;
	private String dtInicio;
	private String dtFim;
	private List<Produto> nomeCategoria1;
	private List<Produto> nomeCategoria2;
	private List<Produto> nomeCategoria3;
	private List<String> totalColunas;
	private List<String> totalValorCategoria1;
	private List<String> totalValorCategoria2;
	private List<String> totalValorCategoria3;
	
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
	
	public List<Produto> getNomeCategoria1() {
        return nomeCategoria1;
    }
    public void setNomeCategoria1(List<Produto> nomeCategoria1) {
        this.nomeCategoria1 = nomeCategoria1;
    }
    
    public List<Produto> getNomeCategoria2() {
        return nomeCategoria2;
    }
    public void setNomeCategoria2(List<Produto> nomeCategoria2) {
        this.nomeCategoria2 = nomeCategoria2;
    }
    
    public List<Produto> getNomeCategoria3() {
        return nomeCategoria3;
    }
    public void setNomeCategoria3(List<Produto> nomeCategoria3) {
        this.nomeCategoria3 = nomeCategoria3;
    }
    
	public List<String> getTotalColunas() {
        return totalColunas;
    }
    public void setTotalColunas(List<String> totalColunas) {
        this.totalColunas = totalColunas;
    }
    
	public List<String> getTotalValorCategoria1() {
        return totalValorCategoria1;
    }
    public void setTotalValorCategoria1(List<String> totalValorCategoria1) {
        this.totalValorCategoria1 = totalValorCategoria1;
    }
    
	public List<String> getTotalValorCategoria2() {
        return totalValorCategoria2;
    }
    public void setTotalValorCategoria2(List<String> totalValorCategoria2) {
        this.totalValorCategoria2 = totalValorCategoria2;
    }
    
	public List<String> getTotalValorCategoria3() {
        return totalValorCategoria3;
    }
    public void setTotalValorCategoria3(List<String> totalValorCategoria3) {
        this.totalValorCategoria3 = totalValorCategoria3;
    }
}