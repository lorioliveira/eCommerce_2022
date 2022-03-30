package com.les.roupa.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.les.roupa.view.helper.IViewHelper;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Produto;
import com.les.roupa.core.dominio.Resultado;

public class DetalheProdutoHelper implements IViewHelper {

	Produto produto = null;
	
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
        
		
		if (("CONSULTAR").equals(operacao)) {
			produto = new Produto();
			
		}
		
		else if (("SALVAR").equals(operacao)) {
			produto = new Produto();
		}
		
		else if (("ALTERAR").equals(operacao)) {
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {
	}
		return produto;
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
				request.setAttribute("idProduto", id);
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/detalhe_produto.jsp").forward(request, response);
			} 
			else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
				// Guarda a mensagem que veio da Strategy na variável para que 
				// seja exibida na tela 'tela-mensagem.jsp'
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				System.out.println("ERRO PARA CONSULTAR DETALHE DO PRODUTO!");
				
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
				request.getRequestDispatcher("JSP/consultar-produto.jsp").forward(request, response);
			}
			else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
				// Guarda a mensagem que veio da Strategy na variável para que 
				// seja exibida na tela 'tela-mensagem.jsp'
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				System.out.println("ERRO PARA SALVAR DETALHE DO PRODUTO!");
				
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
				String alteraProduto = request.getParameter("alteraProduto");
				String id = request.getParameter("id");
				// É para alterar os campos do cliente agora? 
				// No caso 'não', então ele encaminha para tela de alteração com os dados puxados do id do banco
				if(alteraProduto.contentEquals("0")) {
					request.setAttribute("idProduto", id);
					
					request.getRequestDispatcher("JSP/alterar-produto.jsp").forward(request, response);
				}else {
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/perfil-admin.jsp").forward(request, response);
				}
			} 
			else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
				// Guarda a mensagem que veio da Strategy na variável para que 
				// seja exibida na tela 'tela-mensagem.jsp'
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				System.out.println("ERRO PARA ALTERAR DETALHE DO PRODUTO!");
				
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
				request.getRequestDispatcher("JSP/consultar-cliente.jsp").forward(request, response);
			} 
			else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
				// Guarda a mensagem que veio da Strategy na variável para que 
				// seja exibida na tela 'tela-mensagem.jsp'
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				System.out.println("ERRO PARA EXCLUIR DETALHE DO PRODUTO!");
				
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

