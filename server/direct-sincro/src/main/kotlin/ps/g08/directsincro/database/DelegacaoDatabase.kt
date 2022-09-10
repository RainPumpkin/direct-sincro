package ps.g08.directsincro.database

import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.withHandleUnchecked
import org.springframework.stereotype.Component
import ps.g08.directsincro.common.DelegacaoVeiculo
import ps.g08.directsincro.common.getTimestamp
import java.sql.Timestamp

data class DelegacaoDatabaseRow(
        val dataInicio: Timestamp?,
        val dataCriacao: Timestamp,
        val dataFim: Timestamp?,
        val matricula: String,
        val subscritor: String
)

@Component
class DelegacaoDatabase(private val source : Jdbi) {
    companion object {
        const val queryGet = "SELECT * FROM Delegacao WHERE subscritor = ? AND matricula = ?"//user e matricula
        const val queryGetAllUser = "SELECT Delegacao.matricula, Delegacao.datacriacao,Delegacao.datainicio, Delegacao.datafim FROM Veiculo Left JOIN Delegacao ON Veiculo.matricula = Delegacao.matricula WHERE Delegacao.subscritor = ? "//getall for user
        const val queryGetAllMatricula = "SELECT * FROM Delegacao WHERE matricula = ? "//getall for matricula
        const val queryGetWithDate = "SELECT * FROM Delegacao WHERE matricula = ? AND datacriacao = ?"
        const val queryCreate = "INSERT INTO Delegacao(matricula, datacriacao,subscritor, dataInicio, dataFim) VALUES (?,?,?,?,?,?)"
        const val queryUpdateFim = "UPDATE Delegacao SET dataFim = ? WHERE matricula = ? AND subscritor = ? AND datacriacao = ?"
        const val queryUpdateInicio = "UPDATE Delegacao SET datainicio = ? WHERE matricula = ? AND datacriacao = ?"
        const val queryDelete = "Delete FROM Delegacao WHERE matricula = ? AND datacriacao = ?"
    }

    fun get(usuario: String, matricula: String) : DelegacaoDatabaseRow{
        return source.withHandleUnchecked { handle ->
            handle
                .createQuery(queryGet)
                .bind(0, usuario)
                .bind(1, matricula)
                .mapTo(DelegacaoDatabaseRow::class.java)
                .one()
        }
    }

    fun getAllUser(usuario: String): List<DelegacaoDatabaseRow> {
        return source.withHandleUnchecked { handle -> handle
            .createQuery(queryGetAllUser)
            .bind(0, usuario)
            .mapTo(DelegacaoDatabaseRow::class.java)
            .list()
        }
    }

    fun getAllMatricula(matricula: String): List<DelegacaoDatabaseRow> {
        return source.withHandleUnchecked { handle -> handle
            .createQuery(queryGetAllMatricula)
            .bind(0, matricula)
            .mapTo(DelegacaoDatabaseRow::class.java)
            .list()
        }
    }

    fun queryGetWithDate(nif: String, matricula: String, dataCriacao: Long): DelegacaoDatabaseRow {
        val timestamp: Timestamp = getTimestamp(dataCriacao)
        return source.withHandleUnchecked { handle -> handle
            .createQuery(queryGetWithDate)
            .bind(0, matricula)
            .bind(1, timestamp)
            .mapTo(DelegacaoDatabaseRow::class.java)
            .one()
        }
    }

    fun create(delegacao: DelegacaoVeiculo, matricula: String){
        source.withHandleUnchecked { handle ->
            handle
                .createUpdate(queryCreate)
                .bind(0, matricula)
                .bind(1, getTimestamp(delegacao.dataCriacao))
                .bind(2, delegacao.subscritor)
                .bind(3, delegacao.dataInicio?.let { getTimestamp(it) })
                .bind(4, delegacao.dataFim?.let { getTimestamp(it) })
                .execute()
        }
        //n retorna nada por enquanto, change later if needed
    }

    fun updateFim(dataFim: Long, matricula : String, dataInicio: Long){
        source.withHandleUnchecked { handle ->
            handle
                .createUpdate(queryUpdateFim)
                .bind(0, getTimestamp(dataFim))
                .bind(1, matricula)
                .bind(2, getTimestamp(dataInicio))
                .execute()
        }
    }

    fun delete(matricula: String, dataCriacao: Long) {
        source.withHandleUnchecked { handle -> handle
            .createUpdate(queryDelete)
            .bind(0, matricula)
            .bind(1, getTimestamp(dataCriacao))
            .execute()
        }
    }
}