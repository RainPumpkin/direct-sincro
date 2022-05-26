package ps.g08.directsincro.database

import org.jdbi.v3.core.Jdbi
import org.springframework.stereotype.Component

@Component
class PlaceholderDataBase(private val source: Jdbi) {
    companion object {
        const val queryGet = ""//user e id ou só id, depende
        const val queryGetAll = ""//getall for user
        const val queryCreate = ""//create com tudo
        const val queryUpdate = ""//update de todos os campos, para updates parciais fazemos um get para ir buscar o resto antes, mas isso é fora da db
        const val queryDelete = ""
    }
}