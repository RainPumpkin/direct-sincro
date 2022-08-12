package ps.g08.directsincro.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ps.g08.directsincro.common.responseOkWithBody
import ps.g08.directsincro.controller.inputmodels.PedidoDefesaInputModel
import ps.g08.directsincro.controller.inputmodels.getPedidoFromPedidoDefesaInputModel
import java.net.URI

@RestController
@RequestMapping("/api")
class PedidoDefesaController (){
    //subscritores/nif/veiculos/matricula/eventos/numeroauto/pedido ->pedido de defesa desse evento. usado para GET e Post
    @CrossOrigin
    @GetMapping("/subscritores/{nif}/veiculos/{matricula}/eventos/{numeroauto}/pedido/{id}")
    fun getPedido(@PathVariable nif: String, @PathVariable matricula: String, @PathVariable id: Int): ResponseEntity<Any> {
        //return responseOkWithBody(service.getPedido(nif, id))
    }

    @CrossOrigin
    @PostMapping("/subscritores/{nif}/veiculos/{matricula}/eventos/{numeroauto}/pedido")
    fun createPedido(@RequestBody input: PedidoDefesaInputModel, @PathVariable nif: String, @PathVariable matricula: String, @PathVariable numeroauto: String): ResponseEntity<Any>{
        //val id = service.createPedido(getPedidoFromPedidoDefesaInputModel(input), numeroauto, nif)
        return ResponseEntity.created(URI.create("/api/subscritores/${nif}/veiculos/${matricula}/eventos/${numeroauto}/pedido/${id}")).build()
    }
    //subscritores/nif/pedido_defesas/
}