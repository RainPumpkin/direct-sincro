package ps.g08.directsincro.service

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Cidadao
import ps.g08.directsincro.database.CidadaoDatabase
import ps.g08.directsincro.service.mappers.CidadaoMapper

@Component
class CidadaoService(
    private val cidMapper: CidadaoMapper,
    private val cidadaoDb: CidadaoDatabase
) {


    fun getCidadao(nif: String): Cidadao?{
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
}