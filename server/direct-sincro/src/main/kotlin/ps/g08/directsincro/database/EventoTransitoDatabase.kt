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
        const val queryGet = "SELECT * FROM Evento_Transito WHERE numeroAuto = ? "//numeroauto
        const val queryGetAll = "SELECT * FROM Evento_Transito WHERE veiculo = ?"//getall for matricula
        const val queryGetAllTime = "SELECT * FROM Evento_Transito WHERE veiculo = ? AND data BETWEEN ? AND ?"//mesmo de cima mas entre duas datas
        const val queryCreate = "INSERT INTO Evento_Transito(numeroAuto, veiculo, estadoPagamento, data, " +
                "tipo, classificacaoInfracao, descricao, valor, localizacao, entidadeAutuante, dataLimiteDefesa) VALUES (?,?,?,?,?,?,?,?,?,?,?)"
        const val queryDelete = "Delete FROM Evento_Transito WHERE numeroAuto = ? AND data = ? AND veiculo = ?"//defesa aceite I guess?
    }
}