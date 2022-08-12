package ps.g08.directsincro.controller.inputmodels

import ps.g08.directsincro.common.Contraordenacao
import java.text.SimpleDateFormat

data class EventoTransitoInputModel(
        val evento: Evento
)

data class Evento(
    val dadosDoVeiculo : DadosDoVeiculo,
    val dadosDaInfracao : DadosDaInfracao
)

data class DadosDoVeiculo(
        val matricula : String,
        val categoria : String,
        val tipoDeInfracao : String,
        val pais : String
)

data class DadosDaInfracao(
        val numeroAuto : String,
        val veiculoAutuado : String,
        val dataHora : String,
        val local : String,
        val normaInfrigida : String,
        val distrito : String,
        val descricaoSumaria : String,
        val dataLimiteDefesa : String,
        val estadoDoPagamento : String,
        val valorDaCoima : Double,
        val gravidade : String,
        val entidadeAutuante : String,
        val dataNotificacao : String
)
fun getEventoFromEventoTransitoInputModel(input: EventoTransitoInputModel) : Contraordenacao{
        val data = SimpleDateFormat("yyyy-MM-dd HH:mm").parse(input.evento.dadosDaInfracao.dataHora)
        val dataDefesa = SimpleDateFormat("yyyy-MM-dd").parse(input.evento.dadosDaInfracao.dataLimiteDefesa)
        return Contraordenacao(
                numeroAuto = input.evento.dadosDaInfracao.numeroAuto,
                estadoPagamento = input.evento.dadosDaInfracao.estadoDoPagamento,
                data = data.time/1000,
                catagoriaVeiculo = input.evento.dadosDoVeiculo.tipoDeInfracao,
                classificacaoInfracao = input.evento.dadosDaInfracao.gravidade,
                descricao = input.evento.dadosDaInfracao.descricaoSumaria,
                valorCoima = input.evento.dadosDaInfracao.valorDaCoima,
                local = input.evento.dadosDaInfracao.local,
                entidadeAutuante = input.evento.dadosDaInfracao.entidadeAutuante,
                dataLimiteDefesa = dataDefesa.time/1000
        )
}