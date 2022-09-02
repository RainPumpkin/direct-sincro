package ps.g08.directsincro.database

import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.withHandleUnchecked
import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Notificacao
import ps.g08.directsincro.common.getTimestamp

data class NotificacaoDatabaseRow(
    val emitida: Long,
    val mensagem: String,
    val contraordenacao: String,
    val visualizada: Boolean,
    val tipo: String,
    val subscritor: String
)

@Component
class NotificacaoDatabase(private val source : Jdbi) {
    companion object {
        const val queryGet = "SELECT * FROM Notificacao WHERE subscritor = ? AND emitida = ?"
        const val queryGetAll = "SELECT * FROM Notificacao WHERE subscritor = ? "
        const val queryCreate = "INSERT INTO Notificacao(emitida, mensagem, visualizada, tipo, subscritor, contraordenacao) VALUES (?,?,?,?,?,?)"
        const val queryUpdate = "UPDATE Notificacao SET mensagem = ?, visualizada = ?, tipo = ?,  WHERE subscritor = ? AND emitida = ?"
        const val queryDelete = "Delete FROM Notificacao WHERE emitida = ? AND subscritor = ?"
    }

    fun get(subscritor: String, emitida: Long) : NotificacaoDatabaseRow{
        return source.withHandleUnchecked { handle ->
            handle
                .createQuery(queryGet)
                .bind(0, subscritor)
                .bind(1, getTimestamp(emitida))
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

    fun create(notificacao : Notificacao, subscritor: String, contraordenacao: String): Int {
        return source.withHandleUnchecked { handle ->
            handle
                .createUpdate(queryCreate)
                .bind(0, notificacao.emitida)
                .bind(1, notificacao.mensagem)
                .bind(2, notificacao.visualizada)
                .bind(3, notificacao.tipo)
                .bind(4, subscritor)
                .bind(5, contraordenacao)
                .executeAndReturnGeneratedKeys()
                .mapTo(Int::class.java)
                .one()
        }
    }

    fun update(emitida: Long, mensagem: String, recebida: Boolean, tipo: String, subscritor: String){
        source.withHandleUnchecked { handle ->
            handle
                .createUpdate(queryUpdate)
                .bind(0, mensagem)
                .bind(1, recebida)
                .bind(2, tipo)
                .bind(3, subscritor)
                .bind(4, emitida)
                .execute()
        }
    }

    fun delete(emitida: Long, subscritor: String) {
        source.withHandleUnchecked { handle -> handle
            .createUpdate(queryDelete)
            .bind(0, getTimestamp(emitida))
            .bind(1, subscritor)
            .execute()
        }
    }
}