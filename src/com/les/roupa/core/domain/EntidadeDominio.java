package com.les.roupa.core.domain;

import java.util.Date;

/**
 * Classe entidade dominio
 * @author Davi Rodrigues
 * @date 09/08/2021
 */
public class EntidadeDominio implements IEntidade {
	private String id;
	private String dtCadastro;
	//private Date dtCadastro;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDtCadastro() {
		return dtCadastro;
	}
	public void setDtCadastro(String dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
//	public Date getDtCadastro() {
//		return dtCadastro;
//	}
//	public void setDtCadastro(Date dtCadastro) {
//		this.dtCadastro = dtCadastro;
//	}
}
