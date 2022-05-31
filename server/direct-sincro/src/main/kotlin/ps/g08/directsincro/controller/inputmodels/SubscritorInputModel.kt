package ps.g08.directsincro.controller.inputmodels

import ps.g08.directsincro.common.Subscritor
import ps.g08.directsincro.common.SubscritorPessoa

data class SubscritorInputModel(
    val nome: String,
    val nif: String,
    val numeroConducao: String,
    val email: String,
    val password: String
)

fun getSubscritorPessoaFromSubscritorInputModel(input : SubscritorInputModel) : SubscritorPessoa{
    return SubscritorPessoa(
        nif = input.nif,
        password = input.password,
        nome = input.nome,
        numero_conducao = input.numeroConducao,
        email = input.email
    )
}