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

                <caption> * Clientes Cadastrados *</caption><br>
                <table>
                
                            <tr>
                                <th>Nome</th>
                                <th>CPF</th>
                                <th>Dt. Nasc.</th>
                                <th>Genero</th>
                                <th>Telefone</th>
                                <th>Email</th>
                                <th>Senha<th>
                                Status
                                <th>Acoes</th>
                            <%
                                ClienteDAO dao = new ClienteDAO();
                                Cliente cliente = new Cliente();
                                // Listar apenas os Clientes (antes estava trazendo o próprio admin)
                                List<EntidadeDominio> clientes = dao.consultarClienteByTipo(cliente);

                                for(EntidadeDominio e : clientes){

                                    // Aplicado o CAST para poder popular o cliente
                                    Cliente c = (Cliente) e;
                                    //Pega o usuario que esta dentro do cliente
                                    Usuario u = c.getUsuario();
                            %>  
                            
                            <!-- CONTEÚDOS DA TABELA E BOTOES AO LADO -->
                            <tr>
                                <td><%=c.getNome() %></td>
                                <td><%=c.getCpf() %></td>
                                <td><%=c.getDt_nasc() %></td>
                                <td><%=c.getGenero() %></td>
                                <td><%=c.getTelefone() %></td>
                                <td><%=u.getEmail() %></td>
                                <td><%=u.getSenha() %></td>
                                <td><%=c.getStatus() %></td>
                                <td>
                                  <a href="/eCommerce_roupa/cadastro?id=<%= c.getId()%>&operacao=ALTERAR&alteraCliente=0"><button id="button_alterar">Alterar</button></a>
                                  <a href="/eCommerce_roupa/cadastro?id=<%= c.getId()%>&operacao=EXCLUIR"><button id="button_excluir">Excluir</button></a>
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