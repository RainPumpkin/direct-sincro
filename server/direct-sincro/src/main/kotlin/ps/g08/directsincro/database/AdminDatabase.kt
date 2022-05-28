package ps.g08.directsincro.database

import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.withHandleUnchecked
import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Admin

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

    fun get(username: String) : AdminDatabaseRow{
        return source.withHandleUnchecked { handle ->
            handle
                .createQuery(queryGet)
                .bind(0, username)
                .mapTo(AdminDatabaseRow::class.java)
                .one()
        }
    }

    fun create(admin : Admin): String {
        source.withHandleUnchecked { handle ->
            handle
                .createUpdate(queryCreate)
                .bind(0, admin.username)
                .bind(1, admin.password)
                .execute()
        }
        return admin.username
    }

    fun update(newPassword: String, username : String){
        source.withHandleUnchecked { handle ->
            handle
                .createUpdate(queryUpdate)
                .bind(0, newPassword)
                .bind(1, username)
                .execute()
        }
    }

    fun delete(username: String) {
        source.withHandleUnchecked { handle -> handle
            .createUpdate(queryDelete)
            .bind(0, username)
            .execute()
        }
    }
}