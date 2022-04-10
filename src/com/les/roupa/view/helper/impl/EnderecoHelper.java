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

import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Resultado;
import com.les.roupa.core.dominio.Usuario;
import com.les.roupa.view.helper.IViewHelper;

public class EnderecoHelper implements IViewHelper {
	
	Endereco endereco = null;
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// Verifica qual operação do botão foi acionada - SALVAR/ALTERAR/CONSULTAR/EXCLUIR
		String operacao = request.getParameter("operacao");
		
		String id = null;
        String cep = null;
		String logradouro = null;
		String numero = null;
		String bairro = null;
        String cidade = null;
        String estado = null;
        String pais = null;
        String tipoResidencia = null;
        String observacoes = null;
        String tipoEndereco = null;
        String idCliente = null;
        
        String alteraEndereco = null;
        
     // salva a data atual na tabela
     		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
     		Date date = new Date();
     		String dataAtual; 
     		dataAtual = dateFormat.format(date);
		
		if (("CONSULTAR").equals(operacao)) {
			endereco = new Endereco();
			
			id = request.getParameter("id");
			
			endereco.setId(id);
		}
		
		else if (("SALVAR").equals(operacao)) {
			endereco = new Endereco();
			
			// Atributos da classe endereço
			
			id = request.getParameter("id");
			logradouro = request.getParameter("logradouro");
			numero = request.getParameter("numero");
			bairro = request.getParameter("bairro");
			cep = request.getParameter("cep");
			cidade = request.getParameter("cidade");
			estado = request.getParameter("estado");
			pais = request.getParameter("pais");
			tipoResidencia = request.getParameter("tipoResidencia");
			observacoes = request.getParameter("observacoes");
			tipoEndereco = request.getParameter("tipoEndereco");
			idCliente = request.getParameter("idCliente");
			alteraEndereco = request.getParameter("alteraEndereco");
			
			
			// Atribuindo os valores que forma coletados do HTML para o Endereço
			endereco.setId(id);
			endereco.setLogradouro(logradouro);
			endereco.setNumero(numero);
			endereco.setBairro(bairro);
			endereco.setCep(cep);
			endereco.setCidade(cidade);
			endereco.setEstado(estado);
			endereco.setPais(pais);
			endereco.setTipoResidencia(tipoResidencia);
			endereco.setObservacoes(observacoes);
			endereco.setTipoEnd(tipoEndereco);		
			endereco.setIdCliente(idCliente);
			endereco.setAlteraEndereco(alteraEndereco);
			endereco.setData_Cadastro(dataAtual);
		}
		
		else if (("ALTERAR").equals(operacao)) {
			endereco = new Endereco();
			
			// Atributos da classe endereço
			id = request.getParameter("id");
			logradouro = request.getParameter("logradouro");
			numero = request.getParameter("numero");
			bairro = request.getParameter("bairro");
			cep = request.getParameter("cep");
			cidade = request.getParameter("cidade");
			estado = request.getParameter("estado");
			pais = request.getParameter("pais");
			tipoResidencia = request.getParameter("tipoResidencia");
			observacoes = request.getParameter("observacoes");
			tipoEndereco = request.getParameter("tipoEndereco");
			alteraEndereco = request.getParameter("alteraEndereco");
			
			idCliente = request.getParameter("idCliente");
			
			// Atribuindo os valores que foram coletados do HTML para o Endereço
			endereco.setId(id);
			endereco.setLogradouro(logradouro);
			endereco.setNumero(numero);
			endereco.setBairro(bairro);
			endereco.setCep(cep);
			endereco.setCidade(cidade);
			endereco.setEstado(estado);
			endereco.setPais(pais);
			endereco.setTipoResidencia(tipoResidencia);
			endereco.setObservacoes(observacoes);
			endereco.setTipoEnd(tipoEndereco);
			endereco.setAlteraEndereco(alteraEndereco);
			endereco.setData_Cadastro(dataAtual);
			
			endereco.setIdCliente(idCliente);
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			endereco = new Endereco();
			
			id = request.getParameter("id");
			idCliente = request.getParameter("idCliente");
			
			endereco.setId(id);
			endereco.setIdCliente(idCliente);
		}
		
		return endereco;
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
				request.getRequestDispatcher("JSP/minhaConta2.jsp").forward(request, response);
			} 
			else {
				// mostra as mensagens de ERRO se houver
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				request.getRequestDispatcher("JSP/minhaConta2.jsp").forward(request, response);
				System.out.println("ERRO PARA CONSULTAR ENDERECO!");
			}
		}
		
		else if (("SALVAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// foi utilizado o getEntidades do resultado para poder pegar o Login consultado
				List<EntidadeDominio> entidades = resultado.getEntidades();
				// feito o CAST de Entidade para o Usuario (pegando o primeiro indice de Entidade)
				Endereco endereco = (Endereco) entidades.get(0);
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				
				sessao.setAttribute("enderecosCliente", endereco.getTodosEnderecos());
				
				// Mensagem de conta criada para aparece na modal 
				resultado.setMensagem("Endereço salvo com sucesso!");
				
				// pendura o "resultado" na requisicao e manda para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo
				request.getRequestDispatcher("JSP/minhaConta2.jsp").forward(request, response);
			
			}
			else {
				// mostra as mensagens de ERRO se houver
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				request.getRequestDispatcher("JSP/minhaConta2.jsp").forward(request, response);
				System.out.println("ERRO PARA SALVAR ENDERECO!");
			
			}
		}
		
		else if (("ALTERAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// foi utilizado o getEntidades do resultado para poder pegar o Login consultado
				List<EntidadeDominio> entidades = resultado.getEntidades();
				// feito o CAST de Entidade para o Usuario (pegando o primeiro indice de Entidade)
				Endereco endereco = (Endereco) entidades.get(0);
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				
				sessao.setAttribute("enderecosCliente", endereco.getTodosEnderecos());
				// Este apenas puxa apenas o endereço que sera alterado
				sessao.setAttribute("enderecoAlterado", endereco.getTodosEnderecos().get(0));
				

				// Mensagem de conta criada para aparece na modal 
				resultado.setMensagem("Endereço alterado com sucesso!");
				
				// pendura o "resultado" na requisicao e manda para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				
				String alteraEndereco = request.getParameter("alteraEndereco");
				String idEndereco = request.getParameter("id");
				
				// Se eu estiver pela tela de listagem de endereços
				// chama o arquivo .JSP para edição do endereço
				if (alteraEndereco.contentEquals("0")) {					
					// pendura o "idEndereco" na requisição para poder mandar para o arquivo .JSP
					request.setAttribute("idEndereco", idEndereco);
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/alterarendereco.jsp").forward(request, response);
				}
				else {
					// pendura o "resultado" - mensagem de ERRO na requisição 
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/minhaConta2.jsp").forward(request, response);
				}
			
			} 
			else {
				// mostra as mensagens de ERRO se houver
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				request.getRequestDispatcher("JSP/minhaConta2.jsp").forward(request, response);
				System.out.println("ERRO PARA ALTERAR ENDERECO!");
			}
		}
		else if (("EXCLUIR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// foi utilizado o getEntidades do resultado para poder pegar o Login consultado
				List<EntidadeDominio> entidades = resultado.getEntidades();
				// feito o CAST de Entidade para o Usuario (pegando o primeiro indice de Entidade)
				Endereco endereco = (Endereco) entidades.get(0);
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				
				//atualiza a sessao com todos os enderecos existentes
				sessao.setAttribute("enderecosCliente", endereco.getTodosEnderecos());

				String idEndereco = request.getParameter("idEndereco");
				// pendura o "idEndereco" na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("idEndereco", idEndereco);
				

				// Mensagem de conta criada para aparece na modal 
				resultado.setMensagem("Endereço excluído com sucesso!");
								
				// pendura o "resultado" na requisicao e manda para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp, para poder listar os endereços novamente
				request.getRequestDispatcher("JSP/minhaConta2.jsp").forward(request, response);
			} 
			else {
				// mostra as mensagens de ERRO se houver
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				request.getRequestDispatcher("JSP/minhaConta2.jsp").forward(request, response);
				System.out.println("ERRO PARA EXCLUIR ENDERECO!");
			}
		}
	}

}
