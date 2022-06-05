package ps.g08.directsincro.service

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.EmprestimoMatricula
import ps.g08.directsincro.database.EmprestimoDatabase
import ps.g08.directsincro.service.mappers.EmprestimoMatriculaMapper

@Component
class EmprestimoMatriculaService(private val db: EmprestimoDatabase, private val mapper: EmprestimoMatriculaMapper) {
    fun getAllEmprestimosMatricula(matricula: String) : List<EmprestimoMatricula>{
        return mapper.multiple(db.getAllMatricula(matricula))
    }

    fun getEmprestimoMatricula(matricula: String, dataInicio: Long) : EmprestimoMatricula{
        return mapper.single(db.queryGetWithDate(matricula, dataInicio))
    }
}