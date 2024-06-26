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

import com.les.roupa.core.dominio.Cupom;
import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Estoque;
import com.les.roupa.core.dominio.ItemPedido;
import com.les.roupa.core.dominio.Pedido;
import com.les.roupa.core.dominio.Produto;
import com.les.roupa.core.dominio.Resultado;
import com.les.roupa.core.dominio.Usuario;
import com.les.roupa.view.helper.IViewHelper;

/**
 * ViewHelper - Pedido
 * @author Lorena Oliveira
 *
 */
public class PedidoHelper implements IViewHelper {

	Pedido pedido = null;
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		// Verifica qual opera��o do bot�o foi acionada
		String operacao = request.getParameter("operacao");
		
		String id = null;
		String total_itens = null;
        String total_frete = null;
        String total_pedido = null;
        String id_cliente = null;
		String id_endereco = null;
		String forma_pagamento = null;
        String id_cartao_1 = null;
        String valor_cartao_1 = null;
        String id_cartao_2 = null;
        String valor_cartao_2 = null;
        String total_cupons = null;
        
        List<Produto> produtosDaSessao = new ArrayList<>();
        List<Cupom> cuponsDaSessao = new ArrayList<>();
        
        // salva a data atual na tabela
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
 		Date date = new Date();
 		String dataAtual;
 		dataAtual = dateFormat.format(date);
        
		
		if (("CONSULTAR").equals(operacao)) {
			pedido = new Pedido();
			
			id = request.getParameter("id");
			
			pedido.setId(id);
			
		}
		
		else if (("SALVAR").equals(operacao)) {
			pedido = new Pedido();
			
			// capturando os valores do HTML e passando para o Pedido
			total_itens = request.getParameter("total_itens");
			total_frete = request.getParameter("total_frete");
			total_pedido = request.getParameter("total_pedido");
			id_cliente = request.getParameter("idCliente");
			id_endereco = request.getParameter("selecioneEndereco");
			forma_pagamento = request.getParameter("selecioneFormadePagamento");
			id_cartao_1 = request.getParameter("selecioneCartao1");
			valor_cartao_1 = request.getParameter("valorCartao1");
			id_cartao_2 = request.getParameter("selecioneCartao2");
			valor_cartao_2 = request.getParameter("valorCartao2");
			
			total_cupons = request.getParameter("total_cupons");
			
			// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
			HttpSession sessao = request.getSession();
			// pega o objeto salvo em Sess�o com o nome "itensCarrinho",
			// e passa para o "produtosDaSessao" (fazendo o CAST para o tipo List<Produto>)
			produtosDaSessao = (List<Produto>) sessao.getAttribute("itensCarrinho");
			// pega o objeto salvo em Sess�o com o nome "cupons",
			// e passa para o "cuponsDaSessao" (fazendo o CAST para o tipo List<Cupom>)
			cuponsDaSessao = (List<Cupom>) sessao.getAttribute("cupons");
			
			// Atribuindo os valores capturados do HTML para o Pedido
			pedido.setTotalItens(total_itens);
			pedido.setTotalFrete(total_frete);
			pedido.setTotalPedido(total_pedido);
			pedido.setIdCliente(id_cliente);
			pedido.setIdEndereco(id_endereco);
			pedido.setFormaPagamento(forma_pagamento);
			pedido.setIdCartao1(id_cartao_1);
			pedido.setValorCartao1(valor_cartao_1);
			pedido.setIdCartao2(id_cartao_2);
			pedido.setValorCartao2(valor_cartao_2);
			pedido.setTrocado("nao");
			pedido.setTotalCupons(total_cupons);
			pedido.setProdutos(produtosDaSessao);
			pedido.setCupons(cuponsDaSessao);
			pedido.setData_Cadastro(dataAtual);
			
		}
		
		else if (("ALTERAR").equals(operacao)) {
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			
		}
		
		return pedido;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Verifica qual opera��o do bot�o foi acionada
		String operacao = request.getParameter("operacao");
		
		// Usa para escrever na tela
		PrintWriter writer = response.getWriter();
		
		if (("CONSULTAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// foi utilizado o getEntidades do resultado para poder pegar o Login consultado
				List<EntidadeDominio> entidades = resultado.getEntidades();
				// feito o CAST de Entidade para o Usuario (pegando o primeiro indice de Entidade)
				Pedido pedidoSelecionado = (Pedido) entidades.get(0);
				
				
				// pendura o "idPedido" na requisi��o para poder mandar para o arquivo .JSP
				request.setAttribute("pedidoSelecionado", pedidoSelecionado.getPedidosCliente().get(0));
				request.setAttribute("itensPedidoSelecionado", pedidoSelecionado.getItemPedido());
				
				request.getRequestDispatcher("JSP/detalhePedido2.jsp").forward(request, response);
				
				
				Usuario usuarioLogado = new Usuario();
		        HttpSession sessao = request.getSession();
		        usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		        
		        //Lista os pedidos do cliente
				sessao.setAttribute("pedidosCliente", usuarioLogado.getPedidosCliente());
				
		        //Caso usuario logado for Admin 
				if(usuarioLogado.getTipoCliente().equals("admin")) {
					request.getRequestDispatcher("JSP/detalhePedidoAdmin2.jsp").forward(request, response);
				}
			}
				
			else {
				// mostra as mensagens de ERRO se houver
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/minhaConta2.jsp").forward(request, response);
			}
			
		}
		
		else if (("SALVAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				List<EntidadeDominio> entidades = resultado.getEntidades();
				Pedido pedidoAtualizado = (Pedido) entidades.get(0);
				
				List<Cupom> cuponsVazio = new ArrayList<>();
				List<Produto> produtosVazio = new ArrayList<>();
				
				// cria um objeto "sessao"
				HttpSession sessao = request.getSession();
				
				// atualiza a lista de pedidos do Cliente
				sessao.setAttribute("pedidosCliente", pedidoAtualizado.getPedidosCliente());

				// limpa os produtos selecionados do carrinho da sess�o,
				// atualiza o objeto "itensCarrinho" que esta salvo em sess�o = vazio
				sessao.setAttribute("itensCarrinho", produtosVazio);
				
				// limpa os cupons selecionados do carrinho da sess�o,
				// atualiza o objeto "cupons" que esta salvo em sess�o = vazio
				sessao.setAttribute("cupons", cuponsVazio);
				
				// atribui a nova mensagem 
				resultado.setMensagem("Pedido realizado com sucesso!");
				
				// pendura o "resultado" na requisi��o 
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/index2.jsp").forward(request, response);
				
			}
			else {
				// mostra as mensagens de ERRO se houver
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/carrinho2.jsp").forward(request, response);
			}
		}
		
		else if (("ALTERAR").equals(operacao)) {
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			
		}
		
	}

}