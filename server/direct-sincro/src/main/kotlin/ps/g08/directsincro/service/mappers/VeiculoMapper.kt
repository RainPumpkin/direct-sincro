package ps.g08.directsincro.service.mappers

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.EmprestimoMatricula
import ps.g08.directsincro.common.Contraordenacao
import ps.g08.directsincro.common.Veiculo
import ps.g08.directsincro.database.VeiculoDatabaseRow
import ps.g08.directsincro.service.EmprestimoMatriculaService
import ps.g08.directsincro.service.EventoTransitoService

@Component
class VeiculoMapper(val emprestimoMatriculaService: EmprestimoMatriculaService, val eventoTransitoService: EventoTransitoService) : IMapper<VeiculoDatabaseRow, Veiculo> {
    override fun single(obj: VeiculoDatabaseRow): Veiculo {
        val emprestimos : List<EmprestimoMatricula> by lazy {
            emprestimoMatriculaService.getAllEmprestimosMatricula(obj.matricula)
        }

        val eventos : List<Contraordenacao> by lazy {
            eventoTransitoService.getAllEventos(obj.matricula)
        }

        return Veiculo(
            matricula = obj.matricula,
            modelo = obj.modelo,
            categoria = obj.categoria,
            emprestimos = emprestimos,
            eventos = emptyList()//TODO figure date error
        )
    }

    override fun multiple(objs: List<VeiculoDatabaseRow>): List<Veiculo> {
        val ret : MutableList<Veiculo> = mutableListOf()

        for (obj in objs) {
            ret.add(single(obj))
        }

        return ret
    }
}