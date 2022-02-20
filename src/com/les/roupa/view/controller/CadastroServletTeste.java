package com.les.roupa.view.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.les.roupa.core.dao.impl.ClienteDAO;
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.Usuario;

//Registrando uma Servlet sem a necessidade do arquivo "web.xml".
//Parâmetros: nome da Servlet e a URL que ela vai acessar.
@WebServlet(name="Cadastro Servlet", urlPatterns="/cadastroTeste")


/**
 * Servlet feita para testar o CRUD
 * @author Lorena Oliveira
 */
public class CadastroServletTeste extends HttpServlet {
	
	// Servlet Principal do sistema
		protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			// vamos indicar que a nossa resposta será em HTML ao invés de texto
			response.setContentType("text/html");
			
			// Usa para escrever na tela
			PrintWriter writer = response.getWriter();
			
			// instanciando as classes
			Cliente cliente = new Cliente();
			ClienteDAO dao = new ClienteDAO();
			Usuario usuario = new Usuario();
			
			// Pegando as informações digitadas nos Forms do HTML
			/*cliente.setFlgAtivo(request.getParameter("flgAtivo"));*/
			
			usuario.setEmail(request.getParameter("email"));
			usuario.setSenha(request.getParameter("senha"));
			usuario.setConfirmarSenha(request.getParameter("confsenha"));
			cliente.setUsuario(usuario);
			
			cliente.setNome(request.getParameter("nome"));
			cliente.setCpf(request.getParameter("cpf"));
			cliente.setData_Nascimento(request.getParameter("dt_nasc"));
			cliente.setTelefone(request.getParameter("telefone"));
			/*cliente.setCep(request.getParameter("cep"));
			cliente.setLogradouro(request.getParameter("logradouro"));
			cliente.setNumero(request.getParameter("numero"));
			cliente.setBairro(request.getParameter("bairro"));
			cliente.setComplemento(request.getParameter("complemento"));*/
			//cliente.setCdCliente(request.getParameter("cd_cliente"));
			
			// Verificando quais botões foram clicados na tela
			String operacao = (request.getParameter("operacao"));
			
			if (("SALVAR").equals(operacao)) {
				// Salva o que foi digitado na tela
				dao.salvar(cliente);
				
				writer.println("<h1>Cadastro salvo com sucesso!</h1>");
				
				// Mostra o que foi gravado
				writer.println("<ul>");
				/*writer.println("<li>Ativo: " + cliente.getFlgAtivo() + "</li>");*/
				writer.println("<li>Email: " + usuario.getEmail() + "</li>");
				writer.println("<li>Senha: " + usuario.getSenha() + "</li>");
				writer.println("<li>Nome: " + cliente.getNome() + "</li>");
				writer.println("<li>CPF: " + cliente.getCpf() + "</li>");
				writer.println("<li>Data Nascimento: " + cliente.getData_Nascimento() + "</li>");
				writer.println("<li>Telefone: " + cliente.getTelefone() + "</li>");
				/*writer.println("<li>CEP: " + cliente.getCep() + "</li>");
				writer.println("<li>Logradouro: " + cliente.getLogradouro() + "</li>");
				writer.println("<li>Nº: " + cliente.getNumero() + "</li>");
				writer.println("<li>Bairro: " + cliente.getBairro() + "</li>");
				writer.println("<li>Complemento: " + cliente.getComplemento() + "</li>");	*/		
				//writer.println("<li>Codigo Cliente: " + cliente.getCdCliente() + "</li>");
				writer.println("</ul>");
				
				writer.println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">");
			}
			else if (("ALTERAR").equals(operacao)) {
				// Edita o cliente
				dao.alterar(cliente);
				
				writer.println("<h1>Cadastro Alterado com sucesso!</h1>");
				
				writer.println("<ul>");
				/*writer.println("<li>Ativo: " + cliente.getFlgAtivo() + "</li>");*/
				writer.println("<li>Email: " + usuario.getEmail() + "</li>");
				writer.println("<li>Senha: " + usuario.getSenha() + "</li>");
				writer.println("<li>Nome: " + cliente.getNome() + "</li>");
				writer.println("<li>CPF: " + cliente.getCpf() + "</li>");
				writer.println("<li>Data Nascimento: " + cliente.getData_Nascimento() + "</li>");
				writer.println("<li>Telefone: " + cliente.getTelefone() + "</li>");
				/*writer.println("<li>CEP: " + cliente.getCep() + "</li>");
				writer.println("<li>Logradouro: " + cliente.getLogradouro() + "</li>");
				writer.println("<li>Nº: " + cliente.getNumero() + "</li>");
				writer.println("<li>Bairro: " + cliente.getBairro() + "</li>");
				writer.println("<li>Complemento: " + cliente.getComplemento() + "</li>");	*/		
			//	writer.println("<li>Codigo Cliente: " + cliente.getCdCliente() + "</li>");
				writer.println("</ul> <br>");
				
				writer.println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">");
			}
			else if (("EXCLUIR").equals(operacao)) {
				// Deleta o cadastro do cliente conforme o codigo do cliente
				dao.excluir(cliente);
				
				writer.println("<h1>Cadastro Removido com sucesso!</h1>");
				
				writer.println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">");
			}
			else if (("CONSULTAR").equals(operacao)) {
				// Lista todos os clientes do BD
				writer.println("<h1>Listagem dos Clientes!</h1>");
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/lista-clientes-scriptlet.jsp").forward(request, response);
			}
		}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}
