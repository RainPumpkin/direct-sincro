package ps.g08.directsincro.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ps.g08.directsincro.common.Veiculo
import ps.g08.directsincro.controller.inputmodels.VeiculoInputModel
import ps.g08.directsincro.controller.inputmodels.getVeiculoFromVeiculoInputModel
import ps.g08.directsincro.controller.outputmodel.getMultipleVeiculoModel
import ps.g08.directsincro.service.VeiculoService
import java.net.URI

@RestController
@RequestMapping("/api/subscritores/{nif}/veiculos")
class VeiculoController(
    private val service: VeiculoService
) {
    @PostMapping()//recebe input model
    fun putveiculo(@RequestBody input : VeiculoInputModel, @PathVariable nif: String): ResponseEntity<Any>{
        val matricula = service.createVeiculo(getVeiculoFromVeiculoInputModel(input), nif)
        return ResponseEntity.created(URI.create("/api/subscritores/${nif}/veiculos/${matricula}")).build()
    }

    @GetMapping
    fun getAllVeiculos(@PathVariable nif: String) : ResponseEntity<Any>{
        val veiculos : List<Veiculo> = service.getAllVeiculos(nif)
        return ResponseEntity<Any>(getMultipleVeiculoModel(veiculos), HttpStatus.OK)
    }
}