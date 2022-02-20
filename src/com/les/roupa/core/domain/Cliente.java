package com.les.roupa.core.domain;

/**
 * Classe para representar o Cliente
 * @author Davi Rodrigues
 * @date 15/08/2021
 */
public class Cliente extends Usuario {
	private String status;
	private String cd_sistema;
	private String telefone;
	private Usuario usuario;
	private String tipo;
	private Endereco endereco;
	private String alteraCliente;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCdSistema() {
		return cd_sistema;
	}
	public void setCdSistema(String cd_sistema) {
		this.cd_sistema = cd_sistema;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public String getAlteraCliente() {
		return alteraCliente;
	}
	public void setAlteraCliente(String alteraCliente) {
		this.alteraCliente = alteraCliente;
	}
}
