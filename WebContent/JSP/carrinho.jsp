<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
   <head>
      <meta charset="utf-8" />
      <title>Mirror Fashion</title>
      <meta content="width=device-width, initial-scale=1.0" name="viewport" />
      <!-- Favicon -->
      <link href="../img/favicon.ico" rel="icon" />
      <link href="../css/reset.css" rel="stylesheet" />
      <!-- Google Fonts -->
      <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400|Source+Code+Pro:700,900&display=swap"  rel="stylesheet" />
      <!-- Biblioteca CSS - Bootstrap-->
      <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" />
      <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet" />
      <link href="../lib/slick/slick.css" rel="stylesheet" />
      <link href="../lib/slick/slick-theme.css" rel="stylesheet" />
      <!-- CSS Principal do Projeto -->
      <link href="../css/style.css" rel="stylesheet" />
   </head>
   <%
      List<Produto> produtosEmSessao = new ArrayList<>();
      List<Cupom> cuponsSessao = new ArrayList<>();
      Usuario usuarioLogado = new Usuario();
      
      HttpSession sessao = request.getSession();
      
         usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
      
      // pega o objeto salvo em Sessão com o nome "itensCarrinho",
      // e passa para o "produtosEmSessao" (fazendo o CAST para o tipo List<Produto>)
      produtosEmSessao = (List<Produto>) sessao.getAttribute("itensCarrinho");
      
      // pega o objeto salvo em Sessão com o nome "cupons",
      // e passa para o "cuponsSessao" (fazendo o CAST para o tipo List<Cupom>)
      cuponsSessao = (List<Cupom>) sessao.getAttribute("cupons");
      
      //pega todos enderecos do Cliente que estao na sessao
      List<Endereco> enderecos = (List<Endereco>)sessao.getAttribute("enderecosCliente");
      
      //pega todos cartões do Cliente que estao na sessao
      List<CartaoCredito> cartoes = (List<CartaoCredito>)sessao.getAttribute("cartoesCliente");  
      
      
      double total_itens = 0;
      double total_frete = 0;
      double total_pedido = 0;
      double desconto_cupons = 0;
      
      // ajusta o bug de quando abrir o carrinho pela primeira vez,
      // o valor do cupom criado na sessão, não seja atribuido com o valor nulo
      if (!cuponsSessao.isEmpty()) {
      	// calculo do desconto (todos os cupons vinculado na Sessão)
      	for(Cupom cupomDaSessao : cuponsSessao) {
      		desconto_cupons += Double.parseDouble(cupomDaSessao.getValor());
      	}
      }
      
      // faz a somatória dos itens selecionados no carrinho
      for(Produto produto : produtosEmSessao) {
      	// calculo do total dos itens (quantidade do item (*) o valor do item "preço de venda")
      	total_itens += (Double.parseDouble(produto.getQuantidadeSelecionada()) * Double.parseDouble(produto.getPrecoVenda()));
      	
      	// calculo do frete (quantidade do item (*) 10 centavos)
      	total_frete += (Double.parseDouble(produto.getQuantidadeSelecionada()) * 0.10);
      }
      
      // faz o arredondamento da variavel "total_itens" para 2 casas decimais
      total_itens = Math.round(total_itens * 100);
      total_itens = total_itens/100;
      
      // faz o arredondamento da variavel "total_frete" para 2 casas decimais
      total_frete = Math.round(total_frete * 100);
      total_frete = total_frete/100;
      
      // calculo do total do pedido (total dos itens (+) total do frete)
      total_pedido = total_itens + total_frete;
      
      // calculo o total do pedido com o desconto dos cupons
      total_pedido = (total_pedido - desconto_cupons);
      
      // faz o arredondamento da variavel "total_pedido" para 2 casas decimais
      total_pedido = Math.round(total_pedido * 100);
      total_pedido = total_pedido/100;
      
      // faz o arredondamento da variavel "desconto_cupons" para 2 casas decimais
      desconto_cupons = Math.round(desconto_cupons * 100);
      desconto_cupons = desconto_cupons/100;
      %>
   <body>
      <!-- Inicio da faixa superior - Faixa preta contendo email e telefone de "suporte"-->
      <div class="top-bar">
         <div class="container-fluid">
            <div class="row">
               <div class="col-sm-6">
                  <i class="fa fa-envelope"></i>
                  suporte@mirrorfashion.com
               </div>
               <div class="col-sm-6">
                  <i class="fa fa-phone-alt"></i>
                  +55 11 91234-5678
               </div>
            </div>
         </div>
      </div>
      <!-- Fim da faixa superior - Faixa preta contendo email e telefone de "suporte"-->
      
      <!-- Inicio da faixa de menu -  faixa rosa contendo home, produtos e minha conta -->
      <div class="nav">
         <div class="container-fluid">
            <nav class="navbar navbar-expand-md bg-dark navbar-dark">
               <a href="#" class="navbar-brand">MENU</a>
               <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
               <span class="navbar-toggler-icon"></span>
               </button>
               <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                  <div class="navbar-nav mr-auto">
                     <a href="../JSP/index.jsp" class="nav-item nav-link active">Home</a>
                     <a href="../JSP/produtos.jsp" class="nav-item nav-link">Produtos</a>
                  </div>
                  <div class="ml-autonavbar-collapse justify-content-between">Olá ${usuarioLogado.nome}</div>
                  <div class="navbar-nav ml-auto">
                     <div class="nav-item dropdown">
                        <a href="" class="nav-link dropdown-toggle" data-toggle="dropdown">Minha Conta</a>
                        <div class="dropdown-menu">
                           <a href="../JSP/minhaConta.jsp" class="dropdown-item">Meu Perfil</a>
                           <a href="" class="dropdown-item">
                              <!-- BOTAO SAIR -->
                              <form action="http://localhost:8080/eCommerce/login">
                                 <button type="submit" class="btn" name="operacao" value="EXCLUIR"><i class="fa fa-sign-out-alt"></i>Logout</button>
                              </form>
                           </a>
                        </div>
                     </div>
                  </div>
               </div>
            </nav>
         </div>
      </div>
      <!-- Fim da faixa de menu -  faixa rosa contendo home, produtos e minha conta -->
      <!-- Inicio da div contendo logo, barra de pesquisa e botão Minha Sacola-->
      <div class="bottom-bar">
         <div class="container-fluid">
            <div class="row align-items-center">
               <div class="col-md-3">
                  <!-- LOGO -->
                  <div class="logo">
                     <a href="../JSP/index.jsp">
                     <img src="../img/mir.svg" alt="Logo Mirror Fashion" />
                     </a>
                  </div>
               </div>
               <!-- BARRA DE PESQUISA -->
               <div class="col-md-6"> </div>
               <!-- MINHA SACOLA -->
               <div class="col-md-2">
                  <div class="user">
                     <a href="../JSP/carrinho.jsp" class="btn cart">
                     Minha Sacola <i class="fas fa-shopping-bag"></i>
                     </a>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <!--- Fim da div contendo logo, barra de pesquisa e botão Minha Sacola -->
      <!-- Inicio do Breadcrumb -->
      <div class="breadcrumb-wrap">
         <div class="container-fluid">
            <ul class="breadcrumb">
               <li class="breadcrumb-item"><a href="../JSP/index.jsp">Home</a></li>
               <li class="breadcrumb-item active">Carrinho</li>
            </ul>
         </div>
      </div>
      <!-- Fim do Breadcrumb -->
      <!-- Inicio do carrinho  -->
      <div class="cart-page">
         <div class="container-fluid">
            <div class="row">
            <!-- TABELA DE PRODUTOS / ENDEREÇOS / FORMA DE PAGAMENTO-->
               <div class="col-lg-8">
                  <div class="cart-page-inner">
                     <div class="table-responsive">
                        <table class="table table-bordered">
                           <thead class="thead-dark">
                              <tr>
                                 <th>Produto</th>
                                 <th>Preço</th>
                                 <th>Quantidade</th>
                                 <th>Total</th>
                                 <th>Excluir</th>
                              </tr>
                           </thead>
                           <%
                              for(Produto produtoCarrinho : produtosEmSessao){
                              %>
                           <tbody class="align-middle">
                              <tr>
                                 <td>
                                    <div class="img">
                                       <a href="#"><img src=.<%=produtoCarrinho.getFoto()%> alt="Image" /></a>
                                       <p><%=produtoCarrinho.getNome() %></p>
                                    </div>
                                 </td>
                                 <td>R$ <%=produtoCarrinho.getPrecoVenda() %></td>
                                 <td>
                                    <form class="form" action="http://localhost:8080/eCommerce/carrinho">
                                       <div >
                                          <button class="btn-minus" name="tipoDeOperacao"	value="subtracao">
                                          <i class="fa fa-minus"></i>
                                          </button>
                                          <input type="text" name="quantidadeSelecionada"	onkeypress="return event.charCode >= 48 && event.charCode <= 57" maxlength="3" value="<%=produtoCarrinho.getQuantidadeSelecionada() %>" />
                                          <button class="btn-plus" name="tipoDeOperacao" value="adicao">
                                          <i class="fa fa-plus"></i>
                                          </button>
                                       </div>
                                       <!-- operação que é acionada através do form -->
                                       <input type="hidden" name="operacao" id="operacao" value="ALTERAR">
                                       <!-- ID do Produto -->
                                       <input type="hidden" name="idProduto" id="idProduto" value="<%=produtoCarrinho.getId()%>">
                                       <!-- ID do Cliente -->
                                       <input type="hidden" name="idCliente" id="idCliente" value="<%=usuarioLogado.getId() %>">
                                    </form>
                                 </td>
                                 <%
                                    double total_item_atual = 0;
                                    
                                    total_item_atual = (Double.parseDouble(produtoCarrinho.getQuantidadeSelecionada()) * Double.parseDouble(produtoCarrinho.getPrecoVenda()));
                                    
                                    // faz o arredondamento da variavel "total_item_atual" para 2 casas decimais
                                    total_item_atual = Math.round(total_item_atual * 100);
                                    total_item_atual = total_item_atual/100;
                                    %>
                                 <td>R$ <%=total_item_atual %></td>
                                 <td>
                                    <!-- form responsavel por excluir o item inteiro do carrinho selecionado -->
                                    <form class="form" action="http://localhost:8080/eCommerce/carrinho">
                                       <button name="operacao" value="EXCLUIR"><i class="fa fa-trash"></i></button>
                                       <!-- ID do Produto -->
                                       <input type="hidden" name="idProduto" id="idProduto" value="<%=produtoCarrinho.getId() %>">
                                    </form>
                                 </td>
                              </tr>
                           </tbody>
                           <%
                              }
                              %>
                        </table>
                     </div>
                  </div>
                  <!-- ENDEREÇOS -->
                  <div class="col-lg-12 registrar__novaconta">
                     <div class="checkout-inner">
                        <div class="billing-address">
                        <!-- form para o cadastro do pedido -->
							<form action="http://localhost:8080/eCommerce/cadastroPedido">
                           <h3>Endereços</h3>
                           <a href="../JSP/novoendereco.jsp"><button type="submit" class="btn btn_NovoEndCarrinho"><i class="fa fa-plus"></i> Endereço</button></a>
                           </br>
                     
                           <div class="row">
                              <%
                                 for(Endereco e : enderecos){
                                 
                                 	// Aplicado o CAST 
                                 	Endereco d = (Endereco) e;
                                 %>
                              <div class="col-md-6">
                                 <h5><%=d.getTipoEnd() %></h5>
                                 <p><%=d.getTipoResidencia() %> - <%=d.getObservacoes()%></p>
                                 <p><%=d.getLogradouro() %></p>
                                 <p><%=d.getCidade() %> - <%=d.getEstado() %>  / CEP <%=d.getCep() %></p>
                                 <input type="radio" name="selecioneEndereco" value="<%=d.getId() %>" class="btn" />
                                 <i class="fa fa-check-square"></i> Selecionar
                                 </br></br>
                              </div>
                              <%
                                 }
                                 %>
                              <!-- FORMA DE PAGAMENTO  -->
                              <div class="col-md-12">
                                 <div class="checkout-payment">
                                    <div class="payment-methods">
                                       </br>
                                       <hr size="12">
                                       </br>
                                       <h3>Forma de Pagamento</h3>
                                       <a href="../JSP/novoCartao.jsp"><button class="btn btn_cadCartao"><i class="fa fa-plus"></i> Cartão</button></a>
                                       <br>
                                       <div class="payment-method">
                                          <div class="custom-control custom-radio">
                                             <input type="radio" class="custom-control-input" id="payment-1" name=selecioneCartao1"  />
                                             <label class="custom-control-label" for="payment-1">Pagar com 1 Cartão de Crédito</label>
                                          </div>
                                          <div class="payment-content" id="payment-1-show">
                                             <div class="col-md-12">
                                                <select class="form-control" type="text" name=selecioneCartao1" >
                                                   <option selected disabled>Selecione o cartão</option>
                                                   <%
                                                      for(CartaoCredito c : cartoes){
                                                      %>
                                                   <option name=id_cartao_1" value="<%=c.getId() %>">
                                                      <%=c.getNome() %> - <%=c.getBandeira() %> / <%=c.getValidade() %>
                                                   </option>
                                                   <%
                                                      }
                                                      %>
                                                </select>
                                             </div>
                                          </div>
                                       </div>
                                       <div class="payment-method">
                                          <div class="custom-control custom-radio">
                                             <input type="radio" class="custom-control-input" id="payment-2" />
                                             <label class="custom-control-label" for="payment-2" name=selecioneCartao1" >Pagar com 2 Cartões de Crédito</label>
                                          </div>
                                          <div class="payment-content" id="payment-2-show">
                                             <div class="col-md-12">
                                                <select class="form-control" type="text" name="selecioneCartao1">
                                                   <option selected disabled>Selecione o 1º cartão</option>
                                                   <%
                                                      for(CartaoCredito c : cartoes){
                                                      %>
                                                   <option name=id_cartao_1" value="<%=c.getId() %>">
                                                      <%=c.getNome() %> - <%=c.getBandeira() %> / <%=c.getValidade() %>
                                                   </option>
                                                   <%
                                                      }
                                                      %>
                                                </select>
                                             </div>
                                             <div class="col-md-12">
                                                <select class="form-control" type="text" name="selecioneCartao2">
                                                   <option selected disabled>Selecione o 2º cartão</option>
                                                   <%
                                                      for(CartaoCredito c : cartoes){
                                                      %>
                                                   <option name=id_cartao_2" value="<%=c.getId() %>">
                                                      <%=c.getNome() %> - <%=c.getBandeira() %> / <%=c.getValidade() %>
                                                   </option>
                                                   <%
                                                      }
                                                      %>
                                                </select>
                                             </div>
                                          </div>
                                       </div>
                                    </div>
                                 </div>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
               <!-- FIM DOS PRODUTOS / ENDEREÇOS/ FORMA DE PAGAMENTO -->
               
               <!-- RESUMO / CUPONS -->
               <div class="col-lg-4">
                  <div class="cart-page-inner">
                     <div class="row">
                        <div class="col-md-12">
                           <!-- RESUMO -->
                           <div class="cart-summary">
                              <div class="cart-content">
                                 <h1>Resumo</h1>
                                 <p>Subtotal<span>R$ <%=total_itens %></span></p>
                                 <p>Frete<span>R$ <%=total_frete %> </span></p>
                                 <p>Descontos<span class="desconto">- R$ <%=desconto_cupons %></span></p>
                                 <h2>Total<span>R$ <%=total_pedido %></span></h2>
                              </div>
                           </div>
                           <hr size="8">
                           <!-- CUPONS -->
                           <div class="cart-summary">
                              <div class="cart-content">
                                 <table class="table table-bordered">
                                    <thead class="thead-dark">
                                       <th class="tit_tabela_cupons" colspan="3">Cupons ativos</th>
                                       <tr>
                                          <th>Nome</th>
                                          <th>Valor R$</th>
                                          <th>Ação</th>
                                       </tr>
                                    </thead>
                                    <tbody>
                                       <tr>
                                          <td>promo10</td>
                                          <td>10,00</td>
                                          <td>
                                          	<input type="checkbox" name="cupom"/>
                                          </td>
                                       </tr>
                                       <tr>
                                          <td>troca</td>
                                          <td>90,00</td>
                                          <td>
                                          	<input type="checkbox" name="cupom"/>
                                          </td>
                                       </tr>
                                    </tbody>
                                 </table>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
                  <!-- BOTÃO FINALIZAR COMPRA -->
                  <div class="checkout-btn ">
                     <button class="btn btnFinalizarCompra" name="operacao" value="SALVAR"> <i class="fa fa-check-circle"></i> Finalizar Compra</button>
                  </div>
               </div>
               <!-- ID do Cliente -->
				<input type="hidden" name="idCliente" id="idCliente" value="<%=usuarioLogado.getId() %>">
				<!-- ID do Cupom -->
				<input type="hidden" name="total_cupons" id="total_cupons" value="<%=desconto_cupons %>">
				<!-- Total dos Itens -->
			    <input type="hidden" name="total_itens" id="total_itens" value="<%=total_itens %>">
			    <!-- Total do Frete -->
			    <input type="hidden" name="total_frete" id="total_frete" value="<%=total_frete %>">
			    <!-- Total do Pedido -->
			    <input type="hidden" name="total_pedido" id="total_pedido" value="<%=total_pedido %>">
			    
			    <!-- Forma de Pagamento - Setado apenas como Cartao -->
			    <input type="hidden" name="selecioneFormadePagamento" id="selecioneFormadePagamento" value="cartao">
		</form>
            </div>
         </div>
      </div>
      <!-- Fim do Carrinho -->
      <!-- Início do Footer -->
      <div class="footer">
         <div class="container-fluid">
            <div class="row">
               <div class="col-lg-3 col-md-6">
                  <div class="footer-widget">
                     <h2>Contato</h2>
                     <div class="contact-info">
                        <p><i class="fa fa-map-marker"></i>Mogi das Cruzes - SP</p>
                        <p><i class="fa fa-envelope"></i>Lorena Oliveira</p>
                        <p><i class="fa fa-phone"></i>+55 11 91234-5678</p>
                     </div>
                  </div>
               </div>
               <div class="col-lg-3 col-md-6"></div>
               <div class="col-lg-3 col-md-6">
                  <div class="footer-widget">
                     <h2>Siga-nos</h2>
                     <div class="contact-info">
                        <div class="social">
                           <a href=""><i class="fab fa-twitter"></i></a>
                           <a href=""><i class="fab fa-facebook-f"></i></a>
                           <a href=""><i class="fab fa-linkedin-in"></i></a>
                           <a href=""><i class="fab fa-instagram"></i></a>
                           <a href=""><i class="fab fa-youtube"></i></a>
                        </div>
                     </div>
                  </div>
               </div>
               <div class="col-lg-3 col-md-6">
                  <div class="footer-widget">
                     <h2>Infos</h2>
                     <ul>
                        <li><a href="#">Sobre nós</a></li>
                        <li><a href="#">Política de Privacidade</a></li>
                        <li><a href="#">Termos & Condições</a></li>
                     </ul>
                  </div>
               </div>
            </div>
            <div class="row payment align-items-center">
               <div class="col-md-6">
                  <div class="payment-method">
                     <h2>Forma de pagamento</h2>
                     <img src="../img/payment-method.png" alt="Forma de pagamento" />
                  </div>
               </div>
               <div class="col-md-6">
                  <div class="payment-security">
                     <h2>Compre com segurança</h2>
                     <img src="../img/godaddy.svg" alt="Segurança" />
                     <img src="../img/norton.svg" alt="Segurança" />
                     <img src="../img/ssl.svg" alt="Segurança" />
                  </div>
               </div>
            </div>
         </div>
      </div>
      <!-- Fim do Footer -->
      <!-- Inicio do rodapé preta  -->
      <div class="footer-bottom">
         <div class="container">
            <div class="row">
               <div class="col-md-6 copyright">
                  <p>
                     Copyright &copy; <a href="../JSP/index.jsp">Mirror Fashion</a> - 2022 - Todos os direitos reservados
                  </p>
               </div>
               <!-- <div class="col-md-6 template-by">
                  <p>Template By <a href="https://htmlcodex.com">HTML Codex</a></p>
                  </div> -->
            </div>
         </div>
      </div>
      <!-- Fim do rodapé preta  -->
      <!-- Voltar pro topo -->
      <a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>
      <!-- Bibliotecas JavaScript  -->
      <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
      <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
      <script src="../lib/easing/easing.min.js"></script>
      <script src="../lib/slick/slick.min.js"></script>
      <!--  Javascript -->
      <script src="../js/main.js"></script>
   </body>
</html>