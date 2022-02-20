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
         <link rel="stylesheet" type="text/css" href="../css/relatorio.css"> 
        <meta charset="utf-8">
         <!-- FAVICON -->
        <link rel="shortcut icon" href="../favicon.ico">
    </head>
	
	<body>
		<!-- Header -->
		<header class="container">
        <h1><a href="../JSP/admin.jsp"><img src="../img/logo.png" alt="Mirror Fashion"></a></h1>
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
                <li><a href="../JSP/admin.jsp">Home</a></li>
                <li><a href="../JSP/perfil-admin.jsp">Perfil</a></li>
                </li>
            </ul>
        </nav>
    </header>
		<!-- Fim Header -->
		 <div class="container destaque">
            <!-- SEÇÃO MENU DE ADMIN -->
            <section class="menu-departamentos">
                <h2 style="margin-left: 5px;">Menu</h2>
                <nav>
                    <li><a href="../JSP/consultar-cliente.jsp">Clientes</a></li>
                    <li>
                        <a href="../JSP/consultar-pedidos.jsp">Pedidos</a>
                        <ul>
                            <li><a href="../JSP/grafico1.jsp">Relatorio</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="../JSP/consultar-produtos.jsp">Produtos</a>
                        <ul>
                            <li><a href="../JSP/estoque.jsp">Estoque</a></li>
                            <li><a href="../JSP/novo-produto.jsp">Adicionar Novo</a></li>
                        </ul>
                    </li>
                    <li><a href="../JSP/cupons.jsp">Cupons</a></li>
                </nav>
            </section>
            <!-- FIM DA SECAO DO MENU DE ADMIN -->
        </div>
		
		
		<div class="container destaque">
			<form class="formulario" action="http://localhost:8080/eCommerce_roupa/graficoAnalise">
			<div id="relatorio">Análise do Gráfico - 3 Produtos mais vendidos </div>
			 <br>
			 <br>
				<div>
					<div class="datas">
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
		
		
		<!-- Grafico gerado pelo Chart.js -->
		<div style="width: 50%; height: 50%; margin-left: 350px;">
			<canvas id="myChart"></canvas>
		</div>
		<!-- Fim Grafico gerado pelo Chart.js -->
		
	 <!-- RODAPE -->
        <footer>
            <div class="container">
                <a href="../JSP/admin.jsp">
                <img src="../img/logo-rodape.png" alt="Logo Mirror Fashion"></a>
                <ul class="social">
                    <li><a href="http://facebook.com/mirrorfashion">Facebook</a></li>
                    <li><a href="http://twitter.com/mirrorfashion">Twitter</a></li>
                    <li><a href="http://plus.google.com/mirrorfashion">Google+</a></li>
                </ul>
            </div>
        </footer>
    <!-- FIM DO RODAPE -->
  	  
  	  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.5.1/chart.min.js" integrity="sha512-Wt1bJGtlnMtGP0dqNFH1xlkLBNpEodaiQ8ZN5JLA5wpc1sUlk/O5uuOMNgvzddzkpvZ9GLyYNa8w2s7rqiTk5Q==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
  	  <script src="../js/chart.js"></script>
  	  
	</body>
</html>