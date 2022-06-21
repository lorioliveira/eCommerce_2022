package com.les.roupa.core.strategy.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.GraficoAnalise;
import com.les.roupa.core.strategy.IStrategy;

/**
 * Classe para validar as datas do Gráfico da Análise

 */
public class ValidarDatasGraficoAnalise implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		GraficoAnalise grafico = (GraficoAnalise) entidade;
		
		// pega a data atual
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String dataAtual;
		dataAtual = dateFormat.format(date);
		
		if(grafico.getDtInicio() == null || grafico.getDtInicio().equals("")) {
			return ("Insira uma Data de Inicio.<br>");
		}
		else if(grafico.getDtFim() == null || grafico.getDtFim().equals("")) {
			return ("Insira uma Data de Fim.<br>");
		}
		else {
			try {
				// CAST de String para Date
				Date dateInicio = dateFormat.parse(grafico.getDtInicio());
				Date dateFim = dateFormat.parse(grafico.getDtFim());
				Date dateAtual = dateFormat.parse(dataAtual);
				
				// data inicio vem depois (maior) que data atual?
				if(dateInicio.after(dateAtual)) {
					return ("Insira uma Data de Inicio menor ou igual a Data Atual.<br>");
				}
				// data fim vem depois (maior) que data atual?
				else if(dateFim.after(dateAtual)) {
					return ("Insira uma Data de Fim menor ou igual a Data Atual.<br>");
				}
				// data inicio vem depois (maior) que data fim?
				else if(dateInicio.after(dateFim)) {
					return ("Insira uma Data de Inicio menor do que a Data de Fim.<br>");
				}
				else {
					return null;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ("Erro de Exception nas datas do gráfico! Verifique. <br>");
			}
		}
	}

}