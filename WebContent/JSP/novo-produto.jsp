<!-- ADMIN -  PRODUTO-->  
<%@page import='com.les.roupa.core.dao.*'%>
<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import='com.les.roupa.core.dao.impl.*'%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
   <head>
      <title>Mirror Fashion</title>
      <!-- FAVICON -->
      <link rel="shortcut icon" href="../favicon.ico">
      <link rel="stylesheet" type="text/css" href="../css/produto.css">
      <meta charset="utf-8">
   </head>
   <header class="container">
      <h1><a href="../JSP/admin.jsp"><img src="../img/logo.png" alt="Mirror Fashion"></a></h1>
      <!-- AQUI INFORMA QUEM É O USUARIO LOGADO -->
      <form action="http://localhost:8080/eCommerce_roupa/login">
         <p>Ol� ${usuarioLogado.nome} <button  type="submit" name="operacao" value="EXCLUIR" id="btnSairAdmin" onclick="sair()">Sair</button></p>
         <script>
            function sair(){
                alert("Voce saiu com sucesso! Volte em breve!");
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
   <%
         Usuario usuarioLogado = new Usuario();
         
         HttpSession sessao = request.getSession();
         usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
   %>
   <body>
      <!-- CONTAINER - DESTAQUE - PARTE PRINCIPAL-->
      <div class="container destaque">
         <!-- SESSAO MENU ADMIN -->
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
         <!-- FIM DA SESSA�O MENU ADMIN -->
      </div>
      <!-- FIM CONTAINER DESTAQUE -->
      <div class="container destaque">
         <!-- FORMULARIO DE NOVA CONTA  -->
         <form class="formularioProduto" action="http://localhost:8080/eCommerce_roupa/cadastroProduto" >
            <caption>* CADASTRO DE PRODUTO *</caption>
            <br>
            <br>
            <div>
            <label>Nome: </label> <input type="text" id="nome" name="nome" />
            <label>Categoria: </label> 
               <select name="categoria">
                <option disabled selected>- Selecione -</option>
                  <option value="Calca">Cal�a</option>
                  <option value="Blusa">Manga Comprida</option>
                  <option value="Blusa">Manga Curta</option>
                  <option value="Vestido">Vestido</option>
               </select>
               <label>Cor:</label> 
                <select name="cores">
                <option disabled selected>- Selecione -</option>
                <option value="Amarela">Amarela</option>
                <option value="Verde">Verde</option>
                <option value="Preta">Preta</option>
                <option value="Rosa">Rosa</option>
                </select>
               <label>Tamanho:</label> 
                 <select name="tamanho">
                 <option disabled selected>- Selecione -</option>
                  <option value="36">36</option>
                  <option value="38">38</option>
                  <option value="40">40</option>
                  <option value="42">42</option>
               </select>
               <br><br>
               <label>Qtde.:</label> <input type="text" name="qtdeEstoque" id="qtdeEstoque" />
               
               
               <label>R$ Compra:</label><input type="text" id="precoCompra" name="precoCompra" >
               <label>R$ Venda:</label><input type="text" id="precoVenda" name="precoVenda" >  
                   
               <label>Grupo Precifica��o:</label> <input type="hidden" id="grupoPrecificacao" />
                  <select name="grupoPrecificacao">
                  <option disabled selected>- Selecione -</option>
                  <option value="Grupo1">Grupo 1</option>
                  <option value="Grupo2">Grupo 2</option>
                  <option value="Grupo3">Grupo 3</option>
               </select>
                         <br><br>   
               <label>Foto:</label> <input type="text" id="foto" name="foto" />
               
               <label id="lb_descricao">Motivo Ativa��o/Inativa��o:</label>
               <textarea id="descStatusProduto" name="descricaoStatusProduto" ></textarea>
                 <br><br> 
                 
                 
               <label id="lb_descricao">Descri��o:</label>
               <textarea id="descricao" name="descricao" ></textarea>
               <br><br>
               
               <label>Status:</label> 
               <select name="status">
               <option disabled selected>- Selecione -</option>
                  <option value="Ativo">Ativo</option>
                  <option value="Inativo">Inativo</option>
               </select>
            </div>
            
              <div> 
               <br>
               <button class="button" type="submit" id="btnVoltar" onclick="window.history.go(-1); return false;">Voltar</button>
               <button class="button" type="submit" name="operacao" value="SALVAR" id="btnSalvar">Confirmar</button>
               
               <%-- <input type="hidden" name="alteraProduto" value="1"/>
               <input type="hidden" name="id" value="<%=produto.get(0).getId()%>"/> --%>
               
            </div>
         </form>
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