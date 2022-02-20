<!--  CARRINHO -->
<!DOCTYPE html>
<html>
    <head>
        <title>Mirror Fashion</title>
        <link rel="stylesheet" type="text/css" href="../css/carrinho.css">
        <meta charset="utf-8">
        <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
            pageEncoding="UTF-8"%>
            <!-- FAVICON -->
        <link rel="shortcut icon" href="../favicon.ico">
        <script type="text/javascript" src="../js/qtde.js"></script>
    </head>
    <header class="container">
        <h1><a href="../JSP/index.jsp"><img src="../img/logo.png" alt="Mirror Fashion"></a></h1>
        <p class="sacola"><a href="../JSP/carrinho.jsp">Minha Sacola</a></p>
        <form action="http://localhost:8080/eCommerce_roupa/login">
            <p>Olá ${usuarioLogado.nome} <button class="button" type="submit" name="operacao" value="EXCLUIR" id="btnSair">Sair</button></p>
        </form>
        <nav class="menu-opcoes" class="links">
            <ul>
                <li><a href="../JSP/index.jsp">Home</a></li>
                <li><a href="../JSP/produtos.jsp">Produtos</a></li>
                </br>
            </ul>
        </nav>
    </header>
    <body>
        <!-- PARTE PRINCIPAL-->
        <div class="container carrinho">
            
            <!-- CARRINHO  --> <br>
            <p>MEU CARRINHO</p>
            
            <table id="tabelaProduto"> 
                <tr>
                
                <!-- TITULOS -->
                    <th>Codigo</th>
                    <th>Produto</th>
                    <th>Cor</th>
                    <th>Tamanho</th>
                    <th>Qtde.</th>
                    <th>Preco</th>
                    <th>Acoes</th>
                </tr>
                <!-- CODIGO -->
                <td>01</td>
                <!-- PRODUTOS -->
                <td>Blusa M. Comprida</td>
                 <!-- COR -->
                <td>Amarela</td>
                <!-- TAMANHO -->
                <td>36</td>
                <!-- QUANTIDADE -->
                <td>
                    <input type="button" name="menos" id="menos" value="-" />
                    <input type="text" name="format" value="1" id="format" size="2" />
                    <input type="button" name="mais" id="mais" value="+" />
                </td>
                <!-- PREÇO -->
                <td>
                    <input type="text" step="0.01" name="quantity" min="0.01" name="total" id="total" value="49.99"/>
                </td>
                <!-- EXCLUIR PRODUTO -->
                <td>
                    <button class="button" type="submit" name="operacao" value="EXCLUIR" id="btnExcluir">X</button>
                </td>
                
                <tr>
                <!-- PRODUTO 2 -->
                
                <!-- CODIGO -->
                <td>02</td>
                <!-- PRODUTOS -->
                <td>Calça de Elastico - Preto</td>
                <!-- COR -->
                <td>Preto</td>
                <!-- TAMANHO -->
                <td>40</td>
                <!-- QUANTIDADE -->
                <td>
                    <input type="button" name="menos" id="menos" value="-" />
                    <input type="text" name="format" value="1" id="format" size="2" />
                    <input type="button" name="mais" id="mais" value="+" />
                </td>
                <!-- PREÇO -->
                <td>
                    <input type="text" step="0.01" name="quantity" min="0.01" name="total" id="total" value="42.90"/>
                </td>
                <!-- EXCLUIR PRODUTO -->
                <td>
                    <button class="button" type="submit" name="operacao" value="EXCLUIR" id="btnExcluir">X</button>
                </td>
                </tr>
            </table>
                
                <!-- VALORES -->
                <br>
            <table id=tabelaValores>
                <tr> 
                    <td>Valor Produtos</td>
                    <td>R$ 92.89</td>
                </tr>
                <tr>
                    <td>Valor Frete</td>
                    <td>R$ 2.00</td>
                </tr>
                <tr>
                    <td>Desconto</td>
                    <td>R$ 5.00</td>
                </tr>
                <tr>
                    <td>Valor Total</td>
                    <td>R$ 89.89</td>
                </tr>
            
           
            <div>
            Endereco de entrega:
            <select>
                <option disabled selected>Selecione </option>
                <option value="1">Casa Mae - Av. Feliciano 90</option>
                <option value="2">Apto Jr - Rua Olavo ap40</option>
            </select>
            <br>
            <br>
            Forma de Pagamento:
            <select>
                <option disabled selected>Selecione </option>
                <option value="1">Mastercard - 9008</option>
                <option value="2">Visa - 7527</option>
            </select>
            <br><br>
            Cupom: 
            <select>
                <option>DESC5</option>
               <option>TROCAPEDIDO</option>
                <option>DEVOLUCAOPEDIDO</option>
             </select> 
            <button type="submit" name="validarCupom" id="validarCupom" >Validar</button>
            </div>
           </table>
           <br>
           <button type="submit" name="confirmarCompra" id="confirmarCompra">Confirmar Compra</button>
        </div>
          
        <!-- FIM CONTAINER DESTAQUE -->
        <!-- RODAPE -->
        <footer>
            <div class="container">
                <a href="../JSP/index.jsp">
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