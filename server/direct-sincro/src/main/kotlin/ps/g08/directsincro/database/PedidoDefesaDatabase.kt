package ps.g08.directsincro.database

import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.withHandleUnchecked
import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Pedido_Defesa

data class PedidoDefesaDatabaseRow(
    val id: Int,
    val moradaSede: String,
    val justificacao: String,
    val numero_conducao: String,
    val numeroAuto: String,
    val requeridor: String
)

@Component
class PedidoDefesaDatabase(private val source : Jdbi) {
    companion object {
        const val queryGet = "SELECT * FROM Pedido_Defesa WHERE requeridor = ? AND id = ?"
        const val queryGetAll = "SELECT * FROM Pedido_Defesa WHERE requeridor = ?"
        const val queryCreate = "INSERT INTO Pedido_Defesa(moradaSede, justificacao, numero_conducao, numeroAuto, requeridor) VALUES (?,?,?,?,?) RETURNING id"
        const val queryUpdate = "UPDATE Pedido_Defesa SET moradaSede = ?, justificacao = ?, numero_conducao = ?, requeridor = ? WHERE id = ?"
        const val queryDelete = "Delete FROM Pedido_Defesa WHERE id = ?"
    }

    fun get(requeridor: String, id: Int) : PedidoDefesaDatabaseRow{
        return source.withHandleUnchecked { handle ->
            handle
                .createQuery(queryGet)
                .bind(0, requeridor)
                .bind(1, id)
                .mapTo(PedidoDefesaDatabaseRow::class.java)
                .one()
        }
    }

    fun getAll(requeridor: String): List<PedidoDefesaDatabaseRow> {
        return source.withHandleUnchecked { handle -> handle
            .createQuery(queryGetAll)
            .bind(0, requeridor)
            .mapTo(PedidoDefesaDatabaseRow::class.java)
            .list()
        }
    }

    fun create(pedidoDefesa : Pedido_Defesa, numeroAuto: String, requeridor: String): Int {
        return source.withHandleUnchecked { handle ->
            handle
                .createUpdate(queryCreate)
                .bind(0, pedidoDefesa.moradaSede)
                .bind(1, pedidoDefesa.justificacao)
                .bind(2, pedidoDefesa.numero_conducao)
                .bind(3, numeroAuto)
                .bind(4, requeridor)
                .executeAndReturnGeneratedKeys()
                .mapTo(Int::class.java)
                .one()
        }
    }

    fun update(moradaSede: String, justificacao: String, numero_conducao: String, requeridor: String, id: Int){
        source.withHandleUnchecked { handle ->
            handle
                .createUpdate(queryUpdate)
                .bind(0, moradaSede)
                .bind(1, justificacao)
                .bind(2, numero_conducao)
                .bind(3, requeridor)
                .bind(4, id)
                .execute()
        }
    }

    fun delete(id: Int) {
        source.withHandleUnchecked { handle -> handle
            .createUpdate(queryDelete)
            .bind(0, id)
            .execute()
        }
    }
}