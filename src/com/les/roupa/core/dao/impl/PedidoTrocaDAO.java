package com.les.roupa.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.les.roupa.core.dominio.Cupom;
import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Estoque;
import com.les.roupa.core.dominio.ItemPedido;
import com.les.roupa.core.dominio.Pedido;
import com.les.roupa.core.dominio.PedidoTroca;
import com.les.roupa.core.dominio.Produto;

public class PedidoTrocaDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para salvar a Troca do Pedido ou dos Itens do Pedido
	 * @param entidade
	 */
	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		
	} // Salvar
	
	
	/**
	 * Metodo para alterar a Troca do Pedido ou dos Itens do Pedido
	 * @param entidade
	 */
	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		
		
		
	} // Alterar
	
	
	/**
	 * Metodo para excluir a Troca do Pedido ou dos Itens do Pedido
	 * @param entidade
	 */
	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Excluir
	
	
	/**
	 * Metodo para Consultar a Troca do Pedido ou dos Itens do Pedido
	 * @param entidade
	 */
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		return null;
		
	} // Consultar
	
}