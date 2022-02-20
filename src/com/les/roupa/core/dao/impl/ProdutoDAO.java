package com.les.roupa.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Produto;

public class ProdutoDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para SALVAR 
	 */
	public void salvar(EntidadeDominio entidade) {
openConnection();
		
		String sql = "insert into produto (nome, descricao, categoria, cores, tamanho, precoCompra, "
				+ "precoVenda, qtdeEstoque, foto, dt_cadastro, status, grupoPrecificacao, descricaoStatusProduto) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			Produto produto = (Produto) entidade;
			
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
				stmt.setString(10,produto.getDt_cadastro());
				stmt.setString(11,produto.getStatus());
				stmt.setString(12,produto.getGrupoPrecificacao());
				stmt.setString(13, produto.getStatusProduto());
				
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
		
		String sql = "update produto set nome=?, descricao=?, categoria=?, cores=?, tamanho=?, precoCompra=?, "
				+ "precoVenda=?, qtdeEstoque=?, foto=?, dt_cadastro=?, status=?, grupoPrecificacao=?, descricaoStatusProduto=? where id=?";
		
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
				stmt.setString(10,produto.getDt_cadastro());
				stmt.setString(11,produto.getStatus());
				stmt.setString(12,produto.getGrupoPrecificacao());
				stmt.setString(13, produto.getStatusProduto());
				stmt.setString(14,produto.getId());
				
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
		
	} // Excluir
	
	
	/**
	 * Metodo para Listar Produto 
	 * @param entidade
	 * @return
	 */
	public List<EntidadeDominio> consultar (EntidadeDominio entidade){
		openConnection();
		try {
			List<EntidadeDominio> produtos = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from produto");
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
				prod.setDt_cadastro(rs.getString("dt_cadastro"));
				prod.setStatus(rs.getString("status"));
				prod.setGrupoPrecificacao(rs.getString("grupoPrecificacao"));
				prod.setStatusProduto(rs.getString("descricaoStatusProduto"));
				
								
				// adicionando o objeto � lista
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
				prod.setDt_cadastro(rs.getString("dt_cadastro"));
				prod.setStatus(rs.getString("status"));
				prod.setGrupoPrecificacao(rs.getString("grupoPrecificacao"));
				prod.setStatusProduto(rs.getString("descricaoStatusProduto"));
							
				
				// adicionando o objeto � lista
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
				prod.setDt_cadastro(rs.getString("dt_cadastro"));
				prod.setStatus(rs.getString("status"));
				prod.setGrupoPrecificacao(rs.getString("grupoPrecificacao"));
				prod.setStatusProduto(rs.getString("descricaoStatusProduto"));
				
				// adicionando o objeto � lista
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
	