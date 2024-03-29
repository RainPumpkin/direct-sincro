package ps.g08.directsincro.service.mappers

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Cidadao
import ps.g08.directsincro.database.CidadaoDatabaseRow
import ps.g08.directsincro.service.CidadaoService
import ps.g08.directsincro.service.SubscritorService

@Component
class CidadaoMapper(val subscritorService: SubscritorService) : IMapper<CidadaoDatabaseRow, Cidadao> {
    override fun single(obj: CidadaoDatabaseRow): Cidadao {
        return Cidadao(
            nome = obj.nome,
            nif = obj.nif,
            tituloConducao = obj.tituloConducao,
            email = obj.email,
            password = obj.password,
            subscritor = subscritorService.getSubscription(obj.nif)
        )
    }

    override fun multiple(objs: List<CidadaoDatabaseRow>): List<Cidadao> {
        val ret : MutableList<Cidadao> = mutableListOf()

        for (obj in objs) {
            ret.add(single(obj))
        }

        return ret
    }
}