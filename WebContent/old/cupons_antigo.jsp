<!-- ADMIN - CADASTRO DE CUPONS -->  
<%@page import='com.les.roupa.core.dao.*'%>
<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import='com.les.roupa.core.dao.impl.*'%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Mirror Fashion</title>
        <link rel="stylesheet" type="text/css" href="../css/clientes.css">
        <meta charset="utf-8">
        <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
            pageEncoding="UTF-8"%>
        <!-- FAVICON -->
        <link rel="shortcut icon" href="./favicon.ico">
    </head>
    <header class="container">
        <h1><a href="../JSP/index.jsp"><img src="../img/logo.png" alt="Mirror Fashion"></a></h1>
        <!-- AQUI INFORMA QUEM É O USUARIO LOGADO -->
        <form action="http://localhost:8080/eCommerce_roupa/login">
            <p>Olá ${usuarioLogado.nome} <button  type="submit" name="operacao" value="EXCLUIR" id="btnSair" onclick="sair()">Sair</button></p>
            <script>
                function sair(){
                    alert("Você saiu com sucesso! Volte em breve!");
                }
            </script>
        </form>
        <nav class="menu-opcoes" class="links">
            <ul>
                <li><a href="../JSP/admin.jsp">Home</a></li>
                <li><a href="../JSP/perfil-admin.jsp">Perfil</a></li>
                </br>
            </ul>
        </nav>
    </header>
    <body>
        <!-- CONTAINER - DESTAQUE - PARTE PRINCIPAL-->
        <div class="container destaque">
            <!--  MENU ADMIN -->
            <section class="menu-departamentos">
                <h2 style="margin-left: 5px;"> Menu</h2>
                <nav>
                    <li><a href="../JSP/consultar-cliente.jsp">Clientes</a></li>
                    <li>
                        <a href="../JSP/consultar-pedidos.jsp">Pedidos</a>
                        <ul>
                            <li><a href="">Relatorio</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="../JSP/consultar-produtos.jsp">Produtos</a>
                        <ul>
                            <li><a href="">Estoque</a></li>
                            <li><a href="">Adicionar Novo</a></li>
                        </ul>
                    </li>
                   <li><a href="../JSP/cupons.jsp">Cupons</a></li>
                </nav>
            </section>
            <!-- FIM DA SESSAO MENU ADMIN -->
        </div>
        
        <!-- FIM CONTAINER DESTAQUE -->
        <div class="container destaque">
            <form action="http://localhost:8080/eCommerce_roupa/cupom">
            <caption> * Cadastro de Cupons *</caption>
            <br>
                <div>
                    <label>Nome:</label> <input type="text" name="nome" />
                    <label>Tipo de Cupom: </label>
                        <select name="tipo">
                            <option value="Troca">Troca</option>
                            <option value="Devolucao"> Devolução</option>
                            <option value="Promocional">Promocional</option>
                            <option value="Carteira">Carteira</option>
                        </select>
                    <label>Valor:</label> <input type="text" name="valor">
                    <br>
                       <button type="submit" name="operacao" value="SALVAR" id="btnSalvar"> Salvar </button>
                    <button type="submit" name="operacao" value="CONSULTAR" id="btnConsultar"> Consultar Todos </button>
                </div>
           
            </form>        
        </div>
        <!-- RODAPE -->
        <footer>
            <div class="container">
                <a href="../JSP/admin.jsp">
                <img src="../img/logo-rodape.png" alt="Logo Mirror Fashion"></a>
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