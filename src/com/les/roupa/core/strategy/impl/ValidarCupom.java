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
		
		// 1º verifica se o Cupom foi digitado na tela
		if(verificaCupom.getCupom().getNome() == null || verificaCupom.getCupom().getNome().equals("")) {
			return ("Favor insira um Cupom.");
		}
		// 2º verifica se existe algum Cupom com o nome digitado na tela
		else if(cupons.isEmpty()) {
			return ("Cupom inexistente! Por favor, tente novamente.");
		}
		// 3º se existe algum Cupom com esse nome, ele passa para a proxima verificação
		else {
			// 4º faz um laço com todos os cupons do sistema,  
			// para verificar se existe algum Cupom "promocional" com o mesmo nome digitado na tela
			for(Cupom coupon : cupons) {
				if (coupon.getTipo().equals("promocional")) {
					cupomPromocional = true;
				}
			}
			// não encontrou nenhum Cupom do tipo "promocional",
			// então o Cupom é do tipo "troca" ou "devolucao"
			if (!cupomPromocional) {
				// 5º verifica se esse Cupom existe para o Usuário logado que digitou
				if(cuponsCliente.isEmpty()) {
					return ("Cupons inexistentes para esse Usuário!.");
				}
				// 6º se esse Cupom existe para esse Usuário, passa para a proxima verificação
				else {
					// 7ª é feito um laço de repetição nos Cupons do Usuário logado
					for(Cupom coupon : cuponsCliente) {
						// 8º caso esse Cupom ainda não foi utilizado, 
						// ele irá seta a variavel "cupomUtilizado" como false
						if (coupon.getUtilizado().equals("nao")) {
							cupomUtilizado = false;
						}
					}
					// será verificado a variavel "cupomUtilizado",
					// ser for "true", ele mostrará a mensagem
					if (cupomUtilizado) {
						return ("Cupom já utilizado!.");
					}
					// caso contrário, ele pode usar esse cupom que ainda não foi utilizado
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