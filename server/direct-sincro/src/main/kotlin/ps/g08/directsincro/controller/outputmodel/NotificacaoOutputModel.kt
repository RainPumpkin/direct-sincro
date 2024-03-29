package ps.g08.directsincro.controller.outputmodel

import ps.g08.directsincro.common.Notificacao

data class NotificacaoOutputModel(
    val emitida: Long,
    val mensagem: String,
    val visualizada: Boolean,
    val tipo: String,
    val contraordenacao: String
)

data class MultipleNotificacaoOutputModel(
    val notificacoes: List<NotificacaoOutputModel>,
    val number: Int
)

fun getNotificacaoFromOutputModel(notificacao: Notificacao) : NotificacaoOutputModel {
    return NotificacaoOutputModel(
        emitida = notificacao.emitida,
        mensagem = notificacao.mensagem,
        visualizada = notificacao.visualizada,
        tipo = notificacao.tipo,
        contraordenacao = notificacao.contraordenacao
    )
}

fun getMultipleNotificacaoOutputModel(notificacoes: List<Notificacao>): MultipleNotificacaoOutputModel {
    val result = mutableListOf<NotificacaoOutputModel>()

    for (notificacao in notificacoes) {
        result.add(getNotificacaoFromOutputModel(notificacao))
    }

    return MultipleNotificacaoOutputModel(notificacoes = result, number = result.size)
}