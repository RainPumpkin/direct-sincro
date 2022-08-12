package ps.g08.directsincro.service

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Contraordenacao
import ps.g08.directsincro.database.ContraordenacaoDatabase
import ps.g08.directsincro.service.mappers.EventoTransitoMapper

@Component
class EventoTransitoService(private val db: ContraordenacaoDatabase, private val mapper: EventoTransitoMapper) {

    fun getAllEventos(matricula: String) : List<Contraordenacao>{
        return mapper.multiple(db.getAll(matricula))
    }

    fun createEvento(evento: Contraordenacao, matricula: String): String {
        return db.create(evento, matricula)
    }
}