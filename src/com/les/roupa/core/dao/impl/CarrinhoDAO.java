package com.les.roupa.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.les.roupa.core.dominio.Carrinho;
import com.les.roupa.core.dominio.CartaoCredito;
import com.les.roupa.core.dominio.Cupom;
import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Produto;
/**
 * DAO PARA CARRINHO
 * @author Lorena Oliveira
 *
 */

public class CarrinhoDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para SALVAR o Carrinho 
	 * @param entidade
	 */
	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		Carrinho carrinho = (Carrinho) entidade;
		
		ProdutoDAO dao = new ProdutoDAO();
		
		// pesquisa no BD o produto selecionado conforme o ID que foi pego
		List<Produto> produtoSelecionado = dao.consultarProdutoById(carrinho.getDetalheProduto().getProduto().getId());
		
		// adiciona o produto selecionado para poder pegar todos os dados do produto,
		// o Carrinho (entidade) que veio como parametro, esse objeto esta sendo alterado como REFERENCIA,
		// logo consigo buscar esse objeto no resultado no SetView do CarrinhoHelper
		carrinho.getDetalheProduto().setProduto(produtoSelecionado.get(0));
		
	} // Salvar Carrinho
	
	
	/**
	 * Metodo para ALTERAR o Carrinho
	 * @param entidade
	 */
	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		Carrinho carrinho = (Carrinho) entidade;
		
		ProdutoDAO dao = new ProdutoDAO();
		
		// pesquisa no BD o produto selecionado conforme o ID que foi pego
		List<Produto> produtoSelecionado = dao.consultarProdutoById(carrinho.getDetalheProduto().getProduto().getId());
		
		// adiciona o produto selecionado para poder pegar todos os dados do produto,
		// o Carrinho (entidade) que veio como parametro, esse objeto esta sendo alterado como REFERENCIA,
		// logo consigo buscar esse objeto no resultado no SetView do CarrinhoHelper
		carrinho.getDetalheProduto().setProduto(produtoSelecionado.get(0));
		
	} // Alterar Carrinho
	
	
	/**
	 * Metodo para EXCLUIR o Carrinho
	 * @param entidade
	 */
	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		Carrinho carrinho = (Carrinho) entidade;
		
		ProdutoDAO dao = new ProdutoDAO();
		
		// pesquisa no BD o produto selecionado conforme o ID que foi pego
		List<Produto> produtoSelecionado = dao.consultarProdutoById(carrinho.getDetalheProduto().getProduto().getId());
		
		// adiciona o produto selecionado para poder pegar todos os dados do produto,
		// o Carrinho (entidade) que veio como parametro, esse objeto esta sendo alterado como REFERENCIA,
		// logo consigo buscar esse objeto no resultado no SetView do CarrinhoHelper
		carrinho.getDetalheProduto().setProduto(produtoSelecionado.get(0));
		
	} // Excluir Carrinho
	
	
	/**
	 * Metodo para CONSULTAR o Carrinho
	 * @param entidade
	 */
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		try {
			Carrinho carrinho = (Carrinho) entidade;
			Carrinho novoCarrinho = new Carrinho();
			List<EntidadeDominio> listCarrinho = new ArrayList<>();
			
			PreparedStatement stmt = connection.prepareStatement("select * from endereco where id_cliente = ?");
			stmt.setString(1, carrinho.getIdCliente());
			ResultSet rs = stmt.executeQuery();
			
			// Lista de Endereços
			List<Endereco> enderecos = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Endereço
				Endereco endAltera = new Endereco();
				
				endAltera.setId(rs.getString("id"));
				endAltera.setCep(rs.getString("cep"));
				endAltera.setLogradouro(rs.getString("logradouro"));
				endAltera.setNumero(rs.getString("numero"));
				endAltera.setBairro(rs.getString("bairro"));
				endAltera.setCidade(rs.getString("cidade"));
				endAltera.setEstado(rs.getString("estado"));
				endAltera.setPais(rs.getString("pais"));
				endAltera.setTipoResidencia(rs.getString("tipoResidencia"));
				endAltera.setObservacoes(rs.getString("observacoes"));
				endAltera.setTipoEnd(rs.getString("tipoEndereco"));
				endAltera.setData_Cadastro(rs.getString("data_Cadastro"));
				
				// adicionando o objeto à lista
				enderecos.add(endAltera);
			}
			
			stmt = connection.prepareStatement("select * from cartaoCredito where id_cliente=?");
			stmt.setString(1, carrinho.getIdCliente());
			rs = stmt.executeQuery();
			
			List<CartaoCredito> cartoes = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Cartao de Credito
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
				cartoes.add(cartaoAltera);
			}
			
			stmt = connection.prepareStatement("select * from cupom where id_cliente=?");
			stmt.setString(1, carrinho.getIdCliente());
			rs = stmt.executeQuery();
			
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
			
			novoCarrinho.setEnderecos(enderecos);
			novoCarrinho.setCartoes(cartoes);
			novoCarrinho.setCupons(cupons);
			
			listCarrinho.add(novoCarrinho);
			
			rs.close();
			stmt.close();
			return listCarrinho;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Consultar
	
}