<!DOCTYPE html>

<%@page import='com.les.roupa.core.dao.*'%>
<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import='com.les.roupa.core.dao.impl.*'%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<html>
	<head> 
        <title>Mirror Fashion</title>
        <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
            pageEncoding="UTF-8"%>  
         <link rel="stylesheet" type="text/css" href="./css/relatorio.css"> 
        <meta charset="utf-8">
         <!-- FAVICON -->
        <link rel="shortcut icon" href="./favicon.ico">
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
        <header class="container">
        <h1><a href="./JSP/admin.jsp"><img src="./img/logo.png" alt="Mirror Fashion"></a></h1>
        <!-- AQUI INFORMA QUEM É O USUARIO LOGADO -->
        <form action="http://localhost:8080/eCommerce_roupa/login">
            <p>Olá ${usuarioLogado.nome} <button  type="submit" name="operacao" value="EXCLUIR" id="btnSairAdmin" onclick="sair()">Sair</button></p>
            <script>
                function sair(){
                    alert("Você saiu com sucesso! Volte em breve!");
                }
            </script>
        </form>
        <nav class="menu-opcoes" class="links">
            <ul>
                <li><a href="./JSP/admin.jsp">Home</a></li>
                <li><a href="./JSP/perfil-admin.jsp">Perfil</a></li>
                </li>
            </ul>
        </nav>
    </header>
        <!-- Fim Header -->
		
		<div class="container destaque">
            <form class="formularioGrafico" action="http://localhost:8080/eCommerce_roupa/graficoAnalise">
            <div id="relatorio">Análise do Gráfico - 3 Produtos mais vendidos </div>
             <br>
             <br>
                <div>
                    <div class="datas2">
                        <!-- Data Inicio -->
                        <label>Data Inicio: </label>
                        <input type="date" class="form-control" name="dtInicio" placeholder="Data Inicio" required>
                    
                        <!-- Data Fim -->
                        <label id="labelDtFim">Data Fim: </label>
                        <input type="date" class="form-control" name="dtFim" placeholder="Data Fim" required>
                        
                         <!-- BOTÃO -->
                        <div id="botaoGrafico" style="margin-top: 32px">
                          <button class="button" type="submit" id="btnVoltar" onclick="window.history.go(-1); return false;">Voltar</button>
                          <button id="btnConsultar" name="operacao" value="CONSULTAR">Gerar Gráfico</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
	  		
	  		<!-- Total de colunas (eixo X) no Chart.js -->
	  		<select style="display: none" id="ColunasChart">
				<% 
					for(String coluna : totalColunasChart) {
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
		
		<h4 style="text-align:center">Período Filtrado: de <%=resultInicio[2] %>/<%=resultInicio[1] %>/<%=resultInicio[0] %> a <%=resultFim[2] %>/<%=resultFim[1] %>/<%=resultFim[0] %></h4>
		
		<!-- Grafico gerado pelo Chart.js -->
		<div style="width: 50%; height: 50%; margin-left: 350px;">
			<canvas id="myChart"></canvas>
		</div>
		<br><br>
		<!-- Fim Grafico gerado pelo Chart.js -->
		
	  <!-- RODAPE -->
        <footer>
            <div class="container">
                <a href="./JSP/admin.jsp">
                <img src="./img/logo-rodape.png" alt="Logo Mirror Fashion"></a>
                <ul class="social">
                    <li><a href="http://facebook.com/mirrorfashion">Facebook</a></li>
                    <li><a href="http://twitter.com/mirrorfashion">Twitter</a></li>
                    <li><a href="http://plus.google.com/mirrorfashion">Google+</a></li>
                </ul>
            </div>
        </footer>
    <!-- FIM DO RODAPE -->
  	  
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