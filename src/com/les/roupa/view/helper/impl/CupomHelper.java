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
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.Cupom;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Resultado;

public class CupomHelper implements IViewHelper {

	Cupom cupom = null;

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {

		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");

		String id = null;
		String nome = null;
		String tipo = null;
		String valor = null;
		String id_cliente = null;
		String utilizado = null;
		String alteraCupom = null;

		// salva a data atual na tabela de Cupom
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String dataAtual;
		dataAtual = dateFormat.format(date);

		if (("CONSULTAR").equals(operacao)) {
			cupom = new Cupom();

			id = request.getParameter("id");

			cupom.setId(id);
		}

		else if (("SALVAR").equals(operacao)) {
			cupom = new Cupom();

			nome = request.getParameter("nome");
			tipo = request.getParameter("tipo");
			valor = request.getParameter("valor");
			id_cliente = request.getParameter("id_cliente");
			utilizado = request.getParameter("utilizado");
			//alteraCupom = request.getParameter("alteraCupom");

			cupom.setNome(nome);
			cupom.setTipo(tipo);
			cupom.setValor(valor);
			cupom.setIdCliente(id_cliente);
			cupom.setUtilizado(utilizado);
			cupom.setAlteraCupom(alteraCupom);
			cupom.setDt_cadastro(dataAtual);
		}

		else if (("ALTERAR").equals(operacao)) {
			cupom = new Cupom();

			nome = request.getParameter("nome");
			tipo = request.getParameter("tipo");
			valor = request.getParameter("valor");
			id_cliente = request.getParameter("id_cliente");
			utilizado = request.getParameter("utilizado");
			id = request.getParameter("id");
			alteraCupom = request.getParameter("alteraCupom");

			cupom.setNome(nome);
			cupom.setTipo(tipo);
			cupom.setValor(valor);
			cupom.setIdCliente(id_cliente);
			cupom.setUtilizado(utilizado);
			cupom.setId(id);

			cupom.setAlteraCupom(alteraCupom); 

			cupom.setDt_cadastro(dataAtual);
		}

		else if (("EXCLUIR").equals(operacao)) {
			cupom = new Cupom();

			id = request.getParameter("id");

			cupom.setId(id);

		}

		return cupom;
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
				request.getRequestDispatcher("JSP/consultar-cupons.jsp").forward(request, response);
			} else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
				// Guarda a mensagem que veio da Strategy na variável para que 
				// seja exibida na tela 'tela-mensagem.jsp'
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				System.out.println("ERRO PARA CONSULTAR CUPOM!");
				
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
				request.getRequestDispatcher("JSP/consultar-cupons.jsp").forward(request, response);
			} else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
				// Guarda a mensagem que veio da Strategy na variável para que 
				// seja exibida na tela 'tela-mensagem.jsp'
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				System.out.println("ERRO PARA SALVAR CUPOM!");
				
				//writer.println(resultado.getMensagem());
				//System.out.println("ERRO PARA SALVAR!");
				//writer.println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">");
			}
		}

		else if (("ALTERAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				String alteraCupom = request.getParameter("alteraCupom");
				String idCupom = request.getParameter("id");
				// É para alterar os campos do Cupom agora?
				// No caso 'não', então ele encaminha para tela de alteração com os dados
				// puxados do ID do banco
				if (alteraCupom.contentEquals("0")) {
					request.setAttribute("idCupom", idCupom);

					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/alterar-cupom.jsp").forward(request, response);
				} else {
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/consultar-cupons.jsp").forward(request, response);
				}

			} else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
				// Guarda a mensagem que veio da Strategy na variável para que 
				// seja exibida na tela 'tela-mensagem.jsp'
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				System.out.println("ERRO PARA ALTERAR CUPOM!");
				
				//writer.println(resultado.getMensagem());
				//System.out.println("ERRO PARA ALTERAR!");
				//writer.println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">");
			}
		}

		else if (("EXCLUIR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/consultar-cupons.jsp").forward(request, response);
			} else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
				// Guarda a mensagem que veio da Strategy na variável para que 
				// seja exibida na tela 'tela-mensagem.jsp'
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				System.out.println("ERRO PARA EXCLUIR CUPOM!");
				
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
