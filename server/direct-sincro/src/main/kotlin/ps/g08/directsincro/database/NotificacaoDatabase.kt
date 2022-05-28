package ps.g08.directsincro.database

import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.withHandleUnchecked
import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Notificacao

data class NotificacaoDatabaseRow(
    val emitida: Boolean,
    val mensagem: String,
    val id: Int,
    val recebida: Boolean,
    val tipo: String,
    val subscritor: String
)

@Component
class NotificacaoDatabase(private val source : Jdbi) {
    companion object {
        const val queryGet = "SELECT * FROM Notificacao WHERE id = ?"
        const val queryGetAll = "SELECT * FROM Notificacao WHERE subscritor = ? "
        const val queryCreate = "INSERT INTO Notificacao(emitida, mensagem, recebida, tipo, subscritor) VALUES (?,?,?,?,?) RETURNING id"
        const val queryUpdate = "UPDATE Notificacao SET emitida = ?, mensagem = ?, recebida = ?, tipo = ?, subscritor = ? WHERE id = ?"
        const val queryDelete = "Delete FROM Notificacao WHERE id = ? AND subscritor = ?"
    }

    fun get(id: Int) : NotificacaoDatabaseRow{
        return source.withHandleUnchecked { handle ->
            handle
                .createQuery(queryGet)
                .bind(0, id)
                .mapTo(NotificacaoDatabaseRow::class.java)
                .one()
        }
    }

    fun getAll(subscritor: String): List<NotificacaoDatabaseRow> {
        return source.withHandleUnchecked { handle -> handle
            .createQuery(queryGetAll)
            .bind(0, subscritor)
            .mapTo(NotificacaoDatabaseRow::class.java)
            .list()
        }
    }

    fun create(notificacao : Notificacao, subscritor: String): Int {
        return source.withHandleUnchecked { handle ->
            handle
                .createUpdate(queryCreate)
                .bind(0, notificacao.emitida)
                .bind(1, notificacao.mensagem)
                .bind(2, notificacao.recebida)
                .bind(3, notificacao.tipo)
                .bind(4, subscritor)
                .executeAndReturnGeneratedKeys()
                .mapTo(Int::class.java)
                .one()
        }
    }

    fun update(emitida: Boolean, mensagem: String, recebida: Boolean, tipo: String, subscritor: String, id: Int){
        source.withHandleUnchecked { handle ->
            handle
                .createUpdate(queryUpdate)
                .bind(0, emitida)
                .bind(1, mensagem)
                .bind(2, recebida)
                .bind(3, tipo)
                .bind(4, subscritor)
                .bind(5, id)
                .execute()
        }
    }

    fun delete(id: Int, subscritor: String) {
        source.withHandleUnchecked { handle -> handle
            .createUpdate(queryDelete)
            .bind(0, id)
            .bind(1, subscritor)
            .execute()
        }
    }
}