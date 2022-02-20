package com.les.roupa.view.command.impl;

import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Resultado;

/**
 * Classe ConsultarCommand
 */
public class ConsultarCommand extends AbstractCommand {

	@Override
	public Resultado execute(EntidadeDominio entidade) {
		return fachada.consultar(entidade);
	}
	
}
