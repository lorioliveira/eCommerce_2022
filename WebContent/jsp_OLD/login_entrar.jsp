<%@page import='com.les.roupa.core.dao.*'%>
<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import='com.les.roupa.core.dao.impl.*'%>

<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
    <head>  
        <title>Mirror Fashion</title>
        <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
            pageEncoding="UTF-8"%>  
         <link rel="stylesheet" type="text/css" href="./css/login.css"> 
        <meta charset="utf-8">
         <!-- FAVICON -->
        <link rel="shortcut icon" href="../favicon.ico">
    </head>
    
        <header class="container">
            <h1><img src="./img/logo.png" alt="Mirror Fashion"></h1>
        </header>
    <body>
        <!-- CONTAINER - FORMULARIO-->
        <div class="container destaque">
            <!-- DADOS PARA LOGAR NA CONTA  -->
            <form class="formulario" action="http://localhost:8080/eCommerce_roupa/login" method="post">
                <div>
                    <label>E-mail: </label> <input type="email" id="email" name="email" placeholder="digite seu e-mail aqui" required/> 
                    <label>Senha: </label> <input type="password" name="senha" id="senha" value="" accesskey="S" min="8" required/>
                </div>

                <div class="button_suaconta">
                    <button type="submit" name="operacao" value="CONSULTAR" id="btnEntrar">Entrar</button>
                    <button type="submit" id="btnNovaConta"> <a href="./HTML/formCliente.html">Criar nova conta</a></button>
                </div>
            </form>
        </div>
            <!-- FIM DOS DADOS DE LOGIN -->


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