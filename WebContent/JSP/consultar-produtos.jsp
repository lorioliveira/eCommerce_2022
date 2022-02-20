<!-- PRODUTOS - ADMIN -->
<%@page import='com.les.roupa.core.dao.*'%>
<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import='com.les.roupa.core.dao.impl.*'%>
<%@page import="java.util.List"%>  
<!DOCTYPE html>
<html>
    <head>
        <title>Mirror Fashion</title>
        <link rel="stylesheet" type="text/css" href="../css/estilos.css">
        <meta charset="utf-8">
        <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
            pageEncoding="UTF-8"%>
        <!-- FAVICON -->
        <link rel="shortcut icon" href="../favicon.ico">
    </head>
    <header class="container">
        <h1><a href="../JSP/admin.jsp"><img src="../img/logo.png" alt="Mirror Fashion"></a></h1>
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
            </ul>
        </nav>
    </header>
    <body>
        <div class="containerADMIN destaque">
            <div id="consultarProdutos"> * PRODUTOS CADASTRADOS * <button type="submit" onclick="window.history.go(-1); return false;" id="btnVoltar">Voltar</button></div> 
            <br>
            <table>
                <tr>
                    <th>Nome</th>
                    <th>Categoria</th>
                    <th>R$ Compra</th>
                    <th>R$ Venda</th>
                    <th>Dt. Cadastro</th>
                    <th>Cor</th>
                    <th>Tamanho</th>
                    <th>Qtde</th>
                    <th>Descrição</th>
                    <th>Status</th>
                    <th>Grupo Preço</th>
                    <th>Ações</th>
                    <%
                    ProdutoDAO dao = new ProdutoDAO();
                    Produto produto = new Produto();
                    
                    List<EntidadeDominio> produtos = dao.consultar(produto);
                    
                    for(EntidadeDominio e : produtos){
                    
                        // Aplicado o CAST para poder popular o produto
                        Produto p = (Produto) e;
                    %>  
                    <!-- CONTEÚDOS DA TABELA E BOTOES AO LADO -->
                <tr>
                    <td><%=p.getNome() %></td>
                    <td><%=p.getCategoria() %></td>
                    <td><%=p.getPrecoCompra() %></td>
                    <td><%=p.getPrecoVenda() %></td>
                    <td><%=p.getDt_cadastro() %></td>
                    <td><%=p.getCores()%></td>
                    <td><%=p.getTamanho()%></td>
                    <td><%=p.getQtdeEstoque() %></td>
                    <td id="descricaoConsulta"><%=p.getDescricao()%></td>
                    <td><%=p.getStatus()%></td>
                    <td><%=p.getGrupoPrecificacao() %></td>
                   <input type="hidden" <%=p.getStatusProduto() %> />
                     <td>
                        <a href="/eCommerce_roupa/cadastroProduto?id=<%=p.getId()%>&operacao=ALTERAR&alteraProduto=0"><button id="button_alterar">Alterar</button></a>
                        
                     </td>
                </tr>
                <%
                    }
                    %>
            </table>
        </div>
        <!-- RODAPE -->
        <footer>
            <div class="container">
                <a href="../JSP/admin.jsp">
                <img src="../img/logo-rodape.png" alt="Logo Mirror Fashion"></a>
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