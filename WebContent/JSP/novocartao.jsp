<%@page import='com.les.roupa.core.dominio.*'%>

<%@page import="java.util.List"%>  

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="UTF-8"%>	

<html lang="pt-BR">
    <head>
        <meta charset="utf-8">
        <title>Mirror Fashion</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        
        <!-- Favicon -->
        <link href="../img/favicon.ico" rel="icon">
        <link href="../css/reset.css" rel="stylesheet">
        
        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400|Source+Code+Pro:700,900&display=swap" rel="stylesheet">
        
        <!-- Biblioteca CSS - Bootstrap-->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="../lib/slick/slick.css" rel="stylesheet">
        <link href="../lib/slick/slick-theme.css" rel="stylesheet">
        
        <!--- Biblioteca CSS - Principal-->
        <link href="../css/style.css" rel="stylesheet">
            
    </head>

		<%
			Usuario usuarioLogado = new Usuario();
		
			HttpSession sessao = request.getSession();
			usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
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

        
       <!-- Inicio da faixa de menu -  faixa rosa contendo home, produtos e minha conta -->
       <div class="nav">
        <div class="container-fluid">
            <nav class="navbar navbar-expand-md bg-dark navbar-dark">
                <a href="#" class="navbar-brand">MENU</a>
                <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                    <div class="navbar-nav mr-auto">
                        <a href="../JSP/index.jsp" class="nav-item nav-link active">Home</a>
                        <a href="../JSP/produtos.jsp" class="nav-item nav-link">Produtos</a>
                    </div>
                    <div class="navbar-nav ml-auto">
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Minha Conta</a>
                            <div class="dropdown-menu">
                                <a href="../JSP/login.jsp" class="dropdown-item">Logout</a>
                            </div>
                        </div>
                    </div>
                </div>
            </nav>
        </div>
    </div>
    <!-- Fim da faixa de menu -  faixa rosa contendo home, produtos e minha conta--> 
           
        
        <!-- Inicio da div contendo logo, barra de pesquisa e botão Minha Sacola -->
        <div class="bottom-bar">
            <div class="container-fluid">
                <div class="row align-items-center">
                    <div class="col-md-3">
                        <div class="logo">
                            <a href="../JSP/index.jsp">
                            <img src="../img/mir.svg" alt="Logo Mirror Fashion" >
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
                            <a href="../JSP/carrinho.jsp" class="btn cart">
                            Minha Sacola <i class="fas fa-shopping-bag"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Fim da div contendo logo, barra de pesquisa e botão Minha Sacola -->
        
        
        <!-- Inicio do Breadcrumb -->
        <div class="breadcrumb-wrap">
            <div class="container-fluid">
                <ul class="breadcrumb">
                    <li class="breadcrumb-item"><a href="../JSP/index.jsp">Home</a></li>
                    <li class="breadcrumb-item"><a href="../JSP/minhaConta.jsp">Minha Conta</a></li>
                    <li class="breadcrumb-item active">Novo Cartão</li>
                </ul>
            </div>
        </div>
        <!-- Fim do Breadcrumb -->
        
        <!-- Inicio do formulário de novo cartao -->
        <form action="http://localhost:8080/eCommerce/cartao" method="post">
	        <div class="registrar__novoCartao">
	            <div class="container-novoCartao">
	                <div class="row">
	                    <div class="col-md-9">
	                        <div class="tab-content">
	                             <div id="address-tab" role="tabpanel" aria-labelledby="address-nav">
	                                <h4>Cadastrar novo cartão</h4> <br>
	                                <div class="row">
	                                    <div class="col-md-4">
	                                        <label>Número do cartão </label>
	                                        <input class="form-control" type="text" id="cartao" name="numCartao">
	                                    </div>
	                                    <div class="col-md-3">
	                                        <label>Bandeira</label>
	                                        <select class="form-control" type="text" name="bandeira">
	                                            <option selected disabled>Selecione</option>
	                                            <option value="Visa">Visa</option>
	                                            <option value="Mastercard">Mastercard</option>
	                                            <option value="Elo">Elo</option>
	                                        </select>
	                                    </div>
	                                    <div class="col-md-2">
	                                        <label>CVV:</label>
	                                        <input class="form-control" type="number" placeholder="XXX" maxlength="3" name="cvv" >
	                                    </div>
	                                    <div class="col-md-2">
	                                        <label>Cartão Principal: </label></br>
	                                        <input type="radio" name="preferencial" value="Sim">    Sim
	                                        <input type="radio" name="preferencial" value="Nao" class="espacamento_Cartao">    Não 
	                                    </div>
	                                    <div class="col-md-5">
	                                        <label>Nome do títular</label>
	                                        <input class="form-control" type="text" placeholder="digite como consta no cartão" name="nome">
	                                    </div>
	                                    <div class="col-md-4">
	                                        <label>Validade</label>
	                                        <input class="form-control" type="month" name="validade">
	                                    </div>
	                                    <div class="col-md-6">
	                                        <button type="submit" class="btn" onclick="window.history.go(-1); return false;"><i class="fa fa-ban"></i> Cancelar</button>
	                                        <button type="submit" class="btn" name="operacao" value="SALVAR"><i class="fa fa-save"></i>  Salvar</button>
	                                    </div>
	                                     	<input type="hidden" name="alteraPreferencial" value="0"/>
                							<input type="hidden" name="idCliente" value="<%=usuarioLogado.getId()%>"/>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </form>
        <!-- Fim do formulário de novo cartao -->
        
         <!-- InÃ­cio do Footer -->
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
                        <!--VAZIO PARA DAR ESPAÃO ENTRE DADOS E REDES SOCIAIS  -->
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
                            <img src="../img/godaddy.svg" alt="segurança" />
                            <img src="../img/norton.svg" alt="segurança" />
                            <img src="../img/ssl.svg" alt="segurança" />
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
                        <p>Copyright &copy; <a href="../JSP/index.jsp">Mirror Fashion</a> - 2022 - Todos os direitos reservados</p>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer Bottom End -->       
        
        <!-- Back to Top -->
        <a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>
        
        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/../js/bootstrap.bundle.min.js"></script>
        <script src="../lib/easing/easing.min.js"></script>
        <script src="../lib/slick/slick.min.js"></script>
        
        <!--  Javascript -->
        <script src="../js/main.js"></script>
        <script src="../js/all.js"></script>
    </body>
</html>