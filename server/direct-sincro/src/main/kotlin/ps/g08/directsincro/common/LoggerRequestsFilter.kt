package ps.g08.directsincro.common

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class LoggerRequestsFilter: Filter {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(LoggerRequestsFilter::class.java)
    }

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val httpRequest = request as HttpServletRequest
        logger.info("${httpRequest.method}:${httpRequest.requestURI}")
        chain.doFilter(request, response)
    }
}