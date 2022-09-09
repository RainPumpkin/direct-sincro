package ps.g08.directsincro.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ps.g08.directsincro.common.responseOk
import ps.g08.directsincro.common.PushSubscription
import ps.g08.directsincro.common.responseOkWithBody
import ps.g08.directsincro.controller.inputmodels.SubscritorInputModel
import ps.g08.directsincro.controller.inputmodels.getSubscritorPessoaFromSubscritorInputModel
import ps.g08.directsincro.service.Keys
import ps.g08.directsincro.service.NotificacaoService
import ps.g08.directsincro.service.PushSubscriptionCredentials
import ps.g08.directsincro.service.SubscritorService
import java.net.URI


@RestController
@RequestMapping("/api/subscritores")
class SubscritorController(private val subscritorService: SubscritorService, private val notificacaoService: NotificacaoService) {

    @CrossOrigin
    @GetMapping("/{nif}")
    fun getSubscritor(@PathVariable nif: String): ResponseEntity<Any>{
        return responseOkWithBody(subscritorService.getSubscritor(nif))
    }

    //create recebe tudo de pessoa+sub
    @CrossOrigin
    @PostMapping
    fun createSubscritor(@RequestBody input : SubscritorInputModel) : ResponseEntity<Any>{
        val id : String = subscritorService.createSubscritor(getSubscritorPessoaFromSubscritorInputModel(input))
        return ResponseEntity.created(URI.create("/api/subscritores/${id}")).build()
    }

    @CrossOrigin
    @DeleteMapping("/{nif}/push")
    fun cancelarSubscricao(@PathVariable nif: String): ResponseEntity<Any>{
        subscritorService.cancelSub(nif)
        return responseOk()
    }

    @CrossOrigin
    @PostMapping("/{nif}")
    fun iniciarSubscricao(@PathVariable nif: String): ResponseEntity<Any>{
        subscritorService.subscribe(nif)
        return responseOk()
    }

    @CrossOrigin
    @PostMapping("/{nif}")
    fun createSubsciptionPushNotification(@PathVariable nif: String, @RequestBody input : PushSubscriptionCredentials) : ResponseEntity<Any>{
        subscritorService.createPushSubscription(PushSubscription(nif = nif, endpoint = input.endpoint, publicKey = input.keys.p256dh, auth = input.keys.auth))
        notificacaoService.send(input)
        return ResponseEntity.ok().build()
    }
}