package ps.g08.directsincro.database

import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.withHandleUnchecked
import org.springframework.stereotype.Component

data class PushSubscriptionDatabaseRow(
    val nif: String,
    val endpoint: String,
    val publicKey: String,
    val auth: String
)

@Component
class PushSubscriptionDatabase (private val source: Jdbi){
    companion object {
        const val queryCreatePushSubscription = "INSERT into PUSH_SUBSCRIPTION VALUES (?,?,?,?)"
        const val queryGetPushCredentials = "SELECT * FROM PUSH_SUBSCRIPTION WHERE nif = ?"
        const val queryDeletePushSubscription = "DELETE FROM PUSH_SUBSCRIPTION WHERE nif = ? and endpoint = ?"
    }

    fun getPushCredentials(nif: String) : List<PushSubscriptionDatabaseRow> {
        return source.withHandleUnchecked { handle ->
            handle
                .createQuery(queryGetPushCredentials)
                .bind(0, nif)
                .mapTo(PushSubscriptionDatabaseRow::class.java)
                .list()
        }
    }

    fun createPushSubscription(nif: String, endpoint: String, publicKey: String, auth: String) {
        source.withHandleUnchecked { handle ->
            handle
                .createUpdate(queryCreatePushSubscription)
                .bind(0, nif)
                .bind(1, endpoint)
                .bind(2, publicKey)
                .bind(3, auth)
                .execute()
        }
    }

    fun deletePushSubscription(nif: String, endpoint: String) {
        source.withHandleUnchecked { handle ->
            handle
                .createUpdate(queryDeletePushSubscription)
                .bind(0, nif)
                .bind(1, endpoint)
                .execute()
        }
    }
}