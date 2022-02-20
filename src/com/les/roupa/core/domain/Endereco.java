package com.les.roupa.core.domain;

/**
 * Classe para representar o Endereço
 * @author Davi Rodrigues
 * @date 18/08/2021
 */
public class Endereco extends EntidadeDominio {
	private String logradouro;
	private String numero;
	private String bairro;
	private String cep;
	private String cidade;
	private String estado;
	private String complemento;
	private String id_cliente;
//	private Cidade cidade;
	private TipoEndereco tipoEndereco;
	private String alteraEndereco;
	private String apelido;
	private String observacao;
	private String tipo_endereco;
	private String tipo_residencia;
	private String pais;
	
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getIdCliente() {
		return id_cliente;
	}
	public void setIdCliente(String idCliente) {
		this.id_cliente = idCliente;
	}
//	public Cidade getCidade() {
//        return cidade;
//    }
//    public void setCidade(Cidade cidade) {
//        this.cidade = cidade;
//    }
    public TipoEndereco getTipoEndereco() {
        return tipoEndereco;
    }
    public void setTipoEndereco(TipoEndereco tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }
    public String getAlteraEndereco() {
		return alteraEndereco;
	}
	public void setAlteraEndereco(String alteraEndereco) {
		this.alteraEndereco = alteraEndereco;
	}
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getTipo_Endereco() {
		return tipo_endereco;
	}
	public void setTipo_Endereco(String tipo_endereco) {
		this.tipo_endereco = tipo_endereco;
	}
	public String getTipoResidencia() {
		return tipo_residencia;
	}
	public void setTipoResidencia(String tipo_residencia) {
		this.tipo_residencia = tipo_residencia;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
}
