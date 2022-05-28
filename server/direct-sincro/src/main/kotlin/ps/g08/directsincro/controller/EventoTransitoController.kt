package ps.g08.directsincro.controller

import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ps.g08.directsincro.controller.inputmodels.EventoTransitoInputModel

@RestController
@RequestMapping("/api")
class EventoTransitoController() {

    //Evento proveniente do SIGET
    @PutMapping("/evento")
    fun receiveEvento(@RequestBody input : EventoTransitoInputModel) {

    }

    //subscritor/nif/veiculo/matricula/evento/numeroAuto ->eventos de transito do meu veiculo
    //subscritor/nif/alugado/matricula/evento/numeroAuto ->eventos de transito do veiculo alugado
}