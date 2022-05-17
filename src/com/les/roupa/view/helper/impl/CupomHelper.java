package com.les.roupa.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.les.roupa.core.dominio.Cupom;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Resultado;
import com.les.roupa.view.helper.IViewHelper;

/**
 * ViewHelper - Cupom (Cadastro - Admin)
 * @author Lorena Oliveira
 * Abril / 2022
 *
 */
public class CupomHelper implements IViewHelper{
	
	Cupom cupom = null;

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
		String id = null;
        String nome = null;
        String valor = null;
        String tipo = null;
        String utilizado = null;
        String idCliente = null;
        String AlteraCupom = null;
        
        if (("CONSULTAR").equals(operacao)) {
        	cupom = new Cupom();
        }
        
        else if (("SALVAR").equals(operacao)) {
        	cupom = new Cupom();
        	
        	// capturando os valores do HTML e passando para o Cupoom
        	nome = request.getParameter("nome");
        	valor = request.getParameter("valor");
        	tipo = request.getParameter("tipo");
        	utilizado = request.getParameter("utilizado");
        	idCliente = request.getParameter("idCliente");
        	AlteraCupom = request.getParameter("alteraCupom");
        	
        	// Atribuindo os valores capturados do HTML para o Cupom
        	cupom.setNome(nome);
        	cupom.setValor(valor);
        	cupom.setTipo(tipo);
        	cupom.setUtilizado(utilizado);
        	cupom.setIdCliente(idCliente);
        	cupom.setAlteraCupom(AlteraCupom);
        }
        
        else if (("ALTERAR").equals(operacao)) {
        	cupom = new Cupom();
        	
        	// capturando os valores do HTML e passando para o Cupom
        	id = request.getParameter("idCupom");
        	nome = request.getParameter("nome");
        	valor = request.getParameter("valor");
        	tipo = request.getParameter("tipo");
        	utilizado = request.getParameter("utilizado");
        	idCliente = request.getParameter("idCliente");
        	AlteraCupom = request.getParameter("alteraCupom");
        	
        	// Atribuindo os valores capturados do HTML para o Cupom
        	cupom.setId(id);
        	cupom.setNome(nome);
        	cupom.setValor(valor);
        	cupom.setTipo(tipo);
        	cupom.setUtilizado(utilizado);
        	cupom.setAlteraCupom(AlteraCupom);
        	
        	// ajuste do bug de quando o cupom não tiver nenhum Cliente vinculado,
        	if (idCliente == null) {
        		cupom.setIdCliente(idCliente);
        	}
        	else if (idCliente.equals("null")) {
        		// se eu estiver pela tela de edição do Cupom,
        		// o valor do "idCliente" será "null", em formato de String, 
        		// então não atribui o valor ao objeto "cupom",
        		// pq se o valor for "null" em formato de String, irá acusar ERRO na alteração do Cupom na DAO.
        		System.out.println("erro !!");
        	}
        	else {
        		// caso contrário, se tiver algum Cliente para vincular,
        		// o valor será atribuido no Cupom
        		cupom.setIdCliente(idCliente);
        	}
        	
        }
        
        else if (("EXCLUIR").equals(operacao)) {
        	cupom = new Cupom();
        	
        	id = request.getParameter("idCupom");
        	
        	cupom.setId(id);
        }
		
		return cupom;
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
				List<EntidadeDominio> entidades = resultado.getEntidades();
				Cupom cupomEntidade = (Cupom) entidades.get(0);
				
				// pendura todos os cupons na requisição
				request.setAttribute("todosCuponsSistema", cupomEntidade.getTodosCupons());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/indexAdm2.jsp").forward(request, response);
			} 
			else {
				// mostra as mensagens de ERRO se houver
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/indexAdm2.jsp").forward(request, response);
			}
		}
		
		else if (("SALVAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// atribui a nova mensagem
				resultado.setMensagem("Cupom cadastrado com sucesso!");
				
				// pendura o "resultado" na requisição
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/indexAdm2.jsp").forward(request, response);
			}
			else {
				// mostra as mensagens de ERRO se houver
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/indexAdm2.jsp").forward(request, response);
			}
		}
		
		else if (("ALTERAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// foi utilizado o getEntidades do resultado para poder pegar o cartao
				List<EntidadeDominio> entidades = resultado.getEntidades();
				// feito o CAST de Entidade para o Cupom (pegando o primeiro indice de Entidade)
				Cupom cupomEntidade = (Cupom) entidades.get(0);
				
				String alteraCupom = request.getParameter("alteraCupom");
				
				if (alteraCupom.equals("0")) {
					// pendura o objeto "cupom" na requisição 
					request.setAttribute("cupomPesquisado", cupomEntidade.getCupomPesquisado());
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/alterarCupom2.jsp").forward(request, response);
				}
				else {
					// atribui a nova mensagem
					resultado.setMensagem("Cupom alterado com sucesso!");
					
					// pendura o "resultado" (mensagem) na requisição
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/indexAdm2.jsp").forward(request, response);
				}
			} 
			else {
				// mostra as mensagens de ERRO se houver
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/indexAdm2.jsp").forward(request, response);
			}
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				List<EntidadeDominio> entidades = resultado.getEntidades();
				Cupom cupomEntidade = (Cupom) entidades.get(0);
				
				// pendura todos os cupons na requisição 
				request.setAttribute("todosCuponsSistema", cupomEntidade.getTodosCupons());
				
				// atribui a mensagem ao resultado
				resultado.setMensagem("Cupom excluído com sucesso!");
				
				// Redireciona para o arquivo .jsp, para poder listar os cupons atualizados novamente
				request.getRequestDispatcher("JSP/indexAdm2.jsp").forward(request, response);
			} 
			else {
				// mostra as mensagens de ERRO se houver
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/indexAdm2.jsp").forward(request, response);
			}
		}
		
	}

}