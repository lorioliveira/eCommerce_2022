<%@page import='com.les.roupa.core.dao.*'%>
<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import='com.les.roupa.core.dao.impl.*'%>

<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Mirror Fashion</title>
		<link rel="stylesheet" type="text/css" href="../css/enderecos.css">
		<meta charset="utf-8">
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    		pageEncoding="UTF-8"%>
    		 <!-- FAVICON -->
        <link rel="shortcut icon" href="../favicon.ico">	
	</head>
	<header class="container">
		<h1><a href="../JSP/index.jsp"><img src="../img/logo.png" alt="Mirror Fashion"></a></h1>
		 <p class="sacola"><a href="./carrinho.jsp">Minha Sacola</a></p>
		
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
				<li><a href="../JSP/index.jsp">Home</a></li>
                <li><a href="../JSP/produtos.jsp">Produtos</a></li>
                <li><a href="../JSP/perfil.jsp">Perfil</a></li>
			</ul>
		</nav>
	</header>
	
	<%
	    Usuario usuarioLogado = new Usuario();
	    
	    HttpSession sessao = request.getSession();
	    usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");      
    %>
	
	<body>
		<!-- CONTAINER - DESTAQUE - PARTE PRINCIPAL-->
		<div class="container destaque">
			<!--SeÃ§Ã£o do MENU CLIENTE-->
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
			<!-- DADOS  DE ENDEREOO  -->
			<form class="formulario" action="http://localhost:8080/eCommerce_roupa/cadastroEndereco">

				<div>* MEU ENDEREÇO *</div><br>
				<div>
					<label>Tipo Residencia: </label>
						<select id="tipoResidencia" name="tipoResidencia" >
						    <option disabled selected>Selecione </option>
							<option value="casa">Casa</option>
							<option value="apartamento">Apto</option>
							<option value="outro">Outro</option>
						</select>
					<label class="CEP">CEP:</label><input type="text" id="CEP" name="cep" class="form-control cep-mask" placeholder="00000-000" maxlength="8" onkeypress="return event.charCode >= 48 && event.charCode <= 57" />

					<label id="endereco">Endereco:</label>
						<select name="tipoEndereco">
							<option disabled selected>Selecione</option>
							<option value="Cobranca e Entrega">Cobrança e Entrega</option>
							<option value="Cobranca">Cobrança</option>
							<option value="Entrega">Entrega</option>
						</select>
				</div>
					<label id="logradouro">Logradouro:</label> <input type="text" name="logradouro" id="logradouro" maxlength="40" />
					<label id="numero">Numero:</label> <input type="text" name="numero" id="numero" maxlength="4"  />
				
					<label id="bairro">Bairro:</label><input type="text" name="bairro" id="bairro"  />
					
					<!-- Estado / Cidade / Pais -->
					<label> Estado: </label>
					<select id="estado" name="estado" onchange="buscaCidades(this.value)"  >
						<option disabled selected>Selecione Estado</option>
						<option value="AC">Acre</option>
						<option value="AL">Alagoas</option>
						<option value="AP">Amapa</option>
						<option value="AM">Amazonas</option>
						<option value="BA">Bahia</option>
						<option value="CE">Ceara</option>
						<option value="DF">Distrito Federal</option>
						<option value="ES">Espirito Santo</option>
						<option value="GO">Goias</option>
						<option value="MA">Maranhao</option>
						<option value="MT">Mato Grosso</option>
						<option value="MS">Mato Grosso do Sul</option>
						<option value="MG">Minas Gerais</option>
						<option value="PA">Para</option>
						<option value="PB">Paraiba</option>
						<option value="PR">Parana</option>
						<option value="PE">Pernambuco</option>
						<option value="PI">Piaui</option>
						<option value="RJ">Rio de Janeiro</option>
						<option value="RN">Rio Grande do Norte</option>
						<option value="RS">Rio Grande do Sul</option>
						<option value="RO">Rondonia</option>
						<option value="RR">Roraima</option>
						<option value="SC">Santa Catarina</option>
						<option value="SP">Sao Paulo</option>
						<option value="SE">Sergipe</option>
						<option value="TO">Tocantins</option>
					</select>
				
					<label for="Cidade"> Cidade: </label>
					<select id="cidade" name="cidade" >
						<option disabled selected>Selecione o Estado</option>
						<script type="text/javascript" src="../js/estados-cidades.js" charset="utf-8"></script>
					</select>
				
					<label>Pais: </label>
					<select id="pais" name="pais">
						<option value="Brasil">Brasil </option>
					</select>
				
				<textarea id="textarea" placeholder="Campo para observacoes (opcional)" name="observacoes"></textarea>
					
				<!-- BOTOES -->
				<div class="button">
					<!-- <button type="submit" onclick="window.history.go(-1); return false;" id="btnVoltar">Voltar</button> -->
					<button type="submit" name="operacao" value="SALVAR" id="btnSalvar"> Salvar </button>
					<button type="submit" name="operacao" value="CONSULTAR" id="btnConsultar"> Consultar Todos </button>
				</div>
			
				<input type="hidden" name="idCliente" value="<%=usuarioLogado.getId()%>"/>
				<input type="hidden" name="alteraEndereco" value="0"/>
			</form>
        </div>
		
		<!-- FIM DOS DADOS -->
		<!-- RODAPE -->
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
		<!-- FIM DO RODAPE -->
		<!-- Para habilitar estados e cidades  -->
		<script src="../js/estados-cidades.js"> </script>
	</body>
</html>