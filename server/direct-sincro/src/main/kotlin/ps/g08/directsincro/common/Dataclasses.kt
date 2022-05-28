package ps.g08.directsincro.common

//api/

//Pessoa/nif/
data class Pessoa(
    val nome: String,
    val nif: Int,
    val numero_conducao: String,
    val email: String,
    val subscritor: Subscritor?,
    val admin: Admin?
)

//Pessoa/nif/subscritor
data class Subscritor(
    val nif: Int,
    val password: String,
    val notificacoes: List<Notificacao>,
    val veiculos: List<Veiculo>,
    val veiculosAlugados: List<Veiculo>,
    val emprestimos: List<EmprestimoUsuario>
)

//pessoa/nif/admin/username??
data class Admin(
    val username: String,
    val password: String
    //nif
)

//subscritor/nif/notificacao/id
data class Notificacao(
    val emitida: Boolean,
    val mensagem: String,
    val id: Int,
    val recebida: Boolean,
    val tipo: String
)

//subscritor/nif/veiculo/matricula
//subscritor/nif/emprestado/matricula -> com campo para o original owner?
data class Veiculo(
    val matricula: String,
    val modelo: String,
    val categoria: String,
    val eventos: List<Evento_Transito>,
    val emprestimos: List<EmprestimoMatricula>
)

//subscritor/nif/veiculo/matricula/emprestimo -> emprestimos concebidos
//subscritor/nif/emprestimo -> emprestimos recebidos
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

//subscritor/nif/veiculo/matricula/evento/numeroAuto ->eventos de transito do meu veiculo
//subscritor/nif/alugado/matricula/evento/numeroAuto ->eventos de transito do veiculo alugado
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

//subscritor/nif/pedido_defesa/id
//acho que aqui não faz sentido a pesquisa por numero auto, mas n sei
data class Pedido_Defesa(
    val id: Int,
    val moradaSede: String,
    val justificacao: String,
    val numero_conducao: String
    //numeroAuto,
    //requeridor,
)