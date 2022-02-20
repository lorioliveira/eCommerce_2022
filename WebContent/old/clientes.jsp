<!--  ADMIN -->
<%@page import='com.les.roupa.core.dao.*'%>
<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import='com.les.roupa.core.dao.impl.*'%>
<%@page import="java.util.List"%>   
<!DOCTYPE html>
<html>
    <head>
        <title>Mirror Fashion</title>
        <link rel="stylesheet" type="text/css" href="../css/clientes.css">
        <meta charset="utf-8">
        <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
            pageEncoding="UTF-8"%>  
        <!-- FAVICON -->
        <link rel="shortcut icon" href="./favicon.ico">
    </head>
    <header class="container">
        <h1><a href="../JSP/index.jsp"><img src="../img/logo.png" alt="Mirror Fashion"></a></h1>
        <!-- AQUI INFORMA QUEM É O USUARIO LOGADO -->
        <form action="http://localhost:8080/eCommerce_roupa/login">
            <p>Olá ${usuarioLogado.nome} <button  type="submit" name="operacao" value="EXCLUIR" id="btnSair" onclick="sair()">Sair</button></p>
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
                            <li><a href="">Relatorio</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="../JSP/consultar-produtos.jsp">Produtos</a>
                        <ul>
                            <li><a href="">Estoque</a></li>
                            <li><a href="">Adicionar Novo</a></li>
                        </ul>
                    </li>
                    <li><a href="../JSP/cupons.jsp">Cupons</a></li>
                </nav>
            </section>
            <!-- FIM DA SESSAO MENU  -->
        </div>
        <!-- FIM CONTAINER DESTAQUE -->
        <div class="container destaque">
            <!-- FORMULARIO DE NOVA CONTA  -->
            <form class="formulario" action="http://localhost:8080/eCommerce_roupa/cadastro" >
                <caption>* Novo Cadastro *</caption>
                <br>
                <div>
                    <br>
                    <label>Nome: </label> <input type="text" id="nome" name="nome" />  
                    <label>CPF: </label><input type="CPF" id="CPF" name="cpf" class="form-control cpf-mask" placeholder="000.000.000-00" maxlength="11" />
                    <label>Dt. Nasc.:</label><input type="date" name="dt_nasc">
                </div>
                <!-- OPCOES DE GENERO (MASC/FEM)-->
                <div>
                    <label>Gênero: </label>
                    <select name="genero">
                        <option>Selecione</option>
                        <option value="Feminino">Feminino</option>
                        <!-- selected -->
                        <option value="Masculino" >Masculino</option>
                    </select>
                    <label id="telefone">Telefone: </label><input type="tel" name="telefone" id="telefone" maxlength="11" >
                    <label id="email">E-mail:</label> <input type="email" id="email" name="email" placeholder="digite seu e-mail aqui" />
                </div>
                <div>
                    <label>Senha:</label> <input type="password" name="senha" id="senha" maxlength="8"/>
                    <label>Confirmar Senha: </label> <input type="password" name="confsenha" maxlength="8" id="senha" />
                    <input type="hidden" name="status" value="ativo"/>
                    <input type="hidden" name="tipo" value="cliente"/>
                    <button class="button" type="submit" name="operacao" value="SALVAR" id="btnSalvar">Salvar</button>
                    <button class="button" type="submit" name="operacao" value="CONSULTAR" id="btnConsultar">Consultar</button>
                    <br>
                    <br>
                    </td>
                    </tr>
                    </table>
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