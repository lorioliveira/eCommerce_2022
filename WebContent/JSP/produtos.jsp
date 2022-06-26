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
    <link
      href="https://fonts.googleapis.com/css?family=Open+Sans:300,400|Source+Code+Pro:700,900&display=swap"
      rel="stylesheet">
    <!-- Biblioteca CSS - Bootstrap-->
    <link
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
      rel="stylesheet">
    <link
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
      rel="stylesheet">
    <link href="../lib/slick/slick.css" rel="stylesheet">
    <link href="../lib/slick/slick-theme.css" rel="stylesheet">
    <!--- Biblioteca CSS - Principal-->
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
            <i class="fa fa-envelope"></i> suporte@mirrorfashion.com
          </div>
          <div class="col-sm-6">
            <i class="fa fa-phone-alt"></i> +55 11 91234-5678
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
          <button type="button" class="navbar-toggler" data-toggle="collapse"
            data-target="#navbarCollapse">
          <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse justify-content-between"
            id="navbarCollapse">
            <div class="navbar-nav mr-auto">
              <a href="../JSP/index.jsp" class="nav-item nav-link">Home</a> <a
                href="../JSP/produtos.jsp" class="nav-item nav-link active">Produtos</a>
            </div>
            <div class="ml-autonavbar-collapse justify-content-between">Olá
              ${usuarioLogado.nome}
            </div>
            <div class="navbar-nav ml-auto">
              <div class="nav-item dropdown">
                <a href="" class="nav-link dropdown-toggle"
                  data-toggle="dropdown">Minha Conta</a>
                <div class="dropdown-menu">
                  <a href="../JSP/minhaConta.jsp" class="dropdown-item">Meu
                  Perfil</a> 
                  <a href="" class="dropdown-item">
                    <!-- BOTAO SAIR -->
                    <form action="http://localhost:8080/eCommerce/login">
                      <button type="submit" class="btn" name="operacao"
                        value="EXCLUIR">
                      <i class="fa fa-sign-out-alt"></i>Logout
                      </button>
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
            <div class="logo">
              <a href="../JSP/index.jsp"> <img src="../img/mir.svg"
                alt="Logo Mirror Fashion">
              </a>
            </div>
          </div>
          <div class="col-md-6">
            <!-- <div class="eyefa-eye">
              <input type="text" placeholder="Procuro por ...">
              <button><i class="fa fa-eye"></i></button>
              </div> -->
          </div>
          <div class="col-md-2">
            <div class="user">
              <a href="../JSP/carrinho.jsp" class="btn cart"> Minha Sacola <i
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
          <li class="breadcrumb-item"><a href="../JSP/index.jsp">Home</a></li>
          <li class="breadcrumb-item active">Produtos</li>
        </ul>
      </div>
    </div>
    <!-- Fim do Breadcrumb -->
    <!-- Inicio da Listagem de Produtos -->
    <div class="product-view">
      <div class="container-fluid">
        <div class="row">
          <div class="col-lg-8">
            <div class="row">
              <div class="col-md-12">
                <div class="product-view-top">
                  <div class="row">
                    <div class="col-md-5">
                      <h3>Produtos</h3>
                      <div class="product-search search">
                      </div>
                    </div>
                    <!-- Inicio da parte em branco usado como espaçamento entre
                      barra de pesquisa e filtro  -->
                    <div class="col-md-4">
                      <div class="product-short">
                        <div class="dropdown"></div>
                      </div>
                    </div>
                    <!-- Fim da parte em branco usado como espaçamento entre
                      barra de pesquisa e filtro  -->
                    <div class="col-md-3">
                      <div class="product-short">
                        <div class="dropdown">
                          <div class="dropdown-toggle" data-toggle="dropdown">Selecione a categoria
                          </div>
                          <div class="dropdown-menu dropdown-menu-right categoria fade">
                            <a href="#" class="dropdown-item"><i class="fas fa-tshirt"></i> Blusas</a> 
                            <a href="#" class="dropdown-item"><i class="fa fa-archway"></i> Calças</a> 
                            <a href="#" class="dropdown-item"><i class="fa fa-female"></i> Vestidos</a>
                            <a href="#" class="dropdown-item"><i class="fa fa-gem"></i> Acessorios</a>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <%
                for(Produto p : produtos){
                %>
              <div class="col-md-4">
                <div class="product-item">
                  <!-- Produto -->
                  <div class="product-title">
                    <a
                      href="/eCommerce/detalheProduto?id=<%= p.getId()%>&operacao=SALVAR"><%=p.getNome() %></a>
                    <div class="ratting">
                      <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
                        class="fa fa-star"></i> <i class="fa fa-star"></i> <i
                        class="fa fa-star"></i>
                    </div>
                  </div>
                  <div class="product-image">
                    <img src=".<%=p.getFoto()%>" alt="Foto do Produto">
                    <div class="product-action">
                      <a
                        href="/eCommerce/detalheProduto?id=<%= p.getId()%>&operacao=SALVAR"><i
                        class="fa fa-eye"></i></a>
                    </div>
                  </div>
                  <div class="product-price">
                    <h3>
                      <span>R$</span>
                      <%=p.getPrecoVenda() %>
                    </h3>
                  </div>
                </div>
              </div>
              <%
                }
                %>
            </div>
            <!-- Inicio de Páginação - indisponível no momento -->
            <div class="col-md-12">
              <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                  <li class="page-item disabled"><a class="page-link"
                    href="#" tabindex="-1">Anterior</a></li>
                  <li class="page-item active"><a class="page-link" href="#">1</a></li>
                  <li class="page-item"><a class="page-link" href="#">2</a></li>
                  <li class="page-item"><a class="page-link" href="#">3</a></li>
                  <li class="page-item"><a class="page-link" href="#">Próximo</a>
                  </li>
                </ul>
              </nav>
            </div>
            <!-- Fim da Páginação -->
          </div>
          <!-- Inicio do Menu de Categoria - Que vou por imagem apenas para preencher -->
          <div class="col-lg-4 sidebar">
            
            <div class="feature">
              <div class="container-fluid">
                <div class="row align-items-center">
					<div class="col-md-12 feature-col">
						<div class="feature-content">
							<i class="fab fa-cc-mastercard"></i>
							<h2>Pagamento Seguro</h2>
							<p>Pague com seu cartão de crédito ou no boleto</p>
						</div>
					</div>
					<div class="col-lg-12"></div>
					<div class="col-md-12 feature-col">
						<div class="feature-content">
							<i class="fa fa-truck"></i>
							<h2>Entrega Rápida</h2>
							<p>Enviamos seu pedido para qualquer lugar que deseja</p>
						</div>
					</div>
					<div class="col-lg-12"></div>
					<div class="col-md-12 feature-col">
						<div class="feature-content">
							<i class="fa fa-sync-alt"></i>
							<h2>Troca Rápida em 24h</h2>
							<p>Comprou e viu algo melhor? Entre em contato para realizar sua troca</p>
						</div>
					</div>
					<div class="col-lg-12"></div>
					<div class="col-md-12 feature-col">
						<div class="feature-content">
							<i class="fa fa-comments"></i>
							<h2>Suporte 24/7</h2>
							<p>Prestamos todo o suporte que necessite em nossos canais de atendimento</p>
						</div>
					</div>
				</div>
              </div>
            </div>
          </div>
          <!-- Fim do Menu de Categoria -->
          <!-- OBSERVAÇÃO : PARA FAZER AS PROXIMAS PÃGINAS DE PRODUTOS, PODE USAR A MESMA IDEIA DO MENU CLICAVEL DA 'MINHA CONTA'
            VINCULAR O NUMERO COM O BOTAO, ASSIM, AO CLICAR NO 2 VAI "CARREGAR" OUTROS PRODUTOS E ASSIM POR DIANTE .... FIZ ISSO COM O ESTOQUE -->
        </div>
      </div>
    </div>
    <!-- Fim da Listagem de Produtos -->
    <!-- Brand Start -->
    <!-- <div class="brand">
      <div class="container-fluid">
          <div class="brand-slider">
              <div class="brand-item"><img src="img/brand-1.png" alt=""></div>
              <div class="brand-item"><img src="img/brand-2.png" alt=""></div>
              <div class="brand-item"><img src="img/brand-3.png" alt=""></div>
              <div class="brand-item"><img src="img/brand-4.png" alt=""></div>
              <div class="brand-item"><img src="img/brand-5.png" alt=""></div>
              <div class="brand-item"><img src="img/brand-6.png" alt=""></div>
          </div>
      </div>
      </div> -->
    <!-- Brand End -->
    <!-- Inicio do Footer -->
    <div class="footer">
      <div class="container-fluid">
        <div class="row">
          <div class="col-lg-3 col-md-6">
            <div class="footer-widget">
              <h2>Contato</h2>
              <div class="contact-info">
                <p>
                  <i class="fa fa-map-marker"></i>Mogi das Cruzes - SP
                </p>
                <p>
                  <i class="fa fa-envelope"></i>Lorena Oliveira
                </p>
                <p>
                  <i class="fa fa-phone"></i>+55 11 91234-5678
                </p>
              </div>
            </div>
          </div>
          <div class="col-lg-3 col-md-6"></div>
          <div class="col-lg-3 col-md-6">
            <div class="footer-widget">
              <h2>Siga-nos</h2>
              <div class="contact-info">
                <div class="social">
                  <a href=""><i class="fab fa-twitter"></i></a> <a href=""><i
                    class="fab fa-facebook-f"></i></a> <a href=""><i
                    class="fab fa-linkedin-in"></i></a> <a href=""><i
                    class="fab fa-instagram"></i></a> <a href=""><i
                    class="fab fa-youtube"></i></a>
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
              <img src="../img/payment-method.png" alt="Forma de pagamento" />
            </div>
          </div>
          <div class="col-md-6">
            <div class="payment-security">
              <h2>Compre com segurança</h2>
              <img src="../img/godaddy.svg" alt="Segurança" /> <img
                src="../img/norton.svg" alt="Segurança" /> <img
                src="../img/ssl.svg" alt="Segurança" />
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
            <p>
              Copyright &copy; <a href="../JSP/index.jsp">Mirror Fashion</a> -
              2022 - Todos os direitos reservados
            </p>
          </div>
        </div>
      </div>
    </div>
    <!-- Fim da faixa Footer -->
    <!-- Voltar pro topo -->
    <a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>
    <!-- Bibliotecas JavaScript  -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
    <script src="../lib/easing/easing.min.js"></script>
    <script src="../lib/slick/slick.min.js"></script>
    <!--  Javascript -->
    <script src="../js/main.js"></script>
  </body>
</html>