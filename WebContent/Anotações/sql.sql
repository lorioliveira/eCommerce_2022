create database ecommerce;
use ecommerce;

select * from cliente;
select * from endereco;
select * from cartaoCredito;
select * from produto;
select * from carrinho;

select * from produto;
select * from pedido;
select * from item_pedido;
select * from cupom;
select * from cuponsNoCarrinho;
select * from estoque;


/*update carrinho set status = "inativo" where id = 4;*/
/*select * from pedido where id = 1;*/



create table cliente (
	id	BIGINT	NOT	NULL	AUTO_INCREMENT,
	nome		VARCHAR(50),
	cpf		    VARCHAR(14),
	dt_nasc		DATE,
	genero		VARCHAR(10),
	telefone	VARCHAR(12),
	email		VARCHAR(50),
	senha		VARCHAR(20),
    status 		VARCHAR(10),
    tipo		VARCHAR(10),
    dt_cadastro  DATE,
	primary	key	(id)
);
create table endereco (
	id	BIGINT	NOT	NULL	AUTO_INCREMENT,
	cep		      VARCHAR(10),
	logradouro    VARCHAR(50),
	numero		  VARCHAR(10),
	bairro	      VARCHAR(30),
    cidade		  VARCHAR(50),
    estado        VARCHAR(10),
	pais		  VARCHAR(20),
	tipoResidencia	  VARCHAR(20),
	observacoes	  VARCHAR(20),
    tipoEndereco  VARCHAR(20),
    id_cliente BIGINT,
    dt_cadastro DATE,
	primary	key	(id),
	CONSTRAINT fk_EnderecoCliente FOREIGN KEY (id_cliente) REFERENCES cliente (id)
);
create table cartaoCredito (
	id	BIGINT	NOT	NULL	AUTO_INCREMENT,
	nCartao	      VARCHAR(20),
	bandeira	  VARCHAR(15),
	nome		  VARCHAR(30),
	validade      VARCHAR(10),
    cvv			  VARCHAR(5),
    preferencial  VARCHAR(10),
	id_cliente BIGINT,
    dt_cadastro	DATE,
	primary	key	(id),
	CONSTRAINT fk_CartaoCliente FOREIGN KEY (id_cliente) REFERENCES cliente (id)
);
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
    foto              VARCHAR(50),
    dt_cadastro        DATE,
    status            VARCHAR(10),
    grupoPrecificacao VARCHAR(30),
    descricaoStatusProduto	VARCHAR(40),
    primary key (id)
);
create table carrinho (
	id	BIGINT	NOT	NULL	AUTO_INCREMENT,
	id_cliente 		BIGINT,
    id_produto 		BIGINT,
    qtdeProduto  	VARCHAR(10),
    status			VARCHAR(10),
	primary	key	(id),
	CONSTRAINT fk_CarrinhoCliente FOREIGN KEY (id_cliente) REFERENCES cliente (id),
    CONSTRAINT fk_CarrinhoProduto FOREIGN KEY (id_produto) REFERENCES produto (id)
);
create table pedido (
	id 	 BIGINT  NOT NULL AUTO_INCREMENT,
	precoTotalProduto VARCHAR(10),
    precoFrete   VARCHAR(10),
    precoTotal   VARCHAR(10),
    statusPedido VARCHAR(30),
    id_cliente	 BIGINT,
	id_endereco	 BIGINT,
	cartao1		 BIGINT,
    valorCartao1 VARCHAR(10),
    cartao2	 	 BIGINT,
    valorCartao2 VARCHAR(10),
	desconto     VARCHAR(10),
    dt_pedido	 date,
	primary	key	(id),
		CONSTRAINT fk_ClientePedido FOREIGN KEY (id_cliente) REFERENCES cliente (id),
		CONSTRAINT fk_EnderecoPedido FOREIGN KEY (id_endereco) REFERENCES endereco (id),
		CONSTRAINT fk_CartaoPedido1 FOREIGN KEY (cartao1) REFERENCES cartaoCredito (id),
        CONSTRAINT fk_CartaoPedido2 FOREIGN KEY (cartao2) REFERENCES cartaoCredito (id)
);
create table item_pedido (
	id 	 BIGINT  NOT NULL AUTO_INCREMENT,
    id_produto   BIGINT,
    nome         VARCHAR(40),
	qtdeProduto  VARCHAR(10),
    precoVenda   VARCHAR(10),
    id_pedido 	 BIGINT,
    dt_pedido date,
    primary	key	(id),
		CONSTRAINT fk_ProdutoItem FOREIGN KEY (id_produto) REFERENCES produto (id),
		CONSTRAINT fk_PedidoItem FOREIGN KEY (id_pedido) REFERENCES pedido (id)
);
create table cupom (
	id	BIGINT	NOT	NULL  AUTO_INCREMENT,
	nome	     VARCHAR(50),
	tipo	     VARCHAR(20),
    valor	     VARCHAR(10),
    id_cliente   BIGINT,
    utilizado	VARCHAR(3),
    dt_cadastro  DATE,
	primary	key	(id),
	CONSTRAINT fk_ClienteCupom FOREIGN KEY (id_cliente) REFERENCES cliente (id)
);
create table cuponsNoCarrinho(
	id	BIGINT	NOT	NULL  AUTO_INCREMENT,
    id_cupom   BIGINT,
    id_cliente BIGINT,
    valor	   VARCHAR(10),
    status	   VARCHAR(10),
    dt_cadastro  DATE,
	primary	key	(id),
	CONSTRAINT fk_Cupom FOREIGN KEY (id_cupom) REFERENCES cupom (id)
);
create table estoque (
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


select id_produto, sum(qtdeProduto) as quantidade_vendido from item_pedido where dt_pedido BETWEEN ('2021-11-01') AND ('2021-11-29') group by id_produto order by sum(qtdeProduto) desc LIMIT 3;
select id_produto, sum(qtdeProduto) as quantidade_vendido  FROM item_pedido WHERE id_produto = 1 AND MONTH(dt_pedido) = '11' AND YEAR(dt_pedido) = '2021' group by id_produto;


-- INSERT para o Pedido
insert into pedido(total_itens, total_frete, total_pedido, status, id_cliente, id_endereco, forma_pagamento, total_cupons, trocado, dt_cadastro)
values ('0', '0', '0', 'EM PROCESSAMENTO', '46', '4', 'boleto', '0', 'nao', '2021/10/20');

-- INSERT para o Item do Pedido
insert into pedido(precoTotalProduto, precoFrete, statusPedido, id_cliente, id_endereco, cartao1, valorCartao1, cartao2, valorCartao2, desconto, dt_pedido)
values ('0', '0', 'EM PROCESSAMENTO', '9', '8', '5', '0', '5', '0', '0.0', '2021/11/02');


insert into item_pedido(id_produto, nome, qtdeProduto, precoVenda, id_pedido, dt_pedido)
values ('2', 'Blusa Manga Curta', '24', '20.00', '12', '2020/03/02');

select * from endereco;
select * from produto;
select * from pedido;
select * from item_pedido;



drop table cliente;
drop table endereco;
drop table cartaoCredito;
drop table produto;
drop table carrinho;
drop table pedido;
drop table item_pedido;
drop table cupom;
drop table cuponsNoCarrinho;
drop table estoque;