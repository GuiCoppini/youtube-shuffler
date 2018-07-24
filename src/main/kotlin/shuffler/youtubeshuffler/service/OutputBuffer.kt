package shuffler.youtubeshuffler.service

import org.springframework.stereotype.Service
import org.springframework.util.ResourceUtils
import reactor.core.publisher.Flux

@Service
class OutputBuffer {

    // TODO deve virar um property depois
    val bufferSize: Int = 1024
    val buffer = ByteArray(bufferSize)
    var isPlaying: Boolean = false

    fun reactiveStartPlaying(): Flux<ByteArray> {
        return Flux.create<ByteArray> { sub ->
            if (isPlaying) {
                sub.next(buffer)
            } else {
                val buf = ByteArray(1024)
                val file = ResourceUtils.getFile("classpath:tetris-mp3.mp3")
                val data = file.readBytes()
                val inputStream = data.inputStream()

                var bytesRead = inputStream.read(buf)

                if (bytesRead > 0)
                    isPlaying = true // Evita fazer isso dentro do while toda hora

                while (bytesRead > 0) {
                    sub.next(buf)
                    bytesRead = inputStream.read(buf, 0, bytesRead)
                    Thread.sleep(500)
                }
                isPlaying = false
            }
        }
    }
}