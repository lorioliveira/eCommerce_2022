package com.les.roupa.core.domain;

/**
 * Classe para representar a verificação do Cupom
 * @author Davi Rodrigues
 * @date 04/09/2021
 */
public class VerificaCupom extends EntidadeDominio {
	private Cupom cupom;
	private String id_cliente;
	
	public Cupom getCupom() {
        return cupom;
    }
    public void setCupom(Cupom cupom) {
        this.cupom = cupom;
    }
    public String getIdCliente() {
		return id_cliente;
	}
	public void setIdCliente(String idCliente) {
		this.id_cliente = idCliente;
	}
}
