package com.les.roupa.view.command.impl;

import com.les.roupa.core.fachada.IFachada;
import com.les.roupa.core.fachada.impl.Fachada;
import com.les.roupa.view.command.ICommand;

/**
 * Classe abstrata AbstractCommand,
 * para instanciar a Fachada.
 */
public abstract class AbstractCommand implements ICommand {
	
	protected IFachada fachada = new Fachada();
	
}
