<!-- ALTERAR CARRINHO -->
<%@page import='com.les.roupa.core.dao.*'%>
<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import='com.les.roupa.core.dao.impl.*'%>
<%@page import="java.util.List"%> 
<!DOCTYPE html>
<html>
   <head>
      <title>Mirror Fashion</title>
      <link rel="stylesheet" type="text/css" href="./css/carrinho.css">
      <meta charset="utf-8">
      <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8"%>
      <!-- FAVICON -->
      <link rel="shortcut icon" href="./favicon.ico">
      <script type="text/javascript" src="./js/qtde.js"></script>
   </head>
   <header class="container">
      <h1><a href="./JSP/index.jsp"><img src="./img/logo.png" alt="Mirror Fashion"></a></h1>
      <p class="sacola"><a href="./JSP/carrinho.jsp">Minha Sacola</a></p>
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
            <li><a href="./JSP/index.jsp">Home</a></li>
            <li><a href="./JSP/produtos.jsp">Produtos</a></li>
            </br>
         </ul>
      </nav>
   </header>
   <body>
      <!-- PARTE PRINCIPAL-->
      <div class="container carrinho">
         <!-- CARRINHO  --> <br>
         <p>MEU CARRINHO</p>
         <table id="tabelaProduto">
            <tr>
               <!-- TITULOS -->
               <th>Produto</th>
               <th>Cor</th>
               <th>Tamanho</th>
               <th>Qtde.</th>
               <th>Preço</th>
               <th>Remover</th>
            </tr>
            <%
               CarrinhoDAO dao = new CarrinhoDAO();
               ProdutoDAO roupaDAO = new ProdutoDAO();
               EnderecoDAO endDAO = new EnderecoDAO();
               CartaoCreditoDAO cartaoDAO = new CartaoCreditoDAO();
               CupomDAO cupomDAO = new CupomDAO();
               CupomCarrinhoDAO cupomCarrinhoDAO = new CupomCarrinhoDAO();
               
               Carrinho carrinho = new Carrinho();
               
               double total_produtos = 0;
               double total_frete = 0;
               double total_pedido = 0;
               double desconto = 0;
               
               Usuario usuarioLogado = new Usuario();
               
               HttpSession sessao = request.getSession();
               usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
               
               List<Carrinho> carrinhos = dao.consultarCarrinhoByStatus(usuarioLogado.getId());
               
               List<Endereco> enderecos = endDAO.consultarEnderecoByIdCliente(usuarioLogado.getId());
               
               List<CartaoCredito> cartoes = cartaoDAO.consultarCartCreditoByIdCliente(usuarioLogado.getId());
               
               List<Cupom> cupons = cupomDAO.consultarCupomByIdCliente(usuarioLogado.getId());
               
               List<CupomCarrinho> cupomCarrinho = cupomCarrinhoDAO.consultarCupomAtivoByCliente(usuarioLogado.getId());
               
               
              for(CupomCarrinho desc : cupomCarrinho){
                  
                  //Somatoria do desconto (cupons)
                  desconto += Double.parseDouble(desc.getValor());
                  
              }
               
               for(EntidadeDominio e : carrinhos){
               
                   // aplicado o CAST 
                   Carrinho r = (Carrinho) e;
                   
                   List<Produto> produto = roupaDAO.consultarProdutoById(r.getIdProduto());
                   
                    // calculo do Total dos Produtos - carrinho
                   total_produtos += (Double.parseDouble(produto.get(0).getPrecoVenda()) * Double.parseDouble(r.getQtdeProduto()));
                   // arredondar 2 casas decimais
                   total_produtos = Math.round(total_produtos * 100);
                   total_produtos = total_produtos/100;
                   
                   //calculo do Total do frete - R$ 1 por qtde
                   total_frete += 1 * Double.parseDouble(r.getQtdeProduto());
                   
                   //calculo do Total do pedido = valor dos produtos + valor do frete
                   total_pedido = (total_produtos + total_frete) - desconto;
                   //arrendondar 2 casas decimais
                   total_pedido = Math.round(total_pedido * 100);
                   total_pedido = total_pedido /100;
               %> 
            <tr>
               <td><%=produto.get(0).getNome() %></td>
               <td><%=produto.get(0).getCores() %></td>
               <td><%=produto.get(0).getTamanho() %></td>
               <td>
                  <!-- QUANTIDADE -->
                  <a href="/eCommerce_roupa/carrinho?id=<%=r.getId()%>&operacao=ALTERAR&qtde_produto=<%=r.getQtdeProduto() %>&valorOperacao=SUBTRACAO"><button id="menos">-</button></a>
                  <input disabled type="text" id="qtde" name="qtde_produto" value="<%=r.getQtdeProduto() %>" maxlength="3" />
                  <a href="/eCommerce_roupa/carrinho?id=<%=r.getId()%>&operacao=ALTERAR&qtde_produto=<%=r.getQtdeProduto() %>&valorOperacao=ADICAO"><button id="mais">+</button></a>
               </td>
               <!-- PREÇO -->
               <td><%=produto.get(0).getPrecoVenda()%></td>
               <!-- EXCLUIR -->
               <td>
                  <a href="/eCommerce_roupa/carrinho?id=<%=r.getId()%>&operacao=EXCLUIR"><button id="button_excluir">X</button></a>
               </td>
            </tr>
            <%
               }
               %>
         </table>
         <br>
         <form action="http://localhost:8080/eCommerce_roupa/pedido">
            <table id="tabelaValores">
               <!-- VALORES -->
               <tr>
                  <td>Valor Produtos</td>
                  <td>R$ <%=total_produtos %>0</td>
               </tr>
               <tr>
                  <td>Valor Frete</td>
                  <td>R$ <%=total_frete %>0</td>
               </tr>
               <tr>
                  <td>Desconto</td>
                  <td>R$ <%=desconto %>0</td>
               </tr>
               <tr>
                  <td>Valor Total</td>
                  <td>R$ <%=total_pedido %>0</td>
               </tr>
               <div>
                  <!--  OPÇÃO DE ENDERECO -->
                  Endereco de entrega: <a href="/eCommerce_roupa/JSP/meusenderecos.jsp">Novo Endereço</a><br>
                  <select name="idEndereco">
                     <option disabled selected>Selecione </option>
                     <%
                        for(Endereco end : enderecos){
                        %>
                     <option value="<%=end.getId()%>"> <%=end.getTipoResidencia()%> - <%=end.getLogradouro()%>, <%=end.getNumero()%> - <%=end.getEstado()%></option>
                     <%
                        }
                        %>
                  </select>
                  <br>
                  <br>
                 <!--  OPÇÃO DE CARTAO USADO -->
                        Forma de Pagamento: <a href="/eCommerce_roupa/JSP/cartoes.jsp">Novo Cartão</a><br>
                            <%
                                for(CartaoCredito cartao : cartoes){
                                %>
                           <select name="cartao1">
                           <option disabled selected>Selecione</option>
                            <option value="<%=cartao.getId()%>"><%=cartao.getNome() %> - (<%=cartao.getBandeira()%> / <%=cartao.getValidade()%>)</option>
                        </select> 
                        Valor: <input type="text" name="valorCartao1" style="width: 50px;"/>
                        <br>
                        <%
                            }
                            %>
               </div>
            </table>
            <br>
            <input type="hidden" name="idCliente" value="<%=usuarioLogado.getId() %>"/>
            <input type="hidden" name="precoTotalProduto" value="<%=total_produtos %>" />
            <input type="hidden" name="precoFrete" value=" <%=total_frete %>" />
            <input type="hidden" name="desconto" value=" <%=desconto %>"/>
            <input type="hidden" name="total_pedido" value="<%=total_pedido %>" />
            <input type="hidden" name="statusPedido" value="EM PROCESSAMENTO" />
            <input type="hidden" name="valorCartao" value="<%=total_pedido %>" />      
            <button type="submit" name="operacao" value="SALVAR" id="confirmarCompra">Confirmar Compra</button>
         </form>
         <form action="http://localhost:8080/eCommerce_roupa/cupomCarrinho" id="formCupomCarrinho">
            Cupons Ativos: 
            <select name="id_cupom">
               <option disabled selected>Selecione </option>
               <%
                  for(Cupom c : cupons){
                  %>
               <option value="<%=c.getId()%>"> <%=c.getNome()%></option>
               <%
                  }
                  %>
            </select>
            <button type="submit" name="operacao" value="SALVAR" id="validarCupom" >Aplicar</button>
         </form>
      </div>
      <!-- FIM FORM PEDIDO -->
      <!-- RODAPE -->
      <footer>
         <div class="container">
            <a href="./JSP/index.jsp">
            <img src="./img/logo-rodape.png" alt="Logo Mirror Fashion"></a>
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