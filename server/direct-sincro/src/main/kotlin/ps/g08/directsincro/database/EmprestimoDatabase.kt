package ps.g08.directsincro.database

import org.jdbi.v3.core.Jdbi
import org.springframework.stereotype.Component
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
        const val queryGet = "SELECT * FROM Admin WHERE usuario = ? AND matricula = ?"//user e matricula
        const val queryGetAllUser = "SELECT * FROM Emprestimo WHERE usuario = ? "//getall for user
        const val queryGetAllMatricula = "SELECT * FROM Emprestimo WHERE matricula = ? "//getall for matricula
        const val queryCreate = "INSERT INTO Emprestimo(matricula, usuario, dataInicio, dataFim, estado) VALUES (?,?,?,?,?)"
        const val queryUpdate = "UPDATE Emprestimo SET estado = ? WHERE matricula = ? AND usuario = ? AND dataInicio = ?"//apenas estado
        const val queryDelete = "Delete FROM Emprestimo WHERE matricula = ? AND usuario = ?"
    }
}