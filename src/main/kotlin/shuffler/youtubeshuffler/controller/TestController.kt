package shuffler.youtubeshuffler.controller

import org.springframework.util.ResourceUtils
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse


@CrossOrigin
@RestController
class TestController {


    @RequestMapping(path = ["/"])
    fun getString(response: HttpServletResponse) {
        response.contentType = "application/octet-stream"
        response.setHeader("Transfer-Encoding", "chunked")

        val buffer = ByteArray(32)

        println("BATERAM NO /")

        val outputStream = response.outputStream

        val file = ResourceUtils.getFile("classpath:tetris-mp3.mp3")

        val data = file.readBytes()
        val inputStream = data.inputStream()

        var bytesRead = inputStream.read(buffer)
        while (bytesRead > 0) {
            response.outputStream.write(buffer, 0, bytesRead)
            bytesRead = inputStream.read(buffer)
            Thread.sleep(500)
        }




    }
}