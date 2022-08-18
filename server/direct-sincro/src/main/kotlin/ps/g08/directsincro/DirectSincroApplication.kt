package ps.g08.directsincro

import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.KotlinPlugin
import org.postgresql.ds.PGSimpleDataSource
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import javax.sql.DataSource

@SpringBootApplication
class DirectSincroApplication{
	@Bean
	fun dataSource(): DataSource = PGSimpleDataSource().apply {
		url = System.getenv("DirectSincroDB") ?: throw Exception("Could not get environment variable 'PostgresSql'")
		//jdbc:postgresql://localhost:5432/directsincro?user=postgres&password=123
	}

	@Bean
	fun jdbi(dataSource: DataSource): Jdbi = Jdbi.create(dataSource).apply { installPlugin(KotlinPlugin()) }

}

fun main(args: Array<String>) {
	runApplication<DirectSincroApplication>(*args)
}
