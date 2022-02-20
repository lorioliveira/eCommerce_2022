<!-- CARTAO DE CREDITO -->

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
        <link rel="stylesheet" type="text/css" href="../css/perfil.css">
        <meta charset="utf-8">
        <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    		pageEncoding="UTF-8"%>	
    </head>
    <header class="container">
        <h1><a href="../JSP/index.jsp"><img src="../img/logo.png" alt="Mirror Fashion"></a></h1>
         <p class="sacola"><a href="./carrinho.jsp">Minha Sacola</a></p>
         
         <!-- AQUI INFORMA QUEM É O USUARIO LOGADO -->
         <form action="http://localhost:8080/eCommerce_roupa/login">
        <p>Olá ${usuarioLogado.nome} <button type="submit" name="operacao" value="EXCLUIR" id="btnSair">Sair</button></p>
        <script>
               function sair(){
                   alert("Você saiu com sucesso! Volte em breve!");
               }
        </script>
        </form> 
        
        <nav class="menu-opcoes" class="links">
            <ul>
                <li><a href="../JSP/index.jsp">Home</a></li>
                <li><a href="../JSP/produtos.jsp">Produtos</a></li>
                <li><a href="../JSP/perfil.jsp">Perfil</a></li>
            </ul>
        </nav>
    </header>
    
    <%
        Usuario usuarioLogado = new Usuario();
        
        HttpSession sessao = request.getSession();
        usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");   
        
        
        CupomDAO cupomDAO = new CupomDAO();
        Cupom cupom = new Cupom();
        
        //Lista de cupons vinculados ao cliente logado
        List<Cupom> cupons = cupomDAO.consultarCupomByIdCliente(usuarioLogado.getId());
    %>
    
    <body>
        <!-- CONTAINER - DESTAQUE - PARTE PRINCIPAL-->
        <div class="container destaque">
            <section class="menu-departamentos">
                <h2 style="margin-left: 5px;">Perfil</h2>
                <nav>
                    <li>
                        <a href="../JSP/meusdados.jsp">Meus Dados</a>
                        <ul>
                            <li><a href="../JSP/meusenderecos.jsp">Enderecos</a></li>
                            <li><a href="../JSP/cartoes.jsp">Cartoes e Cupons</a></li>
                            <li><a href="../JSP/alterarsenha.jsp">Alterar Senha</a></li>
                        </ul>
                    </li>
                    <li><a href="../JSP/pedidos.jsp">Meus Pedidos</a></li>
                </nav>
            </section>
            
            <!-- DADOS PARA LOGAR NA CONTA  -->
            <form class="formularioCartao" action="http://localhost:8080/eCommerce_roupa/cadastroCartao">
                <div id="cartaocredito">
                    <p id="titulo">* CADASTRO DE CARTÃO *</p>
                    <label>Nº do Cartão: </label> <input type="text" id="numerocartao" name="nCartao"  maxlength="20" />    
                    <label>Bandeira: </label>
                    <select name="bandeira">
                        <option disabled selected>Selecione</option>
                        <option value="Elo">Elo</option>
                        <option value="Mastercard">Mastercard</option>
                        <option value="Visa">Visa</option>
                    </select>
                    <br>
                    <label>Nome do Títular Completo: </label><input type="text" id="nomecartao" name="nome" placeholder="como esta no cartao" />
                    <div>                
                        <label>Validade:</label>  <input type="month" class="form-control" name="validade" >
                        
                        <label id="labelcvv">CVV:</label><input type="text" id="cvv" name="cvv" maxlength="3">
                        
                        <label>Cartão Principal:</label>
	                        <select name="preferencial">
	                        <option value="Sim">Sim</option>
	                        <option value="Nao">Nao</option>
                    </select>
                    <br>
                    </div>

                    <br>
                    <button type="submit" name="operacao" value="SALVAR" id="btnSalvar">Salvar Cartão</button>
                    <button type="submit" name="operacao" value="CONSULTAR" id="btnConsultar">Consultar Cartões</button>
                </div>
        </div>
        <!-- FIM CONTAINER -->
                <input type="hidden" name="alteraPreferencial" value="0"/>
                <input type="hidden" name="idCliente" value="<%=usuarioLogado.getId()%>"/>
            </form>
            
            <!--  LISTAGEM DE CUPONS DIPONÍVEIS PARA O CLIENTE -->
            <div class="container destaque">
                <div class="formCupom"> 
                    <p>* CUPONS DISPONÍVEIS *</p>
                       <table>
                        <tr>
                            <th>Cupom</th>
                            <th>Tipo</th>
                            <th>Valor</th>
                        </tr>
                  <%      
                         for(Cupom c : cupons) {
                          
                 %>
                  
                  <!-- CONTEUDO DA TABELA E BOTOES AO LADO -->
                  <tr>
                     <td> <%=c.getNome()%> </td>
                     <td> <%=c.getTipo()%> </td>
                     <td> R$ <%=c.getValor()%> </td>
                  </tr>
                 <%
                     }
                 %>
                       </table>    
                </div>
                
            </div>
        <!-- FIM DOS DADOS -->
        <!-- RODAPÃ -->
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
        <!-- FIM DO RODAPÃ -->
    </body>
</html>