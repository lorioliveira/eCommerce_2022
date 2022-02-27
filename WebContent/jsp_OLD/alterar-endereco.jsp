<!-- ALTERAR ENDERECO -->	
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
		 <link rel="stylesheet" type="text/css" href="./css/enderecos.css"> 
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
			EnderecoDAO dao = new EnderecoDAO();
			Usuario usuarioLogado = new Usuario();
			
			HttpSession sessao = request.getSession();
			usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
			
			String idEndereco = (String)request.getAttribute("idEndereco");
			
			List<Endereco> endereco = dao.consultarEnderecoById(idEndereco);
		%>
		
		
	<body>
		<!-- CONTAINER - DESTAQUE - PARTE PRINCIPAL-->
		<div class="container destaque">
			
			<!-- SESSAO MENU ADMIN -->
				<section class="menu-departamentos">
				<h2 style="margin-left: 5px;">Perfil</h2>
				<nav>
					<li>
						<a href="./JSP/perfil.jsp">Meus Dados</a>
						<ul>
							<li><a href="./JSP/meusenderecos.jsp">Endereços</a></li>
							<li><a href="./JSP/cartoes.jsp">Cartões e Cupons</a></li>
							<li><a href="./JSP/alterarsenha.jsp">Alterar Senha</a></li>
						</ul>
					</li>
					<li><a href="./JSP/pedidos.jsp">Meus Pedidos</a></li>
					
				</nav>
			</section>
				<!-- FIM DA SESSAÂO MENU ADMIN -->
		</div>
		<!-- FIM CONTAINER DESTAQUE -->

		<div class="container destaque">
					<!-- FORMULÁRIO DE NOVA CONTA  -->
			<form class="formulario" action="http://localhost:8080/eCommerce_roupa/cadastroEndereco" >

				<div>* EDITAR ENDEREÇO *</div><br>
				<div>					
				<label>Tipo Residencia: </label> <input type="hidden" value="<%=endereco.get(0).getTipoResidencia()%>"/>
					<select id="tipoResidencia" name="tipoResidencia">
						<option value="casa">Casa</option>
						<option value="apartamento">Apto</option>
						<option value="outro">Outro</option>
					</select>
					<label class="CEP">CEP:</label><input type="text" id="CEP" name="cep" class="form-control cep-mask" placeholder="00000-000" maxlength="8" value="<%=endereco.get(0).getCep()%>"  />
					<label id="endereco" >Endereço:</label> <input type="hidden" value="<%=endereco.get(0).getTipoEnd()%>" />
						<select name="tipoEndereco">
						<option value="Cobranca e Entrega">Cobrança e Entrega</option>
							<option value="Cobranca">Cobrança</option>
							<option value="Entrega">Entrega</option>
						</select>
				</div>
				<label id="logradouro">Logradouro:</label> <input type="text" name="logradouro" id="logradouro"  value="<%=endereco.get(0).getLogradouro()%>"/>
				<label id="numero">Número:</label> <input type="text" name="numero" id="numero" value="<%=endereco.get(0).getNumero()%>" />
				
					<label id="bairro">Bairro:</label><input type="text" name="bairro" id="bairro" value="<%=endereco.get(0).getBairro()%>"  />
					<!-- Estado / Cidade / País -->
					<label> Estado: </label>
					<select id="estado" name="estado" onchange="buscaCidades(this.value)">
					<option><%=endereco.get(0).getEstado()%></option>
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
				
					<label> Cidade: </label>
					<select id="cidade" name="cidade">
						<option><%=endereco.get(0).getCidade()%></option>
						<script type="text/javascript" src="./js/estados-cidades.js" charset="utf-8"></script>
					</select>
				
					<label>Pais: </label>
					<select id="pais" name="pais">
						<option><%=endereco.get(0).getPais()%></option>
					</select>
				
				<div>
				<textarea id="textarea" name="observacoes" ><%=endereco.get(0).getObservacoes()%></textarea>
				
					<button class="button" type="submit" id="btnVoltar" onclick="window.history.go(-1); return false;">Voltar</button>
					<button type="submit" name="operacao" value="ALTERAR" id="btnSalvar">Confirmar</button>
					
					<input type="hidden" name="id" value="<%=endereco.get(0).getId()%>"/>
					<input type="hidden" name="alteraEndereco" value="1"/>
					
				</div>
			</form>
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
	<!-- Para habilitar estados e cidades  -->
		<script src="./js/estados-cidades.js"> </script>
	<!-- FIM DO RODAPE -->

	</body>
</html>