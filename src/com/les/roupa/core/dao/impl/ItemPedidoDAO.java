package com.les.roupa.core.dao.impl;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.ItemPedido;
import com.les.roupa.core.dominio.Pedido;
import com.les.roupa.core.dominio.Produto;

/**
 * DAO para ITEM DO PEDIDO
 * @author Lorena Oliveira 
 * 
 */
public class ItemPedidoDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para salvar os Itens do Pedido
	 * @param entidade
	 */
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		
		String sql = "insert into pedido_item "+
				"(id_produto, nome, valor_de_venda, quantidade, id_pedido, trocado, dt_cadastro)" +
				"values (?,?,?,?,?,?,?)";
		
		try {
			ItemPedido item_pedido = (ItemPedido) entidade;
			
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setString(1, item_pedido.getProduto().getId());
			stmt.setString(2, item_pedido.getProduto().getNome());
			stmt.setString(3, item_pedido.getProduto().getPrecoVenda());
			stmt.setString(4, item_pedido.getProduto().getQuantidadeSelecionada());
			stmt.setString(5, item_pedido.getIdPedido());
			stmt.setString(6, item_pedido.getTrocado());
			stmt.setString(7, item_pedido.getData_Cadastro());
			
			// executa
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Salvar
	
	/**
	 * Metodo para alterar os Itens do Pedido
	 * @param entidade
	 */
	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Alterar
	
	
	/**
	 * Metodo para excluir os Itens do Pedido
	 * @param entidade
	 */
	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Excluir
	
	
	/**
	 * Metodo para consultar os Itens do Pedido
	 * @param entidade
	 */
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		openConnection();
		try {
			ItemPedido item_pedido_entidade = (ItemPedido) entidade;
			ItemPedido novoItemPedido = new ItemPedido();
			List<EntidadeDominio> listItemPedido = new ArrayList<>();
			
			PreparedStatement stmt = connection.prepareStatement("select * from pedido_item where id_pedido=?");
			stmt.setString(1, item_pedido_entidade.getIdPedido());
			ResultSet rs = stmt.executeQuery();
			
			List<ItemPedido> itens_pedido = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Pedido
				ItemPedido item_pedidoItem = new ItemPedido();
				Produto produto = new Produto();
				
				item_pedidoItem.setId(rs.getString("id"));
				
				produto.setId(rs.getString("id_produto"));
				produto.setNome(rs.getString("nome"));
				produto.setPrecoVenda(rs.getString("valor_de_venda"));
				produto.setQuantidadeSelecionada(rs.getString("quantidade"));
				item_pedidoItem.setProduto(produto);
				
				item_pedidoItem.setIdPedido(rs.getString("id_pedido"));
				item_pedidoItem.setTrocado(rs.getString("trocado"));
				item_pedidoItem.setData_Cadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				itens_pedido.add(item_pedidoItem);
			}
			
			stmt = connection.prepareStatement("select * from pedido where id=?");
			stmt.setString(1, item_pedido_entidade.getIdPedido());
			rs = stmt.executeQuery();
			
			List<Pedido> pedidos = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Pedido
				Pedido pedidoItem = new Pedido();
				
				pedidoItem.setId(rs.getString("id"));
				pedidoItem.setTotalItens(rs.getString("total_itens"));
				pedidoItem.setTotalFrete(rs.getString("total_frete"));
				pedidoItem.setTotalPedido(rs.getString("total_pedido"));
				pedidoItem.setStatus(rs.getString("status"));
				pedidoItem.setIdCliente(rs.getString("id_cliente"));
				pedidoItem.setIdEndereco(rs.getString("id_endereco"));
				pedidoItem.setFormaPagamento(rs.getString("forma_pagamento"));
				pedidoItem.setIdCartao1(rs.getString("id_cartao_1"));
				pedidoItem.setValorCartao1(rs.getString("valor_cartao_1"));
				pedidoItem.setIdCartao2(rs.getString("id_cartao_2"));
				pedidoItem.setValorCartao2(rs.getString("valor_cartao_2"));
				pedidoItem.setTotalCupons(rs.getString("total_cupons"));
				pedidoItem.setTrocado(rs.getString("trocado"));
				pedidoItem.setData_Cadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				pedidos.add(pedidoItem);
			}
			
			stmt = connection.prepareStatement("select * from endereco where id=?");
			stmt.setString(1, pedidos.get(0).getIdEndereco());
			rs = stmt.executeQuery();
			
			List<Endereco> enderecos = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Endereço
				Endereco enderecoItem = new Endereco();
				
				enderecoItem.setId(rs.getString("id"));
				enderecoItem.setCep(rs.getString("cep"));
				enderecoItem.setLogradouro(rs.getString("logradouro"));
				enderecoItem.setNumero(rs.getString("numero"));
				enderecoItem.setBairro(rs.getString("bairro"));
				enderecoItem.setCidade(rs.getString("cidade"));
				enderecoItem.setEstado(rs.getString("estado"));
				enderecoItem.setPais(rs.getString("pais"));
				enderecoItem.setTipoResidencia(rs.getString("tipoResidencia"));
				enderecoItem.setObservacoes(rs.getString("observacoes"));
				enderecoItem.setTipoEnd(rs.getString("tipoEndereco"));
				enderecoItem.setData_Cadastro(rs.getString("data_Cadastro"));
				
				enderecoItem.setIdCliente(rs.getString("id_cliente"));
				
				// adicionando o objeto à lista
				enderecos.add(enderecoItem);
			}
			
			novoItemPedido.setItensPedido(itens_pedido);
			novoItemPedido.setPedidos(pedidos);
			novoItemPedido.setEnderecoPedido(enderecos);
			
			listItemPedido.add(novoItemPedido);
				
			rs.close();
			stmt.close();
			return listItemPedido;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
				
	} // Consultar
	
	
	/**
	 * Metodo para consultar o Item do Pedido por ID
	 */
	public List<ItemPedido> consultarItemPedidoById (String idItemPedido) {
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from pedido_item where id=?");
			stmt.setString(1, idItemPedido);
			ResultSet rs = stmt.executeQuery();
			
			List<ItemPedido> itens_pedido = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Pedido
				ItemPedido item_pedidoItem = new ItemPedido();
				Produto produto = new Produto();
				
				item_pedidoItem.setId(rs.getString("id"));
				
				produto.setId(rs.getString("id_produto"));
				produto.setNome(rs.getString("nome"));
				produto.setPrecoVenda(rs.getString("valor_de_venda"));
				produto.setQuantidadeSelecionada(rs.getString("quantidade"));
				item_pedidoItem.setProduto(produto);
				
				item_pedidoItem.setIdPedido(rs.getString("id_pedido"));
				item_pedidoItem.setTrocado(rs.getString("trocado"));
				item_pedidoItem.setData_Cadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				itens_pedido.add(item_pedidoItem);
			}
				
			rs.close();
			stmt.close();
			return itens_pedido;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
				
	} // Consultar o Item do Pedido por ID
	
	
	/**
	 * Metodo para alterar a trocação do Item do Pedido
	 * @param entidade
	 */
	public void alterarTrocacaoItemPedido (String idItemPedido) {
		openConnection();
		
		String sql = "update pedido_item set " +
					 "trocado='sim' " +
					 "where id=?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, idItemPedido);
			
			stmt.execute();
			stmt.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Alterar trocação do Item do Pedido
	
	
	/**
	 * Metodo para consultar se exite algum Item do Pedido que esteja com o status de trocado como "nao"
	 */
	public List<ItemPedido> consultarItemPedidoByIdPedidoAndTrocadoNao (String idPedido) {
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from pedido_item where id_pedido=? and trocado='nao'");
			stmt.setString(1, idPedido);
			ResultSet rs = stmt.executeQuery();
			
			List<ItemPedido> itens_pedido = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Pedido
				ItemPedido item_pedidoItem = new ItemPedido();
				Produto produto = new Produto();
				
				item_pedidoItem.setId(rs.getString("id"));
				
				produto.setId(rs.getString("id_produto"));
				produto.setNome(rs.getString("nome"));
				produto.setPrecoVenda(rs.getString("valor_de_venda"));
				produto.setQuantidadeSelecionada(rs.getString("quantidade"));
				item_pedidoItem.setProduto(produto);
				
				item_pedidoItem.setIdPedido(rs.getString("id_pedido"));
				item_pedidoItem.setTrocado(rs.getString("trocado"));
				item_pedidoItem.setData_Cadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				itens_pedido.add(item_pedidoItem);
			}
				
			rs.close();
			stmt.close();
			return itens_pedido;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
				
	} // Consultar se exite algum Item do Pedido que esteja com o status de trocado como "nao"
	
	
	/**
	 * Metodo para consultar se exite algum Item do Pedido que esteja com o status de trocado como "sim"
	 */
	public List<ItemPedido> consultarItemPedidoByIdPedidoAndTrocadoSim (String idPedido) {
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from pedido_item where id_pedido=? and trocado='sim'");
			stmt.setString(1, idPedido);
			ResultSet rs = stmt.executeQuery();
			
			List<ItemPedido> itens_pedido = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Pedido
				ItemPedido item_pedidoItem = new ItemPedido();
				Produto produto = new Produto();
				
				item_pedidoItem.setId(rs.getString("id"));
				
				produto.setId(rs.getString("id_produto"));
				produto.setNome(rs.getString("nome"));
				produto.setPrecoVenda(rs.getString("valor_de_venda"));
				produto.setQuantidadeSelecionada(rs.getString("quantidade"));
				item_pedidoItem.setProduto(produto);
				
				item_pedidoItem.setIdPedido(rs.getString("id_pedido"));
				item_pedidoItem.setTrocado(rs.getString("trocado"));
				item_pedidoItem.setData_Cadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				itens_pedido.add(item_pedidoItem);
			}
				
			rs.close();
			stmt.close();
			return itens_pedido;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
				
	} // Consultar se exite algum Item do Pedido que esteja com o status de trocado como "sim"
	
	
	/**
	 * Metodo para consultar os 3 Produtos mais vendidos
	 */
	public List<ItemPedido> consultar3ProdutosMaisVendidos () {
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select id_produto, sum(quantidade) as quantidade_vendido from pedido_item group by id_produto order by sum(quantidade) desc LIMIT 3;");
			ResultSet rs = stmt.executeQuery();
			
			List<ItemPedido> itens_pedido = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Pedido
				ItemPedido item_pedidoItem = new ItemPedido();
				Produto produto = new Produto();
				
				produto.setId(rs.getString("id_produto"));
				produto.setQuantidadeSelecionada(rs.getString("quantidade_vendido"));
				item_pedidoItem.setProduto(produto);
				
				// adicionando o objeto à lista
				itens_pedido.add(item_pedidoItem);
			}
				
			rs.close();
			stmt.close();
			return itens_pedido;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
				
	} // consultar os 3 Produtos mais vendidos
	
	
	/**
	 * Metodo para alterar a nova quantidade do Item do Pedido
	 * @param entidade
	 */
	public void alterarQuantidadeItemPedido (String qtdeItemPedido, String idItemPedido) {
		openConnection();
		
		String sql = "update pedido_item set " +
					 "quantidade=? " +
					 "where id=?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, qtdeItemPedido);
			stmt.setString(2, idItemPedido);
			
			stmt.execute();
			stmt.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Alterar a nova quantidade do Item do Pedido
	
}