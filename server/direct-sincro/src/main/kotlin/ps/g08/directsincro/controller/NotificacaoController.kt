package ps.g08.directsincro.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ps.g08.directsincro.common.responseOkWithBody
import ps.g08.directsincro.service.NotificacaoService

@RestController
@RequestMapping("/api/subscritores/{nif}/notificacoes")
class NotificacaoController(private val notificacaoService: NotificacaoService) {

    @GetMapping("/{id}")
    fun getsinglenotification(@PathVariable nif: String, @PathVariable id: Int) : ResponseEntity<Any> {
        return responseOkWithBody(notificacaoService.getNotificacao(id))
    }

    @GetMapping
    fun getallnotifications(@PathVariable nif: String) : ResponseEntity<Any> {
        return responseOkWithBody(notificacaoService.getAllNotificacoes(nif))
    }
}