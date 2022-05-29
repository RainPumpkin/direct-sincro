package ps.g08.directsincro.service.mappers

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.EmprestimoUsuario
import ps.g08.directsincro.common.Notificacao
import ps.g08.directsincro.common.Subscritor
import ps.g08.directsincro.common.Veiculo
import ps.g08.directsincro.database.SubscritorDatabaseRow
import ps.g08.directsincro.service.EmprestimoUsuarioService
import ps.g08.directsincro.service.VeiculoService
import ps.g08.directsincro.service.NotificacaoService
import ps.g08.directsincro.service.VeiculoAlugadoService

@Component
class SubscritorMapper(val veiculoService : VeiculoService, val notificaoService : NotificacaoService, val veiculoAlugadoService : VeiculoAlugadoService, val emprestimoUsuarioService : EmprestimoUsuarioService) : IMapper<SubscritorDatabaseRow, Subscritor>{
    override fun single(obj: SubscritorDatabaseRow): Subscritor {
        val notificacoes : List<Notificacao> by lazy {
            notificaoService.getAllNotificacoes(obj.nif)
        }
        val veiculos : List<Veiculo> by lazy {
            veiculoService.getAllVeiculos(obj.nif)
        }
        val veiculosAlugados : List<Veiculo> by lazy {
            veiculoAlugadoService.getAllVeiculosAlugados(obj.nif)
        }
        val emprestimos : List<EmprestimoUsuario> by lazy {
            emprestimoUsuarioService.getAllEmprestimosUsuario(obj.nif)
        }
        return Subscritor(
            nif = obj.nif,
            password = obj.password,
            notificacoes = notificacoes,
            veiculos = veiculos,
            veiculosAlugados = veiculosAlugados,
            emprestimos = emprestimos
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