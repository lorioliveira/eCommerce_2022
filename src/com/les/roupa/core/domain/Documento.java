package com.les.roupa.core.domain;

import java.util.Date;

/**
 * Classe para representar o Documento
 * @author Davi Rodrigues
 * @date 10/03/2021
 */
public class Documento extends EntidadeDominio {
	private String codigo;
	private Date validade;
	private TipoDocumento tipoDocumento;

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Date getValidade() {
		return validade;
	}
	public void setValidade(Date validade) {
		this.validade = validade;
	}
	public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }
    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
}
