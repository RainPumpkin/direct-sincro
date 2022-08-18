package ps.g08.directsincro.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ps.g08.directsincro.common.responseOkWithBody
import ps.g08.directsincro.controller.outputmodel.getAllDelegacaoSubscritorOutputModel
import ps.g08.directsincro.controller.outputmodel.getDelegacaoSubscritorOutputModel
import ps.g08.directsincro.service.DelegacaoSubscritorService

@RestController
@RequestMapping("/api/subscritores/{nif}")
class DelegacaoSubscritorController(private val delegacaoSubscritorService: DelegacaoSubscritorService) {
    //get de emprestimos passados e aguardar

    @CrossOrigin
    @GetMapping("/delegacoes")//emprestimos?matricula=string&datainicio=long
    fun getDelegacao(@PathVariable nif: String, @RequestParam matricula: String, @RequestParam datainicio: Long): ResponseEntity<Any> {
        return responseOkWithBody(getDelegacaoSubscritorOutputModel(delegacaoSubscritorService.getDelegacaoWithDate(nif ,matricula, datainicio)))
    }

    @CrossOrigin
    @GetMapping("/delegados")
    fun getAllAlugados(@PathVariable nif: String): ResponseEntity<Any>{
        return responseOkWithBody(getAllDelegacaoSubscritorOutputModel(delegacaoSubscritorService.getAllDelegacoesSubscritor(nif)))
    }
    //path para transformar emprestimo aguardar em emprestado
}
