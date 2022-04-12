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
		
		// Verifica qual operação do botão foi acionada - SALVAR/ALTERAR/CONSULTAR/EXCLUIR
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
			
			// Verificação necessaria para o usuario não clicar no botão de
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
			
			// verifica o tipo de operação que esta sendo realizado no carrinho,
			// se é uma "adição", para adicionar mais "1" de quantidade do item selecionado ao carrinho,
			// ou é uma "subtração", para retirar mais "1" de quantidade do item selecionado ao carrinho
			if (("adicao").equals(tipoDeOperacao)) {
				int quantidadeSelecionadaInteiro;
				
				// feito o CAST de String para INT, para poder fazer a adição de mais 1
				quantidadeSelecionadaInteiro = Integer.parseInt(quantidadeSelecionada);
				quantidadeSelecionadaInteiro ++;
				
				// feito o CAST de INT para String, para poder atualizar a quantidade selecionada com mais 1
				quantidadeSelecionada = Integer.toString(quantidadeSelecionadaInteiro);
			}
			else if (("subtracao").equals(tipoDeOperacao)) {
				int quantidadeSelecionadaInteiro;
				
				// feito o CAST de String para INT, para poder fazer a subtração de mais 1
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
		
		// Verifica qual operação do botão foi acionada - SALVAR/ALTERAR/CONSULTAR/EXCLUIR
		String operacao = request.getParameter("operacao");
		
		// Usa para escrever na tela
		PrintWriter writer = response.getWriter();
		
		if (("CONSULTAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// foi utilizado o getEntidades do resultado para poder pegar o Login consultado
				List<EntidadeDominio> entidades = resultado.getEntidades();
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				
				// salva na sessão os dados do Cliente (Endereços, Cartões e Cupons (caso possua)),
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
				
				// verificação necessaria para o usuario não clicar no botão de
				// "adicionar" do Produto com o campo "quantidade" vazia
				if (quantidadeSelecionada.equals("")) {
					quantidadeSelecionada = "0";
				}
				
				// pega o Carrinho que esta no resultado da requisição, que foi alterado como REFERENCIA no CarrinhoDAO
				Carrinho resultadoCarrinho = (Carrinho) resultado.getEntidades().get(0);
				// pega o Produto preenchido que esta dentro do Carrinho do resultado da requisição
				Produto produtoSelecionado = resultadoCarrinho.getDetalheProduto().getProduto();
				
				List<Produto> produtoParaAdicionarAoCarrinho = new ArrayList<>();
				
				// se a quantidade selecionada é maior que ZERO, então adiciona o item selecionado na lista da Sessão
				if (Integer.parseInt(quantidadeSelecionada) > 0) {
					// guarda no produto, o valor da quantidade selecionada da tela, 
					// para poder listar a quantidade selecionada no arquivo "carrinho2.jsp"
					produtoSelecionado.setQuantidadeSelecionada(quantidadeSelecionada);
					
					// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
					HttpSession sessao = request.getSession();
					// pega o objeto salvo em Sessão com o nome "itensCarrinho",
					// e passa para o "produtoParaAdicionarAoCarrinho" (fazendo o CAST para o tipo List<Produto>)
					produtoParaAdicionarAoCarrinho = (List<Produto>) sessao.getAttribute("itensCarrinho");
					
					// faz um laço de repetição para encontrar se o produto selecionado já existe na Sessão,
					// caso exista, atualiza somente a "quantidadeSelecionada",
					// se não, adiciona o novo item inteiro na lista de produtos da Sessão
					for (int i = 0; i< produtoParaAdicionarAoCarrinho.size(); i++) {
						// se o ID do carrinho for igual ao ID do "produto selecionado", 
						// o atributo "quantidadeSelecionada" será atualizada
						if ((produtoParaAdicionarAoCarrinho.get(i).getId()).equals(produtoSelecionado.getId())) {
							int somatoriaDasQuantidades = (Integer.parseInt(produtoParaAdicionarAoCarrinho.get(i).getQuantidadeSelecionada()) + Integer.parseInt(produtoSelecionado.getQuantidadeSelecionada()));
							
							// verifica se a somatoria das quantidades selecionadas é maior que a quantidade do estoque disponivel
							if (somatoriaDasQuantidades > Integer.parseInt(produtoSelecionado.getQtdeEstoque())) {
								// atribui a nova mensagem para poder mostra na pagina .JSP
								resultado.setMensagem("A Quantidade selecionada é MAIOR que disponivel no estoque!");
								
								adicionaNovoItemAoCarrinho = false;
								break;
							}
							else {
								// feito o CAST de INT para String,
								// para poder atualizar a quantidade selecionada com a somatoria
								produtoSelecionado.setQuantidadeSelecionada(Integer.toString(somatoriaDasQuantidades));
								
								// ".set" do ArrayList faz o seguinte:
								// set(int index, Object element):
								// Substitui o i-ésimo elemento da lista pelo elemento especificado.
								produtoParaAdicionarAoCarrinho.set(i, produtoSelecionado);
								
								// atribui a nova mensagem para poder mostrar na pagina carinho2.JSP
								resultado.setMensagem("Item do Carrinho atualizado com sucesso!");
								
								adicionaNovoItemAoCarrinho = false;
								break;
							}
						}
						else {
							// não encontrou nenhum item igual no Carrinho da Sessão,
							// então seta a variavel "adicionaNovoItemAoCarrinho" como TRUE
							adicionaNovoItemAoCarrinho = true;
						}
					}
					
					// verifica se é para adicionar um novo item ao Carrinho
					if (adicionaNovoItemAoCarrinho) {
						// passa o produto selecionado para a variavel que será responsavel para atualizar
						// a sessão dos itens do carrinho
						produtoParaAdicionarAoCarrinho.add(produtoSelecionado);
						
						// atribui a nova mensagem para poder mostra na pagina .JSP
						resultado.setMensagem("Item adicionado ao Carrinho com sucesso!");
					}
					
					// adiciona o produto selecionado ao carrinho da sessão
					// atualiza o objeto "itensCarrinho" que esta salvo em sessão, com o novo produto selecionado
					sessao.setAttribute("itensCarrinho", produtoParaAdicionarAoCarrinho);
					
					// pendura o "resultado" na requisição para poder mandar para o arquivo carrinho2.JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/carrinho2.jsp").forward(request, response);
				}
				
				// se não, a quantidade selecionada é igual a ZERO, então mostra mensagem de erro na página
				else {
					// atribui a nova mensagem para poder mostra na pagina .JSP
					resultado.setMensagem("Selecione uma quantidade maior que ZERO!");
					
					// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/index2.jsp").forward(request, response);
				}
			}
			else {
				// mostra as mensagens de ERRO se houver
				// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
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
				
				// pega o Carrinho que esta no resultado da requisição, que foi alterado como REFERENCIA no CarrinhoDAO
				Carrinho resultadoCarrinho = (Carrinho) resultado.getEntidades().get(0);
				// pega o Produto preenchido que esta dentro do Carrinho do resultado da requisição
				Produto produtoSelecionado = resultadoCarrinho.getDetalheProduto().getProduto();
				
				List<Produto> produtoParaAdicionarAoCarrinho = new ArrayList<>();
				
				// verifica o tipo de operação que esta sendo realizado no carrinho,
				// se é uma "adição", para adicionar mais "1" de quantidade do item selecionado ao carrinho,
				// ou é uma "subtração", para retirar mais "1" de quantidade do item selecionado ao carrinho
				if (("adicao").equals(tipoDeOperacao)) {
					int quantidadeSelecionadaInteiro;
					
					// feito o CAST de String para INT, para poder fazer a adição de mais 1
					quantidadeSelecionadaInteiro = Integer.parseInt(quantidadeSelecionada);
					quantidadeSelecionadaInteiro ++;
					
					// feito o CAST de INT para String, para poder atualizar a quantidade selecionada com mais 1
					quantidadeSelecionada = Integer.toString(quantidadeSelecionadaInteiro);
				}
				else if (("subtracao").equals(tipoDeOperacao)) {
					int quantidadeSelecionadaInteiro;
					
					// feito o CAST de String para INT, para poder fazer a subtração de mais 1
					quantidadeSelecionadaInteiro = Integer.parseInt(quantidadeSelecionada);
					quantidadeSelecionadaInteiro --;
					
					// feito o CAST de INT para String, para poder atualizar a quantidade selecionada com menos 1
					quantidadeSelecionada = Integer.toString(quantidadeSelecionadaInteiro);
				}
				
				// se a quantidade selecionada é maior que ZERO, então atualiza a lista da Sessão
				if (Integer.parseInt(quantidadeSelecionada) > 0) {
					// guarda no produto, o valor da quantidade selecionada da tela,
					// ja com o valor atualizado com o tipo de operação, 
					// para poder listar a quantidade selecionada no arquivo "lista-carrinho-scriptlet.jsp"
					produtoSelecionado.setQuantidadeSelecionada(quantidadeSelecionada);
					
					// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
					HttpSession sessao = request.getSession();
					// pega o objeto salvo em Sessão com o nome "itensCarrinho",
					// e passa para o "produtoParaAdicionarAoCarrinho" (fazendo o CAST para o tipo List<Produto>)
					produtoParaAdicionarAoCarrinho = (List<Produto>) sessao.getAttribute("itensCarrinho");
					
					// faz um laço de repetição para encontrar o produto que será atualizado na Sessão
					for (int i = 0; i< produtoParaAdicionarAoCarrinho.size(); i++) {
						// se o ID do carrinho for igual ao ID do "produto atualizado", ele será adicionado ao carrinho
						if ((produtoParaAdicionarAoCarrinho.get(i).getId()).equals(produtoSelecionado.getId())) {
							// ".set" do ArrayList faz o seguinte:
							// set(int index, Object element):
							// Substitui o i-ésimo elemento da lista pelo elemento especificado.
							produtoParaAdicionarAoCarrinho.set(i, produtoSelecionado);
							
							// outra forma de "atualizar" a lista dos produtos:
							//produtoParaAdicionarAoCarrinho.remove(i);
							//produtoParaAdicionarAoCarrinho.add(i, produtoSelecionado.get(0));
						}
					}
					
					// atualiza o objeto "itensCarrinho" que esta salvo em sessão,
					// com o novo produto selecionado atualizado
					sessao.setAttribute("itensCarrinho", produtoParaAdicionarAoCarrinho);
					
					// atribui a nova mensagem para poder mostra na pagina .JSP
					resultado.setMensagem("Item do Carrinho atualizado com sucesso!");
					
					// pendura o "resultado" na requisição para poder mandar para o arquivo carrinho2.JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/carrinho2.jsp").forward(request, response);
				}
				
				// se não, a quantidade selecionada é igual a ZERO, então retira da lista da Sessão
				else {
					// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
					HttpSession sessao = request.getSession();
					// pega o objeto salvo em Sessão com o nome "itensCarrinho",
					// e passa para o "produtoParaAdicionarAoCarrinho" (fazendo o CAST para o tipo List<Produto>)
					produtoParaAdicionarAoCarrinho = (List<Produto>) sessao.getAttribute("itensCarrinho");
					
					// faz um laço de repetição para encontrar o produto selecionado e retirar da lista da Sessão
					for (int i = 0; i< produtoParaAdicionarAoCarrinho.size(); i++) {
						// se o ID do carrinho for igual ao ID do "produto selecionado", ele será retirado do carrinho
						if ((produtoParaAdicionarAoCarrinho.get(i).getId()).equals(produtoSelecionado.getId())) {
							// remove o item inteiro da Sessão
							produtoParaAdicionarAoCarrinho.remove(i);
						}
					}
					
					// atualiza o objeto "itensCarrinho" que esta salvo em sessão, a nova lista atualizada
					sessao.setAttribute("itensCarrinho", produtoParaAdicionarAoCarrinho);
					
					// atribui a nova mensagem para poder mostra na pagina .JSP
					resultado.setMensagem("Item do Carrinho removido com sucesso!");
					
					// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/carrinho2.jsp").forward(request, response);
				}
			}
			else {
				// mostra as mensagens de ERRO se houver
				// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/carrinho2.jsp").forward(request, response);
			}
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// pega o Carrinho que esta no resultado da requisição, que foi alterado como REFERENCIA no CarrinhoDAO
				Carrinho resultadoCarrinho = (Carrinho) resultado.getEntidades().get(0);
				// pega o Produto preenchido que esta dentro do Carrinho do resultado da requisição
				Produto produtoSelecionado = resultadoCarrinho.getDetalheProduto().getProduto();
				
				List<Produto> produtoParaAdicionarAoCarrinho = new ArrayList<>();
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				
				// pega o objeto salvo em Sessão com o nome "itensCarrinho",
				// e passa para o "produtoParaAdicionarAoCarrinho" (fazendo o CAST para o tipo List<Produto>)
				produtoParaAdicionarAoCarrinho = (List<Produto>) sessao.getAttribute("itensCarrinho");
				
				// faz um laço de repetição para encontrar o produto selecionado e retirar da lista da Sessão
				for (int i = 0; i< produtoParaAdicionarAoCarrinho.size(); i++) {
					// se o ID do carrinho for igual ao ID do "produto selecionado", ele será retirado do carrinho
					if ((produtoParaAdicionarAoCarrinho.get(i).getId()).equals(produtoSelecionado.getId())) {
						// remove o item inteiro da Sessão
						produtoParaAdicionarAoCarrinho.remove(i);
					}
				}
				
				// atualiza o objeto "itensCarrinho" que esta salvo em sessão, a nova lista atualizada
				sessao.setAttribute("itensCarrinho", produtoParaAdicionarAoCarrinho);
				
				// atribui a nova mensagem para poder mostra na pagina .JSP
				resultado.setMensagem("Item do Carrinho removido com sucesso!");
				
				// pendura o "resultado" na requisição para poder mandar para o arquivo carrinho2.JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/carrinho2.jsp").forward(request, response);
			}
			else {
				// mostra as mensagens de ERRO se houver
				// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/carrinho2.jsp").forward(request, response);
			}
		}
		
	}

}