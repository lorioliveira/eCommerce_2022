package com.les.roupa.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.GraficoAnalise;
import com.les.roupa.core.dominio.Produto;
import com.les.roupa.core.dominio.Resultado;
import com.les.roupa.view.helper.IViewHelper;

public class GraficoAnaliseHelper implements IViewHelper {

	GraficoAnalise grafico = null;
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
		String dtInicio = null;
		String dtFim = null;
		
		if (("CONSULTAR").equals(operacao)) {
			grafico = new GraficoAnalise();
			
			// capturando os valores do HTML e passando para o Gráfico
			dtInicio = request.getParameter("dtInicio");
			dtFim = request.getParameter("dtFim");
			
			// Atribuindo os valores capturados do HTML para o Gráfico
			grafico.setDtInicio(dtInicio);
			grafico.setDtFim(dtFim);
		}
		
		else if (("SALVAR").equals(operacao)) {

		}
		
		else if (("ALTERAR").equals(operacao)) {
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			
		}
		
		return grafico;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
		// Usa para escrever na tela
		PrintWriter writer = response.getWriter();
		
		if (("CONSULTAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// foi utilizado o getEntidades do resultado para poder pegar os dados do Grafico Anlise
				List<EntidadeDominio> entidades = resultado.getEntidades();
				// feito o CAST de Entidade para o GraficoAnalise (pegando o primeiro indice de Entidade)
				GraficoAnalise graficoAnaliseEntidade = (GraficoAnalise) entidades.get(0);
				
				// pendura o "dtInicio" na requisição, para poder mandar para o arquivo .JSP
				request.setAttribute("dtInicio", graficoAnaliseEntidade.getDtInicio());
				
				// pendura o "dtFim" na requisição, para poder mandar para o arquivo .JSP
				request.setAttribute("dtFim", graficoAnaliseEntidade.getDtFim());
				
				// pendura o "nomeProduto1" na requisição, para poder mandar para o arquivo .JSP
				request.setAttribute("nomeProduto1", graficoAnaliseEntidade.getNomeProduto1().get(0).getNome());
				
				// pendura o "nomeProduto2" na requisição, para poder mandar para o arquivo .JSP
				request.setAttribute("nomeProduto2", graficoAnaliseEntidade.getNomeProduto2().get(0).getNome());
				
				// pendura o "nomeProduto3" na requisição, para poder mandar para o arquivo .JSP
				request.setAttribute("nomeProduto3", graficoAnaliseEntidade.getNomeProduto3().get(0).getNome());
				
				// pendura o "totalColunas" na requisição, para poder mandar para o arquivo .JSP
				request.setAttribute("totalColunas", graficoAnaliseEntidade.getTotalColunas());
				
				// pendura o "totalValorProduto1" na requisição, para poder mandar para o arquivo .JSP
				request.setAttribute("totalValorProduto1", graficoAnaliseEntidade.getTotalValorProduto1());
				
				// pendura o "totalValorProduto2" na requisição, para poder mandar para o arquivo .JSP
				request.setAttribute("totalValorProduto2", graficoAnaliseEntidade.getTotalValorProduto2());
				
				// pendura o "totalValorProduto3" na requisição, para poder mandar para o arquivo .JSP
				request.setAttribute("totalValorProduto3", graficoAnaliseEntidade.getTotalValorProduto3());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/grafico2.jsp").forward(request, response);
			}
			else {
				// mostra as mensagens de ERRO se houver
				// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/indexAdm2.jsp").forward(request, response);
			}
		}
		
		else if (("SALVAR").equals(operacao)) {
			
		}
		
		else if (("ALTERAR").equals(operacao)) {
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {

		}
		
	}

}