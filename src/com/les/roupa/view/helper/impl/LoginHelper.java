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
import com.les.roupa.core.dominio.Cupom;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.PedidoTroca;
import com.les.roupa.core.dominio.Produto;
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
			usuario = new Usuario();
			
			email = request.getParameter("email");
			senha = request.getParameter("senha");
			
			usuario.setEmail(email);
			usuario.setSenha(senha);
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
				
				//Todas as listas que serao adicionadas a Sessao 
				sessao.setAttribute("enderecosCliente", usuario.getEnderecosCliente());
				sessao.setAttribute("todosProdutos", usuario.getTodosProdutos());
				sessao.setAttribute("cartoesCliente", usuario.getTodosCartoes());
				sessao.setAttribute("todosClientes", usuario.getTodosClientes());
				sessao.setAttribute("pedidosCliente", usuario.getPedidosCliente());
				sessao.setAttribute("todosPedidos", usuario.getTodosPedidos());
				sessao.setAttribute("cuponsCliente", usuario.getCuponsCliente());
				sessao.setAttribute("todosCupons", usuario.getTodosCupons());
				
				// ADMIN - salva na sessão o objeto "produtosAtivos", recebendo somente os produtos ativos do sistema - ESTOQUE
				sessao.setAttribute("produtosAtivos", usuario.getProdutosAtivos());
				
				
				List<PedidoTroca> itensPedidoTroca = new ArrayList<>();
				// salva na sessão o objeto "itensPedidoTroca", para quando for clicado no botão de "Solicitar Troca",
				// da tela do detalhes do pedido, ele poder adicionar os itens do pedido selecionados para gerar um pedido de troca
				sessao.setAttribute("itensPedidoTroca", itensPedidoTroca);
				
				
				List<Produto> detalheProduto = new ArrayList<>();
				// salva na sessão o objeto "detalheProduto", para quando for clicado no botão de "Adicionar",
				// da tela do detalhes do produto, ele poder adicionar os produtos selecionados para o carrinho
				sessao.setAttribute("itensCarrinho", detalheProduto);
				
				List<Cupom> cupons = new ArrayList<>();
				// salva na sessão o objeto "cupons", que será calculado dentro da tela do carrinho
				sessao.setAttribute("cupons", cupons);
				
				// Este apenas puxa apenas o cliente que sera alterado
				sessao.setAttribute("clienteAlterado", usuario.getTodosClientes().get(0));
				
				// Mensagem de boas vindas para aparece na modal
				resultado.setMensagem("Bem vinda(o) ao <b>Mirror Fashion</b>! ");
				
				// pendura o "resultado" na requisicao e manda para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				//Caso usuario logado for Cliente ou Admin 
				if(usuario.getTipoCliente().equals("cliente")) {
					
					request.getRequestDispatcher("JSP/index2.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("JSP/indexAdm2.jsp").forward(request, response);
				}
			} 
			else {
				
				// Guarda a mensagem que veio da Strategy na variavel para que 
				// seja exibida na tela 'tela-mensagem.jsp'
            	request.setAttribute("mensagemStrategy", resultado.getMensagem());
            	System.out.println("ERRO PARA CONSULTAR LOGIN!");
				request.getRequestDispatcher("JSP/login2.jsp").forward(request, response);
			}
		}
		
		else if (("SALVAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				
				// Mensagem de conta criada para aparece na modal 
				resultado.setMensagem("Eba! Conta criada com sucesso! <br> Agora faça o seu login!");
				
				// pendura o "resultado" na requisicao e manda para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Requisicao da nova conta e redirecionada para tela de login para acessar
				request.getRequestDispatcher("JSP/login2.jsp").forward(request, response);
			}
			else {
				// mostra as mensagens de ERRO se houver na tela do formulário da Nova Conta
            	request.setAttribute("mensagemStrategy", resultado.getMensagem());
            	System.out.println("ERRO PARA SALVAR LOGIN!");
				request.getRequestDispatcher("JSP/novaConta2.jsp").forward(request, response);
			}
		}
		
		//		ALTERAR SENHA - CLIENTE 
		
		else if (("ALTERAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
			// requisicao de alteracao de senha
				
				// Mensagem de senha alterada para aparece na modal 
				resultado.setMensagem("Senha alterada com sucesso!");
				
				// pendura o "resultado" na requisicao e manda para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
			request.getRequestDispatcher("JSP/minhaConta2.jsp").forward(request, response);
		}
		else {
			// mostra as mensagens de ERRO se houver na tela Minha Conta (perfil do Cliente)
        	request.setAttribute("mensagemStrategy", resultado.getMensagem());
        	System.out.println("ERRO PARA ALTERAR LOGIN - (Senha)!");
			request.getRequestDispatcher("JSP/minhaConta2.jsp").forward(request, response);
		}
	}
		
		else if (("EXCLUIR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// Limpa a sessao ao Sair
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				 
				sessao.invalidate(); // destroi o cookie JSESSIONID inteiro e cria outro
				
				// Mensagem de logout para aparece na modal ao sair - tela de Logout
				resultado.setMensagem("<b>Logout</b> foi realizado com sucesso! </br> Volte Sempre!");
				
				// pendura o "resultado" na requisicao e manda para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo de login apos sair da loja 
				request.getRequestDispatcher("JSP/logout2.jsp").forward(request, response);
			}
			else {
				// Mensagem de ERRO
            	request.setAttribute("mensagemStrategy", resultado.getMensagem());
            	System.out.println("ERRO PARA EXCLUIR LOGIN!");
				request.getRequestDispatcher("JSP/login2.jsp").forward(request, response);
				
			}
		}
		
	}

}
