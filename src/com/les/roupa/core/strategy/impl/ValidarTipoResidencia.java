package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;

/**
 * Classe para validar o campo Tipo Residencia do Endereco
 * 
 */

public class ValidarTipoResidencia implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Endereco endereco = (Endereco) entidade;
		
			if(endereco.getTipoResidencia() == null || endereco.getTipoResidencia().equals("")) {
				return ((" Selecione um tipo de residência para o endereço a cadastrar. <br>"));
			}
			else {
				return null;
			}
	}

}