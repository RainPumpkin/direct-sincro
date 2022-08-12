package ps.g08.directsincro.common

data class Cidadao(
    val nome: String,
    val nif: String,
    val numero_conducao: String,
    val email: String,
    val password: String,
    val subscritor: Subscritor?
)

data class Subscritor(
    val nif: String,
    val notificacoes: List<Notificacao>,
    val veiculos: List<Veiculo>,
    val veiculosAlugados: List<Veiculo>,
    val emprestimos: List<EmprestimoUsuario>
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
    val eventos: List<Contraordenacao>,
    val emprestimos: List<EmprestimoMatricula>
)

data class EmprestimoMatricula(
    val dataInicio: Long,
    val dataFim: Long,
    val estado: String,
    val usuario: String?
)

data class EmprestimoUsuario(
    val dataInicio: Long,
    val dataFim: Long,
    val estado: String,
    val matricula: String
)

data class SubscritorCidadao(
    val nome: String,
    val nif: String,
    val numero_conducao: String,
    val email: String,
    val password: String
)

data class Contraordenacao(
    val numeroAuto: String,
    val estadoPagamento: String,
    val data: Long,
    val catagoriaVeiculo: String,
    val classificacaoInfracao: String,
    val descricao: String,
    val valorCoima: Double,
    val local: String,
    val entidadeAutuante: String,
    val dataLimiteDefesa: Long
)
