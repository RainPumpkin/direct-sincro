package ps.g08.directsincro.database

import org.jdbi.v3.core.Jdbi
import org.springframework.stereotype.Component

data class VeiculoDatabaseRow(
    val matricula: String,
    val modelo: String,
    val categoria: String,
    val owner: String
)

@Component
class VeiculoDatabase(private val source : Jdbi) {
    companion object {
        const val queryGet = "SELECT * FROM Veiculo WHERE matricula = ?"//user e id ou só id, depende
        const val queryGetAll = "SELECT * FROM Veiculo WHERE matricula = ? "//getall for user
        const val queryCreate = "INSERT INTO Veiculo(matricula, modelo, categoria, owner) VALUES (?,?,?,?)"//create com tudo
        const val queryUpdate = "UPDATE Veiculo SET owner = ? WHERE matricula = ?"//update de todos os campos, para updates parciais fazemos um get para ir buscar o resto antes, mas isso é fora da db
        const val queryDelete = "Delete FROM Veiculo WHERE matricula = ?"
    }
}