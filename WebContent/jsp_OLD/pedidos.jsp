<!--  CLIENTE - PEDIDOS -->
<%@page import='com.les.roupa.core.dao.*'%>
<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import='com.les.roupa.core.dao.impl.*'%>

<%@page import="java.util.List"%>   
<!DOCTYPE html>
<html>
    <head>
        <title>Mirror Fashion</title>
        <link rel="shortcut icon" href="../favicon.ico">
        <link rel="stylesheet" type="text/css" href="../css/pedidos.css">
        <meta charset="utf-8">
        <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
            pageEncoding="UTF-8"%> 
             
    </head>
    <header class="container">
        <h1><a href="../JSP/index.jsp"><img src="../img/logo.png" alt="Mirror Fashion"></a></h1>
        <p class="sacola"><a href="../JSP/carrinho.jsp">Minha Sacola</a></p>
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
                * MEUS PEDIDOS * 
                <br>
                <br>
                <table id="tabelaProduto">
                    <tr>
                        <th>N. Pedido</th>
                        <th>Data Pedido</th>
                        <th>Valor</th>
                        <th>Status</th>
                        <th>Ações</th>
                    </tr>
                    <%
                      PedidoDAO dao = new PedidoDAO();
                      Pedido pedido = new Pedido();
                      Usuario usuarioLogado = new Usuario();
                      
                      HttpSession sessao = request.getSession();
                      usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
                      
                      List<Pedido> pedidos = dao.consultarPedidoByIdCliente(usuarioLogado.getId());
                      
                      for(EntidadeDominio e : pedidos) {
                    	  
                    	  // Aplicado o CAST 
                    	  Pedido p = (Pedido) e;
                    %>
                    
                    <tr>
                        <td>00<%=p.getId() %></td>
                        <td><%=p.getDt_cadastro() %></td>
                        <td>R$ <%=p.getPrecoTotal() %></td>
                        <td><%=p.getStatusPedido() %></td>
                        <td>
                          <button type="submit" value="Visualizar" id="btnVisualizar" > <a href="/eCommerce_roupa/itemPedido?id=<%= p.getId()%>&operacao=CONSULTAR">Visualizar</a> </button>
                          <button type="submit" id="btnTrocarPedido"> <a href="/eCommerce_roupa/trocaPedido?id=<%=p.getId()%>&trocaPedidoInteiro=1&operacao=SALVAR">Trocar Pedido</a></button>
                        </td>
                    </tr>
                   <%
		              }
		           %>
                </table>
               
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