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
import com.les.roupa.core.dominio.Produto;
import com.les.roupa.core.dominio.Resultado;

public class CarrinhoHelper implements IViewHelper {

	Carrinho carrinho = null;
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
		String id = null;
		String id_cliente = null;
		String id_produto = null;
		String qtde_produto = null;
		String status = null;
		String valor_operacao = null;
		
		if (("CONSULTAR").equals(operacao)) {
			carrinho = new Carrinho();
			
		}
		
		else if (("SALVAR").equals(operacao)) {
			carrinho = new Carrinho();
			
			id_cliente = request.getParameter("id_cliente");
			id_produto = request.getParameter("id_produto");
			qtde_produto = request.getParameter("qtde_produto");
			status = request.getParameter("status");
			
			carrinho.setIdCliente(id_cliente);
			carrinho.setIdProduto(id_produto);
			carrinho.setQtdeProduto(qtde_produto);
			carrinho.setStatus(status);
		}
		
		else if (("ALTERAR").equals(operacao)) {
			carrinho = new Carrinho();
			
			id = request.getParameter("id");
			qtde_produto = request.getParameter("qtde_produto");
			valor_operacao = request.getParameter("valorOperacao");			
			
			carrinho.setId(id);
			carrinho.setQtdeProduto(qtde_produto);
			carrinho.setValorOperacao(valor_operacao);
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			carrinho = new Carrinho();
			
			id = request.getParameter("id");
			
			carrinho.setId(id);
		}
		
	return carrinho;
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
				String id = request.getParameter("idCarrinho");
				request.setAttribute("idCarrinho", id);
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/carrinho.jsp").forward(request, response);
			} 
			else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
				// Guarda a mensagem que veio da Strategy na variável para que 
				// seja exibida na tela 'tela-mensagem.jsp'
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				System.out.println("ERRO PARA CONSULTAR CARRINHO!");
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
				request.getRequestDispatcher("JSP/carrinho2.jsp").forward(request, response);
			}
			else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
				// Guarda a mensagem que veio da Strategy na variável para que 
				// seja exibida na tela 'tela-mensagem.jsp'
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				System.out.println("ERRO PARA SALVAR CARRINHO!");
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
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/alterar_carrinho.jsp").forward(request, response);

			} 
			else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
				// Guarda a mensagem que veio da Strategy na variável para que 
				// seja exibida na tela 'tela-mensagem.jsp'
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				System.out.println("ERRO PARA ALTERAR CARRINHO!");
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				
//				writer.println(resultado.getMensagem());
//				System.out.println("ERRO PARA ALTERAR!");
//				writer.println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">");
			}
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/carrinho2.jsp").forward(request, response);
			} 
			else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
				// Guarda a mensagem que veio da Strategy na variável para que 
				// seja exibida na tela 'tela-mensagem.jsp'
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				System.out.println("ERRO PARA EXCLUIR CARRINHO!");
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

