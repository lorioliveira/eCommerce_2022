package com.les.roupa.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Usuario;

public class LoginDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para SALVAR 
	 * @param entidade
	 */
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		
		String sql = "insert into cliente "+
				"(nome,cpf,data_Nascimento,genero,telefone,email,senha,status,tipoCliente, data_Cadastro)" +
				"values (?,?,?,?,?,?,?,?,?,?)";
		
		try {
			Cliente cliente = (Cliente) entidade;
			Usuario usuario = cliente.getUsuario();
			
			// prepared statement para inser��o
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setString(1,cliente.getNome());
			stmt.setString(2,cliente.getCpf());
			stmt.setString(3,cliente.getData_Nascimento());
			stmt.setString(4,cliente.getGenero());
			stmt.setString(5,cliente.getTelefone());
			stmt.setString(6,usuario.getEmail());
			stmt.setString(7,usuario.getSenha());
			stmt.setString(8,cliente.getStatus());
			stmt.setString(9,cliente.getTipoCliente());
			stmt.setString(10,cliente.getData_Cadastro());
			
			// executa
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
		
		String sql = "update cliente set nome=?, cpf=?, data_Nascimento=?, genero=?, "
				+ "telefone=?, email=?, senha=?, status=? where id=?";
		
		try {
			Cliente cliente = (Cliente) entidade;
			Usuario usuario = cliente.getUsuario();
			
			// quando o atributo "alteraCliente" for igual a 1, ele altera o Cliente
			if(cliente.getAlteraCliente().contentEquals("1")) {
				PreparedStatement stmt = connection.prepareStatement(sql);
				
				stmt.setString(1,cliente.getNome());
				stmt.setString(2,cliente.getCpf());
				stmt.setString(3,cliente.getData_Nascimento());
				stmt.setString(4,cliente.getGenero());
				stmt.setString(5,cliente.getTelefone());
				stmt.setString(6,usuario.getEmail());
				stmt.setString(7,usuario.getSenha());
				stmt.setString(8,cliente.getStatus());
				stmt.setString(9,cliente.getId());
				
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
	 * @param entidade
	 */
	public void excluir (EntidadeDominio entidade) {
		
	} // Excluir
	
	
	/**
	 * Metodo para Listar/Verificar o Usuario por EMAIL e SENHA
	 * @param entidade
	 * @return
	 */
	public List<Usuario> consultarUsuarioEmailSenha (String email, String senha){
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from cliente where email=? and senha=?");
			stmt.setString(1, email);
			stmt.setString(2, senha);
			ResultSet rs = stmt.executeQuery();
			
			List<Usuario> usuarios = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Usuario
				Usuario usuario = new Usuario();
				
				usuario.setId(rs.getString("id"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));;
				usuario.setTipoCliente(rs.getString("tipoCliente"));
				usuario.setNome(rs.getString("nome"));
				
				// adicionando o objeto na lista
				usuarios.add(usuario);
			}
				
			rs.close();
			stmt.close();
			return usuarios;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar/Verificar Usuario por LOGIN e SENHA
	
	
	
	
	/**
	 * Metodo para consultar o Usuario
	 * funcaoo criada para consultar o Login ao s acessar o sistema,
	 * quando a Fachada realizar a funcaoo de consulta, esta funcao de Consultar sera chamada para
	 * poder fazer o setEntidades do Resultado, com isso feito, sera salvo esse Login em sessao arquivo LoginHelper.
	 * @param entidade
	 */
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		try {
			Usuario usuario = (Usuario) entidade;
			PreparedStatement stmt = connection.prepareStatement("select * from cliente where email=? and senha=?");
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getSenha());
			ResultSet rs = stmt.executeQuery();
			
			List<EntidadeDominio> usuarios = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Usuario
				Usuario usuarioItem = new Usuario();
				
				usuarioItem.setId(rs.getString("id"));
				usuarioItem.setNome(rs.getString("nome"));
				usuarioItem.setEmail(rs.getString("email"));
				usuarioItem.setSenha(rs.getString("senha"));
				usuarioItem.setTipoCliente(rs.getString("tipoCliente"));
				
				// adicionando o objeto a lista
				usuarios.add(usuarioItem);
			}
				
			rs.close();
			stmt.close();
			return usuarios;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
				
	} // Consultar
	
	
	/**
	 * Metodo para Listar Cliente por ID
	 * @param entidade
	 * @return
	 */
	public List<Cliente> consultarClienteById (String idCliente){
		openConnection();
		try {
			List<Cliente> clientes = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from cliente where id=?");
			stmt.setString(1, idCliente);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Cliente
				
				Cliente cliente = new Cliente();
				Usuario usuario = new Usuario();
				
				cliente.setId(rs.getString("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setData_Nascimento(rs.getString("data_Nascimento"));
				cliente.setGenero(rs.getString("genero"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setStatus(rs.getString("status"));
				
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				
				cliente.setUsuario(usuario);
				
				
				// adicionando o objeto � lista
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
	 * Metodo para Listar/Verificar o Usuario por EMAIL
	 * @param entidade
	 * @return
	 */
	public List<Usuario> consultarUsuarioByEmail (String email){
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from cliente where email=?");
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			
			List<Usuario> usuarios = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Usuario
				Usuario usuarioItem = new Usuario();
				
				usuarioItem.setId(rs.getString("id"));
				usuarioItem.setEmail(rs.getString("email"));
				usuarioItem.setSenha(rs.getString("senha"));
				usuarioItem.setNome(rs.getString("nome"));
				usuarioItem.setTipoCliente(rs.getString("tipoCliente"));
				
				// adicionando o objeto � lista
				usuarios.add(usuarioItem);
			}
				
			rs.close();
			stmt.close();
			return usuarios;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar/Verificar Usuario por EMAIL
	
	
	
	
}
	