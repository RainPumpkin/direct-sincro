package ps.g08.directsincro.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ps.g08.directsincro.common.responseOkWithBody
import ps.g08.directsincro.controller.outputmodel.getEmprestimoMatriculaOutputModel
import ps.g08.directsincro.service.EmprestimoUsuarioService

@RestController
@RequestMapping("/api/subscritores/{nif}/emprestimos")
class EmprestimoUsuarioController(private val emprestimoUsuarioService: EmprestimoUsuarioService) {
    //get de emprestimos passados e aguardar

    @CrossOrigin
    @GetMapping//emprestimos?matricula=string&datainicio=long
    fun getemprestimo(@RequestParam matricula: String, @RequestParam datainicio: Long): ResponseEntity<Any> {
        return responseOkWithBody(getEmprestimoMatriculaOutputModel(emprestimoUsuarioService.getEmprestimoWithDate(matricula, datainicio)))
    }
    //path para transformar emprestimo aguardar em emprestado
}
/*
GET all
/api/subscritores/{nif}/alugados
lista de veiculos emprestados a este usuário (output sem usuario)
Query:
Select * from
join de veiculo e emprestimo no parametro veiculo/matricula
where usuario = {nif}
*/