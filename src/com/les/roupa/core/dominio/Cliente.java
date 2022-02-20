package com.les.roupa.core.dominio;

/**
 * Classe para representar o Cliente -
 * @author Lorena Oliveira
 * 
 */
public class Cliente extends Pessoa {
	private String telefone;
	private Usuario usuario;
	private String data_Nascimento;
	private String status;
	private String tipoCliente;
	
	private String alteraCliente;
	
	
	
	/* Telefone */
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	/* Usuário */
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	/* Data Nascimento */
	public String getData_Nascimento() {
		return data_Nascimento;
	}
	public void setData_Nascimento(String data_Nascimento) {
		this.data_Nascimento = data_Nascimento;
	}
	
	// STATUS = ATIVO OU INATIVO
    public String getStatus() {
    	return status;
    }
    public void setStatus(String status) {
    	this.status = status;
    }
	
    /* Tipo - Cliente/ Admin */
	public String getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
    
	/* Altera Cliente */
	public String getAlteraCliente() {
		return alteraCliente;
	}
	public void setAlteraCliente(String alteraCliente) {
		this.alteraCliente = alteraCliente;
	}
}
