package com.les.roupa.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Estoque;
import com.les.roupa.core.dominio.Produto;

/**
 * DAO para PRODUTO
 * @author Lorena Oliveira
 * 
 */
public class ProdutoDAO extends AbstractJdbcDAO {

	/**
	 * Metodo para SALVAR - opчуo como Admin
	 */
	public void salvar(EntidadeDominio entidade) {
		/*
		 * openConnection();
		 * 
		 * String sql =
		 * "insert into produto (nome, descricao, categoria, cores, tamanho, precoCompra, "
		 * +
		 * "precoVenda, qtdeEstoque, foto, Data_Cadastro, status, grupoPrecificacao, descricaoStatusProduto) values (?,?,?,?,?,?,?,?,?,?,?,?,?)"
		 * ;
		 * 
		 * try { Produto produto = (Produto) entidade;
		 * 
		 * PreparedStatement stmt = connection.prepareStatement(sql);
		 * 
		 * stmt.setString(1,produto.getNome());
		 * stmt.setString(2,produto.getDescricao());
		 * stmt.setString(3,produto.getCategoria());
		 * stmt.setString(4,produto.getCores()); stmt.setString(5,produto.getTamanho());
		 * stmt.setString(6,produto.getPrecoCompra());
		 * stmt.setString(7,produto.getPrecoVenda());
		 * stmt.setString(8,produto.getQtdeEstoque());
		 * stmt.setString(9,produto.getFoto());
		 * stmt.setString(10,produto.getData_Cadastro());
		 * stmt.setString(11,produto.getStatus());
		 * stmt.setString(12,produto.getGrupoPrecificacao()); stmt.setString(13,
		 * produto.getStatusProduto());
		 * 
		 * stmt.execute(); stmt.close();
		 * 
		 * } catch (Exception e) { throw new RuntimeException(e); }
		 */
	} // Salvar

	/**
	 * Metodo para ALTERAR PRODUTO
	 * 
	 * @param entidade
	 */
	public void alterar(EntidadeDominio entidade) {
		openConnection();

		String sql = "update produto set nome=?, descricao=?, categoria=?, cores=?, tamanho=?, precoCompra=?, "
				+ "precoVenda=?, qtdeEstoque=?, foto=?, dt_cadastro=?, status=?, grupoPrecificacao=?, motivoStatus=? where id=?";

		try {
			Produto produto = (Produto) entidade;

			// quando o atributo "alteraProduto" for igual a 1, ele altera o Produto
			if (produto.getAlteraProduto().contentEquals("1")) {
				PreparedStatement stmt = connection.prepareStatement(sql);

				stmt.setString(1, produto.getNome());
				stmt.setString(2, produto.getDescricao());
				stmt.setString(3, produto.getCategoria());
				stmt.setString(4, produto.getCores());
				stmt.setString(5, produto.getTamanho());
				stmt.setString(6, produto.getPrecoCompra());
				stmt.setString(7, produto.getPrecoVenda());
				stmt.setString(8, produto.getQtdeEstoque());
				stmt.setString(9, produto.getFoto());
				stmt.setString(10, produto.getData_Cadastro());
				stmt.setString(11, produto.getStatus());
				stmt.setString(12, produto.getGrupoPrecificacao());
				stmt.setString(13, produto.getMotivoStatus());
				stmt.setString(14, produto.getId());

				stmt.execute();

				// Listar todos os dados do Produto
				List<Produto> todosProdutos = new ArrayList<>();
				stmt = connection.prepareStatement("select * from produto where id = ?");
				stmt.setString(1, produto.getId());
				ResultSet rs = stmt.executeQuery();

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

				produto.setTodosProdutos(todosProdutos);

			} else {

				// Listar todos os dados de um Produto para alterar
				List<Produto> todosProdutos = new ArrayList<>();
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt = connection.prepareStatement("select * from produto where id = ?");
				stmt.setString(1, produto.getId());
				ResultSet rs = stmt.executeQuery();

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

				produto.setTodosProdutos(todosProdutos);

				rs.close();
				stmt.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Alterar

	/**
	 * Metodo para EXCLUIR
	 */
	public void excluir(EntidadeDominio entidade) {

	} // Excluir

	/**
	 * Metodo para Listar/Consultar PRODUTO
	 * 
	 * @param entidade
	 * @return
	 */
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		openConnection();
		try {
			Produto produto = (Produto) entidade;
			PreparedStatement stmt = connection.prepareStatement("select * from produto");
			ResultSet rs = stmt.executeQuery();

			// Lista de Produtos criada
			List<EntidadeDominio> produtos = new ArrayList<>();

			while (rs.next()) {
				// criando o objeto Produto

				Produto prod = new Produto();

				prod.setId(rs.getString("id"));
				prod.setNome(rs.getString("nome"));
				prod.setDescricao(rs.getString("descricao"));
				prod.setCategoria(rs.getString("categoria"));
				prod.setCores(rs.getString("cores"));
				prod.setTamanho(rs.getString("tamanho"));
				prod.setPrecoCompra(rs.getString("precoCompra"));
				prod.setPrecoVenda(rs.getString("precoVenda"));
				prod.setQtdeEstoque(rs.getString("qtdeEstoque"));
				prod.setFoto(rs.getString("foto"));
				prod.setData_Cadastro(rs.getString("dt_cadastro"));
				prod.setStatus(rs.getString("status"));
				prod.setGrupoPrecificacao(rs.getString("grupoPrecificacao"));
				prod.setMotivoStatus(rs.getString("motivoStatus"));

				// adicionando o objeto р lista
				produtos.add(prod);
			}
			rs.close();
			stmt.close();
			return produtos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar

	/**
	 * Metodo para Listar Produto por ID
	 * 
	 * @param entidade
	 * @return
	 */
	public List<Produto> consultarProdutoById(String idProduto) {
		openConnection();
		try {
			List<Produto> produto = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from produto where id=? ");
			stmt.setString(1, idProduto);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Produto

				Produto prod = new Produto();

				prod.setId(rs.getString("id"));
				prod.setNome(rs.getString("nome"));
				prod.setDescricao(rs.getString("descricao"));
				prod.setCategoria(rs.getString("categoria"));
				prod.setCores(rs.getString("cores"));
				prod.setTamanho(rs.getString("tamanho"));
				prod.setPrecoCompra(rs.getString("precoCompra"));
				prod.setPrecoVenda(rs.getString("precoVenda"));
				prod.setQtdeEstoque(rs.getString("qtdeEstoque"));
				prod.setFoto(rs.getString("foto"));
				prod.setData_Cadastro(rs.getString("dt_cadastro"));
				prod.setStatus(rs.getString("status"));
				prod.setGrupoPrecificacao(rs.getString("grupoPrecificacao"));
				prod.setMotivoStatus(rs.getString("motivoStatus"));

				// adicionando o objeto р lista
				produto.add(prod);
			}
			rs.close();
			stmt.close();
			return produto;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar

	/**
	 * Metodo para Listar os Produtos somente Ativos
	 * 
	 * @param entidade
	 * @return
	 */
	public List<EntidadeDominio> consultarSomenteAtivo(EntidadeDominio entidade) {
		openConnection();
		try {
			List<EntidadeDominio> produtos = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from produto where status='ativo'");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Produto
				Produto prod = new Produto();

				prod.setId(rs.getString("id"));
				prod.setNome(rs.getString("nome"));
				prod.setDescricao(rs.getString("descricao"));
				prod.setCategoria(rs.getString("categoria"));
				prod.setCores(rs.getString("cores"));
				prod.setTamanho(rs.getString("tamanho"));
				prod.setPrecoCompra(rs.getString("precoCompra"));
				prod.setPrecoVenda(rs.getString("precoVenda"));
				prod.setQtdeEstoque(rs.getString("qtdeEstoque"));
				prod.setFoto(rs.getString("foto"));
				prod.setData_Cadastro(rs.getString("Data_Cadastro"));
				prod.setStatus(rs.getString("status"));
				prod.setGrupoPrecificacao(rs.getString("grupoPrecificacao"));
				prod.setMotivoStatus(rs.getString("motivoStatus"));

				// adicionando o objeto р lista
				produtos.add(prod);
			}
			rs.close();
			stmt.close();
			return produtos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}// listar apenas os ativos

	/**
	 * Metodo para Listar o ultimo Produto cadastrado no sistema
	 * @param entidade
	 * @return
	 */
	public List<Produto> consultarUltimoProdutoCadastrado (EntidadeDominio entidade){
		openConnection();
		try {
			List<Produto> produtos = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM produto WHERE id=(SELECT max(id) FROM produto)");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Produto

				Produto prod = new Produto();

				prod.setId(rs.getString("id"));
				prod.setNome(rs.getString("nome"));
				prod.setDescricao(rs.getString("descricao"));
				prod.setCategoria(rs.getString("categoria"));
				prod.setCores(rs.getString("cores"));
				prod.setTamanho(rs.getString("tamanho"));
				prod.setPrecoCompra(rs.getString("precoCompra"));
				prod.setPrecoVenda(rs.getString("precoVenda"));
				prod.setQtdeEstoque(rs.getString("qtdeEstoque"));
				prod.setFoto(rs.getString("foto"));
				prod.setData_Cadastro(rs.getString("dt_cadastro"));
				prod.setStatus(rs.getString("status"));
				prod.setGrupoPrecificacao(rs.getString("grupoPrecificacao"));
				prod.setMotivoStatus(rs.getString("motivoStatus"));

				// adicionando o objeto р lista
				produtos.add(prod);
			}
			rs.close();
			stmt.close();
			return produtos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar ultimo Produto cadastrado
	
	
	/**
		 * Metodo para Listar o ultimo codigo do Produto cadastrado no sistema
		 * 
		 * @param entidade
		 * @return
		 */
	/*
	 * public List<Produto> consultarUltimoCodigoSistemaCadastrado (){
	 * openConnection(); try { List<Produto> produtos = new ArrayList<>();
	 * PreparedStatement stmt = connection.
	 * prepareStatement("SELECT * FROM produto WHERE cd_sistema=(SELECT max(cd_sistema) FROM produto)"
	 * ); ResultSet rs = stmt.executeQuery();
	 * 
	 * while (rs.next()) { // criando o objeto Produto Produto produtoItem = new
	 * Produto();
	 * 
	 * produtoItem.setId(rs.getString("id"));
	 * produtoItem.setNome(rs.getString("nome"));
	 * produtoItem.setDescricao(rs.getString("descricao"));
	 * produtoItem.setCategoria(rs.getString("categoria"));
	 * produtoItem.setPrecoDeCompra(rs.getString("preco_de_compra"));
	 * produtoItem.setPrecoDeVenda(rs.getString("preco_de_venda"));
	 * produtoItem.setFoto(rs.getString("foto"));
	 * produtoItem.setFotoDetalhe(rs.getString("foto_detalhe"));
	 * produtoItem.setGrupoDePrecificacao(rs.getString("grupo_de_precificacao"));
	 * produtoItem.setFoto(rs.getString("foto"));
	 * produtoItem.setQuantidade(rs.getString("quantidade"));
	 * produtoItem.setCdSistema(rs.getString("cd_sistema"));
	 * produtoItem.setStatus(rs.getString("status"));
	 * produtoItem.setDtCadastro(rs.getString("dt_cadastro"));
	 * produtoItem.setObservacao(rs.getString("observacao"));
	 * 
	 * // adicionando o objeto р lista produtos.add(produtoItem); } rs.close();
	 * stmt.close(); return produtos; } catch (Exception e) { throw new
	 * RuntimeException(e); } } // Listar o ultimo codigo do Produto cadastrado no
	 * sistema
	 * 
	 * 
	 *//**
		 * Metodo para Listar os Produtos pela Pesquisa por Filtro
		 * 
		 * @param entidade
		 * @return
		 *//*
			 * public List<EntidadeDominio> consultarProdutoPesquisaByFiltro
			 * (EntidadeDominio entidade, String Parametro){ openConnection(); try {
			 * List<EntidadeDominio> produtos = new ArrayList<>(); PreparedStatement stmt =
			 * connection.prepareStatement("select * from produto where nome LIKE ?");
			 * stmt.setString(1, "%" + Parametro + "%"); ResultSet rs = stmt.executeQuery();
			 * 
			 * while (rs.next()) { // criando o objeto Produto Produto produtoItem = new
			 * Produto();
			 * 
			 * produtoItem.setId(rs.getString("id"));
			 * produtoItem.setNome(rs.getString("nome"));
			 * produtoItem.setDescricao(rs.getString("descricao"));
			 * produtoItem.setCategoria(rs.getString("categoria"));
			 * produtoItem.setPrecoDeCompra(rs.getString("preco_de_compra"));
			 * produtoItem.setPrecoDeVenda(rs.getString("preco_de_venda"));
			 * produtoItem.setFoto(rs.getString("foto"));
			 * produtoItem.setFotoDetalhe(rs.getString("foto_detalhe"));
			 * produtoItem.setGrupoDePrecificacao(rs.getString("grupo_de_precificacao"));
			 * produtoItem.setFoto(rs.getString("foto"));
			 * produtoItem.setQuantidade(rs.getString("quantidade"));
			 * produtoItem.setCdSistema(rs.getString("cd_sistema"));
			 * produtoItem.setStatus(rs.getString("status"));
			 * produtoItem.setDtCadastro(rs.getString("dt_cadastro"));
			 * produtoItem.setObservacao(rs.getString("observacao"));
			 * 
			 * // adicionando o objeto р lista produtos.add(produtoItem); } rs.close();
			 * stmt.close(); return produtos; } catch (Exception e) { throw new
			 * RuntimeException(e); } } // Listar os Produtos pela Pesquisa por Filtro
			 *
			 */
	
	/**
	 * Metodo para Gravar um registro novo no Estoque
	 * 
	 * @param entidade
	 * @return
	 */
	public void gravarRegistroEstoque() {
		ProdutoDAO dao = new ProdutoDAO();
		EstoqueDAO estoqueDAO = new EstoqueDAO();
		Produto produto = new Produto();
		Estoque estoque = new Estoque();

		// consulta o ultimo Produto cadastrado para poder pegar o ID do Produto,
		// e salvar na primeira entrada do Estoque
		List<Produto> ultimoProduto = dao.consultarUltimoProdutoCadastrado(produto);

		// salva os atributos do ultimo Produto cadastrado no Estoque,
		// pra poder dar a primeira entrada no Estoque
		estoque.setIdProduto(ultimoProduto.get(0).getId());
		estoque.setTipo("entrada");
		estoque.setQuantidadeEntradaSaida(ultimoProduto.get(0).getQuantidadeSelecionada());
		estoque.setValorCusto(ultimoProduto.get(0).getPrecoCompra());
		estoque.setFornecedor("Primeira entrada no Estoque");
		estoque.setDtEntrada(ultimoProduto.get(0).getData_Cadastro());
		estoque.setQuantidadeFinal(ultimoProduto.get(0).getQtdeEstoque());
		estoque.setData_Cadastro(ultimoProduto.get(0).getData_Cadastro());

		// cria a primeira entrada do produto no "Estoque"
		estoqueDAO.salvar(estoque);
	} // Gravar um registro novo no Estoque

}
