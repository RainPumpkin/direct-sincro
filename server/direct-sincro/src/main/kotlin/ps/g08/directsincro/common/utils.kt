package ps.g08.directsincro.common

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.ZoneOffset


fun getEpoch(timestamp: Timestamp): Long{
    //if (timestamp == null) return null
    val localDateTime = timestamp.toLocalDateTime()
    return localDateTime.toEpochSecond(ZoneOffset.UTC)
}

fun getTimestamp(long: Long): Timestamp{
    return Timestamp((long-3600) * 1000)
}

data class ErrorMessage (
    val code: Int,
    val title: String,
    val description: String?
) {
    override fun toString(): String {
        var str: String? = null
        if(description != null) {
            str = "\"${description}\""
        }
        return "{\"code\":${code},\"title\":\"${title}\",\"description\":${str}}"
    }
}

fun responseNotFoundError(message: String?): ResponseEntity<Any> {
    return ResponseEntity<Any>(ErrorMessage(404, "Not Found Error", message), HttpStatus.NOT_FOUND)
}

fun responseConflictError(message: String?): ResponseEntity<Any> {
    return ResponseEntity<Any>(ErrorMessage(409, "Conflict Error", message), HttpStatus.CONFLICT)
}

fun responseServerErrror(message: String?): ResponseEntity<Any> {
    return ResponseEntity<Any>(ErrorMessage(500, "Server Error", message), HttpStatus.INTERNAL_SERVER_ERROR)
}

fun responseOk(): ResponseEntity<Any> {
    return ResponseEntity<Any>(HttpStatus.OK)
}

fun responseOkWithBody(obj: Any): ResponseEntity<Any> {
    return ResponseEntity<Any>(obj, HttpStatus.OK)
}