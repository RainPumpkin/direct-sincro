package ps.g08.directsincro.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ps.g08.directsincro.common.responseOkWithBody
import ps.g08.directsincro.controller.inputmodels.EmprestimoInputModel
import ps.g08.directsincro.controller.inputmodels.getEmprestimoFromInputModel
import ps.g08.directsincro.controller.outputmodel.getEmprestimoMatriculaOutputModel
import ps.g08.directsincro.service.EmprestimoMatriculaService
import java.net.URI

@RestController
@RequestMapping("/api/subscritores/{nif}/veiculos/{matricula}/emprestimos")
class EmprestimoMatriculaController(private val emprestimoMatriculaService: EmprestimoMatriculaService) {

    @CrossOrigin
    @GetMapping//emprestimo?matricula=string&datainicio=long
    fun getemprestimo(@PathVariable matricula: String, @RequestParam(value = "datainicio", required = false) datainicio: Long?): ResponseEntity<Any>{
        if (datainicio == null)
            return responseOkWithBody(emprestimoMatriculaService.getAllEmprestimosMatricula(matricula))
        return responseOkWithBody(getEmprestimoMatriculaOutputModel(emprestimoMatriculaService.getEmprestimoMatricula(matricula, datainicio)))
    }

    //post emprestimo
    //inputmodel com: usuario que recebe, datainicio, datafim.

    @CrossOrigin
    @PostMapping
    fun createEmprestimo(@RequestBody input : EmprestimoInputModel, @PathVariable nif : String, @PathVariable matricula: String) : ResponseEntity<Any>{
        emprestimoMatriculaService.createEmprestimo(getEmprestimoFromInputModel(input), matricula)
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