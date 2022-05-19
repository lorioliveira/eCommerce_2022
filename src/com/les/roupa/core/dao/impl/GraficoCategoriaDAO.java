package com.les.roupa.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.GraficoCategoria;
import com.les.roupa.core.dominio.GraficoCategoria;
import com.les.roupa.core.dominio.Produto;

/**
 * DAO - GR�FICO (An�lise)
 * @author Lorena Oliveira
 *
 */
public class GraficoCategoriaDAO extends AbstractJdbcDAO {
	
	/**
	 * M�todo para salvar o Gr�fico da An�lise
	 * @param entidade
	 */
	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Salvar
	
	
	/**
	 * M�todo para alterar o Gr�fico da An�lise
	 * @param entidade
	 */
	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Alterar
	
	
	/**
	 * M�todo para excluir o Gr�fico da An�lise
	 * @param entidade
	 */
	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Excluir
	
	
	/**
	 * M�todo para Consultar o Gr�fico da An�lise - (com verifica��es de datas)
	 * @param entidade
	 */
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		GraficoCategoria graficoCategoriaEntidade = (GraficoCategoria) entidade;
		GraficoCategoria novoGraficoCategoria= new GraficoCategoria();
		List<EntidadeDominio> ListGraficoCategoria = new ArrayList<>();
		
		GraficoCategoriaDAO graficoDAO = new GraficoCategoriaDAO();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		
		// busca os 3 produtos mais vendidos, conforme as datas selecionadas na tela como parametros
		// no indexAdm.jsp
		List<GraficoCategoria> produtosMaisVendidos = graficoDAO.consultar3ProdutosMaisVendidos(graficoCategoriaEntidade.getDtInicio(), graficoCategoriaEntidade.getDtFim());
		
		// Cria��o de listas a ser utilizadas
		List<Produto> nomeProduto1 = new ArrayList<>();
		List<Produto> nomeProduto2 = new ArrayList<>();
		List<Produto> nomeProduto3 = new ArrayList<>();
		List<String> totalColunas = new ArrayList<>();
		List<String> totalValorProduto1 = new ArrayList<>();
		List<String> totalValorProduto2 = new ArrayList<>();
		List<String> totalValorProduto3 = new ArrayList<>();
		
		// Criando e definindo variavel para produto VAZIO e ZERO quantidade,
		// cao tenha algum produto com quantidade 0,
		// para n�o quebrar o gr�fico
		Produto produtoVazio = new Produto();
		produtoVazio.setNome("VAZIO");
		produtoVazio.setQuantidadeSelecionada("0");
		
		// separa o dia-m�s-ano que foi selecionado na tela,
		// para poder criar as colunas no gr�fico do arquivo .js - apenas usando MES e ANO
		String[] resultInicio  = graficoCategoriaEntidade.getDtInicio().split("-");
		String[] resultFim  = graficoCategoriaEntidade.getDtFim().split("-");
		
		// data inicio
		String anoInicio = resultInicio[0];
		String mesInicio = resultInicio[1];
		// data fim
		String anoFim = resultFim[0];
		String mesFim = resultFim[1];
		
		int condicaoDeParada = 0;
		int auxAnoInicio = Integer.parseInt(anoInicio);
		int auxMesInicio = Integer.parseInt(mesInicio);
		
		/*** Preenche os nomes dos Produtos que ser�o usados no Gr�fico - Chart.js ***/
	
		if (produtosMaisVendidos.size() == 0) {
			// adi��o 3 Produtos VAZIO's
			nomeProduto1.add(produtoVazio);
			nomeProduto2.add(produtoVazio);
			nomeProduto3.add(produtoVazio);
		}
		else if (produtosMaisVendidos.size() == 1) {
			// adiciona somente 2 Produtos VAZIO's
			nomeProduto1 = produtoDAO.consultarProdutoById(produtosMaisVendidos.get(0).getProduto().getId());
			nomeProduto2.add(produtoVazio);
			nomeProduto3.add(produtoVazio);
		}
		else if (produtosMaisVendidos.size() == 2) {
			// adiciona somente 1 Produto VAZIO
			nomeProduto1 = produtoDAO.consultarProdutoById(produtosMaisVendidos.get(0).getProduto().getId());
			nomeProduto2 = produtoDAO.consultarProdutoById(produtosMaisVendidos.get(1).getProduto().getId());
			nomeProduto3.add(produtoVazio);
		}
		else {
			// busca os produtos, conforme a consulta realizada acima (os que sao mais vendidos)
			nomeProduto1 = produtoDAO.consultarProdutoById(produtosMaisVendidos.get(0).getProduto().getId());
			nomeProduto2 = produtoDAO.consultarProdutoById(produtosMaisVendidos.get(1).getProduto().getId());
			nomeProduto3 = produtoDAO.consultarProdutoById(produtosMaisVendidos.get(2).getProduto().getId());
		}
		
		/*** Fim do preenchimento dos nomes dos Produtos - Chart.js ***/
		
		
		/****** La�o para criar as colunas do Gr�fico - mes/ano ******/
		
		for(int i = 0; condicaoDeParada == 0; i++) {
			// salva o mes e o ano em um array de String
			totalColunas.add(Integer.toString(auxMesInicio) + "/" + Integer.toString(auxAnoInicio));
			
			// se ano inicio � igual a ano fim e
			// o mes inicio � igual a mes fim? - PARA O LA�O
			if (auxAnoInicio == Integer.parseInt(anoFim) && auxMesInicio == Integer.parseInt(mesFim)) {
				break;
			}
			// sen�o, continua com as verifica��es
			else {
				// o mes inicio ja esta em dezembro?
				if (auxMesInicio == 12) {
					// o mes retorna para janeiro
					auxMesInicio = 1;
					// acrescenta mais 1 no ano inicio
					auxAnoInicio ++;
				}
				// sen�o, acrescenta mais 1 no mes inicio
				else {
					auxMesInicio ++;
				}
			}
		}
		/*** Fim cria��o das colunas do Gr�fico ***/
		
		
		/*** La�o para preencher os valores de cada produto,
		conforme a coluna (de datas) criada acima, de acordo com os produtos trazidos do BD ***/
		
		for(String coluna : totalColunas) {
			// separa o m�s-ano que esta na coluna,
			// conforme o mes, ano e o ID do produto
			String[] resultColuna  = coluna.split("/");
			
			// mes e ano da coluna
			String mesColuna = resultColuna[0];
			String anoColuna = resultColuna[1];
			
			// verifica as quantidades dos produtos, retornados do BD,
			// se a lista dos produtos for menor que 3, ser� adicionado o valor ZERO no indice que n�o tem o Produto
			if (produtosMaisVendidos.size() == 0) {						
				// adi��o ZERO's na lista dos valores do produto
				totalValorProduto1.add("0");
				totalValorProduto2.add("0");
				totalValorProduto3.add("0");
			}
			else if (produtosMaisVendidos.size() == 1) {						
				// busca os valores do produto vendido, conforme o id produto, mes e ano como parametros
				List<GraficoCategoria> qtdeProdutoMesAno1 = graficoDAO.consultarQtdeProdutosVendidosByIdProdutoMesAno(produtosMaisVendidos.get(0).getProduto().getId(), mesColuna, anoColuna);
				
				// verifica se naquele mes teve alguma qtde do produto1 vendido,
				// se n�o tiver nada, ser� adicionado ZERO para n�o quebrar a tela
				if (qtdeProdutoMesAno1.size() == 0) {
					totalValorProduto1.add("0");
				}
				else {
				// salva a qtde do produto 1 vendido por mes e ano em um array de String
					totalValorProduto1.add(qtdeProdutoMesAno1.get(0).getProduto().getQuantidadeSelecionada());
				}
				
				// adiciona somente 2 ZERO's na lista dos valores do produto
				totalValorProduto2.add("0");
				totalValorProduto3.add("0");
			}
			else if (produtosMaisVendidos.size() == 2) {						
				// busca os valores do produto vendido, conforme o id produto, mes e ano como parametros
				List<GraficoCategoria> qtdeProdutoMesAno1 = graficoDAO.consultarQtdeProdutosVendidosByIdProdutoMesAno(produtosMaisVendidos.get(0).getProduto().getId(), mesColuna, anoColuna);
				List<GraficoCategoria> qtdeProdutoMesAno2 = graficoDAO.consultarQtdeProdutosVendidosByIdProdutoMesAno(produtosMaisVendidos.get(1).getProduto().getId(), mesColuna, anoColuna);
				
				// verifica se naquele mes teve alguma qtde do produto 1 vendido,
				// se n�o tiver nada, ser� adicionado ZERO para n�o quebrar a tela
				if (qtdeProdutoMesAno1.size() == 0) {
					totalValorProduto1.add("0");
				}
				else {
					// salva a qtde do produto 1 vendido por mes e ano em um array de String
					totalValorProduto1.add(qtdeProdutoMesAno1.get(0).getProduto().getQuantidadeSelecionada());
				}
				// verifica se naquele mes teve alguma qtde do produto 2 vendido,
				// se n�o tiver nada, ser� adicionado ZERO para n�o quebrar a tela
				if (qtdeProdutoMesAno2.size() == 0) {
					totalValorProduto2.add("0");
				}
				else {
					// salva a qtde do produto 2 vendido por mes e ano em um array de String
					totalValorProduto2.add(qtdeProdutoMesAno2.get(0).getProduto().getQuantidadeSelecionada());
				}
				
				// adiciona somente 1 ZERO na lista dos valores do produto
				totalValorProduto3.add("0");
			}
			// se o banco retornar 3 produtos
			else {						
				// busca os valores do produto vendido, conforme o id produto, mes e ano como parametros
				List<GraficoCategoria> qtdeProdutoMesAno1 = graficoDAO.consultarQtdeProdutosVendidosByIdProdutoMesAno(produtosMaisVendidos.get(0).getProduto().getId(), mesColuna, anoColuna);
				List<GraficoCategoria> qtdeProdutoMesAno2 = graficoDAO.consultarQtdeProdutosVendidosByIdProdutoMesAno(produtosMaisVendidos.get(1).getProduto().getId(), mesColuna, anoColuna);
				List<GraficoCategoria> qtdeProdutoMesAno3 = graficoDAO.consultarQtdeProdutosVendidosByIdProdutoMesAno(produtosMaisVendidos.get(2).getProduto().getId(), mesColuna, anoColuna);
				
				// verifica se naquele mes teve alguma qtde do produto 1 vendido,
				// se n�o tiver nada, ser� adicionado ZERO para n�o quebrar a tela
				if (qtdeProdutoMesAno1.size() == 0) {
					totalValorProduto1.add("0");
				}
				else {
					// salva a qtde do produto 1 vendido por mes e ano em um array de String
					totalValorProduto1.add(qtdeProdutoMesAno1.get(0).getProduto().getQuantidadeSelecionada());
				}
				// verifica se naquele mes teve alguma qtde do produto 2 vendido,
				// se n�o tiver nada, ser� adicionado ZERO para n�o quebrar a tela
				if (qtdeProdutoMesAno2.size() == 0) {
					totalValorProduto2.add("0");
				}
				else {
					// salva a qtde do produto 2 vendido por mes e ano em um array de String,
					// que ser� mandado para a tela (grafico2.jsp)
					totalValorProduto2.add(qtdeProdutoMesAno2.get(0).getProduto().getQuantidadeSelecionada());
				}
				// verifica se naquele mes teve alguma qtde do produto 3 vendido,
				// se n�o tiver nada, ser� adicionado ZERO para n�o quebrar a tela
				if (qtdeProdutoMesAno3.size() == 0) {
					totalValorProduto3.add("0");
				}
				else {
					// salva a qtde do produto 3 vendido por mes e ano em um array de String
					totalValorProduto3.add(qtdeProdutoMesAno3.get(0).getProduto().getQuantidadeSelecionada());
				}
			}
		}
		
		/*** Fim de preenchimento dos valores de cada produto, conforme a coluna (de datas) criada acima ***/
		
		
		// recebe a REFERENCIA de "graficoCategoriaEntidade"
		novoGraficoCategoria = graficoCategoriaEntidade;
		
		novoGraficoCategoria.setNomeProduto1(nomeProduto1);
		novoGraficoCategoria.setNomeProduto2(nomeProduto2);
		novoGraficoCategoria.setNomeProduto3(nomeProduto3);
		novoGraficoCategoria.setTotalColunas(totalColunas);
		novoGraficoCategoria.setTotalValorProduto1(totalValorProduto1);
		novoGraficoCategoria.setTotalValorProduto2(totalValorProduto2);
		novoGraficoCategoria.setTotalValorProduto3(totalValorProduto3);
		
		ListGraficoCategoria.add(novoGraficoCategoria);
		
		return ListGraficoCategoria;
	} // Consultar
	
	
	/**
	 * M�todo para consultar as 3 categorias de produtos mais vendidos com filtro de datas - Query principal !!
	 */
	public List<GraficoCategoria> consultar3CategoriasProdutosMaisVendidos (String dtInicio, String dtFim) {
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select id_produto, sum(quantidade) as quantidade_vendido from pedido_item where dt_cadastro BETWEEN (?) AND (?) group by id_produto order by sum(quantidade) desc LIMIT 3;");
			stmt.setString(1, dtInicio);
			stmt.setString(2, dtFim);
			ResultSet rs = stmt.executeQuery();
			
			List<GraficoCategoria> itens_grafico = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Grafico Categoria
				GraficoCategoria item_grafico = new GraficoCategoria();
				Produto produto = new Produto();
				
				produto.setId(rs.getString("id_produto"));
				produto.setQuantidadeSelecionada(rs.getString("quantidade_vendido"));
				item_grafico.setProduto(produto);
				
				// adicionando o objeto � lista
				itens_grafico.add(item_grafico);
			}
				
			rs.close();
			stmt.close();
			return itens_grafico;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
				
	} // Consultar os 3 Produtos mais vendidos com filtro de datas
	
	
	/**
	 * M�todo para consultar a quantidade de Produtos vendidos por ID Produto, Mes e Ano 
	 *
	 */
	public List<GraficoCategoria> consultarQtdeProdutosVendidosByIdProdutoMesAno (String idProduto, String mes, String ano) {
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT id_produto, sum(quantidade) as quantidade_vendido  FROM pedido_item WHERE id_produto = ? AND MONTH(dt_cadastro) = ? AND YEAR(dt_cadastro) = ? group by id_produto;");
			stmt.setString(1, idProduto);
			stmt.setString(2, mes);
			stmt.setString(3, ano);
			ResultSet rs = stmt.executeQuery();
			
			List<GraficoCategoria> itens_grafico = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Grafico Categoria
				GraficoCategoria item_grafico = new GraficoCategoria();
				Produto produto = new Produto();
				
				produto.setId(rs.getString("id_produto"));
				produto.setQuantidadeSelecionada(rs.getString("quantidade_vendido"));
				item_grafico.setProduto(produto);
				
				// adicionando o objeto � lista
				itens_grafico.add(item_grafico);
			}
				
			rs.close();
			stmt.close();
			return itens_grafico;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
				
	} // Consultar a quantidade de Produtos vendidos por ID Produto, Mes e Ano
	
}