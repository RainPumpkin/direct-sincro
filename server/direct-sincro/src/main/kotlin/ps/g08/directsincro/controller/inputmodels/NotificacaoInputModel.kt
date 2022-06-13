package ps.g08.directsincro.controller.inputmodels

import ps.g08.directsincro.common.Notificacao

data class NotificacaoInputModel(
    val emitida: Boolean,
    val mensagem: String,
    val recebida: Boolean,
    val tipo: String
)

fun getNotificacaoFromNotificacaoInputModel(input : NotificacaoInputModel) : Notificacao{
    return Notificacao(
        emitida = input.emitida,
        mensagem = input.mensagem,
        id = 0,
        recebida = input.recebida,
        tipo = input.tipo
    )
}