package ps.g08.directsincro.controller

import jdk.jshell.spi.ExecutionControl.NotImplementedException
import okhttp3.MediaType
import okhttp3.Request
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ps.g08.directsincro.common.Notificacao
import ps.g08.directsincro.common.responseOkWithBody
import ps.g08.directsincro.controller.inputmodels.ContraordenacaoInputModel
import ps.g08.directsincro.controller.inputmodels.getContraordenacaoFromContraordenacaoInputModel
import ps.g08.directsincro.controller.outputmodel.getContraordenacaoOutputModel
import ps.g08.directsincro.controller.outputmodel.getMultipleContraordenacaoOutputModel
import ps.g08.directsincro.service.ContraordenacaoService
import ps.g08.directsincro.service.NotificacaoService
import ps.g08.directsincro.service.SubscritorService
import java.net.URI
import java.sql.Timestamp

private const val SCOT = "http://localhost:4000/scot/pagamento"
private val contentType : MediaType = MediaType.get("application/json; charset=utf-8")

fun updateEstadoDePagamentoSCOT(numeroAuto: String, matricula: String) : Int? {
    val body = okhttp3.RequestBody.create(contentType, "{\"numeroAuto\":\"$numeroAuto\", \"matricula\":\"$matricula\"}")
    val response = Request.Builder()
        .url(SCOT)
        .post(body)
        .build()
        .execute()
    return response?.code()
}

@RestController
@RequestMapping("/api")
class ContraordenacaoController(
    private val service: ContraordenacaoService,
    private val notificationService: NotificacaoService,
    private val subscritorService: SubscritorService
) {
    //Evento proveniente do SIGET
    @CrossOrigin
    @PostMapping("/contraordenacoes")
    fun receiveContraordenacao(@RequestBody input : ContraordenacaoInputModel) : ResponseEntity<Any> {
        service.createContraordenacao(getContraordenacaoFromContraordenacaoInputModel(input), input.evento.dadosDoVeiculo.matricula)
        subscritorService.
        /*notificationService.createNotification(Notificacao(emitida = System.currentTimeMillis(), mensagem = "nova contraordenação", visualizada = false, tipo = "Contraordenação"),
        )*/
        return responseOkWithBody(responseOkWithBody("Evento adicionado com sucesso"))
    }

    //subscritores/nif/veiculos/matricula/eventos ->eventos de transito do meu veiculo
    //subscritores/nif/alugados/matricula/eventos ->eventos de transito do veiculo alugado

    @CrossOrigin
    @PostMapping("/subscritores/{nif}/veiculos/{matricula}/contraordenacoes")
    fun createContraordenacaoVeiculo(@RequestBody input: ContraordenacaoInputModel, @PathVariable nif: String, @PathVariable matricula: String) : ResponseEntity<Any> {
        val numeroAuto = service.createContraordenacao(getContraordenacaoFromContraordenacaoInputModel(input), matricula)
        return ResponseEntity.created(URI.create("/api/subscritores/${nif}/veiculos/${matricula}/eventos/${numeroAuto}")).build()
    }

    @CrossOrigin
    @GetMapping("/subscritores/{nif}/veiculos/{matricula}/contraordenacoes")
    fun getAllContraordenacoesVeiculo(@PathVariable matricula: String) : ResponseEntity<Any>{
        return responseOkWithBody(getMultipleContraordenacaoOutputModel(service.getAllContraordenacoes(matricula)))
    }

    @CrossOrigin
    @GetMapping("/subscritores/{nif}/veiculos/{matricula}/contraordenacoes/{numeroauto}")
    fun getContraordenacaoVeiculo(@PathVariable numeroauto: String) : ResponseEntity<Any>{
        service.UpdateVisualizada(numeroauto)
        return responseOkWithBody(getContraordenacaoOutputModel(service.getContraordenação(numeroauto)))
    }

    @CrossOrigin
    @PostMapping("/subscritores/{nif}/alugados/{matricula}/contraordenacoes")
    fun createContraordenacaoAlugado(@RequestBody input: ContraordenacaoInputModel, @PathVariable nif: String, @PathVariable matricula: String) : ResponseEntity<Any> {
        val numeroAuto = service.createContraordenacao(getContraordenacaoFromContraordenacaoInputModel(input), matricula)
        return ResponseEntity.created(URI.create("/api/subscritores/${nif}/alugados/${matricula}/eventos/${numeroAuto}")).build()
    }

    @CrossOrigin
    @GetMapping("/subscritores/{nif}/alugados/{matricula}/contraordenacoes")
    fun getAllContraordenacoesAlugado() : ResponseEntity<Any>{
        //retorna apenas eventos entre datainicio e datafim do emprestimo
        throw NotImplementedException("getalleventos");
    }

    @CrossOrigin
    @PostMapping("/subscritores/{nif}/veiculos/{matricula}/contraordenacoes/{numeroauto}")
    fun updateEstadoPagamento(@PathVariable nif: String, @PathVariable matricula: String, @PathVariable numeroauto: String): ResponseEntity<Any> {
        //if response from simulator siget status is valid 200 - 300 status, Direct-Sincro can then
        //update estado de pagamento of event in DB
        val responseFromSCOT = updateEstadoDePagamentoSCOT(numeroauto, matricula)
        if (responseFromSCOT != null) {
            if (responseFromSCOT in 200..300) {
                service.updateEstadoPagamento(numeroauto)
                return ResponseEntity.created(URI.create("/api/subscritores/${nif}/veiculos/${matricula}/eventos/${numeroauto}")).build()
            }
        }
        return ResponseEntity<Any>(HttpStatus.NOT_ACCEPTABLE)
    }
}