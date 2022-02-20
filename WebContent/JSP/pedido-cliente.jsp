<!-- ADMIN - VISUALIZAR PEDIDOS PELO CONSULTAR CLIENTE -->  
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
      <link rel="shortcut icon" href="./favicon.ico">
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
         </ul>
      </nav>
   </header>
   
    <%
        PedidoDAO pedidoDAO = new PedidoDAO();
        Pedido pedido = new Pedido();
        ClienteDAO clienteDAO = new ClienteDAO();
        
        String id = (String)request.getAttribute("id");
        List<Pedido> pedidos = pedidoDAO.consultarPedidoByIdCliente(id);
        
          for(Pedido e : pedidos) {
               
               // Aplicado o CAST 
               Pedido p = (Pedido) e;
               
               List<Cliente> clientes = clienteDAO.consultarClienteById(p.getIdCliente());
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
         <!-- FIM DO MENU ADMIN -->
      </div>
      
      <div class="container destaque">
      
         <!-- CONSULTAR PEDIDOS REALIZADOS POR CLIENTE-->
         
            <caption>* Pedidos Realizados pelo Cliente <%=clientes.get(0).getNome() %>*</caption>
            <br><br>
            <div class="tabela">
               <table>
                  <tr>
                     <th>Nº Pedido</th>
                     <th>Data Pedido</th>
                     <th>Valor</th>
                     <th>Status</th>
                     <th id="acoes">Ações</th>
                  </tr>
               
                  <!-- CONTEUDO DA TABELA E BOTOES AO LADO -->
                  <tr>
                     <td>00<%=p.getId()%></td>
                    <td><%=clientes.get(0).getNome() %></td>
                     <td>R$ <%=p.getPrecoTotal() %></td>
                     <td><%=p.getStatusPedido() %></td>
                     <td>
                        <div class="button">
                           <a href="/eCommerce_roupa/pedido?id=<%= p.getId()%>&operacao=ALTERAR&alteraPedido=0"><button type="submit" id="button_alterar">Alterar Status</button></a>
                        </div>
                     </td>
                  </tr>
                 <%
                     }
                 %>
               </table>
            </div>
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
      <!-- FIM DO RODAPE -->
   </body>
</html>