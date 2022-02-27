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
        <link rel="stylesheet" type="text/css" href="../css/cupom.css">
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
                ClienteDAO clienteDAO = new ClienteDAO();
                Cliente clienteAux = new Cliente();
                List<EntidadeDominio> clientes = clienteDAO.consultarClienteByTipo(clienteAux);
            
                Usuario usuarioLogado = new Usuario();
                
                HttpSession sessao = request.getSession();
                usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");      
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
            
            <form class="formCupom" action="http://localhost:8080/eCommerce_roupa/cupom">
            <caption> * Cadastro de Cupons *</caption>
            <br>
            <br>
               <label>Nome:</label> <input type="text" name="nome" id="nome"/>
               <label id="tipoCupom">Tipo de Cupom: </label>
                   <select name="tipo">
                       <option disabled selected>-- Selecione --  </option>
                       <option value="Troca">Troca</option>
                       <option value="Devolucao"> Devolução</option>
                       <option value="Promocional">Promocional</option>
                       <option value="Carteira">Carteira</option>
                   </select><br>
               <label>Valor:</label> <input type="text" name="valor" id="valorCupom" >
               
                 Cliente Vinculado:
                <select name="id_cliente">
                    <option disabled selected>Selecione </option>
                       <%
                           for(EntidadeDominio e: clientes){
                        	 
                        	// Aplicado o CAST 
                        	 Cliente c = (Cliente) e;
                       %>
                       <option value="<%=c.getId()%>"> <%=c.getNome()%></option>
                       <%
                           }
                         %>
                 </select>
                 Utilizado: 
                 <select name="utilizado">
                    <option disabled selected>Selecione </option>
                       <option value="Sim">Sim</option>
                       <option value="Nao">Não</option>                       
                 </select>
               
               <br>
               <br>
                  <button type="submit" name="operacao" value="CONSULTAR" id="btnConsultar">Consultar Todos</button>
                  <button type="submit" name="operacao" value="SALVAR" id="btnSalvar"> Salvar </button>
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