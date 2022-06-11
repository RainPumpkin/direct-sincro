package ps.g08.directsincro.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ps.g08.directsincro.common.responseOkWithBody
import ps.g08.directsincro.service.EmprestimoMatriculaService

@RestController
@RequestMapping("/api/subscritores/{nif}/veiculos/{matricula}/emprestimos")
class EmprestimoMatriculaController(private val emprestimoMatriculaService: EmprestimoMatriculaService) {

    @GetMapping("/{datainicio}") //emprestimo?matricula=string&datainicio=long
    fun getemprestimo(@PathVariable matricula: String, @PathVariable datainicio: Long): ResponseEntity<Any>{
        return responseOkWithBody(emprestimoMatriculaService.getEmprestimoMatricula(matricula, datainicio))
    }

    @GetMapping
    fun getAllEmprestimo(@PathVariable matricula: String): ResponseEntity<Any>{
        return responseOkWithBody(emprestimoMatriculaService.getAllEmprestimosMatricula(matricula))
    }

    //post emprestimo
    //inputmodel com: usuario que recebe, datainicio, datafim.
}