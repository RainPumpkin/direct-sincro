package ps.g08.directsincro.service.mappers

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.PushSubscription
import ps.g08.directsincro.database.PushSubscriptionDatabaseRow
import ps.g08.directsincro.service.PushSubscriptionService

@Component
class PushSubscriptionMapper : IMapper<PushSubscriptionDatabaseRow, PushSubscription> {
    override fun single(obj: PushSubscriptionDatabaseRow): PushSubscription {
        return PushSubscription(
            nif = obj.nif,
            endpoint = obj.endpoint,
            publicKey = obj.publicKey,
            auth = obj.auth
        )
    }

    override fun multiple(objs: List<PushSubscriptionDatabaseRow>): List<PushSubscription> {
        val ret : MutableList<PushSubscription> = mutableListOf()
        for (obj in objs) {
            ret.add(single(obj))
        }
        return ret
    }

}