package ps.g08.directsincro.service

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Cidadao
import ps.g08.directsincro.common.PushSubscription
import ps.g08.directsincro.common.Subscritor
import ps.g08.directsincro.common.SubscritorCidadao
import ps.g08.directsincro.database.CidadaoDatabase
import ps.g08.directsincro.database.SubscritorDatabase
import ps.g08.directsincro.service.mappers.CidadaoMapper
import ps.g08.directsincro.service.mappers.SubscritorMapper
import java.sql.Timestamp

data class data(
    val inicio: Timestamp
)

@Component
class SubscritorService(
    private val cidadaoDb: CidadaoDatabase,
    private val subsDb: SubscritorDatabase,
    private val subsMapper: SubscritorMapper,
    ) {

    fun getSubscritor(nif: String): Subscritor{
        return subsMapper.single(subsDb.get(nif)!!)
    }

    fun getDateOfSubscription(nif: String): Long {
        val  data = subsDb.getDateOfSubscription(nif).time
        return subsDb.getDateOfSubscription(nif).time
    }

    fun getSubscription(nif: String): Boolean{
        return subsDb.getSubscription(nif)
    }

    fun createSubscritor(subs: SubscritorCidadao): String {
        cidadaoDb.create(subs.nome, subs.nif, subs.tituloConducao, subs.email, subs.password)
        return  subsDb.create(subs.nif)
    }

    fun cancelSub(nif: String, dataInicio: Long) {
        subsDb.deactivateSubscription(nif, dataInicio)
    }

    fun subscribe(nif: String) {
        subsDb.activateSubscription(nif)
    }
}