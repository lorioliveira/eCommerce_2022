package com.les.roupa.core.dominio;

import java.util.List;

/**
 * Classe para representar o Endereco - Cliente
 */
public class Endereco extends EntidadeDominio {
	public String logradouro;
	private String numero;
	private String bairro;
	private String cep;
	private String cidade;
	private String estado;
	private String pais;
	private String tipoResidencia;
	private String observacoes;
	private String tipoEndereco;
	private String idCliente;
	private String status;
	
	private String alteraEndereco;
	
	private List<Endereco> todosEnderecos;
	
	
//	LOGRADOURO
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	// NUMERO
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	//BAIRRO
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	//CEP
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	//CIDADE
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	//ESTADO
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	// PAIS
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	//TIPO ENDERECO
	public String getTipoResidencia() {
        return tipoResidencia;
    }
    public void setTipoResidencia(String tipoResidencia) {
        this.tipoResidencia = tipoResidencia;
    }
    
    // OBS
    public String getObservacoes() {
    	return observacoes;
    }
    public void setObservacoes(String observacoes) {
    	this.observacoes = observacoes;
    }
    
 // TIPO DE ENDERECO - COBRANCA/ENTREGA
    public String getTipoEnd() {
    	return tipoEndereco;
    }
    public void setTipoEnd(String tipoEndereco) {
    	this.tipoEndereco = tipoEndereco;
    }
    
  //ID 
  	public String getIdCliente() {
  		return idCliente;
  	}
  	public void setIdCliente(String idCliente) {
  		this.idCliente = idCliente;
  	}
    
 // STATUS = ATIVO OU INATIVO
    public String getStatus() {
    	return status;
    }
    public void setStatus(String status) {
    	this.status = status;
    }
    
    /* ALTERA ENDERECO */
	public String getAlteraEndereco() {
		return alteraEndereco;
	}
	public void setAlteraEndereco(String alteraEndereco) {
		this.alteraEndereco = alteraEndereco;
	}
	
	/* ENDERECOS DO CLIENTE */
	public List<Endereco> getTodosEnderecos() {
		return todosEnderecos;
	}

	public void setTodosEnderecos(List<Endereco> todosEnderecos) {
		this.todosEnderecos = todosEnderecos;
	}
}
