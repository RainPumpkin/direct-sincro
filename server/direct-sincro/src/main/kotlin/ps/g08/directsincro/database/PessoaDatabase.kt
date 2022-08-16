package ps.g08.directsincro.database

import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.withHandleUnchecked
import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Cidadao

data class CidadaoDatabaseRow(
        val nome: String,
        val nif: String,
        val numero_conducao: String,
        val email: String,
        val password: String
)

@Component
class PessoaDatabase(private val source: Jdbi) {
    companion object {
        const val queryGet = "SELECT * FROM Cidadao WHERE nif = ? "
        //const val queryGetAll = "SELECT * FROM Pessoa WHERE nif = ? "
        const val queryCreate = "INSERT INTO Cidadao(nome, nif, numero_conducao, email, password) VALUES (?,?,?,?,?)"
        const val queryUpdate = "UPDATE Cidadao SET email = ? WHERE nif = ?"
        const val queryUpdatePass = "UPDATE Cidadao SET password = ? WHERE nif = ?"
        const val queryDelete = "Delete FROM Cidadao WHERE nif = ?"
    }

    fun get(nif: String) : CidadaoDatabaseRow{
        return source.withHandleUnchecked { handle ->
            handle
                .createQuery(queryGet)
                .bind(0, nif)
                .mapTo(CidadaoDatabaseRow::class.java)
                .one()
        }
    }

    fun create(nome: String, nif: String, numero_conducao: String, email: String, password: String): String {
        source.withHandleUnchecked { handle ->
            handle
                .createUpdate(queryCreate)
                .bind(0, nome)
                .bind(1, nif)
                .bind(2, numero_conducao)
                .bind(3, email)
                .bind(4, password)
                .execute()
        }
        return nif
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