package ps.g08.directsincro.database

import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.withHandleUnchecked
import org.springframework.stereotype.Component
import ps.g08.directsincro.common.getTimestamp
import java.sql.Timestamp

data class EmprestimoDatabaseRow(
        val dataInicio: Timestamp,
        val dataFim: Timestamp,
        val estado: String,
        val matricula: String,
        val usuario: String
)

@Component
class EmprestimoDatabase(private val source : Jdbi) {
    companion object {
        const val queryGet = "SELECT * FROM Emprestimo WHERE usuario = ? AND matricula = ?"//user e matricula
        const val queryGetAllUser = "SELECT * FROM Emprestimo WHERE usuario = ? "//getall for user
        const val queryGetAllMatricula = "SELECT * FROM Emprestimo WHERE matricula = ? "//getall for matricula
        const val queryCreate = "INSERT INTO Emprestimo(matricula, usuario, dataInicio, dataFim, estado) VALUES (?,?,?,?,?)"
        const val queryUpdate = "UPDATE Emprestimo SET estado = ? WHERE matricula = ? AND usuario = ? AND dataInicio = ?"//apenas estado
        const val queryDelete = "Delete FROM Emprestimo WHERE matricula = ? AND usuario = ?"
    }

    fun get(usuario: String, matricula: String) : EmprestimoDatabaseRow{
        return source.withHandleUnchecked { handle ->
            handle
                .createQuery(queryGet)
                .bind(0, usuario)
                .bind(1, matricula)
                .mapTo(EmprestimoDatabaseRow::class.java)
                .one()
        }
    }

    fun queryGetAllUser(usuario: String): List<EmprestimoDatabaseRow> {
        return source.withHandleUnchecked { handle -> handle
            .createQuery(queryGetAllUser)
            .bind(0, usuario)
            .mapTo(EmprestimoDatabaseRow::class.java)
            .list()
        }
    }

    fun queryGetAllMatricula(matricula: String): List<EmprestimoDatabaseRow> {
        return source.withHandleUnchecked { handle -> handle
            .createQuery(queryGetAllMatricula)
            .bind(0, matricula)
            .mapTo(EmprestimoDatabaseRow::class.java)
            .list()
        }
    }
    /*
    não sei como é que queres fazer aqui não é preciso uma class Emprestimo mas ela à 2
     */
    fun create(emprestimo : Emprestimo, owner: String): String {
        source.withHandleUnchecked { handle ->
            handle
                .createUpdate(queryCreate)
                .bind(0, veiculo.matricula)
                .bind(1, veiculo.modelo)
                .bind(2, veiculo.categoria)
                .bind(3, owner)
                .execute()
        }
        return veiculo.matricula;
    }

    fun update(estado: String, matricula : String, usuario : String, dataInicio : Long){
        source.withHandleUnchecked { handle ->
            handle
                .createUpdate(queryUpdate)
                .bind(0, estado)
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