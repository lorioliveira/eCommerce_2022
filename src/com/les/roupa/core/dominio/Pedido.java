package com.les.roupa.core.dominio;

import java.util.List;

/**
 * Classe para representar o Pedido
 */
public class Pedido extends EntidadeDominio{
	private String total_itens;
	private String total_frete;
	private String total_pedido;
	private String status;
	private String id_cliente;
	private String id_endereco;
	private String forma_pagamento;
	private String id_cartao_1;
	private String valor_cartao_1;
	private String id_cartao_2;
	private String valor_cartao_2;
	private String total_cupons;
	private String trocado;
	private String todosItensTrocado;
	private List<Produto> produtos;
	private List<Cupom> cupons;
	private String id_cliente_consulta;
	private List<Pedido> pedidosCliente;
	private String nome_cliente;
	private String dar_baixa_estoque;
	
	private Endereco endereco;
	
	private Pedido pedidoSelecionado;
	
	
	public String getTotalItens() {
		return total_itens;
	}
	public void setTotalItens(String total_itens) {
		this.total_itens = total_itens;
	}
	public String getTotalFrete() {
		return total_frete;
	}
	public void setTotalFrete(String total_frete) {
		this.total_frete = total_frete;
	}
	public String getTotalPedido() {
		return total_pedido;
	}
	public void setTotalPedido(String total_pedido) {
		this.total_pedido = total_pedido;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIdCliente() {
		return id_cliente;
	}
	public void setIdCliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}
	public String getIdEndereco() {
		return id_endereco;
	}
	public void setIdEndereco(String id_endereco) {
		this.id_endereco = id_endereco;
	}
	public String getFormaPagamento() {
		return forma_pagamento;
	}
	public void setFormaPagamento(String forma_pagamento) {
		this.forma_pagamento = forma_pagamento;
	}
	public String getIdCartao1() {
		return id_cartao_1;
	}
	public void setIdCartao1(String id_cartao_1) {
		this.id_cartao_1 = id_cartao_1;
	}
	public String getValorCartao1() {
		return valor_cartao_1;
	}
	public void setValorCartao1(String valor_cartao_1) {
		this.valor_cartao_1 = valor_cartao_1;
	}
	public String getIdCartao2() {
		return id_cartao_2;
	}
	public void setIdCartao2(String id_cartao_2) {
		this.id_cartao_2 = id_cartao_2;
	}
	public String getValorCartao2() {
		return valor_cartao_2;
	}
	public void setValorCartao2(String valor_cartao_2) {
		this.valor_cartao_2 = valor_cartao_2;
	}
	public String getTotalCupons() {
		return total_cupons;
	}
	public void setTotalCupons(String total_cupons) {
		this.total_cupons = total_cupons;
	}
	public String getTrocado() {
		return trocado;
	}
	public void setTrocado(String trocado) {
		this.trocado = trocado;
	}
	public String getTodosItensTrocado() {
		return todosItensTrocado;
	}
	public void setTodosItensTrocado(String todosItensTrocado) {
		this.todosItensTrocado = todosItensTrocado;
	}
	
	public List<Produto> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
	public List<Cupom> getCupons() {
        return cupons;
    }
    public void setCupons(List<Cupom> cupons) {
        this.cupons = cupons;
    }
    
    public String getIdClienteConsulta() {
		return id_cliente_consulta;
	}
	public void setIdClienteConsulta(String id_cliente_consulta) {
		this.id_cliente_consulta = id_cliente_consulta;
	}
	
	public List<Pedido> getPedidosCliente() {
        return pedidosCliente;
    }
    public void setPedidosCliente(List<Pedido> pedidosCliente) {
        this.pedidosCliente = pedidosCliente;
    }
    
    public String getNomeCliente() {
		return nome_cliente;
	}
	public void setNomeCliente(String nome_cliente) {
		this.nome_cliente = nome_cliente;
	}
	
	public String getDarBaixaEstoque() {
		return dar_baixa_estoque;
	}
	public void setDarBaixaEstoque(String dar_baixa_estoque) {
		this.dar_baixa_estoque = dar_baixa_estoque;
	}

	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	// Metodo
	public void ValidarPrimieraCompra() {
		
	}
}