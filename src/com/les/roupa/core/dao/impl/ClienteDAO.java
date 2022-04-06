package com.les.roupa.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Usuario;
import com.mysql.cj.exceptions.RSAException;

/**
 * DAO para CLIENTE
 * @author Lorena Oliveira 
 * 
 */
public class ClienteDAO extends AbstractJdbcDAO {
	
	/**
	 * Método para SALVAR o Cliente
	 * @param entidade
	 */
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		
		String sql = "insert into cliente "+
				"(nome,cpf,data_Nascimento,genero,telefone,email,senha,"
				+ "status,tipoCliente,data_Cadastro) values (?,?,?,?,?,?,?,?,?,?)";
		
		try {
			Cliente cliente = (Cliente) entidade;
			Usuario usuario = cliente.getUsuario();
			
			// para inserir o cliente no banco
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores corretamente
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
			
			// executa e fecha conexao
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // fim Salvar Cliente
	
	
	/**
	 * Metodo para ALTERAR os Dados do Cliente
	 * @param entidade
	 */
	public void alterar (EntidadeDominio entidade) {
		openConnection();
		
		String sql = "update cliente set nome=?, cpf=?, data_Nascimento=?, genero=?, "
				+ "telefone=?, email=?, senha=?, status=?, data_Cadastro=? where id=?";
		
		try {
			Cliente cliente = (Cliente) entidade;
			Usuario usuario = cliente.getUsuario();
			
			// quando o atributo "alteraCliente" for igual a 1, ele permite alterar o Cliente
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
				stmt.setString(9,cliente.getData_Cadastro());
				stmt.setString(10,cliente.getId());
				
				stmt.execute();
				
				//Listar todos os Clientes cadastrados - Opção como ADMIN
				List<Cliente> todosClientes = new ArrayList<>();
				stmt = connection.prepareStatement("select * from cliente where tipoCliente = 'cliente'");
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					
					// criando o objeto Cliente, onde tambem possui dados do Usuario
					Cliente clienteAltera = new Cliente();
					Usuario usuarioCliente = new Usuario();
					
					clienteAltera.setId(rs.getString("id"));
					clienteAltera.setNome(rs.getString("nome"));
					clienteAltera.setCpf(rs.getString("cpf"));
					clienteAltera.setData_Nascimento(rs.getString("data_Nascimento"));
					clienteAltera.setGenero(rs.getString("genero"));
					clienteAltera.setTelefone(rs.getString("telefone"));
					clienteAltera.setStatus(rs.getString("status"));
					clienteAltera.setTipoCliente(rs.getString("tipoCliente"));

					clienteAltera.setData_Cadastro(rs.getString("data_Cadastro"));
					
					usuarioCliente.setEmail(rs.getString("email"));
					usuarioCliente.setSenha(rs.getString("senha"));
					
					clienteAltera.setUsuario(usuarioCliente);
					
					// adicionando o objeto a lista
					todosClientes.add(clienteAltera);
				}
				//lista de todos os clientes
				cliente.getUsuario().setTodosClientes(todosClientes);
				
				rs.close();
				stmt.close();
				
			}else {
				
				//Listar todos os dados de um Cliente para alterar - Opção como ADMIN
				List<Cliente> todosClientes = new ArrayList<>();
				PreparedStatement stmt = connection.prepareStatement("select * from cliente where id = ?");
				stmt.setString(1, cliente.getId());
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					
					// criando o objeto Cliente, onde tambem possui dados do Usuario
					Cliente clienteAltera = new Cliente();
					Usuario usuarioCliente = new Usuario();
					
					clienteAltera.setId(rs.getString("id"));
					clienteAltera.setNome(rs.getString("nome"));
					clienteAltera.setCpf(rs.getString("cpf"));
					clienteAltera.setData_Nascimento(rs.getString("data_Nascimento"));
					clienteAltera.setGenero(rs.getString("genero"));
					clienteAltera.setTelefone(rs.getString("telefone"));
					clienteAltera.setStatus(rs.getString("status"));
					clienteAltera.setTipoCliente(rs.getString("tipoCliente"));

					clienteAltera.setData_Cadastro(rs.getString("data_Cadastro"));
					
					usuarioCliente.setEmail(rs.getString("email"));
					usuarioCliente.setSenha(rs.getString("senha"));
					
					clienteAltera.setUsuario(usuarioCliente);
					
					// adicionando o objeto a lista
					todosClientes.add(clienteAltera);
				}
				// lista atualizada
				cliente.getUsuario().setTodosClientes(todosClientes);
				
				rs.close();
				stmt.close();
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // fim Alterar Cliente
	
	
	/**
	 * Metodo para EXCLUIR o Cliente
	 * @param entidade
	 */
	public void excluir (EntidadeDominio entidade) {
		openConnection();
		try {
			Cliente cliente = (Cliente) entidade;
			
			// Exclui os enderecos relacionados ao cliente
			PreparedStatement stmt = connection.prepareStatement("delete from endereco where id_cliente=?");
			stmt.setString(1, cliente.getId());
			stmt.executeUpdate();
			
			// Exclui os cartoes de credito relacionados ao cliente
			stmt = connection.prepareStatement("delete from cartaoCredito where id_cliente=?");
			stmt.setString(1, cliente.getId());
			stmt.executeUpdate();
			
			// Exclui o cadastro do cliente
			stmt = connection.prepareStatement("delete from cliente where id=?");
			stmt.setString(1, cliente.getId());
			stmt.executeUpdate();
			
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // fim Excluir Cliente
	
	
	/**
	 * Metodo para CONSULTAR (LISTAR) o Cliente
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
				
				// criando o objeto Cliente, onde tambem possui dados do Usuario
				Cliente cliente = new Cliente();
				Usuario usuario = new Usuario();
				
				cliente.setId(rs.getString("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setData_Nascimento(rs.getString("data_Nascimento"));
				cliente.setGenero(rs.getString("genero"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setStatus(rs.getString("status"));
				cliente.setTipoCliente(rs.getString("tipoCliente"));

				cliente.setData_Cadastro(rs.getString("data_Cadastro"));
				
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				
				cliente.setUsuario(usuario);
				
				// adicionando o objeto a lista
				clientes.add(cliente);
			}
			rs.close();
			stmt.close();
			return clientes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // fim Listar Cliente
	
	/**
	 * Metodo para Listar Cliente por ID
	 * @param entidade
	 * @return
	 */
	public List<Cliente> consultarClienteById (String idCliente){
		openConnection();
		try {
			List<Cliente> client = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from cliente where id = ?");
			stmt.setString(1, idCliente);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				// criando o objeto Cliente, onde tambem possui dados do Usuario
				Cliente cliente = new Cliente();
				Usuario usuario = new Usuario();
				
				cliente.setId(rs.getString("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setData_Nascimento(rs.getString("data_Nascimento"));
				cliente.setGenero(rs.getString("genero"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setStatus(rs.getString("status"));
				cliente.setTipoCliente(rs.getString("tipoCliente"));
				cliente.setData_Cadastro(rs.getString("data_Cadastro"));
				
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				
				cliente.setUsuario(usuario);
				
				// adicionando o objeto a lista
				client.add(cliente);
			}
			rs.close();
			stmt.close();
			return client;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // fim Listar Cliente por ID
	
	
	/**
	 * Método para Listar somente Cliente -- ADMIN
	 * @param entidade
	 * @return
	 */
	public List<EntidadeDominio> consultarClienteByTipo (EntidadeDominio entidade){
		openConnection();
		try {
			List<EntidadeDominio> clientes = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from cliente where tipoCliente = 'cliente'");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Cliente, onde tambem possui dados do Usuario
				Cliente cliente = new Cliente();
				Usuario usuario = new Usuario();
				
				cliente.setId(rs.getString("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setData_Nascimento(rs.getString("data_Nascimento"));
				cliente.setGenero(rs.getString("genero"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setStatus(rs.getString("status"));
				cliente.setTipoCliente(rs.getString("tipoCliente"));
				cliente.setData_Cadastro(rs.getString("data_Cadastro"));
				
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				
				cliente.setUsuario(usuario);
				
				// adicionando o objeto a lista
				clientes.add(cliente);
			}
			rs.close();
			stmt.close();
			return clientes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // fim Listar apenas CLIENTE - ADMIN
	
	
	/**
	 * Método para Listar/Verificar o status do Usuario (ativo/inativo) -- opção como ADMIN
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
				// criando o objeto Cliente, onde tambem possui dados do Usuario
				
				Cliente cliente = new Cliente();
				Usuario usuario = new Usuario();
				
				cliente.setId(rs.getString("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setData_Nascimento(rs.getString("data_Nascimento"));
				cliente.setGenero(rs.getString("genero"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setStatus(rs.getString("status"));
				cliente.setTipoCliente(rs.getString("tipoCliente"));

				cliente.setData_Cadastro(rs.getString("data_Cadastro"));
				
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				
				cliente.setUsuario(usuario);
				
				// adicionando o objeto a lista
				clientes.add(cliente);
			}
				
			rs.close();
			stmt.close();
			return clientes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // fim Listar/Verificar Usuario por status ativo/inativo
}