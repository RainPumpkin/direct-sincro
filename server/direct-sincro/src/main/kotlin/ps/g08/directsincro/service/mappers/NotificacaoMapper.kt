package ps.g08.directsincro.service.mappers

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Notificacao
import ps.g08.directsincro.database.NotificacaoDatabaseRow

@Component
class NotificacaoMapper : IMapper<NotificacaoDatabaseRow, Notificacao>{
    override fun single(obj: NotificacaoDatabaseRow): Notificacao {
        return Notificacao(
            emitida = obj.emitida.time,
            mensagem = obj.mensagem,
            visualizada = obj.visualizada,
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