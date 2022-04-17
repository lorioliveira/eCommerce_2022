package com.les.roupa.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.les.roupa.core.dominio.Cupom;
import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Estoque;
import com.les.roupa.core.dominio.ItemPedido;
import com.les.roupa.core.dominio.Pedido;
import com.les.roupa.core.dominio.PedidoTroca;
import com.les.roupa.core.dominio.Produto;

public class PedidoTrocaDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para salvar a Troca do Pedido ou dos Itens do Pedido
	 * @param entidade
	 */
	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		PedidoTroca pedidoTrocaEntidade = (PedidoTroca) entidade;
		
		PedidoDAO pedidoDAO = new PedidoDAO();
		ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
		Pedido pedido = new Pedido();
		Pedido novoPedido = new Pedido();
		ItemPedido novoItemPedido = new ItemPedido();
		ItemPedido itemPedidoTrocaInteira = new ItemPedido();
		List<ItemPedido> alteraQtdeAndStatusItemPedido = new ArrayList<>();	
		List<PedidoTroca> itensPedidoParaAdicionarNaSessao = new ArrayList<>();	
		List<Pedido> pedidoOriginal =  new ArrayList<>();
		double total_itens = 0;
		
		// salva a data atual no Pedido e na sequencia no Item do Pedido
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String dataAtual;
		dataAtual = dateFormat.format(date);
		
		// verifica se foi acionado o botão para realizar a troca inteira do Pedido,
		
		if (pedidoTrocaEntidade.getTrocaPedidoInteiro().equals("1")) {
			// seta o valor do ID do Pedido com o valor que foi enviado pela tela
			itemPedidoTrocaInteira.setIdPedido(pedidoTrocaEntidade.getIdPedido());
			
			// busca os Itens do Pedido pelo ID do Pedido
			List<EntidadeDominio> entidades = itemPedidoDAO.consultar(itemPedidoTrocaInteira);
			
			// feito o CAST de Entidade para o ItemPedido (pegando o primeiro indice de Entidade)
			ItemPedido itemPedidoEntidade = (ItemPedido) entidades.get(0);
			
			for(ItemPedido order_items : itemPedidoEntidade.getItensPedido()) {
				// Aplicado o CAST para poder popular os itens do pedido
				// guarda o Item do Pedido no objeto "pedidoTroca"
				PedidoTroca pedidoTroca = new PedidoTroca();
				pedidoTroca.setItemPedido(order_items);
				
				// passa o item selecionado para a variavel que será responsavel para atualizar a sessão dos itens de troca do Pedido
				itensPedidoParaAdicionarNaSessao.add(pedidoTroca);
			}
			
			// atualiza o objeto "itensPedidoTroca" que esta salvo em sessão, com os novos itens do Pedido selecionado
			pedidoTrocaEntidade.setItensPedidoTroca(itensPedidoParaAdicionarNaSessao);
		}
		
		// buscar as informações do Pedido que esta vinculado no Item do Pedido de Troca da Sessão,
		// para salvar com as mesmas informações do Pedido Original
		pedidoOriginal = pedidoDAO.consultarPedidoById(pedidoTrocaEntidade.getItensPedidoTroca().get(0).getItemPedido().getIdPedido());
		
		//calcular o total dos itens do pedido de troca
		for (int i = 0; i< pedidoTrocaEntidade.getItensPedidoTroca().size(); i++) {
			// faz o calculo dos itens que serão solicitados para a troca
			total_itens += (Double.parseDouble(pedidoTrocaEntidade.getItensPedidoTroca().get(i).getItemPedido().getProduto().getQuantidadeSelecionada()) * Double.parseDouble(pedidoTrocaEntidade.getItensPedidoTroca().get(i).getItemPedido().getProduto().getPrecoVenda()));
		}
		
		novoPedido.setTotalItens("0");
		novoPedido.setTotalFrete("0");
		novoPedido.setTotalPedido(Double.toString(total_itens));
		novoPedido.setStatus("TROCA SOLICITADA");
		novoPedido.setIdCliente(pedidoOriginal.get(0).getIdCliente());
		novoPedido.setIdEndereco(pedidoOriginal.get(0).getIdEndereco());
		novoPedido.setFormaPagamento(pedidoOriginal.get(0).getFormaPagamento());
		novoPedido.setIdCartao1(pedidoOriginal.get(0).getIdCartao1());
		novoPedido.setValorCartao1(pedidoOriginal.get(0).getValorCartao1());
		novoPedido.setIdCartao2(pedidoOriginal.get(0).getIdCartao2());
		novoPedido.setValorCartao2(pedidoOriginal.get(0).getValorCartao2());
		novoPedido.setTotalCupons("0");
		novoPedido.setTrocado("sim");
		novoPedido.setData_Cadastro(dataAtual);
		novoPedido.setDarBaixaEstoque("NAO");
		
		// salva o novo Pedido com o status TROCA SOLICITADA
		pedidoDAO.salvar(novoPedido);
		
		// consulta o ultimo Pedido cadastrado para poder pegar o ID do Pedido
		List<Pedido> ultimoPedido = pedidoDAO.consultarUltimoPedidoCadastrado(pedido);
		
		// para cria os novos Itens do Pedido
		for (int i = 0; i< pedidoTrocaEntidade.getItensPedidoTroca().size(); i++) {
			novoItemPedido.setProduto(pedidoTrocaEntidade.getItensPedidoTroca().get(i).getItemPedido().getProduto());
			novoItemPedido.setIdPedido(ultimoPedido.get(0).getId());
			novoItemPedido.setTrocado("sim");
			novoItemPedido.setData_Cadastro(ultimoPedido.get(0).getData_Cadastro());
			
			// salva o novo Item do Pedido
			itemPedidoDAO.salvar(novoItemPedido);
		}
		
		// altera a Quantidade e o Status do Item do Pedido que foi selecionado no Pedido
		for (int i = 0; i< pedidoTrocaEntidade.getItensPedidoTroca().size(); i++) {
			int novaQuantidadeDoItemPedido = 0;
			
			// busca o item do pedido para fazer a somatoria
			alteraQtdeAndStatusItemPedido = itemPedidoDAO.consultarItemPedidoById(pedidoTrocaEntidade.getItensPedidoTroca().get(i).getItemPedido().getId());
			
			// faz o calculo do novo valor da quantidade do item do pedido
			novaQuantidadeDoItemPedido = (Integer.parseInt(alteraQtdeAndStatusItemPedido.get(0).getProduto().getQuantidadeSelecionada()) - Integer.parseInt(pedidoTrocaEntidade.getItensPedidoTroca().get(i).getItemPedido().getProduto().getQuantidadeSelecionada()));
			
			// faz a alteração da nova quantidade do item do pedido no banco
			itemPedidoDAO.alterarQuantidadeItemPedido(Integer.toString(novaQuantidadeDoItemPedido), pedidoTrocaEntidade.getItensPedidoTroca().get(i).getItemPedido().getId());
			
			
			// trocado o status do item para "já foi trocado",
			// altera no banco a tabela "pedido_item" da coluna "trocado" para "sim".
			if (novaQuantidadeDoItemPedido == 0) {
				itemPedidoDAO.alterarTrocacaoItemPedido(pedidoTrocaEntidade.getItensPedidoTroca().get(i).getItemPedido().getId());
			}
		}
		
		// verifica se existe algum item desse Pedido selecionado, esta com o status "trocado" como "nao" e 
		// altera o status do Pedido para "já foi trocado".
		List<ItemPedido> pedidoComTodosOsItensJaTrocados = itemPedidoDAO.consultarItemPedidoByIdPedidoAndTrocadoNao(pedidoTrocaEntidade.getItensPedidoTroca().get(0).getItemPedido().getIdPedido());
		
		if (pedidoComTodosOsItensJaTrocados.isEmpty()) {
			// altera o status do Pedido selecionado para "já foi trocado",
			// altera no banco a tabela "pedido" da coluna "trocado" para "sim".
			pedidoDAO.alterarTrocacaoPedido(pedidoTrocaEntidade.getItensPedidoTroca().get(0).getItemPedido().getIdPedido());
			
			// altera o status do Pedido selecionado para CANCELAMENTO ACEITO
			pedidoDAO.alterarStatusPedido(pedidoTrocaEntidade.getItensPedidoTroca().get(0).getItemPedido().getIdPedido(), "CANCELAMENTO ACEITO");
		}
		
	} // Salvar
	
	
	/**
	 * Metodo para alterar a Troca do Pedido ou dos Itens do Pedido
	 * @param entidade
	 */
	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		PedidoTroca pedidoTrocaEntidade = (PedidoTroca) entidade;
		
		PedidoDAO pedidoDAO = new PedidoDAO();
		ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
		EstoqueDAO estoqueDAO = new EstoqueDAO();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		CupomDAO cupomDAO = new CupomDAO();
		ItemPedido itemPedido = new ItemPedido();
		Cupom cupom = new Cupom();
		Estoque estoque = new Estoque();
		int quantidadeFinal;
		
		List<Pedido> listPedido = pedidoDAO.consultarPedidoById(pedidoTrocaEntidade.getIdPedido());
		
		// salva a data atual no Cupom e no Estoque
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String dataAtual;
		dataAtual = dateFormat.format(date);
		
		pedidoTrocaEntidade.setPedido(listPedido.get(0));
		
		// se for algum desses status, será feito a ReEntrada no Estoque e gerado o Cupom de desconto do mesmo
		if (pedidoTrocaEntidade.getNovoStatusPedido().equals("TROCA EFETUADA") || pedidoTrocaEntidade.getNovoStatusPedido().equals("CANCELAMENTO EFETUADO")) {
			// ajusta o bug de não realizar a Reentrada no Estoque mais de uma vez,
			// verifica se o Pedido selecionado já esta com o status "TROCA EFETUADA"
			if (pedidoTrocaEntidade.getPedido().getStatus().equals("TROCA EFETUADA")) {
				
			}
			// verifica se o Pedido selecionado já esta com o status "CANCELAMENTO EFETUADO"
			else if (pedidoTrocaEntidade.getPedido().getStatus().equals("CANCELAMENTO EFETUADO")) {
				
			}
			else {
				// caso contrário, será realizada a ReEntrada no Estoque e gerado o Cupom e altera o status
				pedidoDAO.alterarStatusPedido(pedidoTrocaEntidade.getIdPedido(), pedidoTrocaEntidade.getNovoStatusPedido());
				
				
				itemPedido.setIdPedido(pedidoTrocaEntidade.getIdPedido());
				
				// busca todos os itens desse Pedido para realizar a ReEntrada no Estoque
				List<EntidadeDominio> entidades = itemPedidoDAO.consultar(itemPedido);
				
				ItemPedido itens_pedido = (ItemPedido) entidades.get(0);
				
				for(EntidadeDominio e : itens_pedido.getItensPedido()) {
					ItemPedido order_items = (ItemPedido) e;
					
					// soma a quantidade anterior do produto, 
					// com a quantidade que dará a entrada, para poder salvar a "Quantidade Final"
					List<Produto> produtoSelecionado = produtoDAO.consultarProdutoById(order_items.getProduto().getId());
					
					// faz a conta de soma da quantidade de entrada, mais a quantidade que já tinha no produto
					quantidadeFinal = (Integer.parseInt(order_items.getProduto().getQuantidadeSelecionada()) + Integer.parseInt(produtoSelecionado.get(0).getQtdeEstoque()));
					
					// realiza a ReEntrada no Estoque,
					// altera a quantidade do estoque do Produto
					estoqueDAO.alterarQuantidadeProduto(Integer.toString(quantidadeFinal), order_items.getProduto().getId());
					
					// ajuste do BUG de quando for realizar a ReEntrada de algum Produto
					if (produtoSelecionado.get(0).getStatus().equals("inativo")) {
						estoqueDAO.ativarProdutoEstoque(produtoSelecionado.get(0).getId());
					}
					
					// salva os atributos do ultimo Pedido cadastrado no Estoque
					estoque.setIdProduto(order_items.getProduto().getId());
					estoque.setTipo("entrada");
					estoque.setQuantidadeEntradaSaida(order_items.getProduto().getQuantidadeSelecionada());
					estoque.setValorCusto(produtoSelecionado.get(0).getPrecoCompra());
					estoque.setFornecedor("Entrada no Estoque pelo Pedido: " + order_items.getIdPedido());
					estoque.setDtEntrada(dataAtual);
					estoque.setQuantidadeFinal(Integer.toString(quantidadeFinal));
					estoque.setData_Cadastro(dataAtual);
					
					// cria a entrada do produto no "Estoque"
					estoqueDAO.salvar(estoque);
				}
				
				// gera o Cupom de Troca
				if(pedidoTrocaEntidade.getNovoStatusPedido().equals("TROCA EFETUADA")) {
					cupom.setNome("TROCA" + pedidoTrocaEntidade.getIdPedido());
					cupom.setTipo("troca");
				}
				else {
					cupom.setNome("DEVOLUCAO" + pedidoTrocaEntidade.getIdPedido());
					cupom.setTipo("devolucao");
				}
				
				cupom.setValor(pedidoTrocaEntidade.getTotalPedido());
				cupom.setUtilizado("nao");
				cupom.setIdCliente(pedidoTrocaEntidade.getIdCliente());
				cupom.setData_Cadastro(dataAtual);
				
				// gera o novo Cupom
				cupomDAO.salvar(cupom);
			}
		}
		else {
			// altera o status do Pedido conforme foi selecionado na tela
			pedidoDAO.alterarStatusPedido(pedidoTrocaEntidade.getIdPedido(), pedidoTrocaEntidade.getNovoStatusPedido());
		}
		
		// atualiza a lista de pedidos conforme a alteração do status do pedidos
		openConnection();
		
		List<Pedido> pedidosAtualizados = new ArrayList<>();
		PreparedStatement stmt = connection.prepareStatement("select * from pedido");
		ResultSet rs = stmt.executeQuery();

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
			
			// adicionando o objeto à lista
			pedidosAtualizados.add(pedidoItem);
		}
		
		// Pedidos atualizados,
		// conforme a alteração do status do pedidos
		pedidoTrocaEntidade.setPedidos(pedidosAtualizados);
		
	} // Alterar
	
	
	/**
	 * Metodo para excluir
	 * @param entidade
	 */
	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Excluir
	
	
	/**
	 * Metodo para Consultar a Troca do Pedido ou dos Itens do Pedido
	 * @param entidade
	 */
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		try {
			PedidoTroca pedidoTrocaEntidade = (PedidoTroca) entidade;
			PedidoTroca novoPedidoTroca = new PedidoTroca();
			List<EntidadeDominio> listPedidoTroca = new ArrayList<>();
			
			PreparedStatement stmt = connection.prepareStatement("select * from pedido where id=?");
			stmt.setString(1, pedidoTrocaEntidade.getIdPedido());
			ResultSet rs = stmt.executeQuery();
			
			List<Pedido> pedidos = new ArrayList<>();
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
				
				// adicionando o objeto à lista
				pedidos.add(pedidoItem);
			}
			
			stmt = connection.prepareStatement("select * from pedido_item where id=?");
			stmt.setString(1, pedidoTrocaEntidade.getIdItemPedido());
			rs = stmt.executeQuery();
			
			List<ItemPedido> item_pedido_selecionado = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Pedido
				ItemPedido item_pedidoItem = new ItemPedido();
				Produto produto = new Produto();
				
				item_pedidoItem.setId(rs.getString("id"));
				
				produto.setId(rs.getString("id_produto"));
				produto.setNome(rs.getString("nome"));
				produto.setPrecoVenda(rs.getString("valor_de_venda"));
				produto.setQuantidadeSelecionada(rs.getString("quantidade"));
				item_pedidoItem.setProduto(produto);
				
				item_pedidoItem.setIdPedido(rs.getString("id_pedido"));
				item_pedidoItem.setTrocado(rs.getString("trocado"));
				item_pedidoItem.setData_Cadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				item_pedido_selecionado.add(item_pedidoItem);
			}
			
			stmt = connection.prepareStatement("select * from pedido_item where id_pedido=?");
			stmt.setString(1, pedidoTrocaEntidade.getIdPedido());
			rs = stmt.executeQuery();
			
			List<ItemPedido> todos_itens_pedido = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Pedido
				ItemPedido item_pedidoItem = new ItemPedido();
				Produto produto = new Produto();
				
				item_pedidoItem.setId(rs.getString("id"));
				
				produto.setId(rs.getString("id_produto"));
				produto.setNome(rs.getString("nome"));
				produto.setPrecoVenda(rs.getString("valor_de_venda"));
				produto.setQuantidadeSelecionada(rs.getString("quantidade"));
				item_pedidoItem.setProduto(produto);
				
				item_pedidoItem.setIdPedido(rs.getString("id_pedido"));
				item_pedidoItem.setTrocado(rs.getString("trocado"));
				item_pedidoItem.setData_Cadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				todos_itens_pedido.add(item_pedidoItem);
			}
			
			stmt = connection.prepareStatement("select * from endereco where id=?");
			stmt.setString(1, pedidos.get(0).getIdEndereco());
			rs = stmt.executeQuery();
			
			List<Endereco> enderecos = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Endereço
				Endereco enderecoItem = new Endereco();
				
				enderecoItem.setId(rs.getString("id"));
				enderecoItem.setCep(rs.getString("cep"));
				enderecoItem.setLogradouro(rs.getString("logradouro"));
				enderecoItem.setNumero(rs.getString("numero"));
				enderecoItem.setBairro(rs.getString("bairro"));
				enderecoItem.setCidade(rs.getString("cidade"));
				enderecoItem.setEstado(rs.getString("estado"));
				enderecoItem.setPais(rs.getString("pais"));
				enderecoItem.setTipoResidencia(rs.getString("tipoResidencia"));
				enderecoItem.setObservacoes(rs.getString("observacoes"));
				enderecoItem.setTipoEnd(rs.getString("tipoEndereco"));
				enderecoItem.setData_Cadastro(rs.getString("data_Cadastro"));
				
				enderecoItem.setIdCliente(rs.getString("id_cliente"));
				
				// adicionando o objeto à lista
				enderecos.add(enderecoItem);
			}
			
			novoPedidoTroca.setPedidos(pedidos);
			novoPedidoTroca.setItemPedidoSelecionado(item_pedido_selecionado);
			novoPedidoTroca.setTodosItensPedido(todos_itens_pedido);
			novoPedidoTroca.setEnderecoPedido(enderecos);
			
			listPedidoTroca.add(novoPedidoTroca);
			
			rs.close();
			stmt.close();
			return listPedidoTroca;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Consultar
	
}