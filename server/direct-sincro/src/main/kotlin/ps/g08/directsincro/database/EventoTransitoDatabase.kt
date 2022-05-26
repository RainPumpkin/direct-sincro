package ps.g08.directsincro.database

import org.jdbi.v3.core.Jdbi
import org.springframework.stereotype.Component
import java.sql.Timestamp

data class EventoTransitoDatabaseRow(
    val numeroAuto: String,
    val estadoPagamento: String,
    val data: Timestamp,
    val tipo: String,
    val classificacaoInfracao: String,
    val descricao: String,
    val valor: Double,
    val localizao: String,
    val entidadeAutuante: String,
    val dataLimiteDefesa: Timestamp,
    val veiculo: String
)

@Component
class EventoTransitoDatabase(private val source : Jdbi) {
    companion object {
        const val queryGet = ""//numeroauto
        const val queryGetAll = ""//getall for matricula
        const val queryGetAllTime = ""//mesmo de cima mas entre duas datas
        const val queryCreate = ""
        const val queryDelete = ""//defesa aceite I guess?
    }
}