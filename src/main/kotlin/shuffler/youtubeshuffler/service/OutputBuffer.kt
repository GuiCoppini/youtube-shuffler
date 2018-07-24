package shuffler.youtubeshuffler.service

import org.springframework.stereotype.Service
import org.springframework.util.ResourceUtils
import reactor.core.publisher.Flux
import java.io.OutputStream
import java.util.*

@Service
class OutputBuffer {

    // TODO deve virar um property depois
    val bufferSize: Int = 128
    val buffer = ByteArray(bufferSize)
    var isPlaying: Boolean = false

    fun startPlaying(outputStreamer: OutputStream) {
        if(isPlaying) {
            outputStreamer.write(buffer)
        }

        // preenche o buffer de N em N bytes a cada X ms
//        val fileBuffer = ByteArray(this.bufferSize)

        val file = ResourceUtils.getFile("classpath:tetris-mp3.mp3")
        val inputStream = file.readBytes().inputStream()

        var bytesRead = inputStream.read(buffer)

        if(bytesRead > 0)
            isPlaying = true // Evita fazer isso dentro do while toda hora

        while (bytesRead > 0) {
            bytesRead = inputStream.read(buffer)
            println("Buffer do OutputBuffer tem: ${Arrays.toString(buffer)}")
            outputStreamer.write(buffer)
            Thread.sleep(50)
        }
        isPlaying = false
    }

    fun reactiveStartPlaying() : Flux<ByteArray> {
        return Flux.create<ByteArray> { sub ->
            val file = ResourceUtils.getFile("classpath:tetris-mp3.mp3")
            val inputStream = file.readBytes().inputStream()

            var bytesRead = inputStream.read(buffer)

            if(bytesRead > 0)
                isPlaying = true // Evita fazer isso dentro do while toda hora

            while (bytesRead > 0) {
                bytesRead = inputStream.read(buffer)
                println("Buffer do OutputBuffer tem: ${Arrays.toString(buffer)}")
                sub.next(buffer)
                Thread.sleep(50)
            }
            isPlaying = false

        }
    }
}