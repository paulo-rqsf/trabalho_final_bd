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
	'01304401162',
    "123456789012345",
    null,
    STR_TO_DATE( "30/05/2001", "%d/%m/%Y" ),
    'M',
    "Tatiana",
    "28988143298",
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
