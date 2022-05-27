package ps.g08.directsincro.database

import org.jdbi.v3.core.Jdbi
import org.springframework.stereotype.Component

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
        const val queryGet = "SELECT * FROM Pedido_Defesa WHERE requeridor = ? AND id = ?"//user e id ou só id, depende
        const val queryGetAll = "SELECT * FROM Pedido_Defesa WHERE requeridor = ?"//getall for user
        const val queryCreate = "INSERT INTO Pedido_Defesa(moradaSede, justificacao, numero_conducao, numeroAuto, requeridor) VALUES (?,?,?,?,?) RETURNING id"//create com tudo
        const val queryUpdate = "UPDATE Pedido_Defesa SET moradaSede = ?, justificacao = ?, numero_conducao = ?, requeridor = ? WHERE id = ?"//update de todos os campos, para updates parciais fazemos um get para ir buscar o resto antes, mas isso é fora da db
        const val queryDelete = "Delete FROM Pedido_Defesa WHERE id = ?"
    }
}