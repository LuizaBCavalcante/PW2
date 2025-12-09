-- insert into produto (id, descricao, valor) values (1, 'Arroz',43.00);
-- -- insert into produto (id, descricao, valor) values (2,'Leite',7.00);
-- -- insert into produto (id, descricao, valor) values (3,'Feijão',24.00);
-- -- insert into produto (id, descricao, valor) values (4,'Bolo',25.00);
-- --
-- -- insert into pessoa (id, email, telefone) values (101, 'luiza@mail', '000000-0000');
-- -- insert into pessoa_fisica (id_pessoa, nome, cpf) values (101, 'Luiza', '000.000.000-00');
-- --
-- -- insert into pessoa (id, email, telefone) values (102, 'compremais@mail', '11111111-1111');
-- -- insert into pessoa_juridica (id_pessoa, razao_social, cnpj) values (102, 'Mercado Compre Mais', '11.111.111/0001-11');
-- --
-- -- insert into venda (id, data, id_pessoa) values (1, '2025-10-10 14:30:55', 101);
-- -- insert into venda (id, data, id_pessoa) values (2, '2025-10-18 18:11:10', 102);
-- --
-- --
-- -- insert into item_venda (id, quantidade, id_produto, id_venda) values (100, 1, 2, 1);
-- -- insert into item_venda (id, quantidade, id_produto, id_venda) values (200, 3, 4, 2);
-- -- insert into item_venda (id, quantidade, id_produto, id_venda) values (300, 2, 2, 2);
-- --
-- --
-- -- ALTER TABLE produto ALTER COLUMN id RESTART WITH 5;
-- -- ALTER TABLE pessoa ALTER COLUMN id RESTART WITH 103;
-- -- ALTER TABLE venda ALTER COLUMN id RESTART WITH 3;
-- -- ALTER TABLE item_venda ALTER COLUMN id RESTART WITH 301;


insert into produto (id, descricao, valor, imagem) values (1, 'Arroz', 43.00, '/img/arroz.jpg');
insert into produto (id, descricao, valor, imagem) values (2, 'Leite', 7.00,  '/img/leite.jpg');
insert into produto (id, descricao, valor, imagem) values (3, 'Feijão', 24.00, '/img/feijao.jpg');
insert into produto (id, descricao, valor, imagem) values (4, 'Bolo', 25.00,   '/img/bolo.jpg');


insert into pessoa (id, email, telefone) values (101, 'luiza@mail', '000000-0000');
insert into pessoa_fisica (id_pessoa, nome, cpf) values (101, 'Luiza', '000.000.000-00');

insert into pessoa (id, email, telefone) values (102, 'compremais@mail', '11111111-1111');
insert into pessoa_juridica (id_pessoa, razao_social, cnpj) values (102, 'Mercado Compre Mais', '11.111.111/0001-11');


insert into venda (id, data, id_pessoa) values (1, '2025-10-10 14:30:55', 101);
insert into venda (id, data, id_pessoa) values (2, '2025-10-18 18:11:10', 102);


insert into item_venda (id, quantidade, valor, id_produto, id_venda) values (100, 1.0, 7.00, 2, 1);
insert into item_venda (id, quantidade, valor, id_produto, id_venda) values (200, 3.0, 25.00, 4, 2);
insert into item_venda (id, quantidade, valor, id_produto, id_venda) values (300, 2.0, 7.00, 2, 2);

ALTER TABLE produto ALTER COLUMN id RESTART WITH 5;
ALTER TABLE pessoa ALTER COLUMN id RESTART WITH 103;
ALTER TABLE venda ALTER COLUMN id RESTART WITH 3;
ALTER TABLE item_venda ALTER COLUMN id RESTART WITH 301;

