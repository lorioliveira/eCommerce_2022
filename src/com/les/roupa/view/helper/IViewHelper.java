package com.les.roupa.view.helper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Resultado;

/**
 * interface IViewHelper
 */
public interface IViewHelper {
	
	public EntidadeDominio getEntidade(HttpServletRequest request);
	
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
