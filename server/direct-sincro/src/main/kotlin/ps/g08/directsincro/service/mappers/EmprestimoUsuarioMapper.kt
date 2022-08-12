package ps.g08.directsincro.service.mappers

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.EmprestimoUsuario
import ps.g08.directsincro.common.getEpoch
import ps.g08.directsincro.database.DelegacaoDatabaseRow

@Component
class EmprestimoUsuarioMapper : IMapper<DelegacaoDatabaseRow, EmprestimoUsuario> {
    override fun single(obj: DelegacaoDatabaseRow): EmprestimoUsuario {
        return EmprestimoUsuario(
            dataFim = getEpoch(obj.dataFim),
            dataInicio = getEpoch(obj.dataInicio),
            matricula = obj.matricula,
            estado = obj.estado
        )
    }

    override fun multiple(objs: List<DelegacaoDatabaseRow>): List<EmprestimoUsuario> {
        val ret : MutableList<EmprestimoUsuario> = mutableListOf()

        for (obj in objs) {
            ret.add(single(obj))
        }

        return ret
    }
}