package ps.g08.directsincro.service.mappers

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.DelegacaoSubscritor
import ps.g08.directsincro.common.Notificacao
import ps.g08.directsincro.common.Subscritor
import ps.g08.directsincro.common.Veiculo
import ps.g08.directsincro.database.SubscritorDatabaseRow
import ps.g08.directsincro.service.DelegacaoSubscritorService
import ps.g08.directsincro.service.VeiculoService
import ps.g08.directsincro.service.NotificacaoService
import ps.g08.directsincro.service.VeiculoAlugadoService

@Component
class SubscritorMapper(val veiculoService : VeiculoService, val notificaoService : NotificacaoService, val veiculoAlugadoService : VeiculoAlugadoService, val delegacaoSubscritorService : DelegacaoSubscritorService) : IMapper<SubscritorDatabaseRow, Subscritor>{
    override fun single(obj: SubscritorDatabaseRow): Subscritor {
        val notificacoes : List<Notificacao> by lazy {
            notificaoService.getAllNotificacoes(obj.nif)
        }
        val veiculos : List<Veiculo> by lazy {//veiculos que possuimos que n estão emprestados
            veiculoService.getAllVeiculos(obj.nif)//mudar para getAllVeiculosNaoAlugados
        }
        val veiculosAlugados : List<Veiculo> by lazy {//veiculos que possuimos que estão emprestados
            veiculoAlugadoService.getAllVeiculosAlugados(obj.nif)
        }
        val emprestimos : List<DelegacaoSubscritor> by lazy {//emprestimos que recebeste
            delegacaoSubscritorService.getAllDelegacoesSubscritor(obj.nif)
        }
        return Subscritor(
            nif = obj.nif,
            notificacoes = notificacoes,
            veiculos = veiculos,
            veiculosAlugados = veiculosAlugados,
            delegacoes = emprestimos
        )
    }

    override fun multiple(objs: List<SubscritorDatabaseRow>): List<Subscritor> {
        val ret : MutableList<Subscritor> = mutableListOf()

        for (obj in objs) {
            ret.add(single(obj))
        }

        return ret
    }

}