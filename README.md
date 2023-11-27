
Trabalho Final de Banco de Dados:
Projeto de Banco de Dados Imuniza
Autores: Benjamin Silva Sergio, Emanuel Silva Sergio e Paulo Roberto


Sumário	1
1.0 Introdução	2
2.0 O projeto	2
2.1 Modelo de dados	2
2.2 Dicionário de Dados	3
3.0 Implementação	4
3.1 Script de inicialização	5
3.2 Páginas	7
4.0 Conclusão	10



1.0 Introdução

O trabalho final da matéria de banco de dados consiste na confecção de um projeto e implementação de um banco de dados para uma comunidade às margens do rio Madeira na Amazônia Brasileira, a comunidade é composta por 10 famílias e devido a o seu isolamento é necessário um controle preciso sobre as vacinas que chegam, para tal o chefe da comunidade solicitou uma aplicação de banco de dados para a gestão de vacinas. O banco de dados deve conter informações pessoais e de identificação, tais como número do sus do cidadão, cpf,  nome do cidadão, data de nascimento, nome da mãe, sexo, endereço completo, estado civil, escolaridade, raça/cor/etnia e outras caso nescessario, o sistema tambem deve levar em conta e administrar as dosagens tomadas de cada vacina.


2.0 O projeto
	O projeto para o banco de dados é feito pensando nas dificuldades para a utilização facilitada do sistema, para tal é necessário para ter uma gestão fácil a administração das doses e lotes de cada vacina, além das fabricantes, pensando em possíveis erros e correções em caso de erro em vacinas, o projeto é construído em um docker, e utiliza da linguagem de programação Java para a implementação do código e o  SGBD MySQL para a criação e gestão do banco de dados.
	A escolha de utilizar o Docker se deve ao fato de ser um pedido da professora, a utilização dele visa a facilitar a utilização e serve para evitar possíveis erros oriundos de versões diferentes em cada máquina, além de exumar a máquina que o rodar como um fator a dar erros, tendo em visto que qualquer máquina irá apresentar uma execução parecida, senão igual. A escolha de usar Java para a implementação da interface web se dá pela familiaridade que os componentes do grupo estão com a linguagem, desta forma economizando tempo de estudo, além do java para fazer a parte web do sistema foram utilizados html e css.


3.0 Implementação
	A implementação começou com um banco de dados dados no docker e utilizando a SGBD MySQL e um código em java para a criação de uma interface web para garantir a facilidade no uso do sistema o script em MySQL para a criação do banco de dados é a seguinte:

3.1 Script de inicialização

create database sistema;
use sistema;
create table Morador(
	NOME varchar(255) NOT NULL,
	EMAIL varchar(255) NOT NULL,
	SENHA varchar(60) NOT NULL,
	CPF char(11) NOT NULL,
	NUMERO_SUS char(15) NOT NULL UNIQUE,
	NOME_SOCIAL varchar(255),
	DATA_NASCIMENTO DATE NOT NULL,
	SEXO char(1) check (sexo in ('M', 'F', 'O')) NOT NULL,
	NOME_MAE varchar(255) NOT NULL, 
	TELEFONE char(11) NOT NULL,
	CEP char(8) NOT NULL, 
	LOGRADOURO varchar(60) NOT NULL, 
	NUMERO int NOT NULL,
	BAIRRO varchar(30) NOT NULL, 
	CIDADE varchar(30) NOT NULL, 
	UF char(2) NOT NULL,
	COMPLEMENTO varchar(30) NOT NULL,
	ESTADO_CIVIL varchar(20) NOT NULL,
	ESCOLARIDADE varchar(60) NOT NULL,
	ETNIA varchar(20) NOT NULL,
	TEM_PLANO_SAUDE bool NOT NULL default false,
    ADMIN bool NOT NULL default false,
	PRIMARY KEY (CPF)
);

INSERT INTO Morador VALUES (
	"Paulo",
    "paulo.rqsf@gmail.com",
    "admin",
	'12345678901',
    "123456789012345",
    null,
    STR_TO_DATE( "30/05/2001", "%d/%m/%Y" ),
    'M',
    "Lurdes",
    "28999999999",
    "29931640",
    "Rua fulano",
    "0123",
    "Bairro",
    "Conchas",
    "SP",
    "apto 2",
    "Solteiro",
    "Ensino medio completo",
    "Pardo",
    false,
    true
);

select * from Morador;

CREATE TABLE Lotes (
	CODIGO_LOTE BIGINT PRIMARY KEY,
    CNPJ_FABRICANTE CHAR(14) NOT NULL,
    NOME_FABRICANTE VARCHAR(60) NOT NULL,
    QUANTIDADE_VACINAS INT,
    DATA_FABRICACAO DATE NOT NULL
);

CREATE TABLE Vacinas (
    ID_VACINA BIGINT NOT NULL,
    CODIGO_LOTE BIGINT NOT NULL,
    NOME VARCHAR(255) NOT NULL,
    DESCRICAO VARCHAR(255) NOT NULL,
    DATA_VALIDADE DATE NOT NULL,
    QUANTIDADE_DOSES INT,
    INTERVALO_DOSES INT,
    FOREIGN KEY (CODIGO_LOTE) REFERENCES Lotes(CODIGO_LOTE),
    PRIMARY KEY (ID_VACINA, CODIGO_LOTE)
);

CREATE TABLE Registro_Vacinacao (
    ID_REGISTRO BIGINT NOT NULL,
    CPF char(11) NOT NULL,
    ID_VACINA BIGINT NOT NULL,
    DATA_ADMINISTRACAO DATE NOT NULL,
    DOSES_TOMADAS INT default 0,
    FOREIGN KEY (CPF) REFERENCES Morador(CPF),
    FOREIGN KEY (ID_VACINA) REFERENCES Vacinas(ID_VACINA),
    PRIMARY KEY (ID_REGISTRO, CPF, ID_VACINA)
    
);

4.0 Conclusão
	A atividade realizada pelo grupo apresentou dificuldades inesperadas mas também foi uma oportunidade de aprendizado, apesar da implementação do banco de dados em si ser bastante simples, ainda mais com a implementação sendo feita com a ajuda de um SGBD como o MySQL, o projeto em si e a decisão de como seria cada relação e tabela adicionada se mostrou um desafio, tanto lógico como funcional. Decisões de tipos e tabelas levaram a discussões construtivas entre o grupo e com os conselhos da professora fomos capazes de chegarmos a uma solução satisfatória.
	A interface web foi implementada em java, na implementação foi feita uma série de restrições como o fato de ser necessário escolher um lote já existente ao registrar uma vacina, desta forma garantimos que ao registrar duas vacinas de mesmo lote eles não criem lotes duplicados no sistema, além desta existem outras. 
	O sistema criado provavelmente seria satisfatório ao público alvo desejado, tendo em consideração o escopo da operação e a facilidade de uso do sistema e a interface que garante que mesmo pessoas com pouca ou nenhuma experiência possam usá-lo corretamente. Desta forma a atividade em grupo contribui tanto para o aprendizado quanto para projetos futuros, pois cria uma base na qual podemos confortavelmente criar novos sistemas, mesmo utilizando outras linguagens ou SGBDs.

