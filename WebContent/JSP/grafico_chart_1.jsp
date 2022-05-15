<!DOCTYPE html>
<%@page import='com.les.roupa.core.dominio.*'%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>Gráfico Chart.js</title>
		
		<!-- importações para funcionar o Header e o Footer -->
		<link href="../CSS/bootstrap.min.css" rel="stylesheet">
  		<link href="../CSS/shop-homepage.css" rel="stylesheet">
  		<link href="../CSS/form-default.css" rel="stylesheet" type="text/css">
	</head>
	
	<body>
		<!-- Header -->
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		  <div class="container">
		    <a href="../JSP/indexAdm.jsp" class="navbar-brand">Ecommerce de Bebida</a>
		    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
		      <span class="navbar-toggler-icon"></span>
		    </button>
		    <div class="collapse navbar-collapse" id="navbarResponsive">
		      <ul class="navbar-nav ml-auto">
		        <li class="nav-item">
		          <%// foi utilizado a tag "${}" para poder escrever o objeto salvo em sessão dentro da tela %>
		       	  	<a class="nav-link">Bem vindo ${usuarioLogado.nome} </a>
		          
		        </li>
		        <li class="nav-item">
		          <form action="http://localhost:8080/eCommerce/login">
		          	<button type="submit" class="btn btn-danger pull-right" name="operacao" value="EXCLUIR">Sair</button>
		          </form>
		        </li>
		      </ul>
		    </div>
		  </div>
		</nav>
		<!-- Fim Header -->
		
		<fieldset class="form-group fieldset_form" style="margin-top: 52px; margin-bottom: 76px !important;">
		<legend align="center">Gerenciamento de Gráficos - 3 Produtos mais vendidos</legend>
			<form class="form_form" action="http://localhost:8080/eCommerce/graficoAnalise">
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
				  			<button class="btn btn-warning" name="operacao" value="CONSULTAR">Gerar</button>
				  		</div>
			  		</div>
		  		</div>
	  		</form>
		</fieldset>
		
		<!-- Grafico gerado pelo Chart.js -->
		<div style="width: 50%; height: 50%; margin-left: 350px;">
			<canvas id="myChart"></canvas>
		</div>
		<!-- Fim Grafico gerado pelo Chart.js -->
		
	  <!-- Footer -->
	  <footer class="py-5 bg-dark">
	    <div class="container">
	      <p class="m-0 text-center text-white">Copyright &copy; Drink Fast 2021</p>
	    </div>
	  </footer>
  	  <!-- Fim Footer -->
  	  
  	  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.5.1/chart.min.js" integrity="sha512-Wt1bJGtlnMtGP0dqNFH1xlkLBNpEodaiQ8ZN5JLA5wpc1sUlk/O5uuOMNgvzddzkpvZ9GLyYNa8w2s7rqiTk5Q==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
  	  <script src="../js/chart.js"></script>
  	  
	</body>
</html>