Create Table Pessoa(
	nome 				varchar(100),
	nif 				char(9),
	numero_conducao 	char(9),--L-(numero 7 digitos)
	email 				varchar(400),--google says 320

	Primary Key(nif),
	CONSTRAINT Pessoa_nif CHECK (nif ~ '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	CONSTRAINT Pessoa_conducao CHECK (numero_conducao ~ 'L-[0-9][0-9][0-9][0-9][0-9][0-9][0-9]')
);

Create Table Subscritor(
	nif 		char(9),
	password	varchar(100),--autenticacao mudar isto para password+salt e guardar o hash
	
	Primary Key(nif),
	Foreign Key(nif) References Pessoa(nif)
);

Create Table Admin(
	nif 		char(9),
	username 	varchar(20),
	password 	varchar(100),--autenticacao mudar isto para password+salt e guardar o hash

	Primary Key(username),
	Foreign Key(nif) References Pessoa(nif)
);

Create Table Notificacao(
	emitida 	boolean,--0->n/1->s
	mensagem 	varchar(5000),
	id 			serial,
	recebida	boolean,--0->n/1->s
	tipo 		varchar(100),
	subscritor 	char(9),

	Primary Key(id),
	Foreign Key(subscritor) References Subscritor(nif)
);

Create Table Veiculo(
	matricula 	char(8),
	modelo 		varchar(50),
	categoria 	varchar(50),
	owner 		char(9),

	Primary Key(matricula),
	Foreign Key(owner) References Subscritor(nif),
	CONSTRAINT Veiculo_matricula CHECK (matricula ~ '([A-Z]|[0-9])([A-Z]|[0-9])-([A-Z]|[0-9])([A-Z]|[0-9])-([A-Z]|[0-9])([A-Z]|[0-9])')
);

Create Table Emprestimo(
	matricula 	char(8),
	usuario 	char(9),
	dataInicio 	timestamp,
	dataFim 	timestamp,
	estado 		varchar(20),--aguardar/emprestado/devolvido

	Primary Key(matricula, dataInicio),
	Foreign key(matricula) References Veiculo(matricula),
	Foreign Key(usuario) References Subscritor(nif)
);

Create Table Evento_Transito(
	numeroAuto 				char(9),
	veiculo 				char(8),
	estadoPagamento 		varchar(10),
	data 					timestamp,
	tipo 					varchar(15),
	classificacaoInfracao 	varchar(20),
	descricao 				varchar(500),
	valor 					decimal(6,2),
	localizacao 			varchar(100),
	entidadeAutuante 		varchar(50),
	dataLimiteDefesa 		date,

	Primary Key(numeroAuto),
	Foreign Key(veiculo) References Veiculo(matricula)
);

Create Table Pedido_Defesa(
	id 					serial,--id interno, pedido n volta para o direct
	moradaSede 			varchar(100),
	justificacao		varchar(1500),
	numero_conducao 	char(9),--L-(numero 7 digitos)
	numeroAuto 			char(9) unique,
	requeridor 			char(9),

	Primary Key(id),
	Foreign Key(numeroAuto) References Evento_Transito(numeroAuto),
	Foreign Key(requeridor) References Subscritor(nif),
	CONSTRAINT Pedido_Defesa_conducao CHECK (numero_conducao ~ 'L-[0-9][0-9][0-9][0-9][0-9][0-9][0-9]')
);