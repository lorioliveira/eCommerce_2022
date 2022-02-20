package com.les.roupa.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.les.roupa.core.dominio.Carrinho;
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Produto;
import com.les.roupa.core.dominio.Usuario;

public class CarrinhoDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para SALVAR o Carrinho
	 * @param entidade
	 */
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		
		String sql = "insert into carrinho "+
				"(id_cliente,id_produto,qtdeProduto,status)" +
				"values (?,?,?,?)";
		
		try {
			Carrinho carrinho = (Carrinho) entidade;
			
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setString(1,carrinho.getIdCliente());
			stmt.setString(2,carrinho.getIdProduto());
			stmt.setString(3,carrinho.getQtdeProduto());
			stmt.setString(4,carrinho.getStatus());
			
			// executa
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Salvar
	
	/**
	 * Metodo para ALTERAR
	 * @param entidade
	 */
	public void alterar (EntidadeDominio entidade) {
		openConnection();
		String sql = "update carrinho set qtdeProduto=? where id=?";
		
		try {
			Carrinho carrinho = (Carrinho) entidade;
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1,carrinho.getQtdeProduto());
			stmt.setString(2,carrinho.getId());
			
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Alterar
	
	/**
	 * Metodo para EXCLUIR
	 */
	public void excluir (EntidadeDominio entidade) {
		openConnection();
		
		try {
			Carrinho carrinho = (Carrinho) entidade;
			
			PreparedStatement stmt = connection.prepareStatement("delete from carrinho where id=?");
			
			stmt.setString(1, carrinho.getId());
			
			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Excluir
	

	/**
	 * Metodo para Listar Carrinho 
	 * @param entidade
	 * @return
	 */
	public List<EntidadeDominio> consultar (EntidadeDominio entidade){
		openConnection();
		try {
			List<EntidadeDominio> carrinhos = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from carrinho");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Produto
				
				Carrinho car = new Carrinho();
								
				car.setId(rs.getString("id"));
				car.setIdCliente(rs.getString("id_cliente"));
				car.setIdProduto(rs.getString("id_produto"));
				car.setQtdeProduto(rs.getString("qtdeProduto"));
				car.setStatus(rs.getString("status"));
				
				// adicionando o objeto à lista
				carrinhos.add(car);
			}
			rs.close();
			stmt.close();
			return carrinhos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar


	/**
	 * Metodo para Listar Carrinho por status = ativo
	 * @param entidade
	 * @return
	 */
	public List<Carrinho> consultarCarrinhoByStatus (String idCliente){
		openConnection();
		try {
			List<Carrinho> carrinhos = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from carrinho where id_cliente = ? and status = 'ativo' ");
			stmt.setString(1, idCliente);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Produto
				
				Carrinho car = new Carrinho();
								
				car.setId(rs.getString("id"));
				car.setIdCliente(rs.getString("id_cliente"));
				car.setIdProduto(rs.getString("id_produto"));
				car.setQtdeProduto(rs.getString("qtdeProduto"));
				car.setStatus(rs.getString("status"));
				
				// adicionando o objeto à lista
				carrinhos.add(car);
			}
			rs.close();
			stmt.close();
			return carrinhos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar Carrinho por Status
	

	/**
	 * Metodo para ALTERAR Status do Carrinho
	 * @param entidade
	 */
	public void alterarStatus (String idCarrinho) {
		openConnection();		
		String sql = "update carrinho set status='inativo' where id=?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, idCarrinho);
			
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Alterar Status
	
}
	