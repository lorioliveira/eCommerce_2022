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
      <link href="../img/favicon.ico" rel="icon">
      <link href="../css/reset.css" rel="stylesheet">
     
      <!-- Google Fonts -->
      <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400|Source+Code+Pro:700,900&display=swap" rel="stylesheet">
      
      <!-- Biblioteca CSS - Bootstrap-->
      <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
      <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
      <link href="../lib/slick/slick.css" rel="stylesheet">
      <link href="../lib/slick/slick-theme.css" rel="stylesheet">
     
      <!-- CSS Principal do Projeto -->
      <link href="../css/style.css" rel="stylesheet">
   </head>
    	<%
	      Usuario usuarioLogado = new Usuario();
	      
	      HttpSession sessao = request.getSession();
	      usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
	      
	      //pega todos produtos que estao na sessao
	      List<Produto> produtos = (List<Produto>)sessao.getAttribute("todosProdutos");
	      
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
                     
                     <div class="ml-autonavbar-collapse justify-content-between">Olá ${usuarioLogado.nome}</div>

                     <div class="navbar-nav ml-auto">
                         <div class="nav-item dropdown">
                              <a href="" class="nav-link dropdown-toggle" data-toggle="dropdown">Minha Conta</a>
                             <div class="dropdown-menu">
                                 <a href="../JSP/minhaConta.jsp" class="dropdown-item">Meu Perfil</a>
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
      <!-- Fim da faixa de menu -  faixa rosa contendo home, produtos e minha conta -->      
      <!-- Inicio da div contendo logo, barra de pesquisa e botão Minha Sacola-->
      <div class="bottom-bar">
         <div class="container-fluid">
            <div class="row align-items-center">
               <div class="col-md-3">
                  <!-- LOGO -->
                  <div class="logo">
                     <a href="../JSP/index.jsp">
                     <img src="../img/mir.svg" alt="Logo Mirror Fashion" >
                     </a>
                  </div>
               </div>
               <!-- BARRA DE PESQUISA -->
               <div class="col-md-6">
                  <div class="search">
                     <input type="text" placeholder="Procuro por ...">
                     <button><i class="fa fa-search"></i></button>
                  </div>
               </div>
               <!-- MINHA SACOLA -->
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
      <!--- Fim da div contendo logo, barra de pesquisa e botão Minha Sacola -->       
      <!-- Início do MENU LATERAL - CATEGORIA -->
      <div class="header">
         <div class="container-fluid">
            <div class="row">
               <div class="col-md-3">
                  <nav class="navbar bg-light">
                     <ul class="navbar-nav">
                        <li class="nav-item">
                           <a class="nav-link" href="#"><i class="fas fa-tshirt"></i>Blusas</a>
                        </li>
                        <li class="nav-item">
                           <a class="nav-link" href="#"><i class="fa fa-archway"></i>Calças</a>
                        </li>
                        <li class="nav-item">
                           <a class="nav-link" href="#"><i class="fa fa-female"></i>Vestidos</a>
                        </li>
                        <li class="nav-item">
                           <a class="nav-link" href="#"><i class="fa fa-gem"></i>Acessórios</a>
                        </li>
                     </ul>
                  </nav>
               </div>
               <!-- Início do Banner de Imagens -->
               <div class="col-md-7">
                  <div class="header-slider normal-slider">
                     <div class="header-slider-item">
                        <img src="../img/banner1.png" alt="Slider Image" />
                     </div>
                     <div class="header-slider-item">
                        <img src="../img/cupom_promocional.png" alt="Slider Image" />
                     </div>
                     <!-- Acessorios -->
                     <div class="header-slider-item">
                        <a href="#">
                           <img src="../img/3.png" alt="Slider Image" />
                        </a>
                     </div>
                     <!-- <div class="header-slider-item">
                        <img src="../img/banner2.png" alt="Slider Image" />
                     </div> -->
                  </div>
               </div>
               <!-- Fim do banner de Imagens -->
            </div>
         </div>
      </div>
      <!-- Fim do MENU LATERAL - CATEGORIA -->
      <!-- Secão - Nossos Produtos -->
      <div class="featured-product product">
         <div class="container-fluid">
            <div class="section-header">
               <h1>Nossos Produtos</h1>
            </div>
            
            <div class="row align-items-center product-slider product-slider-4">
				<%
				for (Produto p : produtos) {
			%>
			<div class="col-lg-3">
					<div class="product-item">
						<div class="product-title">
							<a href="#"><%=p.getNome() %></a>
							<div class="ratting">
								<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fa fa-star"></i>
							</div>
						</div>
						<div class="product-image">
							<a href="#"><img src=".<%=p.getFoto()%>" alt="Product Image"></a>
							<div class="product-action">
								<a href="/eCommerce/detalheProduto?id=<%= p.getId()%>&operacao=SALVAR"><i class="fa fa-eye"></i></a>
							</div>
						</div>
						<div class="product-price">
							<h3>
								<span>R$</span> <%=p.getPrecoVenda() %>
							</h3>
							<a class="btn" href="/eCommerce/carrinho?idProduto=<%= p.getId()%>&quantidadeSelecionada=1&operacao=SALVAR"><i class="fa fa-shopping-bag"></i>Adicionar</a>
						</div>
					</div>
				</div>
				<%
					}
				%>
			</div>
		</div>
	</div>
      <!-- Featured Product End -->       

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
       <!-- Fim do Footer -->

      <!-- Footer Bottom Start -->
      <div class="footer-bottom">
         <div class="container">
             <div class="row">
                 <div class="col-md-6 copyright">
                     <p>Copyright &copy; <a href="../JSP/index.jsp">Mirror Fashion</a> - 2021 - Todos os direitos reservados</p>
                 </div>

                 <!-- <div class="col-md-6 template-by">
                     <p>Template By <a href="https://htmlcodex.com">HTML Codex</a></p>
                 </div> -->
             </div>
         </div>
     </div>
     <!-- Footer Bottom End -->  

      <!-- Voltar pro topo -->
      <a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>
      <!-- Bibliotecas JavaScript  -->
      <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
      <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
      <script src="../lib/easing/easing.min.js"></script>
      <script src="../lib/slick/slick.min.js"></script>
      <!--  Javascript -->
      <script src="../js/main.js"></script>
   </body>
</html>