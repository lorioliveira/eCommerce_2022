<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import="java.util.List"%>  

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="UTF-8"%>
   	
<!DOCTYPE html>
<html lang="pt-BR">
   <head>
      <meta charset="utf-8">
      <title>Mirror Fashion</title>
      <meta content="width=device-width, initial-scale=1.0" name="viewport">
      
      <!-- Favicon -->
      <link href="../img/favicon.ico" rel="icon">
      <link href="../css/reset.css" rel="stylesheet">
      
      <!-- Google Fonts -->
      <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400|Source+Code+Pro:700,900&display=swap" rel="stylesheet">
      
      <!-- Biblioteca CSS - Bootstrap-->
      <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
      <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
      <link href="../lib/slick/slick.css" rel="stylesheet">
      <link href="../lib/slick/slick-theme.css" rel="stylesheet">
      
      <!--- Biblioteca CSS - Principal-->
      <link href="../css/style.css" rel="stylesheet">
   </head>
   <%
      Usuario usuarioLogado = new Usuario();
      
      HttpSession sessao = request.getSession();
      usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
      
      //pega todos enderecos que estao na sessao
      List<Endereco> enderecos = (List<Endereco>)sessao.getAttribute("enderecosCliente");
      
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
                  </div>
               </div>
         </div>
         </nav>
      </div>
      </div>
      <!-- Fim da faixa de menu -  faixa rosa contendo home, produtos e minha conta--> 
      
      <!-- Inicio da div contendo logo, barra de pesquisa e botão Minha Sacola -->
      <div class="bottom-bar">
         <div class="container-fluid">
            <div class="row align-items-center">
               <div class="col-md-3">
                  <div class="logo">
                     <a href="../JSP/index.jsp">
                     <img src="../img/mir.svg" alt="Logo Mirror Fashion" >
                     </a>
                  </div>
               </div>
               <div class="col-md-6">
                  <div class="search">
                     <!-- <input type="text" placeholder="Procuro por ...">
                        <button><i class="fa fa-search"></i></button> -->
                  </div>
               </div>
            </div>
         </div>
      </div>
      <!-- Fim da div contendo logo, barra de pesquisa e botão Minha Sacola -->
      <!-- Inicio do Breadcrumb -->
      <div class="breadcrumb-wrap">
         <div class="container-fluid">
            <ul class="breadcrumb">
               <li class="breadcrumb-item"><a href="../JSP/index.jsp">Home</a></li>
               <li class="breadcrumb-item active">Minha Conta</li>
            </ul>
         </div>
      </div>
      <!-- Fim do Breadcrumb -->
      <!-- Inicio da Minha Conta -->
      <div class="my-account">
         <div class="container-fluid">
            <div class="row">
               <div class="col-md-3">
                  <div class="nav flex-column nav-pills" role="tablist" aria-orientation="vertical">
                     <a class="nav-link" id="account-nav" data-toggle="pill" href="#account-tab" role="tab"><i class="fa fa-user"></i>Meus Dados</a>
                     <a class="nav-link" id="address-nav" data-toggle="pill" href="#address-tab" role="tab"><i class="fa fa-map-marker-alt"></i>Meus Endereços</a>
                     <a class="nav-link" id="payment-nav" data-toggle="pill" href="#payment-tab" role="tab"><i class="fa fa-credit-card"></i>Cartões</a>
                     <a class="nav-link" id="orders-nav" data-toggle="pill" href="#orders-tab" role="tab"><i class="fa fa-receipt"></i>Pedidos</a>
                     <a class="nav-link" id="changepassword-nav" data-toggle="pill" href="#changepassword-tab" role="tab"><i class="fa fa-key"></i>Alterar Senha</a>
                  </div>
                  <br>
                  <!-- BOTAO SAIR -->
                  <form action="http://localhost:8080/eCommerce/login">
                     <button type="submit" class="btn" name="operacao" value="EXCLUIR"><i class="fa fa-sign-out-alt"></i>Logout</button>
                  </form>
               </div>
               <div class="col-md-9">
                  <div class="tab-content">
                     <div class="tab-pane fade show active" id="dashboard-tab" role="tabpanel" aria-labelledby="dashboard-nav">
                        <h4>Minha conta</h4>
                        <p><br>
                           <i class="fa fa-arrow-left"></i>  Aqui você poderá visualizar seus dados cadastrais, endereços, cartões e muito mais !
                        </p>
                     </div>
                     <!-- MEUS DADOS PESSOAIS -->
                     <div class="tab-pane fade" id="account-tab" role="tabpanel" aria-labelledby="account-nav">
                        <h4>Dados Pessoais</h4>
                        <form action="http://localhost:8080/eCommerce/cadastro" >
                           <div class="row">
                              <div class="col-md-6">
                                 <input class="form-control" type="text" name="nome" value="<%=usuarioLogado.getNome() %>">
                              </div>
                              <div class="col-md-6">
                                 <input class="form-control" name="cpf" id="RegraCPF" onkeydown="javascript: fMasc( this, mCPF );" maxlength="14" value="<%=usuarioLogado.getCpf() %>" readonly>
                              </div>
                              <div class="col-md-6">
                                 <input class="form-control" type="date" name="data_Nascimento" class="fa fa-birthday-cake" value="<%=usuarioLogado.getData_Nascimento() %>">
                              </div>
                              <div class="col-md-6">
                                 <input type="tel" class="form-control" id="telefone" name="telefone" maxlength="15" placeholder="Celular" pattern="\(\d{2}\)\s*\d{5}-\d{4}" value="<%=usuarioLogado.getTelefone() %>">
                              </div>
                              <div class="col-md-6">
                                 <input class="form-control valEmail" name="email" type="email" value="<%=usuarioLogado.getEmail() %>">
                              </div>
                              <div class="col-md-4">
                                 <button type="submit" class="btn" name="operacao" value="ALTERAR"><i class="fa fa-sync-alt"></i> Atualizar Dados</button>
                                 <br>
                              </div>
                              <input type="hidden" name="tipoCliente" value="cliente"/>
                              <input type="hidden" name="status" value="ativo"/>
                              <input type="hidden" name="alteraCliente" value="1"/>
                              <input type="hidden" name="id" value="<%=usuarioLogado.getId()%>"/>
                              <input type="hidden" name="senha" accesskey="S" value="<%=usuarioLogado.getSenha() %>" />
                              <input type="hidden" name="confirmarSenha" value="<%=usuarioLogado.getConfirmarSenha() %>"/>
                              <input type="hidden" name="genero" value="<%=usuarioLogado.getGenero() %> "> 
                           </div>
                        </form>
                     </div>
                     <!-- MEUS ENDEREÇOS  -->
                     <div class="tab-pane fade" id="address-tab" role="tabpanel" aria-labelledby="address-nav">
                        <h4>Meus Endereços</h4>
                        <button type="submit" class="btn ml-auto nav-link btn_NovoEndereco"><a href="../JSP/novoendereco.jsp"><i class="fa fa-map-marker-alt"></i> Novo</a></button>
                        </br>
                        <div class="row">
                           <%
                              for(Endereco e : enderecos){
                              
                              	// Aplicado o CAST 
                              	Endereco d = (Endereco) e;
                           %>	
                              
                           <div class="col-md-4">
                              <h5><%=d.getTipoResidencia() %></h5>
                              <p><%=d.getObservacoes()%></p>
                              <p><%=d.getLogradouro() %></p>
                              <p><%=d.getCidade() %> - <%=d.getEstado() %></p>
                              <p>CEP <%=d.getCep() %></p>
                              <a href="/eCommerce/cadastroEndereco?id=<%= d.getId()%>&idCliente=<%=usuarioLogado.getId() %>&operacao=ALTERAR&alteraEndereco=0"><button class="btn"><i class="fa fa-edit"></i></button></a>
                              <a href="/eCommerce/cadastroEndereco?id=<%= d.getId()%>&idCliente=<%=usuarioLogado.getId() %>&operacao=EXCLUIR"><button class="btn"><i class="fa fa-trash-alt"></i></button></a>
                           </div>
                           <%
                              }
                            %> 
                        </div>
                     </div>
                     <!-- FORMA DE PAGAMENTO - CARTÕES -->
                     <div class="tab-pane fade" id="payment-tab" role="tabpanel" aria-labelledby="payment-nav">
                        <h4>Meus Cartões <a href="../JSP/novocartao.jsp"><button class="btn ml-auto nav-link btn_NovoCartao"><i class="fa fa-credit-card"></i> Novo </a></button></h4>
                        <table class="table table-bordered">
                           <thead class="thead-dark">
                              <tr>
                                 <th>Bandeira</th>
                                 <th>Nº Cartão</th>
                                 <th>Titular</th>
                                 <th>Validade</th>
                                 <th>Preferencial</th>
                                 <th>Ação</th>
                              </tr>
                           </thead>
                           <tbody>
                              <tr>
                                 <td>Mastercard</td>
                                 <td>5144 **** **** 5978</td>
                                 <td>lorena s oliveira</td>
                                 <td>03/2029</td>
                                 <td>Sim</td>
                                 <td><a href="../JSP/alterarcartao.jsp"><button class="btn"><i class="fa fa-eye"></i></button></td>
                                 </a>
                              </tr>
                              <tr>
                                 <td>Visa</td>
                                 <td>4916 **** **** 8350</td>
                                 <td>lorena s oliveira</td>
                                 <td>06/2023</td>
                                 <td>Não</td>
                                 <td><a href="../JSP/alterarcartao.jsp"><button class="btn"><i class="fa fa-eye"></i></button></td>
                                 </a>
                              </tr>
                           </tbody>
                        </table>
                     </div>
                     <!-- MEUS PEDIDOS -->
                     <div class="tab-pane fade" id="orders-tab" role="tabpanel" aria-labelledby="orders-nav">
                        <div class="table-responsive">
                           <h4>Meus Pedidos</h4>
                           <table class="table table-bordered">
                              <thead class="thead-dark">
                                 <tr>
                                    <th>Nº Pedido</th>
                                    <th>Data</th>
                                    <th>Valor R$</th>
                                    <th>Status</th>
                                    <th>Ação</th>
                                 </tr>
                              </thead>
                              <tbody>
                                 <tr>
                                    <td>001</td>
                                    <td>21 Jun 2021</td>
                                    <td>$239</td>
                                    <td>Em Processamento</td>
                                    <td><a href="../JSP/detalhePedido.jsp"><button class="btn"><i class="fa fa-eye"></i></button></a></td>
                                 </tr>
                                 <tr>
                                    <td>001</td>
                                    <td>10 Jan 2021</td>
                                    <td>$99</td>
                                    <td>Entregue</td>
                                    <td><a href="../JSP/detalhePedido.jsp"><button class="btn"><i class="fa fa-eye"></i></button></a></td>
                                 </tr>
                                 <tr>
                                    <td>001</td>
                                    <td>01 Fev 2020</td>
                                    <td>$599</td>
                                    <td>Cancelado</td>
                                    <td><a href="../JSP/detalhePedido.jsp"><button class="btn"><i class="fa fa-eye"></i></button></a></td>
                                 </tr>
                                 <tr>
                                    <td>001</td>
                                    <td>28 Ago 2021</td>
                                    <td>$99</td>
                                    <td>Aprovado</td>
                                    <td><a href="../JSP/detalhePedido.jsp"><button class="btn"><i class="fa fa-eye"></i></button></a></td>
                                 </tr>
                              </tbody>
                           </table>
                        </div>
                     </div>
                     <!-- ALTERAR SENHA -->
                     <div class="tab-pane fade" id="changepassword-tab" role="tabpanel" aria-labelledby="changepassword-nav">
                        <h4>Alterar Senha</h4>
                        <br>
                        <div class="row">
                        	<form action="http://localhost:8080/eCommerce/cadastro" method="post">
                        
		                        <input type="hidden" name="nome" value="<%=usuarioLogado.getNome() %>" />      
				                <input type="hidden" name="cpf" value="<%=usuarioLogado.getCpf() %>"/>
				                <input type="hidden" name="data_Nascimento" value="<%=usuarioLogado.getData_Nascimento() %>">
				                <input type="hidden" name="genero" value="<%=usuarioLogado.getGenero()%>">
				                <input type="hidden" name="telefone" value="<%=usuarioLogado.getTelefone() %>">
				                <input type="hidden" name="email" value="<%=usuarioLogado.getEmail() %>"/>
			                
	                           <div class="col-md-10">
	                              <label>Digite nova senha:</label>
	                              <input class="form-control" type="password" name="senha">
	                           </div>
	                           <div class="col-md-10">
	                              <label>Confirme a nova senha:</label>
	                              <input class="form-control" type="password"  name="confirmarSenha">
	                           </div>
	                           <div class="col-md-6">
	                              <button class="btn" name="operacao" value="ALTERAR"><i class="fa fa-check"></i> Salvar</button>
	                              <br>
	                           </div>
	                           <input type="hidden" name="status" value="ativo"/>
				            <input type="hidden" name="tipoCliente" value="cliente"/>
						    <input type="hidden" name="alteraCliente" value="1"/>
				            <input type="hidden" name="id" value="<%=usuarioLogado.getId()%>"/>
			            </form>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <!-- Fim da Minha Conta -->
      
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
               <div class="col-lg-3 col-md-6">
                  <!--VAZIO PARA DAR ESPAÇO ENTRE DADOS E REDES SOCIAIS  -->
               </div>
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
                     <img src="../img/payment-method.png" alt="Forma de Pagamento" />
                  </div>
               </div>
               <div class="col-md-6">
                  <div class="payment-security">
                     <h2>Compre com segurança</h2>
                     <img src="../img/godaddy.svg" alt="Payment Security" />
                     <img src="../img/norton.svg" alt="Payment Security" />
                     <img src="../img/ssl.svg" alt="Payment Security" />
                  </div>
               </div>
            </div>
         </div>
      </div>
      <!-- Fim do Footer -->
      
      <!-- Footer Bottom Start -->
      <div class="footer-bottom">
         <div class="container">
            <div class="row">
               <div class="col-md-6 copyright">
                  <p>Copyright &copy; <a href="../JSP/index.jsp">Mirror Fashion</a> - 2021 - Todos os direitos reservados</p>
               </div>
            </div>
         </div>
      </div>
      <!-- Footer Bottom End --> 
            
      <!-- Back to Top -->
      <a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>
      
      <!-- JavaScript Libraries -->
      <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
      <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
      <script src="../lib/easing/easing.min.js"></script>
      <script src="../lib/slick/slick.min.js"></script>
      
      <!--  Javascript do Projeto -->
      <script src="../js/main.js"></script>
      <script src="../js/all.js"></script>
   </body>
</html>