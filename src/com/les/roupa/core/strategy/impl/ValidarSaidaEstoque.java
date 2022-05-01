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
 * Classe para validar a quantidade de saida do Estoque
 */
public class ValidarSaidaEstoque implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		EstoqueDAO dao = new EstoqueDAO();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> produtoSelecionado = new ArrayList<>();
		int quantidadeFinal;
		
		Estoque estoque = (Estoque) entidade;
		
		if(estoque.getTipo().equals("saida") || estoque.getTipo().equals("Saida")) {
			// faz a consulta pelo ID do produto selecionado, para poder somar a quantidade anterior do produto, 
			// com a quantidade de entrada, para poder salvar a "Quantidade Final"
			produtoSelecionado = produtoDAO.consultarProdutoById(estoque.getIdProduto());
			
			// verifica se a quantidade de saida é maior do que tem disponivel no estoque daquele produto
			if(Integer.parseInt(estoque.getQuantidadeEntradaSaida()) > Integer.parseInt(produtoSelecionado.get(0).getQtdeEstoque())) {
				return ("Quantidade de Saida é maior que disponivel no estoque!");
			}
			else {
				// faz a conta de subtração da quantidade de saida, menos a quantidade que já tinha no produto
				quantidadeFinal = (Integer.parseInt(produtoSelecionado.get(0).getQtdeEstoque()) - Integer.parseInt(estoque.getQuantidadeEntradaSaida()));
				
				// guarda a soma no atributo "Quantidade Final"
				estoque.setQuantidadeFinal(Integer.toString(quantidadeFinal));
				
				// altera a quantidade do estoque do Produto
				dao.alterarQuantidadeProduto(estoque.getQuantidadeFinal(), estoque.getIdProduto());
				
				// faz a consulta pelo ID do produto selecionado novamente, 
				// para verificar a quantidade do estoque atualizado (após a subtração feita a cima),
				// se a quantidade for igual a ZERO, o produto será "inativado"
				produtoSelecionado = produtoDAO.consultarProdutoById(estoque.getIdProduto());
				if(produtoSelecionado.get(0).getQtdeEstoque().equals("0")) {
					String motivo_inativacao;
					motivo_inativacao = " - SEM ESTOQUE";
					
					// faz a concatenação da obeservação com a mensagem "SEM ESTOQUE"
					produtoSelecionado.get(0).setMotivoStatus(produtoSelecionado.get(0).getQtdeEstoque() + motivo_inativacao);
					
					dao.inativaProdutoSemEstoque(produtoSelecionado.get(0).getId(), produtoSelecionado.get(0).getMotivoStatus());
				}
				
				return null;
			}
		}
		else {
			return null;
		}
	}

}