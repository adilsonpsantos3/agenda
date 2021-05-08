/**
*Projeto 1: Agenda de contatos
*@adilson dos Santos
*/
-- Listar bancos disponiveis no Servidor
show databases;
-- Criar um novo banco de dados 
 create database dbadilson1; 
 -- Excluir um banco de dados
drop database teste;
-- selecionar o banco de dados
use dbadilson;
/* Tabelas */
/*int (tipos de dados: numero inteiros)*/
/* primary key (chave primaria)*/
/* varchar tipo de dados : String de carcteres*/
/* not null (campo com preenchimentos obrigatorios)*/
create table Contatos (
idcon int primary key auto_increment,
nome varchar (50) not null,
fone varchar (15) not null,
email varchar (50)
);
show tables;
-- Descrever estrura da tabela
describe Contatos;
-- Excluir uma tabela 
drop  table Contatos;
/* CRUD (Create Read Update Delete) */

/* CRUD Create*/
-- Inserir um novo contato
insert into Contatos(nome,fone,email)
values('adilson','222233333','adilson@gmail');


insert into Contatos(nome,fone,email)
values ('Bill Gates','9999888','bill@gmail');

insert into Contatos(nome,fone,email)
values ('Claudio','99997777','claudio@gmail');

insert into Contatos(nome,fone,email)
values ('Jose','99996666','jose@gmail');

insert into Contatos(nome,fone,email)
values ('Santos','99995555','santos@gmail');

insert into Contatos(nome,fone,email)
values ('Paulo','99994444','paulo@gmail');

/* Crud Read */
-- Listar todos os contatos da tabela
select * from Contatos;
-- Listar os contatos por ordem alfabetica
select * from Contatos order by nome;
-- Listar campos especifcos da tabela ex: apenas nome e fone
select nome,fone from Contatos order by nome;

-- Criar um apelido para os campos da tabela
select nome as Contatos, fone as Telefone,
email as Email from Contatos order by nome;

-- Selecionar um contato especifico
select * from Contatos where nome='Claudio';
-- Selecionar um id especifico
select * from Contatos where idcon='1';

/* CRUD Update */

-- Altera o numero de um nome e telefone especifico
update Contatos set nome='Claudio Dantas',fone='77779999',
email='claudiodantas@gmail' where idcon='2';

-- Altera o numero de um telefone (id) especifico
update Contatos set fone= '5555 2222' where idcon='1';

/* CRUD DELETE*/

delete from Contatos where idcon='5';

