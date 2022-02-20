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
import com.les.roupa.core.dominio.CartaoCredito;
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Resultado;
import com.les.roupa.core.dominio.Usuario;

public class CartaoCreditoHelper implements IViewHelper {

    CartaoCredito cartaoCredito = null;

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        // Verifica qual operação do botão foi acionada
        String operacao = request.getParameter("operacao");

        String nCartao = null;
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

            id = request.getParameter("idCliente");

            cartaoCredito.setId(id);
            
            
        } else if (("SALVAR").equals(operacao)) {
            cartaoCredito = new CartaoCredito();

            // capturando os valores do HTML e passando para o Cartao de Credito

            nCartao = request.getParameter("nCartao");
			bandeira = request.getParameter("bandeira");
			nome = request.getParameter("nome");
			validade = request.getParameter("validade");
			cvv = request.getParameter("cvv");
			id = request.getParameter("id");	
			preferencial = request.getParameter("preferencial");
			idCliente = request.getParameter("idCliente");

            // Atribuindo os valores capturados do HTML para o Cartao de Credito
            cartaoCredito.setNCartao(nCartao);
            cartaoCredito.setBandeira(bandeira);
            cartaoCredito.setNome(nome);
            cartaoCredito.setValidade(validade);
            cartaoCredito.setCvv(cvv);
            cartaoCredito.setId(id);
            cartaoCredito.setPreferencial(preferencial);
            cartaoCredito.setIdCliente(idCliente);
            
           // cartaoCredito.setAlteraPref(alteraPreferencial);
            cartaoCredito.setDt_cadastro(dataAtual);
                        
        }
        
        else if (("ALTERAR").equals(operacao)) {
        	cartaoCredito = new CartaoCredito();
			
			// Atributos da classe 
			nCartao = request.getParameter("nCartao");
			bandeira = request.getParameter("bandeira");
			nome = request.getParameter("nome");
			validade = request.getParameter("validade");
			cvv = request.getParameter("cvv");
			id = request.getParameter("id");	
			preferencial = request.getParameter("preferencial");
            alteraPreferencial = request.getParameter("alteraPreferencial");
            idCliente = request.getParameter("idCliente");
									
            // Atribuindo os valores capturados do HTML para o Cartao de Credito
            cartaoCredito.setNCartao(nCartao);
            cartaoCredito.setBandeira(bandeira);
            cartaoCredito.setNome(nome);
            cartaoCredito.setValidade(validade);
            cartaoCredito.setCvv(cvv);
            cartaoCredito.setId(id);
            cartaoCredito.setPreferencial(preferencial);
            cartaoCredito.setAlteraPref(alteraPreferencial);
            cartaoCredito.setIdCliente(idCliente);
            cartaoCredito.setDt_cadastro(dataAtual);
            

		}else if (("EXCLUIR").equals(operacao)) {
            cartaoCredito = new CartaoCredito();

            id = request.getParameter("id");

            cartaoCredito.setId(id);
        }

        return cartaoCredito;

    }

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
                request.getRequestDispatcher("JSP/cartao-credito.jsp").forward(request, response);
            } else {
                // se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
            	// Guarda a mensagem que veio da Strategy na variável para que 
				// seja exibida na tela 'tela-mensagem.jsp'
            	request.setAttribute("mensagemStrategy", resultado.getMensagem());
            	System.out.println("ERRO PARA CONSULTAR CARTAO DE CREDITO!");
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				
				/*
				 * writer.println(resultado.getMensagem());
				 * System.out.println("ERRO PARA CONSULTAR!"); writer.
				 * println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">"
				 * );
				 */
            }
            
            
        } else if (("SALVAR").equals(operacao)) {
            if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
                request.getRequestDispatcher("JSP/cartao-credito.jsp").forward(request, response);
            } else {
                // se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
            	// Guarda a mensagem que veio da Strategy na variável para que 
				// seja exibida na tela 'tela-mensagem.jsp'
            	request.setAttribute("mensagemStrategy", resultado.getMensagem());
            	System.out.println("ERRO PARA SALVAR CARTAO DE CREDITO!");
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
    				String alteraPreferencial = request.getParameter("alteraPreferencial");
    				String id = request.getParameter("id");
    				// É para alterar os campos do cartao agora? 
    				// No caso 'não', então ele encaminha para tela de alteração com os dados puxados do id do banco
    				if(alteraPreferencial.contentEquals("0")) {
    					request.setAttribute("id", id);
    					
    					request.getRequestDispatcher("JSP/alterar-cartao.jsp").forward(request, response);
    				}else {
    					// Redireciona para o arquivo .jsp
    					request.getRequestDispatcher("JSP/cartao-credito.jsp").forward(request, response);
    				}
    			} 
    			else {
    				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
    				// Guarda a mensagem que veio da Strategy na variável para que 
    				// seja exibida na tela 'tela-mensagem.jsp'
    				request.setAttribute("mensagemStrategy", resultado.getMensagem());
    				System.out.println("ERRO PARA ALTERAR CARTAO DE CREDITO!");
    				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
					/*
					 * writer.println(resultado.getMensagem());
					 * System.out.println("ERRO PARA ALTERAR!"); writer.
					 * println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">"
					 * );
					 */
    			}
            
        } else if (("EXCLUIR").equals(operacao)) {
            if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
                // Redireciona para o arquivo .jsp
                request.getRequestDispatcher("JSP/cartao-credito.jsp").forward(request, response);
            } else {
                // se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
            	// Guarda a mensagem que veio da Strategy na variável para que 
				// seja exibida na tela 'tela-mensagem.jsp'
            	request.setAttribute("mensagemStrategy", resultado.getMensagem());
            	System.out.println("ERRO PARA EXCLUIR CARTAO DE CREDITO!");
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
