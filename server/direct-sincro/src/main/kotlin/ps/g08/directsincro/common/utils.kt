package ps.g08.directsincro.common

import java.sql.Timestamp
import java.time.ZoneOffset

fun getEpoch(timestamp: Timestamp?): Long? {
    if (timestamp == null) return null
    val localDateTime = timestamp.toLocalDateTime()
    return localDateTime.toEpochSecond(ZoneOffset.UTC)
}

fun getTimestamp(long: Long?): Timestamp? {
    if(long == null) return null
    return Timestamp(long * 1000)
}