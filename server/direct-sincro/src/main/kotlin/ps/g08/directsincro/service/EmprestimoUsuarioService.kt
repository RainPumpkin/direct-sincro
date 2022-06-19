package ps.g08.directsincro.service

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.EmprestimoUsuario
import ps.g08.directsincro.database.EmprestimoDatabase
import ps.g08.directsincro.service.mappers.EmprestimoUsuarioMapper

@Component
class EmprestimoUsuarioService(private val db: EmprestimoDatabase, private val mapper: EmprestimoUsuarioMapper) {

    fun getAllEmprestimosUsuario(nif : String) : List<EmprestimoUsuario>{
        return mapper.multiple(db.getAllUser(nif))
    }

    fun getEmprestimoWithDate(nif: String, matricula: String, dataInicio: Long) : EmprestimoUsuario {
        return mapper.single(db.queryGetWithDate(nif,matricula, dataInicio))
    }
}