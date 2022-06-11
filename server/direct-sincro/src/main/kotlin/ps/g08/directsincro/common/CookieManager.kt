package ps.g08.directsincro.common

import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.HashMap

@Component
class CookieManager {

    //private val inMemoryCookieManager: HashMap<String, Subscritor> = HashMap()

    private val validChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"

    private fun generateRandom(length: Int): String {
        val random = Random()
        val builder = StringBuilder()
        repeat(length) {
            builder.append(validChars[random.nextInt(validChars.length)])
        }
        return builder.toString()
    }

    /*
    fun newCookieValue(user: Subscritor): String {
        var str = generateRandom(50)
        while(inMemoryCookieManager.containsKey(str)) {
            str = generateRandom(50)
        }
        inMemoryCookieManager[str] = user
        return str
    }*/

    //get

    //remove
}