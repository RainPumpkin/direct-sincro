package ps.g08.directsincro.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ps.g08.directsincro.common.responseOk
import ps.g08.directsincro.common.PushSubscription
import ps.g08.directsincro.common.responseOkWithBody
import ps.g08.directsincro.controller.inputmodels.SubscritorInputModel
import ps.g08.directsincro.controller.inputmodels.getSubscritorPessoaFromSubscritorInputModel
import ps.g08.directsincro.service.NotificacaoService
import ps.g08.directsincro.service.SubscritorService
import java.net.URI


@RestController
@RequestMapping("/api/subscritores")
class SubscritorController(private val subscritorService: SubscritorService) {

    @CrossOrigin
    @GetMapping("/{nif}")
    fun getSubscritor(@PathVariable nif: String): ResponseEntity<Any>{
        return responseOkWithBody(subscritorService.getSubscritor(nif))
    }

    @CrossOrigin
    @GetMapping("/{nif}/date")
    fun getDateOfSubscription(@PathVariable nif: String): ResponseEntity<Any>{
        return responseOkWithBody(subscritorService.getDateOfSubscription(nif))
    }

    //create recebe tudo de pessoa+sub
    @CrossOrigin
    @PostMapping
    fun createSubscritor(@RequestBody input : SubscritorInputModel) : ResponseEntity<Any>{
        val id : String = subscritorService.createSubscritor(getSubscritorPessoaFromSubscritorInputModel(input))
        return ResponseEntity.created(URI.create("/api/subscritores/${id}")).build()
    }

    @CrossOrigin
    @PostMapping("/{nif}/deactivate")
    fun cancelarSubscricao(@PathVariable nif: String, @RequestBody dataInicio: Long): ResponseEntity<Any>{
        subscritorService.cancelSub(nif, dataInicio)
        return responseOk()
    }

    @CrossOrigin
    @PostMapping("/{nif}/activate")
    fun activarSubscricao(@PathVariable nif: String): ResponseEntity<Any>{
        subscritorService.subscribe(nif)
        return responseOk()
    }
}