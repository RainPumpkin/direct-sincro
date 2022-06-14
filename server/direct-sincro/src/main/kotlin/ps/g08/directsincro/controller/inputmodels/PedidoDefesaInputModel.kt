package ps.g08.directsincro.controller.inputmodels

import ps.g08.directsincro.common.Pedido_Defesa

data class PedidoDefesaInputModel(
    val moradaSede: String,
    val justificacao: String,
    val numero_conducao: String
)

fun getPedidoFromPedidoDefesaInputModel(input: PedidoDefesaInputModel): Pedido_Defesa{
    return Pedido_Defesa(
        id = 0,
        moradaSede = input.moradaSede,
        justificacao = input.justificacao,
        numero_conducao = input.numero_conducao,
    )
}