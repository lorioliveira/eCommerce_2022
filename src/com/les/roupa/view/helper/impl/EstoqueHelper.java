package com.les.roupa.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Estoque;
import com.les.roupa.core.dominio.Resultado;
import com.les.roupa.view.helper.IViewHelper;

public class EstoqueHelper implements IViewHelper {

	Estoque estoque = null;
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		// Verifica qual opera��o do bot�o foi acionada
		String operacao = request.getParameter("operacao");
		
		String id_produto = null;
        String tipo = null;
        String quantidade = null;
        String valorCompra = null;
        String dt_entrada = null;
		
		if (("CONSULTAR").equals(operacao)) {
			estoque = new Estoque();
			
			// capturando os valores do HTML e passando para o Estoque
			id_produto = request.getParameter("selecioneProduto");
			
			// Atribuindo os valores capturados do HTML para o Estoque
			estoque.setIdProduto(id_produto);
		}
		
		else if (("SALVAR").equals(operacao)) {
			estoque = new Estoque();
			
			// capturando os valores do HTML e passando para o Estoque
			id_produto = request.getParameter("selecioneProduto");
			tipo = request.getParameter("tipo");
			quantidade = request.getParameter("quantidade");
			valorCompra = request.getParameter("valorCompra");
			dt_entrada = request.getParameter("dt_entrada");
			
			// Atribuindo os valores capturados do HTML para o Estoque
			estoque.setIdProduto(id_produto);
			estoque.setTipo(tipo);
			estoque.setQuantidade(quantidade);
			estoque.setValorCompra(valorCompra);
			estoque.setDtEntrada(dt_entrada);
		}
		
		else if (("ALTERAR").equals(operacao)) {
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			
		}
		
		return estoque;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Verifica qual operacaoo do botao foi acionada
		String operacao = request.getParameter("operacao");
		
		// Usa para escrever na tela
		PrintWriter writer = response.getWriter();
		
		if (("CONSULTAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				String id_produto = request.getParameter("selecioneProduto");
				
				// pendura o "idProduto" na requisi��o para poder mandar para o arquivo .JSP
				request.setAttribute("idProduto", id_produto);
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/consultar-estoque.jsp").forward(request, response);
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
				// atribui a nova mensagem para poder mostra na pagina .JSP
				resultado.setMensagem("Cadastro do Estoque foi salvo com sucesso!<br>");
				
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
				request.getRequestDispatcher("JSP/tela-mensagem..jsp").forward(request, response);
			}
		}
		
		else if (("ALTERAR").equals(operacao)) {
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			
		}
		
	}

}