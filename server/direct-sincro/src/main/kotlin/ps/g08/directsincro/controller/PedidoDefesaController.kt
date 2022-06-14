package ps.g08.directsincro.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ps.g08.directsincro.common.responseOkWithBody
import ps.g08.directsincro.controller.inputmodels.PedidoDefesaInputModel
import ps.g08.directsincro.controller.inputmodels.getPedidoFromPedidoDefesaInputModel
import ps.g08.directsincro.service.PedidoDefesaService
import java.net.URI

@RestController
@RequestMapping("/api")
class PedidoDefesaController (
    private val service: PedidoDefesaService
        ){
    //subscritores/nif/veiculos/matricula/eventos/numeroauto/pedido ->pedido de defesa desse evento. usado para GET e Post
    @GetMapping("/subscritores/{nif}/veiculos/{matricula}/eventos/{numeroauto}/pedido/{id}")
    fun getPedido(@PathVariable nif: String, @PathVariable matricula: String, @PathVariable id: Int): ResponseEntity<Any> {
        return responseOkWithBody(service.getPedido(nif, id))
    }

    @PostMapping("/subscritores/{nif}/veiculos/{matricula}/eventos/{numeroauto}/pedido")
    fun createPedido(@RequestBody input: PedidoDefesaInputModel, @PathVariable nif: String, @PathVariable matricula: String, @PathVariable numeroauto: String): ResponseEntity<Any>{
        val id = service.createPedido(getPedidoFromPedidoDefesaInputModel(input), numeroauto, nif)
        return ResponseEntity.created(URI.create("/api/subscritores/${nif}/veiculos/${matricula}/eventos/${numeroauto}/pedido/${id}")).build()
    }
    //subscritores/nif/pedido_defesas/
}