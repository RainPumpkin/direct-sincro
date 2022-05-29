package ps.g08.directsincro.service.mappers

import ps.g08.directsincro.common.Notificacao
import ps.g08.directsincro.common.getEpoch
import ps.g08.directsincro.database.NotificacaoDatabaseRow

class NotificacaoMapper : IMapper<NotificacaoDatabaseRow, Notificacao>{
    override fun single(obj: NotificacaoDatabaseRow): Notificacao {
        return Notificacao(
            emitida = obj.emitida,
            mensagem = obj.mensagem,
            id = obj.id,
            recebida = obj.recebida,
            tipo = obj.tipo
        )
    }

    override fun multiple(objs: List<NotificacaoDatabaseRow>): List<Notificacao> {
        val ret : MutableList<Notificacao> = mutableListOf()

        for (obj in objs) {
            ret.add(single(obj))
        }

        return ret
    }

}