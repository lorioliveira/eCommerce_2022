<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-BR">

	<head>
	    <meta charset="utf-8">
	    <title>[Admin] Mirror Fashion</title>
	    <meta content="width=device-width, initial-scale=1.0" name="viewport">
	
	    <!-- Favicon -->
	    <link href="./img/favicon.ico" rel="icon">
	
	    <!-- Google Fonts -->
	    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400|Source+Code+Pro:700,900&display=swap"
	        rel="stylesheet">
	
	    <!-- Biblioteca CSS - Bootstrap -->
	    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
	    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
	    <link href="./lib/slick/slick.css" rel="stylesheet">
	    <link href="./lib/slick/slick-theme.css" rel="stylesheet">
	
	    <!-- CSS Principal do Projeto -->
	    <link href="./css/style.css" rel="stylesheet">
	</head>

    <%
    Usuario usuarioLogado = new Usuario();
    
    // Pega o usuário em sessão - Cliente logado -> admin
    HttpSession sessao = request.getSession();
    usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
    
    //pega todos os clientes 
    List<Cliente> clientes = (List<Cliente>)sessao.getAttribute("todosClientes");
    
  	//pega todos produtos 
    List<Produto> produtos = (List<Produto>)sessao.getAttribute("todosProdutos");
    
 	 //pega todos os pedidos
    List<Pedido> pedidos = (List<Pedido>)sessao.getAttribute("todosPedidos");
 	 
 	 //pega todos os cupons
    List<Cupom> cupons = (List<Cupom>)sessao.getAttribute("todosCupons"); 
    
 	// pega a mensagem que estava pendurado na requisição e enviado pelo VH "ClienteHelper"
    String mensagemStrategy = (String)request.getAttribute("mensagemStrategy");
 	
 	
	/***************** GRAFICO **********************/
		
	// Listas da coluna e das linhas do gráfico
	List<String> totalColunasChart = new ArrayList<>();
	List<String> totalProduto1Chart = new ArrayList<>();
	List<String> totalProduto2Chart = new ArrayList<>();
	List<String> totalProduto3Chart = new ArrayList<>();
	
	// Data Inicio que estava pendurado na requisição, enviado pelo VH do Gráfico
	String dtInicio = (String)request.getAttribute("dtInicio");
	
	// Data Fim que estava pendurado na requisição, enviado pelo VH do Gráfico
	String dtFim = (String)request.getAttribute("dtFim");
	
	// separa o dia-mês-ano que foi selecionado na tela e exibe de forma correta na tela
	String[] resultInicio  = dtInicio.split("-");
	String[] resultFim  = dtFim.split("-");
			
	// pega o nome do primeiro produto, enviado pelo VH do Gráfico
	String nomeProduto1 = (String)request.getAttribute("nomeProduto1");
	
	// pega o total de valores do produto1, enviado pelo VH do Gráfico
	totalProduto1Chart = (List<String>)request.getAttribute("totalValorProduto1");
	
	
	// pega o nome do segundo produto, enviado pelo VH do Gráfico
	String nomeProduto2 = (String)request.getAttribute("nomeProduto2");
	
	// pega o total de valores do produto2, enviado pelo VH do Gráfico
	totalProduto2Chart = (List<String>)request.getAttribute("totalValorProduto2");
	
	
	// pega o nome do terceiro produto, enviado pelo VH do Gráfico
	String nomeProduto3 = (String)request.getAttribute("nomeProduto3");
	
	// pega o total de valores do produto3, enviado pelo VH do Gráfico
	totalProduto3Chart = (List<String>)request.getAttribute("totalValorProduto3");
	
	
	// pega o total de colunas, enviado pelo VH do Gráfico
	totalColunasChart = (List<String>)request.getAttribute("totalColunas");
	
    %>

	<body onload="AtivaGrafico()">
	    <!-- Inicio da faixa superior - Faixa preta contendo email e telefone de "suporte"-->
	    <div class="top-bar">
	        <div class="container-fluid">
	            <div class="row">
	                <div class="col-sm-6">
	                    <i class="fa fa-envelope"></i>
	                    suporte@mirrorfashion.com
	                </div>
	                <div class="col-sm-6">
	                    <i class="fa fa-phone-alt"></i>
	                    +55 11 91234-5678
	                </div>
	            </div>
	        </div>
	    </div>
	    <!-- Fim da faixa superior - Faixa preta contendo email e telefone de "suporte"-->
	
	
	    <!-- Inicio da faixa de menu -  faixa rosa contendo home e minha conta -->
	    <div class="nav">
	        <div class="container-fluid">
	            <nav class="navbar navbar-expand-md bg-dark navbar-dark">
	                <a href="#" class="navbar-brand">MENU</a>
	                <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
	                    <span class="navbar-toggler-icon"></span>
	                </button>
	
	                <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
	                    <div class="navbar-nav mr-auto">
	                        <a href="./JSP/indexAdm.jsp" class="nav-item nav-link">Home</a>
	                    </div>
	                    <div class="navbar-nav ml-auto">
	                    </div>
	                </div>
	            </nav>
	        </div>
	    </div>
	    <!-- Fim da faixa de menu -  faixa rosa contendo home e minha conta-->
	
	
	    <!-- Inicio da div contendo logo, barra de pesquisa e nome do Admin -->
	    <div class="bottom-bar">
	        <div class="container-fluid">
	            <div class="row align-items-center">
	                <div class="col-md-3">
	                    <div class="logo">
	                        <a href="./JSP/indexAdm.jsp">
	                            <img src="./img/mir.svg" alt="Logo Mirror Fashion">
	                        </a>
	                    </div>
	                </div>
	                 <div class="col-md-6">
	                </div> 
	                <div class="col-md-2">
	                    <div class="user">
	                        <h6>
	                            <div class="ml-autonavbar-collapse justify-content-between">Olá ${usuarioLogado.nome}</div>
	                        </h6>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	    <!-- Fim da div contendo logo, barra de pesquisa e nome do Admin -->
	
		<!-- Inicio do Breadcrumb -->
	     <div class="breadcrumb-wrap">
	         <div class="container-fluid">
	             <ul class="breadcrumb">
	                 <li class="breadcrumb-item active">Minha Conta</li>
	             </ul>
	         </div>
	     </div>
	     <!-- Fim do Breadcrumb -->
	
	    <!-- Inicio da Secão do Administrador  -->
	    <div class="my-account">
	        <div class="container-fluid">
	            <div class="row">
	                <div class="col-md-2">
	                
	                    <!-- MENU DO ADMIN -->
	                    <div class="nav flex-column nav-pills" role="tablist" aria-orientation="vertical">
	                        <a class="nav-link " id="dashboard-nav" data-toggle="pill" href="#dashboard-tab" role="tab"><i class="fa fa-caret-square-down"></i>Menu</a>
	                        <a class="nav-link" id="clients-nav" data-toggle="pill" href="#clients-tab" role="tab"><i class="fas fa-user-friends"></i>Clientes</a>
	                        <a class="nav-link" id="orders-nav" data-toggle="pill" href="#orders-tab" role="tab"><i class="fa fa-receipt"></i> Pedidos</a>
	                        <a class="nav-link" id="products-nav" data-toggle="pill" href="#products-tab" role="tab"><i class="fa fa-barcode"></i> Produtos</a>
	                        <a class="nav-link" id="coupons-nav" data-toggle="pill" href="#coupons-tab" role="tab"><i class="fa fa-tags"></i>Cupons</a>
	                        <a class="nav-link active" id="sales-nav" data-toggle="pill" href="#sales-tab" role="tab"><i class="fa fa-chart-line"></i>Análise de Vendas</a>
	                   </div>
						<!-- BOTAO SAIR -->
						<form action="http://localhost:8080/eCommerce/login">
							<button type="submit" class="btn col-md-12 btnLogoutAdmin" name="operacao" value="EXCLUIR"><i class="fa fa-sign-out-alt"></i>Logout</button>
						</form>
	                </div>
	                <div class="col-md-10">
	                    <div class="tab-content">
	                        <div class="tab-pane fade" id="dashboard-tab" role="tabpanel"
	                            aria-labelledby="dashboard-nav">
	                            <h4>Links</h4>
	                            <br>
	                            <p>
	                                <i class="fa fa-arrow-left"></i> Navegue pelas opções no menu lateral esquerdo
	                            </p>
	                        </div>
	
	                        <!-- CLIENTES -->
	                        <div class="tab-pane fade" id="clients-tab" role="tabpanel" aria-labelledby="clients-nav">
	                        <h4><i class="fas fa-user-friends"></i> Lista de Clientes</h4><br>
	                            <div class="row">
	                            <table class="table table-bordered">
	                                <thead class="thead-dark">
	                                    <tr>
	                                        <th>Nome</th>
	                                        <th>CPF</th>
	                                        <th>Dt. Nascimento</th>
	                                        <th>Celular</th>
	                                        <th>e-mail</th>
	                                        <th>Status</th>
	                                        <th>Ação</th>
	                                    </tr>
	                                </thead>
	                                
	                                <%
	                                	for(Cliente client : clientes){
		
		                                    // Aplicado o CAST para poder popular o cliente
		                                    Cliente c = (Cliente) client;
		                                    //Pega o usuario que esta dentro do cliente
		                                    Usuario u = c.getUsuario();
	                           		 %>
	                                <tbody>
	                                    <tr>
	                                        <td><%=c.getNome() %></td>
	                                        <td><%=c.getCpf() %></td>
	                                        <td><%=c.getData_Nascimento() %></td>
	                                        <td><%=c.getTelefone() %></td>
	                                        <td><%=u.getEmail() %></td>
	                                        <td><%=c.getStatus() %></td>
	                                        <td><a href="/eCommerce/cadastro?id=<%=c.getId()%>&operacao=ALTERAR&alteraCliente=0"><button class="btn" data-tooltip="Editar" data-flow="bottom"><i class="fa fa-edit" ></i></button></a></td>
	                                    </tr>
	                                </tbody>
	                                <%
	                                }
	                                %>
	                            </table>
	                            </div>
	                        </div>
	
	                        <!-- PEDIDOS  -->
	                        <div class="tab-pane fade" id="orders-tab" role="tabpanel" aria-labelledby="orders-nav">
	                            <div class="table-responsive">
	                                <h4><i class="fa fa-receipt"></i> Lista de Pedidos</h4><br>
	                                <table class="table table-bordered">
	                                    <thead class="thead-dark">
	                                        <tr>
	                                            <th>Nº Pedido</th>
	                                            <th>Cliente</th>
	                                            <th>Valor</th>
	                                            <th>Status</th>
	                                            <th class="thAcao">Ação</th>
	                                        </tr>
	                                    </thead>
	                                    <%
			                              	for(Pedido pedido : pedidos) {
			                              %>
	                                    <tbody>
	                                        <tr>
	                                            <td><%=pedido.getId() %></td>
	                                            <td><%=pedido.getIdCliente() %></td>
	                                            <td>R$ <%=pedido.getTotalPedido() %></td>
	                                            <td><%=pedido.getStatus() %></td>
	                                            <td>
	                                            	
	                        <form action="http://localhost:8080/eCommerce/pedidoTroca" class="tabelaPedido">
							<div class="form-row">
								<div class="form-group col-md-7">
									<label>Selecione o status:</label>
	
						  			<select name="alterarStatusPedido" class="form-control borderSelect" placeholder="Selecione um Status" required>
								      	<option value="" disabled selected>Selecione uma opção...</option>
								      	<option value="EM PROCESSAMENTO">EM PROCESSAMENTO</option>
								      	<option value="PAGAMENTO REALIZADO">PAGAMENTO REALIZADO</option>
								      	<option value="EM TRANSPORTE">EM TRANSPORTE</option>
								      	<option value="TROCA SOLICITADA">TROCA SOLICITADA</option>
								      	<option value="TROCA AUTORIZADA">TROCA AUTORIZADA</option>
								      	<option value="TROCA REJEITADA">TROCA REJEITADA</option>
								      	<option value="TROCA ACEITA">TROCA ACEITA</option>
								      	<option value="TROCA EFETUADA"><b>TROCA EFETUADA</b></option>
								      	<option value="CANCELAMENTO SOLICITADO">CANCELAMENTO SOLICITADO</option>
								      	<option value="CANCELAMENTO REJEITADA">CANCELAMENTO REJEITADA</option>
								      	<option value="CANCELAMENTO ACEITO">CANCELAMENTO ACEITO</option>
								      	<option value="CANCELAMENTO EFETUADO"><b>CANCELAMENTO EFETUADO</b></option>
								      	<option value="ENTREGA REALIZADA">ENTREGA REALIZADA</option>
							      	</select>
								</div>
								
								<!-- Botão ALTERAR - Status-->
								<div class="form-group col-md-4">
									<div style="margin-top: 29px;">
										<button class="btn btn-warning" name="operacao" value="ALTERAR">Alterar</button>
									</div>
								</div>
							</div>
							
							<!-- ID do Pedido -->
				    		<input type="hidden" name="idPedido" id="idPedido" value="<%=pedido.getId() %>">
				    		<!-- ID do Cliente -->
				    		<input type="hidden" name="idCliente" id="idCliente" value="<%=pedido.getIdCliente() %>">
				    		<!-- Total do Pedido -->
				    		<input type="hidden" name="totalPedido" id="totalPedido" value="<%=pedido.getTotalPedido() %>">
						</form>
	                                            	
	                                            </td>
	                                        </tr>
	                                    </tbody>
	                                    <%
			                              	}
	                                    %>
	                                </table>
	                            </div>
	                        </div>
	
	                        <!-- PRODUTOS -->
	                        <div class="tab-pane fade" id="products-tab" role="tabpanel" aria-labelledby="products-nav">
	                            <div class="table-responsive">
	                                <h4><i class="fa fa-barcode"></i> Lista de Produtos</h4>
	                                <a href="./JSP/estoque.jsp"><button class="btn btnEstoque nav-link"><i class="fa fa-warehouse"></i> Consultar Estoque</button></a>
	                                <br>
	                                <div class="col-md-12">
	                                    <table class="table table-bordered">
	                                        <thead class="thead-dark">
	                                            <tr>
	                                                <th>Nome</th>
	                                                <th>Categoria</th>
	                                                <th>Cor</th>
	                                                <th>R$ Compra</th>
	                                                <th>R$ Venda</th>
	                                                <th>Dt. Cadastro</th>
	                                                <th>Qtde</th>
	                                                <th>Status</th>
	                                                <th>Grupo Preço</th>
	                                                <th>Ação</th>
	                                            </tr>
	                                        </thead>
	                                        <%
												for (Produto p : produtos) {
											%>
	                                        <tbody>
	                                            <tr>
	                                                <td><%=p.getNome() %></td>
	                                                <td><%=p.getCategoria() %></td>
	                                                <td><%=p.getCores() %></td>
	                                                <td><%=p.getPrecoCompra() %></td>
	                                                <td><%=p.getPrecoVenda()%></td>
	                                                <td><%=p.getData_Cadastro() %></td>
	                                                <td><%=p.getQtdeEstoque() %></td>
	                                                <td><%=p.getStatus() %></td>
	                                                <td><%=p.getGrupoPrecificacao() %></td>
	                                                <td><a href="/eCommerce/produto?id=<%= p.getId()%>&alteraProduto=0&operacao=ALTERAR"><button class="btn" data-tooltip="Editar" data-flow="top"><i class="fa fa-edit"></i></button></a></td>
	                                            </tr>
	                                        </tbody>
	                                        <%
												}
	                                        %>
	                                    </table>
	                                </div>
	                            </div>
	                        </div>
	
	                        <!-- CUPONS  -->
	                        <div class="tab-pane fade" id="coupons-tab" role="tabpanel" aria-labelledby="coupons-nav">
	                            <h4><i class="fa fa-tags"></i> Lista de Cupons </h4>
	                            <a href="./JSP/novoCupom.jsp"><button class="btn btnCupom nav-link"><i class="fa fa-tags"></i> Novo</button></a>
	                            </br>
	                            <div class="col-md-12">
	                                <table class="table table-bordered">
	                                    <thead class="thead-dark">
	                                        <tr>
	                                            <th>Nome</th>
	                                            <th>Tipo Cupom</th>
	                                            <th>Valor R$</th>
	                                            <th>Dt. Cadastro</th>
	                                            <th>Cliente</th>
	                                            <th>Utilizado?</th>
	                                            <th>Ação</th>
	                                        </tr>
	                                    </thead>
	                                    
	                                    <%
			                              	for(Cupom cupom : cupons) {
			                             %>
	                                    <tbody>
	                                        <tr>
	                                            <td><%=cupom.getNome() %></td>
	                                            <td><%=cupom.getTipo() %></td>
	                                            <td>R$ <%=cupom.getValor() %></td>
	                                            <td><%=cupom.getData_Cadastro() %></td>
	                                            <td><%=cupom.getIdCliente()%></td>
	                                            <td><%=cupom.getUtilizado() %></td>
	                                            <td>
	                                                <a href="/eCommerce/cupom?id=<%= cupom.getId()%>&alteraCupom=0&operacao=ALTERAR"><button class="btn" data-tooltip="Editar" data-flow="bottom"><i class="fa fa-edit"></i></button></a>
	                                                <a href="/eCommerce/cupom?id=<%= cupom.getId()%>&operacao=EXCLUIR"><button class="btn" data-tooltip="Excluir" data-flow="bottom"><i class="fa fa-eraser"></i></button></a>
	                                            </td>
	                                        </tr>
	                                    </tbody>
	                                    <%
			                              	}
	                                    %>
	                                </table>
	                            </div>
	                        </div>
	
	                        <!-- ANALISE DE VENDAS -->
	                        <div class="tab-pane fade show active" id="sales-tab" role="tabpanel" aria-labelledby="sales-nav">
	                            <h4><i class="fa fa-chart-line"></i> Análise de Vendas - Produtos </h4>
	                            </br>
	                            <div class="row">
	                                <div class="col-md-7">
		                                <label>Selecione o período:<br> <i>(OBS.: Vendas filtradas por mês)</i></label>
										<form action="http://localhost:8080/eCommerce/graficoAnalise">
											<div class="form-row">
												<div class="col-md-4">
												<label>De</label>
											  		<!-- Data Inicio -->
											      	
											      	<input type="date" class="form-control" name="dtInicio" required>
										  		</div>
										  		
										  		<div class="col-md-4">
										  		<label>Até</label>
											  		<!-- Data Fim -->
											      	<input type="date" class="form-control" name="dtFim" required>
										  		</div>
												
												<div class="col-md-4 btnGerarGrafico">
											  		<button class="btn" name="operacao" value="CONSULTAR"><i class="fa fa-chart-area"></i> Gerar</button>
											  	</div>
										  		<br>
									  		</div>
								  		</form>							  		
	                                </div>
	                            </div>
					             <!-- Total de colunas (meses pesquisados) no Chart.js -->
						  		<select style="display: none" id="ColunasChart">
									<% 
										for(String coluna : totalColunasChart) {
										      	
										// lista todas as colunas dentro do "value", de cada TAG "<option>".
									%>
									<option value="<%=coluna%>"><%=coluna%></option>
									<%
										}
									%>
								</select>
								
								<!-- Total de valores do Produto1 (rosa) no Chart.js -->
						  		<select style="display: none" id="Produto1Chart">
									<% 
										for(String valor : totalProduto1Chart) {
										      	
									// lista todos os valores de Produto1
									%>
									<option value="<%=valor%>"><%=valor%></option>
									<%
										}
									%>
								</select>
								
								<!-- Total de valores do Produto2 (amarelo) no Chart.js -->
						  		<select style="display: none" id="Produto2Chart">
									<% 
										for(String valor : totalProduto2Chart) {
										      	
									// lista todos os valores de Produto2
									%>
									<option value="<%=valor%>"><%=valor%></option>
									<%
										}
									%>
								</select>
								
								<!-- Total de valores do Produto3 (azul) no Chart.js -->
						  		<select style="display: none" id="Produto3Chart">
									<% 
										for(String valor : totalProduto3Chart) {
										      	
									// lista todos os valores de Produto3
									%>
									<option value="<%=valor%>"><%=valor%></option>
									<%
										}
									%>
								</select>
						  		
						  		<!-- Nomes dos Produtos para enviar ao chart.js -->
					            <input type="hidden" name="nomeProduto1" id="nomeProduto1" value="<%=nomeProduto1 %>">
					            <input type="hidden" name="nomeProduto2" id="nomeProduto2" value="<%=nomeProduto2 %>">
					            <input type="hidden" name="nomeProduto3" id="nomeProduto3" value="<%=nomeProduto3%>">
							
								<hr>
								
								<!--  GRÁFICO  -->
								
								<!-- Título do Gráfico com as datas pesquisadas -->
								<h5 style="text-align: center;">Período consultado: <%=resultInicio[2] %>/<%=resultInicio[1] %>/<%=resultInicio[0] %> até <%=resultFim[2] %>/<%=resultFim[1] %>/<%=resultFim[0] %></h5>
								
								<!-- Tamanho do Grafico gerado pelo Chart.js -->
								<div style="width: 75%; height: 80%; margin-left: 170px;">
									<canvas id="myChart"></canvas>
								</div>
								<!-- Fim Grafico gerado pelo Chart.js -->
	                            
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	    <!-- Fim da Secão do Administrador  -->
	
	    <!-- Início do Footer -->
	    <div class="footer">
	        <div class="container-fluid">
	            <div class="row">
	                <div class="col-lg-3 col-md-6">
	                    <div class="footer-widget">
	                        <h2>Contato</h2>
	                        <div class="contact-info">
	                            <p><i class="fa fa-map-marker"></i>Mogi das Cruzes - SP</p>
	                            <p><i class="fa fa-envelope"></i>Lorena Oliveira</p>
	                            <p><i class="fa fa-phone"></i>+55 11 91234-5678</p>
	                        </div>
	                    </div>
	                </div>
	
	                <div class="col-lg-3 col-md-6">
	                    <!--VAZIO PARA DAR ESPAÇO ENTRE DADOS E REDES SOCIAIS  -->
	                </div>
	
	                <div class="col-lg-3 col-md-6">
	                    <div class="footer-widget">
	                        <h2>Siga-nos</h2>
	                        <div class="contact-info">
	                            <div class="social">
	                                <a href="#"><i class="fab fa-twitter"></i></a>
	                                <a href="#"><i class="fab fa-facebook-f"></i></a>
	                                <a href="#"><i class="fab fa-linkedin-in"></i></a>
	                                <a href="#"><i class="fab fa-instagram"></i></a>
	                                <a href="#"><i class="fab fa-youtube"></i></a>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	
	                <div class="col-lg-3 col-md-6">
	                    <div class="footer-widget">
	                        <h2>Infos</h2>
	                        <ul>
	                            <li><a href="#">Sobre nós</a></li>
	                            <li><a href="#">Política de Privacidade</a></li>
	                            <li><a href="#">Termos & Condições</a></li>
	                        </ul>
	                    </div>
	                </div>
	            </div>
	
	            <div class="row payment align-items-center">
	                <div class="col-md-6">
	                    <div class="payment-method">
	                        <h2>Forma de pagamento</h2>
	                        <img src="./img/payment-method.png" alt="Forma de Pagamento" />
	                    </div>
	                </div>
	                <div class="col-md-6">
	                    <div class="payment-security">
	                        <h2>Compre com segurança</h2>
	                        <img src="./img/godaddy.svg" alt="Payment Security" />
	                        <img src="./img/norton.svg" alt="Payment Security" />
	                        <img src="./img/ssl.svg" alt="Payment Security" />
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	    <!-- Fim do Footer -->
	
	    <!-- Footer Bottom Start -->
	    <div class="footer-bottom">
	        <div class="container">
	            <div class="row">
	                <div class="col-md-6 copyright">
	                    <p>Copyright &copy; <a href="./JSP/indexAdm.jsp">Mirror Fashion</a> - 2022 - Todos os direitos reservados
	                    </p>
	                </div>
	            </div>
	        </div>
	    </div>
	    <!-- Footer Bottom End -->
	
	    <!-- Back to Top -->
	    <a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>
	
	    <!-- JavaScript Libraries -->
	    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
	    <script src="./lib/easing/easing.min.js"></script>
	    <script src="./lib/slick/slick.min.js"></script>
	
	    <!--  Javascript -->
	    <script src="./js/main.js"></script>
	    <script src="./js/all.js"></script>
	    
	    <!-- Javascript - Início da chamada do Gráfico -->
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.5.1/chart.min.js" integrity="sha512-Wt1bJGtlnMtGP0dqNFH1xlkLBNpEodaiQ8ZN5JLA5wpc1sUlk/O5uuOMNgvzddzkpvZ9GLyYNa8w2s7rqiTk5Q==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	    <script src="./js/chart.js"></script>
	  	  
	  	  <!-- Botão para chamar o grafico do Chart.js -->
		  <button style="display: none" id="idGrafico" onclick="graficoChart()"></button>
	  	  
	  	  
	  	  <script>
	 	  // Função que irá ativar o grafico do Chart.js,
		  // essa função é carregada junto ao carregamento da página com o evento ONLOAD, dentro da tag <body>.
			  function AtivaGrafico(){
			  	// metodo para poder ativar o "onClick" sem precisar clicar no botão
			   	document.getElementById('idGrafico').click();
			  }
		  </script>
		  <!-- Fim da Chamada do Gráfico -->
	    
	</body>

</html>