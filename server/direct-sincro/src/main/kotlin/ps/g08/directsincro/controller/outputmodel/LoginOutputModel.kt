package ps.g08.directsincro.controller.outputmodel

import ps.g08.directsincro.common.Cidadao

data class LoginOutputModel(
    val nif: String,
    val email: String,
    val nome: String,
    val subscritor: Boolean
)

fun getLoginOutputModel(cid: Cidadao) : LoginOutputModel{
    return LoginOutputModel( nif = cid.nif,
        email = cid.email,
        nome = cid.nome,
        subscritor = cid.subscritor!=null
    )
}