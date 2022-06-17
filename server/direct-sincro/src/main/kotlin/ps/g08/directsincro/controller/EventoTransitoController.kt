package ps.g08.directsincro.controller

import jdk.jshell.spi.ExecutionControl.NotImplementedException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ps.g08.directsincro.controller.inputmodels.EventoTransitoInputModel
import ps.g08.directsincro.controller.inputmodels.getEventoFromEventoTransitoInputModel
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
    fun receiveEvento(@RequestBody input : EventoTransitoInputModel) {

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
    fun getAllEventosVeiculo() : ResponseEntity<Any>{
        //retorna todos os eventos do veiculo
        throw NotImplementedException("getalleventos");
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