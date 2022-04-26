<%@page import='com.les.roupa.core.dominio.*'%>

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
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400|Source+Code+Pro:700,900&display=swap"
	rel="stylesheet">

<!-- Biblioteca CSS - Bootstrap -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
	rel="stylesheet">
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
 
    // pega a mensagem que estava pendurado na requisição,
    // que foi enviado pelo arquivo "ClienteHelper"
    String mensagemStrategy = (String)request.getAttribute("mensagemStrategy");
    %>

<body onload="AtivaModal()">
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


	<!-- Inicio da faixa de menu -  faixa rosa contendo home e minha conta -->
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
						<a href="../JSP/indexAdm.jsp" class="nav-item nav-link">Home</a>
					</div>
					<div class="navbar-nav ml-auto"></div>
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
						<a href="../JSP/indexAdm.jsp"> <img src="../img/mir.svg"
							alt="Logo Mirror Fashion">
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
							<div class="ml-autonavbar-collapse justify-content-between">Olá
								${usuarioLogado.nome}</div>
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
				<li class="breadcrumb-item"><a href="../JSP/indexAdm.jsp">Minha
						Conta</a></li>
				<li class="breadcrumb-item "><a href="">Produtos</a></li>
				<li class="breadcrumb-item active">Estoque</li>
			</ul>
		</div>
	</div>
	<!-- Fim do Breadcrumb -->

	<!-- Inicio da parte de Estoque - Registro  -->
	<div class="formRegistrarEstoque">
		<div class="container-novaconta">
			<div class="col-lg-12">
				<h4>Registrar Estoque</h4>
				<br>
				<div class="row">
					<div class="col-md-3">
						<label>Produto</label> <select class="form-control"
							name="listaProduto" id="listaProduto">
							<option selected disabled>Selecione</option>
							<option value="Blusa Comprida">Blusa Comprida</option>
							<option value="Blusa Curta">Blusa Curta</option>
							<option value="Calca">Calça</option>
							<option value="Vestido">Vestido</option>
						</select>
					</div>
					<div class="col-md-3">
						<label>Tipo Estoque</label> <select
							class="tipoEstoque form-control" name="tipoEstoque"
							id="tipoEstoque">
							<option selected disabled>Selecione</option>
							<option value="Entrada">Entrada</option>
							<option value="Saida">Saída</option>
						</select>
					</div>
					<div class="col-md-4">
						<label>Qtde</label> <input class="inputQuantidade form-control"
							type="number" placeholder="000">
					</div>
					<div class="col-md-3">
						<label>Valor de Compra do Produto</label> <input
							class="inputValorCompra form-control borderSelect" type="number"
							placeholder="000">
					</div>
					<div class="col-md-3">
						<label>Data</label> <input class="form-control" min="2022-01-01" max="2022-12-31" type="date">
					</div>
					<div class="col-md-4">
						<button class="btn btnCadastrarEstoque">
							<i class="fa fa-save"></i> Salvar
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Fim da parte do Estoque - Registrar  -->

	<!-- Início da Consulta de Estoque - TABELA -->
	<div class="col-md-9 divConsultarEstoque">
		<div class="my-account">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-3">
					<h4> Consultar Estoque</h4>
						<br>
					</div>
					<div class="col-md-10 tabelaConsultarEstoque">
						<div class="divSelecionarProduto register-form">
							<h5>Produto: XXXX </h5> 
							
							<table class="table table-bordered">
								<thead class="thead-dark">
									<tr>
										<th>Nome</th>
										<th>Tipo</th>
										<th>Qtde.</th>
										<th>R$ Compra</th>
										<th>Data</th>
										<th>Qtde. Total</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>Blusa Manga Comprida</td>
										<td>Entrada</td>
										<td>6</td>
										<td>20.00</td>
										<td>2022-01-21</td>
										<td>6</td>
									</tr>
									<tr>
										<td>Blusa Manga Comprida</td>
										<td>Saida</td>
										<td>3</td>
										<td>20.00</td>
										<td>2022-01-21</td>
										<td>3</td>
									</tr>
								</tbody>
							</table>
						</div>
						<button class="btn"> <i class="fa fa-arrow-left"></i> Voltar</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Fim da Consulta de Estoque - TABELA -->

	<!-- Início do Footer -->
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

				<div class="col-lg-3 col-md-6">
					<!--VAZIO PARA DAR ESPAÇO ENTRE DADOS E REDES SOCIAIS  -->
				</div>

				<div class="col-lg-3 col-md-6">
					<div class="footer-widget">
						<h2>Siga-nos</h2>
						<div class="contact-info">
							<div class="social">
								<a href="#"><i class="fab fa-twitter"></i></a> <a href="#"><i
									class="fab fa-facebook-f"></i></a> <a href="#"><i
									class="fab fa-linkedin-in"></i></a> <a href="#"><i
									class="fab fa-instagram"></i></a> <a href="#"><i
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
						<img src="../img/payment-method.png" alt="Forma de Pagamento" />
					</div>
				</div>
				<div class="col-md-6">
					<div class="payment-security">
						<h2>Compre com segurança</h2>
						<img src="../img/godaddy.svg" alt="Payment Security" /> <img
							src="../img/norton.svg" alt="Payment Security" /> <img
							src="../img/ssl.svg" alt="Payment Security" />
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
					<p>
						Copyright &copy; <a href="../JSP/indexAdm.jsp">Mirror Fashion</a>
						- 2022 - Todos os direitos reservados
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
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
	<script src="../lib/easing/easing.min.js"></script>
	<script src="../lib/slick/slick.min.js"></script>

	<!--  Javascript -->
	<script src="../js/main.js"></script>
	<script src="../js/all.js"></script>
	
	<!-- Modal -->
	<div class="modal fade" id="modal-mensagem">
	   <div class="modal-dialog">
	   		<div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal"><span>×</span></button>
	                <h3 class="modal-titulo">Atenção</h3>
	            </div>
	            <div class="modal-body">
	                <p><% out.println(mensagemStrategy); %></p>
	            </div>
	            <!-- <div class="modal-footer">
	                <button type="btn" class="btn btn-default" data-dismiss="modal">Fechar</button>
	            </div> -->
	        </div>
	    </div>
	</div>
		
	<!-- Botão para chamar a Modal -->
	<button style="display: none" id="idModal" class="btn btn-primary" data-toggle="modal" data-target="#modal-mensagem">
		Exibir mensagem da modal
	</button>
      
      <script>
    // Função que irá ativar a Modal com a mensagem retornada do BackEnd,
    // essa função é carregada junto ao carregamento da página com o evento ONLOAD, dentro da tag <body>.
	    function AtivaModal(){
    		// metodo para poder ativar o "onClick" sem precisar clicar no botão
	    	document.getElementById('idModal').click();
	    }
    </script>
	
</body>

</html>