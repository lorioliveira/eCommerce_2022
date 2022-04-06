package com.les.roupa.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

import com.les.roupa.core.dominio.CartaoCredito;
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Usuario;


/**
 * DAO para CARTAO DE CREDITO
 * @author Lorena Oliveira
 *
 */
public class CartaoCreditoDAO extends AbstractJdbcDAO{
	
	/**
	 * Metodo para SALVAR o Cartao 
	 * @param entidade
	 */
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		
		String sql = "insert into cartaoCredito (numCartao, bandeira, nome, "
				+ "validade, cvv, preferencial, id_cliente, data_Cadastro) values (?,?,?,?,?,?,?,?)";
		
		try {
			CartaoCredito cartaocredito = (CartaoCredito) entidade;
			
			// para inserir o cartao no banco
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setString(1,cartaocredito.getNumCartao());
			stmt.setString(2,cartaocredito.getBandeira());
			stmt.setString(3,cartaocredito.getNome());
			stmt.setString(4,cartaocredito.getValidade());
			stmt.setString(5,cartaocredito.getCvv());
			stmt.setString(6,cartaocredito.getPreferencial());
			stmt.setString(7,cartaocredito.getIdCliente());
			stmt.setString(8,cartaocredito.getData_Cadastro());
			
			// executa
			stmt.execute();
			
			//Lista todos os dados do Cartao
			List<CartaoCredito> cartoesCliente = new ArrayList<>();
			stmt = connection.prepareStatement("select * from cartaoCredito where id_cliente = ?");
			stmt.setString(1, cartaocredito.getIdCliente());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Cartao
				CartaoCredito listaCartao = new CartaoCredito();
				
				listaCartao.setId(rs.getString("id"));
				listaCartao.setNumCartao(rs.getString("numCartao"));
				listaCartao.setBandeira(rs.getString("bandeira"));
				listaCartao.setNome(rs.getString("nome"));
				listaCartao.setValidade(rs.getString("validade"));
				listaCartao.setCvv(rs.getString("cvv"));
				listaCartao.setPreferencial(rs.getString("preferencial"));
				listaCartao.setData_Cadastro(rs.getString("data_Cadastro"));
									
				// adicionando o objeto a lista
				cartoesCliente.add(listaCartao);
			}
			
			cartaocredito.setTodosCartoes(cartoesCliente);
			
			
			rs.close();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Salvar
	
	/**
	 * Metodo para SALVAR o Cartao 
	 * @param entidade
	 */
	@Override
	public void alterar(EntidadeDominio entidade) {
			openConnection();
		
		String sql = "update cartaoCredito set numCartao=?, bandeira=?, nome=?, "
				+ "validade=?, cvv=?, preferencial=?, data_Cadastro=? where id=?" ;
		
		try {
			CartaoCredito cartaocredito = (CartaoCredito) entidade;
			Cliente cliente = new Cliente();
			Usuario usuario = cliente.getUsuario();
			
			// quando o atributo "alteraPref" for igual a 1, ele permite alterar alguns dos dados do Cartao
			if(cartaocredito.getAlteraPref().contentEquals("1")) {
			
				// para inserir dados agora atualizados do cartao no banco
				PreparedStatement stmt = connection.prepareStatement(sql);
				
				// seta os valores
				stmt.setString(1,cartaocredito.getNumCartao());
				stmt.setString(2,cartaocredito.getBandeira());
				stmt.setString(3,cartaocredito.getNome());
				stmt.setString(4,cartaocredito.getValidade());
				stmt.setString(5,cartaocredito.getCvv());
				stmt.setString(6,cartaocredito.getPreferencial());
			
				stmt.setString(7,cartaocredito.getData_Cadastro());
				stmt.setString(8,cartaocredito.getId());
				
				// executa
				stmt.execute();

				//Lista todos os dados do Cartao
				List<CartaoCredito> cartoesCliente = new ArrayList<>();
				stmt = connection.prepareStatement("select * from cartaoCredito where id_cliente = ?");
				stmt.setString(1, cartaocredito.getIdCliente());
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					// criando o objeto Cartao
					CartaoCredito cartaoAltera = new CartaoCredito();
					
					cartaoAltera.setId(rs.getString("id"));
					cartaoAltera.setNumCartao(rs.getString("numCartao"));
					cartaoAltera.setBandeira(rs.getString("bandeira"));
					cartaoAltera.setNome(rs.getString("nome"));
					cartaoAltera.setValidade(rs.getString("validade"));
					cartaoAltera.setCvv(rs.getString("cvv"));
					cartaoAltera.setPreferencial(rs.getString("preferencial"));
					cartaoAltera.setData_Cadastro(rs.getString("data_Cadastro"));
										
					// adicionando o objeto a lista
					cartoesCliente.add(cartaoAltera);
				}
				
				cartaocredito.setTodosCartoes(cartoesCliente);
				
				rs.close();
				stmt.close();
				
			
			}else {
				
				//Listar todos os dados de um Cartao de Credito para Alterar
				List<CartaoCredito> cartoesCliente = new ArrayList<>();
				PreparedStatement stmt = connection.prepareStatement("select * from cartaoCredito where id = ?");
				stmt.setString(1, cartaocredito.getId());
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					// criando o objeto Cartao
					CartaoCredito cartaoAltera = new CartaoCredito();
					
					cartaoAltera.setId(rs.getString("id"));
					cartaoAltera.setNumCartao(rs.getString("numCartao"));
					cartaoAltera.setBandeira(rs.getString("bandeira"));
					cartaoAltera.setNome(rs.getString("nome"));
					cartaoAltera.setValidade(rs.getString("validade"));
					cartaoAltera.setCvv(rs.getString("cvv"));
					cartaoAltera.setPreferencial(rs.getString("preferencial"));
					cartaoAltera.setData_Cadastro(rs.getString("data_Cadastro"));
										
					// adicionando o objeto a lista
					cartoesCliente.add(cartaoAltera);
				}
				
				cartaocredito.setTodosCartoes(cartoesCliente);
				
				rs.close();
				stmt.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}// Alterar Cartao
	
	
	/**
	 * Método para EXCLUIR CARTAO 
	 * @param entidade
	 */
	public void excluir (EntidadeDominio entidade) {
		openConnection();
		
		try {
			CartaoCredito cartaocredito = (CartaoCredito) entidade;
			
			PreparedStatement stmt = connection.prepareStatement("delete from cartaoCredito where id=?");
			
			stmt.setString(1, cartaocredito.getId());
			
			stmt.executeUpdate();
			
			//Lista todos os dados do Cartao
			List<CartaoCredito> cartoesCliente = new ArrayList<>();
			stmt = connection.prepareStatement("select * from cartaoCredito where id_cliente = ?");
			stmt.setString(1, cartaocredito.getIdCliente());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Cartao
				CartaoCredito listaCartao = new CartaoCredito();
				
				listaCartao.setId(rs.getString("id"));
				listaCartao.setNumCartao(rs.getString("numCartao"));
				listaCartao.setBandeira(rs.getString("bandeira"));
				listaCartao.setNome(rs.getString("nome"));
				listaCartao.setValidade(rs.getString("validade"));
				listaCartao.setCvv(rs.getString("cvv"));
				listaCartao.setPreferencial(rs.getString("preferencial"));
				listaCartao.setData_Cadastro(rs.getString("data_Cadastro"));
									
				// adicionando o objeto a lista
				cartoesCliente.add(listaCartao);
			}
			
			cartaocredito.setTodosCartoes(cartoesCliente);
			
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Excluir Cartao
	
	/**
	 * Método para CONSULTAR todos os cartoes
	 * @param entidade
	 * @return
	 */
	public List<EntidadeDominio> consultar (EntidadeDominio entidade) throws SQLException {
		openConnection();
		try {
			CartaoCredito cartaocredito = (CartaoCredito) entidade;
			PreparedStatement stmt = connection.prepareStatement("select * from cartaoCredito");
			ResultSet rs = stmt.executeQuery();
			
			// Lista de cartao criada
			List<EntidadeDominio> cartoes = new ArrayList<>();
			while (rs.next()) {
				
				// criando o objeto cartao de credito
				CartaoCredito cCredito = new CartaoCredito();
				
				cCredito.setNumCartao(rs.getString("numCartao"));
				cCredito.setBandeira(rs.getString("bandeira"));
				cCredito.setNome(rs.getString("nome"));
				cCredito.setValidade(rs.getString("validade"));
				cCredito.setCvv(rs.getString("cvv"));
				cCredito.setId(rs.getString("id"));
				cCredito.setPreferencial(rs.getString("preferencial"));
				cCredito.setData_Cadastro(rs.getString("data_Cadastro"));
				
				
				// adicionando o objeto à lista de Enderecos criado acima
				cartoes.add(cCredito);
			}
			rs.close();
			stmt.close();
			return cartoes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar/Consultar


	/**
	 * Método para LISTAR Cartao de Credito por ID
	 * @param entidade
	 * @return
	 */
	public List<CartaoCredito> consultarCartCreditoById (String idCartao){
		openConnection();
		try {
			
			List<CartaoCredito> cartoes = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from cartaoCredito where id = ?");
			stmt.setString(1, idCartao);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto cartao de credito
				
				CartaoCredito cartaocredito = new CartaoCredito();
				
				cartaocredito.setId(rs.getString("id"));
				cartaocredito.setNumCartao(rs.getString("numCartao"));
				cartaocredito.setBandeira(rs.getString("bandeira"));
				cartaocredito.setNome(rs.getString("nome"));
				cartaocredito.setValidade(rs.getString("validade"));
				cartaocredito.setCvv(rs.getString("cvv"));
				cartaocredito.setPreferencial(rs.getString("preferencial"));
				cartaocredito.setData_Cadastro(rs.getString("data_Cadastro"));
				
				
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
	 * Método para Listar Cartao de Credito por ID do Cliente
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
				cartaocredito.setNumCartao(rs.getString("numCartao"));
				cartaocredito.setBandeira(rs.getString("bandeira"));
				cartaocredito.setNome(rs.getString("nome"));
				cartaocredito.setValidade(rs.getString("validade"));
				cartaocredito.setCvv(rs.getString("cvv"));
				cartaocredito.setPreferencial(rs.getString("preferencial"));
				cartaocredito.setIdCliente(rs.getString("id_cliente"));
				cartaocredito.setData_Cadastro(rs.getString("data_Cadastro"));
				
				
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