package com.les.roupa.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.les.roupa.core.dominio.Cupom;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.ItemPedido;
import com.les.roupa.core.dominio.Pedido;
import com.les.roupa.core.dominio.Produto;

public class ItemPedidoDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para SALVAR o Item do Pedido
	 * @param entidade
	 */
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		
		String sql = "insert into item_pedido "+
				"(id_produto,nome,qtdeProduto,precoVenda,id_pedido,dt_pedido) "
				+ "values (?,?,?,?,?,?)";
		
		try {
			ItemPedido itemPedido = (ItemPedido) entidade;
			
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			
			stmt.setString(1,itemPedido.getProduto().getId());
			stmt.setString(2,itemPedido.getProduto().getNome());
			stmt.setString(3,itemPedido.getQtdeProduto());
			stmt.setString(4,itemPedido.getProduto().getPrecoVenda());
			stmt.setString(5,itemPedido.getIdPedido());
			stmt.setString(6,itemPedido.getDt_cadastro());
			
			
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
	} // Alterar
	
	/**
	 * Metodo para EXCLUIR
	 */
	public void excluir (EntidadeDominio entidade) {
		openConnection();
	} // Excluir
	

	/**
	 * Metodo para Listar TODOS OS ITENS do Pedido 
	 * @param entidade
	 * @return
	 */
	public List<EntidadeDominio> consultar (EntidadeDominio entidade){
		openConnection();
		try {
			List<EntidadeDominio> itensPedido = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from item_pedido");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Produto
				
				ItemPedido item = new ItemPedido();
				Produto produto = new Produto();
								
				item.setProduto(produto);
				
				produto.setId(rs.getString("id"));
				produto.setNome(rs.getString("nome"));
				item.setQtdeProduto(rs.getString("qtdeProduto"));
				produto.setPrecoVenda(rs.getString("precoVenda"));
				item.setIdPedido(rs.getString("id_pedido"));
				item.setDt_cadastro(rs.getString("dt_pedido"));
				
								
				// adicionando o objeto à lista
				itensPedido.add(item);
			}
			rs.close();
			stmt.close();
			return itensPedido;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar todos os itens do pedido
	

	/**
	 * Metodo para Listar Itens do Pedido por ID do Pedido
	 * @param entidade
	 * @return
	 */
	public List<ItemPedido> consultarItemPedidoByIdPedido (String idPedido){
		openConnection();
		try {
			List<ItemPedido> itemPedido = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from item_pedido where id_pedido = ?");
			stmt.setString(1, idPedido);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Produto
				
				ItemPedido item = new ItemPedido();
				Produto produto = new Produto();
								
				item.setId(rs.getString("id"));
				
				produto.setId(rs.getString("id_produto"));
				produto.setNome(rs.getString("nome"));
				produto.setPrecoVenda(rs.getString("precoVenda"));
				
				item.setProduto(produto);
				
				item.setQtdeProduto(rs.getString("qtdeProduto"));
				item.setIdPedido(rs.getString("id_pedido"));
				item.setDt_cadastro(rs.getString("dt_pedido"));
				
				// adicionando o objeto à lista
				itemPedido.add(item);
			}
			rs.close();
			stmt.close();
			return itemPedido;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar Itens do Pedido por ID do Pedido

	
	/**
	 * Metodo para Listar os Itens do Pedido por ID
	 * @param entidade
	 * @return
	 */
	public List<ItemPedido> consultarItemPedidoById (String id){
		openConnection();
		try {
			List<ItemPedido> itemPedido = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from item_pedido where id = ?");
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Produto
				
				ItemPedido item = new ItemPedido();
				Produto produto = new Produto();
								
				item.setId(rs.getString("id"));
				
				produto.setId(rs.getString("id_produto"));
				produto.setNome(rs.getString("nome"));
				produto.setPrecoVenda(rs.getString("precoVenda"));
				
				item.setProduto(produto);
				
				item.setQtdeProduto(rs.getString("qtdeProduto"));
				item.setIdPedido(rs.getString("id_pedido"));
				item.setDt_cadastro(rs.getString("dt_pedido"));
				
				// adicionando o objeto à lista
				itemPedido.add(item);
			}
			rs.close();
			stmt.close();
			return itemPedido;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar Itens do Pedido por ID

	

	/**
	 * Metodo para Alterar Quantidade do item do pedido trocado
	 * @param entidade
	 * @return
	 */
	public void alterarQtdeItemPedido (String qtdeProduto, String id) {
		openConnection();
		
		String sql = "update item_pedido set qtdeProduto=? where id=?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, qtdeProduto);
			stmt.setString(2, id);
			
			stmt.execute();
			stmt.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Alterar a nova quantidade do Item do pedido
	
	
}
	