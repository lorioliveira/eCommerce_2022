package com.les.roupa.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Estoque;
import com.les.roupa.core.dominio.ItemPedido;
import com.les.roupa.core.dominio.Pedido;
import com.les.roupa.core.dominio.PedidoTroca;
import com.les.roupa.core.dominio.Produto;
import com.les.roupa.core.dominio.Resultado;
import com.les.roupa.view.helper.IViewHelper;

public class PedidoTrocaHelper implements IViewHelper {

	PedidoTroca pedidoTroca = null;
	ItemPedido itemPedido = null;
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		// Verifica qual opera��o do bot�o foi acionada
		String operacao = request.getParameter("operacao");
		
		String idPedido = null;
		String idItemPedido = null;
		String trocaPedidoInteiro = null;
		String idCliente = null;
		String novoStatusPedido = null;
		String totalPedido = null;
		
		List<PedidoTroca> todosItensPedidoTrocaSessao = new ArrayList<>();
		
		pedidoTroca = new PedidoTroca();
		
		if (("CONSULTAR").equals(operacao)) {
			idPedido = request.getParameter("idPedido");
			idItemPedido = request.getParameter("idItemPedido");

			pedidoTroca.setIdPedido(idPedido);
			pedidoTroca.setIdItemPedido(idItemPedido);
		}
		
		else if (("SALVAR").equals(operacao)) {
			trocaPedidoInteiro = request.getParameter("trocaPedidoInteiro");
			idPedido = request.getParameter("idPedido");
			
			// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
			HttpSession sessao = request.getSession();
			// pega o objeto salvo em Sess�o com o nome "itensPedidoTroca",
			// e passa para o "todosItensPedidoTrocaSessao" (fazendo o CAST para o tipo List<PedidoTroca>)
			todosItensPedidoTrocaSessao = (List<PedidoTroca>) sessao.getAttribute("itensPedidoTroca");
			
			pedidoTroca.setIdPedido(idPedido);
			pedidoTroca.setTrocaPedidoInteiro(trocaPedidoInteiro);
			pedidoTroca.setItensPedidoTroca(todosItensPedidoTrocaSessao);
		}
		
		else if (("ALTERAR").equals(operacao)) {
			idPedido = request.getParameter("idPedido");
			idCliente = request.getParameter("idCliente");
			novoStatusPedido = request.getParameter("alterarStatusPedido");
			totalPedido = request.getParameter("totalPedido");
			
			pedidoTroca.setIdPedido(idPedido);
			pedidoTroca.setIdCliente(idCliente);
			pedidoTroca.setNovoStatusPedido(novoStatusPedido);
			pedidoTroca.setTotalPedido(totalPedido);
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			
		}
		
		return pedidoTroca;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Verifica qual opera��o do bot�o foi acionada
		String operacao = request.getParameter("operacao");
		
		// Usa para escrever na tela
		PrintWriter writer = response.getWriter();
		
		if (("CONSULTAR").equals(operacao)) {
			// *********
			// usado a oper��o "CONSULTAR" para poder salvar os itens selecionados para a troca na Sess�o
			// *********
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// foi utilizado o getEntidades do resultado para poder pegar o PedidoTroca consultado
				List<EntidadeDominio> entidades = resultado.getEntidades();
				// feito o CAST de Entidade para o PedidoTroca (pegando o primeiro indice de Entidade)
				PedidoTroca pedidoTrocaEntidade = (PedidoTroca) entidades.get(0);
				
				String qtdeItemParaTroca = request.getParameter("qtdeItemParaTroca");
				
				boolean adicionaNovoItemParaTroca = true;

				PedidoTroca pedidoTroca = new PedidoTroca();
				List<ItemPedido> itemPedidoSelecionado = new ArrayList<>();
				List<PedidoTroca> itensParaAdicionarAoPedidoTroca = new ArrayList<>();		
				
				// busca o Item do Pedido selecionado na tela
				itemPedidoSelecionado = pedidoTrocaEntidade.getItemPedidoSelecionado();
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				// pega o objeto salvo em Sess�o com o nome "itensPedidoTroca",
				// e passa para o "itensParaAdicionarAoPedidoTroca" (fazendo o CAST para o tipo List<PedidoTroca>)
				itensParaAdicionarAoPedidoTroca = (List<PedidoTroca>) sessao.getAttribute("itensPedidoTroca");
				
				// ajuste no bug de quando tentar gravar um item do pedido, de um Pedido diferente ao que ja tem na Sess�o,
				// se a lista "itensParaAdicionarAoPedidoTroca" estiver vazia, ou
				// se o item do Pedido que esta sendo selecionado � do mesmo Pedido do primeiro item da Sess�o,
				// se for do mesmo Pedido, ele ser� adicionado na lista da Sess�o "itensPedidoTroca"
				if (itensParaAdicionarAoPedidoTroca.isEmpty() || itensParaAdicionarAoPedidoTroca.get(0).getItemPedido().getIdPedido().equals(itemPedidoSelecionado.get(0).getIdPedido())) {
					
					// a lista da Sess�o ja tem algum Item?
					if (itensParaAdicionarAoPedidoTroca.size() > 0) {
						// faz um la�o de repeti��o para somar a QUANTIDADE do item para troca,
						// com a quantidade do item que esta salvo na Sess�o,
						// caso seja MAIOR do que disponivel para troca, ser� mostrado uma mensagem de erro,
						// caso contrario, ser� somado com a quantidade que esta na Sess�o
						for (int i = 0; i< itensParaAdicionarAoPedidoTroca.size(); i++) {
							// o ID Item Pedido que esta na requisi��o, � igual ao ID Item Pedido que esta na Sess�o?
							if (itemPedidoSelecionado.get(0).getId().equals(itensParaAdicionarAoPedidoTroca.get(i).getItemPedido().getId())) {
								// soma a quantidade que foi selecionado na tela, com a quantidade que esta na Sess�o
								int somatoriaDasQuantidades = (Integer.parseInt(qtdeItemParaTroca) + Integer.parseInt(itensParaAdicionarAoPedidoTroca.get(i).getItemPedido().getProduto().getQuantidadeSelecionada()));
								
								if (somatoriaDasQuantidades > Integer.parseInt(itemPedidoSelecionado.get(0).getProduto().getQuantidadeSelecionada())) {
									// atribui a nova mensagem para poder mostra na pagina .JSP
									resultado.setMensagem("Quantidade selecionada para Troca, � MAIOR do que disponivel no Item!");
									
									adicionaNovoItemParaTroca = false;
									break;
								}
								else {
									// feito o CAST de INT para String, para poder atualizar a quantidade selecionada com a somatoria
									itemPedidoSelecionado.get(0).getProduto().setQuantidadeSelecionada(Integer.toString(somatoriaDasQuantidades));
									
									// atualiza o Item do Pedido no objeto "pedidoTroca"
									pedidoTroca.setItemPedido(itemPedidoSelecionado.get(0));
									
									// ".set" do ArrayList faz o seguinte:
									// set(int index, Object element):
									// Substitui o i-�simo elemento da lista pelo elemento especificado.
									itensParaAdicionarAoPedidoTroca.set(i, pedidoTroca);
									
									// atribui a nova mensagem para poder mostra na pagina .JSP
									resultado.setMensagem("Quantidade selecionada para Troca, atualizado com sucesso!");
									
									adicionaNovoItemParaTroca = false;
									break;
								}
							}
							else {
								// n�o encontrou nenhum item igual da Sess�o,
								// ent�o seta a variavel "adicionaNovoItemParaTroca" como TRUE
								adicionaNovoItemParaTroca = true;
							}
						}
						
						// verifica se � para adicionar um novo item na Sess�o para Troca
						if (adicionaNovoItemParaTroca) {
							// a QUANTIDADE do item para Troca � MAIOR que a quantidade do item que esta na tela?
							if (Integer.parseInt(qtdeItemParaTroca) > Integer.parseInt(itemPedidoSelecionado.get(0).getProduto().getQuantidadeSelecionada())) {
								// atribui a nova mensagem para poder mostra na pagina .JSP
								resultado.setMensagem("Quantidade selecionada para Troca, � MAIOR do que disponivel no Item!");
							}
							else {
								// salva na Sess�o a QUANTIDADE do Item do Pedido para Troca, conforme digitado na tela
								itemPedidoSelecionado.get(0).getProduto().setQuantidadeSelecionada(qtdeItemParaTroca);
								
								// guarda o Item do Pedido no objeto "pedidoTroca"
								pedidoTroca.setItemPedido(itemPedidoSelecionado.get(0));
								
								// passa o item selecionado para a variavel que ser� responsavel para atualizar a sess�o dos itens de troca do Pedido
								itensParaAdicionarAoPedidoTroca.add(pedidoTroca);
								
								// atribui a nova mensagem para poder mostra na pagina .JSP
								resultado.setMensagem("Item adicionado para a Troca com sucesso!");
							}
						}
					}
					// SE a lista da Sess�o esta VAZIA
					else {
						// a QUANTIDADE do item para Troca � MAIOR que a quantidade do item que esta na tela?
						if (Integer.parseInt(qtdeItemParaTroca) > Integer.parseInt(itemPedidoSelecionado.get(0).getProduto().getQuantidadeSelecionada())) {
							// atribui a nova mensagem para poder mostra na pagina .JSP
							resultado.setMensagem("Quantidade selecionada para Troca, � MAIOR do que disponivel no Item!");
						}
						// adiciona o novo item para troca na Sess�o
						else {
							// salva na Sess�o a QUANTIDADE do Item do Pedido para Troca, conforme digitado na tela
							itemPedidoSelecionado.get(0).getProduto().setQuantidadeSelecionada(qtdeItemParaTroca);
							
							// guarda o Item do Pedido no objeto "pedidoTroca"
							pedidoTroca.setItemPedido(itemPedidoSelecionado.get(0));
							
							// passa o item selecionado para a variavel que ser� responsavel para atualizar a sess�o dos itens de troca do Pedido
							itensParaAdicionarAoPedidoTroca.add(pedidoTroca);
							
							// atribui a nova mensagem para poder mostra na pagina .JSP
							resultado.setMensagem("Item adicionado para a Troca com sucesso!");
						}
					}
						
					// adiciona na lista de itens de troca do Pedido selecionado da sess�o
					// atualiza o objeto "itensPedidoTroca" que esta salvo em sess�o, com o novo item selecionado
					sessao.setAttribute("itensPedidoTroca", itensParaAdicionarAoPedidoTroca);
					
					// pendura o "resultado" na requisi��o para poder mandar para o arquivo .JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// pendura o "Pedido inteiro" na requisi��o para poder mandar para o arquivo .JSP
					//request.setAttribute("pedidoInteiro", pedidoTrocaEntidade.getPedidos().get(0));
					
					// pendura os " Itens do Pedido" na requisi��o para poder mandar para o arquivo .JSP
					//request.setAttribute("itensPedido", pedidoTrocaEntidade.getTodosItensPedido());
					
					// pendura o "Endere�o do Pedido" na requisi��o para poder mandar para o arquivo .JSP
					//request.setAttribute("enderecoPedido", pedidoTrocaEntidade.getEnderecoPedido().get(0));
					
					
					// pendura o "idPedido" na requisi��o para poder mandar para o arquivo .JSP
					Endereco enderecoPedidoParaTela = new Endereco();
					enderecoPedidoParaTela = pedidoTrocaEntidade.getEnderecoPedido().get(0);
					pedidoTrocaEntidade.getPedidos().get(0).setEndereco(enderecoPedidoParaTela);
					
					request.setAttribute("pedidoSelecionado",  pedidoTrocaEntidade.getPedidos().get(0));
					request.setAttribute("itensPedidoSelecionado", pedidoTrocaEntidade.getTodosItensPedido());
					
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/detalhePedido3_msg.jsp").forward(request, response);
				}
				else {
					// caso contr�rio, o Item do Pedido selecionado n�o � do mesmo Pedido que ja existe na Sess�o,
					// atribui a nova mensagem para poder mostra na pagina .JSP
					resultado.setMensagem("Selecione um Item do mesmo Pedido para a Troca!");
					
					// pendura o "resultado" na requisi��o para poder mandar para o arquivo .JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// adiciona na lista de itens de troca do Pedido selecionado da sess�o
					// atualiza o objeto "itensPedidoTroca" que esta salvo em sess�o, com o novo item selecionado
					sessao.setAttribute("itensPedidoTroca", itensParaAdicionarAoPedidoTroca);
					
					// pendura o "Pedido inteiro" na requisi��o para poder mandar para o arquivo .JSP
					//request.setAttribute("pedidoInteiro", pedidoTrocaEntidade.getPedidos().get(0));
					
					// pendura os " Itens do Pedido" na requisi��o para poder mandar para o arquivo .JSP
					//request.setAttribute("itensPedido", pedidoTrocaEntidade.getTodosItensPedido());
					
					// pendura o "Endere�o do Pedido" na requisi��o para poder mandar para o arquivo .JSP
					//request.setAttribute("enderecoPedido", pedidoTrocaEntidade.getEnderecoPedido().get(0));
					
					Endereco enderecoPedidoParaTela = new Endereco();
					enderecoPedidoParaTela = pedidoTrocaEntidade.getEnderecoPedido().get(0);
					pedidoTrocaEntidade.getPedidos().get(0).setEndereco(enderecoPedidoParaTela);
					
					request.setAttribute("pedidoSelecionado",  pedidoTrocaEntidade.getPedidos().get(0));
					request.setAttribute("itensPedidoSelecionado", pedidoTrocaEntidade.getTodosItensPedido());
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/detalhePedido3_msg.jsp").forward(request, response);
				}
			}
			else {
				// mostra as mensagens de ERRO se houver
				// pendura o "resultado" na requisi��o para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/index2.jsp").forward(request, response);
			}
		}
		
		else if (("SALVAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				List<PedidoTroca> ItensPedidoTrocaVazio = new ArrayList<>();
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				
				// limpa os itens dos pedidos de troca selecionados da sess�o,
				// atualiza o objeto "itensPedidoTroca" que esta salvo em sess�o, com o novo objeto de Item Pedido de Troca vazio
				sessao.setAttribute("itensPedidoTroca", ItensPedidoTrocaVazio);
				
				// atribui a nova mensagem para poder mostra na pagina .JSP
				resultado.setMensagem("Troca solicitada com sucesso!");
				
				// pendura o "resultado" na requisi��o para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/minhaConta2.jsp").forward(request, response);
			}
			else {
				// mostra as mensagens de ERRO se houver
				// pendura o "resultado" na requisi��o para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/index2.jsp").forward(request, response);
			}
		}
		
		else if (("ALTERAR").equals(operacao)) {
			
			// ADMIN - ALTERAR STATUS PEDIDO
			
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// foi utilizado o getEntidades do resultado para poder pegar o PedidoTroca no sistema
				List<EntidadeDominio> entidades = resultado.getEntidades();
				// feito o CAST de Entidade para o PedidoTroca (pegando o primeiro indice de Entidade)
				PedidoTroca pedidoTrocaEntidade = (PedidoTroca) entidades.get(0);
				
				// se for algum desses status, ser� feito a ReEntrada no Estoque e gerado o Cupom de desconto do mesmo
				if (pedidoTrocaEntidade.getNovoStatusPedido().equals("TROCA EFETUADA") || pedidoTrocaEntidade.getNovoStatusPedido().equals("CANCELAMENTO EFETUADO")) {
					// ajusta o bug de n�o realizar a Reentrada no Estoque mais de uma vez,
					// verifica se o Pedido selecionado j� esta com o status "TROCA EFETUADA"
					if (pedidoTrocaEntidade.getPedido().getStatus().equals("TROCA EFETUADA")) {
						// atribui a nova mensagem para poder mostra na pagina .JSP
						resultado.setMensagem("Pedido j� esta com o status TROCA EFETUADA !");
					}
					// verifica se o Pedido selecionado j� esta com o status "CANCELAMENTO EFETUADO"
					else if (pedidoTrocaEntidade.getPedido().getStatus().equals("CANCELAMENTO EFETUADO")) {
						// atribui a nova mensagem para poder mostra na pagina .JSP
						resultado.setMensagem("Pedido j� esta com o status CANCELAMENTO EFETUADO!");
					}
					// caso contr�rio, ser� realizada a ReEntrada no Estoque e gerado o Cupom
					else {
						// atribui a nova mensagem para poder mostra na pagina .JSP
						resultado.setMensagem("ReEntrada no Estoque e Cupom gerado com sucesso!");
					}
					
					// pendura o "resultado" na requisi��o para poder mandar para o arquivo .JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/indexAdm2.jsp").forward(request, response);
				}
				else {					
					// atribui a nova mensagem para poder mostra na pagina .JSP
					resultado.setMensagem("Status do Pedido alterado com sucesso!");
					
					// pendura o "resultado" na requisi��o para poder mandar para o arquivo .JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/indexAdm2.jsp").forward(request, response);
				}
			}
			else {
				// mostra as mensagens de ERRO se houver
				// pendura o "resultado" na requisi��o para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/indexAdm2.jsp").forward(request, response);
			}
		}
		
		else if (("EXCLUIR").equals(operacao)) {

		}
		
	}

}