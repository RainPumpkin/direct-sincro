package ps.g08.directsincro.controller

import org.springframework.web.bind.annotation.*
import ps.g08.directsincro.service.PlaceholdderService

@RestController
@RequestMapping("/api/project/{projectId}/issue/{issueId}/comment")
class PlaceholderController(
        private val service: PlaceholdderService
) {}