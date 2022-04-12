package com.les.roupa.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.les.roupa.core.dominio.Carrinho;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.DetalheProduto;
import com.les.roupa.core.dominio.Produto;
import com.les.roupa.core.dominio.Resultado;
import com.les.roupa.view.helper.IViewHelper;

/**
 * ViewHelper - Carrinho
 * @author Lorena Oliveira
 * Abril/2022 
 */

public class CarrinhoHelper implements IViewHelper {

	Carrinho carrinho = null;
	DetalheProduto detalheProduto = null;
	Produto produto = null;
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		// Verifica qual opera��o do bot�o foi acionada - SALVAR/ALTERAR/CONSULTAR/EXCLUIR
		String operacao = request.getParameter("operacao");
		
		String id = null;
		String quantidadeSelecionada = null;
		String tipoDeOperacao = null;
		String idCliente = null;
		
		if (("CONSULTAR").equals(operacao)) {
			carrinho = new Carrinho();
			
			idCliente = request.getParameter("idCliente");
			
			carrinho.setIdCliente(idCliente);
		}
		
		else if (("SALVAR").equals(operacao)) {
			carrinho = new Carrinho();
			detalheProduto = new DetalheProduto();
			produto = new Produto();
			
			// capturando os valores do HTML e passando para o Produto, 
			// logo em sequencia passando para o Item Carrinho
			id = request.getParameter("idProduto");
			quantidadeSelecionada = request.getParameter("quantidadeSelecionada");
			
			// Verifica��o necessaria para o usuario n�o clicar no bot�o de
			// "Adicionar" (pro carrinho) com o campo "Quantidade" vazia
			if (quantidadeSelecionada.equals("")) {
				quantidadeSelecionada = "0";
			}
			
			// Atribuindo os valores capturados do HTML para o Item Carrinho, 
			// logo em sequencia passando para o Carrinho
			produto.setId(id);
			produto.setQuantidadeSelecionada(quantidadeSelecionada);
			detalheProduto.setProduto(produto);
			carrinho.setDetalheProduto(detalheProduto);
		}
		
		else if (("ALTERAR").equals(operacao)) {
			carrinho = new Carrinho();
			detalheProduto = new DetalheProduto();
			produto = new Produto();
			
			// capturando os valores do HTML e passando para o Produto,
			//logo em sequencia passando para o Item Carrinho
			id = request.getParameter("idProduto");
			quantidadeSelecionada = request.getParameter("quantidadeSelecionada");
			tipoDeOperacao = request.getParameter("tipoDeOperacao");
			idCliente = request.getParameter("idCliente");
			
			// verifica o tipo de opera��o que esta sendo realizado no carrinho,
			// se � uma "adi��o", para adicionar mais "1" de quantidade do item selecionado ao carrinho,
			// ou � uma "subtra��o", para retirar mais "1" de quantidade do item selecionado ao carrinho
			if (("adicao").equals(tipoDeOperacao)) {
				int quantidadeSelecionadaInteiro;
				
				// feito o CAST de String para INT, para poder fazer a adi��o de mais 1
				quantidadeSelecionadaInteiro = Integer.parseInt(quantidadeSelecionada);
				quantidadeSelecionadaInteiro ++;
				
				// feito o CAST de INT para String, para poder atualizar a quantidade selecionada com mais 1
				quantidadeSelecionada = Integer.toString(quantidadeSelecionadaInteiro);
			}
			else if (("subtracao").equals(tipoDeOperacao)) {
				int quantidadeSelecionadaInteiro;
				
				// feito o CAST de String para INT, para poder fazer a subtra��o de mais 1
				quantidadeSelecionadaInteiro = Integer.parseInt(quantidadeSelecionada);
				quantidadeSelecionadaInteiro --;
				
				// feito o CAST de INT para String, para poder atualizar a quantidade selecionada com menos 1
				quantidadeSelecionada = Integer.toString(quantidadeSelecionadaInteiro);
			}
			
			// Atribuindo os valores capturados do HTML para o Item Carrinho, 
			// logo em sequencia passando para o Carrinho
			produto.setId(id);
			produto.setQuantidadeSelecionada(quantidadeSelecionada);
			detalheProduto.setProduto(produto);
			carrinho.setDetalheProduto(detalheProduto);
			carrinho.setIdCliente(idCliente);
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			carrinho = new Carrinho();
			detalheProduto = new DetalheProduto();
			produto = new Produto();
			
			// capturando os valores do HTML e passando para o Produto,
			// logo em sequencia passando para o Item Carrinho
			id = request.getParameter("idProduto");
			
			// Atribuindo os valores capturados do HTML para o Item Carrinho,
			// logo em sequencia passando para o Carrinho
			produto.setId(id);
			detalheProduto.setProduto(produto);
			carrinho.setDetalheProduto(detalheProduto);
		}
		
		return carrinho;
	}

	// SET VIEW
	
	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Verifica qual opera��o do bot�o foi acionada - SALVAR/ALTERAR/CONSULTAR/EXCLUIR
		String operacao = request.getParameter("operacao");
		
		// Usa para escrever na tela
		PrintWriter writer = response.getWriter();
		
		if (("CONSULTAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// foi utilizado o getEntidades do resultado para poder pegar o Login consultado
				List<EntidadeDominio> entidades = resultado.getEntidades();
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				
				// salva na sess�o os dados do Cliente (Endere�os, Cart�es e Cupons (caso possua)),
				// pra poder usar na tela do Carrinho
				sessao.setAttribute("entidadesEnderecoCartaoCupom", entidades);
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/minhaConta2.jsp").forward(request, response);
			} 
			else {
				// mostra as mensagens de ERRO se houver
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				System.out.println("ERRO PARA CONSULTAR!");
				request.getRequestDispatcher("JSP/carrinho2.jsp").forward(request, response);
			}
		}
		
		else if (("SALVAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// capturando os valores de quantidade do HTML
				String quantidadeSelecionada = request.getParameter("quantidadeSelecionada");
				boolean adicionaNovoItemAoCarrinho = true;
				
				// verifica��o necessaria para o usuario n�o clicar no bot�o de
				// "adicionar" do Produto com o campo "quantidade" vazia
				if (quantidadeSelecionada.equals("")) {
					quantidadeSelecionada = "0";
				}
				
				// pega o Carrinho que esta no resultado da requisi��o, que foi alterado como REFERENCIA no CarrinhoDAO
				Carrinho resultadoCarrinho = (Carrinho) resultado.getEntidades().get(0);
				// pega o Produto preenchido que esta dentro do Carrinho do resultado da requisi��o
				Produto produtoSelecionado = resultadoCarrinho.getDetalheProduto().getProduto();
				
				List<Produto> produtoParaAdicionarAoCarrinho = new ArrayList<>();
				
				// se a quantidade selecionada � maior que ZERO, ent�o adiciona o item selecionado na lista da Sess�o
				if (Integer.parseInt(quantidadeSelecionada) > 0) {
					// guarda no produto, o valor da quantidade selecionada da tela, 
					// para poder listar a quantidade selecionada no arquivo "carrinho2.jsp"
					produtoSelecionado.setQuantidadeSelecionada(quantidadeSelecionada);
					
					// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
					HttpSession sessao = request.getSession();
					// pega o objeto salvo em Sess�o com o nome "itensCarrinho",
					// e passa para o "produtoParaAdicionarAoCarrinho" (fazendo o CAST para o tipo List<Produto>)
					produtoParaAdicionarAoCarrinho = (List<Produto>) sessao.getAttribute("itensCarrinho");
					
					// faz um la�o de repeti��o para encontrar se o produto selecionado j� existe na Sess�o,
					// caso exista, atualiza somente a "quantidadeSelecionada",
					// se n�o, adiciona o novo item inteiro na lista de produtos da Sess�o
					for (int i = 0; i< produtoParaAdicionarAoCarrinho.size(); i++) {
						// se o ID do carrinho for igual ao ID do "produto selecionado", 
						// o atributo "quantidadeSelecionada" ser� atualizada
						if ((produtoParaAdicionarAoCarrinho.get(i).getId()).equals(produtoSelecionado.getId())) {
							int somatoriaDasQuantidades = (Integer.parseInt(produtoParaAdicionarAoCarrinho.get(i).getQuantidadeSelecionada()) + Integer.parseInt(produtoSelecionado.getQuantidadeSelecionada()));
							
							// verifica se a somatoria das quantidades selecionadas � maior que a quantidade do estoque disponivel
							if (somatoriaDasQuantidades > Integer.parseInt(produtoSelecionado.getQtdeEstoque())) {
								// atribui a nova mensagem para poder mostra na pagina .JSP
								resultado.setMensagem("A Quantidade selecionada � MAIOR que disponivel no estoque!");
								
								adicionaNovoItemAoCarrinho = false;
								break;
							}
							else {
								// feito o CAST de INT para String,
								// para poder atualizar a quantidade selecionada com a somatoria
								produtoSelecionado.setQuantidadeSelecionada(Integer.toString(somatoriaDasQuantidades));
								
								// ".set" do ArrayList faz o seguinte:
								// set(int index, Object element):
								// Substitui o i-�simo elemento da lista pelo elemento especificado.
								produtoParaAdicionarAoCarrinho.set(i, produtoSelecionado);
								
								// atribui a nova mensagem para poder mostrar na pagina carinho2.JSP
								resultado.setMensagem("Item do Carrinho atualizado com sucesso!");
								
								adicionaNovoItemAoCarrinho = false;
								break;
							}
						}
						else {
							// n�o encontrou nenhum item igual no Carrinho da Sess�o,
							// ent�o seta a variavel "adicionaNovoItemAoCarrinho" como TRUE
							adicionaNovoItemAoCarrinho = true;
						}
					}
					
					// verifica se � para adicionar um novo item ao Carrinho
					if (adicionaNovoItemAoCarrinho) {
						// passa o produto selecionado para a variavel que ser� responsavel para atualizar
						// a sess�o dos itens do carrinho
						produtoParaAdicionarAoCarrinho.add(produtoSelecionado);
						
						// atribui a nova mensagem para poder mostra na pagina .JSP
						resultado.setMensagem("Item adicionado ao Carrinho com sucesso!");
					}
					
					// adiciona o produto selecionado ao carrinho da sess�o
					// atualiza o objeto "itensCarrinho" que esta salvo em sess�o, com o novo produto selecionado
					sessao.setAttribute("itensCarrinho", produtoParaAdicionarAoCarrinho);
					
					// pendura o "resultado" na requisi��o para poder mandar para o arquivo carrinho2.JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/carrinho2.jsp").forward(request, response);
				}
				
				// se n�o, a quantidade selecionada � igual a ZERO, ent�o mostra mensagem de erro na p�gina
				else {
					// atribui a nova mensagem para poder mostra na pagina .JSP
					resultado.setMensagem("Selecione uma quantidade maior que ZERO!");
					
					// pendura o "resultado" na requisi��o para poder mandar para o arquivo .JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/index2.jsp").forward(request, response);
				}
			}
			else {
				// mostra as mensagens de ERRO se houver
				// pendura o "resultado" na requisi��o para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/carrinho2.jsp").forward(request, response);
			}
		}
		
		else if (("ALTERAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// capturando os valores do HTML
				String quantidadeSelecionada = request.getParameter("quantidadeSelecionada");
				String tipoDeOperacao = request.getParameter("tipoDeOperacao");
				
				// pega o Carrinho que esta no resultado da requisi��o, que foi alterado como REFERENCIA no CarrinhoDAO
				Carrinho resultadoCarrinho = (Carrinho) resultado.getEntidades().get(0);
				// pega o Produto preenchido que esta dentro do Carrinho do resultado da requisi��o
				Produto produtoSelecionado = resultadoCarrinho.getDetalheProduto().getProduto();
				
				List<Produto> produtoParaAdicionarAoCarrinho = new ArrayList<>();
				
				// verifica o tipo de opera��o que esta sendo realizado no carrinho,
				// se � uma "adi��o", para adicionar mais "1" de quantidade do item selecionado ao carrinho,
				// ou � uma "subtra��o", para retirar mais "1" de quantidade do item selecionado ao carrinho
				if (("adicao").equals(tipoDeOperacao)) {
					int quantidadeSelecionadaInteiro;
					
					// feito o CAST de String para INT, para poder fazer a adi��o de mais 1
					quantidadeSelecionadaInteiro = Integer.parseInt(quantidadeSelecionada);
					quantidadeSelecionadaInteiro ++;
					
					// feito o CAST de INT para String, para poder atualizar a quantidade selecionada com mais 1
					quantidadeSelecionada = Integer.toString(quantidadeSelecionadaInteiro);
				}
				else if (("subtracao").equals(tipoDeOperacao)) {
					int quantidadeSelecionadaInteiro;
					
					// feito o CAST de String para INT, para poder fazer a subtra��o de mais 1
					quantidadeSelecionadaInteiro = Integer.parseInt(quantidadeSelecionada);
					quantidadeSelecionadaInteiro --;
					
					// feito o CAST de INT para String, para poder atualizar a quantidade selecionada com menos 1
					quantidadeSelecionada = Integer.toString(quantidadeSelecionadaInteiro);
				}
				
				// se a quantidade selecionada � maior que ZERO, ent�o atualiza a lista da Sess�o
				if (Integer.parseInt(quantidadeSelecionada) > 0) {
					// guarda no produto, o valor da quantidade selecionada da tela,
					// ja com o valor atualizado com o tipo de opera��o, 
					// para poder listar a quantidade selecionada no arquivo "lista-carrinho-scriptlet.jsp"
					produtoSelecionado.setQuantidadeSelecionada(quantidadeSelecionada);
					
					// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
					HttpSession sessao = request.getSession();
					// pega o objeto salvo em Sess�o com o nome "itensCarrinho",
					// e passa para o "produtoParaAdicionarAoCarrinho" (fazendo o CAST para o tipo List<Produto>)
					produtoParaAdicionarAoCarrinho = (List<Produto>) sessao.getAttribute("itensCarrinho");
					
					// faz um la�o de repeti��o para encontrar o produto que ser� atualizado na Sess�o
					for (int i = 0; i< produtoParaAdicionarAoCarrinho.size(); i++) {
						// se o ID do carrinho for igual ao ID do "produto atualizado", ele ser� adicionado ao carrinho
						if ((produtoParaAdicionarAoCarrinho.get(i).getId()).equals(produtoSelecionado.getId())) {
							// ".set" do ArrayList faz o seguinte:
							// set(int index, Object element):
							// Substitui o i-�simo elemento da lista pelo elemento especificado.
							produtoParaAdicionarAoCarrinho.set(i, produtoSelecionado);
							
							// outra forma de "atualizar" a lista dos produtos:
							//produtoParaAdicionarAoCarrinho.remove(i);
							//produtoParaAdicionarAoCarrinho.add(i, produtoSelecionado.get(0));
						}
					}
					
					// atualiza o objeto "itensCarrinho" que esta salvo em sess�o,
					// com o novo produto selecionado atualizado
					sessao.setAttribute("itensCarrinho", produtoParaAdicionarAoCarrinho);
					
					// atribui a nova mensagem para poder mostra na pagina .JSP
					resultado.setMensagem("Item do Carrinho atualizado com sucesso!");
					
					// pendura o "resultado" na requisi��o para poder mandar para o arquivo carrinho2.JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/carrinho2.jsp").forward(request, response);
				}
				
				// se n�o, a quantidade selecionada � igual a ZERO, ent�o retira da lista da Sess�o
				else {
					// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
					HttpSession sessao = request.getSession();
					// pega o objeto salvo em Sess�o com o nome "itensCarrinho",
					// e passa para o "produtoParaAdicionarAoCarrinho" (fazendo o CAST para o tipo List<Produto>)
					produtoParaAdicionarAoCarrinho = (List<Produto>) sessao.getAttribute("itensCarrinho");
					
					// faz um la�o de repeti��o para encontrar o produto selecionado e retirar da lista da Sess�o
					for (int i = 0; i< produtoParaAdicionarAoCarrinho.size(); i++) {
						// se o ID do carrinho for igual ao ID do "produto selecionado", ele ser� retirado do carrinho
						if ((produtoParaAdicionarAoCarrinho.get(i).getId()).equals(produtoSelecionado.getId())) {
							// remove o item inteiro da Sess�o
							produtoParaAdicionarAoCarrinho.remove(i);
						}
					}
					
					// atualiza o objeto "itensCarrinho" que esta salvo em sess�o, a nova lista atualizada
					sessao.setAttribute("itensCarrinho", produtoParaAdicionarAoCarrinho);
					
					// atribui a nova mensagem para poder mostra na pagina .JSP
					resultado.setMensagem("Item do Carrinho removido com sucesso!");
					
					// pendura o "resultado" na requisi��o para poder mandar para o arquivo .JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/carrinho2.jsp").forward(request, response);
				}
			}
			else {
				// mostra as mensagens de ERRO se houver
				// pendura o "resultado" na requisi��o para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/carrinho2.jsp").forward(request, response);
			}
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// pega o Carrinho que esta no resultado da requisi��o, que foi alterado como REFERENCIA no CarrinhoDAO
				Carrinho resultadoCarrinho = (Carrinho) resultado.getEntidades().get(0);
				// pega o Produto preenchido que esta dentro do Carrinho do resultado da requisi��o
				Produto produtoSelecionado = resultadoCarrinho.getDetalheProduto().getProduto();
				
				List<Produto> produtoParaAdicionarAoCarrinho = new ArrayList<>();
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				
				// pega o objeto salvo em Sess�o com o nome "itensCarrinho",
				// e passa para o "produtoParaAdicionarAoCarrinho" (fazendo o CAST para o tipo List<Produto>)
				produtoParaAdicionarAoCarrinho = (List<Produto>) sessao.getAttribute("itensCarrinho");
				
				// faz um la�o de repeti��o para encontrar o produto selecionado e retirar da lista da Sess�o
				for (int i = 0; i< produtoParaAdicionarAoCarrinho.size(); i++) {
					// se o ID do carrinho for igual ao ID do "produto selecionado", ele ser� retirado do carrinho
					if ((produtoParaAdicionarAoCarrinho.get(i).getId()).equals(produtoSelecionado.getId())) {
						// remove o item inteiro da Sess�o
						produtoParaAdicionarAoCarrinho.remove(i);
					}
				}
				
				// atualiza o objeto "itensCarrinho" que esta salvo em sess�o, a nova lista atualizada
				sessao.setAttribute("itensCarrinho", produtoParaAdicionarAoCarrinho);
				
				// atribui a nova mensagem para poder mostra na pagina .JSP
				resultado.setMensagem("Item do Carrinho removido com sucesso!");
				
				// pendura o "resultado" na requisi��o para poder mandar para o arquivo carrinho2.JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/carrinho2.jsp").forward(request, response);
			}
			else {
				// mostra as mensagens de ERRO se houver
				// pendura o "resultado" na requisi��o para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/carrinho2.jsp").forward(request, response);
			}
		}
		
	}

}