package ps.g08.directsincro.service

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Evento_Transito
import ps.g08.directsincro.database.EventoTransitoDatabase
import ps.g08.directsincro.service.mappers.EventoTransitoMapper

@Component
class EventoTransitoService(private val db: EventoTransitoDatabase, private val mapper: EventoTransitoMapper) {

    fun getAllEventos(matricula: String) : List<Evento_Transito>{
        return mapper.multiple(db.getAll(matricula))
    }
}