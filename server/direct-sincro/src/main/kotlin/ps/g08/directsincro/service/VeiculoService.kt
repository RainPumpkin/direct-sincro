package ps.g08.directsincro.service

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Veiculo
import ps.g08.directsincro.database.VeiculoDatabase
import ps.g08.directsincro.service.mappers.VeiculoMapper

@Component
class VeiculoService(private val veiculodb: VeiculoDatabase, private val veiculoMapper: VeiculoMapper) {

    fun getAllVeiculos(nif : String) : List<Veiculo>{
        return veiculoMapper.multiple(veiculodb.getAll(nif))
    }

    fun deleteVeiculos(matricula: String) {
        return veiculodb.delete(matricula)
    }
}