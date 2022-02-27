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
	 <link rel="stylesheet" type="text/css" href="../css/login.css"> 
	<meta charset="utf-8">
</head>
	<header class="container">
		<h1><a href="../JSP/index.jsp"><img src="../img/logo.png" alt="Mirror Fashion"></a></h1>
		<p class="sacola">Minha Sacola</p>
		
		<!-- AQUI INFORMA QUEM É O USUARIO LOGADO -->
        <form action="http://localhost:8080/eCommerce_roupa/login">
            <p>Olá ${usuarioLogado.nome} </p>
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
	<!-- CONTAINER - FORMULARIO-->
	<div class="container destaque">
		<!-- DADOS  -->
		<form class="formulario" action="http://localhost:8080/eCommerce_roupa/cadastro" method="post">
			<div>
				<p>ALTERAR SENHA</p>
				
				<input type="hidden" id="nome" name="nome" value="<%=clientes.get(0).getNome() %>" />      
                <input type="hidden" id="CPF" name="cpf" class="form-control cpf-mask" maxlength="11" value="<%=clientes.get(0).getCpf() %>"/>
                <input type="hidden" name="dt_nasc" value="<%=clientes.get(0).getDt_nasc() %>">
                <input type="hidden" name="genero" value="<%=clientes.get(0).getGenero()%>">
                <input type="hidden" name="telefone" id="telefone" maxlength="11" value="<%=clientes.get(0).getTelefone() %>">
                <input type="hidden" id="email" name="email" value="<%=clientes.get(0).getUsuario().getEmail() %>"/>
                    
				
				<label>Digite nova senha: </label> <input type="password" name="senha" id="senha"  accesskey="S" placeholder="*********" required/> 
				<label>Confirme a senha: </label> <input type="password" name="confsenha" id="senha" accesskey="S" placeholder="*********"  required/>
			</div>
			<br>

		    <div class="button_suaconta">
		    	<button id="btnVoltar"><a href="../JSP/perfil.jsp"> Cancelar </a></button>
		    	<button type="submit" id="btnSalvar" name="operacao" value="ALTERAR"> Salvar </button>
		    </div>
		    <input type="hidden" name="status" value="ativo"/>
            <input type="hidden" name="tipo" value="cliente"/>
		    <input type="hidden" name="alteraCliente" value="1"/>
            <input type="hidden" name="id" value="<%=clientes.get(0).getId()%>"/>
		</form>
	</div>
	<!-- FIM DOS DADOS -->


<!-- RODAPÃ‰ -->
<footer>
	<div class="container">
		<a href="../JSP/index.jsp">
			<img src="../img/logo-rodape.png" alt="Logo Mirror Fashion"></a>
		<ul class="social">
			<li><a href="http://facebook.com/mirrorfashion">Facebook</a></li>
			<li><a href="http://twitter.com/mirrorfashion">Twitter</a></li>
			<li><a href="http://plus.google.com/mirrorfashion">Google+</a></li>
		</ul>
	</div>
</footer>
<!-- FIM DO RODAPÃ‰ -->

</body>
</html>