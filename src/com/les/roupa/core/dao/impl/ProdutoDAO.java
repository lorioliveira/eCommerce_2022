package com.les.roupa.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Produto;

public class ProdutoDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para SALVAR 
	 */
	public void salvar(EntidadeDominio entidade) {
		/*
		 * openConnection();
		 * 
		 * String sql =
		 * "insert into produto (nome, descricao, categoria, cores, tamanho, precoCompra, "
		 * +
		 * "precoVenda, qtdeEstoque, foto, Data_Cadastro, status, grupoPrecificacao, descricaoStatusProduto) values (?,?,?,?,?,?,?,?,?,?,?,?,?)"
		 * ;
		 * 
		 * try { Produto produto = (Produto) entidade;
		 * 
		 * PreparedStatement stmt = connection.prepareStatement(sql);
		 * 
		 * stmt.setString(1,produto.getNome());
		 * stmt.setString(2,produto.getDescricao());
		 * stmt.setString(3,produto.getCategoria());
		 * stmt.setString(4,produto.getCores()); stmt.setString(5,produto.getTamanho());
		 * stmt.setString(6,produto.getPrecoCompra());
		 * stmt.setString(7,produto.getPrecoVenda());
		 * stmt.setString(8,produto.getQtdeEstoque());
		 * stmt.setString(9,produto.getFoto());
		 * stmt.setString(10,produto.getData_Cadastro());
		 * stmt.setString(11,produto.getStatus());
		 * stmt.setString(12,produto.getGrupoPrecificacao()); stmt.setString(13,
		 * produto.getStatusProduto());
		 * 
		 * stmt.execute(); stmt.close();
		 * 
		 * } catch (Exception e) { throw new RuntimeException(e); }
		 */
	} // Salvar
	
	
	/**
	 * Metodo para ALTERAR PRODUTO
	 * @param entidade
	 */
	public void alterar (EntidadeDominio entidade) {
		openConnection();
		
		String sql = "update produto set nome=?, descricao=?, categoria=?, cores=?, tamanho=?, precoCompra=?, "
				+ "precoVenda=?, qtdeEstoque=?, foto=?, dt_cadastro=?, status=?, grupoPrecificacao=?, motivoStatus=? where id=?";
		
		try {
			Produto produto = (Produto) entidade;
			
			// quando o atributo "alteraProduto" for igual a 1, ele altera o Produto
			if(produto.getAlteraProduto().contentEquals("1")) {
				PreparedStatement stmt = connection.prepareStatement(sql);
				
				stmt.setString(1,produto.getNome());
				stmt.setString(2,produto.getDescricao());
				stmt.setString(3,produto.getCategoria());
				stmt.setString(4,produto.getCores());
				stmt.setString(5,produto.getTamanho());
				stmt.setString(6,produto.getPrecoCompra());
				stmt.setString(7,produto.getPrecoVenda());
				stmt.setString(8,produto.getQtdeEstoque());
				stmt.setString(9,produto.getFoto());
				stmt.setString(10,produto.getData_Cadastro());
				stmt.setString(11,produto.getStatus());
				stmt.setString(12,produto.getGrupoPrecificacao());
				stmt.setString(13,produto.getMotivoStatus());
				stmt.setString(14,produto.getId());
				
				stmt.execute();
				
				//Listar todos os dados do Produto
				List<Produto> todosProdutos = new ArrayList<>();
				stmt = connection.prepareStatement("select * from produto where id = ?");
				stmt.setString(1, produto.getId());
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					// criando o objeto Produto
					Produto listaProduto = new Produto();
					
					listaProduto.setId(rs.getString("id"));
					listaProduto.setNome(rs.getString("nome"));
					listaProduto.setDescricao(rs.getString("descricao"));
					listaProduto.setCategoria(rs.getString("categoria"));
					listaProduto.setCores(rs.getString("cores"));
					listaProduto.setTamanho(rs.getString("tamanho"));
					listaProduto.setPrecoCompra(rs.getString("precoCompra"));
					listaProduto.setPrecoVenda(rs.getString("precoVenda"));
					listaProduto.setQtdeEstoque(rs.getString("qtdeEstoque"));
					listaProduto.setFoto(rs.getString("foto"));
					listaProduto.setData_Cadastro(rs.getString("dt_cadastro"));
					listaProduto.setStatus(rs.getString("status"));
					listaProduto.setGrupoPrecificacao(rs.getString("grupoPrecificacao"));
					listaProduto.setMotivoStatus(rs.getString("motivoStatus"));
										
					// adicionando o objeto a lista
					todosProdutos.add(listaProduto);
				}
				
				produto.setTodosProdutos(todosProdutos);
				
			}else {

				//Listar todos os dados de um Produto para alterar
				List<Produto> todosProdutos = new ArrayList<>();
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt = connection.prepareStatement("select * from produto where id = ?");
				stmt.setString(1, produto.getId());
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					
					// criando o objeto Produto
					Produto listaProduto = new Produto();
					
					listaProduto.setId(rs.getString("id"));
					listaProduto.setNome(rs.getString("nome"));
					listaProduto.setDescricao(rs.getString("descricao"));
					listaProduto.setCategoria(rs.getString("categoria"));
					listaProduto.setCores(rs.getString("cores"));
					listaProduto.setTamanho(rs.getString("tamanho"));
					listaProduto.setPrecoCompra(rs.getString("precoCompra"));
					listaProduto.setPrecoVenda(rs.getString("precoVenda"));
					listaProduto.setQtdeEstoque(rs.getString("qtdeEstoque"));
					listaProduto.setFoto(rs.getString("foto"));
					listaProduto.setData_Cadastro(rs.getString("dt_cadastro"));
					listaProduto.setStatus(rs.getString("status"));
					listaProduto.setGrupoPrecificacao(rs.getString("grupoPrecificacao"));
					listaProduto.setMotivoStatus(rs.getString("motivoStatus"));
										
					// adicionando o objeto a lista
					todosProdutos.add(listaProduto);
				}

				produto.setTodosProdutos(todosProdutos);

				rs.close();
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
		
	} // Excluir
	
	
	/**
	 * Metodo para Listar/Consultar PRODUTO 
	 * @param entidade
	 * @return
	 */
	public List<EntidadeDominio> consultar (EntidadeDominio entidade){
		openConnection();
		try {
			Produto produto = (Produto) entidade;
			PreparedStatement stmt = connection.prepareStatement("select * from produto");
			ResultSet rs = stmt.executeQuery();
			
			//Lista de Produtos criada
			List<EntidadeDominio> produtos = new ArrayList<>();
			
			while (rs.next()) {
				// criando o objeto Produto
				
				Produto prod = new Produto();
								
				prod.setId(rs.getString("id"));
				prod.setNome(rs.getString("nome"));
				prod.setDescricao(rs.getString("descricao"));
				prod.setCategoria(rs.getString("categoria"));
				prod.setCores(rs.getString("cores"));
				prod.setTamanho(rs.getString("tamanho"));
				prod.setPrecoCompra(rs.getString("precoCompra"));
				prod.setPrecoVenda(rs.getString("precoVenda"));
				prod.setQtdeEstoque(rs.getString("qtdeEstoque"));
				prod.setFoto(rs.getString("foto"));
				prod.setData_Cadastro(rs.getString("dt_cadastro"));
				prod.setStatus(rs.getString("status"));
				prod.setGrupoPrecificacao(rs.getString("grupoPrecificacao"));
				prod.setMotivoStatus(rs.getString("motivoStatus"));
				
								
				// adicionando o objeto à lista
				produtos.add(prod);
			}
			rs.close();
			stmt.close();
			return produtos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar
	
	
	/**
	 * Metodo para Listar Produto por ID
	 * @param entidade
	 * @return
	 */
	public List<Produto> consultarProdutoById (String idProduto){
		openConnection();
		try {
			List<Produto> produto = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from produto where id=? ");
			stmt.setString(1, idProduto);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Produto
				
				Produto prod = new Produto();
				
				prod.setId(rs.getString("id"));
				prod.setNome(rs.getString("nome"));
				prod.setDescricao(rs.getString("descricao"));
				prod.setCategoria(rs.getString("categoria"));
				prod.setCores(rs.getString("cores"));
				prod.setTamanho(rs.getString("tamanho"));
				prod.setPrecoCompra(rs.getString("precoCompra"));
				prod.setPrecoVenda(rs.getString("precoVenda"));
				prod.setQtdeEstoque(rs.getString("qtdeEstoque"));
				prod.setFoto(rs.getString("foto"));
				prod.setData_Cadastro(rs.getString("Data_Cadastro"));
				prod.setStatus(rs.getString("status"));
				prod.setGrupoPrecificacao(rs.getString("grupoPrecificacao"));
				prod.setMotivoStatus(rs.getString("motivoStatus"));
							
				
				// adicionando o objeto à lista
				produto.add(prod);
			}
			rs.close();
			stmt.close();
			return produto;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar
	
	/**
	 * Metodo para Listar os Produtos somente Ativos
	 * @param entidade
	 * @return
	 */
	public List<EntidadeDominio> consultarSomenteAtivo (EntidadeDominio entidade){
		openConnection();
		try {
			List<EntidadeDominio> produtos = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from produto where status='ativo'");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Produto
				Produto prod = new Produto();
				
				prod.setId(rs.getString("id"));
				prod.setNome(rs.getString("nome"));
				prod.setDescricao(rs.getString("descricao"));
				prod.setCategoria(rs.getString("categoria"));
				prod.setCores(rs.getString("cores"));
				prod.setTamanho(rs.getString("tamanho"));
				prod.setPrecoCompra(rs.getString("precoCompra"));
				prod.setPrecoVenda(rs.getString("precoVenda"));
				prod.setQtdeEstoque(rs.getString("qtdeEstoque"));
				prod.setFoto(rs.getString("foto"));
				prod.setData_Cadastro(rs.getString("Data_Cadastro"));
				prod.setStatus(rs.getString("status"));
				prod.setGrupoPrecificacao(rs.getString("grupoPrecificacao"));
				prod.setMotivoStatus(rs.getString("motivoStatus"));
				
				// adicionando o objeto à lista
				produtos.add(prod);
			}
			rs.close();
			stmt.close();
			return produtos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}// listar apenas os ativos
	
	

}
	