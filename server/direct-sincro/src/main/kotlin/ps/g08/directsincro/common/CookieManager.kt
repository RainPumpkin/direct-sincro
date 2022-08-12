package ps.g08.directsincro.common

import org.springframework.stereotype.Component
import java.util.Random
import kotlin.collections.HashMap

data class User(
    val nif: String,
    val password: String
)

@Component
class CookieManager {

    private val inMemoryCookieManager: HashMap<String, User> = HashMap()

    private val validChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"

    private fun generateRandom(length: Int): String {
        val random = Random()
        val builder = StringBuilder()
        repeat(length) {
            builder.append(validChars[random.nextInt(validChars.length)])
        }
        return builder.toString()
    }


    fun newCookieValue(nif: String, password: String): String {
        var str = generateRandom(50)
        while(inMemoryCookieManager.containsKey(str)) {
            str = generateRandom(50)
        }
        inMemoryCookieManager[str] = User(nif, password)
        return str
    }

    fun getUser(cookie: String): User? {
        return inMemoryCookieManager[cookie]
    }

    fun removeCookie(value: String) {
        inMemoryCookieManager.remove(value)
    }
}