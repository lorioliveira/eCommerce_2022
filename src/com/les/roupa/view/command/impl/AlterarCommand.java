package com.les.roupa.view.command.impl;

import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Resultado;

/**
 * Classe AlterarCommand
 */
public class AlterarCommand extends AbstractCommand {

	@Override
	public Resultado execute(EntidadeDominio entidade) {
		return fachada.alterar(entidade);
	}

}
