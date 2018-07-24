package shuffler.youtubeshuffler.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import shuffler.youtubeshuffler.service.OutputBuffer
import java.util.*
import javax.servlet.http.HttpServletResponse


@CrossOrigin
@RestController
class TestController {

    @Autowired
    lateinit var outputBuffer: OutputBuffer

    @RequestMapping(path = ["/"])
    fun getString(response: HttpServletResponse) {
        response.contentType = "application/octet-stream"
        response.setHeader("Transfer-Encoding", "chunked")

        println("Comecou a tocar")
        outputBuffer.reactiveStartPlaying().doOnNext {
            response.outputStream.write(it)
            response.outputStream.flush()
            println("Escreveu ${Arrays.toString(it)}")
        }.subscribe()


    }
}
