package ps.g08.directsincro.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/subscritores")
class SubscritorController {

    @GetMapping("/{nif}")
    fun getSubscritor(){

    }

    //create recebe tudo de pessoa+sub

}