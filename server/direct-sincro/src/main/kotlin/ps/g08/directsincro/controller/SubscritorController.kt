package ps.g08.directsincro.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ps.g08.directsincro.common.responseOkWithBody
import ps.g08.directsincro.controller.inputmodels.SubscritorInputModel
import ps.g08.directsincro.controller.inputmodels.getSubscritorPessoaFromSubscritorInputModel
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

    //create recebe tudo de pessoa+sub
    @CrossOrigin
    @PostMapping
    fun createSubscritor(@RequestBody input : SubscritorInputModel) : ResponseEntity<Any>{
        val id : String = subscritorService.createSubscritor(getSubscritorPessoaFromSubscritorInputModel(input))
        return ResponseEntity.created(URI.create("/api/subscritores/${id}")).build()
    }
}