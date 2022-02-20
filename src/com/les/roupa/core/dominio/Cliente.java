package com.les.roupa.core.dominio;

/**
 * Classe para representar o Cliente
 */
public class Cliente extends Pessoa {
	private String telefone;
	private Usuario usuario;
	private String dt_nasc;
	private String status;
	private String tipo;
	
	
	private String alteraCliente;
	
	
	/* Telefone */
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	/* Dt Nascimento */
	public String getDt_nasc() {
		return dt_nasc;
	}
	public void setDt_nasc(String dt_nasc) {
		this.dt_nasc = dt_nasc;
	}
		
	/* Usuário */
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	// STATUS = ATIVO OU INATIVO
    public String getStatus() {
    	return status;
    }
    public void setStatus(String status) {
    	this.status = status;
    }
	
	/* Altera Cliente */
	public String getAlteraCliente() {
		return alteraCliente;
	}
	public void setAlteraCliente(String alteraCliente) {
		this.alteraCliente = alteraCliente;
	}
	
	/* Tipo - Cliente/ Admin */
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
