package ps.g08.directsincro.service.mappers

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.DelegacaoVeiculo
import ps.g08.directsincro.common.getEpoch
import ps.g08.directsincro.database.DelegacaoDatabaseRow

@Component
class DelegacaoVeiculoMapper : IMapper<DelegacaoDatabaseRow, DelegacaoVeiculo> {
    override fun single(obj: DelegacaoDatabaseRow): DelegacaoVeiculo {
        return DelegacaoVeiculo(
            dataFim = obj.dataFim?.let { getEpoch(it) },
            dataInicio = obj.dataInicio?.let {  getEpoch(it) },
            subscritor = obj.subscritor,
            dataCriacao = getEpoch(obj.dataCriacao)
        )
    }

    override fun multiple(objs: List<DelegacaoDatabaseRow>): List<DelegacaoVeiculo> {
        val ret : MutableList<DelegacaoVeiculo> = mutableListOf()

        for (obj in objs) {
            ret.add(single(obj))
        }

        return ret
    }
}