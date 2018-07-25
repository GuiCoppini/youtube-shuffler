package shuffler.youtubeshuffler.service

import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.util.ResourceUtils
import reactor.core.publisher.Flux

@Service
class OutputBuffer {

    // TODO deve virar um property depois
    val bufferSize: Int = 1024
    var isPlaying: Boolean = false
    val inputStream = ResourceUtils.getFile("classpath:tetris-mp3.mp3")
            .readBytes()
            .inputStream()

    val buffer = ByteArray(bufferSize)

    @Async
    fun reactiveStartPlaying(): Flux<ByteArray> {
        var bytesRead = inputStream.read(buffer)

        return Flux.create<ByteArray> { sub ->
            if (bytesRead > 0)
                isPlaying = true // Evita fazer isso dentro do while toda hora

            while (bytesRead > 0) {
                sub.next(buffer.copyOf())
                bytesRead = inputStream.read(buffer, 0, bytesRead)
                Thread.sleep(100)
            }
            isPlaying = false
        }
    }

    fun readBuffer(): Flux<ByteArray> {
        return reactiveStartPlaying().doOnEach {

        }
    }

}
fun main(args: Array<String>) {

    var x = 0
    val flux = Flux.create<String> {
        x++
        while (true) {
            it.next("flux")
            println("x vale $x")
            Thread.sleep(5000)
        }
    }

    val thread1 = Thread {
        flux.doOnEach {
            println("t1 recebeu $it")
        }.subscribe()
    }

    val thread2 = Thread {
        flux.doOnEach {
            println("t2 recebeu $it")
        }
    }
    thread1.isDaemon = true
    thread1.start()
    thread2.start()

}





