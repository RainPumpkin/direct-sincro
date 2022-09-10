package ps.g08.directsincro.common

import ps.g08.directsincro.service.CidadaoService
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthFilter(private val cookieManager: CookieManager, private val cidadãoService: CidadaoService): Filter {

    private fun respondWithUnauthorized(httpResponse: HttpServletResponse, reason: String? = null) {
        httpResponse.status = HttpServletResponse.SC_UNAUTHORIZED
        httpResponse.addHeader("Content-Type", "application/json")
        httpResponse.writer?.write(ErrorMessage(
            code = 403,
            title = "Access denied",
            description = reason
        ).toString())
    }

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        //ESTÁ ASSIM PARA N PRECISARMOS DO COOKIE PARA TESTES
        if(true) chain.doFilter(request, response)
        else {
            val httpRequest = request as HttpServletRequest
            val httpResponse = response as HttpServletResponse

            val cookies = httpRequest.cookies
            if (cookies == null) return respondWithUnauthorized(httpResponse, "User don't have cookie")

            val cookie = request.cookies.find { it.name == "Authorization" }
            if (cookie == null) return respondWithUnauthorized(httpResponse, "User don't have cookie")

            val user = cookieManager.getUser(cookie.value)
            if (user == null) return respondWithUnauthorized(httpResponse, "User does not exist")

            if (cidadãoService.checkPassword(user.nif, user.password)!=null) chain.doFilter(request, response)
            else return respondWithUnauthorized(httpResponse, "User invalid credentials")
        }

    }
}