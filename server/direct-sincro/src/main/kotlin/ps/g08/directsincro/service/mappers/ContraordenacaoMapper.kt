package ps.g08.directsincro.service.mappers

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Contraordenacao
import ps.g08.directsincro.common.getEpoch
import ps.g08.directsincro.database.ContraordenacaoDatabaseRow

@Component
class ContraordenacaoMapper : IMapper<ContraordenacaoDatabaseRow, Contraordenacao> {
    override fun single(obj: ContraordenacaoDatabaseRow): Contraordenacao {
        return Contraordenacao(
            numeroAuto = obj.numeroAuto,
            estadoPagamento = obj.estadoPagamento,
            data = getEpoch(obj.data),
            categoriaVeiculo = obj.categoriaVeiculo,
            classificacaoInfracao = obj.classificacaoInfracao,
            descricao = obj.descricao,
            valorCoima = obj.valorCoima,
            local = obj.local,
            entidadeAutuante = obj.entidadeAutuante,
            dataLimiteDefesa = obj.dataLimiteDefesa.time,//inverse is new Date(long)
            normaInfringida = obj.normaInfringida
        )
    }

    override fun multiple(objs: List<ContraordenacaoDatabaseRow>): List<Contraordenacao> {
        val ret : MutableList<Contraordenacao> = mutableListOf()

        for (obj in objs) {
            ret.add(single(obj))
        }

        return ret
    }
}