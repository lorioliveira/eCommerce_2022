package com.les.roupa.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.Cupom;
import com.les.roupa.core.dominio.EntidadeDominio;

public class CupomDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para salvar o Cupom
	 * @param entidade
	 */
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		
		String sql = "insert into cupom "+ 
				"(nome, valor, tipo, utilizado, id_cliente, dt_cadastro)" +
				"values (?,?,?,?,?,?)";
		
		try {
			Cupom cupom = (Cupom) entidade;
			
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setString(1,cupom.getNome());
			stmt.setString(2,cupom.getValor());
			stmt.setString(3,cupom.getTipo());
			stmt.setString(4,cupom.getUtilizado());
			stmt.setString(5, cupom.getIdCliente());
			stmt.setString(6, cupom.getData_Cadastro());
			
			// executa
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Salvar
	
	
	/**
	 * Metodo para alterar o Cupom
	 * @param entidade
	 */
	public void alterar (EntidadeDominio entidade) {
		openConnection();
		
		String sql = "update cupom set " +
					 "nome=?, valor=?, tipo=?, utilizado=?, id_cliente=? where id=?";
		
		try {
			Cupom cupomEntidade = (Cupom) entidade;
			
			// se tiver algo no "alteraCupom", altera o cupom
			if(cupomEntidade.getAlteraCupom().contentEquals("1")) {
				PreparedStatement stmt = connection.prepareStatement(sql);
				
				stmt.setString(1, cupomEntidade.getNome());
				stmt.setString(2, cupomEntidade.getValor());
				stmt.setString(3, cupomEntidade.getTipo());
				stmt.setString(4, cupomEntidade.getUtilizado());
				stmt.setString(5, cupomEntidade.getIdCliente());

				stmt.setString(6, cupomEntidade.getId());
				
				stmt.execute();
				stmt.close();
			}
			// caso contrário, não faz alteração e somente fecha a conexão com o banco,
			// e no CupomHelper, irá ter outra verificação para poder chamar a JSP de edição do cupom
			else {
				PreparedStatement stmt = connection.prepareStatement("select * from cupom where id=?");
				stmt.setString(1, cupomEntidade.getId());
				ResultSet rs = stmt.executeQuery();
				
				List<Cupom> cupons = new ArrayList<>();
				while (rs.next()) {
					// criando o objeto Cupom
					Cupom cupomItem = new Cupom();
					
					cupomItem.setId(rs.getString("id"));
					cupomItem.setNome(rs.getString("nome"));
					cupomItem.setValor(rs.getString("valor"));
					cupomItem.setTipo(rs.getString("tipo"));
					cupomItem.setUtilizado(rs.getString("utilizado"));
					cupomItem.setIdCliente(rs.getString("id_cliente"));
					cupomItem.setData_Cadastro(rs.getString("dt_cadastro"));
					
					// adicionando o objeto à lista
					cupons.add(cupomItem);
				}
				
				rs.close();
				stmt.close();
				
				// salva o objeto do cupom pesquisado, para mandar para a tela de edição
				// salva como REFERENCIA para o mesmo objeto que veio como parametro
				cupomEntidade.setCupomPesquisado(cupons.get(0));
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Alterar
	
	
	/**
	 * Metodo para Excluir o Cupom
	 * @param entidade
	 */
	public void excluir (EntidadeDominio entidade) {
		openConnection();
		
		try {
			Cupom cupomEntidade = (Cupom) entidade;
			
			ClienteDAO clienteDAO = new ClienteDAO();
			
			// Exclui o cupom
			PreparedStatement stmt = connection.prepareStatement("delete from cupom where id=?");
			stmt.setString(1, cupomEntidade.getId());
			stmt.executeUpdate();
			
			stmt = connection.prepareStatement("select * from cupom");
			ResultSet rs = stmt.executeQuery();
			
			List<Cupom> cupons = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Cupom
				Cupom cupomItem = new Cupom();
				
				cupomItem.setId(rs.getString("id"));
				cupomItem.setNome(rs.getString("nome"));
				cupomItem.setValor(rs.getString("valor"));
				cupomItem.setTipo(rs.getString("tipo"));
				cupomItem.setUtilizado(rs.getString("utilizado"));
				cupomItem.setIdCliente(rs.getString("id_cliente"));
				cupomItem.setData_Cadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				cupons.add(cupomItem);
			}
			
			// se tiver cupons, será pego os nomes dos clientes que esses cupons estão vinculados
			if (cupons.size() > 0) {
				for(Cupom coupon : cupons) {
					
					// ajusta o BUG de quando o noome do cliente no Cupom for NULL
					if (coupon.getIdCliente() != null) {
						// busca o Cliente do Cupom, pelo ID do cliente no Cupom
						List<Cliente> clientes = clienteDAO.consultarClienteById(coupon.getIdCliente());
						
						// salva o nome do Cliente no cupom
						coupon.setNomeClienteNoCupom(clientes.get(0).getNome());
					}
				}
			}
			
			rs.close();
			stmt.close();
			
			// salva como REFERENCIA o cupom, para poder listar os cupons de novo
			cupomEntidade.setTodosCupons(cupons);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Excluir
	
	
	/**
	 * Metodo para Listar o Cupom
	 * @param entidade
	 * @return
	 */
	public List<EntidadeDominio> consultar (EntidadeDominio entidade){
		openConnection();
		try {
			List<EntidadeDominio> listCupons = new ArrayList<>();
			Cupom novoCupom = new Cupom();
			
			ClienteDAO clienteDAO = new ClienteDAO();
			
			PreparedStatement stmt = connection.prepareStatement("select * from cupom");
			ResultSet rs = stmt.executeQuery();
			
			List<Cupom> cupons = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Cupom
				Cupom cupomItem = new Cupom();
				
				cupomItem.setId(rs.getString("id"));
				cupomItem.setNome(rs.getString("nome"));
				cupomItem.setValor(rs.getString("valor"));
				cupomItem.setTipo(rs.getString("tipo"));
				cupomItem.setUtilizado(rs.getString("utilizado"));
				cupomItem.setIdCliente(rs.getString("id_cliente"));
				cupomItem.setData_Cadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				cupons.add(cupomItem);
			}
			
			// se tiver cupons, será pego os nomes dos clientes que esses cupons estão vinculados
			if (cupons.size() > 0) {
				for(Cupom coupon : cupons) {
					
					// ajusta o BUG de quando o noome do cliente no Cupom for NULL
					if (coupon.getIdCliente() != null) {
						// busca o Cliente do Cupom, pelo ID do cliente no Cupom
						List<Cliente> clientes = clienteDAO.consultarClienteById(coupon.getIdCliente());
						
						// salva o nome do Cliente no cupom
						coupon.setNomeClienteNoCupom(clientes.get(0).getNome());
					}
				}
			}
			
			novoCupom.setTodosCupons(cupons);
			
			listCupons.add(novoCupom);
			
			rs.close();
			stmt.close();
			return listCupons;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar
	
	
	/**
	 * Metodo para Listar o Cupom por ID
	 * @param entidade
	 * @return
	 */
	public List<Cupom> consultarCupomById (String idCupom){
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from cupom where id=?");
			stmt.setString(1, idCupom);
			ResultSet rs = stmt.executeQuery();
			
			List<Cupom> cupons = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Cupom
				Cupom cupomItem = new Cupom();
				
				cupomItem.setId(rs.getString("id"));
				cupomItem.setNome(rs.getString("nome"));
				cupomItem.setValor(rs.getString("valor"));
				cupomItem.setTipo(rs.getString("tipo"));
				cupomItem.setUtilizado(rs.getString("utilizado"));
				cupomItem.setIdCliente(rs.getString("id_cliente"));
				cupomItem.setData_Cadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				cupons.add(cupomItem);
			}
			rs.close();
			stmt.close();
			return cupons;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar Cupom por ID
	
	
	/**
	 * Metodo para Listar todos os Cupons pelo ID do Cliente
	 * @param entidade
	 * @return
	 */
	public List<Cupom> consultarCupomByIdCliente (String idCliente){
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from cupom where id_cliente=?");
			stmt.setString(1, idCliente);
			ResultSet rs = stmt.executeQuery();
			
			List<Cupom> cupons = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Cupom
				Cupom cupomItem = new Cupom();
				
				cupomItem.setId(rs.getString("id"));
				cupomItem.setNome(rs.getString("nome"));
				cupomItem.setValor(rs.getString("valor"));
				cupomItem.setTipo(rs.getString("tipo"));
				cupomItem.setUtilizado(rs.getString("utilizado"));
				cupomItem.setIdCliente(rs.getString("id_cliente"));
				cupomItem.setData_Cadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				cupons.add(cupomItem);
			}
			rs.close();
			stmt.close();
			return cupons;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar todos os Cupons pelo ID do Cliente
	
	
	/**
	 * Metodo para Listar todos os Cupons pelo Nome
	 * @param entidade
	 * @return
	 */
	public List<Cupom> consultarCupomByNome (String nome){
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from cupom where nome=?");
			stmt.setString(1, nome);
			ResultSet rs = stmt.executeQuery();
			
			List<Cupom> cupons = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Cupom
				Cupom cupomItem = new Cupom();
				
				cupomItem.setId(rs.getString("id"));
				cupomItem.setNome(rs.getString("nome"));
				cupomItem.setValor(rs.getString("valor"));
				cupomItem.setTipo(rs.getString("tipo"));
				cupomItem.setUtilizado(rs.getString("utilizado"));
				cupomItem.setIdCliente(rs.getString("id_cliente"));
				cupomItem.setData_Cadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				cupons.add(cupomItem);
			}
			rs.close();
			stmt.close();
			return cupons;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar todos os Cupons pelo Nome
	
	
	/**
	 * Metodo para Listar todos os Cupons pelo Nome e ID do Cliente
	 * @param entidade
	 * @return
	 */
	public List<Cupom> consultarCupomByNomeAndIdCliente (String nome, String idCliente){
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from cupom where nome=? and id_cliente=?");
			stmt.setString(1, nome);
			stmt.setString(2, idCliente);
			ResultSet rs = stmt.executeQuery();
			
			List<Cupom> cupons = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Cupom
				Cupom cupomItem = new Cupom();
				
				cupomItem.setId(rs.getString("id"));
				cupomItem.setNome(rs.getString("nome"));
				cupomItem.setValor(rs.getString("valor"));
				cupomItem.setTipo(rs.getString("tipo"));
				cupomItem.setUtilizado(rs.getString("utilizado"));
				cupomItem.setIdCliente(rs.getString("id_cliente"));
				cupomItem.setData_Cadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				cupons.add(cupomItem);
			}
			rs.close();
			stmt.close();
			return cupons;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar todos os Cupons pelo Nome e ID do Cliente
	
	
	/**
	 * Metodo para alterar a utilização do Cupom
	 * @param entidade
	 */
	public void alterarUtilizacaoCupom (String idCupom) {
		openConnection();
		
		String sql = "update cupom set " +
					 "utilizado='sim' " +
					 "where id=?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, idCupom);
			
			stmt.execute();
			stmt.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Alterar a utilização do Cupom
	
}