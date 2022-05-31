package ps.g08.directsincro.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ps.g08.directsincro.service.NotificacaoService

@RestController
@RequestMapping("/api/subscritores/{nif}/notificacoes")
class NotificacaoController(private val notificacaoService: NotificacaoService) {

    @GetMapping("/{id}")
    fun getsinglenotification(){

    }

    @GetMapping
    fun getallnotifications(){

    }
}