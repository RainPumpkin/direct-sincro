package ps.g08.directsincro.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ps.g08.directsincro.common.responseOkWithBody
import ps.g08.directsincro.service.NotificacaoService
import java.net.URI

@RestController
@RequestMapping("/api/subscritores/{nif}/notificacoes")
class NotificacaoController(private val notificacaoService: NotificacaoService) {

    @CrossOrigin
    @GetMapping("/{emitida}")
    fun getsinglenotification(@PathVariable nif: String, @PathVariable emitida: Long) : ResponseEntity<Any> {
        return responseOkWithBody(notificacaoService.getNotificacao(nif, emitida))
    }

    @CrossOrigin
    @GetMapping
    fun getallnotifications(@PathVariable nif: String) : ResponseEntity<Any> {
        return responseOkWithBody(notificacaoService.getAllNotificacoes(nif))
    }

    /*@CrossOrigin
    @PostMapping
    fun createNotification(@RequestBody input: NotificacaoInputModel, @PathVariable nif: String) : ResponseEntity<Any>{
        val emitida = notificacaoService.createNotification(getNotificacaoFromNotificacaoInputModel(input), nif)
        return ResponseEntity.created(URI.create("/api/subscritores/${nif}/notificacoes/${emitida}")).build()
    }*/
}