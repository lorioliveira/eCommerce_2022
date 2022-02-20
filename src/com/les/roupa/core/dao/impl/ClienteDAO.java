package com.les.roupa.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Usuario;

public class ClienteDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para SALVAR o Cliente
	 * @param entidade
	 */
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		
		String sql = "insert into cliente "+
				"(nome,cpf,dt_nasc,genero,telefone,email,senha,status,tipo,dt_cadastro)" +
				"values (?,?,?,?,?,?,?,?,?,?)";
		
		try {
			Cliente cliente = (Cliente) entidade;
			Usuario usuario = cliente.getUsuario();
			
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setString(1,cliente.getNome());
			stmt.setString(2,cliente.getCpf());
			stmt.setString(3,cliente.getDt_nasc());
			stmt.setString(4,cliente.getGenero());
			stmt.setString(5,cliente.getTelefone());
			stmt.setString(6,usuario.getEmail());
			stmt.setString(7,usuario.getSenha());
			stmt.setString(8,cliente.getStatus());
			stmt.setString(9,cliente.getTipo());
			stmt.setString(10,cliente.getDt_cadastro());
			
			// executa
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Salvar
	
	
	/**
	 * Metodo para ALTERAR o Cliente
	 * @param entidade
	 */
	public void alterar (EntidadeDominio entidade) {
		openConnection();
		
		String sql = "update cliente set nome=?, cpf=?, dt_nasc=?, genero=?, "
				+ "telefone=?, email=?, senha=?, status=?, dt_cadastro=? where id=?";
		
		try {
			Cliente cliente = (Cliente) entidade;
			Usuario usuario = cliente.getUsuario();
			
			// quando o atributo "alteraCliente" for igual a 1, ele altera o Cliente
			if(cliente.getAlteraCliente().contentEquals("1")) {
				PreparedStatement stmt = connection.prepareStatement(sql);
				
				stmt.setString(1,cliente.getNome());
				stmt.setString(2,cliente.getCpf());
				stmt.setString(3,cliente.getDt_nasc());
				stmt.setString(4,cliente.getGenero());
				stmt.setString(5,cliente.getTelefone());
				stmt.setString(6,usuario.getEmail());
				stmt.setString(7,usuario.getSenha());
				stmt.setString(8,cliente.getStatus());
				stmt.setString(9,cliente.getDt_cadastro());
				stmt.setString(10,cliente.getId());
				
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
	 * Metodo para EXCLUIR o Cliente
	 * @param entidade
	 */
	public void excluir (EntidadeDominio entidade) {
		openConnection();
		try {
			Cliente cliente = (Cliente) entidade;
			
			// Exclui os endereços relacionados com o cliente
			PreparedStatement stmt = connection.prepareStatement("delete from endereco where id_cliente=?");
			stmt.setString(1, cliente.getId());
			stmt.executeUpdate();
			
			// Exclui os cartões de creditos relacionados com o cliente
			stmt = connection.prepareStatement("delete from cartaoCredito where id_cliente=?");
			stmt.setString(1, cliente.getId());
			stmt.executeUpdate();
			
			// Exclui o cliente
			stmt = connection.prepareStatement("delete from cliente where id=?");
			stmt.setString(1, cliente.getId());
			stmt.executeUpdate();
			
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Excluir
	
	
	/**
	 * Metodo para Listar (CONSULTAR) o Cliente
	 * @param entidade
	 * @return
	 */
	public List<EntidadeDominio> consultar (EntidadeDominio entidade){
		openConnection();
		try {
			List<EntidadeDominio> clientes = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from cliente");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Cliente
				
				Cliente cliente = new Cliente();
				Usuario usuario = new Usuario();
				
				cliente.setId(rs.getString("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setDt_nasc(rs.getString("dt_nasc"));
				cliente.setGenero(rs.getString("genero"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setStatus(rs.getString("status"));
				cliente.setTipo(rs.getString("tipo"));

				cliente.setDt_cadastro(rs.getString("dt_cadastro"));
				
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				
				cliente.setUsuario(usuario);
				
				// adicionando o objeto à lista
				clientes.add(cliente);
			}
			rs.close();
			stmt.close();
			return clientes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar
	
	/**
	 * Metodo para Listar Cliente por ID
	 * @param entidade
	 * @return
	 */
	public List<Cliente> consultarClienteById (String idCliente){
		openConnection();
		try {
			List<Cliente> clientes = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from cliente where id=? ");
			stmt.setString(1, idCliente);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Cliente
				
				Cliente cliente = new Cliente();
				Usuario usuario = new Usuario();
				
				cliente.setId(rs.getString("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setDt_nasc(rs.getString("dt_nasc"));
				cliente.setGenero(rs.getString("genero"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setStatus(rs.getString("status"));
				cliente.setTipo(rs.getString("tipo"));
				cliente.setDt_cadastro(rs.getString("dt_cadastro"));
				
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				
				cliente.setUsuario(usuario);
				
				
				// adicionando o objeto à lista
				clientes.add(cliente);
			}
			rs.close();
			stmt.close();
			return clientes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar CLiente por ID
	
	
	/**
	 * Metodo para Listar somente Cliente - apenas CLIENTE - ADMIN
	 * @param entidade
	 * @return
	 */
	public List<EntidadeDominio> consultarClienteByTipo (EntidadeDominio entidade){
		openConnection();
		try {
			List<EntidadeDominio> clientes = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from cliente where tipo = 'cliente'");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Cliente
				Cliente cliente = new Cliente();
				Usuario usuario = new Usuario();
				
				cliente.setId(rs.getString("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setDt_nasc(rs.getString("dt_nasc"));
				cliente.setGenero(rs.getString("genero"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setStatus(rs.getString("status"));
				cliente.setTipo(rs.getString("tipo"));
				cliente.setDt_cadastro(rs.getString("dt_cadastro"));
				
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				
				cliente.setUsuario(usuario);
				
				// adicionando o objeto à lista
				clientes.add(cliente);
			}
			rs.close();
			stmt.close();
			return clientes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar Cliente - apenas CLIENTE
	
	
	/**
	 * Metodo para Listar/Verificar o status do Usuario 
	 * @param entidade
	 * @return
	 */
	public List<Cliente> consultarStatusByEmailSenha (String email, String senha){
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from cliente where email=? and senha=?");
			stmt.setString(1, email);
			stmt.setString(2, senha);
			ResultSet rs = stmt.executeQuery();
			
			List<Cliente> clientes = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Cliente
				
				Cliente cliente = new Cliente();
				Usuario usuario = new Usuario();
				
				cliente.setId(rs.getString("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setDt_nasc(rs.getString("dt_nasc"));
				cliente.setGenero(rs.getString("genero"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setStatus(rs.getString("status"));
				cliente.setTipo(rs.getString("tipo"));

				cliente.setDt_cadastro(rs.getString("dt_cadastro"));
				
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				
				cliente.setUsuario(usuario);
				
				// adicionando o objeto à lista
				clientes.add(cliente);
			}
				
			rs.close();
			stmt.close();
			return clientes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar/Verificar Usuario por status ativo/inativo
	
	
}
