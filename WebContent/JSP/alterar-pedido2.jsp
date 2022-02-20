<!-- ADMIN -->  
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
        <link rel="shortcut icon" href="../favicon.ico">
    </head>
    <header class="container">
        <h1><a href="../JSP/index.jsp"><img src="../img/logo.png" alt="Mirror Fashion"></a></h1>
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
                </br>
            </ul>
        </nav>
    </header>
    <body>
        <!-- CONTAINER - DESTAQUE - PARTE PRINCIPAL-->
        <div class="container destaque">
            <!--  MENU ADMIN -->
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
            <!-- FIM DA SESSAO MENU ADMIN -->
        </div>
        <!-- FIM CONTAINER DESTAQUE -->
        <div class="container destaque">
            <!-- FORMULÃRIO DE NOVA CONTA  -->
            <form class="formulario" action="http://localhost:8080/eCommerce_roupa/cadastro" method="post">
                <caption> * ALTERAR STATUS DOS PEDIDOS *</caption>
                <br>
                <br>
                <div class="tabela">
                    <table>
                        <tr>
                            <th>Pedido</th>
                            <th>Cliente</th>
                            <th>Valor</th>
                            <th>Status</th>
                            <th id="acoes">Ações</th>
                        </tr>
                        <!-- ************************************************** -->
                        <tr>
                            <td>0001</td>
                            <td>Lorena</td>
                            <td>R$ 89.89</td>
                            <td>
                                <select>
                                    <option>EM PROCESSAMENTO</option>
                                    <option>PAGAMENTO REALIZADO</option>
                                    <option>EM TRANSPORTE</option>
                                    <option>TROCA SOLICITADA</option>
                                    <option>TROCA AUTORIZADA</option>
                                    <option>TROCA REJEITADA</option>
                                    <option>CANCELAMENTO SOLICITADO</option>
                                    <option>CANCELAMENTO REJEITADO</option>
                                    <option>TROCA ACEITA</option>
                                    <option>CANCELAMENTO ACEITO</option>
                                    <option>ENTREGA REALIZADA</option>
                                    <option>TROCA EFETUADA</option>
                                    <option>CANCELAMENTO EFETUADO</option>
                                </select>
                            </td>
                            <td>
                                <div class="button">
                                    <button type="submit" name="operacao" value="ALTERAR" id="btnAlterarStatus">Alterar Status</button>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </form>
        </div>
        <!-- RODAPÃ -->
        <footer>
            <div class="container">
                <a href="../HTML/admin.html">
                <img src="../img/logo-rodape.png" alt="Logo Mirror Fashion"></a>
                <ul class="social">
                    <!-- <li id="copy">&copy; Copyright MF - Todos os direitos protegidos - 2021</li> -->
                    <li><a href="http://facebook.com/mirrorfashion">Facebook</a></li>
                    <li><a href="http://twitter.com/mirrorfashion">Twitter</a></li>
                    <li><a href="http://plus.google.com/mirrorfashion">Google+</a></li>
                </ul>
            </div>
        </footer>
        <!-- FIM DO RODAPÃ -->
    </body>
</html>