package ps.g08.directsincro.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
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

    @DeleteMapping("/{matricula}")
    fun deleteVeiculo(@PathVariable matricula: String) : ResponseEntity<Any>{
        return ResponseEntity<Any>(service.deleteVeiculos(matricula), HttpStatus.OK)
    }
}