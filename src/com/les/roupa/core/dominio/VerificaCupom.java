package com.les.roupa.core.dominio;

import java.util.List;

/**
 * Classe para representar a verificação do Cupom
 */
public class VerificaCupom extends EntidadeDominio {
	private Cupom cupom;
	private String id_cliente;
	private List<Cupom> nomeCupons;
	
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
	
	public List<Cupom> getNomeCupons() {
        return nomeCupons;
    }
    public void setNomeCupons(List<Cupom> nomeCupons) {
        this.nomeCupons = nomeCupons;
    }
}