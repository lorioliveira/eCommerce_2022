<!-- ADMIN -->  
<%@page import='com.les.roupa.core.dao.*'%>
<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import='com.les.roupa.core.dao.impl.*'%>

<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Mirror Fashion</title>
         <link rel="stylesheet" type="text/css" href="./css/estoque.css">  
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
                </br>
                </ul>       
            </nav>
        </header>
                 <%
                    EstoqueDAO dao = new EstoqueDAO();
                    ProdutoDAO produtoDAO = new ProdutoDAO();
                    Estoque estoque = new Estoque();
                    
                    // pega o "id" do produto que estava pendurado na requisição,
                    // que foi enviado pelo arquivo "EstoqueHelper"
                    String idProduto = (String)request.getAttribute("idProduto");
                    
                    // pesquisa o produto que esta sendo passado pela requisição,
                    // para poder pegar o nome e adicionar o nome na listagem da tabela
                    List<Produto> produto = produtoDAO.consultarProdutoById(idProduto);
                    
                    // seta o atributo "id_produto" do objeto "estoque", com o valor que estava pendurado na requisição (idProduto)
                    estoque.setIdProduto(idProduto);
                    // consulta somente o estoque do produto que esta sendo mandado na requisição
                    List<EntidadeDominio> estoques = dao.consultar(estoque);
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
                <!-- FIM DA SESSAO MENU ADMIN -->
        </div>
        <!-- FIM CONTAINER DESTAQUE -->


        <div class="container destaque">
        <!-- N�o precisa de formulario nesse caso, pois precisa de uma
        requisicao diretamente pela URL -->
            <div id="tabelaClientes">
                <caption> * CONSULTAR ESTOQUE:  <%=produto.get(0).getNome() %> *</caption> 
                    <button type="submit" onclick="window.history.go(-1); return false;" id="btnVoltarEstoque">Voltar</button>
                <br><br>
                <table>
                            <tr>
                                <th>Nome Produto</th>
                                <th>Tipo Estoque</th>
                                <th>Quantidade</th>
                                <th>Valor Compra</th>
                                <th>Data</th>
                                <th>Quantidade Total</th>
                            
                            
                            <!-- CONTEÚDOS DA TABELA E BOTOES AO LADO -->
                            <%
						        for(EntidadeDominio e : estoques) {
						        
						        // Aplicado o CAST para poder popular o estoque,
						        // fazendo o CAST para uma referência mais genérica, no caso para o estoque
						        Estoque est = (Estoque) e;
						     %>
						            <tr>
						                <td><%=produto.get(0).getNome() %></td>
						                <td><%=est.getTipo() %></td>
						                <td><%=est.getQuantidade() %></td>
                                        <td><%=est.getValorCompra() %></td>
                                        <td><%=est.getDtEntrada() %></td>
                                        <td><%=est.getQuantidadeTotal() %></td>
						            </tr>
						     <%
						        }
						      %>
                    </table>        
                </div>
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
    <!-- FIM DO RODAPE -->


    </body>
</html>