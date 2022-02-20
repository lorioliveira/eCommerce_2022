<!-- BLUSAS - MANGA COMPRIDA -->

<!DOCTYPE html>
<html>
<head>
		<title>Mirror Fashion</title>

	<link rel="stylesheet" type="text/css" href="../css/estilos.css">
	<meta charset="utf-8">

	</head>
	<header class="container">
		<h1><a href="../HTML/index.html"><img src="../img/logo.png" alt="Mirror Fashion"></a></h1>
		
        <p class="sacola"><a href="./JSP/carrinho.jsp">Minha Sacola</a></p>
        <!-- AQUI INFORMA QUEM � O USUARIO LOGADO -->
        <form action="http://localhost:8080/eCommerce_roupa/login" method="post">
            <p>Ol� ${usuarioLogado.nome} <button type="submit" name="operacao" value="EXCLUIR" id="btnSair">Sair</button></p>
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
		<!--Seção dos Departamentos -->
			<section class="menu-departamentos">
				<h2 style="margin-left: 5px;"> Categorias</h2>
					<nav>
                        <li>
                            <a href="../JSP/blusas.jsp">Blusas</a>
                            <ul>
                                <li><a href="../JSP/mangacomprida.jsp">Manga Comprida</a></li>
                                <li><a href="../JSP/mangacurta.jsp">Manga Curta</a></li>
                            </ul>
                        </li>
                            
                        <li><a href="../JSP/calcas.jsp">Cal�as</a></li>
                        <li><a href="../JSP/vestidos.jsp">Vestidos</a></li>
                    </nav>
			</section> 
			<!-- FIM DA SEÇÃO DEPARTAMENTOS -->

			<!-- PRODUTOS DE BLUSAS E CAMISAS -->

		<!-- <h2 id="categoria">Blusas & Camisas</h2> -->
			<h2 style="text-align: center;">Blusas - Manga Comprida</h2>
			<section class="painel produtos">
				<div id="fotos">
				<!-- PRIMEIRO PRODUTO -->
					<li>
						<a href="../JSP/detalhe_blusa.jsp">
							<img src="../img/produtos/blusaamarela.png">
						<figcaption>Blusas - R$ 49,99</figcaption>
						</a>
					</li>
					

				</div>
			</section>
<!-- FIM DA LISTAGENS DE PRODUTOS DE BLUSAS E CAMISAS -->
	</div>
	<!-- FIM CONTAINER DESTAQUE -->



<!-- RODAPÉ -->
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
<!-- FIM DO RODAPÉ -->
<script src="../js/produtos.js"> </script>

</body>
</html>
	