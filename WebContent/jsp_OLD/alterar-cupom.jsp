<!-- ALTERAR CUPOM - ADMIN-->	
<%@page import='com.les.roupa.core.dao.*'%>
<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import='com.les.roupa.core.dao.impl.*'%>

<%@page import="java.util.List"%>
		
<!DOCTYPE html>
<html>
	<head>
        <title>Mirror Fashion</title>
        <link rel="stylesheet" type="text/css" href="./css/cupom.css"">
        <meta charset="utf-8">
        <!-- FAVICON -->
        <link rel="shortcut icon" href="./favicon.ico">
        <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
            pageEncoding="UTF-8"%>
    </head>
    <header class="container">
        <h1><a href="./JSP/admin.jsp"><img src="./img/logo.png" alt="Mirror Fashion"></a></h1>
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
                <li><a href="./JSP/admin.jsp">Home</a></li>
                <li><a href="./JSP/perfil-admin.jsp">Perfil</a></li>
                </li>
            </ul>
        </nav>
    </header>
		
		<%
			CupomDAO cupomDAO = new CupomDAO();
		    Usuario usuarioLogado = new Usuario();
		    ClienteDAO clienteDAO = new ClienteDAO();
            Cliente clienteAux = new Cliente();
            List<EntidadeDominio> clientes = clienteDAO.consultarClienteByTipo(clienteAux);
		    
		    HttpSession sessao = request.getSession();
            usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
			
			String idCupom = (String)request.getAttribute("idCupom");
			List<Cupom> cupom = cupomDAO.consultarCupomById(idCupom);
		%>
		
	<body>
        <!-- CONTAINER PARTE PRINCIPAL-->
        <div class="container destaque">
            <!-- SEÇÃO MENU DE ADMIN -->
            <section class="menu-departamentos">
                <h2 style="margin-left: 5px;">Menu</h2>
                <nav>
                    <li><a href="./JSP/consultar-cliente.jsp">Clientes</a></li>
                    <li>
                        <a href="./JSP/consultar-pedidos.jsp">Pedidos</a>
                        <ul>
                            <li><a href="../JSP/grafico1.jsp">Relatorio</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="./JSP/consultar-produtos.jsp">Produtos</a>
                        <ul>
                            <li><a href="./JSP/estoque.jsp">Estoque</a></li>
                            <li><a href="./JSP/novo-produto.jsp">Adicionar Novo</a></li>
                        </ul>
                    </li>
                    <li><a href="./JSP/cupons.jsp">Cupons</a></li>
                </nav>
            </section>
				<!-- FIM DA SESSAO  -->
		</div>
		<!-- FIM CONTAINER DESTAQUE -->

		<div class="container destaque">
		
			<!-- FORMULARIO DE ALTERAR CARTAO  -->
			<form class="formCupom" action="http://localhost:8080/eCommerce_roupa/cupom">
            <caption> * Alterar Cupom *</caption>
            <br>
            <br>
               <label>Nome:</label> <input type="text" name="nome" id="nome" value="<%=cupom.get(0).getNome() %>" />
               <label id="tipoCupom">Tipo de Cupom: </label> <input type="hidden" value="<%=cupom.get(0).getTipo()%>" />
                   <select name="tipo">
                       <option value="Troca">Troca</option>
                       <option value="Devolucao"> Devolução</option>
                       <option value="Promocional">Promocional</option>
                       <option value="Carteira">Carteira</option>
                   </select><br>
               <label>Valor:</label> <input type="text" name="valor" id="valorCupom" value="<%=cupom.get(0).getValor() %>">
                 Cliente Vinculado:
                <select name="id_cliente">
                    <option disabled selected>Selecione </option>
                       <%
                           for(EntidadeDominio e: clientes){
                             
                            // Aplicado o CAST 
                             Cliente c = (Cliente) e;
                           %>
                       <option value="<%=c.getId()%>"> <%=c.getNome()%></option>
                       <%
                           }
                         %>
                 </select>
                  Utilizado: 
                 <select name="utilizado">
                    <option disabled selected>Selecione </option>
                       <option value="Sim">Sim</option>
                       <option value="Nao">Não</option>                       
                 </select>
               <br>
               <br>
               
                <button class="button" type="submit"onclick="window.history.go(-1); return false;" id="btnVoltar" >Voltar</button>
				<button class="button" type="submit" name="operacao" value="ALTERAR" id="btnSalvar">Salvar Alterações</button>
						
				<input type="hidden" name="alteraCupom" value="1"/>
				<input type="hidden" name="id" value="<%=cupom.get(0).getId()%>"/>
		      </form>
		 </div>
			

	<!-- RODAPE -->
	<footer>
		<div class="container">
			<a href="./JSP/admin.jsp">
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