package com.les.roupa.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Estoque;

public class EstoqueDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para salvar o Estoque
	 * @param entidade
	 */
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		
		String sql = "insert into estoque "+
				"(id_produto, tipo, quantidade, valorCompra, dt_entrada, quantidadeTotal, dt_cadastro)" +
				"values (?,?,?,?,?,?,?)";
		
		try {
			Estoque estoque = (Estoque) entidade;
			
			// prepared statement para inser��o
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setString(1, estoque.getIdProduto());
			stmt.setString(2, estoque.getTipo());
			stmt.setString(3, estoque.getQuantidade());
			stmt.setString(4, estoque.getValorCompra());
			stmt.setString(5, estoque.getDtEntrada());
			stmt.setString(6, estoque.getQuantidadeTotal());
			stmt.setString(7, estoque.getDt_cadastro());
			
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
			Estoque estoque = (Estoque) entidade;
			PreparedStatement stmt = connection.prepareStatement("select * from estoque where id_produto=?");
			stmt.setString(1, estoque.getIdProduto());
			ResultSet rs = stmt.executeQuery();
			
			List<EntidadeDominio> estoques = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Estoque
				Estoque estoqueItem = new Estoque();
				
				estoqueItem.setId(rs.getString("id"));
				estoqueItem.setIdProduto(rs.getString("id_produto"));
				estoqueItem.setTipo(rs.getString("tipo"));
				estoqueItem.setQuantidade(rs.getString("quantidade"));
				estoqueItem.setValorCompra(rs.getString("valorCompra"));
				estoqueItem.setDtEntrada(rs.getString("dt_entrada"));
				estoqueItem.setQuantidadeTotal(rs.getString("quantidadeTotal"));
				estoqueItem.setDt_cadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto � lista
				estoques.add(estoqueItem);
			}
				
			rs.close();
			stmt.close();
			return estoques;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
				
	} // Consultar
	
	
	/**
	 * Metodo para Alterar a quantidade do Produto (atualizar)
	 * @param entidade
	 */
	public void alterarQuantidadeProduto (String quantidadeFinal, String idProduto) {
		openConnection();
		
		String sql = "update produto set qtdeEstoque=? where id=?";
		
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
	public void inativaProdutoSemEstoque (String idProduto, String descricaoStatusProduto) {
		openConnection();
		
		String sql = "update produto set status='inativo', descricaoStatusProduto=? where id=?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, descricaoStatusProduto);
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
		
		String sql = "update produto set status='ativo' where id=?";
		
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