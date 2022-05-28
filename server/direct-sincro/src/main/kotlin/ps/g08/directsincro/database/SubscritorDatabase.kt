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
        const val queryGet = "SELECT * FROM Subscritor WHERE nif = ? "
        //const val queryGetAll = "SELECT * FROM Subscritor"
        const val queryCreate = "INSERT INTO Subscritor(nif, password) VALUES (?,?)"
        const val queryUpdate = "UPDATE Subscritor SET password = ? WHERE nif = ?"
        const val queryDelete = "Delete FROM Subscritor WHERE nif = ?"
    }
}