package com.les.roupa.core.dominio;

import java.util.List;

/**
 * Classe para representar o Carrinho
 * @author Lorena Oliveira
 */
public class Carrinho extends EntidadeDominio {
	private DetalheProduto detalheProduto;
	private List<Endereco> enderecos;
	private List<CartaoCredito> cartoes;
	private List<Cupom> cupons;
	private String idCliente;
	
	
	/* DETALHE DO PRODUTO */
	public DetalheProduto getDetalheProduto() {
        return detalheProduto;
    }
    public void setDetalheProduto(DetalheProduto detalheProduto) {
        this.detalheProduto = detalheProduto;
    }
    
    /* ENDERECO */
    public List<Endereco> getEnderecos() {
        return enderecos;
    }
    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
    
    /* CARTAO DE CREDITO */
    public List<CartaoCredito> getCartoes() {
        return cartoes;
    }
    public void setCartoes(List<CartaoCredito> cartoes) {
        this.cartoes = cartoes;
    }
    
    /* CUPOM */
    public List<Cupom> getCupons() {
        return cupons;
    }
    public void setCupons(List<Cupom> cupons) {
        this.cupons = cupons;
    }
    
    /* ID DO CLIENTE */
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
}