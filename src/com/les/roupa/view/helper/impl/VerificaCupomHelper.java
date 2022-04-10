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

public class VerificaCupomHelper implements IViewHelper {

	VerificaCupom verificaCupom = null;
	Cupom cupom = null;
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		// Verifica qual opera��o do bot�o foi acionada
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
		
		// Verifica qual opera��o do bot�o foi acionada
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
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				// pega o objeto salvo em Sess�o com o nome "cupons",
				// e passa para o "cupomParaAdicionarNaSessao" (fazendo o CAST para o tipo List<Cupom>)
				cupomParaAdicionarNaSessao = (List<Cupom>) sessao.getAttribute("cupons");
				
				// o objeto que veio do banco, esta guardado no "verificaCupomEntidade.getNomeCupons()"
				// busca todos os Cupons no sistema com o mesmo nome que foi digitado na tela
				
				// faz um la�o com todos os Cupons
				for (Cupom coupon : verificaCupomEntidade.getNomeCupons()) {
					// 1� se o primeiro Cupom encontrado for do tipo "promocional",
					if (coupon.getTipo().equals("promocional")) {
						// verifica se na lista de cupons da sess�o, j� existe um cupom do tipo promocional,
						// pois poder� ter somente um Cupom do tipo promocional vinculado no pedido
						for(Cupom cupomDaSessao : cupomParaAdicionarNaSessao) {
							if (cupomDaSessao.getTipo().equals("promocional")) {
								// atribui a nova mensagem para poder mostra na pagina .JSP
								resultado.setMensagem("J� existe um Cupom do tipo Promocional vinculado ao Pedido!");
								
								adicionaNovoCupomNaSessao = false;
								break;
							}
							else {
								// n�o encontrou nenhum cupom do tipo "promocional" na Sess�o,
								// ent�o seta a variavel "adicionaNovoCupomNaSessao" como TRUE
								adicionaNovoCupomNaSessao = true;
							}	
						}
						
						// verifica se � para adicionar um novo Cupom na Sess�o
						if (adicionaNovoCupomNaSessao) {
							// passa o cupom selecionado para a variavel que ser� responsavel para atualizar a sess�o dos cupons
							cupomParaAdicionarNaSessao.add(coupon);
							
							// atribui a nova mensagem para poder mostra na pagina .JSP
							resultado.setMensagem("Cupom aplicado com sucesso!");
						}
					}
					// 2� caso contr�rio, verifica se o Cupom � do mesmo cliente logado,
					// e se o Cupom ja foi utilizado, se ainda n�o foi utilizado, o mesmo ser� salvo na Sess�o
					else {
						if (coupon.getIdCliente().equals(idCliente)) {
							if ( coupon.getUtilizado().equals("nao")) {
								// passa o cupom selecionado para a variavel que ser� responsavel para atualizar a sess�o dos cupons
								cupomParaAdicionarNaSessao.add(coupon);
								
								// atribui a nova mensagem para poder mostra na pagina .JSP
								resultado.setMensagem("Cupom aplicado com sucesso!");
							}
						}
					}
				}
				
				// adiciona o cupom selecionado na sess�o
				// atualiza o objeto "cupons" que esta salvo em sess�o, com o novo cupom selecionado
				sessao.setAttribute("cupons", cupomParaAdicionarNaSessao);
				
				// pendura o "resultado" na requisi��o para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/carrinho2.jsp").forward(request, response);
			}
			else {
				// mostra as mensagens de ERRO se houver
				// pendura o "resultado" na requisi��o para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/index2.jsp").forward(request, response);
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