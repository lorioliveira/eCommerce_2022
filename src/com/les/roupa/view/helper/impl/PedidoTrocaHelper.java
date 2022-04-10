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
		
		// Verifica qual operação do botão foi acionada
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
			// pega o objeto salvo em Sessão com o nome "itensPedidoTroca",
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
		
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
		// Usa para escrever na tela
		PrintWriter writer = response.getWriter();
		
		if (("CONSULTAR").equals(operacao)) {
			// *********
			// usado a operção "CONSULTAR" para poder salvar os itens selecionados para a troca na Sessão
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
				// pega o objeto salvo em Sessão com o nome "itensPedidoTroca",
				// e passa para o "itensParaAdicionarAoPedidoTroca" (fazendo o CAST para o tipo List<PedidoTroca>)
				itensParaAdicionarAoPedidoTroca = (List<PedidoTroca>) sessao.getAttribute("itensPedidoTroca");
				
				// ajuste no bug de quando tentar gravar um item do pedido, de um Pedido diferente ao que ja tem na Sessão,
				// se a lista "itensParaAdicionarAoPedidoTroca" estiver vazia, ou
				// se o item do Pedido que esta sendo selecionado é do mesmo Pedido do primeiro item da Sessão,
				// se for do mesmo Pedido, ele será adicionado na lista da Sessão "itensPedidoTroca"
				if (itensParaAdicionarAoPedidoTroca.isEmpty() || itensParaAdicionarAoPedidoTroca.get(0).getItemPedido().getIdPedido().equals(itemPedidoSelecionado.get(0).getIdPedido())) {
					
					// a lista da Sessão ja tem algum Item?
					if (itensParaAdicionarAoPedidoTroca.size() > 0) {
						// faz um laço de repetição para somar a QUANTIDADE do item para troca,
						// com a quantidade do item que esta salvo na Sessão,
						// caso seja MAIOR do que disponivel para troca, será mostrado uma mensagem de erro,
						// caso contrario, será somado com a quantidade que esta na Sessão
						for (int i = 0; i< itensParaAdicionarAoPedidoTroca.size(); i++) {
							// o ID Item Pedido que esta na requisição, é igual ao ID Item Pedido que esta na Sessão?
							if (itemPedidoSelecionado.get(0).getId().equals(itensParaAdicionarAoPedidoTroca.get(i).getItemPedido().getId())) {
								// soma a quantidade que foi selecionado na tela, com a quantidade que esta na Sessão
								int somatoriaDasQuantidades = (Integer.parseInt(qtdeItemParaTroca) + Integer.parseInt(itensParaAdicionarAoPedidoTroca.get(i).getItemPedido().getProduto().getQuantidadeSelecionada()));
								
								if (somatoriaDasQuantidades > Integer.parseInt(itemPedidoSelecionado.get(0).getProduto().getQuantidadeSelecionada())) {
									// atribui a nova mensagem para poder mostra na pagina .JSP
									resultado.setMensagem("Quantidade selecionada para Troca, é MAIOR do que disponivel no Item!");
									
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
									// Substitui o i-ésimo elemento da lista pelo elemento especificado.
									itensParaAdicionarAoPedidoTroca.set(i, pedidoTroca);
									
									// atribui a nova mensagem para poder mostra na pagina .JSP
									resultado.setMensagem("Quantidade selecionada para Troca, atualizado com sucesso!");
									
									adicionaNovoItemParaTroca = false;
									break;
								}
							}
							else {
								// não encontrou nenhum item igual da Sessão,
								// então seta a variavel "adicionaNovoItemParaTroca" como TRUE
								adicionaNovoItemParaTroca = true;
							}
						}
						
						// verifica se é para adicionar um novo item na Sessão para Troca
						if (adicionaNovoItemParaTroca) {
							// a QUANTIDADE do item para Troca é MAIOR que a quantidade do item que esta na tela?
							if (Integer.parseInt(qtdeItemParaTroca) > Integer.parseInt(itemPedidoSelecionado.get(0).getProduto().getQuantidadeSelecionada())) {
								// atribui a nova mensagem para poder mostra na pagina .JSP
								resultado.setMensagem("Quantidade selecionada para Troca, é MAIOR do que disponivel no Item!");
							}
							else {
								// salva na Sessão a QUANTIDADE do Item do Pedido para Troca, conforme digitado na tela
								itemPedidoSelecionado.get(0).getProduto().setQuantidadeSelecionada(qtdeItemParaTroca);
								
								// guarda o Item do Pedido no objeto "pedidoTroca"
								pedidoTroca.setItemPedido(itemPedidoSelecionado.get(0));
								
								// passa o item selecionado para a variavel que será responsavel para atualizar a sessão dos itens de troca do Pedido
								itensParaAdicionarAoPedidoTroca.add(pedidoTroca);
								
								// atribui a nova mensagem para poder mostra na pagina .JSP
								resultado.setMensagem("Item adicionado para a Troca com sucesso!");
							}
						}
					}
					// SE a lista da Sessão esta VAZIA
					else {
						// a QUANTIDADE do item para Troca é MAIOR que a quantidade do item que esta na tela?
						if (Integer.parseInt(qtdeItemParaTroca) > Integer.parseInt(itemPedidoSelecionado.get(0).getProduto().getQuantidadeSelecionada())) {
							// atribui a nova mensagem para poder mostra na pagina .JSP
							resultado.setMensagem("Quantidade selecionada para Troca, é MAIOR do que disponivel no Item!");
						}
						// adiciona o novo item para troca na Sessão
						else {
							// salva na Sessão a QUANTIDADE do Item do Pedido para Troca, conforme digitado na tela
							itemPedidoSelecionado.get(0).getProduto().setQuantidadeSelecionada(qtdeItemParaTroca);
							
							// guarda o Item do Pedido no objeto "pedidoTroca"
							pedidoTroca.setItemPedido(itemPedidoSelecionado.get(0));
							
							// passa o item selecionado para a variavel que será responsavel para atualizar a sessão dos itens de troca do Pedido
							itensParaAdicionarAoPedidoTroca.add(pedidoTroca);
							
							// atribui a nova mensagem para poder mostra na pagina .JSP
							resultado.setMensagem("Item adicionado para a Troca com sucesso!");
						}
					}
						
					// adiciona na lista de itens de troca do Pedido selecionado da sessão
					// atualiza o objeto "itensPedidoTroca" que esta salvo em sessão, com o novo item selecionado
					sessao.setAttribute("itensPedidoTroca", itensParaAdicionarAoPedidoTroca);
					
					// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// pendura o "Pedido inteiro" na requisição para poder mandar para o arquivo .JSP
					//request.setAttribute("pedidoInteiro", pedidoTrocaEntidade.getPedidos().get(0));
					
					// pendura os " Itens do Pedido" na requisição para poder mandar para o arquivo .JSP
					//request.setAttribute("itensPedido", pedidoTrocaEntidade.getTodosItensPedido());
					
					// pendura o "Endereço do Pedido" na requisição para poder mandar para o arquivo .JSP
					//request.setAttribute("enderecoPedido", pedidoTrocaEntidade.getEnderecoPedido().get(0));
					
					
					// pendura o "idPedido" na requisição para poder mandar para o arquivo .JSP
					Endereco enderecoPedidoParaTela = new Endereco();
					enderecoPedidoParaTela = pedidoTrocaEntidade.getEnderecoPedido().get(0);
					pedidoTrocaEntidade.getPedidos().get(0).setEndereco(enderecoPedidoParaTela);
					
					request.setAttribute("pedidoSelecionado",  pedidoTrocaEntidade.getPedidos().get(0));
					request.setAttribute("itensPedidoSelecionado", pedidoTrocaEntidade.getTodosItensPedido());
					
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/detalhePedido3_msg.jsp").forward(request, response);
				}
				else {
					// caso contrário, o Item do Pedido selecionado não é do mesmo Pedido que ja existe na Sessão,
					// atribui a nova mensagem para poder mostra na pagina .JSP
					resultado.setMensagem("Selecione um Item do mesmo Pedido para a Troca!");
					
					// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// adiciona na lista de itens de troca do Pedido selecionado da sessão
					// atualiza o objeto "itensPedidoTroca" que esta salvo em sessão, com o novo item selecionado
					sessao.setAttribute("itensPedidoTroca", itensParaAdicionarAoPedidoTroca);
					
					// pendura o "Pedido inteiro" na requisição para poder mandar para o arquivo .JSP
					//request.setAttribute("pedidoInteiro", pedidoTrocaEntidade.getPedidos().get(0));
					
					// pendura os " Itens do Pedido" na requisição para poder mandar para o arquivo .JSP
					//request.setAttribute("itensPedido", pedidoTrocaEntidade.getTodosItensPedido());
					
					// pendura o "Endereço do Pedido" na requisição para poder mandar para o arquivo .JSP
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
				// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
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
				
				// limpa os itens dos pedidos de troca selecionados da sessão,
				// atualiza o objeto "itensPedidoTroca" que esta salvo em sessão, com o novo objeto de Item Pedido de Troca vazio
				sessao.setAttribute("itensPedidoTroca", ItensPedidoTrocaVazio);
				
				// atribui a nova mensagem para poder mostra na pagina .JSP
				resultado.setMensagem("Troca solicitada com sucesso!");
				
				// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/minhaConta2.jsp").forward(request, response);
			}
			else {
				// mostra as mensagens de ERRO se houver
				// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
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
				
				// se for algum desses status, será feito a ReEntrada no Estoque e gerado o Cupom de desconto do mesmo
				if (pedidoTrocaEntidade.getNovoStatusPedido().equals("TROCA EFETUADA") || pedidoTrocaEntidade.getNovoStatusPedido().equals("CANCELAMENTO EFETUADO")) {
					// ajusta o bug de não realizar a Reentrada no Estoque mais de uma vez,
					// verifica se o Pedido selecionado já esta com o status "TROCA EFETUADA"
					if (pedidoTrocaEntidade.getPedido().getStatus().equals("TROCA EFETUADA")) {
						// atribui a nova mensagem para poder mostra na pagina .JSP
						resultado.setMensagem("Pedido já esta com o status TROCA EFETUADA !");
					}
					// verifica se o Pedido selecionado já esta com o status "CANCELAMENTO EFETUADO"
					else if (pedidoTrocaEntidade.getPedido().getStatus().equals("CANCELAMENTO EFETUADO")) {
						// atribui a nova mensagem para poder mostra na pagina .JSP
						resultado.setMensagem("Pedido já esta com o status CANCELAMENTO EFETUADO!");
					}
					// caso contrário, será realizada a ReEntrada no Estoque e gerado o Cupom
					else {
						// atribui a nova mensagem para poder mostra na pagina .JSP
						resultado.setMensagem("ReEntrada no Estoque e Cupom gerado com sucesso!");
					}
					
					// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/indexAdm2.jsp").forward(request, response);
				}
				else {					
					// atribui a nova mensagem para poder mostra na pagina .JSP
					resultado.setMensagem("Status do Pedido alterado com sucesso!");
					
					// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/indexAdm2.jsp").forward(request, response);
				}
			}
			else {
				// mostra as mensagens de ERRO se houver
				// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/indexAdm2.jsp").forward(request, response);
			}
		}
		
		else if (("EXCLUIR").equals(operacao)) {

		}
		
	}

}