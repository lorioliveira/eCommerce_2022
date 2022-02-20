package com.les.roupa.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.les.roupa.core.dominio.CartaoCredito;
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Usuario;


public class CartaoCreditoDAO extends AbstractJdbcDAO{
	
	/** Metodo para SALVAR cartao */
	
	public void salvar (EntidadeDominio entidade) {
		openConnection();
		
		String sql = "insert into cartaoCredito (nCartao, bandeira, nome, "
				+ "validade, cvv, preferencial, id_cliente, dt_cadastro) values (?,?,?,?,?,?,?,?)";
		
		try {
			CartaoCredito cartaocredito = (CartaoCredito) entidade;
			
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setString(1,cartaocredito.getNCartao());
			stmt.setString(2,cartaocredito.getBandeira());
			stmt.setString(3,cartaocredito.getNome());
			stmt.setString(4,cartaocredito.getValidade());
			stmt.setString(5,cartaocredito.getCvv());
			stmt.setString(6,cartaocredito.getPreferencial());
			stmt.setString(7,cartaocredito.getIdCliente());
			stmt.setString(8,cartaocredito.getDt_cadastro());
			
			// executa
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Salvar
	
	/**
	 * Metodo para ALTERAR o cartão de credito
	 * throws SQLException
	 */
	@Override
	public void alterar(EntidadeDominio entidade) {
			openConnection();
		
		String sql = "update cartaoCredito set nCartao=?, bandeira=?, nome=?, "
				+ "validade=?, cvv=?, preferencial=?, dt_cadastro=? where id=?" ;
		
		try {
			CartaoCredito cartaocredito = (CartaoCredito) entidade;
			if(cartaocredito.getAlteraPref().contentEquals("1")) {
			
				// prepared statement para inserção
				PreparedStatement stmt = connection.prepareStatement(sql);
				
				// seta os valores
	
				stmt.setString(1,cartaocredito.getNCartao());
				stmt.setString(2,cartaocredito.getBandeira());
				stmt.setString(3,cartaocredito.getNome());
				stmt.setString(4,cartaocredito.getValidade());
				stmt.setString(5,cartaocredito.getCvv());
				stmt.setString(6,cartaocredito.getPreferencial());
			
				stmt.setString(7,cartaocredito.getDt_cadastro());
				stmt.setString(8,cartaocredito.getId());
				
				// executa
				stmt.execute();
				stmt.close();
			}else {
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}//Alterar
	
	
	/**
	 * Metodo para EXCLUIR o cartão de credito
	 * @param entidade
	 */
	public void excluir (EntidadeDominio entidade) {
		openConnection();
		
		try {
			CartaoCredito cartaocredito = (CartaoCredito) entidade;
			
			PreparedStatement stmt = connection.prepareStatement("delete from cartaoCredito where id=?");
			
			stmt.setString(1, cartaocredito.getId());
			
			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Excluir
	
	/**
	 * Metodo para Listar (CONSULTAR) o cartao
	 * @param entidade
	 * @return
	 */
	public List<EntidadeDominio> consultar (EntidadeDominio entidade){
		openConnection();
		try {
			//CartaoCredito cartaocredito = (CartaoCredito) entidade;
			PreparedStatement stmt = connection.prepareStatement("select * from cartaoCredito");
			ResultSet rs = stmt.executeQuery();
			
			// Lista de cartao criada
			List<EntidadeDominio> cartaoCredito = new ArrayList<>();
			while (rs.next()) {
				
				// criando o objeto cartao de credito
				CartaoCredito cCredito = new CartaoCredito();
				
				cCredito.setNCartao(rs.getString("nCartao"));
				cCredito.setBandeira(rs.getString("bandeira"));
				cCredito.setNome(rs.getString("nome"));
				cCredito.setValidade(rs.getString("validade"));
				cCredito.setCvv(rs.getString("cvv"));
				cCredito.setId(rs.getString("id"));
				cCredito.setPreferencial(rs.getString("preferencial"));
				cCredito.setDt_cadastro(rs.getString("dt_cadastro"));
				
				
				// adicionando o objeto à lista de Enderecos criado acima
				cartaoCredito.add(cCredito);
			}
			rs.close();
			stmt.close();
			return cartaoCredito;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar/Consultar


	/**
	 * Metodo para Listar Cartao de Credito por ID
	 * @param entidade
	 * @return
	 */
	public List<CartaoCredito> consultarCartCreditoById (String idCartao){
		openConnection();
		try {
			
			List<CartaoCredito> cartoes = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from cartaoCredito where id=?");
			stmt.setString(1, idCartao);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto cartao de credito
				
				CartaoCredito cartaocredito = new CartaoCredito();
				
				cartaocredito.setId(rs.getString("id"));
				cartaocredito.setNCartao(rs.getString("nCartao"));
				cartaocredito.setBandeira(rs.getString("bandeira"));
				cartaocredito.setNome(rs.getString("nome"));
				cartaocredito.setValidade(rs.getString("validade"));
				cartaocredito.setCvv(rs.getString("cvv"));
				cartaocredito.setPreferencial(rs.getString("preferencial"));
				cartaocredito.setDt_cadastro(rs.getString("dt_cadastro"));
				
				
				// adicionando o objeto à lista
				cartoes.add(cartaocredito);
			}
			rs.close();
			stmt.close();
			return cartoes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar
	

	/**
	 * Metodo para Listar Cartao de Credito por ID do Cliente
	 * @param entidade
	 * @return
	 */
	public List<CartaoCredito> consultarCartCreditoByIdCliente (String idCliente){
		openConnection();
		try {
			
			List<CartaoCredito> cartoes = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from cartaoCredito where id_cliente = ?");
			stmt.setString(1, idCliente);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto cartao de credito
				
				CartaoCredito cartaocredito = new CartaoCredito();
				
				cartaocredito.setId(rs.getString("id"));
				cartaocredito.setNCartao(rs.getString("nCartao"));
				cartaocredito.setBandeira(rs.getString("bandeira"));
				cartaocredito.setNome(rs.getString("nome"));
				cartaocredito.setValidade(rs.getString("validade"));
				cartaocredito.setCvv(rs.getString("cvv"));
				cartaocredito.setPreferencial(rs.getString("preferencial"));
				cartaocredito.setIdCliente(rs.getString("id_cliente"));
				cartaocredito.setDt_cadastro(rs.getString("dt_cadastro"));
				
				
				// adicionando o objeto à lista
				cartoes.add(cartaocredito);
			}
			rs.close();
			stmt.close();
			return cartoes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar Cartao - Carrinho
	
	


}
