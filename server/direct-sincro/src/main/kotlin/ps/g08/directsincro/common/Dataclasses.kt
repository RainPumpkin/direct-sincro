package ps.g08.directsincro.common

data class Cidadao(
    val nome: String,
    val nif: String,
    val tituloConducao: String,
    val email: String,
    val password: String,
    val subscritor: Boolean
)

data class Subscritor(
    val nif: String,
    val notificacoes: List<Notificacao>,
    val veiculos: List<Veiculo>,
    val veiculosAlugados: List<Veiculo>,
    val delegacoes: List<DelegacaoSubscritor>
)

data class PushSubscription(
    val nif: String,
    val endpoint: String,
    val publicKey: String,
    val auth: String
)

data class Notificacao(
    val emitida: Long,
    val mensagem: String,
    val visualizada: Boolean,
    val tipo: String
)

data class Veiculo(
    val matricula: String,
    val modelo: String,
    val categoria: String,
    val contraordenacoes: List<Contraordenacao>,
    val delegacoes: List<DelegacaoVeiculo>
)

data class DelegacaoVeiculo(
    val dataInicio: Long,
    val dataFim: Long?,
    val usuario: String?
)

data class DelegacaoSubscritor(
    val dataInicio: Long,
    val dataFim: Long?,
    val matricula: String
)

data class SubscritorCidadao(
    val nome: String,
    val nif: String,
    val tituloConducao: String,
    val email: String,
    val password: String
)

data class Contraordenacao(
    val numeroAuto: String,
    val estadoPagamento: String,
    val data: Long,
    val categoriaVeiculo: String,
    val classificacaoInfracao: String,
    val descricao: String,
    val valorCoima: Double,
    val local: String,
    val entidadeAutuante: String,
    val dataLimiteDefesa: Long,
    val normaInfringida: String,
    val visualizada: Boolean
)
