<%@page import='com.les.roupa.core.dominio.*'%>

<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-BR">
    <head>
      <meta charset="utf-8">
      <title>[Admin] Mirror Fashion</title>
      <meta content="width=device-width, initial-scale=1.0" name="viewport">
      <meta content="eCommerce HTML Template Free Download" name="keywords">
      <meta content="eCommerce HTML Template Free Download" name="description">

      <!-- Favicon -->
      <link href="./img/favicon.ico" rel="icon">

      <!-- Google Fonts -->
      <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400|Source+Code+Pro:700,900&display=swap" rel="stylesheet">

      <!-- Biblioteca CSS - Bootstrap -->
      <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
      <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
      <link href="lib/slick/slick.css" rel="stylesheet">
      <link href="lib/slick/slick-theme.css" rel="stylesheet">

      <!-- CSS Principal do Projeto -->
      <link href="./css/style.css" rel="stylesheet">
    </head>
    
    <% 
	    	    
	    //pega o pedido a ser visualizado
		Pedido pedidoSelecionado = (Pedido)request.getAttribute("pedidoSelecionado");
	    
		Endereco enderecoDoPedidoSelecionado = (Endereco)request.getAttribute("enderecoDoPedidoSelecionado");
		
		List<ItemPedido> itensPedidoSelecionado = (List<ItemPedido>)request.getAttribute("itensPedidoSelecionado");
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
                            <a href="./JSP/indexAdm.jsp" class="nav-item nav-link">Home</a>
                        </div>
                        <div class="navbar-nav ml-auto">
                         <div class="nav-item dropdown">
                              <a href="" class="nav-link dropdown-toggle" data-toggle="dropdown">Minha Conta</a>
                             <div class="dropdown-menu">
                                    <a href="" class="dropdown-item">
                                       <!-- BOTAO SAIR -->
		                                <form action="http://localhost:8080/eCommerce/login">
		                                    <button type="submit" class="btn" name="operacao" value="EXCLUIR"><i class="fa fa-sign-out-alt"></i>Logout</button>
		                                </form>
                                    </a>
                             </div>
                         </div>
                     </div>
                    </div>
                </nav>
            </div>
        </div>
        <!-- Fim da faixa de menu -  faixa rosa contendo home e minha conta-->
    
    
        <!-- Inicio da div contendo logo, barra de pesquisa  -->
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
                        <div class="search">
                            <!-- <input type="text" placeholder="Procuro por ...">
                            <button><i class="fa fa-search"></i></button> -->
                        </div>
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
        <!-- Fim da div contendo logo, barra de pesquisa  -->   
        
        
        <!-- Inicio do Breadcrumb -->
        <div class="breadcrumb-wrap">
            <div class="container-fluid">
                <ul class="breadcrumb">
                    <li class="breadcrumb-item"><a href="./JSP/indexAdm.jsp">Minha Conta</a></li>
                    <li class="breadcrumb-item active">Detalhe do Pedido</li>
                </ul>
            </div>
        </div>
       <!-- Fim do Breadcrumb -->
        
        <!-- Inicio dos dados do Pedido -->
        <div class="registrar__novaconta">
            <div class="container-novaconta">
                <div class="col-lg-9">   
                    <div class="register-form">
                        <h4>Detalhe do Pedido nº <%=pedidoSelecionado.getId() %> - Data: <%=pedidoSelecionado.getData_Cadastro() %> </h4><br>
                            <div class="row">
                                <div class="col-md-4">
                                   <label><b>Total Produtos</b>:</label>
                                    <span><i> R$ <%=pedidoSelecionado.getTotalItens() %></i></span>
                                    <br>
                                    <label><b>Frete</b>:</label>
                                    <span><i> R$ <%=pedidoSelecionado.getTotalFrete() %></i></span>
                                    <br>
                                    <label><b>Descontos</b>:</label>
                                    <span class="valorDesconto"><i> R$ <%=pedidoSelecionado.getTotalCupons() %></i></span>
                                    <br>
                                    <label><b>Total</b>:</label>
                                    <span><i>R$ <%=pedidoSelecionado.getTotalPedido() %></i></span>
                                </div>
                                <div class="col-md-4 formaPagamento_centralizado">
                                    <label><b>Cartao</b></label>
                                    <p><i><%=pedidoSelecionado.getIdCartao1() %></i></p>
                                    <p><i><%=pedidoSelecionado.getIdCartao2() %></i></p>
                                    
                                   <!--  <label><b>Cupom</b></label>
                                    <p>promocional10</p> -->
                                </div>
                                
                                <div class="col-md-4 endereco_aDireita">
                                    <label><b>Entrega</b></label>
                                    <p><i><%=pedidoSelecionado.getEndereco().getLogradouro() %>, <%=pedidoSelecionado.getEndereco().getNumero() %></i></p>
                                    <p><i><%=pedidoSelecionado.getEndereco().getCidade() %> - <%=pedidoSelecionado.getEndereco().getEstado() %></i></p>
                                    <p><i>CEP <%=pedidoSelecionado.getEndereco().getCep() %></i></p>
                                </div>
                            </div>
                        </div>
                    </div>
            <!-- Fim dos dados do Pedido -->

                     <!-- Inicio do Pedido - TABELA -->
                    <div class="col-lg-9">
                        <div class="login-form">
                            <div class="row div__novaconta">
                                <div class="col-md-12">
                                    <table class="table table-bordered">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>Produto</th>
                                                <th>R$ Valor Produto</th>
                                                <th>Qtde.</th>
                                                <th>Status</th>
                                                <th>Ação</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>Blusa Amarela</td>
                                                <td>29</td>
                                                <td>1</td>
                                                <td class="centrarlizarStatus_BtnAcao" rowspan="5"><%=pedidoSelecionado.getStatus() %></td>
                                               <td class="centrarlizarStatus_BtnAcao" rowspan="7">
                                                   <a href="/eCommerce/cadastroPedido?id=<%= pedidoSelecionado.getId()%>&operacao=CONSULTAR"><button class="btn"> <i class="fa fa-edit"></i> Alterar Status</button></a>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                             <!-- ID do Produto -->
				<input type="hidden" name="idPedido" id="idPedido" value="<%=pedidoSelecionado.getId() %>">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Fim do Login -->
        
        <!-- Footer Start -->
        <div class="footer">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-3 col-md-6">
                        <div class="footer-widget">
                            <h2>Contato</h2>
                            <div class="contact-info">
                                <p><i class="fa fa-map-marker"></i>Mogi das Cruzes - SP</p>
                                <p><i class="fa fa-envelope"></i>Lorena Oliveira </p>
                                <p><i class="fa fa-phone"></i>+55 11 91234-5678</p>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-lg-3 col-md-6">
                        
                    </div>

                    <div class="col-lg-3 col-md-6">
                        <div class="footer-widget">
                            <h2>Siga-nos</h2>
                            <div class="contact-info">
                                <div class="social">
                                    <a href=""><i class="fab fa-twitter"></i></a>
                                    <a href=""><i class="fab fa-facebook-f"></i></a>
                                    <a href=""><i class="fab fa-linkedin-in"></i></a>
                                    <a href=""><i class="fab fa-instagram"></i></a>
                                    <a href=""><i class="fab fa-youtube"></i></a>
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
                            <img src="./img/payment-method.png" alt="Forma de pagamento" />
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="payment-security">
                            <h2>Compre com segurança</h2>
                            <img src="./img/godaddy.svg" alt="Segurança" />
                            <img src="./img/norton.svg" alt="Segurança" />
                            <img src="./img/ssl.svg" alt="Segurança" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer End -->
        
        <!-- Footer Bottom Start -->
        <div class="footer-bottom">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 copyright">
                        <p>Copyright &copy; <a href="./JSP/indexAdm.jsp">Mirror Fashion</a> - 2021 - Todos os direitos reservados</p>
                    </div>

                    <!-- <div class="col-md-6 template-by">
                        <p>Template By <a href="https://htmlcodex.com">HTML Codex</a></p>
                    </div> -->
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
        
        <!-- Template Javascript -->
        <script src="./js/main.js"></script>
    </body>
</html>