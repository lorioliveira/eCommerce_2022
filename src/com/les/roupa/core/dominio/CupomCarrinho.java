package com.les.roupa.core.dominio;

/**
 * Classe para representar o Cupom
 * @author Lorena Oliveira
 */
public class CupomCarrinho extends EntidadeDominio {
	private String status;
	private String valor;
	private String id_cupom;
	private String id_cliente;
	
	
	/* Valor*/ 
    public String getValor() {
    	return valor;
    }
    public void setValor(String valor) {
    	this.valor = valor;
    }
        
    /* Id Cupom */
	public String getIdCupom() {
		return id_cupom;
	}
	public void setIdCupom(String id_cupom) {
		this.id_cupom = id_cupom;
	}
	
	/* Status */
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	/* Id Cliente */
	public String getIdCliente() {
		return id_cliente;
	}
	public void setIdCliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}

	
}
