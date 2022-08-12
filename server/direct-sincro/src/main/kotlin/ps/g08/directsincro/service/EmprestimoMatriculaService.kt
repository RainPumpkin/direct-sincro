package ps.g08.directsincro.service

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.EmprestimoMatricula
import ps.g08.directsincro.database.DelegacaoDatabase
import ps.g08.directsincro.service.mappers.EmprestimoMatriculaMapper

@Component
class EmprestimoMatriculaService(private val db: DelegacaoDatabase, private val mapper: EmprestimoMatriculaMapper) {
    fun getAllEmprestimosMatricula(matricula: String) : List<EmprestimoMatricula>{
        return mapper.multiple(db.getAllMatricula(matricula))
    }

    fun getEmprestimoMatricula(nif: String, matricula: String, dataInicio: Long) : EmprestimoMatricula{
        return mapper.single(db.queryGetWithDate(nif, matricula, dataInicio))
    }

    fun createEmprestimo(emprestimoMatricula: EmprestimoMatricula, matricula: String) {
        db.create(emprestimoMatricula, matricula)
    }
}