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
        
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
       
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
        <div class="container Prod">
           
            <!-- CARRINHO  -->
                <script type="text/javascript">
        var produtos = [
              {codigo: '001', nome: "Arroz", valor: 1.99},
              {codigo: '002', nome: "Feijão", valor: 12.25},
              {codigo: '003', nome: "Ovo", valor: 6.11},
              {codigo: '004', nome: "Farinha", valor: 7.07},
            ];

            produtos.forEach(function (item) {
              $('<option />')
                .attr('value', item.codigo)
                .text(`${item.nome} - R$ ${item.valor}`)
                .appendTo('#produtos');
            });

            $(document).on('click', '#btn-add', function () {
              var $dropdown = $('#produtos'), codigo = $dropdown.val();
              
              // 1. Se dropdown não possui nenhum item selecionado nao faça nada
              if (!codigo.length) return;
              
              // obter o item pelo codigo
              var item = getProdutoByCodigo(produtos, codigo);
              
              // 2. Se item selecionado já está no carrinho, 
              // incrementar a quantidade se nao tiver adiciona-lo
              if (!$(`tr#${item.codigo}`).length) addProduto(item);
              else incrementarQuantidade(item);
              
              atualizarTotal();
            });

            $(document).on('change', '.field-qtd', function () {
              var $this = $(this), 
                  item = getProdutoByCodigo(produtos, $this.data('codigo')), 
                  qtd = $this.val(),
                  subTotal = (qtd * item.valor).toFixed(2);
              
              $this.parents('tr').find('.subtotal').text(`R$ ${subTotal}`);
              atualizarTotal();
            });

            $(document).on('click', '.btn-remover', function () {
              $(this).parents('tr').remove();
              atualizarTotal();
            });

            function getProdutoByCodigo(produtos, codigo) {
              return produtos.filter(produto => produto.codigo === codigo)[0];
            }

            function createDropdownQuantidade(item) {
              var $dropdown = $('<select />')
                .attr({name: 'quantidade'})
                .addClass('form-control field-qtd')
                .data('codigo', item.codigo);
                
               for (var i = 1; i < 100; i ++) {
                $('<option />').attr('value', i).text(i).appendTo($dropdown);
               }
               
               return $dropdown;
            }

            function addProduto(item) {  
              var $tr = $('<tr />').attr('id', item.codigo);
              $('<td />').text(item.codigo).appendTo($tr);
              $('<td />').text(item.nome).appendTo($tr);
              $('<td />').append(createDropdownQuantidade(item)).appendTo($tr);  
              $('<td />').text(`R$ ${item.valor.toFixed(2)}`).appendTo($tr);
              $('<td />').addClass('subtotal').text(`R$ ${item.valor.toFixed(2)}`).appendTo($tr);
              
              var $button = $('<button />')
                .addClass('btn btn-danger btn-remover')
                .html('&times;')
                .attr('title', 'Remover');
                
              $('<td />').append($button).appendTo($tr);
              
              $tr.appendTo('#cart-body');
            }

            function incrementarQuantidade(item) {
              var $dropdown = $(`tr#${item.codigo}`).find('select'), qtd = $dropdown.val();
              $dropdown.val(++qtd).trigger('change');
            }

            function atualizarTotal() {
              var total = 0;
              $('#cart-body').find('tr').each(function () {
                var codigo = $(this).attr('id'), 
                    qtd = $(this).find('.field-qtd').val(), 
                    item = getProdutoByCodigo(produtos, codigo);
              
                total += (qtd * item.valor);
              });
                
              $('.total').text(`R$ ${total.toFixed(2)}`);
            }
        </script>
        





<table class="table">
  
<div class="form-inline my-3">
 <select name="produtos" id="produtos" class="form-control">
  <option value="">- Selecione -</option>
 </select>
 <button type="button" class="btn btn-primary ml-2" id="btn-add">Add</button>
</div>

<table class="table">
  <thead>
    <th>Código</th>
    <th>Produto</th>
    <th>Qtd</th>
    <th>Preço unitário</th>
    <th>Subtotal</th>
    <th>Remover</th>
  </thead>
    
  <tbody id="cart-body"></tbody>
  <tfoot>
    <tr>
      <td colspan="5" class="text-right text-uppercase border-top">total</td>
      <td class="total border-top">R$ 0.00</td>
    </tr>
  </tfoot>
</table>
           
           
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