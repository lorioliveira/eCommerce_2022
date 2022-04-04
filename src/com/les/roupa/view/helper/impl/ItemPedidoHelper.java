package com.les.roupa.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.les.roupa.view.helper.IViewHelper;
import com.les.roupa.core.dominio.Carrinho;
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.ItemPedido;
import com.les.roupa.core.dominio.Pedido;
import com.les.roupa.core.dominio.Produto;
import com.les.roupa.core.dominio.Resultado;

public class ItemPedidoHelper implements IViewHelper {

	ItemPedido itemPedido = null;
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
		String id = null;
		String id_produto = null;
		String nome = null;
		String qtde_produto = null;
		String precoVenda = null;
		String id_pedido = null;
				
		
		if (("CONSULTAR").equals(operacao)) {
			
		}
		
		else if (("SALVAR").equals(operacao)) {
			itemPedido = new ItemPedido();
			
			id_produto = request.getParameter("id_produto");
			nome = request.getParameter("nome");
			qtde_produto = request.getParameter("qtde_produto");
			precoVenda = request.getParameter("precoVenda");
			id_pedido = request.getParameter("id_pedido");
			
			
			itemPedido.setIdProduto(id_produto);
			itemPedido.setNome(nome);
			itemPedido.setQtdeProduto(qtde_produto);
			itemPedido.setPrecoVenda(precoVenda);
			itemPedido.setIdPedido(id_pedido);		
			
			
		}
		
		else if (("ALTERAR").equals(operacao)) {
			/*
				id = request.getParameter("id");
				qtde_produto = request.getParameter("qtde_produto");
				valor_operacao = request.getParameter("valorOperacao");			
				
				carrinho.setId(id);
				carrinho.setQtdeProduto(qtde_produto);
				carrinho.setValorOperacao(valor_operacao);
			*/
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			
			//id = request.getParameter("id");
			
			//carrinho.setId(id);
			
		}
		
	return itemPedido;
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
				//String id = request.getParameter("idPedido");
				//request.setAttribute("idPedido", id);
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/index.jsp").forward(request, response);
			} 
			else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
				writer.println(resultado.getMensagem());
				System.out.println("ERRO PARA CONSULTAR!");
				writer.println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">");
			}
		}
		
		else if (("SALVAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				request.getRequestDispatcher("JSP/index_entrar.jsp").forward(request, response);
			}
			else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
				writer.println(resultado.getMensagem());
				System.out.println("ERRO PARA SALVAR!");
				writer.println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">");
			}
		}
		
		else if (("ALTERAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/pedidos.jsp").forward(request, response);

			} 
			else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
				writer.println(resultado.getMensagem());
				System.out.println("ERRO PARA ALTERAR!");
				writer.println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">");
			}
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/pedidos.jsp").forward(request, response);
			} 
			else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
				writer.println(resultado.getMensagem());
				System.out.println("ERRO PARA EXCLUIR!");
				writer.println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">");
			}
		}
	}

}

