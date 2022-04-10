package com.les.roupa.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Estoque;
import com.les.roupa.core.dominio.Produto;

public class EstoqueDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para salvar o Estoque
	 * @param entidade
	 */
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		
		String sql = "insert into estoque "+
				"(id_produto, tipo, quantidade_entrada_saida, valor_custo, fornecedor, dt_entrada, quantidade_final, dt_cadastro)" +
				"values (?,?,?,?,?,?,?,?)";
		
		try {
			Estoque estoque = (Estoque) entidade;
			
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setString(1, estoque.getIdProduto());
			stmt.setString(2, estoque.getTipo());
			stmt.setString(3, estoque.getQuantidadeEntradaSaida());
			stmt.setString(4, estoque.getValorCusto());
			stmt.setString(5, estoque.getFornecedor());
			stmt.setString(6, estoque.getDtEntrada());
			stmt.setString(7, estoque.getQuantidadeFinal());
			stmt.setString(8, estoque.getData_Cadastro());
			
			// executa
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Salvar
	
	/**
	 * Metodo para alterar o Estoque
	 * @param entidade
	 */
	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Alterar
	
	
	/**
	 * Metodo para excluir o Estoque
	 * @param entidade
	 */
	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Excluir
	
	
	/**
	 * Metodo para consultar o Estoque
	 * @param entidade
	 */
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		try {
			Estoque estoqueEntidade = (Estoque) entidade;
			List<EntidadeDominio> listEstoque = new ArrayList<>();
			Estoque novoEstoque = new Estoque();
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			
			PreparedStatement stmt = connection.prepareStatement("select * from estoque where id_produto=?");
			stmt.setString(1, estoqueEntidade.getIdProduto());
			ResultSet rs = stmt.executeQuery();
			
			List<Estoque> estoques = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Estoque
				Estoque estqoueItem = new Estoque();
				
				estqoueItem.setId(rs.getString("id"));
				estqoueItem.setIdProduto(rs.getString("id_produto"));
				estqoueItem.setTipo(rs.getString("tipo"));
				estqoueItem.setQuantidadeEntradaSaida(rs.getString("quantidade_entrada_saida"));
				estqoueItem.setValorCusto(rs.getString("valor_custo"));
				estqoueItem.setFornecedor(rs.getString("fornecedor"));
				estqoueItem.setDtEntrada(rs.getString("dt_entrada"));
				estqoueItem.setQuantidadeFinal(rs.getString("quantidade_final"));
				estqoueItem.setData_Cadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				estoques.add(estqoueItem);
			}
			
			if (estoques.size() > 0) {
				for(Estoque stock : estoques) {
					// busca o Produto do Estoque, pelo ID do cliente no Estoque
					List<Produto> produto = produtoDAO.consultarProdutoById(stock.getIdProduto());
					
					// salva o nome do Produto no Estoque
					stock.setNomeProduto(produto.get(0).getNome());
				}
			}
			
			novoEstoque.setEstoqueDoProduto(estoques);
			
			listEstoque.add(novoEstoque);
				
			rs.close();
			stmt.close();
			return listEstoque;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
				
	} // Consultar
	
	
	/**
	 * Metodo para alterar a quantidade do Produto
	 * @param entidade
	 */
	public void alterarQuantidadeProduto (String quantidadeFinal, String idProduto) {
		openConnection();
		
		String sql = "update produto set " +
					 "qtdeEstoque=? " +
					 "where id=?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, quantidadeFinal);
			stmt.setString(2, idProduto);
			
			stmt.execute();
			stmt.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Alterar quantidade Produto
	
	
	/**
	 * Metodo para Inativar o Produto sem Estoque
	 * @param entidade
	 */
	public void inativaProdutoSemEstoque (String idProduto, String msgObservacao) {
		openConnection();
		
		String sql = "update produto set " +
					 "status='inativo', observacao=? " +
					 "where id=?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, msgObservacao);
			stmt.setString(2, idProduto);
			
			stmt.execute();
			stmt.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Inativar Produto sem Estoque
	
	
	/**
	 * Metodo para Ativar o Produto no Estoque
	 * @param entidade
	 */
	public void ativarProdutoEstoque (String idProduto) {
		openConnection();
		
		String sql = "update produto set " +
					 "status='ativo' " +
					 "where id=?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, idProduto);
			
			stmt.execute();
			stmt.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Ativar Produto no Estoque
	
}