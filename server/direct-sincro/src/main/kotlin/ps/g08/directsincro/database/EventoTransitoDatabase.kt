package ps.g08.directsincro.database

import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.withHandleUnchecked
import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Evento_Transito
import ps.g08.directsincro.common.getTimestamp
import java.sql.Date
import java.sql.Timestamp

data class EventoTransitoDatabaseRow(
    val numeroAuto: String,
    val veiculo: String,
    val estadoPagamento: String,
    val data: Timestamp,
    val tipo: String,
    val classificacaoInfracao: String,
    val descricao: String,
    val valor: Double,
    val localizacao: String,
    val entidadeAutuante: String,
    val dataLimiteDefesa: Date
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

    fun get(numeroAuto: String) : EventoTransitoDatabaseRow{
        return source.withHandleUnchecked { handle ->
            handle
                .createQuery(queryGet)
                .bind(0, numeroAuto)
                .mapTo(EventoTransitoDatabaseRow::class.java)
                .one()
        }
    }

    fun getAll(veiculo: String): List<EventoTransitoDatabaseRow> {
        return source.withHandleUnchecked { handle -> handle
            .createQuery(queryGetAll)
            .bind(0, veiculo)
            .mapTo(EventoTransitoDatabaseRow::class.java)
            .list()
        }
    }

    fun getAllTime(veiculo: String, dataInicio: Long, dataFim: Long): List<EventoTransitoDatabaseRow> {
        return source.withHandleUnchecked { handle -> handle
            .createQuery(queryGetAllTime)
            .bind(0, veiculo)
            .bind(1, getTimestamp(dataInicio))
            .bind(2, getTimestamp(dataFim))
            .mapTo(EventoTransitoDatabaseRow::class.java)
            .list()
        }
    }

    fun create(evento: Evento_Transito, veiculo: String): String {
        source.withHandleUnchecked { handle ->
            handle
                .createUpdate(queryCreate)
                .bind(0, evento.numeroAuto)
                .bind(1, veiculo)
                .bind(2, evento.estadoPagamento)
                .bind(3, evento.data)
                .bind(4, evento.tipo)
                .bind(5, evento.classificacaoInfracao)
                .bind(6, evento.descricao)
                .bind(7, evento.valor)
                .bind(8, evento.localizao)
                .bind(9, evento.entidadeAutuante)
                .bind(10, evento.dataLimiteDefesa)
                .execute()
        }
        return evento.numeroAuto;
    }

    fun delete(numeroAuto: String, data: Long, veiculo: String) {
        source.withHandleUnchecked { handle -> handle
            .createUpdate(queryDelete)
            .bind(0, numeroAuto)
            .bind(1, getTimestamp(data))
            .bind(2, veiculo)
            .execute()
        }
    }
}