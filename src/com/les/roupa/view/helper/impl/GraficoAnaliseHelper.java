package com.les.roupa.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.les.roupa.core.dao.impl.GraficoAnaliseDAO;
import com.les.roupa.core.dao.impl.ProdutoDAO;
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
				String dtInicio = request.getParameter("dtInicio");
				String dtFim = request.getParameter("dtFim");
				
				GraficoAnaliseDAO graficoDAO = new GraficoAnaliseDAO();
				ProdutoDAO produtoDAO = new ProdutoDAO();

				// busca os 3 produtos mais vendidos, conforme as datas selecionadas na tela como parametros
				List<GraficoAnalise> produtosMaisVendidos = graficoDAO.consultar3ProdutosMaisVendidos(dtInicio, dtFim);
				
				List<Produto> nomeProduto1 = new ArrayList<>();
				List<Produto> nomeProduto2 = new ArrayList<>();
				List<Produto> nomeProduto3 = new ArrayList<>();
				List<String> totalColunas = new ArrayList<>();
				List<String> totalValorProduto1 = new ArrayList<>();
				List<String> totalValorProduto2 = new ArrayList<>();
				List<String> totalValorProduto3 = new ArrayList<>();
				
				Produto produtoVazio = new Produto();
				produtoVazio.setNome("VAZIO");
				produtoVazio.setQuantidadeSelecionada("0");
				
				// separa o dia-mês-ano que foi selecionado na tela,
				// para poder criar as colunas (eixo X) na criação do gráfico Chart.js
				String[] resultInicio  = dtInicio.split("-");
				String[] resultFim  = dtFim.split("-");
				
				// data inicio
				String anoInicio = resultInicio[0];
				String mesInicio = resultInicio[1];
				// data fim
				String anoFim = resultFim[0];
				String mesFim = resultFim[1];
				
				int condicaoDeParada = 0;
				int auxAnoInicio = Integer.parseInt(anoInicio);
				int auxMesInicio = Integer.parseInt(mesInicio);
				
				//
				// preenchimento dos nomes dos Produtos do Gráfico Chart.js
				//
				if (produtosMaisVendidos.size() == 0) {
					// adição 3 Produtos VAZIO's, (nome dos produtos que serão usados no Gráfico)
					nomeProduto1.add(produtoVazio);
					nomeProduto2.add(produtoVazio);
					nomeProduto3.add(produtoVazio);
				}
				else if (produtosMaisVendidos.size() == 1) {
					// adiciona somente 2 Produtos VAZIO's, (nomes dos produtos que serão usados no Gráfico)
					nomeProduto1 = produtoDAO.consultarProdutoById(produtosMaisVendidos.get(0).getItemPedido().getProduto().getId());
					nomeProduto2.add(produtoVazio);
					nomeProduto3.add(produtoVazio);
				}
				else if (produtosMaisVendidos.size() == 2) {
					// adiciona somente 1 Produto VAZIO, (nomes dos produtos que serão usados no Gráfico)
					nomeProduto1 = produtoDAO.consultarProdutoById(produtosMaisVendidos.get(0).getItemPedido().getProduto().getId());
					nomeProduto2 = produtoDAO.consultarProdutoById(produtosMaisVendidos.get(1).getItemPedido().getProduto().getId());
					nomeProduto3.add(produtoVazio);
				}
				else {
					// busca os nomes dos produtos, conforme a consulta realizada acima (3 produtos mais vendidos)
					nomeProduto1 = produtoDAO.consultarProdutoById(produtosMaisVendidos.get(0).getItemPedido().getProduto().getId());
					nomeProduto2 = produtoDAO.consultarProdutoById(produtosMaisVendidos.get(1).getItemPedido().getProduto().getId());
					nomeProduto3 = produtoDAO.consultarProdutoById(produtosMaisVendidos.get(2).getItemPedido().getProduto().getId());
				}
				// fim do preenchimento dos nomes dos Produtos do Gráfico Chart.js
				//
				
				// CRIAR COLUNAS (DATAS)
					
				// laço de repetição para criar as colunas do Gráfico Chart.js
				//
				for(int i = 0; condicaoDeParada == 0; i++) {
					// salva o mes e o ano em um array de String,
					// que será mandado para a tela (grafico_chart_2.jsp)
					totalColunas.add(Integer.toString(auxMesInicio) + "/" + Integer.toString(auxAnoInicio));
					
					// o ano inicio é igual a ano fim?
					// E
					// o mes inicio é igual a mes fim?
					// então para o laço de repetição
					if (auxAnoInicio == Integer.parseInt(anoFim) && auxMesInicio == Integer.parseInt(mesFim)) {
						break;
					}
					// se não, continua com as verificações
					else {
						// o mes data inicio ja esta em dezembro?
						if (auxMesInicio == 12) {
							// o mes retorna para janeiro
							auxMesInicio = 1;
							// acrescenta mais 1 no ano inicio
							auxAnoInicio ++;
						}
						// se não, acrescenta mais 1 no mes inicio
						else {
							auxMesInicio ++;
						}
					}
				}
				//
				// fim criação das colunas do Gráfico Chart.js
				//
				
				//
				// laço para preencher os valores de cada produto,
				// conforme a coluna criada a cima
				//
				for(String coluna : totalColunas) {
					// separa o mês-ano que esta na coluna,
					// para poder passar para a DAO e devolver algum valor,
					// conforme o mes, ano e o ID do produto
					String[] resultColuna  = coluna.split("/");
					
					// mes e ano da coluna
					String mesColuna = resultColuna[0];
					String anoColuna = resultColuna[1];
					
					// verifica as quantidades dos produtos mais vendidos, retornados do banco de dados,
					// se a lista dos produtos for menor que 3, será adicionado o valor ZERO no indice que não tem o Produto
					if (produtosMaisVendidos.size() == 0) {						
						// adição ZERO's na lista dos valores do produto
						totalValorProduto1.add("0");
						totalValorProduto2.add("0");
						totalValorProduto3.add("0");
					}
					else if (produtosMaisVendidos.size() == 1) {						
						// busca os valores do produto vendido, conforme o id produto, mes e ano como parametros
						List<GraficoAnalise> qtdeProdutoMesAno1 = graficoDAO.consultarQtdeProdutosVendidosByIdProdutoMesAno(produtosMaisVendidos.get(0).getItemPedido().getProduto().getId(), mesColuna, anoColuna);
						
						// verifica se naquele mes teve alguma qtde do produto 1 vendido,
						// se não tiver nada, será adicionado ZERO para não quebrar a tela
						if (qtdeProdutoMesAno1.size() == 0) {
							totalValorProduto1.add("0");
						}
						else {
							// salva a qtde do produto 1 vendido por mes e ano em um array de String,
							// que será mandado para a tela (grafico_chart_2.jsp)
							totalValorProduto1.add(qtdeProdutoMesAno1.get(0).getItemPedido().getQtdeProduto());
						}
						
						// adiciona somente 2 ZERO's na lista dos valores do produto
						totalValorProduto2.add("0");
						totalValorProduto3.add("0");
					}
					else if (produtosMaisVendidos.size() == 2) {						
						// busca os valores do produto vendido, conforme o id produto, mes e ano como parametros
						List<GraficoAnalise> qtdeProdutoMesAno1 = graficoDAO.consultarQtdeProdutosVendidosByIdProdutoMesAno(produtosMaisVendidos.get(0).getItemPedido().getProduto().getId(), mesColuna, anoColuna);
						List<GraficoAnalise> qtdeProdutoMesAno2 = graficoDAO.consultarQtdeProdutosVendidosByIdProdutoMesAno(produtosMaisVendidos.get(1).getItemPedido().getProduto().getId(), mesColuna, anoColuna);
						
						// verifica se naquele mes teve alguma qtde do produto 1 vendido,
						// se não tiver nada, será adicionado ZERO para não quebrar a tela
						if (qtdeProdutoMesAno1.size() == 0) {
							totalValorProduto1.add("0");
						}
						else {
							// salva a qtde do produto 1 vendido por mes e ano em um array de String,
							// que será mandado para a tela (grafico_chart_2.jsp)
							totalValorProduto1.add(qtdeProdutoMesAno1.get(0).getItemPedido().getQtdeProduto());
						}
						// verifica se naquele mes teve alguma qtde do produto 2 vendido,
						// se não tiver nada, será adicionado ZERO para não quebrar a tela
						if (qtdeProdutoMesAno2.size() == 0) {
							totalValorProduto2.add("0");
						}
						else {
							// salva a qtde do produto 2 vendido por mes e ano em um array de String,
							// que será mandado para a tela (grafico_chart_2.jsp)
							totalValorProduto2.add(qtdeProdutoMesAno2.get(0).getItemPedido().getQtdeProduto());
						}
						
						// adiciona somente 1 ZERO na lista dos valores do produto
						totalValorProduto3.add("0");
					}
					// se o banco retornar 3 produtos
					else {						
						// busca os valores do produto vendido, conforme o id produto, mes e ano como parametros
						List<GraficoAnalise> qtdeProdutoMesAno1 = graficoDAO.consultarQtdeProdutosVendidosByIdProdutoMesAno(produtosMaisVendidos.get(0).getItemPedido().getProduto().getId(), mesColuna, anoColuna);
						List<GraficoAnalise> qtdeProdutoMesAno2 = graficoDAO.consultarQtdeProdutosVendidosByIdProdutoMesAno(produtosMaisVendidos.get(1).getItemPedido().getProduto().getId(), mesColuna, anoColuna);
						List<GraficoAnalise> qtdeProdutoMesAno3 = graficoDAO.consultarQtdeProdutosVendidosByIdProdutoMesAno(produtosMaisVendidos.get(2).getItemPedido().getProduto().getId(), mesColuna, anoColuna);
						
						// verifica se naquele mes teve alguma qtde do produto 1 vendido,
						// se não tiver nada, será adicionado ZERO para não quebrar a tela
						if (qtdeProdutoMesAno1.size() == 0) {
							totalValorProduto1.add("0");
						}
						else {
							// salva a qtde do produto 1 vendido por mes e ano em um array de String,
							// que será mandado para a tela (grafico_chart_2.jsp)
							totalValorProduto1.add(qtdeProdutoMesAno1.get(0).getItemPedido().getQtdeProduto());
						}
						// verifica se naquele mes teve alguma qtde do produto 2 vendido,
						// se não tiver nada, será adicionado ZERO para não quebrar a tela
						if (qtdeProdutoMesAno2.size() == 0) {
							totalValorProduto2.add("0");
						}
						else {
							// salva a qtde do produto 2 vendido por mes e ano em um array de String,
							// que será mandado para a tela (grafico_chart_2.jsp)
							totalValorProduto2.add(qtdeProdutoMesAno2.get(0).getItemPedido().getQtdeProduto());
						}
						// verifica se naquele mes teve alguma qtde do produto 3 vendido,
						// se não tiver nada, será adicionado ZERO para não quebrar a tela
						if (qtdeProdutoMesAno3.size() == 0) {
							totalValorProduto3.add("0");
						}
						else {
							// salva a qtde do produto 3 vendido por mes e ano em um array de String,
							// que será mandado para a tela (grafico_chart_2.jsp)
							totalValorProduto3.add(qtdeProdutoMesAno3.get(0).getItemPedido().getQtdeProduto());
						}
					}
				}
				//
				// fim do preenchimento dos valores de cada produto (conforme a coluna criada a cima)
				//
				
				// pendura o "dtInicio" na requisição, para poder mandar para o arquivo .JSP
				request.setAttribute("dtInicio", dtInicio);
				
				// pendura o "dtFim" na requisição, para poder mandar para o arquivo .JSP
				request.setAttribute("dtFim", dtFim);
				
				// pendura o "nomeProduto1" na requisição, para poder mandar para o arquivo .JSP
				request.setAttribute("nomeProduto1", nomeProduto1.get(0).getNome());
				
				// pendura o "nomeProduto2" na requisição, para poder mandar para o arquivo .JSP
				request.setAttribute("nomeProduto2", nomeProduto2.get(0).getNome());
				
				// pendura o "nomeProduto3" na requisição, para poder mandar para o arquivo .JSP
				request.setAttribute("nomeProduto3", nomeProduto3.get(0).getNome());
				
				// pendura o "totalColunas" na requisição, para poder mandar para o arquivo .JSP
				request.setAttribute("totalColunas", totalColunas);
				
				// pendura o "totalValorProduto1" na requisição, para poder mandar para o arquivo .JSP
				request.setAttribute("totalValorProduto1", totalValorProduto1);
				
				// pendura o "totalValorProduto2" na requisição, para poder mandar para o arquivo .JSP
				request.setAttribute("totalValorProduto2", totalValorProduto2);
				
				// pendura o "totalValorProduto3" na requisição, para poder mandar para o arquivo .JSP
				request.setAttribute("totalValorProduto3", totalValorProduto3);
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/grafico2.jsp").forward(request, response);
			}
			else {
				// mostra as mensagens de ERRO se houver
				// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/admin.jsp").forward(request, response);
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
