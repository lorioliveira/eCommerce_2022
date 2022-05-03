package com.les.roupa.view.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.les.roupa.view.command.ICommand;
import com.les.roupa.view.helper.IViewHelper;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Resultado;
import com.les.roupa.view.command.impl.AlterarCommand;
import com.les.roupa.view.command.impl.ConsultarCommand;
import com.les.roupa.view.command.impl.ExcluirCommand;
import com.les.roupa.view.command.impl.SalvarCommand;
import com.les.roupa.view.helper.impl.CarrinhoHelper;
import com.les.roupa.view.helper.impl.CartaoCreditoHelper;
import com.les.roupa.view.helper.impl.ClienteHelper;
import com.les.roupa.view.helper.impl.CupomHelper;
import com.les.roupa.view.helper.impl.DetalheProdutoHelper;
import com.les.roupa.view.helper.impl.EnderecoHelper;
import com.les.roupa.view.helper.impl.EstoqueHelper;
import com.les.roupa.view.helper.impl.LoginHelper;
import com.les.roupa.view.helper.impl.PedidoHelper;
import com.les.roupa.view.helper.impl.PedidoTrocaHelper;
import com.les.roupa.view.helper.impl.ProdutoHelper;
import com.les.roupa.view.helper.impl.VerificaCupomHelper;

/**
 * Responsavel por processar todas as requisicoes feita pelo usuario,
 * configurado conforme o arquivo web.xml
 * @author Lorena Oliveira
 */
public class ControllerServlet extends HttpServlet {
	
	private static Map<String, IViewHelper> viewHelper;
	private static Map<String, ICommand> commands;
	
	// Construtor que inicializa os caminhos da URL
	public ControllerServlet() {
		
		// Mapa dos Commands de cada operacao
		commands = new HashMap<String, ICommand>();
		
		commands.put("SALVAR", new SalvarCommand());
		commands.put("EXCLUIR", new ExcluirCommand());
		commands.put("CONSULTAR", new ConsultarCommand());
		commands.put("ALTERAR", new AlterarCommand());
		
		
		// Mapa das Views
		viewHelper = new HashMap<String, IViewHelper>();
		
		viewHelper.put("/eCommerce/cadastro", new ClienteHelper());
		viewHelper.put("/eCommerce/cadastroEndereco", new EnderecoHelper());
		viewHelper.put("/eCommerce/login", new LoginHelper());
		viewHelper.put("/eCommerce/cartao", new CartaoCreditoHelper());
		viewHelper.put("/eCommerce/produto", new ProdutoHelper());
		viewHelper.put("/eCommerce/detalheProduto", new DetalheProdutoHelper());
		viewHelper.put("/eCommerce/carrinho", new CarrinhoHelper());
		viewHelper.put("/eCommerce/cupom", new CupomHelper());
		viewHelper.put("/eCommerce/cadastroPedido", new PedidoHelper());
		viewHelper.put("/eCommerce/verificaCupom", new VerificaCupomHelper());
		viewHelper.put("/eCommerce/pedidoTroca", new PedidoTrocaHelper());
		viewHelper.put("/eCommerce/cadastroEstoque", new EstoqueHelper());
	}
	
	// Servlet Principal do sistema
	protected void processRequest (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
        // para aceitar acentuacao
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        // Obtem a operacao que sera executada
        String operacao = request.getParameter("operacao");
        
        // Obtem a uri que invocou esta servlet
        String uri = request.getRequestURI();
        
        //Obtem uma viewhelper indexado pela uri que invocou esta servlet
        IViewHelper vh = viewHelper.get(uri);
        
        // O View Helper retorna a entidade especifica para a tela que chamou esta servlet
        EntidadeDominio entidade = vh.getEntidade(request);
        
        // Recupera o command correspondente com a operacao
        ICommand command = commands.get(operacao);
        
        // Executa o command que chamaria a fachada para executar a operacao requisitada
        // o retorno  pode conter mensagens de erro
        // ou entidades de retorno
        Resultado resultado = command.execute(entidade);
        
        // Executa o metodo setView do view helper especifico para definir como deveria ser apresentado
        // o resultado para o usuario
        vh.setView(resultado, request, response);
	}
	
	// Method doGet
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		processRequest(request, response);
	}
	
	// Method doPost 
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		processRequest(request, response);
	}
	
}
