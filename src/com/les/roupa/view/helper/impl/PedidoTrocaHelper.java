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

/**
 * ViewHelper - Pedido (Troca)
 * @author Lorena Oliveira
 *
 */
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
			
			
			HttpSession sessao = request.getSession();
			
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
				
				
				HttpSession sessao = request.getSession();
				
				itensParaAdicionarAoPedidoTroca = (List<PedidoTroca>) sessao.getAttribute("itensPedidoTroca");
				
				
				if (itensParaAdicionarAoPedidoTroca.isEmpty() || itensParaAdicionarAoPedidoTroca.get(0).getItemPedido().getIdPedido().equals(itemPedidoSelecionado.get(0).getIdPedido())) {
					
					// a lista da Sessão ja tem algum Item?
					if (itensParaAdicionarAoPedidoTroca.size() > 0) {

						for (int i = 0; i< itensParaAdicionarAoPedidoTroca.size(); i++) {
							// o ID Item Pedido que esta na requisição, é igual ao ID Item Pedido que esta na Sessão?
							if (itemPedidoSelecionado.get(0).getId().equals(itensParaAdicionarAoPedidoTroca.get(i).getItemPedido().getId())) {
								
								int somatoriaDasQuantidades = (Integer.parseInt(qtdeItemParaTroca) + Integer.parseInt(itensParaAdicionarAoPedidoTroca.get(i).getItemPedido().getProduto().getQuantidadeSelecionada()));
								
								if (somatoriaDasQuantidades > Integer.parseInt(itemPedidoSelecionado.get(0).getProduto().getQuantidadeSelecionada())) {
									// atribui a nova mensagem 
									resultado.setMensagem("Quantidade selecionada para Troca, é MAIOR do que disponivel no Item!");
									
									adicionaNovoItemParaTroca = false;
									break;
								}
								else {
									// atualiza a quantidade selecionada com a somatoria
									itemPedidoSelecionado.get(0).getProduto().setQuantidadeSelecionada(Integer.toString(somatoriaDasQuantidades));
									
									// atualiza o Item do Pedido no objeto "pedidoTroca"
									pedidoTroca.setItemPedido(itemPedidoSelecionado.get(0));
									
									
									itensParaAdicionarAoPedidoTroca.set(i, pedidoTroca);
									
									// atribui a nova mensagem 
									resultado.setMensagem("Quantidade selecionada para Troca, atualizado com sucesso!");
									
									adicionaNovoItemParaTroca = false;
									break;
								}
							}
							else {
								// não encontrou nenhum item igual da Sessão,
								// então a variavel "adicionaNovoItemParaTroca" = TRUE
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
								// salva na Sessão a QUANTIDADE do Item do Pedido para Troca
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
					
					else {
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
							
							// atribui a nova mensagem 
							resultado.setMensagem("Item adicionado para a Troca com sucesso!");
						}
					}
						
					
					sessao.setAttribute("itensPedidoTroca", itensParaAdicionarAoPedidoTroca);
					
					// pendura o "resultado" na requisição 
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					
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
					resultado.setMensagem("Selecione um Item do mesmo Pedido para a Troca!");
					
					// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// adiciona na lista de itens de troca do Pedido selecionado da sessão
					// atualiza o objeto "itensPedidoTroca" que esta salvo em sessão, com o novo item selecionado
					sessao.setAttribute("itensPedidoTroca", itensParaAdicionarAoPedidoTroca);
					
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
					
					// verifica se o Pedido selecionado já esta com o status "TROCA EFETUADA"
					if (pedidoTrocaEntidade.getPedido().getStatus().equals("TROCA EFETUADA")) {
						
						// atribui a nova mensagem
						resultado.setMensagem("Pedido já esta com o status TROCA EFETUADA !");
					}
					
					else if (pedidoTrocaEntidade.getPedido().getStatus().equals("CANCELAMENTO EFETUADO")) {
						// atribui a nova mensagem para poder mostra na pagina .JSP
						resultado.setMensagem("Pedido já esta com o status CANCELAMENTO EFETUADO!");
					}
					
					else {
						// atribui a nova mensagem 
						resultado.setMensagem("ReEntrada no Estoque e Cupom gerado com sucesso!");
					}
					
					
					HttpSession sessao = request.getSession();
					
					// atualiza os pedidos conforme a alteração do status do mesmo
					sessao.setAttribute("todosPedidos", pedidoTrocaEntidade.getPedidos());
					
					// pendura o "resultado" na requisição 
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/indexAdm2.jsp").forward(request, response);
				}
				else {
					
					HttpSession sessao = request.getSession();
					
					// atualiza os pedidos conforme a alteração do status do mesmo
					sessao.setAttribute("todosPedidos", pedidoTrocaEntidade.getPedidos());
					
					// atribui a nova mensagem 
					resultado.setMensagem("Status do Pedido alterado com sucesso!");
					
					// pendura o "resultado" na requisição 
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/indexAdm2.jsp").forward(request, response);
				}
			}
			else {
				// mostra as mensagens de ERRO se houver
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/indexAdm2.jsp").forward(request, response);
			}
		}
		
		else if (("EXCLUIR").equals(operacao)) {

		}
		
	}

}