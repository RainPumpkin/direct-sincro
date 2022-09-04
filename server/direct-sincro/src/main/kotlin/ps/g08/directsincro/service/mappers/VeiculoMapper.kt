package ps.g08.directsincro.service.mappers

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.DelegacaoVeiculo
import ps.g08.directsincro.common.Contraordenacao
import ps.g08.directsincro.common.Veiculo
import ps.g08.directsincro.database.VeiculoDatabaseRow
import ps.g08.directsincro.service.DelegacaoVeiculoService
import ps.g08.directsincro.service.ContraordenacaoService

@Component
class VeiculoMapper(val delegacaoVeiculoService: DelegacaoVeiculoService, val contraordenacaoService: ContraordenacaoService) : IMapper<VeiculoDatabaseRow, Veiculo> {
    override fun single(obj: VeiculoDatabaseRow): Veiculo {
        val emprestimos : List<DelegacaoVeiculo> by lazy {
            delegacaoVeiculoService.getAllDelegacaoVeiculo(obj.matricula)
        }

        val eventos : List<Contraordenacao> by lazy {
            contraordenacaoService.getAllContraordenacoes(obj.matricula)
        }

        return Veiculo(
            matricula = obj.matricula,
            modelo = obj.modelo,
            categoria = obj.categoria,
            delegacoes = emprestimos,
            contraordenacoes = eventos//TODO figure date error
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