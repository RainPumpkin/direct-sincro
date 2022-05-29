package ps.g08.directsincro.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ps.g08.directsincro.common.Veiculo
import ps.g08.directsincro.controller.outputmodel.getMultipleVeiculoModel
import ps.g08.directsincro.service.VeiculoService

@RestController
@RequestMapping("/api/subscritores/{nif}/veiculos")
class VeiculoController(
    private val service: VeiculoService
) {
    @PutMapping("/{matricula}")//recebe input model
    fun putveiculo(@PathVariable nif: String, @PathVariable matricula: String){

    }

    @GetMapping
    fun getAllVeiculos(@PathVariable nif: String) : ResponseEntity<Any>{
        val veiculos : List<Veiculo> = service.getAllVeiculos(nif)
        return ResponseEntity<Any>(getMultipleVeiculoModel(veiculos), HttpStatus.OK)
    }
}