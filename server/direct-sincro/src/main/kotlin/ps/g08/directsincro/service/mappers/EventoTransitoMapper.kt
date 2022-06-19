package ps.g08.directsincro.service.mappers

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Evento_Transito
import ps.g08.directsincro.common.getEpoch
import ps.g08.directsincro.database.EventoTransitoDatabaseRow

@Component
class EventoTransitoMapper : IMapper<EventoTransitoDatabaseRow, Evento_Transito> {
    override fun single(obj: EventoTransitoDatabaseRow): Evento_Transito {
        return Evento_Transito(
            numeroAuto = obj.numeroAuto,
            estadoPagamento = obj.estadoPagamento,
            data = getEpoch(obj.data),
            tipo = obj.tipo,
            classificacaoInfracao = obj.classificacaoInfracao,
            descricao = obj.descricao,
            valor = obj.valor,
            localizacao = obj.localizacao,
            entidadeAutuante = obj.entidadeAutuante,
            dataLimiteDefesa = obj.dataLimiteDefesa.time//inverse is new Date(long)
        )
    }

    override fun multiple(objs: List<EventoTransitoDatabaseRow>): List<Evento_Transito> {
        val ret : MutableList<Evento_Transito> = mutableListOf()

        for (obj in objs) {
            ret.add(single(obj))
        }

        return ret
    }
}