package ps.g08.directsincro.controller

import jdk.jshell.spi.ExecutionControl.NotImplementedException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ps.g08.directsincro.common.responseOkWithBody
import ps.g08.directsincro.controller.inputmodels.ContraordenacaoInputModel
import ps.g08.directsincro.controller.inputmodels.getContraordenacaoFromContraordenacaoInputModel
import ps.g08.directsincro.controller.outputmodel.getMultipleContraordenacaoOutputModel
import ps.g08.directsincro.service.ContraordenacaoService
import java.net.URI

@RestController
@RequestMapping("/api")
class ContraordenacaoController(
    private val service: ContraordenacaoService
) {
    //Evento proveniente do SIGET
    @CrossOrigin
    @PostMapping("/contraordenacoes")
    fun receiveEvento(@RequestBody input : ContraordenacaoInputModel) : ResponseEntity<Any> {
        service.createContraordenacao(getContraordenacaoFromContraordenacaoInputModel(input), input.evento.dadosDoVeiculo.matricula)
        return responseOkWithBody(responseOkWithBody("Evento adicionado com sucesso"))
    }

    //subscritores/nif/veiculos/matricula/eventos ->eventos de transito do meu veiculo
    //subscritores/nif/alugados/matricula/eventos ->eventos de transito do veiculo alugado

    @CrossOrigin
    @PostMapping("/subscritores/{nif}/veiculos/{matricula}/contraordenacoes")
    fun createEventoVeiculo(@RequestBody input: ContraordenacaoInputModel, @PathVariable nif: String, @PathVariable matricula: String) : ResponseEntity<Any> {
        val numeroAuto = service.createContraordenacao(getContraordenacaoFromContraordenacaoInputModel(input), matricula)
        return ResponseEntity.created(URI.create("/api/subscritores/${nif}/veiculos/${matricula}/eventos/${numeroAuto}")).build()
    }

    @CrossOrigin
    @GetMapping("/subscritores/{nif}/veiculos/{matricula}/contraordenacoes")
    fun getAllEventosVeiculo(@PathVariable matricula: String) : ResponseEntity<Any>{
        return responseOkWithBody(getMultipleContraordenacaoOutputModel(service.getAllContraordenacoes(matricula)))
    }

    @CrossOrigin
    @PostMapping("/subscritores/{nif}/alugados/{matricula}/contraordenacoes")
    fun createEventoAlugado(@RequestBody input: ContraordenacaoInputModel, @PathVariable nif: String, @PathVariable matricula: String) : ResponseEntity<Any> {
        val numeroAuto = service.createContraordenacao(getContraordenacaoFromContraordenacaoInputModel(input), matricula)
        return ResponseEntity.created(URI.create("/api/subscritores/${nif}/alugados/${matricula}/eventos/${numeroAuto}")).build()
    }

    @CrossOrigin
    @GetMapping("/subscritores/{nif}/alugados/{matricula}/contraordenacoes")
    fun getAllEventosAlugado() : ResponseEntity<Any>{
        //retorna apenas eventos entre datainicio e datafim do emprestimo
        throw NotImplementedException("getalleventos");
    }
}