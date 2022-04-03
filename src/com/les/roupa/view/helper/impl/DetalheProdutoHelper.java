package com.les.roupa.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.les.roupa.view.helper.IViewHelper;
import com.les.roupa.core.dominio.CartaoCredito;
import com.les.roupa.core.dominio.DetalheProduto;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Produto;
import com.les.roupa.core.dominio.Resultado;

public class DetalheProdutoHelper implements IViewHelper {

	DetalheProduto detalheProduto = null;
	
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
        
		
		if (("CONSULTAR").equals(operacao)) {
			
			
		}
		
		else if (("SALVAR").equals(operacao)) {
			detalheProduto = new DetalheProduto();
			Produto produto = new Produto();
			
			String id = request.getParameter("id");
			produto.setId(id);
			detalheProduto.setProduto(produto);
			
		}
		
		else if (("ALTERAR").equals(operacao)) {
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {
	}
		return detalheProduto;
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
				
			} 
			else {
				// se houver, mostra as mensagens de ERRO 
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				System.out.println("ERRO PARA CONSULTAR DETALHE DO PRODUTO!");
	
			}
		}
		
		else if (("SALVAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				
				// foi utilizado o getEntidades do resultado para poder pegar o Login consultado
				//List<EntidadeDominio> entidades = resultado.getEntidades();
				// feito o CAST de Entidade para o Usuario (pegando o primeiro indice de Entidade)
				//Produto produto = (Produto) entidades.get(0);
				
				DetalheProduto detalheProduto = (DetalheProduto) resultado.getEntidades().get(0);
				
				request.setAttribute("produtoSelecionado", detalheProduto.getProduto());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/detalhe_produto.jsp").forward(request, response);
			}
			else {
				// se houver, mostra as mensagens de ERRO 
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				System.out.println("ERRO PARA SALVAR DETALHE DO PRODUTO!");
	
			}
		}
		
		else if (("ALTERAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// foi utilizado o getEntidades do resultado para poder pegar o Login consultado
				List<EntidadeDominio> entidades = resultado.getEntidades();
				// feito o CAST de Entidade para o Usuario (pegando o primeiro indice de Entidade)
				Produto produto = (Produto) entidades.get(0);
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				
				sessao.setAttribute("todosProdutos", produto.getTodosProdutos());
				// Este apenas puxa apenas o produto que sera alterado
				sessao.setAttribute("produtoAlterado", produto.getTodosProdutos().get(0));
			
				
				String alteraProduto = request.getParameter("alteraProduto");
				String id = request.getParameter("id");
				// É para alterar os campos do cliente agora? 
				// No caso 'não', então ele encaminha para tela de alteração com os dados puxados do id do banco
				if(alteraProduto.contentEquals("0")) {
					request.setAttribute("idProduto", id);
					
					request.getRequestDispatcher("JSP/alterarProduto.jsp").forward(request, response);
				}else {
					sessao.setAttribute("todosProdutos", produto.getTodosProdutos());
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/indexAdm2.jsp").forward(request, response);
				}
			} 
			else {
				// se houver, mostra as mensagens de ERRO 
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				System.out.println("ERRO PARA ALTERAR DETALHE DO PRODUTO!");
				
			}
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// foi utilizado o getEntidades do resultado para poder pegar o Login consultado
				List<EntidadeDominio> entidades = resultado.getEntidades();
				// feito o CAST de Entidade para o Usuario (pegando o primeiro indice de Entidade)
				Produto produto = (Produto) entidades.get(0);
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				
				//atualiza a sessao com todos os produtos existentes
				sessao.setAttribute("todosProdutos", produto.getTodosProdutos());
				
				String id = request.getParameter("id");
				// pendura o "id" do produto na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("id", id);
				
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/indexAdm2.jsp").forward(request, response);
			} 
			else {
				// se houver, mostra as mensagens de ERRO
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				System.out.println("ERRO PARA EXCLUIR DETALHE DO PRODUTO!");
				
			}
		}
	}

}

