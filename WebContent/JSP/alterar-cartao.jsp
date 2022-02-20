<!-- ALTERAR CARTAO - CLIENTE-->	
<%@page import='com.les.roupa.core.dao.*'%>
<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import='com.les.roupa.core.dao.impl.*'%>

<%@page import="java.util.List"%>
		
<!DOCTYPE html>
<html>
	<head>
		<title>Mirror Fashion</title>
		<!-- FAVICON -->
        <link rel="shortcut icon" href="./favicon.ico">
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    		pageEncoding="UTF-8"%>	
		 <link rel="stylesheet" type="text/css" href="./css/perfil.css"> 
		<meta charset="utf-8">
	</head>
		<header class="container">
		<h1><a href="./JSP/index.jsp"><img src="./img/logo.png" alt="Mirror Fashion"></a></h1>
		 <p class="sacola"><a href="./JSP/carrinho.jsp">Minha Sacola</a></p>
		  <!-- AQUI INFORMA QUEM É O USUARIO LOGADO -->
         <form action="http://localhost:8080/eCommerce_roupa/login">
        <p>Olá ${usuarioLogado.nome} <button type="submit" name="operacao" value="EXCLUIR" id="btnSair">Sair</button></p>
        <script>
               function sair(){
                   alert("Você saiu com sucesso! Volte em breve!");
               }
        </script>
        </form> 
		<nav class="menu-opcoes" class="links">
			<ul>
				<li><a href="./JSP/index.jsp">Home</a></li>
				<li><a href="./JSP/produtos.jsp">Produtos</a></li>
				<li><a href="./JSP/perfil.jsp">Perfil</a></li>
			</ul>
		</nav>
	</header>
		
		<%
			CartaoCreditoDAO dao = new CartaoCreditoDAO();
		    Usuario usuarioLogado = new Usuario();
		    
		    HttpSession sessao = request.getSession();
            usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
			
			String id = (String)request.getAttribute("id");
			
			List<CartaoCredito> cartao = dao.consultarCartCreditoById(id);
		%>
		
	<body>
		<!-- CONTAINER - DESTAQUE - PARTE PRINCIPAL-->
		<div class="container destaque">
			
			<!-- SESSAO -->
				<section class="menu-departamentos">
                <h2 style="margin-left: 5px;">Perfil</h2>
                <nav>
                    <li>
                        <a href="./JSP/meusdados.jsp">Meus Dados</a>
                        <ul>
                            <li><a href="./JSP/meusenderecos.jsp">Endereços</a></li>
                            <li><a href="../JSP/cartoes.jsp">Cartoes e Cupons</a></li>
                            <li><a href="./JSP/alterarsenha.jsp">Alterar Senha</a></li>
                        </ul>
                    </li>
                    <li><a href="./JSP/pedidos.jsp">Meus Pedidos</a></li>
                </nav>
            </section>
				<!-- FIM DA SESSAO  -->
		</div>
		<!-- FIM CONTAINER DESTAQUE -->

		<div class="container destaque">
					<!-- FORMULARIO DE ALTERAR CARTAO  -->
			<form class="formulario" action="http://localhost:8080/eCommerce_roupa/cadastroCartao" >
						<caption>* ALTERAR CARTÃO *</caption><br>
				<div>
				<br>
					<label>Nº Cartao: </label> <input type="text" id="numerocartao" name="nCartao" maxlength="16" value="<%=cartao.get(0).getNCartao() %>" readonly >
					      
                    <label>Bandeira: </label> <input type="hidden" value="<%=cartao.get(0).getBandeira() %>" />
                    <select name="bandeira">
                        <option disabled selected>Selecione</option>
                        <option value="Elo">Elo</option>
                        <option value="Mastercard">Mastercard</option>
                        <option value="Visa">Visa</option>
                    </select>
                    
                    <label>Nome:</label><input type="text" id="nomecartao" name="nome" value="<%=cartao.get(0).getNome() %>">
                    <div>                
                        <label>Validade:</label>  <input type="month" class="form-control" name="validade" value="<%=cartao.get(0).getValidade()%>" readonly >
                        
                        <label id="labelcvv">CVV:</label><input type="text" id="cvv" name="cvv" maxlength="3" value="<%=cartao.get(0).getCvv() %>" readonly>
                        
                        <label>Cartão Principal:</label> <input type="hidden" value="<%=cartao.get(0).getAlteraPref() %>" />
	                        <select name="preferencial">
	                        <option disabled selected>Selecione</option>
	                        <option value="Sim">Sim</option>
	                        <option value="Não">Não</option>
                    </select>
                    </div>
                    <br>
				<div>
						<button class="button" type="submit"onclick="window.history.go(-1); return false;" id="btnVoltar" >Voltar</button>
						<button class="button" type="submit" name="operacao" value="ALTERAR" id="btnSalvar">Salvar Alterações</button>
						
						<input type="hidden" name="alteraPreferencial" value="1"/>
						<input type="hidden" name="id" value="<%=cartao.get(0).getId()%>"/>
				</div>
			</form>
			</div>
		</div>

	<!-- RODAPE -->
	<footer>
		<div class="container">
			<a href="./JSP/index.jsp">
				<img src="./img/logo-rodape.png" alt="Logo Mirror Fashion"></a>
			<ul class="social">
				<!-- <li id="copy">&copy; Copyright MF - Todos os direitos protegidos - 2021</li> -->
				<li><a href="http://facebook.com/mirrorfashion">Facebook</a></li>
				<li><a href="http://twitter.com/mirrorfashion">Twitter</a></li>
				<li><a href="http://plus.google.com/mirrorfashion">Google+</a></li>
			</ul>
		</div>
	</footer>
	<!-- FIM DO RODAPE -->

	</body>
</html>