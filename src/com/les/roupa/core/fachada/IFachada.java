package com.les.roupa.core.fachada;

import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Resultado;

/**
 * Interface IFachada
 */
public interface IFachada {
	
	public Resultado salvar(EntidadeDominio entidade);
    public Resultado alterar(EntidadeDominio entidade);
    public Resultado excluir(EntidadeDominio entidade);
    public Resultado consultar(EntidadeDominio entidade);
	
}
