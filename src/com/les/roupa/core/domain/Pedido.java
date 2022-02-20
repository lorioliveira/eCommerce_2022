package com.les.roupa.core.domain;

/**
 * Classe para representar o Pedido
 * @author Davi Rodrigues
 * @date 17/10/2021
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
	
	// Metodo
	public void ValidarPrimieraCompra() {
		
	}
}