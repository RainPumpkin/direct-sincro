Create Table Pessoa(
	nome varchar(100),
	nif char(9),
	titulo de conducao,--perguntar ao Daniel
	email varchar(50),

	Primary Key(nif)
);

Create Table Subscritor(
	nif char(9),
	password varchar(100),

	Primary Key(nif),
	Foreign Key(nif) References Pessoa(nif)
);

Create Table Admin(
	nif char(9),
	username varchar(20),
	password varchar(100),

	Primary Key(username),
	Foreign Key(nif) References Pessoa(nif)
);

Create Table Notificacao(
	emitida,
	mensagem,
	id,
	recebida,
	tipo,

	Primary Key(id)
);

Create Table Veiculo(
	matricula char(8),
	modelo varchar(50),
	categoria varchar(50),
	owner char(9),

	Primary Key(matricula),
	Foreign Key(owner) References Subscritor(nif)
);

Create Table Emprestimo(
	matricula char(8),
	usuario varchar(100),
	dataInicio timestamp,
	dataFim timestamp,

	Primary Key(matricula, dataInicio),
	Foreign key(matricula) References Veiculo(matricula)
	Foreign Key(usuario) References Pessoa(nif)
);

Create Table Evento_Transito(
	numeroAuto char(9),
	veiculo char(8),
	estadoPagamento varchar(10),
	data timestamp,
	tipo varchar(10),
	classificacaoInfracao varchar(20),
	descricao varchar(500),
	valor decimal(6,2),
	localizacao varchar(100),
	entidadeAutuante varchar(50),
	dataLimiteDefesa timestamp,

	Primary Key(numeroAuto),
	Foreign Key(veiculo) References Veiculo(matricula)
);

Create Table Pedido_Defesa(
	id,--serial, perguntar ao Daniel
	moradaSede varchar(100),
	justificacao varchar(500),
	numeroAuto char(9) unique,
	requeridor char(9),

	Primary Key(id),
	Foreign Key(numeroAuto) References Evento_Transito(numeroAuto),
	Foreign Key(requeridor) References Subscritor(nif)
)