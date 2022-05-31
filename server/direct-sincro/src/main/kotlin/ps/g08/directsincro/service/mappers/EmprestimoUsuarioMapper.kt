package ps.g08.directsincro.service.mappers

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.EmprestimoUsuario
import ps.g08.directsincro.common.getEpoch
import ps.g08.directsincro.database.EmprestimoDatabaseRow

@Component
class EmprestimoUsuarioMapper : IMapper<EmprestimoDatabaseRow, EmprestimoUsuario> {
    override fun single(obj: EmprestimoDatabaseRow): EmprestimoUsuario {
        return EmprestimoUsuario(
            dataFim = getEpoch(obj.dataFim),
            dataInicio = getEpoch(obj.dataInicio),
            matricula = obj.matricula,
            estado = obj.estado
        )
    }

    override fun multiple(objs: List<EmprestimoDatabaseRow>): List<EmprestimoUsuario> {
        val ret : MutableList<EmprestimoUsuario> = mutableListOf()

        for (obj in objs) {
            ret.add(single(obj))
        }

        return ret
    }
}