package shuffler.youtubeshuffler.controller

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64EncoderStream
import org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE
import org.springframework.util.ResourceUtils
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import javax.servlet.http.HttpServletResponse


@RestController
class TestController {

    @RequestMapping(path = ["/"], produces = [TEXT_EVENT_STREAM_VALUE])
    fun getString(response: HttpServletResponse): Flux<String> {
//        response.contentType = "multipart/x-mixed-replace;"
        println("BATERAM NO /")

        val out = response.outputStream

        val fileIn = ResourceUtils.getFile("classpath:tetris-mp3.mp3")

        val bytes = fileIn.readBytes()

        val encoded = BASE64EncoderStream.encode(bytes)

        return Flux.create {
            out.write(bytes)
        }
    }
}