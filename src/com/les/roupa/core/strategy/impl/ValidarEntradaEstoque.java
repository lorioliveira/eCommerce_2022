package com.les.roupa.core.strategy.impl;

import java.util.List;
import java.util.ArrayList;

import com.les.roupa.core.dao.impl.EstoqueDAO;
import com.les.roupa.core.dao.impl.ProdutoDAO;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Estoque;
import com.les.roupa.core.dominio.Produto;
import com.les.roupa.core.strategy.IStrategy;

/**
 * Classe para validar Quantidade de ENTRADA do Estoque
 * 
 */
public class ValidarEntradaEstoque implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		EstoqueDAO dao = new EstoqueDAO();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> produtoSelecionado = new ArrayList<>();
		int quantidadeTotal;
		
		Estoque estoque = (Estoque) entidade;
		
		if(estoque.getTipo().equals("entrada") || estoque.getTipo().equals("Entrada")) {
			// faz a consulta pelo ID do produto selecionado, para poder somar a quantidade anterior do produto, 
			// com a quantidade de entrada, para poder salvar a "Quantidade Final"
			produtoSelecionado = produtoDAO.consultarProdutoById(estoque.getIdProduto());
			
			// faz a conta de soma da quantidade de entrada, mais a quantidade que j� tinha no produto
			quantidadeTotal = (Integer.parseInt(estoque.getQuantidade()) + Integer.parseInt(produtoSelecionado.get(0).getQtdeEstoque()));
			
			// guarda a soma no atributo "Quantidade Total"
			estoque.setQuantidadeTotal(Integer.toString(quantidadeTotal));
			
			// altera a quantidade do estoque do Produto
			dao.alterarQuantidadeProduto(estoque.getQuantidadeTotal(), estoque.getIdProduto());
			
			return null;
		}
		else {
			return null;
		}
	}

}