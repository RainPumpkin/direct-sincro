package ps.g08.directsincro.database

import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.withHandleUnchecked
import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Pessoa

data class PessoaDatabaseRow(
        val nome: String,
        val nif: Int,
        val numero_conducao: String,
        val email: String
)

@Component
class PessoaDatabase(private val source: Jdbi) {
    companion object {
        const val queryGet = "SELECT * FROM Pessoa WHERE nif = ? "
        //const val queryGetAll = "SELECT * FROM Pessoa WHERE nif = ? "
        const val queryCreate = "INSERT INTO Pessoa(nome, nif, numero_conducao, email) VALUES (?,?,?,?)"
        const val queryUpdate = "UPDATE Pessoa SET email = ? WHERE nif = ?"
        const val queryDelete = "Delete FROM Pessoa WHERE nif = ?"
    }

    fun get(nif: Int) : PessoaDatabaseRow{
        return source.withHandleUnchecked { handle ->
            handle
                .createQuery(queryGet)
                .bind(0, nif)
                .mapTo(PessoaDatabaseRow::class.java)
                .one()
        }
    }

    fun create(pessoa : Pessoa): Int {
        source.withHandleUnchecked { handle ->
            handle
                .createUpdate(queryCreate)
                .bind(0, pessoa.nome)
                .bind(1, pessoa.nif)
                .bind(2, pessoa.numero_conducao)
                .bind(3, pessoa.email)
                .execute()
        }
        return pessoa.nif
    }

    fun update(email: String, nif : Int){
        source.withHandleUnchecked { handle ->
            handle
                .createUpdate(queryUpdate)
                .bind(0, email)
                .bind(1, nif)
                .execute()
        }
    }

    fun delete(nif : Int) {
        source.withHandleUnchecked { handle -> handle
            .createUpdate(queryDelete)
            .bind(0, nif)
            .execute()
        }
    }
}