package ps.g08.directsincro.database

import org.jdbi.v3.core.Jdbi
import org.springframework.stereotype.Component

@Component
class PlaceholderDataBase(private val source: Jdbi) {}