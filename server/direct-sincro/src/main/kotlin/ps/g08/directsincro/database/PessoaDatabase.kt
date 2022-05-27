package ps.g08.directsincro.database

import org.jdbi.v3.core.Jdbi
import org.springframework.stereotype.Component

data class PessoaDatabaseRow(
        val nome: String,
        val nif: Int,
        val numero_conducao: String,
        val email: String
)

@Component
class PessoaDatabase(private val source: Jdbi) {
    companion object {
        const val queryGet = "SELECT * FROM Pessoa WHERE nif = ? "//user e id ou só id, depende
        const val queryGetAll = "SELECT * FROM Pessoa WHERE nif = ? "//getall for user
        const val queryCreate = "INSERT INTO Pessoa(nome, nif, numero_conducao, email) VALUES (?,?,?,?)"//create com tudo
        const val queryUpdate = "UPDATE Pessoa SET email = ? WHERE nif = ?"//update de todos os campos, para updates parciais fazemos um get para ir buscar o resto antes, mas isso é fora da db
        const val queryDelete = "Delete FROM Pessoa WHERE nif = ?"
    }
}