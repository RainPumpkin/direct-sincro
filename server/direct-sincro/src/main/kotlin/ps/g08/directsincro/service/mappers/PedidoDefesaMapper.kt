package ps.g08.directsincro.service.mappers

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Pedido_Defesa
import ps.g08.directsincro.database.PedidoDefesaDatabaseRow

@Component
class PedidoDefesaMapper : IMapper<PedidoDefesaDatabaseRow, Pedido_Defesa>{
    override fun single(obj: PedidoDefesaDatabaseRow): Pedido_Defesa {
        return Pedido_Defesa(
            id = obj.id,
            moradaSede = obj.moradaSede,
            justificacao = obj.justificacao,
            numero_conducao = obj.numero_conducao
        )
    }

    override fun multiple(objs: List<PedidoDefesaDatabaseRow>): List<Pedido_Defesa> {
        val ret : MutableList<Pedido_Defesa> = mutableListOf()

        for (obj in objs) {
            ret.add(single(obj))
        }

        return ret
    }

}