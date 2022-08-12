package ps.g08.directsincro.database

import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.withHandleUnchecked
import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Veiculo

data class VeiculoDatabaseRow(
    val matricula: String,
    val modelo: String,
    val categoria: String,
    val owner: String
)

@Component
class VeiculoDatabase(private val source : Jdbi) {
    companion object {
        const val queryGet = "SELECT * FROM Veiculo WHERE matricula = ?"
        const val queryGetAll = "SELECT * FROM Veiculo WHERE owner = ? "
        const val queryGetAllAlugados = "SELECT DISTINCT Veiculo.* FROM Veiculo RIGHT JOIN Delegacao ON Veiculo.matricula = Delegacao.matricula WHERE Veiculo.owner = ?"
        const val queryCreate = "INSERT INTO Veiculo(matricula, modelo, categoria, owner) VALUES (?,?,?,?)"
        const val queryUpdate = "UPDATE Veiculo SET owner = ? WHERE matricula = ?"
        const val queryDelete = "Delete FROM Veiculo WHERE matricula = ?"
    }

    fun get(matricula: String) : VeiculoDatabaseRow{
        return source.withHandleUnchecked { handle ->
            handle
                .createQuery(queryGet)
                .bind(0, matricula)
                .mapTo(VeiculoDatabaseRow::class.java)
                .one()
        }
    }

    fun getAll(owner: String): List<VeiculoDatabaseRow> {
        return source.withHandleUnchecked { handle -> handle
            .createQuery(queryGetAll)
            .bind(0, owner)
            .mapTo(VeiculoDatabaseRow::class.java)
            .list()
        }
    }

    fun getAllAlugados(usuario: String): List<VeiculoDatabaseRow> {
        return source.withHandleUnchecked { handle -> handle
            .createQuery(queryGetAllAlugados)
            .bind(0, usuario)
            .mapTo(VeiculoDatabaseRow::class.java)
            .list()
        }
    }

    fun create(veiculo : Veiculo, owner: String): String {
        source.withHandleUnchecked { handle ->
            handle
                .createUpdate(queryCreate)
                .bind(0, veiculo.matricula)
                .bind(1, veiculo.modelo)
                .bind(2, veiculo.categoria)
                .bind(3, owner)
                .execute()
        }
        return veiculo.matricula
    }

    fun update(newowner: String, matricula : String){
        source.withHandleUnchecked { handle ->
            handle
                .createUpdate(queryUpdate)
                .bind(0, newowner)
                .bind(1, matricula)
                .execute()
        }
    }

    fun delete(matricula: String) {
        source.withHandleUnchecked { handle -> handle
            .createUpdate(queryDelete)
            .bind(0, matricula)
            .execute()
        }
    }

}