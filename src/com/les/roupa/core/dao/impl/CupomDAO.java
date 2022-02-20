package com.les.roupa.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.les.roupa.core.dominio.CartaoCredito;
import com.les.roupa.core.dominio.Cupom;
import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;

public class CupomDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para SALVAR cupom
	 * @param entidade
	 */
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		
		String sql = "insert into cupom "+
				"(nome,tipo,valor,id_cliente,utilizado,dt_cadastro)" +
				"values (?,?,?,?,?,?)";
		
		try {
			Cupom cupom = (Cupom) entidade;
			
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setString(1,cupom.getNome());
			stmt.setString(2,cupom.getTipo());
			stmt.setString(3,cupom.getValor());
			stmt.setString(4,cupom.getIdCliente());
			stmt.setString(5,cupom.getUtilizado());
			stmt.setString(6,cupom.getDt_cadastro());
			
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
		
		String sql = "update cupom set nome=?, tipo=?, valor=?, id_cliente=?, utilizado=?, dt_cadastro=? where id=?";
		
		try {
			Cupom cupom = (Cupom) entidade;
			
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
		}
	} // Alterar
	
	/**
	 * Metodo para EXCLUIR Cupom
	 * @param entidade
	 */
	public void excluir (EntidadeDominio entidade) {
		openConnection();
		
		try {
			Cupom cupom = (Cupom) entidade;
			
			PreparedStatement stmt = connection.prepareStatement("delete from cupom where id=?");
			
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
			Cupom cupom = (Cupom) entidade;
			PreparedStatement stmt = connection.prepareStatement("select * from cupom");
			ResultSet rs = stmt.executeQuery();
			
			// Lista de Cupom criada
			List<EntidadeDominio> cupons = new ArrayList<>();
			while (rs.next()) {
				
				// criando o objeto endereco
				Cupom cup = new Cupom();
				
				cup.setId(rs.getString("id"));
				cup.setNome(rs.getString("nome"));
				cup.setTipo(rs.getString("tipo"));
				cup.setValor(rs.getString("valor"));
				cup.setIdCliente(rs.getString("id_cliente"));
				cup.setUtilizado(rs.getString("utilizado"));
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
	 * Metodo para Listar Cupom por ID
	 * @param entidade
	 * @return
	 */
	public List<Cupom> consultarCupomById (String idCupom){
		openConnection();
		try {
			
			List<Cupom> cupons = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from cupom where id=?");
			stmt.setString(1, idCupom);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Cupom
				
				Cupom cup = new Cupom();
				
				cup.setId(rs.getString("id"));
				cup.setNome(rs.getString("nome"));
				cup.setTipo(rs.getString("tipo"));
				cup.setValor(rs.getString("valor"));
				cup.setIdCliente(rs.getString("id_cliente"));
				cup.setUtilizado(rs.getString("utilizado"));
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
	} // Listar Cupom por ID
	
	
	/**
	 * Metodo para Listar Cupom por ID do Cliente
	 * @param entidade
	 * @return
	 */
	public List<Cupom> consultarCupomByIdCliente (String idCliente){
		openConnection();
		try {
			
			List<Cupom> cupons = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from cupom where id_cliente=?");
			stmt.setString(1, idCliente);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Cupom
				
				Cupom cup = new Cupom();
				
				cup.setId(rs.getString("id"));
				cup.setNome(rs.getString("nome"));
				cup.setTipo(rs.getString("tipo"));
				cup.setValor(rs.getString("valor"));
				cup.setIdCliente(rs.getString("id_cliente"));
				cup.setUtilizado(rs.getString("utilizado"));
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
}
	