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
 * Classe para validar a quantidade de entrada do Estoque
 */
public class ValidarEntradaEstoque implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		EstoqueDAO dao = new EstoqueDAO();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> produtoSelecionado = new ArrayList<>();
		int quantidadeFinal;
		
		Estoque estoque = (Estoque) entidade;
		
		if(estoque.getTipo().equals("entrada") || estoque.getTipo().equals("Entrada") ) {
			// faz a consulta pelo ID do produto selecionado, para poder somar a quantidade anterior do produto, 
			// com a quantidade de entrada, para poder salvar a "Quantidade Final"
			produtoSelecionado = produtoDAO.consultarProdutoById(estoque.getIdProduto());
			
			// faz a conta de soma da quantidade de entrada, mais a quantidade que já tinha no produto
			quantidadeFinal = (Integer.parseInt(estoque.getQuantidadeEntradaSaida()) + Integer.parseInt(produtoSelecionado.get(0).getQtdeEstoque()));
			
			// guarda a soma no atributo "Quantidade Final"
			estoque.setQuantidadeFinal(Integer.toString(quantidadeFinal));
			
			// altera a quantidade do estoque do Produto
			dao.alterarQuantidadeProduto(estoque.getQuantidadeFinal(), estoque.getIdProduto());
			
			return null;
		}
		else {
			return null;
		}
	}

}