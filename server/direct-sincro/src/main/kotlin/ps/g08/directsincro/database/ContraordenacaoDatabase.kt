package ps.g08.directsincro.database

import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.withHandleUnchecked
import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Contraordenacao
import ps.g08.directsincro.common.getTimestamp
import java.sql.Timestamp

data class ContraordenacaoDatabaseRow(
    val numeroAuto: String,
    val veiculo: String,
    val estadoPagamento: String,
    val data: Timestamp,
    val categoriaVeiculo: String,
    val classificacaoInfracao: String,
    val descricao: String,
    val valorCoima: Double,
    val local: String,
    val entidadeAutuante: String,
    val dataLimiteDefesa: Timestamp,
    val normaInfringida: String,
    val visualizada: Boolean
)

@Component
class ContraordenacaoDatabase(private val source : Jdbi) {
    companion object {
        const val queryGet = "SELECT * FROM Contraordenacao WHERE numeroAuto = ? "//numeroauto
        const val queryGetAll = "SELECT * FROM Contraordenacao WHERE veiculo = ?"//getall for matricula
        const val queryGetAllTime = "SELECT * FROM Contraordenacao WHERE veiculo = ? AND data BETWEEN ? AND ?"//mesmo de cima mas entre duas datas
        const val queryCreate = "INSERT INTO Contraordenacao(numeroAuto, veiculo, estadoPagamento, data, " +
                "categoriaVeiculo, classificacaoInfracao, descricao, valorCoima, local, entidadeAutuante, dataLimiteDefesa, normaInfringida) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)"
        const val queryDelete = "Delete FROM Contraordenacao WHERE numeroAuto = ? AND data = ? AND veiculo = ?"//defesa aceite I guess?
        const val queryUpdate = "UPDATE Contraordenacao SET estadoPagamento = 'Pago' WHERE numeroauto = ?"

    }

    fun get(numeroAuto: String) : ContraordenacaoDatabaseRow{
        return source.withHandleUnchecked { handle ->
            handle
                .createQuery(queryGet)
                .bind(0, numeroAuto)
                .mapTo(ContraordenacaoDatabaseRow::class.java)
                .one()
        }
    }

    fun getAll(veiculo: String): List<ContraordenacaoDatabaseRow> {
        return source.withHandleUnchecked { handle -> handle
            .createQuery(queryGetAll)
            .bind(0, veiculo)
            .mapTo(ContraordenacaoDatabaseRow::class.java)
            .list()
        }
    }

    fun getAllTime(veiculo: String, dataInicio: Long, dataFim: Long): List<ContraordenacaoDatabaseRow> {
        return source.withHandleUnchecked { handle -> handle
            .createQuery(queryGetAllTime)
            .bind(0, veiculo)
            .bind(1, getTimestamp(dataInicio))
            .bind(2, getTimestamp(dataFim))
            .mapTo(ContraordenacaoDatabaseRow::class.java)
            .list()
        }
    }

    fun create(evento: Contraordenacao, veiculo: String): String {
        source.withHandleUnchecked { handle ->
            handle
                .createUpdate(queryCreate)
                .bind(0, evento.numeroAuto)
                .bind(1, veiculo)
                .bind(2, evento.estadoPagamento)
                .bind(3, getTimestamp(evento.data))
                .bind(4, evento.categoriaVeiculo)
                .bind(5, evento.classificacaoInfracao)
                .bind(6, evento.descricao)
                .bind(7, evento.valorCoima)
                .bind(8, evento.local)
                .bind(9, evento.entidadeAutuante)
                .bind(10, getTimestamp(evento.dataLimiteDefesa))
                .bind(11, evento.normaInfringida)
                .execute()
        }
        return evento.numeroAuto
    }

    fun update(numeroAuto: String) {
        source.withHandleUnchecked { handle ->
            handle
                .createUpdate(queryUpdate)
                .bind(0, numeroAuto)
                .execute()
        }
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