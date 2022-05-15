
<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import='com.les.roupa.core.dao.impl.*'%>
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
    <link href="../img/favicon.ico" rel="icon">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400|Source+Code+Pro:700,900&display=swap"
        rel="stylesheet">

    <!-- Biblioteca CSS - Bootstrap -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="../lib/slick/slick.css" rel="stylesheet">
    <link href="../lib/slick/slick-theme.css" rel="stylesheet">

    <!-- CSS Principal do Projeto -->
    <link href="../css/style.css" rel="stylesheet">
</head>

        
    <%
    ClienteDAO dao = new ClienteDAO();
    Usuario usuarioLogado = new Usuario();
    
    // Pega o usuário em sessão - Cliente logado -> admin
    HttpSession sessao = request.getSession();
    usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
    // Pega o ID daquele cliente logado -> admin
    List<Cliente> client = dao.consultarClienteById(usuarioLogado.getId());
    
 	 //pega todos produtos 
    List<Produto> produtos = (List<Produto>)sessao.getAttribute("todosProdutos");
  
 	//pega todos os pedidos
    List<Pedido> pedidos = (List<Pedido>)sessao.getAttribute("todosPedidos");
 		 
    %>

<body>
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
                        <a href="../JSP/indexAdm.jsp" class="nav-item nav-link">Home</a>
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
                        <a href="../JSP/indexAdm.jsp">
                            <img src="../img/mir.svg" alt="Logo Mirror Fashion">
                        </a>
                    </div>
                </div>
                 <div class="col-md-6">
                    <!--<div class="search">
                        <input type="text" placeholder="Procuro por ...">
                        <button><i class="fa fa-search"></i></button>
                    </div>-->
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
                        <a class="nav-link active" id="dashboard-nav" data-toggle="pill" href="#dashboard-tab" role="tab"><i class="fa fa-caret-square-down"></i>Menu</a>
                        <a class="nav-link" id="clients-nav" data-toggle="pill" href="#clients-tab" role="tab"><i class="fas fa-user-friends"></i>Clientes</a>
                        <a class="nav-link" id="orders-nav" data-toggle="pill" href="#orders-tab" role="tab"><i class="fa fa-receipt"></i> Pedidos</a>
                        <a class="nav-link" id="products-nav" data-toggle="pill" href="#products-tab" role="tab"><i class="fa fa-barcode"></i> Produtos</a>
                        <a class="nav-link" id="coupons-nav" data-toggle="pill" href="#coupons-tab" role="tab"><i class="fa fa-tags"></i>Cupons</a>
                        <a class="nav-link" id="sales-nav" data-toggle="pill" href="#sales-tab" role="tab"><i class="fa fa-chart-line"></i>Análise de Vendas</a>
                   </div>
					<!-- BOTAO SAIR -->
					<form action="http://localhost:8080/eCommerce/login">
						<button type="submit" class="btn col-md-12 btnLogoutAdmin" name="operacao" value="EXCLUIR"><i class="fa fa-sign-out-alt"></i>Logout</button>
					</form>
                </div>
                <div class="col-md-10">
                    <div class="tab-content">
                        <div class="tab-pane fade show active" id="dashboard-tab" role="tabpanel"
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
                                Cliente cliente = new Cliente();
                                
                                // Listar apenas os Clientes (antes estava trazendo o próprio admin)
                                List<EntidadeDominio> clientes = dao.consultarClienteByTipo(cliente);
                                
                                for(EntidadeDominio e : clientes){

                                    // Aplicado o CAST para poder popular o cliente
                                    Cliente c = (Cliente) e;
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
                                        <td><a href="/eCommerce/cadastro?id=<%=c.getId()%>&operacao=ALTERAR&alteraCliente=0"><button class="btn" data-tooltip="Editar" data-flow="bottom"><i class="fa fa-edit"></i></button></a></td>
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
                                            <th>Ação</th>
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
                                            	
                        <form class="form_form" style="width: 500px !important;" action="http://localhost:8080/eCommerce/pedidoTroca">
						<div class="form-row">
							<div class="form-group col-md-8">
								<label>Status</label>

					  			<select name="alterarStatusPedido" class="form-control" placeholder="Selecione um Status" required>
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
							
							<!-- Botões CRUD-->
							<div class="form-group col-md-4">
								<div align="right" style="margin-top: 29px;">
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
                                <a href="../JSP/estoque.jsp"><button class="btn btnEstoque nav-link"><i class="fa fa-warehouse"></i> Consultar Estoque</button></a>
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
                            <a href="../JSP/novoCupom.jsp"><button class="btn btnCupom nav-link"><i class="fa fa-tags"></i> Novo</button></a>
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
                                    
                                    <tbody>
                                        <tr>
                                            <td>testePromo</td>
                                            <td>Promocional</td>
                                            <td>R$ 20.00</td>
                                            <td>2022-01-02</td>
                                            <td>2</td>
                                            <td>Nao</td>
                                            <td>
                                                <a href=""><button class="btn" data-tooltip="Editar" data-flow="bottom"><i class="fa fa-edit"></i></button></a>
                                                <a href="."><button class="btn" data-tooltip="Excluir" data-flow="bottom"><i class="fa fa-eraser"></i></button></a>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <!-- ANALISE DE VENDAS -->
                        <div class="tab-pane fade" id="sales-tab" role="tabpanel" aria-labelledby="sales-nav">
                            <h4><i class="fa fa-chart-line"></i> Análise de Vendas </h4>
                            </br>
                            <div class="row">
                                <div class="col-md-6">
                                   	<legend align="center"> 3 Produtos mais vendidos</legend>
									<form action="http://localhost:8080/eCommerce/graficoAnalise">
										<div class="form-row">
											<div class="form-group col-md-4">
										  		<!-- Data Inicio -->
										      	<label>Data Inicio</label>
										      	<input type="date" class="form-control" name="dtInicio" placeholder="Data Inicio" required>
									  		</div>
									  		
									  		<div class="form-group col-md-4">
										  		<!-- Data Fim -->
										      	<label>Data Fim</label>
										      	<input type="date" class="form-control" name="dtFim" placeholder="Data Fim" required>
									  		</div>
											
											<div class="form-group col-md-4">
												<div align="right" style="margin-top: 32px">
										  			<button class="btn" name="operacao" value="CONSULTAR">Gerar</button>
										  		</div>
									  		</div>
								  		</div>
							  		</form>
                                </div>
                            </div>
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
                        <img src="../img/payment-method.png" alt="Forma de Pagamento" />
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="payment-security">
                        <h2>Compre com segurança</h2>
                        <img src="../img/godaddy.svg" alt="Payment Security" />
                        <img src="../img/norton.svg" alt="Payment Security" />
                        <img src="../img/ssl.svg" alt="Payment Security" />
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
                    <p>Copyright &copy; <a href="../JSP/indexAdm.jsp">Mirror Fashion</a> - 2021 - Todos os direitos reservados
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
    <script src="../lib/easing/easing.min.js"></script>
    <script src="../lib/slick/slick.min.js"></script>

    <!--  Javascript -->
    <script src="../js/main.js"></script>
    <script src="../js/all.js"></script>
</body>

</html>