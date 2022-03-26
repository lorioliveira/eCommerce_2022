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
import com.les.roupa.core.dominio.CartaoCredito;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Resultado;
import com.les.roupa.core.dominio.Usuario;

public class CartaoCreditoHelper implements IViewHelper {

    CartaoCredito cartaoCredito = null;

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        // Verifica qual operação do botão foi acionada - SALVAR/ALTERAR/CONSULTAR/EXCLUIR
        String operacao = request.getParameter("operacao");

        String numCartao = null;
        String bandeira = null;
        String nome = null;
        String validade = null;
        String cvv = null;
        String id = null;
        String preferencial = null;
        String idCliente = null;
        
        String alteraPreferencial = null;
        
        // salva a data atual na tabela de Cartao
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
 		Date date = new Date();
 		String dataAtual;
 		dataAtual = dateFormat.format(date);

        if (("CONSULTAR").equals(operacao)) {
            cartaoCredito = new CartaoCredito();

            id = request.getParameter("id");

            cartaoCredito.setId(id);
            
            
        } else if (("SALVAR").equals(operacao)) {
            cartaoCredito = new CartaoCredito();

            // capturando os valores do HTML e passando para o Cartao de Credito
            numCartao = request.getParameter("numCartao");
			bandeira = request.getParameter("bandeira");
			nome = request.getParameter("nome");
			validade = request.getParameter("validade");
			cvv = request.getParameter("cvv");
			id = request.getParameter("id");	
			preferencial = request.getParameter("preferencial");
			idCliente = request.getParameter("idCliente");
			alteraPreferencial = request.getParameter("alteraPreferencial");

            // Atribuindo os valores capturados do HTML para o Cartao de Credito
            cartaoCredito.setNumCartao(numCartao);
            cartaoCredito.setBandeira(bandeira);
            cartaoCredito.setNome(nome);
            cartaoCredito.setValidade(validade);
            cartaoCredito.setCvv(cvv);
            cartaoCredito.setId(id);
            cartaoCredito.setPreferencial(preferencial);
            cartaoCredito.setIdCliente(idCliente);
            cartaoCredito.setAlteraPref(alteraPreferencial);
            
            cartaoCredito.setData_Cadastro(dataAtual);
        }
        
        else if (("ALTERAR").equals(operacao)) {
        	cartaoCredito = new CartaoCredito();
			
			// Atributos da classe 
			numCartao = request.getParameter("numCartao");
			bandeira = request.getParameter("bandeira");
			nome = request.getParameter("nome");
			validade = request.getParameter("validade");
			cvv = request.getParameter("cvv");
			id = request.getParameter("id");	
			preferencial = request.getParameter("preferencial");
            alteraPreferencial = request.getParameter("alteraPreferencial");
            
            idCliente = request.getParameter("idCliente");
            
            // Atribuindo os valores capturados do HTML para o Cartao de Credito
            cartaoCredito.setNumCartao(numCartao);
            cartaoCredito.setBandeira(bandeira);
            cartaoCredito.setNome(nome);
            cartaoCredito.setValidade(validade);
            cartaoCredito.setCvv(cvv);
            cartaoCredito.setId(id);
            cartaoCredito.setPreferencial(preferencial);
            cartaoCredito.setAlteraPref(alteraPreferencial);
            cartaoCredito.setIdCliente(idCliente);
            cartaoCredito.setData_Cadastro(dataAtual);
            

		}else if (("EXCLUIR").equals(operacao)) {
            cartaoCredito = new CartaoCredito();

            id = request.getParameter("id");
            idCliente = request.getParameter("idCliente");

            cartaoCredito.setId(id);
            cartaoCredito.setIdCliente(idCliente);
        }

        return cartaoCredito;

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
            	
                // Redireciona para o arquivo .jsp
                request.getRequestDispatcher("JSP/minhaConta2.jsp").forward(request, response);
            } else {
                // se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
            	request.setAttribute("mensagemStrategy", resultado.getMensagem());
            	System.out.println("ERRO PARA CONSULTAR CARTAO DE CREDITO!");
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				
            }
            
            
        } else if (("SALVAR").equals(operacao)) {
            if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
            	
            	// foi utilizado o getEntidades do resultado para poder pegar o cartao consultado
				List<EntidadeDominio> entidades = resultado.getEntidades();
				// feito o CAST de Entidade para o Usuario (pegando o primeiro indice de Entidade)
				CartaoCredito cartaoCredito = (CartaoCredito) entidades.get(0);
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				
				sessao.setAttribute("cartoesCliente", cartaoCredito.getTodosCartoes());
			            	
                request.getRequestDispatcher("JSP/minhaConta2.jsp").forward(request, response);
                
            } else {
                // se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
            	request.setAttribute("mensagemStrategy", resultado.getMensagem());
            	System.out.println("ERRO PARA SALVAR CARTAO DE CREDITO!");
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
            }
        }
            else if (("ALTERAR").equals(operacao)) {
    			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
    				
    				// foi utilizado o getEntidades do resultado para poder pegar o cartao consultado
    				List<EntidadeDominio> entidades = resultado.getEntidades();
    				// feito o CAST de Entidade para o Usuario (pegando o primeiro indice de Entidade)
    				CartaoCredito cartaoCredito = (CartaoCredito) entidades.get(0);
    				
    				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
    				HttpSession sessao = request.getSession();
    				
    				sessao.setAttribute("cartoesCliente", cartaoCredito.getTodosCartoes());
    				
    				// Este apenas puxa apenas o cartao que sera alterado
    				sessao.setAttribute("cartaoAlterado", cartaoCredito.getTodosCartoes().get(0));
    				
    				String alteraPreferencial = request.getParameter("alteraPreferencial");
    				String id = request.getParameter("id");
    				
    				// Se eu estiver pela tela de listagem de cartoes, chama o arquivo .JSP para edição do cartao
    				if (alteraPreferencial.contentEquals("0")) {					
    					// pendura o "id" do cartao na requisição para poder mandar para o arquivo .JSP
    					request.setAttribute("id", id);
    				    					
    					request.getRequestDispatcher("JSP/alterarCartao.jsp").forward(request, response);
    					
    				}else {
    					// Redireciona para o arquivo .jsp
    					request.getRequestDispatcher("JSP/minhaConta2.jsp").forward(request, response);
    				}
    			} 
    			else {
    				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
    				request.setAttribute("mensagemStrategy", resultado.getMensagem());
    				System.out.println("ERRO PARA ALTERAR CARTAO DE CREDITO!");
    				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
    			}
            
        } else if (("EXCLUIR").equals(operacao)) {
            if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
            	
            	// foi utilizado o getEntidades do resultado para poder pegar o cartao consultado
				List<EntidadeDominio> entidades = resultado.getEntidades();
				// feito o CAST de Entidade para o Usuario (pegando o primeiro indice de Entidade)
				CartaoCredito cartaoCredito = (CartaoCredito) entidades.get(0);
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				
				sessao.setAttribute("cartoesCliente", cartaoCredito.getTodosCartoes());
            	
				String id = request.getParameter("id");
				// pendura o "id" do cartao na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("id", id);
            	      
                // Redireciona para o arquivo .jsp
                request.getRequestDispatcher("JSP/minhaConta.jsp").forward(request, response);
                
            } else {
            	
                // se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
            	request.setAttribute("mensagemStrategy", resultado.getMensagem());
            	System.out.println("ERRO PARA EXCLUIR CARTAO DE CREDITO!");
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
            }
        }
    }

}
