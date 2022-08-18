package ps.g08.directsincro.database

import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.withHandleUnchecked
import org.springframework.stereotype.Component
import ps.g08.directsincro.common.DelegacaoVeiculo
import ps.g08.directsincro.common.getTimestamp
import java.sql.Timestamp

data class DelegacaoDatabaseRow(
        val dataInicio: Timestamp,
        val dataFim: Timestamp,
        val matricula: String,
        val usuario: String?
)

@Component
class DelegacaoDatabase(private val source : Jdbi) {
    companion object {
        const val queryGet = "SELECT * FROM Delegacao WHERE usuario = ? AND matricula = ?"//user e matricula
        const val queryGetAllUser = "SELECT Delegacao.matricula, Delegacao.datainicio, Delegacao.datafim FROM Veiculo Left JOIN Delegacao ON Veiculo.matricula = Delegacao.matricula WHERE Delegacao.usuario = ? "//getall for user
        const val queryGetAllMatricula = "SELECT * FROM Delegacao WHERE matricula = ? "//getall for matricula
        const val queryGetWithDate = "SELECT * FROM Delegacao WHERE matricula = ? AND dataInicio = ?"
        const val queryCreate = "INSERT INTO Delegacao(matricula, usuario, dataInicio, dataFim) VALUES (?,?,?,?,?)"
        const val queryUpdate = "UPDATE Delegacao SET dataFim = ? WHERE matricula = ? AND usuario = ? AND dataInicio = ?"//apenas estado
        const val queryDelete = "Delete FROM Delegacao WHERE matricula = ? AND usuario = ?"
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

    fun queryGetWithDate(nif: String, matricula: String, dataInicio: Long): DelegacaoDatabaseRow {
        val timestamp: Timestamp = getTimestamp(dataInicio)
        return source.withHandleUnchecked { handle -> handle
            .createQuery(queryGetWithDate)
            .bind(0, matricula)
            .bind(1, timestamp)
            .mapTo(DelegacaoDatabaseRow::class.java)
            .one()
        }
    }

    fun create(emprestimo: DelegacaoVeiculo, matricula: String){
        source.withHandleUnchecked { handle ->
            handle
                .createUpdate(queryCreate)
                .bind(0, matricula)
                .bind(1, emprestimo.usuario)
                .bind(2, getTimestamp(emprestimo.dataInicio))
                .bind(3, getTimestamp(emprestimo.dataFim))
                //.bind(4, emprestimo.estado)
                .execute()
        }
        //n retorna nada por enquanto, change later if needed
    }

    fun update(dataFim: Long, matricula : String, usuario: String, dataInicio: Long){
        source.withHandleUnchecked { handle ->
            handle
                .createUpdate(queryUpdate)
                .bind(0, getTimestamp(dataFim))
                .bind(1, matricula)
                .bind(2, usuario)
                .bind(3, getTimestamp(dataInicio))
                .execute()
        }
    }

    fun delete(matricula: String, usuario: String) {
        source.withHandleUnchecked { handle -> handle
            .createUpdate(queryDelete)
            .bind(0, matricula)
            .bind(1, usuario)
            .execute()
        }
    }
}