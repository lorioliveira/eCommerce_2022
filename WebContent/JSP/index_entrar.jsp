<%@page import='com.les.roupa.core.dao.*'%>
<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import='com.les.roupa.core.dao.impl.*'%>

<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet"  href="./css/estilos.css">
        <title>Mirror Fashion</title>
        <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    		pageEncoding="UTF-8"%>	
        <meta name="viewport" content="width=device-width">
        <!-- FAVICON -->
        <link rel="shortcut icon" href="./favicon.ico">
    </head>
    <header class="container">
        <h1><img src="./img/logo.png" alt="Mirror Fashion"></h1>
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
    <body>
        <!-- CONTAINER - DESTAQUE - PARTE PRINCIPAL-->
        <div class="container destaque">
            
            <!--Sessao das Categorias -->
            <section class="menu-departamentos">
                <h2 style="margin-left: 5px;"> Categorias</h2>
                <nav>
                    <li>
                        <a href="./JSP/blusas.jsp">Blusas</a></li>
                    <li><a href="./JSP/calcas.jsp">Calças</a></li>
                    <li><a href="./JSP/vestidos.jsp">Vestidos</a></li>
                </nav>
            </section>
            <!-- FIM DA SECAO  -->

            <!-- SLIDES DE BANNERS -->
            <!-- Na apostila, so mostra a class com pause, mas na vdd tem que colocar o play tbm, ai funciona.	
             OUTRA COISA: SO FUNCIONA 100% QUANDO COLOCA A VIRGULA!!  -->
            <a href="./JSP/produtos.jsp"> <img src="./img/Clothingindustry.png"></a>
            <a href="" class="play, pause"></a>
            <!-- FIM DOS SLIDES DE BANNERS -->

        </div>
        <!-- FIM CONTAINER DESTAQUE -->

        <!-- RODAPE -->
        <footer>
            <div class="container">
                <a href="./JSP/index.jsp">
                <img src="./img/logo-rodape.png" alt="Logo Mirror Fashion"></a>
                <li id="copy">&copy; Copyright - Todos os direitos protegidos - 2021</li>
                <ul class="social">
                    <li>
                        <a href="http://plus.google.com/mirrorfashion">Google+</a>
                    </li>
                    <li>
                        <a href="http://twitter.com/mirrorfashion">Twitter</a>
                    </li>
                    <li>
                        <a href="http://facebook.com/mirrorfashion">Facebook</a>
                    </li>
                </ul>
            </div>
        </footer>
        <!-- FIM DO RODAPE -->
        <!-- Referenciando o arquivo JS no index.html -->
        <script src="./js/home2.js"> </script>
    </body>
</html>