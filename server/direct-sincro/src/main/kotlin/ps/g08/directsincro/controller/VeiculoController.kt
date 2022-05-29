package ps.g08.directsincro.controller

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ps.g08.directsincro.controller.inputmodels.EventoTransitoInputModel

@RestController
@RequestMapping("/api/subscritores/{nif}/veiculos")
class VeiculoController {
    @PutMapping("/{matricula}")
    fun putveiculo(@PathVariable id: String, @PathVariable matricula: String, @RequestBody details: EventoTransitoInputModel){

    }
}