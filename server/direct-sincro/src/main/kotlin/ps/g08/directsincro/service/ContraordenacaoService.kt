package ps.g08.directsincro.service

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Contraordenacao
import ps.g08.directsincro.database.ContraordenacaoDatabase
import ps.g08.directsincro.service.mappers.ContraordenacaoMapper

@Component
class ContraordenacaoService(private val db: ContraordenacaoDatabase, private val mapper: ContraordenacaoMapper) {

    fun getAllContraordenacoes(matricula: String) : List<Contraordenacao>{
        return mapper.multiple(db.getAll(matricula))
    }

    fun createContraordenacao(evento: Contraordenacao, matricula: String): String {
        return db.create(evento, matricula)
    }
}