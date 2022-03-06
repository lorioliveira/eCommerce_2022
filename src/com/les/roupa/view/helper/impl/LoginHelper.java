package com.les.roupa.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.les.roupa.view.helper.IViewHelper;
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Resultado;
import com.les.roupa.core.dominio.Usuario;

public class LoginHelper implements IViewHelper {
	Usuario usuario = null;
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		// Verifica qual operacao foi acionada
		String operacao = request.getParameter("operacao");
		
        String email = null;
        String senha = null;
		
		if (("CONSULTAR").equals(operacao)) {
			usuario = new Usuario();
			
			email = request.getParameter("email");
			senha = request.getParameter("senha");
			
			usuario.setEmail(email);
			usuario.setSenha(senha);
		}
		
		else if (("SALVAR").equals(operacao)) {
			usuario = new Usuario();
			
			email = request.getParameter("email");
			senha = request.getParameter("senha");
			
			usuario.setEmail(email);
			usuario.setSenha(senha);
		}
		
		else if (("ALTERAR").equals(operacao)) {
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			
		}
		
		return usuario;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Verifica qual operacao foi acionada
		String operacao = request.getParameter("operacao");
		
		// Usa para escrever na tela
		PrintWriter writer = response.getWriter();
		
		if (("CONSULTAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// foi utilizado o getEntidades do resultado para poder pegar o Login consultado
				List<EntidadeDominio> entidades = resultado.getEntidades();
				// feito o CAST de Entidade para o Usuario (pegando o primeiro indice de Entidade)
				Usuario usuario = (Usuario) entidades.get(0);
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				// salva na sessao o objeto "usuarioLogado", recebendo o valor de "usuario"
				sessao.setAttribute("usuarioLogado", usuario);
				
				sessao.setAttribute("enderecosCliente", usuario.getEnderecosCliente());
				
				
				if(usuario.getTipoCliente().equals("cliente")) {
					
					request.getRequestDispatcher("JSP/index2.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("JSP/indexAdm2.jsp").forward(request, response);
				}
			} 
			else {
				// pendura o "resultado" na requisicao para poder mandar para o arquivo .JSP
				// Guarda a mensagem que veio da Strategy na variavel para que 
				// seja exibida na tela 'tela-mensagem.jsp'
            	request.setAttribute("mensagemStrategy", resultado.getMensagem());
            	System.out.println("ERRO PARA CONSULTAR LOGIN!");
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				
			}
		}
		
		else if (("SALVAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// Requisicao da nova conta e redirecionada para tela de login
				request.getRequestDispatcher("JSP/login2.jsp").forward(request, response);
			}
			else {
				// mostra as mensagens de ERRO se houver
				// Guarda a mensagem que veio da Strategy na variavel para que 
				// seja exibida na tela 'tela-mensagem.jsp'
            	request.setAttribute("mensagemStrategy", resultado.getMensagem());
            	System.out.println("ERRO PARA SALVAR LOGIN!");
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
			}
		}
		
		//		ALTERAR SENHA
		else if (("ALTERAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
			// requisisao de alteracao de senha
			request.getRequestDispatcher("JSP/minhaConta2.jsp").forward(request, response);
		}
		else {
			// mostra as mensagens de ERRO se houver
			// Guarda a mensagem que veio da Strategy na variavel para que 
			// seja exibida na tela 'tela-mensagem.jsp'
        	request.setAttribute("mensagemStrategy", resultado.getMensagem());
        	System.out.println("ERRO PARA ALTERAR LOGIN - (Senha)!");
			request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
		}
	}
		
		else if (("EXCLUIR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// Limpa a sessao - Sair
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				//sessao.removeAttribute("usuarioLogado"); // remove somente 1 atributo criado
				sessao.invalidate(); // destroi o cookie JSESSIONID inteiro e cria outro
				
				// pendura o "resultado" na requisisao para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo de login ap�s sair da loja 
				request.getRequestDispatcher("JSP/logout.jsp").forward(request, response);
			}
			else {
				// se tiver alguma mensagem da Strategy, ira redirecionar para a tela de Login do mesmo jeito
				// Redireciona para o arquivo .jsp
				// Guarda a mensagem que veio da Strategy na variavel para que 
				// seja exibida na tela 'tela-mensagem.jsp'
            	request.setAttribute("mensagemStrategy", resultado.getMensagem());
            	System.out.println("ERRO PARA EXCLUIR LOGIN!");
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				
			}
		}
		
	}

}
