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
 * Classe para validar Quantidade de SAIDA do Estoque
 *
 */
public class ValidarSaidaEstoque implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		EstoqueDAO dao = new EstoqueDAO();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> produtoSelecionado = new ArrayList<>();
		int quantidadeTotal;
		
		Estoque estoque = (Estoque) entidade;
		
		if(estoque.getTipo().equals("saida") || estoque.getTipo().equals("Saida")) {
			// faz a consulta pelo ID do produto selecionado, para poder somar a quantidade anterior do produto, 
			// com a quantidade de entrada, para poder salvar a "Quantidade Final"
			produtoSelecionado = produtoDAO.consultarProdutoById(estoque.getIdProduto());
			
			// verifica se a quantidade de saida � maior do que tem disponivel no estoque daquele produto
			if(Integer.parseInt(estoque.getQuantidade()) > Integer.parseInt(produtoSelecionado.get(0).getQtdeEstoque())) {
				return ("Quantidade de Saida esta maior que disponivel no estoque!<br>");
			}
			else {
				// faz a conta de subtra��o da quantidade de saida, menos a quantidade que j� tinha no produto
				quantidadeTotal = (Integer.parseInt(produtoSelecionado.get(0).getQtdeEstoque()) - Integer.parseInt(estoque.getQuantidade()));
				
				// guarda a soma no atributo "Quantidade Final"
				estoque.setQuantidadeTotal(Integer.toString(quantidadeTotal));
				
				// altera a quantidade do estoque do Produto
				dao.alterarQuantidadeProduto(estoque.getQuantidadeTotal(), estoque.getIdProduto());
				
				// faz a consulta pelo ID do produto selecionado novamente, 
				// para verificar a quantidade do estoque atualizado (ap�s a subtra��o feita a cima),
				// se a quantidade for igual a ZERO, o produto ser� "inativado"
				produtoSelecionado = produtoDAO.consultarProdutoById(estoque.getIdProduto());
				if(produtoSelecionado.get(0).getQtdeEstoque().equals("0") || produtoSelecionado.get(0).getQtdeEstoque().equals("null") ) {
					String motivo_inativacao;
					motivo_inativacao = " - SEM ESTOQUE";
					
					// faz a concatena��o da obeserva��o com a mensagem "SEM ESTOQUE"
					produtoSelecionado.get(0).setObservacao(produtoSelecionado.get(0).getObservacao() + motivo_inativacao);
					
					dao.inativaProdutoSemEstoque(produtoSelecionado.get(0).getId(), produtoSelecionado.get(0).getObservacao());
				}
				
				return null;
			}
		}
		else {
			return null;
		}
	}

}