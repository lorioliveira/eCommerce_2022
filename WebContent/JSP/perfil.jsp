<%@page import='com.les.roupa.core.dao.*'%>
<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import='com.les.roupa.core.dao.impl.*'%>
<%@page import="java.util.List"%>  

<!DOCTYPE html>
<html>
    <head>
        <title>Mirror Fashion</title>
        <link rel="shortcut icon" href="../favicon.ico">
        <link rel="stylesheet" type="text/css" href="../css/perfil.css">
        <meta charset="utf-8">
        <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    		pageEncoding="UTF-8"%>	
    </head>
    <header class="container">
        <h1><a href="../JSP/index.jsp"><img src="../img/logo.png" alt="Mirror Fashion"></a></h1>
        <p class="sacola"><a href="../JSP/carrinho.jsp">Minha Sacola</a></p>
         
 <!-- AQUI INFORMA QUEM É O USUARIO LOGADO -->
         <form action="http://localhost:8080/eCommerce_roupa/login">
        <p>Olá ${usuarioLogado.nome} <button class="button" type="submit" name="operacao" value="EXCLUIR" id="btnSair">Sair</button></p>
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
   
    <body>
        <!-- CONTAINER - DESTAQUE - PARTE PRINCIPAL-->
        <div class="container destaque">
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
            
            <!-- DADOS PESSOAIS DO CLIENTE  -->
            <form class="formulario">
                <div>
					Aqui você poderá visualizar seus dados cadastrais, endereços, cartões e muito mais!
				<br>
				</div>
            </form>
        </div>
        <!-- FIM DOS DADOS  -->
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
    </body>
</html>