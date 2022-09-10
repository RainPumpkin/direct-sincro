package ps.g08.directsincro.service

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.DelegacaoSubscritor
import ps.g08.directsincro.database.DelegacaoDatabase
import ps.g08.directsincro.service.mappers.DelegacaoSubscritorMapper

@Component
class DelegacaoSubscritorService(private val db: DelegacaoDatabase, private val mapper: DelegacaoSubscritorMapper) {

    fun getAllDelegacoesSubscritor(nif : String) : List<DelegacaoSubscritor>{
        return mapper.multiple(db.getAllUser(nif))
    }

    fun getDelegacaoWithDate(nif: String, matricula: String, dataInicio: Long) : DelegacaoSubscritor {
        return mapper.single(db.queryGetWithDate(nif,matricula, dataInicio))
    }

    fun updateFim(matricula: String, dataCriacao: Long) {
        db.updateFim(dataCriacao, matricula)
    }

    //fun aceitarDelegacao(matricula: String, dataCriacao: Long)
}