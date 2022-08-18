package ps.g08.directsincro.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ps.g08.directsincro.common.responseOkWithBody
import ps.g08.directsincro.controller.inputmodels.DelegacaoInputModel
import ps.g08.directsincro.controller.inputmodels.getDelegacaoFromInputModel
import ps.g08.directsincro.controller.outputmodel.getDelegacaoVeiculoOutputModel
import ps.g08.directsincro.service.DelegacaoVeiculoService
import java.net.URI

@RestController
@RequestMapping("/api/subscritores/{nif}/veiculos/{matricula}/delegacoes")
class DelegacaoVeiculoController(private val delegacaoVeiculoService: DelegacaoVeiculoService) {

    @CrossOrigin
    @GetMapping//emprestimo?matricula=string&datainicio=long
    fun getdelegacao(@PathVariable nif: String, @PathVariable matricula: String, @RequestParam(value = "datainicio", required = false) datainicio: Long?): ResponseEntity<Any>{
        if (datainicio == null)
            return responseOkWithBody(delegacaoVeiculoService.getAllDelegacaoVeiculo(matricula))
        return responseOkWithBody(getDelegacaoVeiculoOutputModel(delegacaoVeiculoService.getDelegacaoVeiculo(nif, matricula, datainicio)))
    }

    //post emprestimo
    //inputmodel com: usuario que recebe, datainicio, datafim.

    @CrossOrigin
    @PostMapping
    fun createDelegacao(@RequestBody input : DelegacaoInputModel, @PathVariable nif : String, @PathVariable matricula: String) : ResponseEntity<Any>{
        delegacaoVeiculoService.createDelegacao(getDelegacaoFromInputModel(input), matricula)
        return ResponseEntity.created(URI.create("api/subscritores/${nif}/veiculos/${matricula}/emprestimos")).build()
    }
}

/*
GET e POST
/api/subscritores/{nif}/veiculos/{matricula}/emprestimos
lista de emprestimos feitos deste carro a x pessoa (output sem info da tabela veiculo)
Query:
Select * from
join de veiculo e emprestimo no parametro veiculo/matricula
where matricula = {matricula}
 */