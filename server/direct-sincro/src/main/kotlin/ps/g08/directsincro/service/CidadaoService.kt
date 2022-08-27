package ps.g08.directsincro.service

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Subscritor
import ps.g08.directsincro.database.SubscritorDatabase
import ps.g08.directsincro.service.mappers.SubscritorMapper

@Component
class CidadaoService(
    private val subsDb: SubscritorDatabase,
    private val subsMapper: SubscritorMapper
) {
    fun getSubscritorUnsafe(nif: String): Subscritor?{
        return subsMapper.single(subsDb.get(nif))
    }
}