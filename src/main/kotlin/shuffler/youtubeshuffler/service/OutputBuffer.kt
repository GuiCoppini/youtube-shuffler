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

    val inputStream = ResourceUtils.getFile("classpath:tetris-mp3.mp3")
            .readBytes()
            .inputStream()

    var bytesRead = inputStream.read(buffer)

    fun reactiveStartPlaying(): Flux<ByteArray> {
        return Flux.create<ByteArray> { sub ->
            if (bytesRead > 0)
                isPlaying = true // Evita fazer isso dentro do while toda hora

            while (bytesRead > 0) {
                sub.next(buffer)
                bytesRead = inputStream.read(buffer, 0, bytesRead)
                Thread.sleep(100)
            }
            isPlaying = false
        }
    }

    fun readBuffer(): Flux<ByteArray> {
        return Flux.create {
            reactiveStartPlaying().doOnNext { completeBuffer ->
                it.next(completeBuffer)
            }
        }
    }
}
