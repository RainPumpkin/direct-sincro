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
        const val queryGet = ""
        const val queryCreate = ""
        const val queryUpdate = ""//update de password
        const val queryDelete = ""
    }
}