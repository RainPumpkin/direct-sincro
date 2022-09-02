package ps.g08.directsincro.controller.inputmodels

import ps.g08.directsincro.common.SubscritorCidadao

data class SubscritorInputModel(
    val nome: String,
    val nif: String,
    val tituloConducao: String,
    val email: String,
    val password: String
)

fun getSubscritorPessoaFromSubscritorInputModel(input : SubscritorInputModel) : SubscritorCidadao{
    return SubscritorCidadao(
        nif = input.nif,
        password = input.password,
        nome = input.nome,
        tituloConducao = input.tituloConducao,
        email = input.email
    )
}