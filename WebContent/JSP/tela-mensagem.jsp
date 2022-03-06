<%@page import='com.les.roupa.core.dao.*'%>
<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import='com.les.roupa.core.dao.impl.*'%>
<%@page import="java.util.List"%>  

<!DOCTYPE html>
<html>
    <head>
        <title>Mirror Fashion</title>
        <link rel="shortcut icon" href="./favicon.ico">
        <link rel="stylesheet" type="text/css" href="./css/perfil.css">
        <meta charset="utf-8">
        <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
            pageEncoding="UTF-8"%>  
    </head>
    <header class="container">
        <h1><img src="./img/logo.png" alt="Mirror Fashion"></h1>
        <p class="sacola">Minha Sacola</p>
         
    <!-- AQUI INFORMA QUEM É O USUARIO LOGADO -->
         <%-- <form action="http://localhost:8080/eCommerce_roupa/login">
        <p>Olá ${usuarioLogado.nome} <button class="button" type="submit" name="operacao" value="EXCLUIR" id="btnSair">Sair</button></p>
        <script>
               function sair(){
                   alert("Você saiu com sucesso! Volte em breve!");
               }
        </script>
        </form>  --%>
        <!-- <nav class="menu-opcoes" class="links">
            <ul>
                <li><a href="../JSP/index.jsp">Home</a></li>
                <li><a href="../JSP/produtos.jsp">Produtos</a></li>
                <li><a href="../JSP/perfil.jsp">Perfil</a></li>
            </ul>
        </nav> -->
    </header>
        <%
            String mensagem = (String)request.getAttribute("mensagemStrategy");
        %>
    <body>
        <!-- CONTAINER - DESTAQUE - PARTE PRINCIPAL-->
        <div class="tela-mensagem">
                <%=mensagem %>
                <br>
                <button class="button" type="submit" id="btnVoltar" onclick="window.history.go(-1); return false;">Voltar</button>
        </div>
        <!-- FIM DOS DADOS  -->
        <!-- RODAPE -->
        <footer>
            <div class="container">
                <img src="./img/logo-rodape.png" alt="Logo Mirror Fashion">
                <ul class="social">
                    <li><a href="http://facebook.com/mirrorfashion">Facebook</a></li>
                    <li><a href="http://twitter.com/mirrorfashion">Twitter</a></li>
                    <li><a href="http://plus.google.com/mirrorfashion">Google+</a></li>
                </ul>
            </div>
        </footer>
        <!-- FIM DO RODAPE -->
    </body>
</html>
