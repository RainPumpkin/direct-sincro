package ps.g08.directsincro.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ps.g08.directsincro.service.VeiculoAlugadoService

/*
May be later merged with veiculos
 */
@RestController
@RequestMapping("/api/subscritores/{nif}/delegados")
class VeiculoAlugadosController(private val veiculoAlugadoService: VeiculoAlugadoService) {

}