package ps.g08.directsincro.controller

import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ps.g08.directsincro.database.inputmodels.EventoTransitoInputModel

@RestController
@RequestMapping("/api")
class EventoTransitoController() {

    //Evento proveniente do SIGET
    @PutMapping("/evento")
    fun receiveEvento(@RequestBody input : EventoTransitoInputModel) {

    }
}