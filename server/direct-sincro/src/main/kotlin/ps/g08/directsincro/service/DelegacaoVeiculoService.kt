package ps.g08.directsincro.service

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.DelegacaoVeiculo
import ps.g08.directsincro.database.DelegacaoDatabase
import ps.g08.directsincro.service.mappers.DelegacaoVeiculoMapper

@Component
class DelegacaoVeiculoService(private val db: DelegacaoDatabase, private val mapper: DelegacaoVeiculoMapper) {
    fun getAllDelegacaoVeiculo(matricula: String) : List<DelegacaoVeiculo>{
        return mapper.multiple(db.getAllMatricula(matricula))
    }

    fun getDelegacaoVeiculo(nif: String, matricula: String, dataInicio: Long) : DelegacaoVeiculo{
        return mapper.single(db.queryGetWithDate(nif, matricula, dataInicio))
    }

    fun createDelegacao(delegacaoVeiculo: DelegacaoVeiculo, matricula: String) {
        db.create(delegacaoVeiculo, matricula)
    }
}