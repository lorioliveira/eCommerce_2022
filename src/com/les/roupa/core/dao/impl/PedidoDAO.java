package com.les.roupa.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Pedido;
import com.les.roupa.core.dominio.Produto;

public class PedidoDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para SALVAR o Pedido
	 * @param entidade
	 */
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		
		String sql = "insert into pedido "+
				"(precoTotalProduto,precoFrete,precoTotal,statusPedido,id_cliente,id_endereco,"
				+ "cartao1,valorCartao1,cartao2,valorCartao2,desconto,dt_pedido) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			Pedido pedido = (Pedido) entidade;
			
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			
			stmt.setString(1,pedido.getPrecoTotalProduto());
			stmt.setString(2,pedido.getPrecoFrete());
			stmt.setString(3,pedido.getPrecoTotal());
			stmt.setString(4,pedido.getStatusPedido());
			stmt.setString(5,pedido.getIdCliente());
			stmt.setString(6,pedido.getIdEndereco());
			stmt.setString(7,pedido.getCartao1());
			stmt.setString(8,pedido.getValorCartao1());
			stmt.setString(9,pedido.getCartao2());
			stmt.setString(10,pedido.getValorCartao2());
			stmt.setString(11,pedido.getDesconto());
			stmt.setString(12,pedido.getDt_cadastro());
			
			
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
		
		String sql = "update pedido set statusPedido =? where id =?";
		
		try {
			Pedido pedido = (Pedido) entidade;
			
			
			if(pedido.getAlteraPedido().contentEquals("1")) {
				PreparedStatement stmt = connection.prepareStatement(sql);
				
				stmt.setString(1,pedido.getStatusPedido());
				stmt.setString(2,pedido.getId());
				
				
				stmt.execute();
				stmt.close();
			}else {
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Alterar
	

	
	
	/**
	 * Metodo para EXCLUIR
	 */
	public void excluir (EntidadeDominio entidade) {
		openConnection();
		
	} // Excluir
	

	/**
	 * Metodo para Listar TODOS os Pedidos 
	 * @param entidade
	 * @return
	 */
	public List<EntidadeDominio> consultar (EntidadeDominio entidade){
		openConnection();
		try {
			List<EntidadeDominio> pedidos = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from pedido");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Produto
				
				Pedido pedido = new Pedido();
								
				pedido.setId(rs.getString("id"));
				pedido.setPrecoTotalProduto(rs.getString("precoTotalProduto"));
				pedido.setPrecoFrete(rs.getString("precoFrete"));
				pedido.setPrecoTotal(rs.getString("precoTotal"));
				pedido.setStatusPedido(rs.getString("statusPedido"));
				pedido.setIdCliente(rs.getString("id_cliente"));
				pedido.setIdEndereco(rs.getString("id_endereco"));
				pedido.setCartao1(rs.getString("cartao1"));
				pedido.setValorCartao1(rs.getString("valorCartao1"));
				pedido.setCartao2(rs.getString("cartao2"));
				pedido.setValorCartao2(rs.getString("valorCartao2"));
				pedido.setDesconto(rs.getString("desconto"));
				pedido.setDt_cadastro(rs.getString("dt_pedido"));
				
				
				
				// adicionando o objeto à lista
				pedidos.add(pedido);
			}
			rs.close();
			stmt.close();
			return pedidos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar TODOS os Pedidos

	
	/**
	 * Metodo para Listar o ultimo Pedido cadastrado no sistema
	 * @param entidade
	 * @return
	 */
	public List<Pedido> consultarUltimoPedidoCadastrado (EntidadeDominio entidade){
		openConnection();
		try {
			List<Pedido> pedidos = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM pedido WHERE id=(SELECT max(id) FROM pedido)");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Pedido
				Pedido pedido = new Pedido();
				
				pedido.setId(rs.getString("id"));
				pedido.setPrecoTotalProduto(rs.getString("precoTotalProduto"));
				pedido.setPrecoFrete(rs.getString("precoFrete"));
				pedido.setPrecoTotal(rs.getString("precoTotal"));
				pedido.setStatusPedido(rs.getString("statusPedido"));
				pedido.setIdCliente(rs.getString("id_cliente"));
				pedido.setIdEndereco(rs.getString("id_endereco"));
				pedido.setCartao1(rs.getString("cartao1"));
				pedido.setValorCartao1(rs.getString("valorCartao1"));
				pedido.setCartao2(rs.getString("cartao2"));
				pedido.setValorCartao2(rs.getString("valorCartao2"));
				pedido.setDesconto(rs.getString("desconto"));
				pedido.setDt_cadastro(rs.getString("dt_pedido"));
				
				// adicionando o objeto à lista
				pedidos.add(pedido);
			}
			rs.close();
			stmt.close();
			return pedidos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar ultimo Pedido cadastrado
	
	

	/**
	 * Metodo para Listar Pedido por ID do Cliente
	 * @param entidade
	 * @return
	 */
	public List<Pedido> consultarPedidoByIdCliente (String idCliente){
		openConnection();
		try {
			List<Pedido> pedidos = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from pedido where id_cliente = ? ");
			stmt.setString(1, idCliente);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Produto
				
				Pedido pedido = new Pedido();
								
				pedido.setId(rs.getString("id"));
				pedido.setPrecoTotalProduto(rs.getString("precoTotalProduto"));
				pedido.setPrecoFrete(rs.getString("precoFrete"));
				pedido.setPrecoTotal(rs.getString("precoTotal"));
				pedido.setStatusPedido(rs.getString("statusPedido"));
				pedido.setIdCliente(rs.getString("id_cliente"));
				pedido.setIdEndereco(rs.getString("id_endereco"));
				pedido.setCartao1(rs.getString("cartao1"));
				pedido.setValorCartao1(rs.getString("valorCartao1"));
				pedido.setCartao2(rs.getString("cartao2"));
				pedido.setValorCartao2(rs.getString("valorCartao2"));
				pedido.setDesconto(rs.getString("desconto"));
				pedido.setDt_cadastro(rs.getString("dt_pedido"));
				
				
				
				// adicionando o objeto à lista
				pedidos.add(pedido);
			}
			rs.close();
			stmt.close();
			return pedidos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar pedido por ID do cliente

	
	

	/**
	 * Metodo para Listar Pedido por ID
	 * @param entidade
	 * @return
	 */
	public List<Pedido> consultarPedidoById (String idPedido){
		openConnection();
		try {
			List<Pedido> pedidos = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from pedido where id = ? ");
			stmt.setString(1, idPedido);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Produto
				
				Pedido pedido = new Pedido();
								
				pedido.setId(rs.getString("id"));
				pedido.setPrecoTotalProduto(rs.getString("precoTotalProduto"));
				pedido.setPrecoFrete(rs.getString("precoFrete"));
				pedido.setPrecoTotal(rs.getString("precoTotal"));
				pedido.setStatusPedido(rs.getString("statusPedido"));
				pedido.setIdCliente(rs.getString("id_cliente"));
				pedido.setIdEndereco(rs.getString("id_endereco"));
				pedido.setCartao1(rs.getString("cartao1"));
				pedido.setValorCartao1(rs.getString("valorCartao1"));
				pedido.setCartao2(rs.getString("cartao2"));
				pedido.setValorCartao2(rs.getString("valorCartao2"));
				pedido.setDesconto(rs.getString("desconto"));
				pedido.setDt_cadastro(rs.getString("dt_pedido"));
				
				
				
				// adicionando o objeto à lista
				pedidos.add(pedido);
			}
			rs.close();
			stmt.close();
			return pedidos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar pedido por ID 
	
}

	