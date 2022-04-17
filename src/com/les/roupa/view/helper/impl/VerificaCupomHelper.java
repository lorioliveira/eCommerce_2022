package com.les.roupa.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.les.roupa.core.dominio.Cupom;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Resultado;
import com.les.roupa.core.dominio.VerificaCupom;
import com.les.roupa.view.helper.IViewHelper;

/**
 * ViewHelper - Cupom (Carrinho - Cliente)
 * @author Lorena Oliveira
 *
 */
public class VerificaCupomHelper implements IViewHelper {

	VerificaCupom verificaCupom = null;
	Cupom cupom = null;
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
		String nomeCupom = null;
		String idCliente = null;
		
		if (("CONSULTAR").equals(operacao)) {
			verificaCupom = new VerificaCupom();
			cupom = new Cupom();
			
			// capturando os valores do HTML e passando para o Cupom, logo em sequencia passando para o Verifica Cupom
			nomeCupom = request.getParameter("cupom");
			idCliente = request.getParameter("idCliente");
			
			// Atribuindo os valores capturados do HTML para o Verifica Cupom
			cupom.setNome(nomeCupom);
			verificaCupom.setCupom(cupom);
			verificaCupom.setIdCliente(idCliente);
		}
		
		else if (("SALVAR").equals(operacao)) {

		}
		
		else if (("ALTERAR").equals(operacao)) {
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			
		}
		
		return verificaCupom;
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
				String nomeCupom = request.getParameter("cupom");
				String idCliente = request.getParameter("idCliente");
				boolean adicionaNovoCupomNaSessao = true;
				List<Cupom> cupomParaAdicionarNaSessao = new ArrayList<>();
				
				// foi utilizado o getEntidades do resultado para poder pegar o cartao
				List<EntidadeDominio> entidades = resultado.getEntidades();
				// feito o CAST de Entidade para o VerificaCupom (pegando o primeiro indice de Entidade)
				VerificaCupom verificaCupomEntidade = (VerificaCupom) entidades.get(0);
				
				
				HttpSession sessao = request.getSession();

				cupomParaAdicionarNaSessao = (List<Cupom>) sessao.getAttribute("cupons");
				
				// todos os Cupons
				for (Cupom coupon : verificaCupomEntidade.getNomeCupons()) {
					// 1º se o primeiro Cupom encontrado for do tipo "promocional"
					if (coupon.getTipo().equals("promocional")) {
						for(Cupom cupomDaSessao : cupomParaAdicionarNaSessao) {
							if (cupomDaSessao.getTipo().equals("promocional")) {
								// atribui a nova mensagem para poder mostra na pagina .JSP
								resultado.setMensagem("Já existe um Cupom do tipo Promocional vinculado ao Pedido!");
								
								adicionaNovoCupomNaSessao = false;
								break;
							}
							else {
								// não encontrou nenhum cupom do tipo "promocional" na Sessão
								adicionaNovoCupomNaSessao = true;
							}	
						}
						
						// verifica se é para adicionar um novo Cupom na Sessão
						if (adicionaNovoCupomNaSessao) {
							// passa o cupom selecionado para a variavel que será responsavel para atualizar a sessão dos cupons
							cupomParaAdicionarNaSessao.add(coupon);
							
							// atribui a nova mensagem
							resultado.setMensagem("Cupom aplicado com sucesso!");
						}
					}
					else {
						if (coupon.getIdCliente().equals(idCliente)) {
							if ( coupon.getUtilizado().equals("nao")) {
								// passa o cupom selecionado para a variavel que será responsavel para atualizar a sessão dos cupons
								cupomParaAdicionarNaSessao.add(coupon);
								
								// atribui a nova mensagem 
								resultado.setMensagem("Cupom aplicado com sucesso!");
							}
						}
					}
				}
				
				// adiciona o cupom selecionado na sessão e atualiza
				sessao.setAttribute("cupons", cupomParaAdicionarNaSessao);
				
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/carrinho2.jsp").forward(request, response);
			}
			else {
				// mostra as mensagens de ERRO se houver
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/carrinho2.jsp").forward(request, response);
			}
		}
		
		else if (("SALVAR").equals(operacao)) {
			
		}
		
		else if (("ALTERAR").equals(operacao)) {
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {

		}
		
	}

}