package ps.g08.directsincro.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/subscritores/{nif}/emprestimos")
class EmprestimoUsuarioController {
    //get de emprestimos passados e aguardar

    @GetMapping//emprestimos?matricula=string&datainicio=long
    fun getemprestimo(@RequestParam matricula: String, @RequestParam datainicio: Long){

    }
    //path para transformar emprestimo aguardar em emprestado
}