package ps.g08.directsincro.controller

import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ps.g08.directsincro.common.CookieManager
import ps.g08.directsincro.common.ErrorMessage
import ps.g08.directsincro.controller.inputmodels.LoginInputModel
import ps.g08.directsincro.service.SubscritorService
import java.time.Duration
import javax.servlet.http.HttpServletRequest

@RestController
class LoginController(
    val cookieManager: CookieManager,
    val subs: SubscritorService
) {

    @PostMapping("/login")
    fun login(@RequestBody input: LoginInputModel): ResponseEntity<Any> {
        val correct = subs.checkPassword(input.nif, input.password)
        if (correct){
            val value = cookieManager.newCookieValue(input.nif, input.password)
            val headers = HttpHeaders()
            headers.add("Set-Cookie", "Authorization=${value}; Max-Age=${Duration.ofDays(365).toSeconds()}; Path=/; Secure; HttpOnly; SameSite=Strict")
            return ResponseEntity.ok().headers(headers).build()
        } else {
            return ResponseEntity.badRequest().body(
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
}