package com.les.roupa.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.les.roupa.view.helper.IViewHelper;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Produto;
import com.les.roupa.core.dominio.Resultado;

public class ProdutoHelper implements IViewHelper {

	Produto produto = null;
	
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
        String nome = null;
        String categoria = null;
        String cores = null;
        String tamanho = null;
        String precoCompra = null;
        String precoVenda = null;
        String foto = null;
        String qtdeEstoque = null;
        String descricao = null;
        String status = null;
        String grupoPrecificacao = null;
        String descricaoStatusProduto = null;
        
        String alteraProduto = null;
        String id = null;
        
     // salva a data atual na tabela de Roupa
     		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
     		Date date = new Date();
     		String dataAtual;
     		dataAtual = dateFormat.format(date);
		
		if (("CONSULTAR").equals(operacao)) {
			produto = new Produto();
		}
		
		else if (("SALVAR").equals(operacao)) {
			produto = new Produto();
			
			// Atributos da classe Produto
			nome = request.getParameter("nome");
			categoria = request.getParameter("categoria");
			cores = request.getParameter("cores");
			tamanho = request.getParameter("tamanho");
			precoCompra = request.getParameter("precoCompra");
			precoVenda = request.getParameter("precoVenda");
			foto = request.getParameter("foto");
			qtdeEstoque = request.getParameter("qtdeEstoque");
			descricao = request.getParameter("descricao");
			status = request.getParameter("status");
			grupoPrecificacao = request.getParameter("grupoPrecificacao");
			id = request.getParameter("id");			
			descricaoStatusProduto = request.getParameter("descricaoStatusProduto");
			
			
			// Atribuindo os valores capturados do HTML para o Produto
			produto.setNome(nome);
			produto.setCategoria(categoria);
			produto.setCores(cores);
			produto.setTamanho(tamanho);
			produto.setPrecoCompra(precoCompra);
			produto.setPrecoVenda(precoVenda);
			produto.setFoto(foto);
			produto.setQtdeEstoque(qtdeEstoque);
			produto.setDescricao(descricao);
			produto.setStatus(status);
			produto.setGrupoPrecificacao(grupoPrecificacao);
			produto.setId(id);
			produto.setData_Cadastro(dataAtual);
			produto.setStatusProduto(descricaoStatusProduto);
		}
		
		else if (("ALTERAR").equals(operacao)) {
			produto = new Produto();
			
			// Atributos da classe Produto
			nome = request.getParameter("nome");
			categoria = request.getParameter("categoria");
			cores = request.getParameter("cores");
			tamanho = request.getParameter("tamanho");
			precoCompra = request.getParameter("precoCompra");
			precoVenda = request.getParameter("precoVenda");
			foto = request.getParameter("foto");
			qtdeEstoque = request.getParameter("qtdeEstoque");
			descricao = request.getParameter("descricao");
			status = request.getParameter("status");
			grupoPrecificacao = request.getParameter("grupoPrecificacao");
			id = request.getParameter("id");			
			alteraProduto = request.getParameter("alteraProduto");	
			descricaoStatusProduto = request.getParameter("descricaoStatusProduto");
			
			
			// Atribuindo os valores capturados do HTML para o Produto
			produto.setNome(nome);
			produto.setCategoria(categoria);
			produto.setCores(cores);
			produto.setTamanho(tamanho);
			produto.setPrecoCompra(precoCompra);
			produto.setPrecoVenda(precoVenda);
			produto.setFoto(foto);
			produto.setQtdeEstoque(qtdeEstoque);
			produto.setDescricao(descricao);
			produto.setStatus(status);
			produto.setGrupoPrecificacao(grupoPrecificacao);
			produto.setId(id);
			produto.setAlteraProduto(alteraProduto);
			produto.setData_Cadastro(dataAtual);
			produto.setStatusProduto(descricaoStatusProduto);

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
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/produtos2.jsp").forward(request, response);
			} 
			else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
            	request.setAttribute("mensagemStrategy", resultado.getMensagem());
            	System.out.println("ERRO PARA CONSULTAR PRODUTO!");
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
			}
		}
		
		else if (("SALVAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				request.getRequestDispatcher("JSP/produtos2.jsp").forward(request, response);
			}
			else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
            	request.setAttribute("mensagemStrategy", resultado.getMensagem());
            	System.out.println("ERRO PARA SALVAR PRODUTO!");
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				
			
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
					
					request.getRequestDispatcher("JSP/alterarProduto.jsp").forward(request, response);
				}else {
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/index2.jsp").forward(request, response);
				}
			} 
			else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
            	request.setAttribute("mensagemStrategy", resultado.getMensagem());
            	System.out.println("ERRO PARA ALTERAR PRODUTO!");
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				
			}
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/index2.jsp").forward(request, response);
			} 
			else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
            	request.setAttribute("mensagemStrategy", resultado.getMensagem());
            	System.out.println("ERRO PARA EXCLUIR PRODUTO!");
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				
			}
		}
	}

}

