package shuffler.youtubeshuffler.controller

import org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE
import org.springframework.util.ResourceUtils
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse


@CrossOrigin
@RestController
class TestController {


    @RequestMapping(path = ["/"], produces = [TEXT_EVENT_STREAM_VALUE])
    fun getString(response: HttpServletResponse) {
        response.contentType = "audio/mpeg3"

        println("BATERAM NO /")

        val out = response.outputStream

        val fileIn = ResourceUtils.getFile("classpath:tetris-mp3.mp3")
        val fileBytes = fileIn.readBytes()

        out.write(fileBytes)
        out.flush()
        data = inputStream.read()
    }

//    @RequestMapping(path = ["/"])
//    fun getAudioBytes(response: HttpServletResponse) {
//        println("BATERAM NO /")
//        response.contentType = "audio/mpeg;"
//
//        val out = response.outputStream
//
//        val fileIn = ResourceUtils.getFile("classpath:tetrismp3.mp3")
////        val musicBytes = fileIn.readBytes()
//
//        val inputStream = fileIn.readBytes().inputStream()
////        val outStream: OutputStream = fileIn.outputStream()
//
//        var data = inputStream
//        println(data)
//        inputStream.copyTo(out)
//        out.flush()
//    }
}