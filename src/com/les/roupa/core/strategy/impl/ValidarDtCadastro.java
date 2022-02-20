package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.les.roupa.core.dominio.EntidadeDominio;

/**
 * Classe para validar o campo data de cadastro do Produto
 */
public class ValidarDtCadastro implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		// seta a data atual 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		String dataAtual;
		
		dataAtual = dateFormat.format(date);
		
		entidade.setData_Cadastro(dataAtual);
		
		return null;
	}

}