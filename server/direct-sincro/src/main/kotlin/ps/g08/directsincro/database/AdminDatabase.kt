package ps.g08.directsincro.database

import org.jdbi.v3.core.Jdbi
import org.springframework.stereotype.Component

data class AdminDatabaseRow(
    val username: String,
    val password: String
)

@Component
class AdminDatabase(private val source : Jdbi) {
    companion object {
        const val queryGet = "SELECT * FROM Admin WHERE username = ? "
        const val queryCreate = "INSERT INTO Admin(username, password) VALUES (?,?)"
        const val queryUpdate = "UPDATE Admin SET password = ? WHERE username = ?"//update de password
        const val queryDelete = "Delete FROM Admin WHERE username = ?"
    }
}