package ps.g08.directsincro.database

import org.jdbi.v3.core.Jdbi
import org.springframework.stereotype.Component

data class SubscritorDatabaseRow(
    val nif: Int,
    val password: String
)

@Component
class SubscritorDatabase(private val source : Jdbi) {
    companion object {
        const val queryGet = "SELECT * FROM Subscritor WHERE nif = ? "//user e id ou só id, depende
        const val queryGetAll = "SELECT * FROM Subscritor WHERE nif = ? "//getall for user
        const val queryCreate = "INSERT INTO Subscritor(nif, password) VALUES (?,?)"//create com tudo
        const val queryUpdate = "UPDATE Subscritor SET password = ? WHERE nif = ?"//update de todos os campos, para updates parciais fazemos um get para ir buscar o resto antes, mas isso é fora da db
        const val queryDelete = "Delete FROM Subscritor WHERE nif = ?"
    }
}