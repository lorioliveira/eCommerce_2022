<!-- ADMIN -->	
<%@page import='com.les.roupa.core.dao.*'%>
<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import='com.les.roupa.core.dao.impl.*'%>

<%@page import="java.util.List"%>
		
<!DOCTYPE html>
<html>
	<head>
		<title>Mirror Fashion</title>
		<!-- FAVICON -->
        <link rel="shortcut icon" href="../favicon.ico">

		 <link rel="stylesheet" type="text/css" href="../css/perfil.css"> 
		<meta charset="utf-8">
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    		pageEncoding="UTF-8"%>	
	</head>
		<header class="container">
		<h1><a href="../JSP/index.jsp"><img src="../img/logo.png" alt="Mirror Fashion"></a></h1>
        <p class="sacola"><a href="../JSP/carrinho.jsp">Minha Sacola</a></p>
         
 <!-- AQUI INFORMA QUEM É O USUARIO LOGADO -->
         <form action="http://localhost:8080/eCommerce_roupa/login">
        <p>Olá ${usuarioLogado.nome} <button class="button" type="submit" name="operacao" value="EXCLUIR" id="btnSair">Sair</button></p>
        <script>
               function sair(){
                   alert("Você saiu com sucesso! Volte em breve!");
               }
        </script>
        </form> 
        <nav class="menu-opcoes" class="links">
			<ul>
				<li><a href="../JSP/index.jsp">Home</a></li>
				<li><a href="../JSP/produtos.jsp">Produtos</a></li>
				<li><a href="../JSP/perfil.jsp">Perfil</a></li>
			</ul>
		</nav>
	</header>
		
		
		<%
		ClienteDAO dao = new ClienteDAO();
		Usuario usuarioLogado = new Usuario();
		
		HttpSession sessao = request.getSession();
		usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		List<Cliente> clientes = dao.consultarClienteById(usuarioLogado.getId());
		%>
		
	<body>
		<!-- CONTAINER - DESTAQUE - PARTE PRINCIPAL-->
		<div class="container destaque">
			
			<!-- SESSAO MENU CLIENTE -->
				<section class="menu-departamentos">
                <h2 style="margin-left: 5px;">Perfil</h2>
                <nav>
                    <li>
                        <a href="../JSP/meusdados.jsp">Meus Dados</a>
                        <ul>
                            <li><a href="../JSP/meusenderecos.jsp">Enderecos</a></li>
                            <li><a href="../JSP/cartoes.jsp">Cartoes e Cupons</a></li>
                            <li><a href="../JSP/alterarsenha.jsp">Alterar Senha</a></li>
                        </ul>
                    </li>
                    <li><a href="../JSP/pedidos.jsp">Meus Pedidos</a></li>
                </nav>
            </section>
				<!-- FIM DA SESSAO -->
		</div>
		<!-- FIM CONTAINER DESTAQUE -->

		<div class="container destaque">
					<!-- FORMULÃRIO DE NOVA CONTA  -->
			<form class="formulario" action="http://localhost:8080/eCommerce_roupa/cadastro" >
						<caption> * ALTERAR CADASTRO * </caption><br>
				<div>
				<br>
					<label>Nome: </label> <input type="text" id="nome" name="nome" value="<%=clientes.get(0).getNome() %>" />      
					<label>CPF: </label><input type="CPF" id="CPF" name="cpf" class="form-control cpf-mask" placeholder="000.000.000-00" maxlength="11" value="<%=clientes.get(0).getCpf() %>" readonly />
					<label>Dt. Nasc.:</label><input type="date" name="dt_nasc" value="<%=clientes.get(0).getDt_nasc() %>">
				</div>
					<!-- OPCOES DE GENERO (MASC/FEM)-->
				<div>
					<label>Gênero: </label><input type="hidden" value="<%=clientes.get(0).getGenero() %> " />
						<select name="genero"> 
							<option value="Feminino">Feminino</option><!-- selected -->
							<option value="Masculino" >Masculino</option>
						</select>
				
					<label id="telefone">Telefone: </label><input type="tel" name="telefone" id="telefone" maxlength="11" value="<%=clientes.get(0).getTelefone() %>">
					<label id="email">E-mail:</label> <input type="email" id="email" name="email" placeholder="digite seu e-mail aqui" value="<%=clientes.get(0).getUsuario().getEmail() %>" />
					 <input type="hidden" name="senha" id="senha" accesskey="S" value="<%=clientes.get(0).getUsuario().getSenha() %>" />
					 <input type="hidden" name="confsenha" value="<%=clientes.get(0).getUsuario().getSenha() %>"/>
				</div>
				<br>
				
				<div id="botoes">
						<!-- <button class="button" type="submit" id="btnVoltar" onclick="window.history.go(-1); return false;">Voltar</button> -->
						<button class="button" type="submit" name="operacao" value="ALTERAR" id="btnSalvar">Confirmar</button>
						
						<input type="hidden" name="tipo" value="cliente"/>
						<input type="hidden" name="status" value="ativo"/>
						<input type="hidden" name="alteraCliente" value="1"/>
						<input type="hidden" name="id" value="<%=clientes.get(0).getId()%>"/>
						
				</div>
			</form>
		</div>

	<!-- RODAPE -->
	<footer>
		<div class="container">
			<a href="../JSP/index.jsp">
				<img src="../img/logo-rodape.png" alt="Logo Mirror Fashion"></a>
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