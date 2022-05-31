package ps.g08.directsincro.database

import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.withHandleUnchecked
import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Subscritor

data class SubscritorDatabaseRow(
    val nif: String,
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

    fun get(nif: String) : SubscritorDatabaseRow{
        return source.withHandleUnchecked { handle ->
            handle
                .createQuery(queryGet)
                .bind(0, nif)
                .mapTo(SubscritorDatabaseRow::class.java)
                .one()
        }
    }

    fun create(nif: String, password: String): String {
        source.withHandleUnchecked { handle ->
            handle
                .createUpdate(queryCreate)
                .bind(0, nif)
                .bind(1, password)
                .execute()
        }
        return nif
    }

    fun update(password: String, nif : String){
        source.withHandleUnchecked { handle ->
            handle
                .createUpdate(queryUpdate)
                .bind(0, password)
                .bind(1, nif)
                .execute()
        }
    }

    fun delete(nif : String) {
        source.withHandleUnchecked { handle -> handle
            .createUpdate(queryDelete)
            .bind(0, nif)
            .execute()
        }
    }
}