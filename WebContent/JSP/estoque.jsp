<!-- ADMIN - CADASTRO DE CUPONS -->
<%@page import='com.les.roupa.core.dao.*'%>
<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import='com.les.roupa.core.dao.impl.*'%>
<%@page import="java.util.List"%>  

<!DOCTYPE html>
<html>
    <head>
        <title>Mirror Fashion</title>
        <link rel="shortcut icon" href="../favicon.ico">
        <link rel="stylesheet" type="text/css" href="../css/estoque.css">
        <meta charset="utf-8">
        <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
            pageEncoding="UTF-8"%>  
    </head>
    <header class="container">
        <h1><a href="../JSP/admin.jsp"><img src="../img/logo.png" alt="Mirror Fashion"></a></h1>
         
 <!-- AQUI INFORMA QUEM É O USUARIO LOGADO -->
         <form action="http://localhost:8080/eCommerce_roupa/login">
        <p>Olá ${usuarioLogado.nome} <button class="button" type="submit" name="operacao" value="EXCLUIR" id="btnSairAdmin">Sair</button></p>
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
    <%
        ProdutoDAO dao = new ProdutoDAO();
        Produto produto = new Produto();    
    
        // pega todos os produtos cadastrados no sistema
        List<EntidadeDominio> allProdutos = dao.consultar(produto);
        // pega todos os produtos ativos cadastrados no sistema
        List<EntidadeDominio> allProdutosAtivos = dao.consultarSomenteAtivo(produto);
    %>
   
    <body>
        <!-- CONTAINER PARTE MENU PRINCIPAL-->
        <div class="container destaque">
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
            <!-- FIM DO MENU -->
            
            
            <!-- SALVAR ESTOQUE -->
            
            
            <form class="formEstoque" action="http://localhost:8080/eCommerce_roupa/estoque">
            <caption> * CADASTRO DE ESTOQUE *</caption>
            <br>
            <br>
               <div id="div1">
	               <label>Produto</label>
	                   <select name="selecioneProduto">
	                       <option disabled selected>-- Selecione --</option>
	                       <% 
                                for(EntidadeDominio e : allProdutosAtivos) {
                
                                // Aplicado o CAST para poder popular o produto,
                                // fazendo o CAST para uma referência mais genérica, no caso para o produto,
                                // lista todos os produtos indexado com o ID do produto dentro do "value", de cada da TAG "<option>".
                                Produto product = (Produto) e;
                            %>
                            <option value="<%=product.getId()%>"><%=product.getNome()%></option>
                            <%
                                }
                            %>

	                   </select>
	                
	                   
	                <label>Tipo Estoque:</label>
	                   <select name="tipo">
	                       <option disabled selected>-- Selecione --</option>
	                       <option value="Entrada">Entrada</option>
	                       <option value="Saida">Saída</option>
	                   </select>
	                    
	                   
	                 <label>Quantidade:</label>
	                    <input type="text" name="quantidade" id="valorCupom" >
	                 <br>  
	             </div>  
                     
                 <label>Valor de Compra:</label>
                    <input type="text" name="valorCompra" >
                 
                  <label>Data de Entrada:</label>
                    <input type="date" class="form-control" name="dt_entrada" required> 
                   
               <br>
               <br>
                  <button type="submit" name="operacao" value="SALVAR" id="btnSalvar"> Salvar Estoque</button>
                  
            </form> 
            
            <!-- CONSULTAR ESTOQUE -->
            <form class="formEstoque" action="http://localhost:8080/eCommerce_roupa/estoque"> 
            <caption> * CONSULTAR ESTOQUE *</caption><br><br>
            
            <label>Produto</label>
                       <select id="produtoEstoque" name="selecioneProduto">
                           <option disabled selected>-- Selecione --</option>
                           <% 
                                for(EntidadeDominio e : allProdutosAtivos) {
                
                                // Aplicado o CAST para poder popular o produto,
                                // fazendo o CAST para uma referência mais genérica, no caso para o produto,
                                // lista todos os produtos indexado com o ID do produto dentro do "value", de cada da TAG "<option>".
                                Produto prod = (Produto) e;
                            %>
                            <option value="<%=prod.getId()%>"><%=prod.getNome()%></option>
                            
                            <%
                                }
                            %>
                       </select>
            
            <button type="submit" name="operacao" value="CONSULTAR" id="btnConsultar">Consultar</button>
               
            </form>
            
         </div>
           
        <!-- FIM  -->
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