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
import com.les.roupa.core.dao.impl.CupomDAO;
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.Cupom;
import com.les.roupa.core.dominio.CupomCarrinho;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Resultado;
import com.les.roupa.core.dominio.Usuario;

public class CupomCarrinhoHelper implements IViewHelper {

	CupomCarrinho cupomCarrinho = null;

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {

		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");

		String id = null;
		String id_cupom = null;
		String id_cliente = null;
		String valor = null;
		String status = null;

		// salva a data atual na tabela de Cupom
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String dataAtual;
		dataAtual = dateFormat.format(date);

		if (("CONSULTAR").equals(operacao)) {
			cupomCarrinho = new CupomCarrinho();

			id = request.getParameter("id");

			cupomCarrinho.setId(id);
		}

		else if (("SALVAR").equals(operacao)) {
			cupomCarrinho = new CupomCarrinho();

			id_cupom = request.getParameter("id_cupom");
			
			//Consulta os cupons pelo ID
			CupomDAO cupomDAO = new CupomDAO();
			List<Cupom> cupons = cupomDAO.consultarCupomById(id_cupom);
			// Pelo ID pega o valor do cupom
			valor = cupons.get(0).getValor();
			
			//Todo cupom cadastrado estará com status ATIVO
			status = "ativo";
			
			//Usuario
			Usuario usuarioLogado = new Usuario();
		    
		    HttpSession sessao = request.getSession();
		    usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");  
		    
		    cupomCarrinho.setIdCliente(usuarioLogado.getId());			

			cupomCarrinho.setIdCupom(id_cupom);
			cupomCarrinho.setValor(valor);
			cupomCarrinho.setStatus(status);
			cupomCarrinho.setDt_cadastro(dataAtual);
		}

		else if (("ALTERAR").equals(operacao)) {
			cupomCarrinho = new CupomCarrinho();

			id_cupom = request.getParameter("id_cupom");
			valor = request.getParameter("valor");
			status = request.getParameter("status");
			id = request.getParameter("id");
			//alteraCupom = request.getParameter("alteraCupom");

			cupomCarrinho.setIdCupom(id_cupom);
			cupomCarrinho.setValor(valor);
			cupomCarrinho.setStatus(status);
			cupomCarrinho.setId(id);

			//cupom.setAlteraCupom(alteraCupom); 

			cupomCarrinho.setDt_cadastro(dataAtual);
		}

		else if (("EXCLUIR").equals(operacao)) {
			cupomCarrinho = new CupomCarrinho();

			id = request.getParameter("id");

			cupomCarrinho.setId(id);

		}

		return cupomCarrinho;
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
				System.out.println("ERRO PARA CONSULTAR CUPOM NO CARRINHO!");
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
			} else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
				// Guarda a mensagem que veio da Strategy na variável para que 
				// seja exibida na tela 'tela-mensagem.jsp'
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				System.out.println("ERRO PARA SALVAR CUPOM NO CARRINHO!");
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


			} else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
				// Guarda a mensagem que veio da Strategy na variável para que 
				// seja exibida na tela 'tela-mensagem.jsp'
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				System.out.println("ERRO PARA ALTERAR CUPOM NO CARRINHO!");
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
				request.getRequestDispatcher("JSP/consultar-cupons.jsp").forward(request, response);
			} else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
				// Guarda a mensagem que veio da Strategy na variável para que 
				// seja exibida na tela 'tela-mensagem.jsp'
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				System.out.println("ERRO PARA EXCLUIR CUPOM NO CARRINHO!");
				
				/*writer.println(resultado.getMensagem());
				System.out.println("ERRO PARA EXCLUIR!");
				writer.println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">");*/
			}
		}
	}

}
