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
		
		// Verifica qual operação do botão foi acionada
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
		
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
		// Usa para escrever na tela
		PrintWriter writer = response.getWriter();
		
		if (("CONSULTAR").equals(operacao)) {
			// *************************
			// usado a operação "CONSULTAR" para poder salvar, os itens selecionados para a troca, na Sessão
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
								int somatoriaDasQuantidades = (Integer.parseInt(qtdeItemParaTroca) + Integer.parseInt(itensParaAdicionarAoPedidoTroca.get(i).getItemPedido().getQtdeProduto()));
								
								if (somatoriaDasQuantidades > Integer.parseInt(itemPedidoSelecionado.get(0).getQtdeProduto())) {
									// atribui a nova mensagem para poder mostra na pagina .JSP
									resultado.setMensagem("Quantidade selecionada para Troca, é MAIOR do que disponivel no Item!<br>");
									
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
									// Substitui o i-ésimo elemento da lista pelo elemento especificado.
									itensParaAdicionarAoPedidoTroca.set(i, pedidoTroca);
									
									// atribui a nova mensagem para poder mostra na pagina .JSP
									resultado.setMensagem("Quantidade selecionada para Troca, atualizado com sucesso! <br>");
									
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
							// salva na Sessão a QUANTIDADE do Item do Pedido para Troca, conforme digitado na tela
							itemPedidoSelecionado.get(0).setQtdeProduto(qtdeItemParaTroca);
							
							// guarda o Item do Pedido no objeto "pedidoTroca"
							pedidoTroca.setItemPedido(itemPedidoSelecionado.get(0));
							
							// passa o item selecionado para a variavel que será responsavel para atualizar a sessão dos itens de troca do Pedido
							itensParaAdicionarAoPedidoTroca.add(pedidoTroca);
							
							// atribui a nova mensagem para poder mostra na pagina .JSP
							resultado.setMensagem("Item adicionado para a Troca com sucesso!<br>");
						}
					}
					// SE a lista da Sessão esta VAZIA
					else {
						// a QUANTIDADE do item para Troca é MAIOR que a quantidade do item que esta na tela?
						if (Integer.parseInt(qtdeItemParaTroca) > Integer.parseInt(itemPedidoSelecionado.get(0).getQtdeProduto())) {
							// atribui a nova mensagem para poder mostra na pagina .JSP
							resultado.setMensagem("Quantidade selecionada para Troca, é MAIOR do que disponivel no Item!<br>");
						}
						// adiciona o novo item para troca na Sessão
						else {
							// salva na Sessão a QUANTIDADE do Item do Pedido para Troca, conforme digitado na tela
							itemPedidoSelecionado.get(0).setQtdeProduto(qtdeItemParaTroca);
							
							// guarda o Item do Pedido no objeto "pedidoTroca"
							pedidoTroca.setItemPedido(itemPedidoSelecionado.get(0));
							
							// passa o item selecionado para a variavel que será responsavel para atualizar a sessão dos itens de troca do Pedido
							itensParaAdicionarAoPedidoTroca.add(pedidoTroca);
							
							// atribui a nova mensagem para poder mostra na pagina .JSP
							resultado.setMensagem("Item adicionado para a Troca com sucesso! <br>");
						}
					}
						
					// adiciona na lista de itens de troca do Pedido selecionado da sessão
					// atualiza o objeto "itensPedidoTroca" que esta salvo em sessão, com o novo item selecionado
					sessao.setAttribute("itensPedidoTroca", itensParaAdicionarAoPedidoTroca);
					
					// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// pendura o "id" do Pedido na requisição para poder mandar para o arquivo .JSP
					request.setAttribute("id_pedido", idPedido);
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				}
				else {
					// caso contrário, o Item do Pedido selecionado não é do mesmo Pedido que ja existe na Sessão,
					// atribui a nova mensagem para poder mostra na pagina .JSP
					resultado.setMensagem("Selecione um Item do mesmo Pedido para a Troca!<br>");
					
					// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// pendura o "id" do Pedido na requisição para poder mandar para o arquivo .JSP
					request.setAttribute("id_pedido", idPedido);
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				}
			}
			else {
				// mostra as mensagens de ERRO se houver
				// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
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
				
				// verifica se foi acionado o botão para realizar a troca inteira do Pedido,
				// (acionado pela tela "pedidos" do cliente),
				// caso foi acionado, será feito o preenchimento da lista "itensPedidoTroca" que esta salvo em Sessão,
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
						
						// passa o item selecionado para a variavel que será responsavel para atualizar a sessão dos itens de troca do Pedido
						itensPedidoParaAdicionarNaSessao.add(pedidoTroca);
					}
					
					// atualiza o objeto "itensPedidoTroca" que esta salvo em sessão, com os novos itens do Pedido selecionado
					sessao.setAttribute("itensPedidoTroca", itensPedidoParaAdicionarNaSessao);
				}
				
				// pega o objeto salvo em Sessão com o nome "itensPedidoTroca",
				// e passa para o "todosItensPedidoTrocaSessao" (fazendo o CAST para o tipo List<PedidoTroca>)
				todosItensPedidoTrocaSessao = (List<PedidoTroca>) sessao.getAttribute("itensPedidoTroca");
				
				// buscar as informações do Pedido que esta vinculado no Item do Pedido de Troca da Sessão,
				// para quando criar um novo Pedido, salvar com as mesmas informações do Pedido Original
				pedidoOriginal = pedidoDAO.consultarPedidoById(todosItensPedidoTrocaSessao.get(0).getItemPedido().getIdPedido());
				
				// faz um laço para calcular o total dos itens do pedido de troca
				for (int i = 0; i< todosItensPedidoTrocaSessao.size(); i++) {
					// faz o calculo dos itens que serão solicitados para a troca
					// calculo do total dos itens (quantidade do item (*) o valor do item "preço de venda")
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
				// com base nos Itens do Pedido de Troca da Sessão,
				pedidoDAO.salvar(novoPedido);
				
				// consulta o ultimo Pedido cadastrado para poder pegar o ID do Pedido,
				// para poder salvar na tabela "item_pedido"
				List<Pedido> ultimoPedido = pedidoDAO.consultarUltimoPedidoCadastrado(pedido);
				
				// faz um laço para percorrer todos os itens que esta na Sessão "itensPedidoTroca",
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
					// calculo da nova quantidade (quantidade do item que esta salvo no banco (-) o valor da quantidade selecionada na Sessão)
					novaQuantidadeDoItemPedido = (Integer.parseInt(alteraQtdeAndStatusItemPedido.get(0).getQtdeProduto()) - Integer.parseInt(todosItensPedidoTrocaSessao.get(i).getItemPedido().getQtdeProduto()));
					
					// faz a alteração da nova quantidade do item do pedido no banco
					itemPedidoDAO.alterarQtdeItemPedido(Integer.toString(novaQuantidadeDoItemPedido), todosItensPedidoTrocaSessao.get(i).getItemPedido().getId());
				
				}
				
				// limpa os itens dos pedidos de troca selecionados da sessão,
				// atualiza o objeto "itensPedidoTroca" que esta salvo em sessão, com o novo objeto de Item Pedido de Troca vazio
				sessao.setAttribute("itensPedidoTroca", ItensPedidoTrocaVazio);
				
				// atribui a nova mensagem para poder mostra na pagina .JSP
				resultado.setMensagem("Troca solicitada com sucesso! <br>");
				
				// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
			}
			else {
				// mostra as mensagens de ERRO se houver
				// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
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
