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

import com.les.roupa.core.dao.impl.CupomDAO;
//import com.les.roupa.core.dao.impl.EstoqueDAO;
import com.les.roupa.core.dao.impl.ItemPedidoDAO;
import com.les.roupa.core.dao.impl.PedidoDAO;
import com.les.roupa.core.dao.impl.ProdutoDAO;
import com.les.roupa.core.dominio.Cupom;
import com.les.roupa.core.dominio.EntidadeDominio;
//import com.les.roupa.core.dominio.Estoque;
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
		
		pedidoTroca = new PedidoTroca();
		
		if (("CONSULTAR").equals(operacao)) {

		}
		
		else if (("SALVAR").equals(operacao)) {

		}
		
		else if (("ALTERAR").equals(operacao)) {
			
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
			// *************************
			// usado a opera��o "CONSULTAR" para poder salvar, os itens selecionados para a troca, na Sess�o
			// *************************
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				String idPedido = request.getParameter("idPedido");
				String idItemPedido = request.getParameter("idItemPedido");
				String qtdeItemParaTroca = request.getParameter("qtdeItemParaTroca");
				
				boolean adicionaNovoItemParaTroca = true;
				
				ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
				PedidoTroca pedidoTroca = new PedidoTroca();
				List<ItemPedido> itemPedidoSelecionado = new ArrayList<>();
				List<PedidoTroca> itensParaAdicionarAoPedidoTroca = new ArrayList<>();		
				
				// busca o Item do Pedido selecionado na tela
				itemPedidoSelecionado = itemPedidoDAO.consultarItemPedidoById(idItemPedido);
				
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
								int somatoriaDasQuantidades = (Integer.parseInt(qtdeItemParaTroca) + Integer.parseInt(itensParaAdicionarAoPedidoTroca.get(i).getItemPedido().getQtdeProduto()));
								
								if (somatoriaDasQuantidades > Integer.parseInt(itemPedidoSelecionado.get(0).getQtdeProduto())) {
									// atribui a nova mensagem para poder mostra na pagina .JSP
									resultado.setMensagem("Quantidade selecionada para Troca, � MAIOR do que disponivel no Item!<br>");
									
									adicionaNovoItemParaTroca = false;
									break;
								}
								else {
									// feito o CAST de INT para String, para poder atualizar a quantidade selecionada com a somatoria
									itemPedidoSelecionado.get(0).setQtdeProduto(Integer.toString(somatoriaDasQuantidades));
									
									// atualiza o Item do Pedido no objeto "pedidoTroca"
									pedidoTroca.setItemPedido(itemPedidoSelecionado.get(0));
									
									// ".set" do ArrayList faz o seguinte:
									// set(int index, Object element):
									// Substitui o i-�simo elemento da lista pelo elemento especificado.
									itensParaAdicionarAoPedidoTroca.set(i, pedidoTroca);
									
									// atribui a nova mensagem para poder mostra na pagina .JSP
									resultado.setMensagem("Quantidade selecionada para Troca, atualizado com sucesso! <br>");
									
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
							// salva na Sess�o a QUANTIDADE do Item do Pedido para Troca, conforme digitado na tela
							itemPedidoSelecionado.get(0).setQtdeProduto(qtdeItemParaTroca);
							
							// guarda o Item do Pedido no objeto "pedidoTroca"
							pedidoTroca.setItemPedido(itemPedidoSelecionado.get(0));
							
							// passa o item selecionado para a variavel que ser� responsavel para atualizar a sess�o dos itens de troca do Pedido
							itensParaAdicionarAoPedidoTroca.add(pedidoTroca);
							
							// atribui a nova mensagem para poder mostra na pagina .JSP
							resultado.setMensagem("Item adicionado para a Troca com sucesso!<br>");
						}
					}
					// SE a lista da Sess�o esta VAZIA
					else {
						// a QUANTIDADE do item para Troca � MAIOR que a quantidade do item que esta na tela?
						if (Integer.parseInt(qtdeItemParaTroca) > Integer.parseInt(itemPedidoSelecionado.get(0).getQtdeProduto())) {
							// atribui a nova mensagem para poder mostra na pagina .JSP
							resultado.setMensagem("Quantidade selecionada para Troca, � MAIOR do que disponivel no Item!<br>");
						}
						// adiciona o novo item para troca na Sess�o
						else {
							// salva na Sess�o a QUANTIDADE do Item do Pedido para Troca, conforme digitado na tela
							itemPedidoSelecionado.get(0).setQtdeProduto(qtdeItemParaTroca);
							
							// guarda o Item do Pedido no objeto "pedidoTroca"
							pedidoTroca.setItemPedido(itemPedidoSelecionado.get(0));
							
							// passa o item selecionado para a variavel que ser� responsavel para atualizar a sess�o dos itens de troca do Pedido
							itensParaAdicionarAoPedidoTroca.add(pedidoTroca);
							
							// atribui a nova mensagem para poder mostra na pagina .JSP
							resultado.setMensagem("Item adicionado para a Troca com sucesso! <br>");
						}
					}
						
					// adiciona na lista de itens de troca do Pedido selecionado da sess�o
					// atualiza o objeto "itensPedidoTroca" que esta salvo em sess�o, com o novo item selecionado
					sessao.setAttribute("itensPedidoTroca", itensParaAdicionarAoPedidoTroca);
					
					// pendura o "resultado" na requisi��o para poder mandar para o arquivo .JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// pendura o "id" do Pedido na requisi��o para poder mandar para o arquivo .JSP
					request.setAttribute("id_pedido", idPedido);
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				}
				else {
					// caso contr�rio, o Item do Pedido selecionado n�o � do mesmo Pedido que ja existe na Sess�o,
					// atribui a nova mensagem para poder mostra na pagina .JSP
					resultado.setMensagem("Selecione um Item do mesmo Pedido para a Troca!<br>");
					
					// pendura o "resultado" na requisi��o para poder mandar para o arquivo .JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// pendura o "id" do Pedido na requisi��o para poder mandar para o arquivo .JSP
					request.setAttribute("id_pedido", idPedido);
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				}
			}
			else {
				// mostra as mensagens de ERRO se houver
				// pendura o "resultado" na requisi��o para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
			}
		}
		
		else if (("SALVAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				String trocaPedidoInteiro = request.getParameter("trocaPedidoInteiro");
				String idPedido = request.getParameter("id");
				
				PedidoDAO pedidoDAO = new PedidoDAO();
				ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
				Pedido pedido = new Pedido();
				Pedido novoPedido = new Pedido();
				ItemPedido novoItemPedido = new ItemPedido();
				ItemPedido itemPedidoTrocaInteira = new ItemPedido();
				List<ItemPedido> alteraQtdeAndStatusItemPedido = new ArrayList<>();	
				List<PedidoTroca> itensPedidoParaAdicionarNaSessao = new ArrayList<>();	
				List<PedidoTroca> todosItensPedidoTrocaSessao = new ArrayList<>();
				List<PedidoTroca> ItensPedidoTrocaVazio = new ArrayList<>();
				List<Pedido> pedidoOriginal =  new ArrayList<>();
				double total_itens = 0;
				
				// salva a data atual no Pedido e na sequencia no Item do Pedido
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date date = new Date();
				String dataAtual;
				dataAtual = dateFormat.format(date);
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				
				// verifica se foi acionado o bot�o para realizar a troca inteira do Pedido,
				// (acionado pela tela "pedidos" do cliente),
				// caso foi acionado, ser� feito o preenchimento da lista "itensPedidoTroca" que esta salvo em Sess�o,
				// com os itens do Pedido que foi acionado a troca inteira
				if (trocaPedidoInteiro.equals("1")) {
					// seta o valor do ID do Pedido com o valor que foi enviado pela tela
					itemPedidoTrocaInteira.setIdPedido(idPedido);
					
					// busca os Itens do Pedido pelo ID do Pedido
					List<ItemPedido> itens_pedido = itemPedidoDAO.consultarItemPedidoById(idPedido);
					
					for(ItemPedido item : itens_pedido) {
						
						// guarda o Item do Pedido no objeto "pedidoTroca"
						PedidoTroca pedidoTroca = new PedidoTroca();
						pedidoTroca.setItemPedido(item);
						
						// passa o item selecionado para a variavel que ser� responsavel para atualizar a sess�o dos itens de troca do Pedido
						itensPedidoParaAdicionarNaSessao.add(pedidoTroca);
					}
					
					// atualiza o objeto "itensPedidoTroca" que esta salvo em sess�o, com os novos itens do Pedido selecionado
					sessao.setAttribute("itensPedidoTroca", itensPedidoParaAdicionarNaSessao);
				}
				
				// pega o objeto salvo em Sess�o com o nome "itensPedidoTroca",
				// e passa para o "todosItensPedidoTrocaSessao" (fazendo o CAST para o tipo List<PedidoTroca>)
				todosItensPedidoTrocaSessao = (List<PedidoTroca>) sessao.getAttribute("itensPedidoTroca");
				
				// buscar as informa��es do Pedido que esta vinculado no Item do Pedido de Troca da Sess�o,
				// para quando criar um novo Pedido, salvar com as mesmas informa��es do Pedido Original
				pedidoOriginal = pedidoDAO.consultarPedidoById(todosItensPedidoTrocaSessao.get(0).getItemPedido().getIdPedido());
				
				// faz um la�o para calcular o total dos itens do pedido de troca
				for (int i = 0; i< todosItensPedidoTrocaSessao.size(); i++) {
					// faz o calculo dos itens que ser�o solicitados para a troca
					// calculo do total dos itens (quantidade do item (*) o valor do item "pre�o de venda")
					total_itens += (Double.parseDouble(todosItensPedidoTrocaSessao.get(i).getItemPedido().getQtdeProduto()) * Double.parseDouble(todosItensPedidoTrocaSessao.get(i).getItemPedido().getProduto().getPrecoVenda()));
				}
				
				
				 novoPedido.setPrecoTotalProduto("0"); 
				 novoPedido.setPrecoFrete("0");
				 novoPedido.setPrecoTotal(Double.toString(total_itens));
				 novoPedido.setStatusPedido("TROCA SOLICITADA");
				 novoPedido.setIdCliente(pedidoOriginal.get(0).getIdCliente());
				 novoPedido.setIdEndereco(pedidoOriginal.get(0).getIdEndereco());
				 novoPedido.setCartao1(pedidoOriginal.get(0).getCartao1());
				 novoPedido.setValorCartao1(pedidoOriginal.get(0).getValorCartao1());
				 novoPedido.setCartao2(pedidoOriginal.get(0).getCartao2());
				 novoPedido.setValorCartao2(pedidoOriginal.get(0).getValorCartao2());
				 novoPedido.setDesconto("0"); 
				 
				novoPedido.setDt_cadastro(dataAtual);
				
				// salva o novo Pedido com o status TROCA SOLICITADA,
				// com base nos Itens do Pedido de Troca da Sess�o,
				pedidoDAO.salvar(novoPedido);
				
				// consulta o ultimo Pedido cadastrado para poder pegar o ID do Pedido,
				// para poder salvar na tabela "item_pedido"
				List<Pedido> ultimoPedido = pedidoDAO.consultarUltimoPedidoCadastrado(pedido);
				
				// faz um la�o para percorrer todos os itens que esta na Sess�o "itensPedidoTroca",
				// para cria os novos Itens do Pedido
				for (int i = 0; i< todosItensPedidoTrocaSessao.size(); i++) {
					novoItemPedido.setProduto(todosItensPedidoTrocaSessao.get(i).getItemPedido().getProduto());
					novoItemPedido.setIdPedido(ultimoPedido.get(0).getId());
					novoItemPedido.setQtdeProduto(todosItensPedidoTrocaSessao.get(i).getItemPedido().getQtdeProduto());
					novoItemPedido.setDt_cadastro(ultimoPedido.get(0).getDt_cadastro());
					
					// salva o novo Item do Pedido
					itemPedidoDAO.salvar(novoItemPedido);
				}
				
				// Alterar a quantidade do pedido original pela quantidade da troca solicitada
				 				
				// altera a Quantidade e o Status do Item do Pedido que foi selecionado no Pedido.
				for (int i = 0; i< todosItensPedidoTrocaSessao.size(); i++) {
					int novaQuantidadeDoItemPedido = 0;
					
					// busca o item do pedido para fazer a somatoria
					alteraQtdeAndStatusItemPedido = itemPedidoDAO.consultarItemPedidoById(todosItensPedidoTrocaSessao.get(i).getItemPedido().getId());
					
					// faz o calculo do novo valor da quantidade do item do pedido
					// calculo da nova quantidade (quantidade do item que esta salvo no banco (-) o valor da quantidade selecionada na Sess�o)
					novaQuantidadeDoItemPedido = (Integer.parseInt(alteraQtdeAndStatusItemPedido.get(0).getQtdeProduto()) - Integer.parseInt(todosItensPedidoTrocaSessao.get(i).getItemPedido().getQtdeProduto()));
					
					// faz a altera��o da nova quantidade do item do pedido no banco
					itemPedidoDAO.alterarQtdeItemPedido(Integer.toString(novaQuantidadeDoItemPedido), todosItensPedidoTrocaSessao.get(i).getItemPedido().getId());
				
				}
				
				// limpa os itens dos pedidos de troca selecionados da sess�o,
				// atualiza o objeto "itensPedidoTroca" que esta salvo em sess�o, com o novo objeto de Item Pedido de Troca vazio
				sessao.setAttribute("itensPedidoTroca", ItensPedidoTrocaVazio);
				
				// atribui a nova mensagem para poder mostra na pagina .JSP
				resultado.setMensagem("Troca solicitada com sucesso! <br>");
				
				// pendura o "resultado" na requisi��o para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
			}
			else {
				// mostra as mensagens de ERRO se houver
				// pendura o "resultado" na requisi��o para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
			}
		}
		
		else if (("ALTERAR").equals(operacao)) {
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {

		}
		
	}

}
