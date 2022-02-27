<!--  CLIENTE - PEDIDOS -->
<%@page import='com.les.roupa.core.dao.*'%>
<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import='com.les.roupa.core.dao.impl.*'%>
<%@page import="java.util.List"%>   
<!DOCTYPE html>
<html>
   <head>
      <title>Mirror Fashion</title>
      <link rel="shortcut icon" href="./favicon.ico">
      <link rel="stylesheet" type="text/css" href="./css/pedidos.css">
      <meta charset="utf-8">
      <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8"%> 
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
                ItemPedidoDAO itemDAO = new ItemPedidoDAO();
                PedidoDAO pedidoDAO = new PedidoDAO();
                ProdutoDAO produtoDAO = new ProdutoDAO();
                Usuario usuarioLogado = new Usuario();
                EnderecoDAO enderecoDAO = new EnderecoDAO();
                CartaoCreditoDAO cartaocreditoDAO = new CartaoCreditoDAO();
                ClienteDAO clienteDAO = new ClienteDAO();
                
                HttpSession sessao = request.getSession();
                usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
                
                
                String idPedido = (String)request.getAttribute("id_pedido");
                List<ItemPedido> itemPedido = itemDAO.consultarItemPedidoByIdPedido(idPedido);
                List<Pedido> pedido = pedidoDAO.consultarPedidoById(idPedido);
                List<Endereco> enderecos = enderecoDAO.consultarEnderecoById(pedido.get(0).getIdEndereco());
                List<CartaoCredito> cartao = cartaocreditoDAO.consultarCartCreditoById(pedido.get(0).getCartao1());
                List<CartaoCredito> cartao2 = cartaocreditoDAO.consultarCartCreditoById(pedido.get(0).getCartao2());
                List<Cliente> clientes = clienteDAO.consultarClienteById(pedido.get(0).getIdCliente());
           %>
   <body>
      <!-- CONTAINER - DESTAQUE - PARTE PRINCIPAL-->
      <div class="container destaque">
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
        
         <!-- DADOS SOBRE O PEDIDO  -->
         <form class="formularioPedidos">
            DETALHES DO PEDIDO nº 00<%=pedido.get(0).getId()%> - Cliente: <%=clientes.get(0).getNome() %><button type="submit" onclick="window.history.go(-1); return false;" id="btnVoltarDetalhe">Voltar</button>
            <br>
            <br>
            <table>
            <td id="infosPedido">
                <div>
                 Total Produtos: R$ <%=pedido.get(0).getPrecoTotalProduto()%>
                 <br>Frete: R$ <%=pedido.get(0).getPrecoFrete()%>0
                 <br><h10 id="desconto">Desconto: R$ <%=pedido.get(0).getDesconto()%>0</h10>
                 <br>Total: R$ <%=pedido.get(0).getPrecoTotal()%>
                </div>
            </td>
            <td id="infosPedido">
                 <div>
                 Cartão 1: <%=cartao.get(0).getBandeira()%>
                 <br>R$ <%=pedido.get(0).getValorCartao1()%>
                 <br>
                 
                </div>
            </td>
            <td id="infosPedido">
                <div>
                  Entrega: <br><%=enderecos.get(0).getLogradouro()%>, nº <%=enderecos.get(0).getNumero() %> <br>
                  <%=enderecos.get(0).getCidade() %> - <%=enderecos.get(0).getEstado() %> <br>
                  CEP <%=enderecos.get(0).getCep() %>
                </div>            
            </td>
                  
            </table>
            
            <br>
            <table id="tabelaProduto"> 
               <tr>
                  <th>Produto</th>
                  <th>R$ Unitário</th>
                  <th>Qtde.</th>
                  <th>Dt. Pedido</th>
                  <th>Status</th>
                  <th>Ações</th>
               </tr>
                <%                      
                  for(ItemPedido e : itemPedido) {
                   
                   //Aplicado o CAST
                     ItemPedido i = (ItemPedido) e;
                   // lista de produtos para ser consultado - nome e preco venda
                  List<Produto> produtoItem = produtoDAO.consultarProdutoById(i.getProduto().getId());
                %>
               
               <tr>
                  <td><%=produtoItem.get(0).getNome()%></td>
                  <td>R$ <%=produtoItem.get(0).getPrecoVenda()%></td>
                  <td><%=i.getQtdeProduto()%></td>
                  <td><%=i.getDt_cadastro()%></td>
                  <td><%=pedido.get(0).getStatusPedido() %></td>
                  <td>
                     <button type="submit" id="button_alterar"><a href="/eCommerce_roupa/pedido?id=<%=pedido.get(0).getId()%>&operacao=ALTERAR&alteraPedido=0">Alterar Status</a></button>
                  </td>
               </tr>
               
               <%
                  }
                %>
            </table>
           
         </form> 
         <input type="hidden" name="tipo" value="admin"/>
      </div>
      <!-- FIM DOS DADOS  -->
      <!-- RODAPE -->
      <footer>
         <div class="container">
            <a href="./JSP/index.jsp">
            <img src="./img/logo-rodape.png" alt="Logo Mirror Fashion"></a>
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