<!-- CALÇAS -->
<!DOCTYPE html>
<html>
	<head>
		<title>Mirror Fashion</title>

		<link rel="stylesheet" type="text/css" href="../css/estilos_2.css">
		<link rel="stylesheet" type="text/css" href="../css/detalhe_calca.css">
		<meta charset="utf-8">

	</head>
		<header class="container">
			<h1><a href="../JSP/index.jsp"><img src="../img/logo.png" alt="Mirror Fashion"></a></h1>
			<p class="sacola"><a href="../JSP/carrinho.jsp">Minha Sacola</a></p>
			
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
				
			<!-- Categoria -->
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
								
							<li><a href="../JSP/calcas.jsp">Cal�a</a></li>
							<li><a href="../JSP/vestidos.jsp">Vestidos</a></li>
						</nav>
				</section> 
				<!-- FIM DA SEÇÃO DEPARTAMENTOS -->

			<!-- PARTE ONDE VAI FICAR O PRODUTO  -->
			<div class="produto-back">	
				<div class="container">
						<div class="produto">
							<h1>Cal�a de Elastico</h1>
								<p>por apenas R$ 42,90</p>
							<div>
								<fieldset class="cores">
									<legend>Escolha a cor:</legend>

									<input type="radio" name="cor" value="preto" id="preto" checked>
									<label for="preto">
										<img src="../img/produtos/calcapreta.png" alt="preto">
									</label>

									<input type="radio" name="cor" value="caramelo" id="caramelo">
									<label for="caramelo">
									<img src="../img/produtos/calcacaramelo.png" alt="caramelo">
									</label>

									<input type="radio" name="cor" value="azulmarinho" id="azulmarinho">
									<label for="azulmarinho">
									<img src="../img/produtos/calcamarinho.png" alt="azulmarinho">
									</label>
								</fieldset>
									
									<fieldset class="tamanhos">
										<legend>Escolha o tamanho:</legend>
											<select name="lista2" id="tamanho" size="1">
											  <option value="">-- Selecione --</option>
											  <option value="36">Tamanho 36</option>
											  <option value="38">Tamanho 38</option>
											  <option value="40">Tamanho 40</option>
											  <option value="42">Tamanho 42</option>
											</select>
										<!-- minimo/maximo/valor selecionado padrao/qtde que ele anda entre casas -->
										<!-- <input type="range" min="36" max="46" value="42" step="2" name="tamanho" id="tamanho"> -->
									</fieldset>
										<input type="submit" class="comprar" name="Comprar" value="Comprar" onclick="comprar()">
                                    <script>
                                         function comprar(){
                                             alert("Adicionado ao carrinho!");
                                         }
                                    </script>
							</div>
						</div>

						<div class="detalhes">
							<h2>Detalhes do produto</h2>
							<div id="tabela"><p>Esse � o melhor vestido de algodao que
							 voce ja viu. Excelente material nacional em um tecido 
							 trabalhado no lapis. Comprimento midi, ajuste slim fit, 
							 perfeito para o Verao 2021. Compre jae receba hoje mesmo pela 
							 nossa entrega a jato.</p>
							<!-- TABELA -->
							
							<table>
								<thead>
									<tr>
										<th>Característica</th>
										<th>Detalhe</th>
									</tr>
								</thead>
								
								<tbody>
									<tr>
									<td>Modelo</td>
									<td>Vestido Mini Lápis</td>
									</tr>
									<tr>
									<td>Material</td>
									<td>100% Algodão</td>
									</tr>
									<tr>
									<td>Cores</td>
									<td>Rosa, Verde e Vermelho</td>
									</tr>
									<tr>
									<td>Lavagem</td>
									<td>Na máquina ou a mão</td>
									</tr>
								</tbody>
							</table>
							</div>
							<!-- FIM TABELA -->
						</div> 
						<!--  FIM DIV DETALHES -->
				</div>
				<!-- FIM DA PARTE DO PRODUTO -->
			</div>	
			<!-- FIM DIV PRODUTO-BACK -->
		</div>
		<!-- FIM CONTAINER DESTAQUE -->



	<!-- RODAPÉ -->
	<footer>
		<div class="container">
			<a href="../HTML/index.html">
				<img src="../img/logo-rodape.png" alt="Logo Mirror Fashion"></a>
			<ul class="social">
				<li><a href="http://facebook.com/mirrorfashion">Facebook</a></li>
				<li><a href="http://twitter.com/mirrorfashion">Twitter</a></li>
				<li><a href="http://plus.google.com/mirrorfashion">Google+</a></li>
			</ul>
		</div>
	</footer>
	<!-- FIM DO RODAPÉ -->

	</body>
</html>
	