package ps.g08.directsincro.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ps.g08.directsincro.common.responseOkWithBody
import ps.g08.directsincro.controller.outputmodel.getAllEmprestimosUsuarioOutputModel
import ps.g08.directsincro.controller.outputmodel.getEmprestimoUsuarioOutputModel
import ps.g08.directsincro.service.EmprestimoUsuarioService

@RestController
@RequestMapping("/api/subscritores/{nif}")
class EmprestimoUsuarioController(private val emprestimoUsuarioService: EmprestimoUsuarioService) {
    //get de emprestimos passados e aguardar

    @CrossOrigin
    @GetMapping("/emprestimos")//emprestimos?matricula=string&datainicio=long
    fun getemprestimo(@PathVariable nif: String, @RequestParam matricula: String, @RequestParam datainicio: Long): ResponseEntity<Any> {
        return responseOkWithBody(getEmprestimoUsuarioOutputModel(emprestimoUsuarioService.getEmprestimoWithDate(nif ,matricula, datainicio)))
    }

    @CrossOrigin
    @GetMapping("/alugados")
    fun getAllAlugados(@PathVariable nif: String): ResponseEntity<Any>{
        return responseOkWithBody(getAllEmprestimosUsuarioOutputModel(emprestimoUsuarioService.getAllEmprestimosUsuario(nif)))
    }
    //path para transformar emprestimo aguardar em emprestado
}
