package com.les.roupa.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Produto;

public class DetalheProdutoDAO extends AbstractJdbcDAO {
	
	
	
	/**
	 * Metodo para SALVAR 
	 */
	public void salvar(EntidadeDominio entidade) {
		
	} // Salvar
	
	/**
	 * Metodo para ALTERAR
	 * @param entidade
	 */
	public void alterar (EntidadeDominio entidade) {
	
	} // Alterar
	
	/**
	 * Metodo para EXCLUIR
	 */
	public void excluir (EntidadeDominio entidade) {
		
	} // Excluir
	
	
	/**
	 * Metodo para Listar Produto por categoria Blusa
	 * @param entidade
	 * @return
	 */
	public List<EntidadeDominio> consultarProdutoByCategoriaBlusa (EntidadeDominio entidade){
		openConnection();
		try {
			List<EntidadeDominio> produtos = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from produto where categoria='blusa' and status ='ativo'");
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
			
				
				// adicionando o objeto � lista
				produtos.add(prod);
			}
			rs.close();
			stmt.close();
			return produtos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Produto por categoria BLUSA
		
	
 
	 /** Metodo para Listar Produto por categoria Calca
	 * @param entidade
	 * @return
	 */
	public List<EntidadeDominio> consultarProdutoByCategoriaCalca (EntidadeDominio entidade){
		openConnection();
		try {
			List<EntidadeDominio> produtos = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from produto where categoria='calca' and status ='ativo'");
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
			
				
				// adicionando o objeto � lista
				produtos.add(prod);
			}
			rs.close();
			stmt.close();
			return produtos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Produto por categoria CALCA
	
	
	 /** Metodo para Listar Produto por categoria Vestido
		 * @param entidade
		 * @return
		 */
		public List<EntidadeDominio> consultarProdutoByCategoriaVestido (EntidadeDominio entidade){
			openConnection();
			try {
				List<EntidadeDominio> produtos = new ArrayList<>();
				PreparedStatement stmt = connection.prepareStatement("select * from produto where categoria='vestido' and status ='ativo'");
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
				
					
					// adicionando o objeto � lista
					produtos.add(prod);
				}
				rs.close();
				stmt.close();
				return produtos;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		} // Produto por categoria VESTIDO
		
	
	
	
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
	public List<Produto> consultarProdutoById (String id){
		openConnection();
		try {
			List<Produto> produto = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from produto where id=? ");
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Produto
				
				Produto prod = new Produto();
				
				prod.setId(rs.getString("id"));
				prod.setNome(rs.getString("nome"));
				prod.setDescricao(rs.getString("descricao"));
				//prod.setCategoria(rs.getString("categoria"));
				//prod.setCores(rs.getString("cores"));
				//prod.setTamanho(rs.getString("tamanho"));
				//prod.setPrecoCompra(rs.getString("precoCompra"));
				prod.setPrecoVenda(rs.getString("precoVenda"));
				//prod.setQtde(rs.getString("qtde"));
				prod.setFoto(rs.getString("foto"));
				//prod.setDtCadastro(rs.getString("dtCadastro"));
				//prod.setStatus(rs.getString("status"));
				//prod.setGrupoPrecificacao(rs.getString("grupoPrecificacao"));
				
				
				
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
	
	
}
	