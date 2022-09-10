package ps.g08.directsincro.controller

import mu.KotlinLogging
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ps.g08.directsincro.common.Veiculo
import ps.g08.directsincro.common.responseOkWithBody
import ps.g08.directsincro.controller.inputmodels.VeiculoInputModel
import ps.g08.directsincro.controller.inputmodels.getVeiculoFromVeiculoInputModel
import ps.g08.directsincro.controller.outputmodel.getMultipleVeiculoModel
import ps.g08.directsincro.controller.outputmodel.getVeiculoAllOutputModel
import ps.g08.directsincro.service.VeiculoService
import java.net.URI
private val logger = KotlinLogging.logger("VeiculoController")
private const val SIGET = "http://localhost:3000/siget/veiculo"
private val contentType : MediaType = MediaType.get("application/json; charset=utf-8")
private val client = OkHttpClient()
fun Request.execute(): Response? {
    var response: Response? = null
    try {
        response = client.newCall(this).execute()
        logger.info("Response from Simulator on ${this.url()} = $response")
    } catch (e : Exception) {
        e.printStackTrace()
    }
    return response
}
fun postMatriculaToSiget(matricula: String, nif: String) : Int? {
    val body = okhttp3.RequestBody.create(contentType, "{\"matricula\":\"$matricula\", \"nif\":\"$nif\"}")
    val response = Request.Builder()
        .url(SIGET)
        .post(body)
        .build()
        .execute()
    return response?.code()
}
fun String.deleteMatriculaFromSiget() {
    val body = okhttp3.RequestBody.create(contentType, "{\"matricula\":\"$this\"}")
    val response = Request.Builder()
        .url(SIGET)
        .delete(body)
        .build()
        .execute()
}


@RestController
@RequestMapping("/api/subscritores/{nif}/veiculos")
class VeiculoController(
    private val service: VeiculoService
) {
    @CrossOrigin
    @PostMapping()//recebe input model
    fun putveiculo(@RequestBody input : VeiculoInputModel, @PathVariable nif: String): ResponseEntity<Any>{
        //if response from simulator siget status is valid 200 - 300 status, Direct-Sincro can then save new vehicle in DB
        //car must match the citizen's nif (IMT)
        val responseFromSiget = postMatriculaToSiget(input.matricula, nif)
        if (responseFromSiget != null) {
            if (responseFromSiget in 200..300) {
                val matricula = service.createVeiculo(getVeiculoFromVeiculoInputModel(input, nif))
                return ResponseEntity.created(URI.create("/api/subscritores/${nif}/veiculos/${matricula}")).build()
            }
        }
        return ResponseEntity<Any>(HttpStatus.NOT_ACCEPTABLE)
    }

    @CrossOrigin
    @GetMapping
    fun getAllVeiculos(@PathVariable nif: String) : ResponseEntity<Any>{
        val veiculos : List<Veiculo> = service.getAllVeiculos(nif)
        return ResponseEntity<Any>(getMultipleVeiculoModel(veiculos), HttpStatus.OK)
    }

    @CrossOrigin
    @DeleteMapping("/{matricula}")
    fun deleteVeiculo(@PathVariable matricula: String) : ResponseEntity<Any>{
        val responseEntity = ResponseEntity<Any>(service.deleteVeiculos(matricula), HttpStatus.OK)
        matricula.deleteMatriculaFromSiget()
        return responseEntity
    }

    @GetMapping("/{matricula}/all")
    fun getVeiculoInfo(@PathVariable matricula: String) : ResponseEntity<Any>{
        val veiculo = service.getVeiculo(matricula)
        val out = getVeiculoAllOutputModel(veiculo)
        return responseOkWithBody(out)
    }
}