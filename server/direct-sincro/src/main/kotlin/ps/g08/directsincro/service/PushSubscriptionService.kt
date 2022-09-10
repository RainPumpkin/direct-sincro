package ps.g08.directsincro.service

import nl.martijndwars.webpush.Notification
import nl.martijndwars.webpush.PushService
import nl.martijndwars.webpush.Utils
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.springframework.stereotype.Component
import ps.g08.directsincro.common.PushSubscription
import ps.g08.directsincro.database.PushSubscriptionDatabase
import ps.g08.directsincro.service.mappers.PushSubscriptionMapper
import java.security.Security

@Component
class PushSubscriptionService(
    private val pushDb: PushSubscriptionDatabase,
    private val pushMapper: PushSubscriptionMapper
) {
    fun createPushSubscription(pushSub: PushSubscription) {
        pushDb.createPushSubscription(pushSub.nif, pushSub.endpoint, pushSub.publicKey, pushSub.auth)
    }

    fun deletePushSubscription(pushSub: PushSubscription) {
        pushDb.deletePushSubscription(pushSub.nif, pushSub.endpoint)
    }

    fun getPushCredentials(nif: String): List<PushSubscription> {
        return pushMapper.multiple(pushDb.getPushCredentials(nif))
    }

    fun send(push: PushSubscription, mensagem: String) {
        val pub = "BIclYkmaTdoEO6e_jVORV9RH8bACZmVBe4iQCtQga_M4iBCUsICPoTXPs0cdFtutsoF-UEOg1Z11-Z4qd8ta52E"
        val priv = "2JbWmuVjCsHehnKiTah-X_uG4gHfnJLBgWwLV9mi2Yo"
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(BouncyCastleProvider())
        }
        val noti = Notification(
            push.endpoint,push.publicKey,push.auth,mensagem
        )
        val service = PushService()
        service.publicKey = Utils.loadPublicKey(pub)
        service.privateKey = Utils.loadPrivateKey(priv)
        service.send(noti)
    }
}
