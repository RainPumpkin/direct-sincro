package ps.g08.directsincro.service

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Notificacao
import ps.g08.directsincro.database.NotificacaoDatabase
import ps.g08.directsincro.service.mappers.NotificacaoMapper

@Component
class NotificacaoService(private val db: NotificacaoDatabase, private val mapper: NotificacaoMapper) {
    fun getAllNotificacoes(nif: String) : List<Notificacao>{
        return mapper.multiple(db.getAll(nif))
    }

    fun getNotificacao(id: Int) : Notificacao {
        return mapper.single(db.get(id))
    }

    fun createNotification(notificacao: Notificacao, subscritor: String) : Int{
        return db.create(notificacao, subscritor)
    }
}