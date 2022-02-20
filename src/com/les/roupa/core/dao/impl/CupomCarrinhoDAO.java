package com.les.roupa.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.les.roupa.core.dominio.CartaoCredito;
import com.les.roupa.core.dominio.CupomCarrinho;
import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;

public class CupomCarrinhoDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para SALVAR cupom no carrinho
	 * @param entidade
	 */
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		
		String sql = "insert into cuponsNoCarrinho "+
				"(id_cupom,id_cliente,valor,status,dt_cadastro)" +
				"values (?,?,?,?,?)";
		
		try {
			CupomCarrinho cupom = (CupomCarrinho) entidade;
			
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setString(1,cupom.getIdCupom());
			stmt.setString(2,cupom.getIdCliente());
			stmt.setString(3,cupom.getValor());
			stmt.setString(4,cupom.getStatus());
			stmt.setString(5,cupom.getDt_cadastro());
			
			// executa
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Salvar
	
	
	/**
	 * Metodo para ALTERAR cupom
	 * @param entidade
	 */
	public void alterar (EntidadeDominio entidade) {
		openConnection();
		
		/*String sql = "update cupomNoCarrinho set id_cupom=?, valor=?, status=?, dt_cadastro=? where id=?";
		
		try {
			CupomCarrinho cupom = (CupomCarrinho) entidade;
			
			// quando o atributo "alteraCupom" for igual a 1, ele altera o Cupom
			//caso contrario, apenas tras as infos para a tela
			if(cupom.getAlteraCupom().contentEquals("1")) {
				PreparedStatement stmt = connection.prepareStatement(sql);
				
				stmt.setString(1,cupom.getNome());
				stmt.setString(2,cupom.getTipo());
				stmt.setString(3,cupom.getValor());
				stmt.setString(4,cupom.getIdCliente());
				stmt.setString(5,cupom.getUtilizado());
				stmt.setString(6,cupom.getDt_cadastro());
				stmt.setString(7,cupom.getId());
				
				stmt.execute();
				stmt.close();
			}else {
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}*/
	} // Alterar
	
	
	/**
	 * Metodo para EXCLUIR Cupom
	 * @param entidade
	 */
	public void excluir (EntidadeDominio entidade) {
		openConnection();
		
		try {
			CupomCarrinho cupom = (CupomCarrinho) entidade;
			
			PreparedStatement stmt = connection.prepareStatement("delete from cuponsNoCarrinho where id=?");
			
			stmt.setString(1, cupom.getId());
			
			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Excluir
	
	
	
	/**
	 * Metodo para Listar (CONSULTAR) o Cupom
	 * @param entidade
	 * @return
	 */
	
	public List<EntidadeDominio> consultar (EntidadeDominio entidade){
		openConnection();
		try {
			CupomCarrinho cupom = (CupomCarrinho) entidade;
			PreparedStatement stmt = connection.prepareStatement("select * from cuponsNoCarrinho");
			ResultSet rs = stmt.executeQuery();
			
			// Lista de Cupom criada
			List<EntidadeDominio> cupons = new ArrayList<>();
			while (rs.next()) {
				
				// criando o objeto cupom
				CupomCarrinho cup = new CupomCarrinho();
				
				cup.setId(rs.getString("id"));
				cup.setIdCupom(rs.getString("id_cupom"));
				cup.setIdCliente(rs.getString("id_cliente"));
				cup.setValor(rs.getString("valor"));
				cup.setStatus(rs.getString("status"));
				cup.setDt_cadastro(rs.getString("dt_cadastro"));
			
				
				// adicionando o objeto à lista de Cupons criado acima
				cupons.add(cup);
			}
			rs.close();
			stmt.close();
			return cupons;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}	
	} // Listar todos os cupons
	
	
	/**
	 * Metodo para Listar Cupom por ID do Cliente
	 * @param entidade
	 * @return
	 */
	public List<CupomCarrinho> consultarCupomCarrinhoByIdCliente (String idCliente){
		openConnection();
		try {
			
			List<CupomCarrinho> cupons = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from cuponsNoCarrinho where id_cliente=?");
			stmt.setString(1, idCliente);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Cupom
				
				CupomCarrinho cup = new CupomCarrinho();
				
				cup.setId(rs.getString("id"));
				cup.setIdCupom(rs.getString("id_cupom"));
				cup.setIdCliente(rs.getString("id_cliente"));
				cup.setValor(rs.getString("valor"));
				cup.setStatus(rs.getString("status"));
				cup.setDt_cadastro(rs.getString("dt_cadastro"));
								
				// adicionando o objeto à lista
				cupons.add(cup);
			}
			rs.close();
			stmt.close();
			return cupons;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar Cupom por ID do Cliente
	
	
	/**
	 * Metodo para Listar os Cupons ATIVOS
	 * @param entidade
	 * @return
	 */
	
	public List<CupomCarrinho> consultarCupomAtivo (){
		openConnection();
		try {
			
			PreparedStatement stmt = connection.prepareStatement("select * from cuponsNoCarrinho where status = 'ativo' ");
			ResultSet rs = stmt.executeQuery();
			
			// Lista de Cupom criada
			List<CupomCarrinho> cupons = new ArrayList<>();
			while (rs.next()) {
				
				// criando o objeto cupom
				CupomCarrinho cup = new CupomCarrinho();
				
				cup.setId(rs.getString("id"));
				cup.setIdCupom(rs.getString("id_cupom"));
				cup.setIdCliente(rs.getString("id_cliente"));
				cup.setValor(rs.getString("valor"));
				cup.setStatus(rs.getString("status"));
				cup.setDt_cadastro(rs.getString("dt_cadastro"));
			
				
				// adicionando o objeto à lista de Cupons criado acima
				cupons.add(cup);
			}
			rs.close();
			stmt.close();
			return cupons;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}	
	} // Listar todos os cupons ativos
	
	
	/**
	 * Metodo para Listar os Cupons ATIVOS do Cliente
	 * @param entidade
	 * @return
	 */
	
	public List<CupomCarrinho> consultarCupomAtivoByCliente (String idCliente){
		openConnection();
		try {
			
			PreparedStatement stmt = connection.prepareStatement("select * from cuponsNoCarrinho where status = 'ativo' and id_cliente=? ");
			stmt.setString(1, idCliente);
			ResultSet rs = stmt.executeQuery();
			
			// Lista de Cupom criada
			List<CupomCarrinho> cupons = new ArrayList<>();
			while (rs.next()) {
				
				// criando o objeto cupom
				CupomCarrinho cup = new CupomCarrinho();
				
				cup.setId(rs.getString("id"));
				cup.setIdCupom(rs.getString("id_cupom"));
				cup.setIdCliente(rs.getString("id_cliente"));
				cup.setValor(rs.getString("valor"));
				cup.setStatus(rs.getString("status"));
				cup.setDt_cadastro(rs.getString("dt_cadastro"));
			
				
				// adicionando o objeto à lista de Cupons criado acima
				cupons.add(cup);
			}
			rs.close();
			stmt.close();
			return cupons;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}	
	} // Listar todos os cupons ativos por ID Cliente
	
	
	
	/**
	 * Metodo para ALTERAR Status dos Cupons no Carrinho pelo ID
	 * @param entidade
	 */
	public void alterarStatusCupom (String idCupom) {
		openConnection();
		
		String sql = "update cuponsNoCarrinho set status='inativo' where id=?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, idCupom);
			
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Alterar Status dos Cupons no carrinho pelo ID
	
	
	
	
	
}
	