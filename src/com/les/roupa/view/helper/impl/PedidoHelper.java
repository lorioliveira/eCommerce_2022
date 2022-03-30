package com.les.roupa.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.les.roupa.view.helper.IViewHelper;
import com.les.roupa.core.dao.impl.CarrinhoDAO;
import com.les.roupa.core.dao.impl.CupomCarrinhoDAO;
import com.les.roupa.core.dao.impl.ItemPedidoDAO;
import com.les.roupa.core.dao.impl.PedidoDAO;
import com.les.roupa.core.dao.impl.ProdutoDAO;
import com.les.roupa.core.dominio.Carrinho;
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.Cupom;
import com.les.roupa.core.dominio.CupomCarrinho;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.ItemPedido;
import com.les.roupa.core.dominio.Pedido;
import com.les.roupa.core.dominio.Produto;
import com.les.roupa.core.dominio.Resultado;
import com.les.roupa.core.dominio.Usuario;
import com.les.roupa.core.strategy.impl.ValidarPrecoVenda;

public class PedidoHelper implements IViewHelper {

	Pedido pedido = null;
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
		String id = null;
		String precoTotalProduto = null;
		String precoFrete = null;
		String precoTotal = null;
		String statusPedido = null;
		String id_cliente = null;
		String id_endereco = null;
		String cartao1 = null;
		String valorCartao1 = null;
		String cartao2 = null;
		String valorCartao2 = null;
		String desconto = null;
		String alteraPedido = null;
		
		// salva a data atual no item do Pedido
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String dataAtual;
		dataAtual = dateFormat.format(date);
				
		
		if (("CONSULTAR").equals(operacao)) {
			pedido = new Pedido();
			
		}
		
		else if (("SALVAR").equals(operacao)) {
			pedido = new Pedido();
			
			precoTotalProduto = request.getParameter("precoTotalProduto");
			precoFrete = request.getParameter("precoFrete");
			precoTotal = request.getParameter("total_pedido");
			statusPedido = request.getParameter("statusPedido");
			id_cliente = request.getParameter("idCliente");
			id_endereco = request.getParameter("idEndereco");
			cartao1 = request.getParameter("cartao1");
			valorCartao1 = request.getParameter("valorCartao1");
			cartao2 = request.getParameter("cartao2");
			valorCartao2 = request.getParameter("valorCartao2");
			desconto = request.getParameter("desconto");
			
			
			
			pedido.setPrecoTotalProduto(precoTotalProduto);
			pedido.setPrecoFrete(precoFrete);
			pedido.setPrecoTotal(precoTotal);
			pedido.setStatusPedido(statusPedido);
			pedido.setIdCliente(id_cliente);
			pedido.setIdEndereco(id_endereco);
			pedido.setCartao1(cartao1);
			pedido.setValorCartao1(valorCartao1);
			pedido.setCartao2(cartao2);
			pedido.setValorCartao2(valorCartao2);
			pedido.setDesconto(desconto);
			
			pedido.setDt_cadastro(dataAtual);
			pedido.setAlteraPedido(alteraPedido);
			
		}
		
		else if (("ALTERAR").equals(operacao)) {
			pedido = new Pedido();
			
			id = request.getParameter("id");
			statusPedido = request.getParameter("statusPedido");
			alteraPedido = request.getParameter("alteraPedido");
			
			pedido.setId(id);
			pedido.setStatusPedido(statusPedido);	
			pedido.setAlteraPedido(alteraPedido);
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {
		}
		
	return pedido;
}

	// SET VIEW 
	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
		// Usa para escrever na tela
		PrintWriter writer = response.getWriter();
		
		if (("CONSULTAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				String id = request.getParameter("id");
				request.setAttribute("id_pedido", id);
				// Redireciona para o arquivo .jsp

				Usuario usuarioLogado = new Usuario();
			    
			    HttpSession sessao = request.getSession();
			    usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");    
				
				if(usuarioLogado.getTipo().equals("cliente")) {
					
					request.getRequestDispatcher("JSP/detalhe-pedido-CLIENTE.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("JSP/detalhe-pedido-ADMIN.jsp").forward(request, response);
				}
			} 
			else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
				// Guarda a mensagem que veio da Strategy na variável para que 
				// seja exibida na tela 'tela-mensagem.jsp'
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				System.out.println("ERRO PARA CONSULTAR PEDIDO!");
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				
				
				/*
				 * writer.println(resultado.getMensagem());
				 * System.out.println("ERRO PARA CONSULTAR!"); writer.
				 * println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">"
				 * );
				 */
			}
		}
		
		else if (("SALVAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				
				PedidoDAO pedidoDAO = new PedidoDAO();
				ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
				CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
				ProdutoDAO produtoDAO = new ProdutoDAO();
				Pedido pedido = new Pedido();
				ItemPedido itemPedido = new ItemPedido();
				Usuario usuarioLogado = new Usuario();
				CupomCarrinhoDAO cupomcarrinhoDAO = new CupomCarrinhoDAO();
				
				
				// salva a data atual no item do Pedido
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date date = new Date();
				String dataAtual;
				dataAtual = dateFormat.format(date);
				
				// usuario salvo na Sessão
				HttpSession sessao = request.getSession();
                usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
                
                // consulta o ultimo Pedido cadastrado
				List<Pedido> ultimoPedido = pedidoDAO.consultarUltimoPedidoCadastrado(pedido);
                
                // consulta todos os produtos que estavam no carrinho,
                // referente aquele cliente com o status "ativo",
                // para criar os itens do pedido cadastrado
                List<Carrinho> carrinhos = carrinhoDAO.consultarCarrinhoByStatus(usuarioLogado.getId());
                
                // percorre a lista de produtos disponiveis dentro do carrinho do cliente,
                // para cada item do carrinho, será criado o item do pedido
                for (Carrinho car : carrinhos) {
                	// consultar o produto by ID
                	List<Produto> produtoCarrinho = produtoDAO.consultarProdutoById(car.getIdProduto());
                	
                	// passa o primeiro indice da lista retornada acima
                	itemPedido.setProduto(produtoCarrinho.get(0));
                	// passa a quantidade do produto que estava no carrinho
                	itemPedido.setQtdeProduto(car.getQtdeProduto());
                	// passo o ID do ultimo Pedido salvo no banco de dados
                	itemPedido.setIdPedido(ultimoPedido.get(0).getId());
                	// passa a data atual do sistema
                	itemPedido.setDt_cadastro(dataAtual);
                	
                	// salva o novo item do pedido
                	itemPedidoDAO.salvar(itemPedido);
                	
                	
                	// Alteração de status do carrinho - deixa com status = 'inativo' 
                	carrinhoDAO.alterarStatus(car.getId());
                	 
                }
                // Tras os cupons ativos pelo ID do cliente logado
                List<CupomCarrinho> cupomCarrinho = cupomcarrinhoDAO.consultarCupomAtivoByCliente(usuarioLogado.getId());
                
                // Alteração de status dos cupons no carrinho = 'inativo'
                for(CupomCarrinho c : cupomCarrinho) {
                	cupomcarrinhoDAO.alterarStatusCupom(c.getId());
                }
                
               
                // redireciona para a tela principal do sistema
				request.getRequestDispatcher("JSP/index_entrar.jsp").forward(request, response);
			}
			else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
				// Guarda a mensagem que veio da Strategy na variável para que 
				// seja exibida na tela 'tela-mensagem.jsp'
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				System.out.println("ERRO PARA SALVAR PEDIDO!");
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
								
				/*
				 * writer.println(resultado.getMensagem());
				 * System.out.println("ERRO PARA SALVAR!"); writer.
				 * println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">"
				 * );
				 */
			}
		}
		
		else if (("ALTERAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				String alteraPedido = request.getParameter("alteraPedido");
				String id = request.getParameter("id");
				
				// É para alterar o sttaus do Pedido agora?
				// No caso 'não', então ele encaminha para tela de alteração com os dados
				// puxados do ID do banco
				if (alteraPedido.contentEquals("0")) {
					request.setAttribute("id_pedido", id);

					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/alterar-pedido.jsp").forward(request, response);
				} else {
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/consultar-pedidos2.jsp").forward(request, response);
				}
			
				// Redireciona para o arquivo .jsp
				//request.getRequestDispatcher("JSP/consultar-pedidos.jsp").forward(request, response);

			} 
			else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
				// Guarda a mensagem que veio da Strategy na variável para que 
				// seja exibida na tela 'tela-mensagem.jsp'
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				System.out.println("ERRO PARA ALTERAR PEDIDO!");
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
								
				/*
				 * writer.println(resultado.getMensagem());
				 * System.out.println("ERRO PARA ALTERAR!"); writer.
				 * println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">"
				 * );
				 */
			}
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/pedidos.jsp").forward(request, response);
			} 
			else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
				// Guarda a mensagem que veio da Strategy na variável para que 
				// seja exibida na tela 'tela-mensagem.jsp'
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				System.out.println("ERRO PARA EXCLUIR PEDIDO!");
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				
				/*
				 * writer.println(resultado.getMensagem());
				 * System.out.println("ERRO PARA EXCLUIR!"); writer.
				 * println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">"
				 * );
				 */
			}
		}
	}

}

