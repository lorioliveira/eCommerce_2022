package com.les.roupa.core.dominio;

import java.util.List;

/**
 * Classe para representar os Itens do Pedido
 */
public class ItemPedido extends EntidadeDominio {
	private String id_pedido;
	private Produto produto;
	private String trocado;
	private List<Pedido> pedidos;
	private List<ItemPedido> itensPedido;
	private List<Endereco> enderecoPedido;
	
	public String getIdPedido() {
		return id_pedido;
	}
	public void setIdPedido(String id_pedido) {
		this.id_pedido = id_pedido;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public String getTrocado() {
		return trocado;
	}
	public void setTrocado(String trocado) {
		this.trocado = trocado;
	}
	
	public List<Pedido> getPedidos() {
        return pedidos;
    }
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }
    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }
    
    public List<Endereco> getEnderecoPedido() {
        return enderecoPedido;
    }
    public void setEnderecoPedido(List<Endereco> enderecoPedido) {
        this.enderecoPedido = enderecoPedido;
    }
}