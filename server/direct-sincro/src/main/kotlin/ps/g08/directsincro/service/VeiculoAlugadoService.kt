package ps.g08.directsincro.service

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Veiculo
import ps.g08.directsincro.database.VeiculoDatabase
import ps.g08.directsincro.service.mappers.VeiculoMapper

@Component
class VeiculoAlugadoService(private val db: VeiculoDatabase, private val mapper: VeiculoMapper) {

    fun getAllVeiculosAlugados(nif : String) : List<Veiculo>{
        return mapper.multiple(db.getAll(nif))
    }
}