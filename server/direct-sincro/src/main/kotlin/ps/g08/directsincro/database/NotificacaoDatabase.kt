package ps.g08.directsincro.database

import org.jdbi.v3.core.Jdbi
import org.springframework.stereotype.Component

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

    /*
    para o create
    .executeAndReturnGeneratedKeys()
            .mapTo(Int::class.java)
     */
}