package ps.g08.directsincro.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ps.g08.directsincro.service.EmprestimoMatriculaService

@RestController
@RequestMapping("/api/subscritores/{nif}/veiculos/{matricula}/emprestimos")
class EmprestimoMatriculaController(private val emprestimoMatriculaService: EmprestimoMatriculaService) {
    //get e put
    @GetMapping //emprestimo?matricula=string&datainicio=long
    fun getemprestimo(@RequestParam matricula: String, @RequestParam datainicio: Long){

    }
}