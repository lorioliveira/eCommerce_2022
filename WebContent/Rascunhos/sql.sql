create database ecommerce;
use ecommerce;

select id_produto, sum(quantidade) as quantidade_vendido from pedido_item where
 dt_cadastro BETWEEN ('2022-02-01') AND ('2022-05-03') group by id_produto order by sum(quantidade) desc LIMIT 3;

insert into cliente (nome, cpf, data_Nascimento, genero, telefone, email, senha, status, tipoCliente, data_Cadastro)
values ('Admin','79628190571','1996/05/29', 'feminino', '11945758990', 'admin@mf.com.br', '12345678', 'ativo', 'admin', '2022/02/20');

insert into cupom(id, nome, valor, tipo, utilizado, id_cliente, dt_cadastro)
values ('1', 'PROMOCIONAL10', '10.00', 'promocional', 'nao', null, '2022-04-11');

insert into cupom(id, nome, valor, tipo, utilizado, id_cliente, dt_cadastro)
values ('10', 'TROCA97', '15.00', 'promocional', 'nao', 16, '2022-06-21');

insert into cupom(id, nome, valor, tipo, utilizado, id_cliente, dt_cadastro)
values ('2', 'LORENA10', '10', 'troca', 'nao', 2, '2022/04/09');

insert into estoque(id_produto, tipo, quantidade_entrada_saida, valor_custo, fornecedor, dt_entrada, quantidade_final, dt_cadastro)
values ('1', 'entrada', '10', '5.99', 'Primeira entrada no Estoque', '2022/03/26', '10', '2022/03/26');

update pedido set status="EM PROCESSAMENTO" where id = 3;

select count(*) from pedido;

delete from cliente where id = 13;

select * from cliente;
select * from endereco;
select * from cartaoCredito;
select * from produto;
select * from pedido;
select * from pedido_item;
select * from cupom;
select * from estoque;

delete from cliente where id = 16;
delete from endereco where id_cliente = 16;
delete from cartaoCredito where id_cliente = 16;
delete from cupom where id_cliente = 15;


delete from pedido where id = 101;
delete from pedido_item where id = 258;


update cupom set tipo = "Troca" where id = 6;
update cupom set valor = 30.00 where id = 6;
update pedido set status = "EM PROCESSAMENTO" where id = 89;

update cliente set email = "teste20@hotmail.com" where id = 16;
/*
delete from pedido where id = 2;
	update carrinho set status = "inativo" where id = 4;
	select * from pedido where id = 1;
	delete from cliente where id =4;
	delete from cupom where id = 5;
	delete from pedido where id = 7;
    */
    

/*** CLIENTE ****/
create table cliente (
	id	BIGINT	NOT	NULL	AUTO_INCREMENT,
	nome		VARCHAR(50),
	cpf		    VARCHAR(14),
	data_Nascimento	DATE,
	genero		VARCHAR(10),
	telefone	VARCHAR(16),
	email		VARCHAR(50),
	senha		VARCHAR(20),
    status 		VARCHAR(10),
    tipoCliente	VARCHAR(10),
    data_Cadastro  DATE,
	primary	key	(id)
);

/*** ENDEREÇO ****/
create table endereco (
	id	BIGINT	NOT	NULL	AUTO_INCREMENT,
	cep		      VARCHAR(10),
	logradouro    VARCHAR(50),
	numero		  VARCHAR(10),
	bairro	      VARCHAR(30),
    cidade		  VARCHAR(50),
    estado        VARCHAR(20),
	pais		  VARCHAR(10),
	tipoResidencia	  VARCHAR(20),
	observacoes	  VARCHAR(30),
    tipoEndereco  VARCHAR(20),
    id_cliente BIGINT,
    data_Cadastro DATE,
	primary	key	(id),
	CONSTRAINT fk_EnderecoCliente FOREIGN KEY (id_cliente) REFERENCES cliente (id)
);

/***** CARTÃO DE CRÉDITO *****/
create table cartaoCredito (
	id	BIGINT	NOT	NULL	AUTO_INCREMENT,
	numCartao	      VARCHAR(20),
	bandeira	  VARCHAR(15),
	nome		  VARCHAR(30),
	validade      VARCHAR(10),
    cvv			  VARCHAR(5),
    preferencial  VARCHAR(5),
	id_cliente BIGINT,
    data_Cadastro	DATE,
	primary	key	(id),
	CONSTRAINT fk_CartaoCliente FOREIGN KEY (id_cliente) REFERENCES cliente (id)
);

/**** PEDIDO ***/
create table pedido (
	id	BIGINT	NOT	NULL	AUTO_INCREMENT,
    total_itens 	VARCHAR(10),
    total_frete		VARCHAR(10),
    total_pedido    VARCHAR(10),
    status		    VARCHAR(30),
    id_cliente	 	BIGINT,
    id_endereco 	BIGINT,
    forma_pagamento VARCHAR(10),
    id_cartao_1 	BIGINT,
    valor_cartao_1	VARCHAR(10),
    id_cartao_2 	BIGINT,
    valor_cartao_2	VARCHAR(10),
	total_cupons	VARCHAR(10),
	trocado		    VARCHAR(3),
    dt_cadastro 	DATE,
	primary	key	(id),
	CONSTRAINT fk_ClientePedido FOREIGN KEY (id_cliente) REFERENCES cliente (id),
	CONSTRAINT fk_EnderecoPedido FOREIGN KEY (id_endereco) REFERENCES endereco (id),
	CONSTRAINT fk_CartaoPedido_1 FOREIGN KEY (id_cartao_1) REFERENCES cartaoCredito (id),
	CONSTRAINT fk_CartaoPedido_2 FOREIGN KEY (id_cartao_2) REFERENCES cartaoCredito (id)
);

/**** ITENS DO PEDIDO ****/
create table pedido_item (
	id	BIGINT	NOT	NULL	AUTO_INCREMENT,
	id_produto	 	BIGINT,
    nome		    VARCHAR(30),
    valor_de_venda  VARCHAR(10),
    quantidade      VARCHAR(10),
    id_pedido 		BIGINT,
    trocado		    VARCHAR(3),
    dt_cadastro 	DATE,
	primary	key	(id),
	CONSTRAINT fk_ProdutoPedidoItem FOREIGN KEY (id_produto) REFERENCES produto (id),
	CONSTRAINT fk_PedidoPedidoItem FOREIGN KEY (id_pedido) REFERENCES pedido (id)
);

/*** ESTOQUE ***/
create table estoque (
	id	BIGINT	NOT	NULL	AUTO_INCREMENT,
	id_produto	  				  BIGINT,
	tipo				    	  VARCHAR(10),
	quantidade_entrada_saida	  VARCHAR(10),
	valor_custo		      		  VARCHAR(10),
	fornecedor		       		  VARCHAR(50),
	dt_entrada  	   			  DATE,
	quantidade_final			  VARCHAR(10),
    dt_cadastro 	   			  DATE,
	primary	key	(id),
	CONSTRAINT fk_EstoqueProduto FOREIGN KEY (id_produto) REFERENCES produto (id)
);

/**** PRODUTO ****/
create table produto (
    id  BIGINT  NOT NULL   AUTO_INCREMENT,
    nome              VARCHAR(40),
    descricao         VARCHAR(300),
    categoria         VARCHAR(20),
    cores			  VARCHAR(50),
    tamanho			  VARCHAR(20),
    precoCompra       VARCHAR(10),
    precoVenda        VARCHAR(10),
    qtdeEstoque       VARCHAR(10),
    foto              VARCHAR(100),
    dt_cadastro        DATE,
    status            VARCHAR(10),
    grupoPrecificacao VARCHAR(30),
    motivoStatus	VARCHAR(40),
    primary key (id)
);

/**** CUPOM ****/
create table cupom (
	id	BIGINT	NOT	NULL	AUTO_INCREMENT,
	nome		       VARCHAR(50),
	valor		       VARCHAR(10),
	tipo	      	   VARCHAR(20),
	utilizado	       VARCHAR(3),
	id_cliente         BIGINT,
    dt_cadastro 	   DATE,
	primary	key	(id),
	CONSTRAINT fk_CupomCliente FOREIGN KEY (id_cliente) REFERENCES cliente (id)
);


-- INSERT para o Pedido
insert into pedido(total_itens, total_frete, total_pedido, status, id_cliente, id_endereco, forma_pagamento, total_cupons, trocado, dt_cadastro)
values ('3', '0', '0', 'ENTREGA REALIZADA', '5', '4', 'boleto', '0', 'nao', '2022/05/05');

-- INSERT para o Item do Pedido
insert into pedido_item(id_produto, nome, valor_de_venda, quantidade, id_pedido, trocado, dt_cadastro)
values ('3', 'blusa xxx', '0', '7', '37', 'nao', '2021/10/20');


drop table cliente;
drop table endereco;
drop table cartaoCredito;
drop table produto;

drop table pedido;
drop table pedido_item;
drop table cupom;
drop table estoque;


select * from cuponsNoCarrinho;
/** TABELAS NÃO USADAS ***/
create table carrinhoANTIGO (
	id	BIGINT	NOT	NULL	AUTO_INCREMENT,
	id_cliente 		BIGINT,
    id_produto 		BIGINT,
    qtdeProduto  	VARCHAR(10),
    status			VARCHAR(10),
	primary	key	(id),
	CONSTRAINT fk_CarrinhoCliente FOREIGN KEY (id_cliente) REFERENCES cliente (id),
    CONSTRAINT fk_CarrinhoProduto FOREIGN KEY (id_produto) REFERENCES produto (id)
);
create table estoqueANTIGO (
	id	BIGINT	NOT	NULL  AUTO_INCREMENT,
    id_produto BIGINT,
    tipo		VARCHAR(10),
    quantidade	VARCHAR(10),
    valorCompra	VARCHAR(10),
    dt_entrada	DATE,
    quantidadeTotal VARCHAR(10),
    dt_cadastro	DATE,
    primary	key	(id),
    CONSTRAINT fk_EstoqueProduto FOREIGN KEY (id_produto) REFERENCES produto (id)
);

