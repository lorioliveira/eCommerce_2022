<!-- ADMIN -->  
<%@page import='com.les.roupa.core.dao.*'%>
<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import='com.les.roupa.core.dao.impl.*'%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Mirror Fashion</title>
        <link rel="stylesheet" type="text/css" href="./css/clientes.css">
        <meta charset="utf-8">
        <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
            pageEncoding="UTF-8"%>
        <!-- FAVICON -->
        <link rel="shortcut icon" href="../favicon.ico">
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
                </br>
            </ul>
        </nav>
    </header>
            <%
            PedidoDAO pedidoDAO = new PedidoDAO();
            Usuario usuarioLogado = new Usuario();
            ClienteDAO clienteDAO = new ClienteDAO();
            
            HttpSession sessao = request.getSession();
            usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
            
            String id = (String)request.getAttribute("id_pedido");
            
            List<Pedido> pedido = pedidoDAO.consultarPedidoById(id);
            
            List<Cliente> clientes = clienteDAO.consultarClienteById(pedido.get(0).getIdCliente());
            
            %>
    <body>
        <!-- CONTAINER - DESTAQUE - PARTE PRINCIPAL-->
        <div class="container destaque">
            <!--  MENU ADMIN -->
            <section class="menu-departamentos">
                <h2 style="margin-left: 5px;">Menu</h2>
                <nav>
                    <li><a href="./JSP/consultar-cliente.jsp">Clientes</a></li>
                    <li>
                        <a href="./JSP/consultar-pedidos.jsp">Pedidos</a>
                        <ul>
                            <li><a href="./JSP/grafico1.jsp">Relatorio</a></li>
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
            <!-- FIM DA SESSAO MENU ADMIN -->
        </div>
        <!-- FIM CONTAINER DESTAQUE -->
        <div class="container destaque">
            <!-- FORMULARIO  -->
            
            <form class="formulario" action="http://localhost:8080/eCommerce_roupa/pedido">
                <caption> * ALTERAR STATUS DO PEDIDO *</caption> <button class="button" type="submit"onclick="window.history.go(-1); return false;" id="btnVoltar" >Voltar</button>
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
                            <td>00<%=pedido.get(0).getId()%></td>
                            <td><%=clientes.get(0).getNome()%></td>
                            <td>R$ <%=pedido.get(0).getPrecoTotal() %></td>
                            <td>
                                <select name="statusPedido" value="<%=pedido.get(0).getStatusPedido() %>"> 
                                 <option selected disabled ><%=pedido.get(0).getStatusPedido() %></option>
                                    <option value="EM PROCESSAMENTO">EM PROCESSAMENTO</option>
                                    <option value="PAGAMENTO REALIZADO">PAGAMENTO REALIZADO</option>
                                    <option value="EM TRANSPORTE">EM TRANSPORTE</option>
                                    <option value="TROCA SOLICITADA">TROCA SOLICITADA</option>
                                    <option value="TROCA AUTORIZADA">TROCA AUTORIZADA</option>
                                    <option value="TROCA REJEITADA">TROCA REJEITADA</option>
                                    <option value="CANCELAMENTO SOLICITADO">CANCELAMENTO SOLICITADO</option>
                                    <option value="CANCELAMENTO REJEITADO">CANCELAMENTO REJEITADO</option>
                                    <option value="TROCA ACEITA">TROCA ACEITA</option>
                                    <option value="CANCELAMENTO ACEITO">CANCELAMENTO ACEITO</option>
                                    <option value="ENTREGA REALIZADA">ENTREGA REALIZADA</option>
                                    <option value="TROCA EFETUADA">TROCA EFETUADA</option>
                                    <option value="CANCELAMENTO EFETUADO">CANCELAMENTO EFETUADO</option>
                                </select>
                            </td>
                            <td>
                               <button type="submit" name="operacao" value="ALTERAR" id="btnAltStatus">Alterar Status</button>
                            </td>
                        </tr>
                    </table>
                </div>
                <input type="hidden" name="alteraPedido" value="1"/>
                <input type="hidden" name="id" value="<%=pedido.get(0).getId()%>"/>
            </form>
        </div>
        <!-- RODAPÃ -->
        <footer>
            <div class="container">
                <a href="./HJSP/admin.jsp">
                <img src="./img/logo-rodape.png" alt="Logo Mirror Fashion"></a>
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