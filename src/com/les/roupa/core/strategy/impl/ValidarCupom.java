package com.les.roupa.core.strategy.impl;

import java.util.List;

import com.les.roupa.core.dao.impl.CupomDAO;
import com.les.roupa.core.dominio.Cupom;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.VerificaCupom;
import com.les.roupa.core.strategy.IStrategy;

/**
 * Classe para validar o Cupom
 */
public class ValidarCupom implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		VerificaCupom verificaCupom = (VerificaCupom) entidade;
		CupomDAO dao = new CupomDAO();
		boolean cupomUtilizado = true;
		boolean cupomPromocional = false;
		
		List<Cupom> cupons = dao.consultarCupomByNome(verificaCupom.getCupom().getNome());
		List<Cupom> cuponsCliente = dao.consultarCupomByNomeAndIdCliente(verificaCupom.getCupom().getNome(), verificaCupom.getIdCliente());
		
		// 1� verifica se o Cupom foi digitado na tela
		if(verificaCupom.getCupom().getNome() == null || verificaCupom.getCupom().getNome().equals("")) {
			return ("Favor insira um Cupom.");
		}
		// 2� verifica se existe algum Cupom com o nome digitado na tela
		else if(cupons.isEmpty()) {
			return ("Cupom inexistente! Por favor, tente novamente.");
		}
		// 3� se existe algum Cupom com esse nome, ele passa para a proxima verifica��o
		else {
			// 4� faz um la�o com todos os cupons do sistema,  
			// para verificar se existe algum Cupom "promocional" com o mesmo nome digitado na tela
			for(Cupom coupon : cupons) {
				if (coupon.getTipo().equals("promocional")) {
					cupomPromocional = true;
				}
			}
			// n�o encontrou nenhum Cupom do tipo "promocional",
			// ent�o o Cupom � do tipo "troca" ou "devolucao"
			if (!cupomPromocional) {
				// 5� verifica se esse Cupom existe para o Usu�rio logado que digitou
				if(cuponsCliente.isEmpty()) {
					return ("Cupons inexistentes para esse Usu�rio!.");
				}
				// 6� se esse Cupom existe para esse Usu�rio, passa para a proxima verifica��o
				else {
					// 7� � feito um la�o de repeti��o nos Cupons do Usu�rio logado
					for(Cupom coupon : cuponsCliente) {
						// 8� caso esse Cupom ainda n�o foi utilizado, 
						// ele ir� seta a variavel "cupomUtilizado" como false
						if (coupon.getUtilizado().equals("nao")) {
							cupomUtilizado = false;
						}
					}
					// ser� verificado a variavel "cupomUtilizado",
					// ser for "true", ele mostrar� a mensagem
					if (cupomUtilizado) {
						return ("Cupom j� utilizado!.");
					}
					// caso contr�rio, ele pode usar esse cupom que ainda n�o foi utilizado
					else {
						return null;
					}
				}
			}
			// encontrou algum Cupom do tipo "promocional"
			else {
				return null;
			}
		}
	}
	
}