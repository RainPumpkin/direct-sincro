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
        const val queryGet = ""//user e matricula
        const val queryGetAllUser = ""//getall for user
        const val queryGetAllMatricula = ""//getall for matricula
        const val queryCreate = ""
        const val queryUpdate = ""//apenas estado
        const val queryDelete = ""
    }
}