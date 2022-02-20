package com.les.roupa.view.command;

import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Resultado;

/**
 * Interface ICommand
 */
public interface ICommand {
	
	public Resultado execute (EntidadeDominio entidade);
	
}
