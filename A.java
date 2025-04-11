--Criando a tabela de autor
create table autor(
	id uuid not null primary key,
	nome varchar(100) not null,
	data_nascimento date not null,
	nascionalidade varchar(50) not null
);