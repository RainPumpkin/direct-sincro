package ps.g08.directsincro.service.mappers

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.EmprestimoMatricula
import ps.g08.directsincro.common.getEpoch
import ps.g08.directsincro.database.DelegacaoDatabaseRow

@Component
class EmprestimoMatriculaMapper : IMapper<DelegacaoDatabaseRow, EmprestimoMatricula> {
    override fun single(obj: DelegacaoDatabaseRow): EmprestimoMatricula {
        return EmprestimoMatricula(
            dataFim = getEpoch(obj.dataFim),
            dataInicio = getEpoch(obj.dataInicio),
            usuario = obj.usuario,
            estado = obj.estado
        )
    }

    override fun multiple(objs: List<DelegacaoDatabaseRow>): List<EmprestimoMatricula> {
        val ret : MutableList<EmprestimoMatricula> = mutableListOf()

        for (obj in objs) {
            ret.add(single(obj))
        }

        return ret
    }
}