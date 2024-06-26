<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import="java.util.List"%>  

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="UTF-8"%>
   	

<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="utf-8">
        <title>[Admin] Mirror Fashion</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
		<meta content="eCommerce HTML Template Free Download" name="keywords">
    	<meta content="eCommerce HTML Template Free Download" name="description">
    	
        <!-- Favicon -->
        <link href="./img/favicon.ico" rel="icon">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400|Source+Code+Pro:700,900&display=swap" rel="stylesheet">

        <!-- Biblioteca CSS - Bootstrap -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="./lib/slick/slick.css" rel="stylesheet">
        <link href="./lib/slick/slick-theme.css" rel="stylesheet">

        <!-- CSS Principal do Projeto -->
        <link href="./css/style.css" rel="stylesheet">
    </head>

	<% 
    Usuario usuarioLogado = new Usuario();
    
    HttpSession sessao = request.getSession();
    usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        
  	//pega cliente a ser alterado
    Produto produtoAlterado = (Produto)sessao.getAttribute("produtoAlterado");
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
                            <a href="./JSP/indexAdm.jsp" class="nav-item nav-link">Home</a>
                        </div>
                        <div class="navbar-nav ml-auto">
                         <div class="nav-item dropdown">
                              <a href="" class="nav-link dropdown-toggle" data-toggle="dropdown">Minha Conta</a>
                             <div class="dropdown-menu">
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
        <!-- Fim da faixa de menu -  faixa rosa contendo home, produtos e minha conta-->
    
    
        <!-- Inicio da div contendo logo, barra de pesquisa e botão Minha Sacola -->
        <div class="bottom-bar">
            <div class="container-fluid">
                <div class="row align-items-center">
                    <div class="col-md-3">
                        <div class="logo">
                            <a href="./JSP/indexAdm.jsp">
                                <img src="./img/mir.svg" alt="Logo Mirror Fashion">
                            </a>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="search">
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="user">
						<h6>
							<div class="ml-autonavbar-collapse justify-content-between">Olá	${usuarioLogado.nome}</div>
						</h6>
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
                    <li class="breadcrumb-item"><a href="./JSP/indexAdm.jsp">Minha Conta</a></li>
                    <li class="breadcrumb-item active">Alterar Produto</li>
                </ul>
            </div>
        </div>
       <!-- Fim do Breadcrumb -->
        
        <!-- Inicio de Alterar Produto  -->
        <div class="registrar__novaconta">
            <div class="container-novaconta">
                <div class="col-lg-9">   
                    <div class="register-form">
                        <h4>Alterar Cadastro de Produto</h4><br>
                        <form action="http://localhost:8080/eCommerce/produto">
                            <div class="row">
                            <div class="col-md-5">
                                <label>Nome</label>
                                <input class="form-control" type="text" name="nome" value="<%=produtoAlterado.getNome()%>">
                            </div>
                            <div class="col-md-3">
                                <label>Categoria</label>
                                <select class="form-control" name="categoria" value="<%=produtoAlterado.getCategoria()%>">
                                    <option selected disabled><%=produtoAlterado.getCategoria()%></option>
                                    <option value="blusa comprida">Blusa Comprida</option>
                                    <option value="blusa curta">Blusa Curta</option>
                                    <option value="calca">Calça</option>
                                    <option value="vestido">Vestido</option>
                                    <option value="acessorios">Acessórios</option>
                                </select>
                            </div>
                            <div class="col-md-2">
                                <label>Cor Produto </label>
                                <select class="form-control" name="cores" value="<%=produtoAlterado.getCores()%>">
                                    <option selected disabled><%=produtoAlterado.getCores()%></option>
                                    <option value="Amarela">Amarela</option>
                                    <option value="Azul">Azul</option>
                                    <option value="Caramelo">Caramelo</option>
                                    <option value="Castanho">Castanho</option>
                                    <option value="Laranja">Laranja</option>
                                    <option value="Mostarda">Mostarda</option>
                                    <option value="Preta">Preta</option>
                                    <option value="Rainbow">Rainbow</option>
                                    <option value="Rosa">Rosa</option>
                                    <option value="Sortido">Sortido</option>
                                    <option value="Rose">Rose</option>
                                    <option value="Verde">Verde</option>
                                    <option value="Verde Militar">Verde Militar</option>
                                    <option value="Transparente">Transparente</option>
                                </select>
                            </div>
                            <div class="col-md-2">
                                <label>Tamanho </label>
                                <select class="form-control" name="tamanho" value="<%=produtoAlterado.getTamanho()%>">
                                    <option selected disabled><%=produtoAlterado.getTamanho()%></option>
                                    <option value="36">36</option>
                                    <option value="38">38</option>
                                    <option value="40">40</option>
                                    <option value="42">42</option>
                                    <option value="UN">UN</option>
                                </select>
                            </div>
                            <div class="col-md-2">
                                <label> Qtde</label>
                                <input class="form-control inputQtde" name="qtdeEstoque" type="number" value="<%=produtoAlterado.getQtdeEstoque()%>">
                           </div>
                            <div class="col-md-3">
                                <label>R$ Compra</label>
                                <input class="form-control" type="number" name="precoCompra" value="<%=produtoAlterado.getPrecoCompra()%>">
                            </div>
                            <div class="col-md-3">
                                <label>R$ Venda</label>
                                <input class="form-control" type="number" name="precoVenda" value="<%=produtoAlterado.getPrecoVenda()%>">
                            </div>
                             <div class="col-md-2">
                                <label>Grupo Preço </label>
                                <select name="grupoPrecificacao">
                                    <option selected disabled><%=produtoAlterado.getGrupoPrecificacao()%></option>
                                    <option value="Grupo1">Grupo1</option>
                                    <option value="Grupo2">Grupo2</option>
                                    <option value="Grupo3">Grupo3</option>
                                    <option value="Grupo4">Grupo4</option>
                                </select>
                            </div>
                            <div class="col-md-2">
                                <label>Status</label><br>
                                <input type="radio" name="status" value="ativo" checked> Ativo <br>
                                <input type="radio" name="status" value="inativo">  Inativo
                            </div>
                            <div class="col-md-5">
                                <label>Foto</label>
                                <input class="btnFile form-control" type="text" name="foto" value="<%=produtoAlterado.getFoto()%>">
                            </div>
                            <div class="col-md-4">
                                <label>Motivo Ativação/Inativação </label>
                                <textarea class="textareaProduto" name="motivoStatus" id="motivo" cols="65" rows="2" ><%=produtoAlterado.getMotivoStatus()%></textarea>
                            </div>
                            <div class="col-md-4">
                                <label>Descrição</label>
                                <textarea class="textareaProduto" name="descricao" id="descricao" cols="120" rows="2"><%=produtoAlterado.getDescricao()%></textarea>
                            </div>

                            <div class="col-md-9">
                            	<button class="btn" onclick="window.history.go(-1); return false;"> <i class="fa fa-times-circle"></i> Cancelar </button>
                                <button class="btn" name="operacao" value="ALTERAR"><i class="fa fa-save"></i>  Salvar</button>
                            </div>
                            <input type="hidden" name="alteraProduto" value="1"/>
				            <input type="hidden" name="id" value="<%=produtoAlterado.getId()%>"/>
                        </div>
                        </form> 
                    </div>
                </div>    
            </div>
        </div>
        <!-- Login End -->
        
        <!-- Footer Start -->
        <div class="footer">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-3 col-md-6">
                        <div class="footer-widget">
                            <h2>Contato</h2>
                            <div class="contact-info">
                                <p><i class="fa fa-map-marker"></i>Mogi das Cruzes - SP</p>
                                <p><i class="fa fa-envelope"></i>Lorena Oliveira </p>
                                <p><i class="fa fa-phone"></i>+55 11 91234-5678</p>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-lg-3 col-md-6">
                        
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
                                <li><a href="#">Sobre nÃ³s</a></li>
                                <li><a href="#">PolÃ­tica de Privacidade</a></li>
                                <li><a href="#">Termos & CondiçÃµes</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                
                <div class="row payment align-items-center">
                    <div class="col-md-6">
                        <div class="payment-method">
                            <h2>Forma de pagamento</h2>
                            <img src="./img/payment-method.png" alt="Payment Method" />
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="payment-security">
                            <h2>Compre com segurança</h2>
                            <img src="./img/godaddy.svg" alt="Payment Security" />
                            <img src="./img/norton.svg" alt="Payment Security" />
                            <img src="./img/ssl.svg" alt="Payment Security" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer End -->
        
        <!-- Footer Bottom Start -->
        <div class="footer-bottom">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 copyright">
                        <p>Copyright &copy; <a href="./JSP/indexAdm.jsp">Mirror Fashion</a> - 2022 - Todos os direitos reservados</p>
                    </div>

                    <!-- <div class="col-md-6 template-by">
                        <p>Template By <a href="https://htmlcodex.com">HTML Codex</a></p>
                    </div> -->
                </div>
            </div>
        </div>
        <!-- Footer Bottom End -->       
        
        <!-- Back to Top -->
        <a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>
        
        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="./lib/easing/easing.min.js"></script>
        <script src="./lib/slick/slick.min.js"></script>
        
        <!-- Template Javascript -->
        <script src="./js/main.js"></script>
    </body>
</html>