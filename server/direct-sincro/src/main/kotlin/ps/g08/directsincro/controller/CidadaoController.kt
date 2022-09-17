package ps.g08.directsincro.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ps.g08.directsincro.controller.inputmodels.SubscritorInputModel
import ps.g08.directsincro.controller.inputmodels.getSubscritorPessoaFromSubscritorInputModel
import ps.g08.directsincro.service.CidadaoService
import ps.g08.directsincro.service.EmailSenderService
import ps.g08.directsincro.service.SubscritorService

@RestController
class CidadaoController(private val subscritorService: SubscritorService, private val emailService: EmailSenderService) {

    @PostMapping("/register")
    fun sendSimpleEmail(
        @RequestBody input: SubscritorInputModel
    ): ResponseEntity<Any> {
        subscritorService.createSubscritor(getSubscritorPessoaFromSubscritorInputModel(input))
        emailService.sendEmail(
            subject = "Registo no sistema DIRECT-SINCRO.",
            targetEmail = input.email,
            text = "Conta registada com sucesso pode realizar o login http://localhost:3001/login"
        )
        return ResponseEntity.ok().build()
    }
}