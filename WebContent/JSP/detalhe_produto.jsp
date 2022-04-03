<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    		pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="utf-8">
        <title>Mirror Fashion</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <!-- Favicon -->
        <link href="./img/favicon.ico" rel="icon">
        <link href="./css/reset.css" rel="stylesheet">
        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400|Source+Code+Pro:700,900&display=swap" rel="stylesheet">
        <!-- Biblioteca CSS - Bootstrap-->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="./lib/slick/slick.css" rel="stylesheet">
        <link href="./lib/slick/slick-theme.css" rel="stylesheet">
        <!--- Biblioteca CSS - Principal-->
        <link href="./css/style.css" rel="stylesheet">
    </head>
    <%
      Usuario usuarioLogado = new Usuario();
      
      HttpSession sessao = request.getSession();
      usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
      
      
    //pega o produto a ser visualizado
	  Produto produtoSelecionado = (Produto)request.getAttribute("produtoSelecionado");
    
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
                            <a href="./JSP/index.jsp" class="nav-item nav-link">Home</a>
                            <a href="./JSP/produtos.jsp" class="nav-item nav-link active">Produtos</a>
                        </div>
                        
                        <div class="ml-autonavbar-collapse justify-content-between">Olá ${usuarioLogado.nome}</div>
                        
                        <div class="navbar-nav ml-auto">
                         <div class="nav-item dropdown">
                              <a href="" class="nav-link dropdown-toggle" data-toggle="dropdown">Minha Conta</a>
                             <div class="dropdown-menu">
                                 <a href="./JSP/minhaConta.jsp" class="dropdown-item">Meu Perfil</a>
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
        <!-- Fim da faixa de menu -  faixa rosa contendo home, produtos e minha conta-->          
        
        <!-- Inicio da div contendo logo, barra de pesquisa e botão Minha Sacola -->
        <div class="bottom-bar">
            <div class="container-fluid">
                <div class="row align-items-center">
                    <div class="col-md-3">
                    <!-- LOGO -->
                        <div class="logo">
                            <a href="./JSP/index.jsp">
                            <img src="./img/mir.svg" alt="Logo Mirror Fashion" >
                            </a>
                        </div>
                    </div>
                    <!-- BARRA DE PESQUISA -->
                    <div class="col-md-6">
<!--                         <div class="search"> -->
<!--                             <input type="text" placeholder="Procuro por ..."> -->
<!--                             <button><i class="fa fa-search"></i></button> -->
<!--                         </div> -->
                    </div>
                    <!-- MINHA SACOLA -->
					<div class="col-md-2">
						<div class="user">
							<a href="./JSP/carrinho.jsp" class="btn cart"> Minha Sacola <i
								class="fas fa-shopping-bag"></i>
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
                    <li class="breadcrumb-item"><a href="./JSP/index.jsp">Home</a></li>
                    <li class="breadcrumb-item"><a href="./JSP/produtos.jsp">Produtos</a></li>
                    <li class="breadcrumb-item active">Detalhe do Produto</li>
                </ul>
            </div>
        </div>
       <!-- Fim do Breadcrumb -->
       
        
        
        <!--  Inicio do detalhe do produto -->
        <div class="product-detail">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12 align-items-center">
                        <!-- produto tomando a tela inteira-->
                        <div class="product-detail-top">
                            <div class="row detalheProduto">
                                <div class="col-md-4 espacamento_DetalheProduto">
                                    <div class="product-slider-single normal-slider">
                                        <img src="<%=produtoSelecionado.getFoto() %>" alt="Blusa Amarela">
                                        <img src="./img/blusapreta.png" alt="Blusa Preta">
                                        <img src="./img/blusaverdearmy.png" alt="Blusa Verde M.">
                                    </div>
                                    <div class="product-slider-single-nav normal-slider">
                                        <div class="slider-nav-img"><img src="./img/blusaamarela.png" alt="Blusa Amarela"> </div>
                                        <div class="slider-nav-img"><img src="./img/blusapreta.png" alt="Blusa Preta"> </div>
                                        <div class="slider-nav-img"><img src="./img/blusaverdearmy.png"  alt="Blusa Verde M."> </div>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="product-content">
                                        <div class="title">
                                            <h2><%=produtoSelecionado.getNome()%></h2>
                                        </div>
                                        <div class="ratting">
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                        </div>
                                        <div class="price">
                                            <h4>Preço:</h4>
                                            <p>R$ <%=produtoSelecionado.getPrecoVenda() %><span>R$149,00</span></p>
                                        </div>
                                        
                                        <form class="form" action="http://localhost:8080/eCommerce/carrinho">
	                                        <div class="quantity">
	                                            <h4>Qtde.:</h4>
	                                            <div class="qty">
	                                                <button class="btn-minus"><i class="fa fa-minus"></i>
	                                                </button>
	                                                
	                                                <input type="text" name="quantidadeSelecionada" onkeypress="return event.charCode >= 48 && event.charCode <= 57" maxlength="3">
	                                                
	                                                <button class="btn-plus"><i class="fa fa-plus"></i>
	                                                </button>
	                                            </div>
	                                        </div>
	                                        <div class="p-size">
	                                            <h4>Tam.:</h4>
	                                            <div class="tamanhos">
	                                                <div class="radioTam">
	                                                    <label>
	                                                        <input type="radio" name="tamanho" value="36">
	                                                        <span><%=produtoSelecionado.getTamanho() %></span>
	                                                    </label>
	                                                </div>
	                                                <div class="radioTam">
	                                                    <label>
	                                                        <input type="radio" name="tamanho" value="38">
	                                                        <span>38</span>
	                                                    </label>
	                                                </div>
	                                                <div class="radioTam">
	                                                    <label>
	                                                        <input type="radio" name="tamanho" value="40">
	                                                        <span>40</span>
	                                                    </label>
	                                                </div>
	                                            </div>
	                                        </div>
	                                        <br>
	                                        <div class="p-color">
	                                            <h4>Cores:</h4>
	                                            <div class="cores">
	                                                <div class="radioCores">
	                                                    <label>
	                                                        <input type="radio" name="cor" value="Amarela">
	                                                        <span><%=produtoSelecionado.getCores() %></span>
	                                                    </label>
	                                                </div>
	                                                <div class="radioCores">
	                                                    <label>
	                                                        <input type="radio" name="cor" value="Preta">
	                                                        <span>Preta</span>
	                                                    </label>
	                                                </div>
	                                                <div class="radioCores">
	                                                    <label>
	                                                        <input type="radio" name="cor" value="Verde Militar">
	                                                        <span>Verde Militar</span>
	                                                    </label>
	                                                </div>
	                                            </div>
	                                            <br><br>
	                                            <div class="action">
	                                                <button class="btn btnAdicionar" name="operacao" value="SALVAR"><i class="fa fa-shopping-bag"></i> Adicionar</button>
	                                            </div>
	                                        </div>
	                                        	<!-- ID do Cliente -->
												<input type="hidden" name="idProduto" id="idProduto" value="<%=produtoSelecionado.getId() %>">
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row product-detail-bottom">
                            <div class="col-lg-12">
                                <ul class="nav nav-pills nav-justified">
                                    <li class="nav-item">
                                        <a class="nav-link active" data-toggle="pill"
                                            href="#description">Descrição</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" data-toggle="pill" href="#specification">Especificação</a>
                                    </li>
                                </ul>

                                <div class="tab-content">
                                    <div id="description" class="container tab-pane active">
                                        <h4>Descrição</h4>
                                        <p>
                                            <%=produtoSelecionado.getDescricao() %>
                                        </p>
                                    </div>
                                    <div id="specification" class="container tab-pane fade">
                                        <h4>Especificação</h4>
                                        <ul>
                                            <li>Teste 1</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
         <!-- Fim do detalhe do produto -->
        
        <!-- Inicio do Footer -->
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
        <!-- Fim do Footer-->

        <!-- Inicio da faixa Footer -->
        <div class="footer-bottom">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 copyright">
                        <p>Copyright &copy; <a href="./JSP/index.jsp">Mirror Fashion</a> - 2021 - Todos os direitos reservados</p>
                    </div>
                </div>
            </div>
        </div>
        <!-- Fim da faixa Footer -->  
 
        
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
