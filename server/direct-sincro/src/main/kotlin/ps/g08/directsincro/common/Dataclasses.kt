package ps.g08.directsincro.common

data class Pessoa(
    val nome: String,
    val nif: Int,
    val numero_conducao: String,
    val email: String,
    val subscritor: Subscritor?,
    val admin: Admin?
)

data class Subscritor(
    val nif: Int,
    val password: String,
    val notificacoes: List<Notificacao>,
    val veiculos: List<Veiculo>,
    val veiculosAlugados: List<Veiculo>,
    val emprestimos: List<EmprestimoUsuario>
)

data class Admin(
    val username: String,
    val password: String
)

data class Notificacao(
    val emitida: Boolean,
    val mensagem: String,
    val id: Int,
    val recebida: Boolean,
    val tipo: String
)

data class Veiculo(
    val matricula: String,
    val modelo: String,
    val categoria: String,
    val eventos: List<Evento_Transito>,
    val emprestimos: List<EmprestimoMatricula>
)

data class EmprestimoMatricula(
    val dataInicio: Long,
    val dataFim: Long,
    val estado: String,
    val usuario: String
)

data class EmprestimoUsuario(
    val dataInicio: Long,
    val dataFim: Long,
    val estado: String,
    val matricula: String
)

data class Evento_Transito(
    val numeroAuto: String,
    val estadoPagamento: String,
    val data: Long,
    val tipo: String,
    val classificacaoInfracao: String,
    val descricao: String,
    val valor: Double,
    val localizao: String,
    val entidadeAutuante: String,
    val dataLimiteDefesa: Long
)

data class Pedido_Defesa(
    val id: Int,
    val moradaSede: String,
    val justificacao: String,
    val numero_conducao: String
    //numeroAuto,
    //requeridor,
)