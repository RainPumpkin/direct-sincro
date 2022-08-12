package ps.g08.directsincro.database

import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.withHandleUnchecked
import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Subscritor
import ps.g08.directsincro.common.getTimestamp
import java.sql.Timestamp

data class SubscritorDatabaseRow(
    val nif: String
)

@Component
class SubscritorDatabase(private val source : Jdbi) {
    companion object {
        const val queryGet = "SELECT * FROM Subscritor WHERE nif = ? "//?
        //const val queryGetAll = "SELECT * FROM Subscritor"
        const val queryGetLastSubs = "Select inicio FROM DataSubscricao WHERE nif = ? AND cancelamento = null "
        const val queryCreate = "INSERT INTO Subscritor(nif) VALUES (?)"
        const val queryCreateData = "INSERT INTO DataSubscricao(nif, inicio) VALUES (?, ?)"
        const val queryUpdate = "UPDATE DataSubscricao SET cancelamento = ? WHERE nif = ? AND inicio = ?"
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

    fun create(nif: String): String {
        try {
            source.withHandleUnchecked { handle ->
                handle
                    .createUpdate(queryCreate)
                    .bind(0, nif)
                    .execute()
            }
        } catch(_: Exception) {
            //já existia subscritor, n faz mal
        }
        val curr = getTimestamp(System.currentTimeMillis())
        source.withHandleUnchecked { handle -> handle
            .createUpdate(queryCreateData)
            .bind(0, nif)
            .bind(1, curr)
            .execute()
        }
        return nif
    }

    fun delete(nif : String) {
        val time: Timestamp = source.withHandleUnchecked { handle -> handle
            .createQuery(queryGetLastSubs)
            .bind(0, nif)
            .mapTo(Timestamp::class.java)
            .one()
        }

        val curr = getTimestamp(System.currentTimeMillis())

        source.withHandleUnchecked { handle -> handle
            .createUpdate(queryUpdate)
            .bind(0, curr)
            .bind(1, nif)
            .bind(2, time)
            .execute()
        }
        /*explicit delete
        source.withHandleUnchecked { handle -> handle
            .createUpdate(queryDelete)
            .bind(0, nif)
            .execute()
        }*/
    }
}