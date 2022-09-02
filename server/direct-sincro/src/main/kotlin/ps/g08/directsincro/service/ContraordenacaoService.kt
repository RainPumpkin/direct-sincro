package ps.g08.directsincro.service

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Contraordenacao
import ps.g08.directsincro.database.ContraordenacaoDatabase
import ps.g08.directsincro.database.ContraordenacaoDatabaseRow
import ps.g08.directsincro.service.mappers.ContraordenacaoMapper

@Component
class ContraordenacaoService(private val db: ContraordenacaoDatabase, private val mapper: ContraordenacaoMapper) {

    fun getContraordenação(numeroAuto: String) : Contraordenacao {
        return mapper.single(db.get(numeroAuto))
    }

    fun getAllContraordenacoes(matricula: String) : List<Contraordenacao>{
        val rows : List<ContraordenacaoDatabaseRow>
        try {
            rows = db.getAll(matricula)
            return mapper.multiple(rows)
        } catch (e : Exception) {
            return emptyList()
        }
    }

    fun createContraordenacao(evento: Contraordenacao, matricula: String): String {
        return db.create(evento, matricula)
    }

    fun updateEstadoPagamento(numeroAuto : String) {
        return db.update(numeroAuto)
    }
}