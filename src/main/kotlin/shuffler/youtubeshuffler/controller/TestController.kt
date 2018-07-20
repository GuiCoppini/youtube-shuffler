package shuffler.youtubeshuffler.controller

import org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import javax.servlet.http.HttpServletResponse

@RestController
class TestController {

    @RequestMapping(path = ["/"], produces = [TEXT_EVENT_STREAM_VALUE])
    fun getString(response: HttpServletResponse): Mono<String> {
//        response.contentType = "multipart/x-mixed-replace;"
        return Mono.create {
            var str = "a"
            val out = response.outputStream
            while (true) {
                str += "a"
                out.print(str)
            }
        }
    }

}