package ps.g08.directsincro.service

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Cidadao
import ps.g08.directsincro.common.Subscritor
import ps.g08.directsincro.common.SubscritorCidadao
import ps.g08.directsincro.database.PessoaDatabase
import ps.g08.directsincro.database.SubscritorDatabase
import ps.g08.directsincro.service.mappers.CidadaoMapper
import ps.g08.directsincro.service.mappers.SubscritorMapper

@Component
class SubscritorService(
    private val cidadaoDb: PessoaDatabase,
    private val subsDb: SubscritorDatabase,
    private val subsMapper: SubscritorMapper,
    private val cidMapper: CidadaoMapper) {
    fun getSubscritor(nif: String): Subscritor{
        return subsMapper.single(subsDb.get(nif))
    }

    fun getCidadao(nif: String):Cidadao?{
        val cidadao : Cidadao?
        try {
            cidadao = cidMapper.single(cidadaoDb.get(nif))
            return cidadao
        } catch (e : Exception){
            return null
        }
    }


    fun checkPassword(nif: String, passIn: String) : Cidadao?{
        val cidadao : Cidadao?
        try {
            cidadao = cidMapper.single(cidadaoDb.get(nif))
        } catch (e : Exception){
            return null
        }

        val password = cidadao.password
        if (password == passIn) return cidadao
        else return null
    }

    fun createSubscritor(subs: SubscritorCidadao): String {
        cidadaoDb.create(subs.nome, subs.nif, subs.numero_conducao, subs.email, subs.password)
        return  subsDb.create(subs.nif)
    }
}