package com.les.roupa.core.domain;

/**
 * Classe para representar uma Pessoa
 * @author Davi Rodrigues
 * @date 10/03/2021
 */
public class Pessoa extends EntidadeDominio {
	private String nome;
	private String cpf;
	private String dt_nasc;
	private String sexo;
	private Documento documento;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getDt_nasc() {
		return dt_nasc;
	}
	public void setDt_nasc(String dt_nasc) {
		this.dt_nasc = dt_nasc;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Documento getDocumento() {
        return documento;
    }
    public void setDocumento(Documento documento) {
        this.documento = documento;
    }
}
