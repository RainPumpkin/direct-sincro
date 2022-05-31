package ps.g08.directsincro.service

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Subscritor
import ps.g08.directsincro.common.SubscritorPessoa
import ps.g08.directsincro.database.PessoaDatabase
import ps.g08.directsincro.database.SubscritorDatabase
import ps.g08.directsincro.service.mappers.SubscritorMapper

@Component
class SubscritorService(
    private val pessoaDb: PessoaDatabase,
    private val subsDb: SubscritorDatabase,
    private val mapper: SubscritorMapper) {
    fun getSubscritor(nif: String): Subscritor{
        return mapper.single(subsDb.get(nif))
    }

    fun createSubscritor(subs: SubscritorPessoa): String {
        pessoaDb.create(subs.nome, subs.nif, subs.numero_conducao, subs.email)
        return  subsDb.create(subs.nif, subs.password)
    }
}