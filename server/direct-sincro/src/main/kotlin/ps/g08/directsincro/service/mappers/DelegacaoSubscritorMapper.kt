package ps.g08.directsincro.service.mappers

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.DelegacaoSubscritor
import ps.g08.directsincro.common.getEpoch
import ps.g08.directsincro.database.DelegacaoDatabaseRow

@Component
class DelegacaoSubscritorMapper : IMapper<DelegacaoDatabaseRow, DelegacaoSubscritor> {
    override fun single(obj: DelegacaoDatabaseRow): DelegacaoSubscritor {
        return DelegacaoSubscritor(
            dataFim = obj.dataFim?.let { getEpoch(it) },
            dataInicio = getEpoch(obj.dataInicio),
            matricula = obj.matricula,
        )
    }

    override fun multiple(objs: List<DelegacaoDatabaseRow>): List<DelegacaoSubscritor> {
        val ret : MutableList<DelegacaoSubscritor> = mutableListOf()

        for (obj in objs) {
            ret.add(single(obj))
        }

        return ret
    }
}