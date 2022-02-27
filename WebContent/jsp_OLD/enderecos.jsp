<%@page import='com.les.roupa.core.dao.*'%>
<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import='com.les.roupa.core.dao.impl.*'%>

<%@page import="java.util.List"%>	
<!DOCTYPE html>
<html>
	<head>
		<title>Mirror Fashion</title>
		<link rel="stylesheet" type="text/css" href="./css/enderecos.css">
		<meta charset="utf-8">
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    		pageEncoding="UTF-8"%>	
	</head>
	<header class="container">
		<h1><a href="./JSP/index.jsp"><img src="./img/logo.png" alt="Mirror Fashion"></a></h1>
		 <p class="sacola"><a href="./JSP/carrinho.jsp">Minha Sacola</a></p>
		 <!-- AQUI INFORMA QUEM É O USUARIO LOGADO -->
         <form action="http://localhost:8080/eCommerce_roupa/login">
        <p>Olá ${usuarioLogado.nome} <button type="submit" name="operacao" value="EXCLUIR" onclick="sair()" id="btnSair">Sair</button></p>
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
	<body>
		<!-- CONTAINER - DESTAQUE - PARTE PRINCIPAL-->
		<div class="container destaque">
			<!--Seção do MENU PERFIL CLIENTE -->
			<section class="menu-departamentos">
				<h2 style="margin-left: 5px;">Perfil</h2>
				<nav>
					<li>
						<a href="./JSP/meusdados.jsp">Meus Dados</a>
						<ul>
							<li><a href="./JSP/meusenderecos.jsp">Endereços</a></li>
							<li><a href="./JSP/cartoes.jsp">Cartões e Cupons</a></li>
							<li><a href="./JSP/alterarsenha.jsp">Alterar Senha</a></li>
						</ul>
					</li>
					<li><a href="./JSP/pedidos.jsp">Meus Pedidos</a></li>
					
				
				</nav>
			</section>	
			<!-- FIM DA SEÇÃO SUA CONTA -->
			<!-- DADOS  DE ENDEREOO  -->
				<div class="formulario_end">
				<caption>* ENDEREÇOS *</caption> 
				<button class="button" type="submit" id="btnVoltar" onclick="window.history.go(-1); return false;">Voltar</button>
				
                <table>
							<tr>
								<th>CEP</th>
								<th>Tipo Resid.</th>
								<th>Logradouro</th>
								<th>Nº</th>
								<th>Bairro</th>
								<th>Cidade</th>
								<th>Est.</th>
								<th>País</th>
								<th>Obs</th>
								<th>End.</th>
								<th>Ações</th>
										
							</tr>
							<%
								EnderecoDAO daoE = new EnderecoDAO();
								Endereco endereco = new Endereco();
								
								ClienteDAO dao = new ClienteDAO();
						        Usuario usuarioLogado = new Usuario();
						        
						        HttpSession sessao = request.getSession();
						        usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
						        
						        // Trazer os endereços do cliente logado
								List<Endereco> enderecos = daoE.consultarEnderecoByIdCliente(usuarioLogado.getId());

								for(Endereco e : enderecos){

									// Aplicado o CAST 
									Endereco d = (Endereco) e;
							%>	
							
							<!-- CONTEUDOS DA TABELA E BOTOES AO LADO -->
							<tr>
								<td><%=d.getCep() %></td>
								<td><%=d.getTipoResidencia() %></td>
								<td><%=d.getLogradouro() %></td>
								<td><%=d.getNumero() %></td>
								<td><%=d.getBairro() %></td>
								<td><%=d.getCidade() %></td>
								<td><%=d.getEstado() %></td>
								<td><%=d.getPais() %></td>
								<td><%=d.getObservacoes()%></td>
								<td><%=d.getTipoEnd()%></td>
								<td>
								  <a href="/eCommerce_roupa/cadastroEndereco?id=<%= d.getId()%>&operacao=ALTERAR&alteraEndereco=0"><button id="btnAlterar">Alterar</button></a>
								  <a href="/eCommerce_roupa/cadastroEndereco?id=<%= d.getId()%>&operacao=EXCLUIR"><button id="btnExcluir">Excluir</button></a>
								</td>
							</tr>
							
							<%
								}
							%>
					</table>
					</div>
        	</div>
		<!-- FIM DOS DADOS DE PERFIL -->
		
		<!-- RODAPE -->
		<footer>
			<div class="container">
				<a href="./JSP/index.jsp">
				<img src="./img/logo-rodape.png" alt="Logo Mirror Fashion"></a>
				<ul class="social">
					<li><a href="http://facebook.com/mirrorfashion">Facebook</a></li>
					<li><a href="http://twitter.com/mirrorfashion">Twitter</a></li>
					<li><a href="http://plus.google.com/mirrorfashion">Google+</a></li>
				</ul>
			</div>
		</footer>
		<!-- FIM DO RODAPE -->
		<!-- Para habilitar estados e cidades  -->
		<script src="./js/estados-cidades.js"> </script>
	</body>
</html>
