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
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Resultado;
import com.les.roupa.core.dominio.Usuario;

public class ClienteHelper implements IViewHelper {
	
	Cliente cliente = null;
	Usuario usuario = null;

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		// Verifica qual operação foi acionada
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
        
     // salva a data atual como Data de Cadastro na tabela de Cliente 
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
		
		// Verifica qual operação foi acionada
		String operacao = request.getParameter("operacao");
		
		// Usa para escrever na tela
		PrintWriter writer = response.getWriter();
		
		if (("CONSULTAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				//Redirecionamento normal para index com o usuario logado com sucesso
					request.getRequestDispatcher("JSP/index2.jsp").forward(request, response);
			}
						
			else {
				// se houver, mostra as mensagens de ERRO logo na tela ao logar
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				request.getRequestDispatcher("JSP/login2.jsp").forward(request, response);
			}
		}
		
		else if (("SALVAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				
				// Mensagem de conta criada para aparece na modal 
				resultado.setMensagem("Eba! Conta criada com sucesso! <br> Agora faça o seu login!");
				
				// pendura o "resultado" na requisicao e manda para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				request.getRequestDispatcher("JSP/login2.jsp").forward(request, response);
			}
			else {
				// se houver, mostra as mensagens de ERRO 
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				request.getRequestDispatcher("JSP/novaConta2.jsp").forward(request, response);
			}
		}
		
		else if (("ALTERAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// foi utilizado o getEntidades do resultado para poder pegar o Login consultado
				List<EntidadeDominio> entidades = resultado.getEntidades();
				// feito o CAST de Entidade para o Usuario (pegando o primeiro indice de Entidade)
				Cliente cliente = (Cliente) entidades.get(0);
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				// atualiza a sessao com todos os clientes alterados
				sessao.setAttribute("todosClientes", cliente.getUsuario().getTodosClientes());
				//cliente selecionado para alteração - opcao ALTERAR
				sessao.setAttribute("clienteAlterado", cliente.getUsuario().getTodosClientes().get(0));
				
				String alteraCliente = request.getParameter("alteraCliente");
				String id = request.getParameter("id");
				
				// É para alterar os campos do cliente agora? 
				// No caso 'não', então ele encaminha para tela de alteração com os dados puxados do banco
				if(alteraCliente.equals("0")) {
					request.setAttribute("idCliente", id);
					
					// Mensagem de boas vindas para aparece na modal
					resultado.setMensagem("Cliente alterado com sucesso! ");
					
					// pendura o "resultado" na requisicao e manda para o arquivo .JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					request.getRequestDispatcher("JSP/alterarCliente2.jsp").forward(request, response);
				}else {
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/indexAdm2.jsp").forward(request, response);
				}
			} 
			else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
			}
		}
		//		TELA ADMIN
		else if (("EXCLUIR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/indexAdm2.jsp").forward(request, response);
			} 
			else {
				// se houver, mostra as mensagens de ERRO com botão para voltar a tela anterior
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
			}
		}
	}

}
