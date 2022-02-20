package com.les.roupa.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Usuario;

public class EnderecoDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para SALVAR o Endereco
	 * @param entidade
	 */
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		
		String sql = "insert into endereco "+
				"(cep,logradouro,numero,bairro,cidade,estado,pais,"
				+ "tipoResidencia,observacoes,tipoEndereco,id_cliente,dt_cadastro)" +
				"values (?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			Endereco endereco = (Endereco) entidade;
			
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setString(1,endereco.getCep());
			stmt.setString(2,endereco.getLogradouro());
			stmt.setString(3,endereco.getNumero());
			stmt.setString(4,endereco.getBairro());
			stmt.setString(5,endereco.getCidade());
			stmt.setString(6,endereco.getEstado());
			stmt.setString(7,endereco.getPais());
			stmt.setString(8,endereco.getTipoResidencia());
			stmt.setString(9,endereco.getObservacoes());
			stmt.setString(10,endereco.getTipoEnd());
			stmt.setString(11,endereco.getIdCliente());
			stmt.setString(12,endereco.getDt_cadastro());
			
			// executa
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Salvar
	
	
	
	/**
	 * Metodo para ALTERAR o Endereco
	 * @param entidade
	 */
	
	public void alterar (EntidadeDominio entidade) {
		
		openConnection();
		
		String sql = "update endereco set cep=?, logradouro=?, numero=?, bairro=?,cidade=?, "
				+ "estado=?, pais=?, tipoResidencia=?, observacoes=?, tipoEndereco=?, dt_cadastro=? where id=?";
		
		try {
			Endereco endereco = (Endereco) entidade;
			
			
			// quando o atributo "alteraEndereco" for igual a 1, ele altera o endereco
			//caso contrario, apenas tras as infos para a tela
			if(endereco.getAlteraEndereco().contentEquals("1")) {
				PreparedStatement stmt = connection.prepareStatement(sql);
				
				stmt.setString(1,endereco.getCep());
				stmt.setString(2,endereco.getLogradouro());
				stmt.setString(3,endereco.getNumero());
				stmt.setString(4,endereco.getBairro());
				stmt.setString(5,endereco.getCidade());
				stmt.setString(6,endereco.getEstado());
				stmt.setString(7,endereco.getPais());
				stmt.setString(8,endereco.getTipoResidencia());
				stmt.setString(9,endereco.getObservacoes());
				stmt.setString(10,endereco.getTipoEnd());
				stmt.setString(11,endereco.getDt_cadastro());
				stmt.setString(12,endereco.getId());
				
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
	 * Metodo para EXCLUIR o endereco
	 * @param entidade
	 */
	public void excluir (EntidadeDominio entidade) {
		openConnection();
		
		try {
			Endereco endereco = (Endereco) entidade;
			
			PreparedStatement stmt = connection.prepareStatement("delete from endereco where id=?");
			
			stmt.setString(1, endereco.getId());
			
			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Excluir
	
	
	/**
	 * Metodo para Listar (CONSULTAR) o Endereco
	 * @param entidade
	 * @return
	 */
	public List<EntidadeDominio> consultar (EntidadeDominio entidade){
		openConnection();
		try {
			Endereco endereco = (Endereco) entidade;
			PreparedStatement stmt = connection.prepareStatement("select * from endereco");
			ResultSet rs = stmt.executeQuery();
			
			// Lista de Enderecos criada
			List<EntidadeDominio> enderecos = new ArrayList<>();
			while (rs.next()) {
				
				// criando o objeto endereco
				Endereco end = new Endereco();
				
				end.setCep(rs.getString("cep"));
				end.setLogradouro(rs.getString("logradouro"));
				end.setNumero(rs.getString("numero"));
				end.setBairro(rs.getString("bairro"));
				end.setCidade(rs.getString("cidade"));
				end.setEstado(rs.getString("estado"));
				end.setPais(rs.getString("pais"));
				end.setTipoResidencia(rs.getString("tipoResidencia"));
				end.setObservacoes(rs.getString("observacoes"));
				end.setId(rs.getString("id"));
				end.setTipoEnd(rs.getString("tipoEndereco"));
				end.setDt_cadastro(rs.getString("dt_cadastro"));
				
				
				// adicionando o objeto à lista de Enderecos criado acima
				enderecos.add(end);
			}
			rs.close();
			stmt.close();
			return enderecos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar
	
	/**
	 * Metodo para Listar Endereco por ID
	 * @param entidade
	 * @return
	 */
	public List<Endereco> consultarEnderecoById (String idEndereco){
		openConnection();
		try {
			List<Endereco> enderecos = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from endereco where id=?");
			stmt.setString(1, idEndereco);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto endereco
				Endereco end = new Endereco();
				
				end.setId(rs.getString("id"));
				end.setCep(rs.getString("cep"));
				end.setLogradouro(rs.getString("logradouro"));
				end.setNumero(rs.getString("numero"));
				end.setBairro(rs.getString("bairro"));
				end.setCidade(rs.getString("cidade"));
				end.setEstado(rs.getString("estado"));
				end.setPais(rs.getString("pais"));
				end.setTipoResidencia(rs.getString("tipoResidencia"));
				end.setObservacoes(rs.getString("observacoes"));
				end.setTipoEnd(rs.getString("tipoEndereco"));
				end.setDt_cadastro(rs.getString("dt_cadastro"));

				
				// adicionando o objeto à lista de Endereco criado acima
				enderecos.add(end);
			}
			rs.close();
			stmt.close();
			return enderecos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar

	/**
	 * Metodo para Listar Endereco por ID do Cliente
	 * @param entidade
	 * @return
	 */
	public List<Endereco> consultarEnderecoByIdCliente (String idCliente){
		openConnection();
		try {
			List<Endereco> enderecos = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from endereco where id_cliente = ?");
			stmt.setString(1, idCliente);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto endereco
				Endereco end = new Endereco();
				
				end.setId(rs.getString("id"));
				end.setCep(rs.getString("cep"));
				end.setLogradouro(rs.getString("logradouro"));
				end.setNumero(rs.getString("numero"));
				end.setBairro(rs.getString("bairro"));
				end.setCidade(rs.getString("cidade"));
				end.setEstado(rs.getString("estado"));
				end.setPais(rs.getString("pais"));
				end.setTipoResidencia(rs.getString("tipoResidencia"));
				end.setObservacoes(rs.getString("observacoes"));
				end.setTipoEnd(rs.getString("tipoEndereco"));
				end.setIdCliente(rs.getString("id_cliente"));
				end.setDt_cadastro(rs.getString("dt_cadastro"));

				
				// adicionando o objeto à lista de Endereco criado acima
				enderecos.add(end);
			}
			rs.close();
			stmt.close();
			return enderecos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar
	
}
