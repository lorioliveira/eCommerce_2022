<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<%@page import='com.les.roupa.core.dominio.*'%>

<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="utf-8">
        <title>[Admin] Mirror Fashion</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="eCommerce HTML Template Free Download" name="keywords">
        <meta content="eCommerce HTML Template Free Download" name="description">
    
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
    Usuario usuarioLogado = new Usuario();
    
    // Pega o usuário em sessão - Cliente logado -> admin
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
                            <a href="../JSP/indexAdm.jsp" class="nav-item nav-link">Home</a>
                        </div>
                        <div class="navbar-nav ml-auto">
                            
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
                            <a href="../JSP/indexAdm.jsp">
                                <img src="../img/mir.svg" alt="Logo Mirror Fashion">
                            </a>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <!-- <div class="search">
                            <input type="text" placeholder="Procuro por ...">
                            <button><i class="fa fa-search"></i></button>
                        </div> -->
                    </div>
                    <div class="col-md-2">
                        <div class="user">
                            <h6>
                            	<div class="ml-autonavbar-collapse justify-content-between">Olá ${usuarioLogado.nome}</div>
                            </h6>
                            <!-- <a href="cart.jsp" class="btn cart">
                                Minha Sacola <i class="fas fa-shopping-bag"></i>
                                </a> -->
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
                    <li class="breadcrumb-item"><a href="../JSP/indexAdm.jsp">Minha Conta</a></li>
                    <li class="breadcrumb-item active">Criar novo cupom</li>
                </ul>
            </div>
        </div>
       <!-- Fim do Breadcrumb -->
        
        <!-- Inicio de Registrar nova conta -->
        <div class="registrar__novaconta">
            <div class="container-novaconta">
                <div class="col-lg-10">   
                    <div class="register-form">
                        <h4>Cadastro de Cupom </h4><br>
                            <div class="row">
                            <div class="col-md-4">
                                <label>Nome</label>
                                <input class="form-control" type="text" placeholder="Nome">
                            </div>
                            <div class="col-md-4">
                                <label>Tipo Cupom</label>
                                <select class="form-control" name="grupoPrecificacao">
                                    <option selected disabled>Selecione</option>
                                    <option value="Troca">Troca</option>
                                    <option value="Devolucao">Devolução</option>
                                    <option value="Promocional">Promocional</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <label>Valor R$ </label>
                                <input class="inputValorCupom form-control" type="number" class="form-control" placeholder="000" >
                            </div>
                            <div class="col-md-5">
                                <label> Cliente Vinculado</label>
                                <select class="clienteVinculado form-control" name="clienteVinculado">
                                    <option selected disabled>Selecione</option>
                                    <option value="Nome">nome XXXXXXXXXXX</option>
                                </select>
                            </div>                            
                            <div class="col-md-9"><br>
                             <!-- btnSalvarCupom --><button type="submit" onclick="window.history.go(-1); return false;" class="btn"><i class="fa fa-ban"></i> Cancelar</button>
                            <button class="btn "><i class="fa fa-plus"></i> Criar </button>
                            </div>
                        </div> 
                    </div>
                </div>    
            </div>
        </div>
        <!-- Login End -->
        
        <!-- Footer Start -->
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
                            <h2>Forma de Pagamento</h2>
                            <img src="../img/payment-method.png" alt="Payment Method" />
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
        <!-- Footer End -->
        
        <!-- Footer Bottom Start -->
        <div class="footer-bottom">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 copyright">
                        <p>Copyright &copy; <a href="../JSP/indexAdm.jsp">Mirror Fashion</a> - 2022 - Todos os direitos reservados</p>
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
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/../js/bootstrap.bundle.min.js"></script>
        <script src="../lib/easing/easing.min.js"></script>
        <script src="../lib/slick/slick.min.js"></script>
        
        <!-- Template Javascript -->
        <script src="../js/main.js"></script>
        <script src="../js/all.js"></script>
    </body>
</html>