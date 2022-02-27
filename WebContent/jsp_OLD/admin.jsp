<!-- ADMIN -->          
<!DOCTYPE html>
<html>
    <head>
        <title>Mirror Fashion</title>
        <link rel="stylesheet" type="text/css" href="../css/admin.css">
        <meta charset="utf-8">
        <!-- FAVICON -->
        <link rel="shortcut icon" href="../favicon.ico">
        <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
            pageEncoding="UTF-8"%>
    </head>
    <header class="container">
        <h1><a href="../JSP/admin.jsp"><img src="../img/logo.png" alt="Mirror Fashion"></a></h1>
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
                <li><a href="../JSP/admin.jsp">Home</a></li>
                <li><a href="../JSP/perfil-admin.jsp">Perfil</a></li>
                </li>
            </ul>
        </nav>
    </header>
    <body>
        <!-- CONTAINER PARTE PRINCIPAL-->
        <div class="container destaque">
            <!-- SEÇÃO MENU DE ADMIN -->
            <section class="menu-departamentos">
                <h2 style="margin-left: 5px;">Menu</h2>
                <nav>
                    <li><a href="../JSP/consultar-cliente.jsp">Clientes</a></li>
                    <li>
                        <a href="../JSP/consultar-pedidos.jsp">Pedidos</a>
                        <ul>
                            <li><a href="../JSP/grafico1.jsp">Relatorio</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="../JSP/consultar-produtos.jsp">Produtos</a>
                        <ul>
                            <li><a href="../JSP/estoque.jsp">Estoque</a></li>
                            <li><a href="../JSP/novo-produto.jsp">Adicionar Novo</a></li>
                        </ul>
                    </li>
                    <li><a href="../JSP/cupons.jsp">Cupons</a></li>
                </nav>
            </section>
            <!-- FIM DA SECAO DO MENU DE ADMIN -->
            <!-- SLIDES DE BANNERS -->
            <a href="../JSP/admin.jsp"> <img src="../img/boss.png"></a>
            <a href="" class="play, pause"></a>
            <!-- FIM DOS SLIDES DE BANNERS -->
        </div>
        <!-- FIM CONTAINER DESTAQUE -->
        <!-- RODAPE -->
        <footer>
            <div class="container">
                <a href="../JSP/admin.jsp">
                <img src="../img/logo-rodape.png" alt="Logo Mirror Fashion"></a>
                <ul class="social">
                    <!-- <li id="copy">© Copyright MF - Todos os direitos protegidos - 2021</li> -->
                    <li><a href="http://facebook.com/mirrorfashion">Facebook</a></li>
                    <li><a href="http://twitter.com/mirrorfashion">Twitter</a></li>
                    <li><a href="http://plus.google.com/mirrorfashion">Google+</a></li>
                </ul>
            </div>
        </footer>
        <!-- FIM DO RODAPE -->
        <!-- Referenciando o arquivo JS no admin.html -->
        <script src="../js/admin.js"> </script>
    </body>
</html>