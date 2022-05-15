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
		<link href="./CSS/bootstrap.min.css" rel="stylesheet">
  		<link href="./CSS/shop-homepage.css" rel="stylesheet">
  		<link href="./CSS/form-default.css" rel="stylesheet" type="text/css">
	</head>
	
	<%
		List<String> totalColunasChart = new ArrayList<>();
		List<String> totalProduto1Chart = new ArrayList<>();
		List<String> totalProduto2Chart = new ArrayList<>();
		List<String> totalProduto3Chart = new ArrayList<>();
		
		// pega a data inicio que foi digitado na tela, que estava pendurado na requisição,
		// que foi enviado pelo arquivo "GraficoAnaliseHelper"
		String dtInicio = (String)request.getAttribute("dtInicio");
		
		// pega a data fim que foi digitado na tela, que estava pendurado na requisição,
		// que foi enviado pelo arquivo "GraficoAnaliseHelper"
		String dtFim = (String)request.getAttribute("dtFim");
		
		// separa o dia-mês-ano que foi selecionado na tela,
		// para poder exibir as datas no formato correto na tela
		String[] resultInicio  = dtInicio.split("-");
		String[] resultFim  = dtFim.split("-");
				
		// pega o nome do primeiro produto que estava pendurado na requisição,
		// que foi enviado pelo arquivo "GraficoAnaliseHelper"
		String nomeProduto1 = (String)request.getAttribute("nomeProduto1");
		
		// pega o nome do segundo produto que estava pendurado na requisição,
		// que foi enviado pelo arquivo "GraficoAnaliseHelper"
		String nomeProduto2 = (String)request.getAttribute("nomeProduto2");
		
		// pega o nome do terceiro produto que estava pendurado na requisição,
		// que foi enviado pelo arquivo "GraficoAnaliseHelper"
		String nomeProduto3 = (String)request.getAttribute("nomeProduto3");
		
		// pega o total de colunas que estava pendurado na requisição,
		// que foi enviado pelo arquivo "GraficoAnaliseHelper"
		totalColunasChart = (List<String>)request.getAttribute("totalColunas");
		
		// pega o total de valores do produto1 que estava pendurado na requisição,
		// que foi enviado pelo arquivo "GraficoAnaliseHelper"
		totalProduto1Chart = (List<String>)request.getAttribute("totalValorProduto1");
		
		// pega o total de valores do produto2 que estava pendurado na requisição,
		// que foi enviado pelo arquivo "GraficoAnaliseHelper"
		totalProduto2Chart = (List<String>)request.getAttribute("totalValorProduto2");
				
		// pega o total de valores do produto3 que estava pendurado na requisição,
		// que foi enviado pelo arquivo "GraficoAnaliseHelper"
		totalProduto3Chart = (List<String>)request.getAttribute("totalValorProduto3");
	%>
	
	<body onload="AtivaGrafico()">
		<!-- Header -->
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		  <div class="container">
		    <a href="/Ecommerce_Bebida/JSP/Home_Page_Back.jsp" class="navbar-brand">Ecommerce de Bebida</a>
		    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
		      <span class="navbar-toggler-icon"></span>
		    </button>
		    <div class="collapse navbar-collapse" id="navbarResponsive">
		      <ul class="navbar-nav ml-auto">
		        <li class="nav-item">
		          <%// foi utilizado a tag "${}" para poder escrever o objeto salvo em sessão dentro da tela %>
		       	  	<a class="nav-link">Bem vindo ${usuarioLogado.nome} !</a>
		          <span class="sr-only">(current)</span>
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
		
		<fieldset class="form-group fieldset_form" style="margin-top: 52px; margin-bottom: 30px !important;">
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
	  		
	  		<!-- Total de colunas (eixo X) no Chart.js -->
	  		<select style="display: none" id="ColunasChart">
				<% 
					for(String coluna : totalColunasChart) {
					      	
					// lista todas as colunas dentro do "value", de cada TAG "<option>".
				%>
				<option value="<%=coluna%>"><%=coluna%></option>
				<%
					}
				%>
			</select>
			
			<!-- Total de valores do Produto1 no Chart.js -->
	  		<select style="display: none" id="Produto1Chart">
				<% 
					for(String valor : totalProduto1Chart) {
					      	
						// lista todos os valores de Produto1 dentro do "value", de cada TAG "<option>".
				%>
				<option value="<%=valor%>"><%=valor%></option>
				<%
					}
				%>
			</select>
			
			<!-- Total de valores do Produto2 no Chart.js -->
	  		<select style="display: none" id="Produto2Chart">
				<% 
					for(String valor : totalProduto2Chart) {
					      	
						// lista todos os valores de Produto2 dentro do "value", de cada TAG "<option>".
				%>
				<option value="<%=valor%>"><%=valor%></option>
				<%
					}
				%>
			</select>
			
			<!-- Total de valores do Produto3 no Chart.js -->
	  		<select style="display: none" id="Produto3Chart">
				<% 
					for(String valor : totalProduto3Chart) {
					      	
						// lista todos os valores de Produto3 dentro do "value", de cada TAG "<option>".
				%>
				<option value="<%=valor%>"><%=valor%></option>
				<%
					}
				%>
			</select>
	  		
	  		<!-- Nomes dos Produtos -->
            <input type="hidden" name="nomeProduto1" id="nomeProduto1" value="<%=nomeProduto1 %>">
            <input type="hidden" name="nomeProduto2" id="nomeProduto2" value="<%=nomeProduto2 %>">
            <input type="hidden" name="nomeProduto3" id="nomeProduto3" value="<%=nomeProduto3%>">
		</fieldset>
		
		<h4 style="margin-left: 430px;">Período consultado: <%=resultInicio[2] %>/<%=resultInicio[1] %>/<%=resultInicio[0] %> até <%=resultFim[2] %>/<%=resultFim[1] %>/<%=resultFim[0] %></h4>
		
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
  	  <script src="./js/chart.js"></script>
  	  
  	  <!-- Botão para chamar o grafico do Chart.js -->
	  <button style="display: none" id="idGrafico" onclick="graficoChart()"></button>
  	  
  	  <script>
 	  // Função que irá ativar o grafico do Chart.js,
	  // essa função é carregada junto ao carregamento da página com o evento ONLOAD, dentro da tag <body>.
		  function AtivaGrafico(){
		  	// metodo para poder ativar o "onClick" sem precisar clicar no botão
		   	document.getElementById('idGrafico').click();
		  }
	  </script>
  	  
	</body>
</html>