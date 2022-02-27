<!-- -->
<%@page import='com.les.roupa.core.dao.*'%>
<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import='com.les.roupa.core.dao.impl.*'%>
<%@page import="java.util.List"%>  
<!DOCTYPE html>
<html>
    <head>
        <title>Mirror Fashion</title>
        <link rel="shortcut icon" href="../favicon.ico">
         <link rel="stylesheet" type="text/css" href="./css/estilos_2.css">
        <link rel="stylesheet" type="text/css" href="./css/detalhe_blusa.css">
        <meta charset="utf-8">
    </head>
    <header class="container">
        <h1><a href="./JSP/index.jsp"><img src="./img/logo.png" alt="Mirror Fashion"></a></h1>
        <p class="sacola"><a href="../JSP/carrinho.jsp">Minha Sacola</a></p>
        <!-- AQUI INFORMA QUEM É O USUARIO LOGADO -->
        <form action="http://localhost:8080/eCommerce_roupa/login" method="post">
            <p>Ola ${usuarioLogado.nome} <button type="submit" name="operacao" value="EXCLUIR" id="btnSair">Sair</button></p>
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
                </br>
            </ul>
        </nav>
          <% 
            ProdutoDAO dao = new ProdutoDAO();
            Usuario usuarioLogado = new Usuario();
                        
            HttpSession sessao = request.getSession();
            usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
            
            String idCliente = usuarioLogado.getId();
            
            String idProduto = (String)request.getAttribute("idProduto");
            List<Produto> produto = dao.consultarProdutoById(idProduto);
         %>
    </header>
    <body>
        <!-- CONTAINER - MENU ESQUERDO - PARTE PRINCIPAL-->
        <div class="container destaque">
            <!-- Categorias -->
            <section class="menu-departamentos">
                <h2 style="margin-left: 5px;"> Categorias</h2>
                <nav>
                    <li>
                        <a href="./JSP/blusas.jsp">Blusas</a></li>
                    <li><a href="./JSP/calcas.jsp">Calcas</a></li>
                    <li><a href="./JSP/vestidos.jsp">Vestidos</a></li>
                </nav>
            </section>
            <!-- FIM DAS CATEGORIAS -->
           
            <!-- PARTE ONDE VAI FICAR O PRODUTO  -->
             
            <div class="produto-back">
            <form action="http://localhost:8080/eCommerce_roupa/carrinho">
                <div class="container">
                    <div class="produto">
                    <img id="imagemProduto" src="<%=produto.get(0).getFoto()%>">
                        <h1><%=produto.get(0).getNome()%></h1>
                        <p>por apenas R$ <%=produto.get(0).getPrecoVenda()%></p>
                        <div>
                            <fieldset class="tamanhos">
                                <legend>Escolha a cor:</legend>
                                
                                    <input disabled type="text" value=<%=produto.get(0).getCores()%> />
                               
                            </fieldset>
                            <fieldset class="tamanhos">
                                <legend>Escolha o tamanho:</legend>
                                
                                   <input disabled type="text" value=<%=produto.get(0).getTamanho()%> />
                                
                            </fieldset>
                            <fieldset class="quantidade">
                            <legend>Quantidade:</legend>
                                <input type="text" name="qtde_produto" value="1" id="qtde_produto" size="1" min="1"/>
                            </fieldset>
                            
                            <button type="submit" class="comprar" name="operacao" value="SALVAR">COMPRAR</button>
                        </div>
                   
                </div>
                <!-- FIM DA PARTE DO PRODUTO -->
            </div>
            <!-- FIM DIV PRODUTO-BACK -->
            
            <div id="detalhes">
                <h2>Detalhes do produto</h2>
                <p id="detalheProduto"><%=produto.get(0).getDescricao()%></p>
            </div>
            <!--  FIM DIV DETALHES -->
            
            <input type="hidden" name="id_cliente" value="<%=usuarioLogado.getId() %>"/>
            <input type="hidden" name="id_produto" value="<%=produto.get(0).getId()%>"/>
            <input type="hidden" name="status" value="ativo"/>
            
            </form>
             
        </div>
        </div>
        
        <!-- FIM CONTAINER DESTAQUE -->
        <!-- RODAPÃ‰ -->
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
        <!-- FIM DO RODAPÃ‰ -->
    </body>
</html>