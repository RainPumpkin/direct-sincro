package ps.g08.directsincro.controller

import org.springframework.web.bind.annotation.*
import ps.g08.directsincro.controller.inputmodels.EventoTransitoInputModel

@RestController
@RequestMapping("/api")
class EventoTransitoController() {

    //Evento proveniente do SIGET
    @PostMapping("/eventos")
    fun receiveEvento(@RequestBody input : EventoTransitoInputModel) {

    }

    //subscritores/nif/veiculos/matricula/eventos ->eventos de transito do meu veiculo
    //subscritores/nif/alugados/matricula/eventos ->eventos de transito do veiculo alugado
}