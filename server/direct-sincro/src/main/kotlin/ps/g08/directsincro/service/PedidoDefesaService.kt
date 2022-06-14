package ps.g08.directsincro.service

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Pedido_Defesa
import ps.g08.directsincro.database.PedidoDefesaDatabase
import ps.g08.directsincro.service.mappers.PedidoDefesaMapper

@Component
class PedidoDefesaService (
    private val db: PedidoDefesaDatabase,
    private val mapper: PedidoDefesaMapper){
    fun getPedido(requeridor: String, id: Int): Pedido_Defesa{
        return mapper.single(db.get(requeridor, id))
    }

    fun createPedido(pedidoDefesa: Pedido_Defesa, numeroAuto: String, requeridor: String): Int{
        return db.create(pedidoDefesa, numeroAuto, requeridor)
    }
}