package ps.g08.directsincro.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ps.g08.directsincro.common.PushSubscription
import ps.g08.directsincro.controller.inputmodels.PushSubscriptionInputModel
import ps.g08.directsincro.controller.inputmodels.getPushSubscriptionFromInputModel
import ps.g08.directsincro.service.NotificacaoService
import ps.g08.directsincro.service.PushSubscriptionCredentials
import ps.g08.directsincro.service.PushSubscriptionService

@RestController
@RequestMapping("/api/subscritores")
class PushNotificationController(private val pushSubscriptionService: PushSubscriptionService) {
    @CrossOrigin
    @PostMapping("/{nif}/push")
    fun createSubsciptionPushNotification(@PathVariable nif: String, @RequestBody input : PushSubscriptionInputModel) : ResponseEntity<Any> {
        pushSubscriptionService.createPushSubscription(PushSubscription(nif = nif, endpoint = input.endpoint, publicKey = input.keys.p256dh , auth = input.keys.auth))
        return ResponseEntity.ok().build()
    }

    @CrossOrigin
    @DeleteMapping("/{nif}/push")
    fun deleteSubsciptionPushNotification(@PathVariable nif: String, @RequestBody input : PushSubscriptionInputModel) : ResponseEntity<Any> {
            pushSubscriptionService.deletePushSubscription(PushSubscription(nif = nif, endpoint = input.endpoint, publicKey = input.keys.p256dh , auth = input.keys.auth))
        return ResponseEntity.ok().build()
    }
}