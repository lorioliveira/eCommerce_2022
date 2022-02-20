<!--  CLIENTE - PEDIDOS -->
<%@page import='com.les.roupa.core.dao.*'%>
<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import='com.les.roupa.core.dao.impl.*'%>
<%@page import="java.util.List"%> 
<%@page import="java.util.ArrayList"%>
  
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
      <h1><a href="./JSP/index.jsp"><img src="./img/logo.png" alt="Mirror Fashion"></a></h1>
      <p class="sacola"><a href="./JSP/carrinho.jsp">Minha Sacola</a></p>
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
            <li><a href="./JSP/index.jsp">Home</a></li>
            <li><a href="./JSP/produtos.jsp">Produtos</a></li>
            <li><a href="./JSP/perfil.jsp">Perfil</a></li>
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
                
                HttpSession sessao = request.getSession();
                usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
                List<PedidoTroca> itensPedidoTrocaEmSessao = new ArrayList<>();
                
                
                String idPedido = (String)request.getAttribute("id_pedido");
                List<ItemPedido> itemPedido = itemDAO.consultarItemPedidoByIdPedido(idPedido);
                List<Pedido> pedido = pedidoDAO.consultarPedidoById(idPedido);
                List<Endereco> enderecos = enderecoDAO.consultarEnderecoById(pedido.get(0).getIdEndereco());
                List<CartaoCredito> cartao = cartaocreditoDAO.consultarCartCreditoById(pedido.get(0).getCartao1());
                List<CartaoCredito> cartao2 = cartaocreditoDAO.consultarCartCreditoById(pedido.get(0).getCartao2());
                
                // pega o objeto salvo em Sessão com o nome "itensPedidoTroca",
                // e passa para o "itensPedidoTrocaEmSessao" (fazendo o CAST para o tipo List<PedidoTroca>)
                itensPedidoTrocaEmSessao = (List<PedidoTroca>) sessao.getAttribute("itensPedidoTroca");
                %>
   <body>
      <!-- CONTAINER - DESTAQUE - PARTE PRINCIPAL-->
      <div class="container destaque">
         <section class="menu-departamentos">
            <h2 style="margin-left: 5px;">Perfil</h2>
            <nav>
               <li>
                  <a href="./JSP/meusdados.jsp">Meus Dados</a>
                  <ul>
                     <li><a href="./JSP/meusenderecos.jsp">Enderecos</a></li>
                     <li><a href="./JSP/cartoes.jsp">Cartoes e Cupons</a></li>
                     <li><a href="./JSP/alterarsenha.jsp">Alterar Senha</a></li>
                  </ul>
               </li>
               <li><a href="./JSP/pedidos.jsp">Meus Pedidos</a></li>
            </nav>
         </section>
        
         <!-- DADOS SOBRE O PEDIDO  -->
         <div class="formularioPedidos">
            DETALHES DO PEDIDO nº 00<%=pedido.get(0).getId()%> - Data Pedido: <%=pedido.get(0).getDt_cadastro()%><button type="submit" onclick="window.history.go(-1); return false;" id="btnVoltarDetalhe">Voltar</button>
            <br>
            <br>
            <table>
            <td id="infosPedido">
                <div>
                 Total Produtos: R$ <%=pedido.get(0).getPrecoTotalProduto()%>
                 <br>Frete: R$ <%=pedido.get(0).getPrecoFrete()%>0
                 <br><h10 id="desconto">Desconto: R$ <%=pedido.get(0).getDesconto()%></h10>
                 <br>Total: R$ <%=pedido.get(0).getPrecoTotal()%>
                </div>
            </td>
            <td id="infosPedido">
                 <div>
                  Cartão: <%=cartao.get(0).getBandeira()%><br>
                   Val.:<%=cartao.get(0).getValidade()%>
                 <br>R$ <%=pedido.get(0).getValorCartao1()%>
                 <br>
                 <%--  Cartão 2: <%=cartao2.get(0).getBandeira()%><br>
                   Val.:<%=cartao2.get(0).getValidade()%>
                 <br>R$ <%=pedido.get(0).getValorCartao2()%> --%>
                 
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
                  <th>Valor Produto</th>
                  <th>Qtde.</th>
                  <th>Status</th>
                  
                   <% if(pedido.get(0).getStatusPedido().equals("ENTREGA REALIZADA")) { %>
                    <th>Qtde Troca</th>
                    <th></th>
                    <th>Total Troca</th>
                <%} %>
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
                  <td><%=pedido.get(0).getStatusPedido() %></td>
                  
                  <!-- INPUT E BOTAO PARA TROCAR ITEM -->
                  
                   <% if(pedido.get(0).getStatusPedido().equals("ENTREGA REALIZADA")) { %>
                    <form action="http://localhost:8080/eCommerce_roupa/trocaPedido">
                       <td> <input type="text" name="qtdeItemParaTroca" id="qtdeTroca" ></td>
                        
                        <td><button type="submit" name="operacao" value="CONSULTAR" id="btnTrocar">Trocar item</button></td>
                   
                        <input type="hidden" name="idPedido" value="<%=i.getIdPedido()%>" />
                        <input type="hidden" name="idItemPedido" value="<%=i.getId()%>" />
                        
                    </form>
                    <%
                      }
                   %>
                    
                    <!-- verifica se esse item do pedido foi acionado para troca, 
                    caso esse item esteja na lista de troca, será mostrado a quantidade dele na tela,
                    para poder saber a quantidade do item que esta sendo trocado -->
                    <% for (PedidoTroca exchange : itensPedidoTrocaEmSessao){
                        // o ID do item que esta salvo na Sessão, é IGUAL ao ID do item da lista atual
                        if (exchange.getItemPedido().getId().equals(i.getId())){ %>
                            <td>
                                <!-- Mostra a quantidade do Item que esta sendo Trocado -->
                                <input style="width: 30px; height: 30px;text-align:center;" type="text" class="form-control" name="qtdeItemParaTroca" value="<%=exchange.getItemPedido().getQtdeProduto()%>" onkeypress="return event.charCode >= 48 && event.charCode <= 57" maxlength="3" required>
                            </td>
                    <%
                        }
                     } %>
                    
                  
               </tr>
               <%
                  }
                %>
            </table>
                  <!-- BOTAO PARA FINALIZAR TROCA -->
                  <div>
                  <% if (itensPedidoTrocaEmSessao.size() > 0) {%>
	                  <form action="http://localhost:8080/eCommerce_roupa/trocaPedido">
	                    <button type="submit" name="operacao" value="SALVAR" id="btnFinalizarTroca">Finalizar Troca</button>
	                    <input type="hidden" name="trocaPedidoInteiro" value="0" />
	                  </form>
	               <%
                      }
                   %>
                  </div>
            
         </div>
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