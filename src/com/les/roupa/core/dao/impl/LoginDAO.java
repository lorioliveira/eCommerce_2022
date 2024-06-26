package com.les.roupa.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.les.roupa.core.dominio.CartaoCredito;
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.Cupom;
import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Pedido;
import com.les.roupa.core.dominio.Produto;
import com.les.roupa.core.dominio.Usuario;

/**
 * DAO para LOGIN - Usuario Cliente/ADMIN
 * @author Lorena Oliveira 
 * 
 */
public class LoginDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para SALVAR o cliente
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
			
			// prepared statement para insercao
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
	 * Metodo para ALTERAR o cliente
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
	 * Metodo para EXCLUIR o cliente
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
	 * Metodo para CONSULTAR o Usuario
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
				usuarioItem.setCpf(rs.getString("cpf"));
				usuarioItem.setData_Nascimento(rs.getString("data_Nascimento"));
				usuarioItem.setGenero(rs.getString("genero"));
				usuarioItem.setTelefone(rs.getString("telefone"));
				
				// adicionando o objeto a lista
				usuarios.add(usuarioItem);
			}
			
			// Listar todos os enderecos do Cliente logado
			stmt = connection.prepareStatement("select * from endereco where id_cliente=?");
			stmt.setString(1, usuarios.get(0).getId());
			rs = stmt.executeQuery();
			
			// Lista de Enderecos criada
				List<Endereco> enderecos = new ArrayList<>();
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
					end.setData_Cadastro(rs.getString("data_Cadastro"));
					
					
					// adicionando o objeto na lista de Enderecos criado acima
					enderecos.add(end);
				}
				
				//Lista todos os Cartoes do cliente
				stmt = connection.prepareStatement("select * from cartaoCredito where id_cliente = ?");
				stmt.setString(1, usuarios.get(0).getId());
				rs = stmt.executeQuery();
				
				//Lista de Cartoes criada
				List<CartaoCredito> cartoes = new ArrayList<>();

				while (rs.next()) {
					// criando o objeto Cartao
					CartaoCredito cCredito = new CartaoCredito();
					
					cCredito.setId(rs.getString("id"));
					cCredito.setNumCartao(rs.getString("numCartao"));
					cCredito.setBandeira(rs.getString("bandeira"));
					cCredito.setNome(rs.getString("nome"));
					cCredito.setValidade(rs.getString("validade"));
					cCredito.setCvv(rs.getString("cvv"));
					cCredito.setPreferencial(rs.getString("preferencial"));
					cCredito.setData_Cadastro(rs.getString("data_Cadastro"));
										
					// adicionando o objeto a lista de cartoes
					cartoes.add(cCredito);
				}
				
				//Listar todos os dados do Produto
				List<Produto> todosProdutos = new ArrayList<>();
				stmt = connection.prepareStatement("select * from produto");
				rs = stmt.executeQuery();
				
				while (rs.next()) {
					// criando o objeto Produto
					Produto listaProduto = new Produto();
					
					listaProduto.setId(rs.getString("id"));
					listaProduto.setNome(rs.getString("nome"));
					listaProduto.setDescricao(rs.getString("descricao"));
					listaProduto.setCategoria(rs.getString("categoria"));
					listaProduto.setCores(rs.getString("cores"));
					listaProduto.setTamanho(rs.getString("tamanho"));
					listaProduto.setPrecoCompra(rs.getString("precoCompra"));
					listaProduto.setPrecoVenda(rs.getString("precoVenda"));
					listaProduto.setQtdeEstoque(rs.getString("qtdeEstoque"));
					listaProduto.setFoto(rs.getString("foto"));
					listaProduto.setData_Cadastro(rs.getString("dt_cadastro"));
					listaProduto.setStatus(rs.getString("status"));
					listaProduto.setGrupoPrecificacao(rs.getString("grupoPrecificacao"));
					listaProduto.setMotivoStatus(rs.getString("motivoStatus"));
										
					// adicionando o objeto a lista
					todosProdutos.add(listaProduto);
				}
				
				// Listar os produtos ATIVOS
				stmt = connection.prepareStatement("select * from produto where status='ativo'");
				rs = stmt.executeQuery();
				
				List<Produto> produtosAtivos = new ArrayList<>();
				while (rs.next()) {

					// criando o objeto Produto
					Produto listaProduto = new Produto();

					listaProduto.setId(rs.getString("id"));
					listaProduto.setNome(rs.getString("nome"));
					listaProduto.setDescricao(rs.getString("descricao"));
					listaProduto.setCategoria(rs.getString("categoria"));
					listaProduto.setCores(rs.getString("cores"));
					listaProduto.setTamanho(rs.getString("tamanho"));
					listaProduto.setPrecoCompra(rs.getString("precoCompra"));
					listaProduto.setPrecoVenda(rs.getString("precoVenda"));
					listaProduto.setQtdeEstoque(rs.getString("qtdeEstoque"));
					listaProduto.setFoto(rs.getString("foto"));
					listaProduto.setData_Cadastro(rs.getString("dt_cadastro"));
					listaProduto.setStatus(rs.getString("status"));
					listaProduto.setGrupoPrecificacao(rs.getString("grupoPrecificacao"));
					listaProduto.setMotivoStatus(rs.getString("motivoStatus"));

					// adicionando o objeto a lista
					produtosAtivos.add(listaProduto);
				}
				
				//Listar todos os Clientes cadastrados - Op��o como ADMIN
				List<Cliente> todosClientes = new ArrayList<>();
				stmt = connection.prepareStatement("select * from cliente where tipoCliente = 'cliente'");
				rs = stmt.executeQuery();
				
				while (rs.next()) {
					// criando o objeto Cliente, onde tambem possui dados do Usuario
					
					Cliente cliente = new Cliente();
					Usuario usuarioCliente = new Usuario();
					
					cliente.setId(rs.getString("id"));
					cliente.setNome(rs.getString("nome"));
					cliente.setCpf(rs.getString("cpf"));
					cliente.setData_Nascimento(rs.getString("data_Nascimento"));
					cliente.setGenero(rs.getString("genero"));
					cliente.setTelefone(rs.getString("telefone"));
					cliente.setStatus(rs.getString("status"));
					cliente.setTipoCliente(rs.getString("tipoCliente"));

					cliente.setData_Cadastro(rs.getString("data_Cadastro"));
					
					usuarioCliente.setEmail(rs.getString("email"));
					usuarioCliente.setSenha(rs.getString("senha"));
					
					cliente.setUsuario(usuarioCliente);
					
					// adicionando o objeto a lista
					todosClientes.add(cliente);
				}
				
				//Listar todos os pedidos - Opcao como ADMIN
				List<Pedido> todosPedidos = new ArrayList<>();
				stmt = connection.prepareStatement("select * from pedido");
				rs = stmt.executeQuery();
				
				while (rs.next()) {
					// criando o objeto Pedido
					Pedido pedidoItem = new Pedido();
					
					pedidoItem.setId(rs.getString("id"));
					pedidoItem.setTotalItens(rs.getString("total_itens"));
					pedidoItem.setTotalFrete(rs.getString("total_frete"));
					pedidoItem.setTotalPedido(rs.getString("total_pedido"));
					pedidoItem.setStatus(rs.getString("status"));
					pedidoItem.setIdCliente(rs.getString("id_cliente"));
					pedidoItem.setIdEndereco(rs.getString("id_endereco"));
					pedidoItem.setFormaPagamento(rs.getString("forma_pagamento"));
					pedidoItem.setIdCartao1(rs.getString("id_cartao_1"));
					pedidoItem.setValorCartao1(rs.getString("valor_cartao_1"));
					pedidoItem.setIdCartao2(rs.getString("id_cartao_2"));
					pedidoItem.setValorCartao2(rs.getString("valor_cartao_2"));
					pedidoItem.setTotalCupons(rs.getString("total_cupons"));
					pedidoItem.setTrocado(rs.getString("trocado"));
					pedidoItem.setData_Cadastro(rs.getString("dt_cadastro"));
					
					// adicionando o objeto � lista
					todosPedidos.add(pedidoItem);
				}
				
				//Listar todos os pedidos 
				List<Pedido> pedidosCliente = new ArrayList<>();
				stmt = connection.prepareStatement("select * from pedido where id_cliente = ?");
				stmt.setString(1, usuarios.get(0).getId());
				rs = stmt.executeQuery();
				
				while (rs.next()) {
					// criando o objeto Pedido
					Pedido pedidoItem = new Pedido();
					
					pedidoItem.setId(rs.getString("id"));
					pedidoItem.setTotalItens(rs.getString("total_itens"));
					pedidoItem.setTotalFrete(rs.getString("total_frete"));
					pedidoItem.setTotalPedido(rs.getString("total_pedido"));
					pedidoItem.setStatus(rs.getString("status"));
					pedidoItem.setIdCliente(rs.getString("id_cliente"));
					pedidoItem.setIdEndereco(rs.getString("id_endereco"));
					pedidoItem.setFormaPagamento(rs.getString("forma_pagamento"));
					pedidoItem.setIdCartao1(rs.getString("id_cartao_1"));
					pedidoItem.setValorCartao1(rs.getString("valor_cartao_1"));
					pedidoItem.setIdCartao2(rs.getString("id_cartao_2"));
					pedidoItem.setValorCartao2(rs.getString("valor_cartao_2"));
					pedidoItem.setTotalCupons(rs.getString("total_cupons"));
					pedidoItem.setTrocado(rs.getString("trocado"));
					pedidoItem.setData_Cadastro(rs.getString("dt_cadastro"));
					
					// adicionando o objeto � lista
					pedidosCliente.add(pedidoItem);
				}
				
				// Lista os Cupons do Cliente
				List<Cupom> cuponsCliente = new ArrayList<>();
				stmt = connection.prepareStatement("select * from cupom where id_cliente=? and utilizado='nao'");
				stmt.setString(1, usuarios.get(0).getId());
				rs = stmt.executeQuery();
				
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
					
					// adicionando o objeto � lista
					cuponsCliente.add(cupomItem);
				}
				
				// Lista TODOS os Cupons - Op��o como ADMIN
				List<Cupom> todosCupons = new ArrayList<>();
				stmt = connection.prepareStatement("select * from cupom");
				rs = stmt.executeQuery();
				
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
					
					// adicionando o objeto � lista
					todosCupons.add(cupomItem);
				}
				
				
				//TODAS AS LISTAS
				Usuario novoUsuario = (Usuario) usuarios.get(0);
				novoUsuario.setEnderecosCliente(enderecos);
				novoUsuario.setTodosClientes(todosClientes);
				novoUsuario.setTodosCartoes(cartoes);
				novoUsuario.setTodosProdutos(todosProdutos);
				novoUsuario.setProdutosAtivos(produtosAtivos);
				novoUsuario.setTodosPedidos(todosPedidos);
				novoUsuario.setPedidosCliente(pedidosCliente);
				novoUsuario.setCuponsCliente(cuponsCliente);
				novoUsuario.setTodosCupons(todosCupons);
				
				
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
			
			List<EntidadeDominio> usuarios = new ArrayList<>();
			
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
				
				
				// adicionando o objeto na lista
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
				
				// adicionando o objeto na lista
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
	