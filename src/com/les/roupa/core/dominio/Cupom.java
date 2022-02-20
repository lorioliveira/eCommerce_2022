package com.les.roupa.core.dominio;

/**
 * Classe para representar o Cupom
 * @author Lorena Oliveira
 */
public class Cupom extends EntidadeDominio {
	private String nome;
	private String tipo;
	private String valor;
	private String id_cliente;
	private String utilizado;
	
	private String alteraCupom;
	
		
	/* Nome */
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/* Tipo de Cupom */
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	/* Valor*/ 
    public String getValor() {
    	return valor;
    }
    public void setValor(String valor) {
    	this.valor = valor;
    }
        
    /* Id Cliente */
	public String getIdCliente() {
		return id_cliente;
	}
	public void setIdCliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}
	
	public String getUtilizado() {
		return utilizado;
	}
	public void setUtilizado(String utilizado) {
		this.utilizado = utilizado;
	}


	/* Altera Cupm */
	public String getAlteraCupom() {
		return alteraCupom;
	}
	public void setAlteraCupom(String alteraCupom) {
		this.alteraCupom = alteraCupom;
	}
	
}
