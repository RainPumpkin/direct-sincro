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
import ps.g08.directsincro.service.*
import java.net.URI
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.Instant

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
    private val subscritorService: SubscritorService,
    private val veiculoService: VeiculoService,
    private val notificacaoService: NotificacaoService,
    private val pushSubscriptionService: PushSubscriptionService,
    private val delegacaoVeiculoService: DelegacaoVeiculoService
) {
    //Evento proveniente do SIGET
    @CrossOrigin
    @PostMapping("/contraordenacoes")
    fun receiveContraordenacao(@RequestBody input : ContraordenacaoInputModel) : ResponseEntity<Any> {
        service.createContraordenacao(getContraordenacaoFromContraordenacaoInputModel(input), input.evento.dadosDoVeiculo.matricula)
        val sub = veiculoService.getVeiculo(input.evento.dadosDoVeiculo.matricula).owner
        val numeroAuto = input.evento.dadosDaInfracao.numeroAuto
        checkSubscriptionAndSendNotification(sub, numeroAuto)
        val dataInfracao = SimpleDateFormat("yyyy-MM-dd HH:mm").parse(input.evento.dadosDaInfracao.dataHora)

        val delegacao = delegacaoVeiculoService.getAllDelegacaoVeiculo(input.evento.dadosDoVeiculo.matricula).find {
                    delegacaoVeiculo -> delegacaoVeiculo.dataInicio != null
                    && Timestamp(delegacaoVeiculo.dataInicio).before(dataInfracao)
                    && (delegacaoVeiculo.dataFim == null
                    || Timestamp(delegacaoVeiculo.dataFim).after(dataInfracao))
        }
        if (delegacao != null ) checkSubscriptionAndSendNotification(delegacao.subscritor, numeroAuto)

        return responseOkWithBody(responseOkWithBody("Evento adicionado com sucesso"))
    }

    private fun checkSubscriptionAndSendNotification(sub: String, numeroAuto: String) {
        if (subscritorService.getSubscription(sub)) {
            val mensagem = "Nova Contraordenação $numeroAuto"
            notificacaoService.createNotification(
                Notificacao(
                    0,
                    mensagem,
                    false,
                    "Nova Contraordenação",
                    numeroAuto
                ),
                sub,
                numeroAuto
            )
            val credentials = pushSubscriptionService.getPushCredentials(sub)
            if (credentials.isNotEmpty()) {
                for (cred in credentials) {
                    pushSubscriptionService.send(cred, mensagem)
                }
            }
        }
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
    @GetMapping("/subscritores/{nif}/contraordenacoes/{numeroauto}")
    fun getVeiculo(@PathVariable numeroauto: String): ResponseEntity<Any> {
        return responseOkWithBody(service.getVeiculo(numeroauto))
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