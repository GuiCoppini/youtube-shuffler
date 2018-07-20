package shuffler.youtubeshuffler.controller

import org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux


@RestController
class TestController {

    @RequestMapping(path = ["/"], produces = [TEXT_EVENT_STREAM_VALUE])
    fun getString(): Flux<String> {
        return Flux.create {
            var str = "a"
            while (true) {
                str += "a"
                it.next(str)
                Thread.sleep(500)
            }
        }
    }

}