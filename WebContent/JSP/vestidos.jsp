<!-- VESTIDOS -->
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
	<link rel="stylesheet" type="text/css" href="../css/estilos.css">
	<meta charset="utf-8">

	</head>
	<header class="container">
		<h1><a href="../JSP/index.jsp"><img src="../img/logo.png" alt="Mirror Fashion"></a></h1>
		<p class="sacola"><a href="../JSP/carrinho.jsp">Minha Sacola</a></p>
        <!-- AQUI INFORMA QUEM É O USUARIO LOGADO -->
        <form action="http://localhost:8080/eCommerce_roupa/login" method="post">
            <p>Olá ${usuarioLogado.nome} <button type="submit" name="operacao" value="EXCLUIR" id="btnSair">Sair</button></p>
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
            </br>
            </ul>	
		</nav>
	</header>
<body>
	<!-- CONTAINER - MENU ESQUERDO - PARTE PRINCIPAL-->
	<div class="container destaque">
			
		<!--SeÃ§Ã£o dos Departamentos -->
			<section class="menu-departamentos">
				<h2 style="margin-left: 5px;"> Categorias</h2>
					<nav>
                        <li><a href="../JSP/blusas.jsp">Blusas</a></li>
                        <li><a href="../JSP/calcas.jsp">Calças</a></li>
                        <li><a href="../JSP/vestidos.jsp">Vestidos</a></li>
                    </nav>
			</section> 
			<!-- FIM DA SEÃ‡ÃƒO DEPARTAMENTOS -->

			<!-- PRODUTOS VESTIDOS -->

			<h2 style="text-align: center;">Vestidos</h2>
			<section class="painel produtos">
				<div id="fotos">
				<% 
                    DetalheProdutoDAO dao = new DetalheProdutoDAO();
                    Produto produto = new Produto();
                    Usuario usuarioLogado = new Usuario();
                    
                    HttpSession sessao = request.getSession();
                    usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
                    
                    
                    List<EntidadeDominio> produtos = dao.consultarProdutoByCategoriaVestido(produto);
                    
                    for(EntidadeDominio e : produtos){
                              
                     // Aplicado o CAST para poder popular
                              Produto p = (Produto) e;    
                  %>
				
				<!-- PRIMEIRO PRODUTO -->
					<li>
                        <a href="/eCommerce_roupa/detalheProduto?id=<%= p.getId()%>&operacao=CONSULTAR">
                        <img src=".<%=p.getFoto()%>">
                        <figcaption><%=p.getNome() %> - R$ <%=p.getPrecoVenda() %></figcaption>
                        </a>
                    </li>
                </div>
                <%
                    }
                %>
			</section>
<!-- FIM DA LISTAGENS DE VESTIDOS-->
	</div>
	<!-- FIM CONTAINER DESTAQUE -->



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
	