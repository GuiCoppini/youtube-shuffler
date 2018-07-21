package shuffler.youtubeshuffler.controller

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

        val out = response.outputStream

        var totalFramesRead = 0
        val fileIn = ResourceUtils.getFile("classpath:tetris-mp3.mp3")

//        val audioInputStream = AudioSystem.getAudioInputStream(fileIn)

        val bytes = fileIn.readBytes()


        return Flux.create {
            //            while (true) {
            bytes.forEach { out.print(it.toString()) }
//            }
        }
    }

}