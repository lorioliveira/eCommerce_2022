package com.les.roupa.core.dominio;

/**
 * Classe para representar o Pedido
 */
public class Pedido extends EntidadeDominio {
	private String precoTotalProduto;
	private String precoFrete;
	private String precoTotal;
	private String statusPedido;
	private String id_cliente;
	private String id_endereco;
	private String cartao1;
	private String valorCartao1;
	private String cartao2;
	private String valorCartao2;
	private String desconto;
	
	private String alteraPedido;
	
	private Cliente cliente;
	
	/* Preco Total dos produtos - sem frete/desconto */
	public String getPrecoTotalProduto() {
		return precoTotalProduto;
	}
	public void setPrecoTotalProduto(String precoTotalProduto) {
		this.precoTotalProduto = precoTotalProduto;
	}
	
	/* Preco do Frete*/
	public String getPrecoFrete() {
		return precoFrete;
	}
	public void setPrecoFrete(String precoFrete) {
		this.precoFrete = precoFrete;
	}
	
	
	/* Preco Total - com frete e desconto*/
	public String getPrecoTotal() {
		return precoTotal;
	}
	public void setPrecoTotal(String precoTotal) {
		this.precoTotal = precoTotal;
	}
	
	// STATUS DO PEDIDO = em processamento/ entregue/ cancelamento
    public String getStatusPedido() {
    	return statusPedido;
    }
    public void setStatusPedido(String statusPedido) {
    	this.statusPedido = statusPedido;
    }
	
    /* Id Cliente*/
	public String getIdCliente() {
		return id_cliente;
	}
	public void setIdCliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}
    
	/* Id Endereco */
	public String getIdEndereco() {
		return id_endereco;
	}
	public void setIdEndereco(String id_endereco) {
		this.id_endereco = id_endereco;
	}
	
	/* Cartao 1 Usado no Pedido*/
	public String getCartao1() {
		return cartao1;
	}
	public void setCartao1(String cartao1) {
		this.cartao1 = cartao1;
	}
	
	/* Valor do Cartao 1 usado no Pedido*/
	public String getValorCartao1() {
		return valorCartao1;
	}
	public void setValorCartao1(String valorCartao1) {
		this.valorCartao1 = valorCartao1;
	}
	
	/* Cartao 2 Usado no Pedido*/
	public String getCartao2() {
		return cartao2;
	}
	public void setCartao2(String cartao2) {
		this.cartao2 = cartao2;
	}
	
	/* Valor do Cartao 2 Usado no Pedido*/
	public String getValorCartao2() {
		return valorCartao2;
	}
	public void setValorCartao2(String valorCartao2) {
		this.valorCartao2 = valorCartao2;
	}
	
	/* Preco do Desconto - CUPOM*/
	public String getDesconto() {
		return desconto;
	}
	public void setDesconto(String desconto) {
		this.desconto = desconto;
	}
	
	/* Cliente */
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	

	/* Altera Pedido */
	public String getAlteraPedido() {
		return alteraPedido;
	}
	public void setAlteraPedido(String alteraPedido) {
		this.alteraPedido = alteraPedido;
	}
}