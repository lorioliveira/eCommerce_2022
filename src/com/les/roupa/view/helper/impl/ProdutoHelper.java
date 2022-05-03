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
import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Produto;
import com.les.roupa.core.dominio.Resultado;

/**
 * PRODUTO
 */
public class ProdutoHelper implements IViewHelper {

	Produto produto = null;
		
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		// Verifica qual operação do botão foi acionada - SALVAR/ALTERAR/CONSULTAR/EXCLUIR
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
        String motivoStatus = null;
        
        String alteraProduto = null;
        String id = null;
        
     // salva a data atual na tabela de Produto
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
			motivoStatus = request.getParameter("motivoStatus");
			
			
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
			produto.setMotivoStatus(motivoStatus);
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
			motivoStatus = request.getParameter("motivoStatus");
			
			
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
			produto.setMotivoStatus(motivoStatus);

		}
		
		else if (("EXCLUIR").equals(operacao)) {
			
		}
		return produto;
}

	// SET VIEW 
	
	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Verifica qual operação do botão foi acionada - SALVAR/ALTERAR/CONSULTAR/EXCLUIR
		String operacao = request.getParameter("operacao");
		
		// Usa para escrever na tela
		PrintWriter writer = response.getWriter();
		
		if (("CONSULTAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
								
				request.getRequestDispatcher("JSP/detalhe_produto.jsp").forward(request, response);
			} 
			else {
				// mostra as mensagens de ERRO se houver
            	request.setAttribute("mensagemStrategy", resultado.getMensagem());
            	System.out.println("ERRO PARA CONSULTAR PRODUTO!");
				request.getRequestDispatcher("JSP/indexAdm2.jsp").forward(request, response);
			}
		}
		
		else if (("SALVAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// foi utilizado o getEntidades do resultado para poder pegar o Login consultado
				List<EntidadeDominio> entidades = resultado.getEntidades();
				// feito o CAST de Entidade para o Usuario (pegando o primeiro indice de Entidade)
				Produto produto = (Produto) entidades.get(0);
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				
				sessao.setAttribute("todosProdutos", produto.getTodosProdutos());
				
				request.getRequestDispatcher("JSP/produtos2.jsp").forward(request, response);
			}
			else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
            	request.setAttribute("mensagemStrategy", resultado.getMensagem());
            	System.out.println("ERRO PARA SALVAR PRODUTO!");
				request.getRequestDispatcher("JSP/indexAdm2.jsp").forward(request, response);
				
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
					
					sessao.setAttribute("todosProdutos", produto.getTodosProdutos());
				}else {
					sessao.setAttribute("todosProdutos", produto.getTodosProdutos());
					
					// Mensagem de produto alterado para aparecer na modal
					resultado.setMensagem("Produto alterado com sucesso! ");
					
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/indexAdm2.jsp").forward(request, response);
				}
			} 
			else {
				// mostra as mensagens de ERRO se houver
            	request.setAttribute("mensagemStrategy", resultado.getMensagem());
            	System.out.println("ERRO PARA ALTERAR PRODUTO!");
				request.getRequestDispatcher("JSP/indexAdm2.jsp").forward(request, response);
				
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
				// mostra as mensagens de ERRO se houver
            	request.setAttribute("mensagemStrategy", resultado.getMensagem());
            	System.out.println("ERRO PARA EXCLUIR PRODUTO!");
				request.getRequestDispatcher("JSP/indexAdm2.jsp").forward(request, response);
				
			}
		}
	}

}

