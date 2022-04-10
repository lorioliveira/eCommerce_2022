package com.les.roupa.core.dominio;

import java.util.List;

/**
 * Classe para representar a Troca do Pedido ou dos Itens do Pedido
 */
public class PedidoTroca extends EntidadeDominio {
	private ItemPedido itemPedido;
	private String idPedido;
	private String idItemPedido;
	private List<Pedido> pedidos;
	private List<ItemPedido> itemPedidoSelecionado;
	private List<Endereco> enderecoPedido;
	private List<ItemPedido> todosItensPedido;
	private List<PedidoTroca> itensPedidoTroca;
	private String trocaPedidoInteiro;
	private String idCliente;
	private String novoStatusPedido;
	private String totalPedido;
	private Pedido pedido;
	
	public ItemPedido getItemPedido() {
		return itemPedido;
	}
	public void setItemPedido(ItemPedido itemPedido) {
		this.itemPedido = itemPedido;
	}
	
	public String getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(String idPedido) {
		this.idPedido = idPedido;
	}
	
	public String getIdItemPedido() {
		return idItemPedido;
	}
	public void setIdItemPedido(String idItemPedido) {
		this.idItemPedido = idItemPedido;
	}
	
	public List<Pedido> getPedidos() {
        return pedidos;
    }
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
	public List<ItemPedido> getItemPedidoSelecionado() {
        return itemPedidoSelecionado;
    }
    public void setItemPedidoSelecionado(List<ItemPedido> itemPedidoSelecionado) {
        this.itemPedidoSelecionado = itemPedidoSelecionado;
    }
    
	public List<Endereco> getEnderecoPedido() {
        return enderecoPedido;
    }
    public void setEnderecoPedido(List<Endereco> enderecoPedido) {
        this.enderecoPedido = enderecoPedido;
    }
    
    public List<ItemPedido> getTodosItensPedido() {
        return todosItensPedido;
    }
    public void setTodosItensPedido(List<ItemPedido> todosItensPedido) {
        this.todosItensPedido = todosItensPedido;
    }
    
    public List<PedidoTroca> getItensPedidoTroca() {
        return itensPedidoTroca;
    }
    public void setItensPedidoTroca(List<PedidoTroca> itensPedidoTroca) {
        this.itensPedidoTroca = itensPedidoTroca;
    }
    
	public String getTrocaPedidoInteiro() {
		return trocaPedidoInteiro;
	}
	public void setTrocaPedidoInteiro(String trocaPedidoInteiro) {
		this.trocaPedidoInteiro = trocaPedidoInteiro;
	}
	
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	
	public String getNovoStatusPedido() {
		return novoStatusPedido;
	}
	public void setNovoStatusPedido(String novoStatusPedido) {
		this.novoStatusPedido = novoStatusPedido;
	}
	
	public String getTotalPedido() {
		return totalPedido;
	}
	public void setTotalPedido(String totalPedido) {
		this.totalPedido = totalPedido;
	}
	
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
}