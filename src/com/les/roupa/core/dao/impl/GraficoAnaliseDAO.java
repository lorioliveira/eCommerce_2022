package com.les.roupa.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.GraficoAnalise;
import com.les.roupa.core.dominio.ItemPedido;
import com.les.roupa.core.dominio.Produto;

public class GraficoAnaliseDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para salvar o Gráfico da Análise
	 * @param entidade
	 */
	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Salvar
	
	
	/**
	 * Metodo para alterar o Gráfico da Análise
	 * @param entidade
	 */
	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Alterar
	
	
	/**
	 * Metodo para excluir o Gráfico da Análise
	 * @param entidade
	 */
	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Excluir
	
	
	/**
	 * Metodo para Consultar o Gráfico da Análise
	 * @param entidade
	 */
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	} // Consultar
	
	
	/**
	 * Metodo para consultar os 3 Produtos mais vendidos com filtros de datas
	 */
	public List<GraficoAnalise> consultar3ProdutosMaisVendidos (String dtInicio, String dtFim) {
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select id_produto, sum(qtdeProduto) as quantidade_vendido from item_pedido where dt_pedido BETWEEN (?) AND (?) group by id_produto order by sum(qtdeProduto) desc LIMIT 3;");
			stmt.setString(1, dtInicio);
			stmt.setString(2, dtFim);
			ResultSet rs = stmt.executeQuery();
			
			List<GraficoAnalise> itens_grafico = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Grafico Analise
				GraficoAnalise item_grafico = new GraficoAnalise();
				
				ItemPedido itemPedido = new ItemPedido();
				Produto produto = new Produto();
				itemPedido.setProduto(produto);
				
				itemPedido.getProduto().setId(rs.getString("id_produto"));
				itemPedido.setQtdeProduto(rs.getString("quantidade_vendido"));
				item_grafico.setItemPedido(itemPedido);
				
				// adicionando o objeto à lista
				itens_grafico.add(item_grafico);
			}
				
			rs.close();
			stmt.close();
			return itens_grafico;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
				
	} // consultar os 3 Produtos mais vendidos com filtros de datas
	
	
	/**
	 * Metodo para consultar a quantidade de Produtos vendidos por ID Produto, Mes e Ano
	 */
	public List<GraficoAnalise> consultarQtdeProdutosVendidosByIdProdutoMesAno (String idProduto, String mes, String ano) {
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT id_produto, sum(qtdeProduto) as quantidade_vendido  FROM item_pedido WHERE id_produto = ? AND MONTH(dt_pedido) = ? AND YEAR(dt_pedido) = ? group by id_produto;");
			stmt.setString(1, idProduto);
			stmt.setString(2, mes);
			stmt.setString(3, ano);
			ResultSet rs = stmt.executeQuery();
			
			List<GraficoAnalise> itens_grafico = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Grafico Analise
				GraficoAnalise item_grafico = new GraficoAnalise();
				ItemPedido itemPedido = new ItemPedido();
				Produto produto = new Produto();
				itemPedido.setProduto(produto);
				
				itemPedido.getProduto().setId(rs.getString("id_produto"));
				itemPedido.setQtdeProduto(rs.getString("quantidade_vendido"));
				item_grafico.setItemPedido(itemPedido);
				
				// adicionando o objeto à lista
				itens_grafico.add(item_grafico);
			}
				
			rs.close();
			stmt.close();
			return itens_grafico;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
				
	} // consultar a quantidade de Produtos vendidos por ID Produto, Mes e Ano
	
}
