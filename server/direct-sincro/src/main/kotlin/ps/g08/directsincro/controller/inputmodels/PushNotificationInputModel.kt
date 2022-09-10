package ps.g08.directsincro.controller.inputmodels

import ps.g08.directsincro.common.PushSubscription

data class PushSubscriptionInputModel(
    val endpoint: String,
    val keys: Key
)

data class Key(
    val p256dh: String,
    val auth: String
)

fun getPushSubscriptionFromInputModel(input: PushSubscriptionInputModel, nif: String): PushSubscription {
    return PushSubscription(
        nif = nif,
        endpoint = input.endpoint,
        publicKey = input.keys.p256dh,
        auth = input.keys.auth
    )
}