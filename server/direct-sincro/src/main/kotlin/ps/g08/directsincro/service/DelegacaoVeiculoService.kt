package ps.g08.directsincro.service

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.DelegacaoVeiculo
import ps.g08.directsincro.database.DelegacaoDatabase
import ps.g08.directsincro.database.DelegacaoDatabaseRow
import ps.g08.directsincro.service.mappers.DelegacaoVeiculoMapper

@Component
class DelegacaoVeiculoService(private val db: DelegacaoDatabase, private val mapper: DelegacaoVeiculoMapper) {
    fun getAllDelegacaoVeiculo(matricula: String) : List<DelegacaoVeiculo>{
        val rows : List<DelegacaoDatabaseRow>
        try{
            rows = db.getAllMatricula(matricula)
            return mapper.multiple(rows)
        } catch (e : Exception) {
            return emptyList()
        }
    }

    fun getDelegacaoVeiculo(nif: String, matricula: String, dataInicio: Long) : DelegacaoVeiculo{
        return mapper.single(db.queryGetWithDate(nif, matricula, dataInicio))
    }

    fun deleteDelegacao(dataCriacao: Long, matricula: String) {
        db.delete(matricula, dataCriacao)
    }

    fun createDelegacao(nif: String, matricula: String) {
        db.create(nif, matricula)
    }
}