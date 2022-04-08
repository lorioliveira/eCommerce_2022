
<%@page import='com.les.roupa.core.dominio.*'%>

<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <title>Mirror Fashion</title>
        
       	<meta charset="utf-8">
       	
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="eCommerce HTML Template Free Download" name="keywords">
        <meta content="eCommerce HTML Template Free Download" name="description">	

        <!-- Favicon -->
        <link rel="shortcut icon" href="../favicon.ico">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400|Source+Code+Pro:700,900&display=swap" rel="stylesheet">

        <!-- Biblioteca CSS - Bootstrap -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="../lib/slick/slick.css" rel="stylesheet">
        <link href="../lib/slick/slick-theme.css" rel="stylesheet">

        <!-- CSS Principal do Projeto -->
        <link href="../css/style.css" rel="stylesheet">
    </head>

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
                    <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                        <div class="navbar-nav mr-auto">
                            <a href="login.jsp" class="nav-item nav-link active">Home</a>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
       <!-- Fim da faixa de menu -  faixa rosa contendo home, produtos e minha conta -->        
        
        <!-- Inicio da div contendo logo, barra de pesquisa e botÃ£o Minha Sacola-->
        <div class="bottom-bar">
            <div class="container-fluid">
            <div class="row align-items-center">
                <div class="col-md-3">
                    <!-- LOGO -->
                    <div class="logo">
                        <a href="login.jsp">
                        <img src="../img/mir.svg" alt="Logo Mirror Fashion" >
                        </a>
                    </div>
                </div>
                <!-- BARRA DE PESQUISA -->
                <div class="col-md-6">
                    <div class="search">
                    </div>
                </div>
            </div>
            </div>
        </div>
        <!--- Fim da div contendo logo, barra de pesquisa e botao Minha Sacola -->       
        
        
        <!-- Inicio do Breadcrumb -->
        <div class="breadcrumb-wrap">
            <div class="container-fluid">
                <ul class="breadcrumb">
                    <li class="breadcrumb-item active">Entre ou Registre-se</li>
                </ul>
            </div>
        </div>
       <!-- Fim do Breadcrumb -->
        
        <!-- Inicio do Login -->
        <form action="http://localhost:8080/eCommerce/login" method="post">
            <div class="login">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="login-form">
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>E-mail</label>
                                        <input class="form-control" type="email" name="email" placeholder="E-mail" >
                                    </div>
                                    <div class="col-md-5">
                                        <label>Senha</label>
                                        <input class="form-control" type="password" name="senha" value="" accesskey="S" min="8" maxlength="15" placeholder="Senha">
                                    </div>
                                    
                                    <div class="col-md-12">
                                        <button type="submit" class="btn btnEntrar" name="operacao" value="CONSULTAR"><i class="fa fa-door-open"></i> Entrar</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Criar nova conta -->
                        <div class="col-lg-4">
                            <div class="login-form divNovaConta_Login">
                                <div class="row">
                                    <div class="col-md-6">
                                        <p>Ou registre-se aqui</p>
                                        <a class="btn" href="../JSP/novaConta.jsp"><i class="fa fa-user-plus"></i> Nova Conta</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
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
                        <p>Copyright &copy; <a href="login.jsp">Mirror Fashion</a> - 2022 - Todos os direitos reservados</p>
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
        
        <!-- Template Javascript -->
        <script src="../js/main.js"></script>
    </body>
</html>