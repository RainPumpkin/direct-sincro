package ps.g08.directsincro.database.inputmodels

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
        val numeroAuto : Int,
        val VeiculoAutuado : String,
        val DataHora : String,
        val local : String,
        val NormaInfigida : String,
        val Distrito : String,
        val DescricaoSumaria : String,
        val DataLimiteDefesa : String,
        val EstadoDoPagamento : String,
        val ValorDaCoima : String,
        val Gravidade : String,
        val EntidadeAutuante : String,
        val DataNotificacao : String
)