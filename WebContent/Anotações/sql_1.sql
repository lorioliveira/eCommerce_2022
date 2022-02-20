
insert into cliente (nome, cpf, dt_nasc, genero, telefone, email, senha, status, tipo, dt_cadastro)
values ('Admin','79628190571','2021/08/01', 'feminino', '11945758990', 'admin@admin.com', '12345678', 'ativo', 'admin','2021/11/05');


insert into produto (nome, descricao, categoria, cores, tamanho, precoCompra, precoVenda, qtdeEstoque, foto, dt_cadastro, status, grupoPrecificacao, descricaoStatusProduto)
values ('Blusa Manga Comprida', 'Excelente material italiano com estampa desenhada pelos artesãos da comunidade de Krotor nas ilhas gregas. Compre já e receba hoje mesmo pela nossa entrega a jato.', 'blusa','Amarela', '36', '20.00', '49.99', '30', './img/produtos/blusaamarela.png', '2021-09-07', 'ativo', 'grupo2','com estoque');

insert into produto (nome, descricao, categoria, cores, tamanho, precoCompra, precoVenda, qtdeEstoque, foto, dt_cadastro, status, grupoPrecificacao,descricaoStatusProduto)
values ('Blusa Manga Curta','Excelente material nacional em um tecido maravilhoso. Comprimento midi, ajuste slim fit, perfeito para o Verão 2021. Compre já e receba hoje mesmo pela nossa entrega a jato.', 'blusa','Verde', '38','10.00', '59.99', '30', './img/produtos/blusaverde.png', '2021-09-07', 'ativo', 'grupo2','com estoque');

insert into produto (nome, descricao, categoria, cores, tamanho, precoCompra, precoVenda, qtdeEstoque, foto, dt_cadastro, status, grupoPrecificacao,descricaoStatusProduto)
values ('Calca de Elastico','Excelente material nacional em uma malha trabalhada no conforto. Comprimento capri, ajuste slim fit, perfeito para o trabalho ou lazer. Compre já e receba hoje mesmo pela nossa entrega a jato.', 'calca', 'Preta', '40', '30.00', '42.90', '30', './img/produtos/calcapreta.png', '2021-09-07', 'ativo', 'grupo2','com estoque');

insert into produto (nome, descricao, categoria, cores, tamanho, precoCompra, precoVenda, qtdeEstoque, foto, dt_cadastro, status, grupoPrecificacao,descricaoStatusProduto)
values ('Vestido Curto','Excelente material nacional em um tecido trabalhado no lápis. Comprimento midi, ajuste slim fit, perfeito para seu estilo de vida. Compre já e receba hoje mesmo pela nossa entrega a jato.', 'vestido', 'Rosa','42', '20.00', '39.90', '30', './img/produtos/vestidorosa.png', '2021-09-07', 'ativo', 'grupo2','com estoque');


insert into pedido(precoTotalProduto, precoFrete, statusPedido, id_cliente, id_endereco, cartao1, valorCartao1, cartao2, valorCartao2, desconto, dt_pedido)
values ('0', '0', 'EM PROCESSAMENTO', '10', '10', '8', '0', '8', '0', '0.0', '2020-01-02');


insert into item_pedido(id_produto, nome, qtdeProduto, precoVenda, id_pedido, dt_pedido)
values ('1', 'Blusa Manga Comprida', '5', '59.99', '1', '2020/02/02');
