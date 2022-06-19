package ps.g08.directsincro.controller

import jdk.jshell.spi.ExecutionControl.NotImplementedException
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ps.g08.directsincro.common.responseOkWithBody
import ps.g08.directsincro.controller.inputmodels.EventoTransitoInputModel
import ps.g08.directsincro.controller.inputmodels.getEventoFromEventoTransitoInputModel
import ps.g08.directsincro.controller.outputmodel.getMultipleEventoOutputModel
import ps.g08.directsincro.service.EventoTransitoService
import java.net.URI

@RestController
@RequestMapping("/api")
class EventoTransitoController(
    private val service: EventoTransitoService
) {
    //Evento proveniente do SIGET
    @CrossOrigin
    @PostMapping("/eventos")
    fun receiveEvento(@RequestBody input : EventoTransitoInputModel) : ResponseEntity<Any> {
        service.createEvento(getEventoFromEventoTransitoInputModel(input), input.evento.dadosDoVeiculo.matricula)
        return responseOkWithBody(responseOkWithBody("Evento adicionado com sucesso"))
    }

    //subscritores/nif/veiculos/matricula/eventos ->eventos de transito do meu veiculo
    //subscritores/nif/alugados/matricula/eventos ->eventos de transito do veiculo alugado

    @CrossOrigin
    @PostMapping("/subscritores/{nif}/veiculos/{matricula}/eventos")
    fun createEventoVeiculo(@RequestBody input: EventoTransitoInputModel, @PathVariable nif: String, @PathVariable matricula: String) : ResponseEntity<Any> {
        val numeroAuto = service.createEvento(getEventoFromEventoTransitoInputModel(input), matricula)
        return ResponseEntity.created(URI.create("/api/subscritores/${nif}/veiculos/${matricula}/eventos/${numeroAuto}")).build()
    }

    @CrossOrigin
    @GetMapping("/subscritores/{nif}/veiculos/{matricula}/eventos")
    fun getAllEventosVeiculo(@PathVariable matricula: String) : ResponseEntity<Any>{
        return responseOkWithBody(getMultipleEventoOutputModel(service.getAllEventos(matricula)))
    }

    @CrossOrigin
    @PostMapping("/subscritores/{nif}/alugados/{matricula}/eventos")
    fun createEventoAlugado(@RequestBody input: EventoTransitoInputModel, @PathVariable nif: String, @PathVariable matricula: String) : ResponseEntity<Any> {
        val numeroAuto = service.createEvento(getEventoFromEventoTransitoInputModel(input), matricula)
        return ResponseEntity.created(URI.create("/api/subscritores/${nif}/alugados/${matricula}/eventos/${numeroAuto}")).build()
    }

    @CrossOrigin
    @GetMapping("/subscritores/{nif}/alugados/{matricula}/eventos")
    fun getAllEventosAlugado() : ResponseEntity<Any>{
        //retorna apenas eventos entre datainicio e datafim do emprestimo
        throw NotImplementedException("getalleventos");
    }
}