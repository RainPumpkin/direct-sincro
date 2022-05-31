package ps.g08.directsincro.service.mappers

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.EmprestimoMatricula
import ps.g08.directsincro.common.getEpoch
import ps.g08.directsincro.database.EmprestimoDatabaseRow

@Component
class EmprestimoMatriculaMapper : IMapper<EmprestimoDatabaseRow, EmprestimoMatricula> {
    override fun single(obj: EmprestimoDatabaseRow): EmprestimoMatricula {
        return EmprestimoMatricula(
            dataFim = getEpoch(obj.dataFim),
            dataInicio = getEpoch(obj.dataInicio),
            usuario = obj.usuario,
            estado = obj.estado
        )
    }

    override fun multiple(objs: List<EmprestimoDatabaseRow>): List<EmprestimoMatricula> {
        val ret : MutableList<EmprestimoMatricula> = mutableListOf()

        for (obj in objs) {
            ret.add(single(obj))
        }

        return ret
    }
}