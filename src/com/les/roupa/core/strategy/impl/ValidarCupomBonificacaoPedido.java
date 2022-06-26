package com.les.roupa.core.strategy.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.les.roupa.core.dao.impl.CupomDAO;
import com.les.roupa.core.dominio.Cupom;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Pedido;
import com.les.roupa.core.strategy.IStrategy;

/**
 * Classe para validar a criação do Cupom de BONIFICACAO do Pedido
 */
public class ValidarCupomBonificacaoPedido implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Pedido pedido = (Pedido) entidade;
		CupomDAO cupomDAO = new CupomDAO();
		Cupom cupom = new Cupom();
		
		// salva a data atual no Cupom
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String dataAtual;
		dataAtual = dateFormat.format(date);
		
		double valor_convertido = 0;
		double total_pedido = (Double.parseDouble(pedido.getTotalItens()) + Double.parseDouble(pedido.getTotalFrete()));
		
		// se o valor total dos cupons selecionados for MAIOR que o total do pedido,
		// o valor total do pedido ficará negativo, então será gerado um Cupom de BONIFICACAO,
		// EX: se a compra for R$28.50, e eu usar 3 cupons de 10,00, (total R$30.00),
		// devo gerar um cupom de R$1.50, porque o valor dos cupons excedeu o valor do total do pedido.
		if(Double.parseDouble(pedido.getTotalCupons()) > total_pedido) {
			// conversão do valor negativo para o valor positivo
			valor_convertido = Math.abs(Double.parseDouble(pedido.getTotalPedido()));
			
			// gera o Cupom de Bonificação
			cupom.setNome("BONIFICACAO" + pedido.getIdCliente());
			cupom.setTipo("bonificacao");
			cupom.setValor(Double.toString(valor_convertido));
			cupom.setUtilizado("nao");
			cupom.setIdCliente(pedido.getIdCliente());
			cupom.setData_Cadastro(dataAtual);
			
			// gera o novo Cupom
			cupomDAO.salvar(cupom);
			
			// seta o valor do Total do Pedido para ZERO,
			// para não ficar com um valor negativo salvo no banco de dados
			pedido.setTotalPedido("0");
			
			return null;
		}
		else {
			return null;
		}
	}

}