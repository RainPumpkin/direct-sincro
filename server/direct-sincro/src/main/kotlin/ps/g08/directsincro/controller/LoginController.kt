package ps.g08.directsincro.controller

import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ps.g08.directsincro.common.CookieManager
import ps.g08.directsincro.common.ErrorMessage
import ps.g08.directsincro.controller.inputmodels.LoginInputModel
import ps.g08.directsincro.controller.outputmodel.getLoginOutputModel
import ps.g08.directsincro.service.CidadaoService
import ps.g08.directsincro.service.SubscritorService
import java.time.Duration
import javax.servlet.http.HttpServletRequest

@RestController
class LoginController(
    val cookieManager: CookieManager,
    val subs: CidadaoService
) {

    @PostMapping("/login")
    fun login(@RequestBody input: LoginInputModel): ResponseEntity<Any> {
        val correct = subs.checkPassword(input.nif, input.password)
        return if (correct!=null){
            val value = cookieManager.newCookieValue(input.nif, input.password)
            val headers = HttpHeaders()
            val out = getLoginOutputModel(correct)
            headers.add("Set-Cookie", "Authorization=${value}; Max-Age=${Duration.ofDays(365).toSeconds()}; Path=/; Secure; HttpOnly; SameSite=Strict")
            ResponseEntity.ok().headers(headers).body(out)
        } else {
            ResponseEntity.badRequest().body(
                ErrorMessage(
                    code = 400,
                    title = "Bad request",
                    description = "User invalid credentials"
                )
            )
        }
    }

    @PostMapping("/logout")
    fun logout(request: HttpServletRequest): ResponseEntity<Any> {
        val cookies = request.cookies!!
        val cookie = cookies.find { it.name == "Authorization" }!!
        cookieManager.removeCookie(cookie.value)
        val headers = HttpHeaders()
        headers.add("Set-Cookie", "Authorization=; Expires=Thu, 01 Jan 1970 00:00:01 GMT; Path=/; Secure; HttpOnly; SameSite=Strict")
        return ResponseEntity.ok().headers(headers).build()
    }

    @GetMapping("/check")
    fun checkLogin(request: HttpServletRequest): ResponseEntity<Any> {
        val cookies = request.cookies!!
        val cookie = cookies.find { it.name == "Authorization" }!!
        val user = cookieManager.getUser(cookie.value)!!
        val cidadao = subs.getCidadao(user.nif)!!
        return ResponseEntity.ok().body(getLoginOutputModel(cidadao))
    }
}

