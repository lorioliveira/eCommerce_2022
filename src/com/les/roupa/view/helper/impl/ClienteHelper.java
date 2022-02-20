package com.les.roupa.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.les.roupa.view.helper.IViewHelper;
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Resultado;
import com.les.roupa.core.dominio.Usuario;

public class ClienteHelper implements IViewHelper {
	
	Cliente cliente = null;
	Usuario usuario = null;

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
        String email = null;
        String senha = null;
        String confirmarSenha = null;
        String nome = null;
        String cpf = null;
        String data_Nascimento = null;
        String telefone = null;
        String genero = null;
        String id = null;
        String alteraCliente = null;
        String status = null;
        String tipoCliente = null;
        
     // salva a data atual na tabela de Cliente
 		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
 		Date date = new Date();
 		String dataAtual;
 		dataAtual = dateFormat.format(date);

		
		if (("CONSULTAR").equals(operacao)) {
			cliente = new Cliente();
		}
		
		else if (("SALVAR").equals(operacao)) {
			cliente = new Cliente();
			usuario = new Usuario();
			
			// Atributos da classe Usuario
			email = request.getParameter("email");
			senha = request.getParameter("senha");
			confirmarSenha = request.getParameter("confirmarSenha");
			
			// Atributos da classe Pessoa
			nome = request.getParameter("nome");
			cpf = request.getParameter("cpf");
			data_Nascimento = request.getParameter("data_Nascimento");
			telefone = request.getParameter("telefone");
			genero = request.getParameter("genero");
			status = request.getParameter("status");
			tipoCliente = request.getParameter("tipoCliente");
			alteraCliente = request.getParameter("alteraCliente");
			
						
			// Atribuindo os valores capturados do HTML para o cliente
			usuario.setEmail(email);
			usuario.setSenha(senha);
			usuario.setConfirmarSenha(confirmarSenha);
			cliente.setUsuario(usuario);
			
			
			cliente.setNome(nome);
			cliente.setCpf(cpf);
			cliente.setData_Nascimento(data_Nascimento);
			cliente.setTelefone(telefone);
			cliente.setGenero(genero);
			cliente.setStatus(status);
			cliente.setTipoCliente(tipoCliente);
			cliente.setAlteraCliente(alteraCliente);
			cliente.setData_Cadastro(dataAtual);
			
		}
		
		else if (("ALTERAR").equals(operacao)) {
			cliente = new Cliente();
			usuario = new Usuario();
			
			// Atributos da classe Usuario
			email = request.getParameter("email");
			senha = request.getParameter("senha");
			confirmarSenha = request.getParameter("confirmarSenha");
			id = request.getParameter("id");
						
			// Atributos da classe Pessoa
			nome = request.getParameter("nome");
			cpf = request.getParameter("cpf");
			data_Nascimento = request.getParameter("data_Nascimento");
			telefone = request.getParameter("telefone");
			genero = request.getParameter("genero");
			alteraCliente = request.getParameter("alteraCliente");
			status = request.getParameter("status");			
									
			// Atribuindo os valores capturados do HTML para o cliente
			usuario.setEmail(email);
			usuario.setSenha(senha);
			usuario.setConfirmarSenha(confirmarSenha);
			cliente.setUsuario(usuario);
						
			cliente.setId(id);
			cliente.setNome(nome);
			cliente.setCpf(cpf);
			cliente.setData_Nascimento(data_Nascimento);
			cliente.setTelefone(telefone);
			cliente.setGenero(genero);
			cliente.setAlteraCliente(alteraCliente);
			cliente.setStatus(status);
			
			cliente.setData_Cadastro(dataAtual);

		}
		
		else if (("EXCLUIR").equals(operacao)) {
			cliente = new Cliente();
			
			id = request.getParameter("id");
			
			cliente.setId(id);
		}
		
		return cliente;
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
				//Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/consultar-cliente.jsp").forward(request, response);
			}
						
			else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior

				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				/*
				 * writer.println(resultado.getMensagem());
				 * System.out.println("ERRO PARA CONSULTAR!");
				 *  writer.println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">"
				 * );
				 */
			}
		}
		
		else if (("SALVAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				request.getRequestDispatcher("JSP/login_entrar.jsp").forward(request, response);
			}
			else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior

				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
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
				String alteraCliente = request.getParameter("alteraCliente");
				String id = request.getParameter("id");
				
				// É para alterar os campos do cliente agora? 
				// No caso 'não', então ele encaminha para tela de alteração com os dados puxados do banco
				if(alteraCliente.equals("0")) {
					request.setAttribute("idCliente", id);
					
					request.getRequestDispatcher("JSP/alterar-cliente.jsp").forward(request, response);
				}else {
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/perfil2.jsp").forward(request, response);
				}
			} 
			else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
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
				request.getRequestDispatcher("JSP/consultar-cliente2.jsp").forward(request, response);
			} 
			else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior

				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
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
