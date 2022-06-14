package ps.g08.directsincro.controller.inputmodels

import ps.g08.directsincro.common.Evento_Transito

data class EventoTransitoInputModel(
        val evento: Evento
)

data class Evento(
    val dadosDoVeiculo : DadosDoVeiculo,
    val dadosDaInfracao : DadosDaInfracao
)

data class DadosDoVeiculo(
        val Matricula : String,
        val Categoria : String,
        val TipoDeInfracao : String,
        val Pais : String
)

data class DadosDaInfracao(
        val numeroAuto : String,
        val VeiculoAutuado : String,
        val DataHora : Long,
        val local : String,
        val NormaInfigida : String,
        val Distrito : String,
        val DescricaoSumaria : String,
        val DataLimiteDefesa : Long,
        val EstadoDoPagamento : String,
        val ValorDaCoima : Double,
        val Gravidade : String,
        val EntidadeAutuante : String,
        val DataNotificacao : String
)

fun getEventoFromEventoTransitoInputModel(input: EventoTransitoInputModel) : Evento_Transito{
        return Evento_Transito(
                numeroAuto = input.evento.dadosDaInfracao.numeroAuto,
                estadoPagamento = input.evento.dadosDaInfracao.EstadoDoPagamento,
                data = input.evento.dadosDaInfracao.DataHora,
                tipo = input.evento.dadosDoVeiculo.TipoDeInfracao,
                classificacaoInfracao = input.evento.dadosDaInfracao.Gravidade,
                descricao = input.evento.dadosDaInfracao.DescricaoSumaria,
                valor = input.evento.dadosDaInfracao.ValorDaCoima,
                localizao = input.evento.dadosDaInfracao.local,
                entidadeAutuante = input.evento.dadosDaInfracao.EntidadeAutuante,
                dataLimiteDefesa = input.evento.dadosDaInfracao.DataLimiteDefesa
        )
}