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
    </head>
    <header class="container">
        <h1><a href="../HTML/index.html"><img src="./img/logo.png" alt="Mirror Fashion"></a></h1>
        <p class="admin"><a href="../HTML/admin.html">Bem vindo(a) Admin</a></p>
        <nav class="menu-opcoes" class="links">
            <ul>
                <li><a href="../HTML/produtos.html">Produtos</a></li>
                <li><a href="../HTML/login.html">Sair</a></li>
                </br>
            </ul>
        </nav>
    </header>
    <body>
        <!-- CONTAINER - DESTAQUE - PARTE PRINCIPAL-->
        <div class="container destaque">
            <!-- SEÃÃO MENU ADMIN -->
            <section class="menu-departamentos">
                <h2 style="margin-left: 5px;">Menu</h2>
                <nav>
                    <li><a href="./HTML/clientes.html">Clientes</a></li>
                    <li>
                        <a href="#">Pedidos</a>
                        <ul>
                            <li><a href="">Vendas Realizadas</a></li>
                            <li><a href="">Devolucao£o</a></li>
                            <li><a href="">Troca</a></li>
                            <li><a href="">Relatorio</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#">Produtos</a>
                        <ul>
                            <li><a href="">Estoque</a></li>
                            <li><a href="">Adicionar Novo</a></li>
                        </ul>
                    </li>
                    <li><a href="">Cupons</a></li>
                </nav>
            </section>
            <!-- FIM DA SEÃÃO MENU ADMIN -->
        </div>
        <!-- FIM CONTAINER DESTAQUE -->
        <div class="container destaque">
            <!-- FORMULÃRIO DE NOVA CONTA  -->
            <form class="formulario" action="http://localhost:8080/eCommerce_roupa/cadastro" >
            <caption>* Novo Cadastro *</caption>
            <br>
            <div>
                <br>
                <label>Nome: </label> <input type="text" id="nome" name="nome" />  

                <label>CPF: </label><input type="CPF" id="CPF" name="cpf" class="form-control cpf-mask" placeholder="000.000.000-00" maxlength="11" />

                <label>Dt. Nasc.:</label><input type="date" name="dt_nasc">
            </div>
            <!-- OPÃÃES DE GENERO (MASC/FEM)-->
            <div>
                <label>G�nero: </label>
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

                <button class="button" type="submit" name="operacao" value="SALVAR" id="btnSalvar">Salvar</button>

                <button class="button" type="submit" name="operacao" value="CONSULTAR" id="btnConsultar">Consultar</button>
                
                <br>
                <table>
				<br>
							<tr>
								<th>Nome</th>
								<th>CPF</th>
								<th>Dt. Nasc.</th>
								<th>G�nero</th>
								<th>Telefone</th>
								<th>Email</th>
								<th>Senha<th>
								A��es
							</tr>
							<%
								ClienteDAO dao = new ClienteDAO();
								Cliente cliente = new Cliente();

								List<EntidadeDominio> clientes = dao.consultar(cliente);

								for(EntidadeDominio e : clientes){

									// Aplicado o CAST para poder popular o cliente
									Cliente c = (Cliente) e;
									//Pega o usuario que esta dentro do cliente
									Usuario u = c.getUsuario();
							%>	
							
							<!-- CONTEÃDOS DA TABELA E BOTOES AO LADO -->
							<tr>
								<td><%=c.getNome() %></td>
								<td><%=c.getCpf() %></td>
								<td><%=c.getDt_nasc() %></td>
								<td><%=c.getGenero() %></td>
								<td><%=c.getTelefone() %></td>
								<td><%=u.getEmail() %></td>
								<td><%=u.getSenha() %></td>
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
        </div>
        <!-- RODAPÃ -->
        <footer>
            <div class="container">
                <a href="./HTML/index.html">
                <img src="./img/logo-rodape.png" alt="Logo Mirror Fashion"></a>
                <ul class="social">
                    <!-- <li id="copy">&copy; Copyright MF - Todos os direitos protegidos - 2021</li> -->
                    <li><a href="http://facebook.com/mirrorfashion">Facebook</a></li>
                    <li><a href="http://twitter.com/mirrorfashion">Twitter</a></li>
                    <li><a href="http://plus.google.com/mirrorfashion">Google+</a></li>
                </ul>
            </div>
        </footer>
        <!-- FIM DO RODAPÃ -->
    </body>
</html>