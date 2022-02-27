<!-- ADMIN -->  
<%@page import='com.les.roupa.core.dao.*'%>
<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import='com.les.roupa.core.dao.impl.*'%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Mirror Fashion</title>
        <!-- FAVICON -->
        <link rel="shortcut icon" href="./favicon.ico">
        <link rel="stylesheet" type="text/css" href="./css/clientes.css">
        <meta charset="utf-8">
    </head>
    <header class="container">
        <h1><a href="./JSP/admin.jsp"><img src="./img/logo.png" alt="Mirror Fashion"></a></h1>
        <!-- AQUI INFORMA QUEM É O USUARIO LOGADO -->
        <form action="http://localhost:8080/eCommerce_roupa/login">
            <p>Ola ${usuarioLogado.nome} <button  type="submit" name="operacao" value="EXCLUIR" id="btnSairAdmin" onclick="sair()">Sair</button></p>
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
            </ul>
        </nav>
    </header>
    <% 
        ClienteDAO dao = new ClienteDAO();
        Usuario usuarioLogado = new Usuario();
        
        HttpSession sessao = request.getSession();
        usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        
        String idCliente = (String)request.getAttribute("idCliente");
        List<Cliente> cliente = dao.consultarClienteById(idCliente);
        %>
    <body>
        <!-- CONTAINER - DESTAQUE - PARTE PRINCIPAL-->
        <div class="container destaque">
            <!-- SESSAO MENU ADMIN -->
            <section class="menu-departamentos">
                <h2 style="margin-left: 5px;">Menu</h2>
                <nav>
                    <li><a href="./JSP/consultar-cliente.jsp">Clientes</a></li>
                    <li>
                        <a href="./JSP/consultar-pedidos.jsp">Pedidos</a>
                        <ul>
                            <li><a href="./JSP/grafico1.jsp">Relatorio</a></li>
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
            <!-- FIM DA SESSAO MENU ADMIN -->
        </div>
        <!-- FIM CONTAINER DESTAQUE -->
        <div class="container destaque">
            <!-- FORMULARIO DE NOVA CONTA  -->
            <form class="formulario" action="http://localhost:8080/eCommerce_roupa/cadastro" >
                <caption>* ALTERAR CADASTRO *</caption>
                <br>
                <div>
                    <br>
                    <label>Nome: </label> <input type="text" id="nome" name="nome" value="<%=cliente.get(0).getNome() %>" />      
                    <label>CPF: </label><input type="CPF" id="CPF" name="cpf" class="form-control cpf-mask" placeholder="000.000.000-00" maxlength="11" value="<%=cliente.get(0).getCpf() %>"readonly/>
                    <label>Dt. Nasc.:</label><input type="date" name="dt_nasc" value="<%=cliente.get(0).getDt_nasc() %>">
                </div>
                <!-- OPCOES DE GENERO (MASC/FEM)-->
                <div id="divGenero">
                    <label>Genero: </label> <input type="hidden" value="<%=cliente.get(0).getGenero() %> "/>
                    <select name="genero">
                        <option value="Feminino">Feminino</option>
                        <option value="Masculino">Masculino</option>
                    </select>
                    <label id="telefone">Telefone: </label><input type="tel" name="telefone" id="telefone" maxlength="11" value="<%=cliente.get(0).getTelefone() %>">
                    <label id="email">E-mail:</label> <input type="email" id="email" name="email" placeholder="digite seu e-mail aqui" value="<%=cliente.get(0).getUsuario().getEmail() %>" readonly/>
                </div>
                <label id="senha">Senha:</label> <input type="text" name="senha" id="senha" maxlength="8" value="<%=cliente.get(0).getUsuario().getSenha()%>" />
                <label>Confirme a senha: </label> <input type="password" name="confsenha" id="senha" accesskey="S" placeholder="*********" value="<%=cliente.get(0).getUsuario().getConfSenha()%>" />
                
                <label>Status:</label> <input type="hidden" value="<%=cliente.get(0).getStatus()%> "/>
                <select name="status"> 
                    <option value="Ativo">Ativo</option>
                    <option value="Inativo">Inativo</option>
                </select>
                <div>
                    <button class="button" type="submit" id="btnVoltar" onclick="window.history.go(-1); return false;">Voltar</button>
                    <button class="button" type="submit" name="operacao" value="ALTERAR" id="btnSalvar">Confirmar</button>
                    <input type="hidden" name="tipo" value="cliente"/>
                    <input type="hidden" name="alteraCliente" value="1"/>
                    <input type="hidden" name="id" value="<%=cliente.get(0).getId()%>"/>
                </div>
            </form>
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