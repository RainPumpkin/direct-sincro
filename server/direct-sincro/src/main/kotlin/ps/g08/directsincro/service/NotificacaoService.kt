package ps.g08.directsincro.service

import nl.martijndwars.webpush.PushService
import nl.martijndwars.webpush.Utils
import nl.martijndwars.webpush.Notification
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Notificacao
import ps.g08.directsincro.database.NotificacaoDatabase
import ps.g08.directsincro.service.mappers.NotificacaoMapper
import java.security.Security

data class PushSubscriptionCredentials(
    val endpoint: String,
    val keys: Keys
)

data class Keys(
    val p256dh: String,
    val auth: String
)

@Component
class NotificacaoService(private val db: NotificacaoDatabase, private val mapper: NotificacaoMapper) {
    fun getAllNotificacoes(nif: String) : List<Notificacao>{
        return mapper.multiple(db.getAll(nif))
    }

    fun getNotificacao(subscritor: String, emitida: Long) : Notificacao {
        return mapper.single(db.get(subscritor, emitida))
    }

    fun send(push: PushSubscriptionCredentials) {
        val pub = "BIclYkmaTdoEO6e_jVORV9RH8bACZmVBe4iQCtQga_M4iBCUsICPoTXPs0cdFtutsoF-UEOg1Z11-Z4qd8ta52E"
        val priv = "2JbWmuVjCsHehnKiTah-X_uG4gHfnJLBgWwLV9mi2Yo"
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(BouncyCastleProvider())
        }
        val noti = Notification(
            push.endpoint,push.keys.p256dh,push.keys.auth,
            "teste"
            )
        val service = PushService()
        service.publicKey = Utils.loadPublicKey(pub)
        service.privateKey = Utils.loadPrivateKey(priv)
        service.send(noti)
    }

    fun createNotification(notificacao: Notificacao, subscritor: String, contraordenacao: String) : Int{
        return db.create(notificacao, subscritor, contraordenacao)
    }
}