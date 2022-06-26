package com.les.roupa.core.strategy.impl;

import java.util.List;

import com.les.roupa.core.dao.impl.ProdutoDAO;
import com.les.roupa.core.dominio.Carrinho;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Produto;
import com.les.roupa.core.strategy.IStrategy;

/**
 * Classe para validar se a quantidade selecionada no item do carrinho, 
 * não seja maior que a quantidade disponivel do estoque,
 * e verifica se a quantidade selecionada é menor que zero
 */
public class ValidarQuantidadeSelecionada implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Carrinho carrinho = (Carrinho) entidade;
		ProdutoDAO dao = new ProdutoDAO();
		
		// pesquisa no banco o produto selecionado
		List<Produto> produtoSelecionado = dao.consultarProdutoById(carrinho.getDetalheProduto().getProduto().getId());
		
		// verifica se a quantidade selecionada � menor que zero
		if (Integer.parseInt(carrinho.getDetalheProduto().getProduto().getQuantidadeSelecionada()) < 0) {
			return ("Selecione uma quantidade maior que ZERO!");
		}
		// verifica se a quantidade selecionada no item do carrinho, � maior que disponivel no estoque,
		// feito o CAST de String para INT, para poder fazer o calculo "Integer.parseInt(String)"
		else if(Integer.parseInt(carrinho.getDetalheProduto().getProduto().getQuantidadeSelecionada()) > Integer.parseInt(produtoSelecionado.get(0).getQtdeEstoque())) {
			return ("Quantidade selecionada é maior que disponivel no estoque!");
		}
		else {
			return null;
		}
	}
	
}