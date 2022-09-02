Create Table Cidadao(
	nome 				varchar(100),
	nif 				char(9),
	tituloConducao  	char(9),--L-(numero 7 digitos)
	email 				varchar(400),--google says 320
	password			varchar(100),--autenticacao mudar isto para password+salt e guardar o hash

	Primary Key(nif),
	CONSTRAINT Cidadao_nif CHECK (nif ~ '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	CONSTRAINT Cidadao_conducao CHECK (tituloConducao ~ 'L-[0-9][0-9][0-9][0-9][0-9][0-9][0-9]')
);

Create Table Subscritor(
	nif 		char(9),
	
	Primary Key(nif),
	Foreign Key(nif) References Cidadao(nif)
);

Create Table DataSubscricao(
	inicio			timestamp,
	cancelamento	timestamp,

	nif 			char(9),

	Primary Key(nif, inicio),
	Foreign Key(nif) References Subscritor(nif)
);

Create Table Veiculo(
	matricula 	char(8),
	modelo 		varchar(50),
	categoria 	varchar(50),
	owner 		char(9),

	Primary Key(matricula),
	Foreign Key(owner) References Subscritor(nif),
	CONSTRAINT Veiculo_matricula CHECK (matricula in ('[A-Z][A-Z]-[0-9][0-9]-[0-9][0-9]', '[A-Z][A-Z]-[0-9][0-9]-[A-Z][A-Z]', '[0-9][0-9]-[0-9][0-9]-[A-Z][A-Z]', '[0-9][0-9]-[A-Z][A-Z]-[0-9][0-9]'))
);

Create Table Delegacao(
	dataInicio 	timestamp,
	dataFim 	timestamp,

	matricula 	char(8),
	usuario 	char(9),

	Primary Key(matricula, dataInicio),
	Foreign key(matricula) References Veiculo(matricula),
	Foreign Key(usuario) References Subscritor(nif)
);

Create Table Contraordenacao(
	numeroAuto 				char(9),
	estadoPagamento 		varchar(10),
	data 					timestamp,
	categoriaVeiculo		varchar(15),
	classificacaoInfracao 	varchar(20),
	descricao 				varchar(500),
	valorCoima				decimal(6,2),
	local					varchar(100),
	entidadeAutuante 		varchar(50),
	dataLimiteDefesa 		date,
	normaInfringida			varchar(20),

	veiculo 				char(8),

	Primary Key(numeroAuto),
	Foreign Key(veiculo) References Veiculo(matricula)
	CONSTRAINT Estado_Pagamento CHECK (estadoPagamento in ('Pago','Por Pagar','Não Pago'))
);

Create Table Notificacao(
	emitida 		timestamp,
	mensagem 		varchar(5000),
	visualizada		boolean,--0->n/1->s
	tipo 			varchar(100),

	subscritor 		char(9),
	contraordenacao	char(9),

	Primary Key(emitida, subscritor),
	Foreign Key(subscritor) References Subscritor(nif),
	Foreign Key(contraordenacao) References Contraordenacao(numeroAuto)
);