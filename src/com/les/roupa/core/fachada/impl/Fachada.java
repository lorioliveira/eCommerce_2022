package com.les.roupa.core.fachada.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.les.roupa.core.dao.IDAO;
import com.les.roupa.core.dao.impl.CarrinhoDAO;
import com.les.roupa.core.dao.impl.CartaoCreditoDAO;
import com.les.roupa.core.dao.impl.ClienteDAO;
import com.les.roupa.core.dao.impl.CupomDAO;
import com.les.roupa.core.dao.impl.DetalheProdutoDAO;
import com.les.roupa.core.dao.impl.EnderecoDAO;
import com.les.roupa.core.dao.impl.EstoqueDAO;
import com.les.roupa.core.dao.impl.GraficoAnaliseDAO;
import com.les.roupa.core.dao.impl.LoginDAO;
import com.les.roupa.core.dao.impl.PedidoDAO;
import com.les.roupa.core.dao.impl.PedidoTrocaDAO;
import com.les.roupa.core.dao.impl.ProdutoDAO;
import com.les.roupa.core.dao.impl.VerificaCupomDAO;
import com.les.roupa.core.fachada.IFachada;
import com.les.roupa.core.dominio.Carrinho;
import com.les.roupa.core.dominio.CartaoCredito;
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.Cupom;
import com.les.roupa.core.dominio.DetalheProduto;
import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Estoque;
import com.les.roupa.core.dominio.GraficoAnalise;
import com.les.roupa.core.dominio.Pedido;
import com.les.roupa.core.dominio.PedidoTroca;
import com.les.roupa.core.dominio.Produto;
import com.les.roupa.core.dominio.Resultado;
import com.les.roupa.core.dominio.Usuario;
import com.les.roupa.core.dominio.VerificaCupom;
import com.les.roupa.core.strategy.impl.ValidarBairro;
import com.les.roupa.core.strategy.impl.ValidarBairro_Alt;
import com.les.roupa.core.strategy.impl.ValidarBandeiraCartao;
import com.les.roupa.core.strategy.impl.ValidarCEP_Alt;
import com.les.roupa.core.strategy.impl.ValidarCPF;
import com.les.roupa.core.strategy.impl.ValidarCartaoPedido;
import com.les.roupa.core.strategy.impl.ValidarCidade;
import com.les.roupa.core.strategy.impl.ValidarCidade_Alt;
import com.les.roupa.core.strategy.impl.ValidarCupom;
import com.les.roupa.core.strategy.impl.ValidarCupomBonificacaoPedido;
import com.les.roupa.core.strategy.impl.ValidarDataEntradaSaidaEstoque;
import com.les.roupa.core.strategy.impl.ValidarDataNascimento;
import com.les.roupa.core.strategy.impl.ValidarDataNascimento_Alt;
import com.les.roupa.core.strategy.impl.ValidarDatasGraficoAnalise;
import com.les.roupa.core.strategy.impl.ValidarDtCadastro;
import com.les.roupa.core.strategy.impl.ValidarEmailVazio;
import com.les.roupa.core.strategy.impl.ValidarEmail_Alt;
import com.les.roupa.core.strategy.impl.ValidarEnderecoPedido;
import com.les.roupa.core.strategy.impl.ValidarEntradaEstoque;
import com.les.roupa.core.strategy.impl.ValidarEstado;
import com.les.roupa.core.strategy.impl.ValidarEstado_Alt;
import com.les.roupa.core.strategy.impl.ValidarExisteEmail;
import com.les.roupa.core.strategy.impl.ValidarExisteEmailSenha;
import com.les.roupa.core.strategy.impl.ValidarFormaDePagamento;
import com.les.roupa.core.strategy.impl.ValidarFornecedorEstoque;
import com.les.roupa.core.strategy.impl.ValidarGenero;
import com.les.roupa.core.strategy.impl.ValidarGenero_Alt;
import com.les.roupa.core.strategy.impl.ValidarLogradouro;
import com.les.roupa.core.strategy.impl.ValidarLogradouro_Alt;
import com.les.roupa.core.strategy.impl.ValidarNome;
import com.les.roupa.core.strategy.impl.ValidarNome_Alt;
import com.les.roupa.core.strategy.impl.ValidarNumCartao;
import com.les.roupa.core.strategy.impl.ValidarNumero;
import com.les.roupa.core.strategy.impl.ValidarNumero_Alt;
import com.les.roupa.core.strategy.impl.ValidarPais;
import com.les.roupa.core.strategy.impl.ValidarPais_Alt;
import com.les.roupa.core.strategy.impl.ValidarProdutoEstoque;
import com.les.roupa.core.strategy.impl.ValidarQuantidadeEstoque;
import com.les.roupa.core.strategy.impl.ValidarQuantidadeSelecionada;
import com.les.roupa.core.strategy.impl.ValidarSaidaEstoque;
import com.les.roupa.core.strategy.impl.ValidarSenha;
import com.les.roupa.core.strategy.impl.ValidarSenhaConfSenha;
import com.les.roupa.core.strategy.impl.ValidarSenhaLogin;
import com.les.roupa.core.strategy.impl.ValidarStatus;
import com.les.roupa.core.strategy.impl.ValidarStatusPedido;
import com.les.roupa.core.strategy.impl.ValidarTelefone;
import com.les.roupa.core.strategy.impl.ValidarTipoEstoque;
import com.les.roupa.core.strategy.impl.ValidarTipoResidencia;
import com.les.roupa.core.strategy.impl.ValidarTipoResidencia_Alt;
import com.les.roupa.core.strategy.impl.ValidarTotalPedido;
import com.les.roupa.core.strategy.impl.ValidarValorCustoEstoque;
import com.les.roupa.view.helper.impl.DetalheProdutoHelper;
import com.les.roupa.core.strategy.IStrategy;


/**
 * FACHADA - Mirror Fashion
 * @author Lorena Oliveira
*/

public class Fachada implements IFachada {

	private Resultado resultado;
	private static Map<String, IDAO> daos;

	/* --------------------------------- Declaracao de TODAS as Strategy's ------------------------------------ */
	
	/* ---- CLIENTE -----*/
	ValidarEmailVazio vEmail = new ValidarEmailVazio();
	ValidarNome vNome = new ValidarNome();
	ValidarCPF vCPF = new ValidarCPF();
	ValidarDataNascimento vDt_nasc = new ValidarDataNascimento();
	ValidarTelefone vTelefone = new ValidarTelefone();
	ValidarGenero vGenero = new ValidarGenero();
	ValidarStatus vStatus = new ValidarStatus();
	ValidarSenhaConfSenha vSenhaConfSenhaIgual = new ValidarSenhaConfSenha();
	ValidarSenha vSenha = new ValidarSenha();
	
	ValidarEmail_Alt vEmailAlterado = new ValidarEmail_Alt();
	ValidarNome_Alt vNomeAlterado = new ValidarNome_Alt();
	ValidarDataNascimento_Alt vDtNascimentoAlterado = new ValidarDataNascimento_Alt();
	ValidarGenero_Alt vGeneroAlterado = new ValidarGenero_Alt();


	/* ---- ENDERE�O ---- */
	ValidarLogradouro vLogradouro = new ValidarLogradouro();
	ValidarNumero vNumero = new ValidarNumero();
	ValidarBairro vBairro = new ValidarBairro();
	ValidarCidade vCidade = new ValidarCidade();
	ValidarEstado vEstado = new ValidarEstado();
	ValidarPais vPais = new ValidarPais();
	ValidarTipoResidencia vTipoResidencia = new ValidarTipoResidencia();
	
	ValidarBairro_Alt vBairroAlterado = new ValidarBairro_Alt();
	ValidarCEP_Alt vCEPAlterado = new ValidarCEP_Alt();
	ValidarLogradouro_Alt vLogradouroAlterado = new ValidarLogradouro_Alt();
	ValidarNumero_Alt vNumAlterado = new ValidarNumero_Alt();
	ValidarCidade_Alt vCidadeAlterado = new ValidarCidade_Alt();
	ValidarEstado_Alt vEstadoAlterado = new ValidarEstado_Alt();
	ValidarPais_Alt vPaisAlterado = new ValidarPais_Alt();
	ValidarTipoResidencia_Alt vTipoResidenciaAlterado = new ValidarTipoResidencia_Alt();
	
	/* ---------- PEDIDO ------------ */
	ValidarTotalPedido vTotalPedido = new ValidarTotalPedido();
	ValidarEnderecoPedido vEnderecoPedido = new ValidarEnderecoPedido();
	ValidarFormaDePagamento vFormaDePagamentoPedido = new ValidarFormaDePagamento();
	ValidarCartaoPedido vCartaoPedido = new ValidarCartaoPedido();
	ValidarStatusPedido vStatusPedido = new ValidarStatusPedido();
	ValidarCupomBonificacaoPedido vCupomBonificacaoPedido = new ValidarCupomBonificacaoPedido();
	
	/* ---- SALVAR/ALTERAR CARTAO DE CREDITO -----*/
	ValidarNumCartao vNumCartao = new ValidarNumCartao();
	ValidarBandeiraCartao vBandeiraCartao = new ValidarBandeiraCartao();
		
	/* ---- LOGIN -----*/
	ValidarExisteEmail vExisteEmail = new ValidarExisteEmail();
	ValidarExisteEmailSenha vExisteEmailSenha = new ValidarExisteEmailSenha();
	ValidarSenhaLogin vSenhaLogin = new ValidarSenhaLogin();
		
	/* --- CUPOM --- */
	ValidarCupom vCupom = new ValidarCupom();
	
	
	/* --- ESTOQUE - ENTRADA E SAIDA --- */
	ValidarProdutoEstoque vProdutoEstoque = new ValidarProdutoEstoque();
	ValidarTipoEstoque vTipoEstoque = new ValidarTipoEstoque();
	ValidarQuantidadeEstoque vQuantidadeEstoque = new ValidarQuantidadeEstoque(); 
	ValidarValorCustoEstoque vValorCustoEstoque = new ValidarValorCustoEstoque();
	//ValidarFornecedorEstoque vFornecedorEstoque = new ValidarFornecedorEstoque();
	ValidarDataEntradaSaidaEstoque vDataEntradaSaidaEstoque = new ValidarDataEntradaSaidaEstoque();
	ValidarEntradaEstoque vEntradaEstoque = new ValidarEntradaEstoque();
	ValidarSaidaEstoque vSaidaEstoque = new ValidarSaidaEstoque();
	ValidarQuantidadeSelecionada vQuantidadeSelecionada = new ValidarQuantidadeSelecionada();
	
	
	/* --- GRAFICO --- */
	ValidarDatasGraficoAnalise vDatasGraficoAnalise = new ValidarDatasGraficoAnalise();
	
	/* ------------------------------------------------------------------------------------------------------------------- */
	
	/* ------------ Declaracao das Listas de Strategy's dos Dominios ------------ */
	/* ------------ SALVAR ------------ */
	List<IStrategy> regrasSalvarCliente = new ArrayList<>();
	List<IStrategy> regrasSalvarEndereco = new ArrayList<>();
	List<IStrategy> regrasSalvarCartaoCredito = new ArrayList<>();
	List<IStrategy> regrasSalvarLogin = new ArrayList<>();
	List<IStrategy> regrasSalvarProduto = new ArrayList<>();
	List<IStrategy> regrasSalvarDetalheProduto = new ArrayList<>();
	List<IStrategy> regrasSalvarCarrinho = new ArrayList<>();
	List<IStrategy> regrasSalvarPedido = new ArrayList<>();
	List<IStrategy> regrasSalvarCupom = new ArrayList<>();
	List<IStrategy> regrasSalvarVerificaCupom = new ArrayList<>();
	List<IStrategy> regrasSalvarCupomCarrinho = new ArrayList<>();
	List<IStrategy> regrasSalvarPedidoTroca = new ArrayList<>();
	List<IStrategy> regrasSalvarEstoque = new ArrayList<>();
	
	/* ------------ CONSULTAR ------------ */
	List<IStrategy> regrasConsultarCliente = new ArrayList<>();
	List<IStrategy> regrasConsultarEndereco = new ArrayList<>();
	List<IStrategy> regrasConsultarCartaoCredito = new ArrayList<>();
	List<IStrategy> regrasConsultarLogin = new ArrayList<>();
	List<IStrategy> regrasConsultarProduto = new ArrayList<>();
	List<IStrategy> regrasConsultarCarrinho = new ArrayList<>();
	List<IStrategy> regrasConsultarPedido = new ArrayList<>();
	List<IStrategy> regrasConsultarCupom = new ArrayList<>();
	List<IStrategy> regrasConsultarVerificaCupom = new ArrayList<>();
	List<IStrategy> regrasConsultarCupomCarrinho = new ArrayList<>();
	List<IStrategy> regrasConsultarPedidoTroca = new ArrayList<>();
	List<IStrategy> regrasConsultarEstoque = new ArrayList<>();
	
	List<IStrategy> regrasConsultarGraficoAnalise = new ArrayList<>();
	
	
	/* ------------ ALTERAR ------------ */
	List<IStrategy> regrasAlterarCliente = new ArrayList<>();
	List<IStrategy> regrasAlterarEndereco = new ArrayList<>();
	List<IStrategy> regrasAlterarCartaoCredito = new ArrayList<>();
	List<IStrategy> regrasAlterarLogin = new ArrayList<>();
	List<IStrategy> regrasAlterarProduto = new ArrayList<>();
	List<IStrategy> regrasAlterarCarrinho = new ArrayList<>();
	List<IStrategy> regrasAlterarPedido = new ArrayList<>();
	List<IStrategy> regrasAlterarVerificaCupom = new ArrayList<>();
	List<IStrategy> regrasAlterarCupom = new ArrayList<>();
	List<IStrategy> regrasAlterarCupomCarrinho = new ArrayList<>();
	List<IStrategy> regrasAlterarPedidoTroca = new ArrayList<>();
	List<IStrategy> regrasAlterarEstoque = new ArrayList<>();
	
	/* ------------ EXCLUIR ------------ */
	List<IStrategy> regrasExcluirCliente = new ArrayList<>();
	List<IStrategy> regrasExcluirEndereco = new ArrayList<>();
	List<IStrategy> regrasExcluirCartaoCredito = new ArrayList<>();
	List<IStrategy> regrasExcluirLogin = new ArrayList<>();
	List<IStrategy> regrasExcluirProduto = new ArrayList<>();
	List<IStrategy> regrasExcluirCarrinho = new ArrayList<>();
	List<IStrategy> regrasExcluirPedido = new ArrayList<>();
	List<IStrategy> regrasExcluirCupom = new ArrayList<>();
	List<IStrategy> regrasExcluirVerificaCupom = new ArrayList<>();
	List<IStrategy> regrasExcluirCupomCarrinho = new ArrayList<>();
	List<IStrategy> regrasExcluirPedidoTroca = new ArrayList<>();
	List<IStrategy> regrasExcluirEstoque = new ArrayList<>();
	
	/* ------------------------------------------------------------------------------------------------------------------- */
	
	/* ------------ Declaracao dos MAP's das Regras de Negocios dos Dominios ------------ */
	Map<String, List<IStrategy>> regrasCliente = new HashMap<>();
	Map<String, List<IStrategy>> regrasEndereco = new HashMap<>();
	Map<String, List<IStrategy>> regrasCartaoCredito = new HashMap<>();
	Map<String, List<IStrategy>> regrasLogin = new HashMap<>();
	Map<String, List<IStrategy>> regrasProduto = new HashMap<>();
	Map<String, List<IStrategy>> regrasDetalheProduto = new HashMap<>();
	Map<String, List<IStrategy>> regrasCarrinho = new HashMap<>();
	Map<String, List<IStrategy>> regrasPedido = new HashMap<>();
	Map<String, List<IStrategy>> regrasCupom = new HashMap<>();
	Map<String, List<IStrategy>> regrasVerificaCupom = new HashMap<>();
	Map<String, List<IStrategy>> regrasCupomCarrinho = new HashMap<>();
	Map<String, List<IStrategy>> regrasPedidoTroca = new HashMap<>();
	Map<String, List<IStrategy>> regrasEstoque = new HashMap<>();
	Map<String, List<IStrategy>> regrasGraficoAnalise = new HashMap<>();
	
	/* ------------------------------------------------------------------------------------------------------------------ */
	
	/* ------------ Declaracao da Regra de Negocio Geral ------------ */
	
	Map<String, Map<String, List<IStrategy>>> regrasGeral = new HashMap<>();
	
	/* ------------------------------------------------------------------------------------------------------------------- */

	// CONSTRUTOR DA FACHADA
	public Fachada() {
		// Mapa dos DAO's
		daos = new HashMap<String, IDAO>();

		// Criando instancias dos DAOS a serem utilizados,
		// adicionando cada dado no MAP indexado pelo nome da classe correspondente
		daos.put(Cliente.class.getName(), new ClienteDAO());
		daos.put(Endereco.class.getName(), new EnderecoDAO());
		daos.put(CartaoCredito.class.getName(), new CartaoCreditoDAO());
		daos.put(Usuario.class.getName(), new LoginDAO());
		daos.put(Produto.class.getName(), new ProdutoDAO());
		daos.put(DetalheProduto.class.getName(), new DetalheProdutoDAO());
		daos.put(Carrinho.class.getName(), new CarrinhoDAO());
		daos.put(Pedido.class.getName(), new PedidoDAO());
		daos.put(Cupom.class.getName(), new CupomDAO());
		daos.put(VerificaCupom.class.getName(), new VerificaCupomDAO());
		daos.put(PedidoTroca.class.getName(), new PedidoTrocaDAO());
		daos.put(Estoque.class.getName(), new EstoqueDAO());
		daos.put(GraficoAnalise.class.getName(), new GraficoAnaliseDAO());
		
			
	/* ---------------------------------------------------------------------------------------------------------------- */
		
		/* ----- Adicionando as Strategy's na lista do CLIENTE ----- */
		
		/* ----- SALVAR ----- */
		regrasSalvarCliente.add(vNome);
		regrasSalvarCliente.add(vCPF);
		regrasSalvarCliente.add(vEmail);
		regrasSalvarCliente.add(vExisteEmail);
		regrasSalvarCliente.add(vSenha);
		regrasSalvarCliente.add(vDt_nasc);
		regrasSalvarCliente.add(vTelefone);
		regrasSalvarCliente.add(vGenero);
		
		/* ------ ALTERAR -------- */
		regrasAlterarCliente.add(vNomeAlterado);
		regrasAlterarCliente.add(vEmailAlterado);
		regrasAlterarCliente.add(vSenhaConfSenhaIgual);
		regrasAlterarCliente.add(vDtNascimentoAlterado);
		regrasAlterarCliente.add(vTelefone);
		regrasAlterarCliente.add(vGeneroAlterado);
		
		/* ---------------------------------------------------------------------------------------------------------------- */

		
		/* ----- Adicionando as Strategy's na lista do ENDERE�O ----- */
		
		/* ----- SALVAR ----- */
		regrasSalvarEndereco.add(vLogradouro);
		regrasSalvarEndereco.add(vNumero);
		regrasSalvarEndereco.add(vBairro);
		regrasSalvarEndereco.add(vCidade);
		regrasSalvarEndereco.add(vEstado);
		regrasSalvarEndereco.add(vPais);
		regrasSalvarEndereco.add(vTipoResidencia);
		
		/* ----- ALTERAR ----- */
		regrasAlterarEndereco.add(vCEPAlterado);
		regrasAlterarEndereco.add(vLogradouroAlterado);
		regrasAlterarEndereco.add(vNumAlterado);
		regrasAlterarEndereco.add(vBairroAlterado);
		regrasAlterarEndereco.add(vCidadeAlterado);
		regrasAlterarEndereco.add(vEstadoAlterado);
		regrasAlterarEndereco.add(vPaisAlterado);
		regrasAlterarEndereco.add(vTipoResidenciaAlterado);
		
		/* --------------------------------------------------------------------------------------------------------------- */
		
		/* --- Adicionando as Strategy's do CART�O DE CR�DITO ------- */
		
		/* ----- SALVAR ----- */
		regrasSalvarCartaoCredito.add(vNumCartao);
		regrasSalvarCartaoCredito.add(vBandeiraCartao);
		
		/* ------------------------------------------------------------------------------------------------------------ */
		
		/* ----- Adicionando as Strategy's na lista do LOGIN ----- */
		/* ----- SALVAR ----- */
		regrasSalvarLogin.add(vExisteEmailSenha);
		
		/* ----- CONSULTAR ----- */
		regrasConsultarLogin.add(vExisteEmailSenha);
		regrasConsultarLogin.add(vSenhaLogin); 
		
		/* -------------------------------------------------------------------------------------------------------------- */
		
		/* ----- Adicionando as Strategy's na lista do PEDIDO ----- */
		/* -------- SALVAR ------- */
		regrasSalvarPedido.add(vTotalPedido);
		regrasSalvarPedido.add(vEnderecoPedido);
		regrasSalvarPedido.add(vFormaDePagamentoPedido);
		regrasSalvarPedido.add(vCartaoPedido);
		regrasSalvarPedido.add(vCupomBonificacaoPedido);
		regrasSalvarPedido.add(vStatusPedido); 
		
		/* --------------------------------------------------------------------------------------------------------------- */
		
		/* ----- Adicionando as Strategy's na lista do CUPOM ----- */
		/* ----- CONSULTAR ----- */
		regrasConsultarVerificaCupom.add(vCupom);
		
		/* --------------------------------------------------------------------------------------------------------------- */
		
		/* ----- Adicionando as Strategy's na lista do ESTOQUE ----- */
		/* ----- SALVAR ----- */
		regrasSalvarEstoque.add(vProdutoEstoque);
		regrasSalvarEstoque.add(vTipoEstoque);
		regrasSalvarEstoque.add(vQuantidadeEstoque);
		regrasSalvarEstoque.add(vValorCustoEstoque);
		//regrasSalvarEstoque.add(vFornecedorEstoque);
		regrasSalvarEstoque.add(vDataEntradaSaidaEstoque);
		regrasSalvarEstoque.add(vEntradaEstoque);
		regrasSalvarEstoque.add(vSaidaEstoque);
		
		/* ----- CONSULTAR ----- */
		regrasConsultarEstoque.add(vProdutoEstoque);
		
		/* --------------------------------------------------------------------------------------------------------------- */
		
		/* ----- Adicionando as Strategy's na lista do GR�FICO ----- */
		/* ----- CONSULTAR ----- */
		regrasConsultarGraficoAnalise.add(vDatasGraficoAnalise);
		
		/* --------------------------------------------------------------------------------------------------------------- */
		
		/* ----- Adicionando as Strategy's na lista do Carrinho ----- */
		/* ----- SALVAR ----- */
		regrasSalvarCarrinho.add(vQuantidadeSelecionada);
		/* ----- ALTERAR ----- */
		regrasAlterarCarrinho.add(vQuantidadeSelecionada);
		/* ---------------------------------------------------------- */
		
		
		/* ----- REGRAS DA ENTIDADE CLIENTE ----- */
		/* ----- SALVAR ----- */
		regrasCliente.put("SALVAR", regrasSalvarCliente);
		/* ----- CONSULTAR ----- */
		regrasCliente.put("CONSULTAR", regrasConsultarCliente);
		/* ----- ALTERAR ----- */
		regrasCliente.put("ALTERAR", regrasAlterarCliente);
		/* ----- EXCLUIR ----- */
		regrasCliente.put("EXCLUIR", regrasExcluirCliente);
		/* --------------------------------------------------------------------------------------------------------------- */

		/* ----- REGRAS DA ENTIDADE ENDERE�O ----- */
		/* ----- SALVAR ----- */
		regrasEndereco.put("SALVAR", regrasSalvarEndereco);
		/* ----- CONSULTAR ----- */
		regrasEndereco.put("CONSULTAR", regrasConsultarEndereco);
		/* ----- ALTERAR ----- */
		regrasEndereco.put("ALTERAR", regrasAlterarEndereco);
		/* ----- EXCLUIR ----- */
		regrasEndereco.put("EXCLUIR", regrasExcluirEndereco);
		/* --------------------------------------------------------------------------------------------------------------- */
		
		/* ----- REGRAS DA ENTIDADE CARTAO DE CREDITO ----- */
		/* ----- SALVAR ----- */
		regrasCartaoCredito.put("SALVAR", regrasSalvarCartaoCredito);
		/* ----- CONSULTAR ----- */
		regrasCartaoCredito.put("CONSULTAR", regrasConsultarCartaoCredito);
		/* ----- ALTERAR ----- */
		regrasCartaoCredito.put("ALTERAR", regrasAlterarCartaoCredito);
		/* ----- EXCLUIR ----- */
		regrasCartaoCredito.put("EXCLUIR", regrasExcluirCartaoCredito);
		/* --------------------------------------------------------------------------------------------------------------- */
		
		/* ----- REGRAS DA ENTIDADE LOGIN ----- */
		/* ----- SALVAR ----- */
		regrasLogin.put("SALVAR", regrasSalvarLogin);
		/* ----- CONSULTAR ----- */
		regrasLogin.put("CONSULTAR", regrasConsultarLogin);
		/* ----- ALTERAR ----- */
		regrasLogin.put("ALTERAR", regrasAlterarLogin);
		/* ----- EXCLUIR ----- */
		regrasLogin.put("EXCLUIR", regrasExcluirLogin);
		/* --------------------------------------------------------------------------------------------------------------- */
		
		/* ----- REGRAS DA ENTIDADE PRODUTO ----- */
		/* ----- SALVAR ----- */
		regrasProduto.put("SALVAR", regrasSalvarProduto);
		/* ----- CONSULTAR ----- */
		regrasProduto.put("CONSULTAR", regrasConsultarProduto);
		/* ----- ALTERAR ----- */
		regrasProduto.put("ALTERAR", regrasAlterarProduto);
		/* ----- EXCLUIR ----- */
		regrasProduto.put("EXCLUIR", regrasExcluirProduto);
		/* -------------------------------------------------------------------------------------------------------------- */
		
		/* ----- REGRAS DA ENTIDADE DETALHE DO PRODUTO ----- */
		/* ----- SALVAR ----- */
		regrasDetalheProduto.put("SALVAR", regrasSalvarDetalheProduto);
		
		/* -------------------------------------------------------------------------------------------------------------- */
		/* ----- REGRAS DA ENTIDADE CARRINHO ----- */
		/* ----- SALVAR ----- */
		regrasCarrinho.put("SALVAR", regrasSalvarCarrinho);
		/* ----- CONSULTAR ----- */
		regrasCarrinho.put("CONSULTAR", regrasConsultarCarrinho);
		/* ----- ALTERAR ----- */
		regrasCarrinho.put("ALTERAR", regrasAlterarCarrinho);
		/* ----- EXCLUIR ----- */
		regrasCarrinho.put("EXCLUIR", regrasExcluirCarrinho);
		/* -------------------------------------------------------------------------------------------------------------- */
		
		/* ----- REGRAS DA ENTIDADE PEDIDO ----- */
		/* ----- SALVAR ----- */
		regrasPedido.put("SALVAR", regrasSalvarPedido);
		/* ----- CONSULTAR ----- */
		regrasPedido.put("CONSULTAR", regrasConsultarPedido);
		/* ----- ALTERAR ----- */
		regrasPedido.put("ALTERAR", regrasAlterarPedido);
		/* ----- EXCLUIR ----- */
		regrasPedido.put("EXCLUIR", regrasExcluirPedido);
		/* -------------------------------------------------------------------------------------------------------------- */
		
		/* ----- REGRAS DA ENTIDADE VERIFICA CUPOM ----- */
		regrasVerificaCupom.put("CONSULTAR", regrasConsultarVerificaCupom);
		
		/* -------------------------------------------------------------------------------------------------------------- */
		
		/* ----- REGRAS DA ENTIDADE CUPOM  ----- */
		/* ----- SALVAR ----- */
		regrasCupom.put("SALVAR", regrasSalvarCupom);
		/* ----- CONSULTAR ----- */
		regrasCupom.put("CONSULTAR", regrasConsultarCupom);
		/* ----- ALTERAR ----- */
		regrasCupom.put("ALTERAR", regrasAlterarCupom);
		/* ----- EXCLUIR ----- */
		regrasCupom.put("EXCLUIR", regrasExcluirCupom);
		/* -------------------------------------------------------------------------------------------------------------- */
		
		/* ----- REGRAS DA ENTIDADE PEDIDO TROCA ----- */
		/* ----- SALVAR ----- */
		regrasPedidoTroca.put("SALVAR", regrasSalvarPedidoTroca);
		/* ----- CONSULTAR ----- */
		regrasPedidoTroca.put("CONSULTAR", regrasConsultarPedidoTroca);
		/* ----- ALTERAR ----- */
		regrasPedidoTroca.put("ALTERAR", regrasAlterarPedidoTroca);
		/* ----- EXCLUIR ----- */
		regrasPedidoTroca.put("EXCLUIR", regrasExcluirPedidoTroca);
		/* ------------------------------------------------------------------------------------------------------------- */
		
		
		/* ----- REGRAS DA ENTIDADE ESTOQUE ----- */
		/* ----- SALVAR ----- */
		regrasEstoque.put("SALVAR", regrasSalvarEstoque);
		/* ----- CONSULTAR ----- */
		regrasEstoque.put("CONSULTAR", regrasConsultarEstoque);
		/* ----- ALTERAR ----- */
		regrasEstoque.put("ALTERAR", regrasAlterarEstoque);
		/* ----- EXCLUIR ----- */
		regrasEstoque.put("EXCLUIR", regrasExcluirEstoque);
		/* ----------------------------------------------------------------------------------------------------------- */
		
		/* ----- Adicionando as Strategy's na lista do Grafico Analise ----- */
		/* ----- CONSULTAR ----- */
		regrasGraficoAnalise.put("CONSULTAR", regrasConsultarGraficoAnalise);
		/* ---------------------------------------------------------- */
		/* ------------------------------------------------------------------------------------------------------------ */
		
		
		/* -------------- REGRAS GERAIS ----------- */
		regrasGeral.put(Cliente.class.getName(), regrasCliente);
		regrasGeral.put(Endereco.class.getName(), regrasEndereco);
		regrasGeral.put(CartaoCredito.class.getName(), regrasCartaoCredito);
		regrasGeral.put(Usuario.class.getName(), regrasLogin);
		regrasGeral.put(Produto.class.getName(), regrasProduto);
		regrasGeral.put(DetalheProduto.class.getName(), regrasDetalheProduto);
		regrasGeral.put(Carrinho.class.getName(), regrasCarrinho);
		regrasGeral.put(Pedido.class.getName(), regrasPedido);
		regrasGeral.put(VerificaCupom.class.getName(), regrasVerificaCupom);
		regrasGeral.put(PedidoTroca.class.getName(), regrasPedidoTroca);
		regrasGeral.put(Estoque.class.getName(), regrasEstoque);
		regrasGeral.put(Cupom.class.getName(), regrasCupom);
		regrasGeral.put(GraficoAnalise.class.getName(), regrasGraficoAnalise);
		
		
		/* ------------------------------------------------------------------------------------------------------------ */
	}

	/*------- SALVAR -------*/
	@Override
	public Resultado salvar(EntidadeDominio entidade) {
		resultado = new Resultado();
		// retornar o nome do pacote com o nome da classe desta entidade de dominio
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "SALVAR");

		if (msg == null || msg == "") {
			// Obt�m o DAO correspondente ao nome do pacote com o nome da classe,
			// que esta dentro do HashMap do "daos"
			IDAO dao = daos.get(nmClasse);
			try {
				dao.salvar(entidade);

				// cria uma lista para mostrar os salvos
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (Exception e) {
				e.printStackTrace();
				resultado.setMensagem("N�o foi poss�vel Salvar o registro!");
			}
		} else {
			resultado.setMensagem(msg);
		}
		return resultado;
	}

	/*--------- ALTERAR -----------*/
	@Override
	public Resultado alterar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "ALTERAR");

		if (msg == null || msg == "") {
			IDAO dao = daos.get(nmClasse);
			try {
				dao.alterar(entidade);

				// cria uma lista para mostrar os alterados
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (Exception e) {
				e.printStackTrace();
				resultado.setMensagem("N�o foi poss�vel Alterar o registro!");
			}
		} else {
			resultado.setMensagem(msg);
		}
		return resultado;
	}

	/*--------- EXCLUIR --------*/
	@Override
	public Resultado excluir(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "EXCLUIR");

		if (msg == null || msg == "") {
			IDAO dao = daos.get(nmClasse);
			try {
				dao.excluir(entidade);

				// cria uma lista para mostrar os excluidos
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (Exception e) {
				e.printStackTrace();
				resultado.setMensagem("N�o foi poss�vel Excluir o registro!");
			}
		} else {
			resultado.setMensagem(msg);
		}
		return resultado;
	}

	/*--------- CONSULTAR ---------*/
	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "CONSULTAR");

		if (msg == null || msg == "") {
			IDAO dao = daos.get(nmClasse);
			try {
				resultado.setEntidades(dao.consultar(entidade));
			} catch (Exception e) {
				e.printStackTrace();
				resultado.setMensagem("Nao foi possivel Consultar o registro!");
			}
		} else {
			resultado.setMensagem(msg);
		}
		return resultado;
	}

	
	/** METODO PARA EXECUTAR AS STRATEGYS - MENSAGEM DE SUCESSO / ERRO **/
	private String executarRegras(EntidadeDominio entidade, String operacao) {
		String msg = "";

		// verifica o nome da classe para pegar o MAP de "regrasGeral"
		String nmClasse = entidade.getClass().getName();
		
		// com o nome da classe, ele pega o MAP com as suas respectivas regras de dominio (exemplo: regrasCliente),
		Map<String, List<IStrategy>> regrasDaEntidade = regrasGeral.get(nmClasse);
		
		// depois ele pega a "opera��o"que deseja ser realizada (exemplo: "salvar")
		List<IStrategy> regrasDaOperacao = regrasDaEntidade.get(operacao);
		
		// para cada "regra" em "regrasDaOperacao", ele ir� chamar as Strategy's 
		// que estiver dentro da lista (exemplo: regrasSalvarCliente)
		for (IStrategy regra : regrasDaOperacao) {
			String resultado = regra.validar(entidade);
			if (resultado != null) {
				msg += " " + resultado + "<br>";
			}
		}

		return msg;
	}
}
