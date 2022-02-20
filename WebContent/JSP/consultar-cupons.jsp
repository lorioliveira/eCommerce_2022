
<!-- ADMIN - CONSULTA DE CUPONS  -->
<%@page import='com.les.roupa.core.dao.*'%>
<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import='com.les.roupa.core.dao.impl.*'%>
<%@page import="java.util.List"%>  

<!DOCTYPE html>
<html>
    <head>
        <title>Mirror Fashion</title>
        <link rel="shortcut icon" href="../favicon.ico">
        <link rel="stylesheet" type="text/css" href="./css/cupom.css">
        <meta charset="utf-8">
        <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
            pageEncoding="UTF-8"%>  
    </head>
    <header class="container">
        <h1><a href="./JSP/admin.jsp"><img src="./img/logo.png" alt="Mirror Fashion"></a></h1>
         
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
                <li><a href="./JSP/admin.jsp">Home</a></li>
                <li><a href="./JSP/perfil-admin.jsp">Perfil</a></li>
                </br>
            </ul>
        </nav>
    </header>
   
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
            <!-- FIM DO MENU -->
            
            <!-- PARTE PRINCIPAL  -->
            <div class="cupom"> 
              <caption> * Cupons Cadastrados *</caption>
                <table id="tabelaCupom">
                <tr>
                    <th>Nome</th>
                    <th>Tipo Cupom</th>
                    <th>Valor</th>
                    <th>Dt. Cadastro</th>
                    <th>P/ Cliente</th>
                    <th>Utilizado?</th>
                    <th>Ações</th>
                </tr>
                <!-- CONTEÚDOS DA TABELA E BOTOES AO LADO -->
                <%
                  CupomDAO cupomDAO = new CupomDAO();
                  Cupom cupom = new Cupom();

                  List<EntidadeDominio> cupons = cupomDAO.consultar(cupom);
                  
                  ClienteDAO clienteDAO = new ClienteDAO();
                  Cliente clienteAux = new Cliente();
                  List<EntidadeDominio> clientes = clienteDAO.consultarClienteByTipo(clienteAux);

                  for(EntidadeDominio e : cupons){

                      // Aplicado o CAST 
                      Cupom cp = (Cupom) e;
                 %>
                <tr>
                    <td><%=cp.getNome()%></td>
                    <td><%=cp.getTipo()%></td>
                    <td>R$ <%=cp.getValor()%></td>
                    <td><%=cp.getDt_cadastro()%></td>
                    <td><%=cp.getIdCliente() %></td>
                    <td><%=cp.getUtilizado() %></td>
                    <td>
                        <a href="/eCommerce_roupa/cupom?id=<%=cp.getId()%>&operacao=ALTERAR&alteraCupom=0"><button id="button_alterar">Alterar</button></a>
                        <a href="/eCommerce_roupa/cupom?id=<%=cp.getId()%>&operacao=EXCLUIR"><button id="button_excluir">Excluir</button></a>
                    </td>
                </tr>
                <%
                   }
                 %>
            </table>
            <br>
            <button type="submit" id="btnCriar"><a href="./JSP/cupons.jsp">Novo Cupom</a></button>
        </div>
        </div>
        <!-- FIM DOS DADOS  -->
        <!-- RODAPE -->
        <footer>
            <div class="container">
                <a href="./JSP/admin.jsp">
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